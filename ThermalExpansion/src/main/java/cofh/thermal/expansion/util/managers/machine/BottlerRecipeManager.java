package cofh.thermal.expansion.util.managers.machine;

import cofh.core.fluid.IFluidStackAccess;
import cofh.core.inventory.FalseIInventory;
import cofh.core.inventory.IItemStackAccess;
import cofh.core.util.ComparableItemStack;
import cofh.core.util.helpers.FluidHelper;
import cofh.thermal.core.ThermalCore;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.managers.AbstractManager;
import cofh.thermal.core.util.managers.IRecipeManager;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.internal.SimpleMachineRecipe;
import cofh.thermal.expansion.init.TExpRecipeTypes;
import cofh.thermal.expansion.util.recipes.machine.BottlerRecipe;
import cofh.thermal.expansion.util.recipes.machine.BottlerRecipeNBT;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.*;

import static cofh.core.util.constants.Constants.*;
import static cofh.core.util.references.CoreReferences.FLUID_POTION;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class BottlerRecipeManager extends AbstractManager implements IRecipeManager {

    private static final BottlerRecipeManager INSTANCE = new BottlerRecipeManager();
    protected static final int DEFAULT_ENERGY = 400;

    protected static boolean defaultBucketRecipes = true;
    protected static boolean defaultPotionRecipes = true;

    protected Map<List<Integer>, IMachineRecipe> recipeMap = new Object2ObjectOpenHashMap<>();
    protected Set<Fluid> validFluids = new ObjectOpenHashSet<>();
    protected Set<ComparableItemStack> validItems = new ObjectOpenHashSet<>();

    protected int maxOutputItems;
    protected int maxOutputFluids;

    public static BottlerRecipeManager instance() {

        return INSTANCE;
    }

    private BottlerRecipeManager() {

        super(DEFAULT_ENERGY);
        this.maxOutputItems = 1;
        this.maxOutputFluids = 0;
    }

    public void addRecipe(ThermalRecipe recipe) {

        if (!recipe.getInputItems().isEmpty()) {
            for (ItemStack recipeInput : recipe.getInputItems().get(0).getMatchingStacks()) {
                addRecipe(recipe.getEnergy(), recipe.getExperience(), recipe.getMinTicks(), singletonList(recipeInput), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
            }
        } else {
            addRecipe(recipe.getEnergy(), recipe.getExperience(), recipe.getMinTicks(), Collections.emptyList(), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
        }
    }

    public boolean validItem(ItemStack item) {

        return validItems.contains(convert(item));
    }

    public boolean validFluid(FluidStack fluid) {

        return validFluids.contains(fluid.getFluid());
    }

    protected void clear() {

        recipeMap.clear();
        validFluids.clear();
        validItems.clear();
        convertedRecipes.clear();
    }

    // region RECIPES
    protected IMachineRecipe getRecipe(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        if (inputSlots.isEmpty() && inputTanks.isEmpty() || inputSlots.get(0).isEmpty() && inputTanks.get(0).isEmpty()) {
            return null;
        }
        if (inputTanks.isEmpty() || inputTanks.get(0).isEmpty()) {
            ItemStack inputItem = inputSlots.get(0).getItemStack();
            return recipeMap.get(singletonList(convert(inputItem).hashCode()));
        }
        if (inputSlots.isEmpty() || inputSlots.get(0).isEmpty()) {
            FluidStack inputFluid = inputTanks.get(0).getFluidStack();
            return recipeMap.get(singletonList(FluidHelper.fluidHashcodeNoTag(inputFluid)));
        }
        ItemStack inputItem = inputSlots.get(0).getItemStack();
        FluidStack inputFluid = inputTanks.get(0).getFluidStack();
        return recipeMap.get(asList(convert(inputItem).hashCode(), FluidHelper.fluidHashcodeNoTag(inputFluid)));
    }

    protected IMachineRecipe addRecipe(int energy, float experience, int minTicks, List<ItemStack> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> chance, List<FluidStack> outputFluids) {

        if (inputItems.isEmpty() || inputFluids.isEmpty() || outputItems.size() > maxOutputItems || outputFluids.size() > maxOutputFluids || energy <= 0) {
            return null;
        }
        ItemStack inputItem = inputItems.get(0);
        if (inputItem.isEmpty()) {
            return null;
        }
        FluidStack inputFluid = inputFluids.get(0);
        if (inputFluid.isEmpty()) {
            return null;
        }
        for (ItemStack stack : outputItems) {
            if (stack.isEmpty()) {
                return null;
            }
        }
        validItems.add(convert(inputItem));
        validFluids.add(inputFluid.getFluid());
        energy = (energy * getDefaultScale()) / 100;

        SimpleMachineRecipe recipe = new SimpleMachineRecipe(energy, experience, minTicks, inputItems, inputFluids, outputItems, chance, outputFluids);
        recipeMap.put(asList(convert(inputItem).hashCode(), FluidHelper.fluidHashcodeNoTag(inputFluid)), recipe);
        return recipe;
    }

    protected IMachineRecipe addRecipe(IMachineRecipe recipe) {

        ItemStack inputItem = recipe.getInputItems().get(0);
        if (inputItem.isEmpty()) {
            return null;
        }
        FluidStack inputFluid = recipe.getInputFluids().get(0);
        if (inputFluid.isEmpty()) {
            return null;
        }
        validItems.add(convert(inputItem));
        validFluids.add(inputFluid.getFluid());
        recipeMap.put(asList(convert(inputItem).hashCode(), FluidHelper.fluidHashcodeNoTag(inputFluid)), recipe);
        return recipe;
    }
    // endregion

    // region IRecipeManager
    @Override
    public IMachineRecipe getRecipe(IThermalInventory inventory) {

        return getRecipe(inventory.inputSlots(), inventory.inputTanks());
    }

    @Override
    public List<IMachineRecipe> getRecipeList() {

        return new ArrayList<>(recipeMap.values());
    }
    // endregion

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        int energy = (getDefaultEnergy() * getDefaultScale()) / 100;
        if (defaultBucketRecipes) {
            ThermalCore.LOG.debug("Adding default Bucket recipes to the Fluid Encapsulator...");
            Set<Fluid> bucketFluids = new HashSet<>(32);
            for (Fluid fluid : ForgeRegistries.FLUIDS) {
                if (fluid instanceof FlowingFluid) {
                    Fluid still = ((FlowingFluid) fluid).getStillFluid();
                    ItemStack bucket = new ItemStack(still.getFilledBucket());
                    if (!bucket.isEmpty() && !bucketFluids.contains(still)) {
                        addRecipe(convert(energy, 0.0F, -1, new ItemStack(Items.BUCKET), new FluidStack(still, BUCKET_VOLUME), bucket));
                        bucketFluids.add(still);
                    }
                }
            }
        }
        if (defaultPotionRecipes) {
            ThermalCore.LOG.debug("Adding default Potion recipes to the Fluid Encapsulator...");
            addRecipe(convert(energy, 0.0F, -1, new ItemStack(Items.GLASS_BOTTLE), new FluidStack(FLUID_POTION, BOTTLE_VOLUME), new ItemStack(Items.POTION)));
        }
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipeTypes.RECIPE_BOTTLER);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addRecipe((ThermalRecipe) entry.getValue());
        }
    }
    // endregion

    // region CONVERSION
    protected List<BottlerRecipe> convertedRecipes = new ArrayList<>();

    public List<BottlerRecipe> getConvertedRecipes() {

        return convertedRecipes;
    }

    protected BottlerRecipeNBT convert(int energy, float experience, int minTicks, @Nonnull ItemStack inputItem, @Nonnull FluidStack inputFluid, @Nonnull ItemStack outputItem) {

        System.out.println("CALLED CONVERT");
        convertedRecipes.add(new BottlerRecipe(new ResourceLocation(ID_THERMAL, "bottler_" + inputItem.getItem().getRegistryName().getPath()), energy, experience, minTicks, singletonList(Ingredient.fromStacks(inputItem)), singletonList(inputFluid), singletonList(outputItem), emptyList(), emptyList()));
        return new BottlerRecipeNBT(energy, experience, minTicks, inputItem, inputFluid, outputItem);
    }
    // endregion
}
