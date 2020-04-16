package cofh.thermal.core.util.managers.machine;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.FalseIInventory;
import cofh.lib.inventory.IItemStackAccess;
import cofh.lib.util.ComparableItemStack;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.init.ThermalRecipeTypes;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.managers.AbstractManager;
import cofh.thermal.core.util.managers.IRecipeManager;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.core.util.recipes.internal.BaseMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.machine.BottlerRecipePotion;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;

import static cofh.lib.util.constants.Constants.BOTTLE_VOLUME;
import static cofh.lib.util.references.CoreReferences.FLUID_POTION;
import static java.util.Arrays.asList;

public class BottlerRecipeManager extends AbstractManager implements IRecipeManager {

    private static final BottlerRecipeManager INSTANCE = new BottlerRecipeManager();
    protected static final int DEFAULT_ENERGY = 2000;

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
                addRecipe(recipe.getEnergy(), recipe.getExperience(), Collections.singletonList(recipeInput), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
            }
        } else {
            addRecipe(recipe.getEnergy(), recipe.getExperience(), Collections.emptyList(), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
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
    }

    protected IMachineRecipe getRecipe(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        if (inputSlots.isEmpty() && inputTanks.isEmpty() || inputSlots.get(0).isEmpty() && inputTanks.get(0).isEmpty()) {
            return null;
        }
        if (inputTanks.isEmpty() || inputTanks.get(0).isEmpty()) {
            ItemStack inputItem = inputSlots.get(0).getItemStack();
            return recipeMap.get(Collections.singletonList(convert(inputItem).hashCode()));
        }
        if (inputSlots.isEmpty() || inputSlots.get(0).isEmpty()) {
            FluidStack inputFluid = inputTanks.get(0).getFluidStack();
            return recipeMap.get(Collections.singletonList(FluidHelper.fluidHashcodeNoTag(inputFluid)));
        }
        ItemStack inputItem = inputSlots.get(0).getItemStack();
        FluidStack inputFluid = inputTanks.get(0).getFluidStack();
        return recipeMap.get(asList(convert(inputItem).hashCode(), FluidHelper.fluidHashcodeNoTag(inputFluid)));
    }

    protected IMachineRecipe addRecipe(int energy, float experience, List<ItemStack> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> chance, List<FluidStack> outputFluids) {

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

        BaseMachineRecipe recipe = new BaseMachineRecipe(energy, experience, inputItems, inputFluids, outputItems, chance, outputFluids);
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
        if (defaultPotionRecipes) {
            int energy = (getDefaultEnergy() * getDefaultScale()) / 100;
            addRecipe(new BottlerRecipePotion(energy, 0.0F, new ItemStack(Items.GLASS_BOTTLE), new FluidStack(FLUID_POTION, BOTTLE_VOLUME), new ItemStack(Items.POTION)));
        }
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(ThermalRecipeTypes.RECIPE_BOTTLER);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addRecipe((ThermalRecipe) entry.getValue());
        }
    }
    // endregion
}
