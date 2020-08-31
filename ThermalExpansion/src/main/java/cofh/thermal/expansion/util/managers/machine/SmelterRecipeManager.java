package cofh.thermal.expansion.util.managers.machine;

import cofh.core.fluid.IFluidStackAccess;
import cofh.core.inventory.FalseIInventory;
import cofh.core.inventory.IItemStackAccess;
import cofh.core.util.ComparableItemStack;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.managers.AbstractManager;
import cofh.thermal.core.util.managers.IRecipeManager;
import cofh.thermal.core.util.recipes.ThermalCatalyst;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.core.util.recipes.internal.BaseMachineCatalyst;
import cofh.thermal.core.util.recipes.internal.CatalyzedMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IRecipeCatalyst;
import cofh.thermal.expansion.init.TExpRecipeTypes;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.*;

import static java.util.Arrays.asList;

public class SmelterRecipeManager extends AbstractManager implements IRecipeManager {

    private static final SmelterRecipeManager INSTANCE = new SmelterRecipeManager();
    protected static final int DEFAULT_ENERGY = 3200;

    protected Int2ObjectMap<IMachineRecipe> recipeMap = new Int2ObjectOpenHashMap<>();
    protected Map<ComparableItemStack, IRecipeCatalyst> catalystMap = new Object2ObjectOpenHashMap<>();
    protected Set<ComparableItemStack> validItems = new ObjectOpenHashSet<>();

    protected int maxInputItems;
    protected int maxOutputItems;
    protected int maxOutputFluids;

    public static SmelterRecipeManager instance() {

        return INSTANCE;
    }

    private SmelterRecipeManager() {

        super(DEFAULT_ENERGY);
        this.maxInputItems = 3;
        this.maxOutputItems = 4;
        this.maxOutputFluids = 0;
    }

    public void addRecipe(ThermalRecipe recipe) {

        switch (recipe.getInputItems().size()) {
            case 1:
                for (ItemStack firstInput : recipe.getInputItems().get(0).getMatchingStacks()) {
                    addRecipe(recipe.getEnergy(), recipe.getExperience(), recipe.getMinTicks(), Collections.singletonList(firstInput), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
                }
                return;
            case 2:
                for (ItemStack firstInput : recipe.getInputItems().get(0).getMatchingStacks()) {
                    for (ItemStack secondInput : recipe.getInputItems().get(1).getMatchingStacks()) {
                        addRecipe(recipe.getEnergy(), recipe.getExperience(), recipe.getMinTicks(), asList(firstInput, secondInput), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
                    }
                }
                return;
            case 3:
                for (ItemStack firstInput : recipe.getInputItems().get(0).getMatchingStacks()) {
                    for (ItemStack secondInput : recipe.getInputItems().get(1).getMatchingStacks()) {
                        for (ItemStack thirdInput : recipe.getInputItems().get(2).getMatchingStacks()) {
                            addRecipe(recipe.getEnergy(), recipe.getExperience(), recipe.getMinTicks(), asList(firstInput, secondInput, thirdInput), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
                        }
                    }
                }
                return;
            default:
        }
    }

    public boolean validItem(ItemStack item) {

        return validItems.contains(convert(item));
    }

    protected void clear() {

        recipeMap.clear();
        catalystMap.clear();
        validItems.clear();
    }

    // region RECIPES
    protected IMachineRecipe getRecipe(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        if (inputSlots.isEmpty()) {
            return null;
        }
        int recipeKey = 0;
        for (int i = 0; i < maxInputItems; ++i) {
            recipeKey += inputSlots.get(i).isEmpty() ? 0 : convert(inputSlots.get(i).getItemStack()).hashCode();
        }
        return recipeMap.get(recipeKey);
    }

    protected IMachineRecipe addRecipe(int energy, float experience, int minTicks, List<ItemStack> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> chance, List<FluidStack> outputFluids) {

        if (inputItems.isEmpty() || outputItems.isEmpty() && outputFluids.isEmpty() || outputItems.size() > maxOutputItems || outputFluids.size() > maxOutputFluids || energy <= 0) {
            return null;
        }
        for (ItemStack stack : inputItems) {
            if (stack.isEmpty()) {
                return null;
            }
        }
        for (ItemStack stack : outputItems) {
            if (stack.isEmpty()) {
                return null;
            }
        }
        for (FluidStack stack : outputFluids) {
            if (stack.isEmpty()) {
                return null;
            }
        }
        energy = (energy * getDefaultScale()) / 100;

        int recipeKey = 0;
        for (ItemStack stack : inputItems) {
            ComparableItemStack compStack = convert(stack);
            recipeKey += compStack.hashCode();
            validItems.add(compStack);
        }
        InternalSmelterRecipe recipe = new InternalSmelterRecipe(energy, experience, minTicks, inputItems, inputFluids, outputItems, chance, outputFluids);
        recipeMap.put(recipeKey, recipe);
        return recipe;
    }
    // endregion

    // region CATALYSTS
    public IRecipeCatalyst getCatalyst(IItemStackAccess input) {

        return catalystMap.get(convert(input.getItemStack()));
    }

    public IRecipeCatalyst getCatalyst(ItemStack input) {

        return catalystMap.get(convert(input));
    }

    public void addCatalyst(ThermalCatalyst catalyst) {

        for (ItemStack ingredient : catalyst.getIngredient().getMatchingStacks()) {
            addCatalyst(ingredient, catalyst.getPrimaryMod(), catalyst.getSecondaryMod(), catalyst.getEnergyMod(), catalyst.getMinChance(), catalyst.getUseChance());
        }
    }

    public IRecipeCatalyst addCatalyst(ItemStack input, float primaryMod, float secondaryMod, float energyMod, float minChance, float useChance) {

        if (input == null || input.isEmpty()) {
            return null;
        }
        BaseMachineCatalyst catalyst = new BaseMachineCatalyst(primaryMod, secondaryMod, energyMod, minChance, useChance);
        catalystMap.put(convert(input), catalyst);
        return catalyst;
    }

    public boolean validCatalyst(ItemStack input) {

        return getCatalyst(input) != null;
    }

    public IRecipeCatalyst removeCatalyst(ItemStack input) {

        return catalystMap.remove(convert(input));
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
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipeTypes.RECIPE_SMELTER);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addRecipe((ThermalRecipe) entry.getValue());
        }
        Map<ResourceLocation, IRecipe<FalseIInventory>> catalysts = recipeManager.getRecipes(TExpRecipeTypes.CATALYST_SMELTER);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : catalysts.entrySet()) {
            addCatalyst((ThermalCatalyst) entry.getValue());
        }
    }
    // endregion

    // region CATALYZED RECIPE

    protected static class InternalSmelterRecipe extends CatalyzedMachineRecipe {

        public InternalSmelterRecipe(int energy, float experience, int minTicks, @Nullable List<ItemStack> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> chance, @Nullable List<FluidStack> outputFluids) {

            super(3, energy, experience, minTicks, inputItems, inputFluids, outputItems, chance, outputFluids);
        }

        @Override
        public IRecipeCatalyst getCatalyst(ItemStack input) {

            return instance().getCatalyst(input);
        }

    }
    // endregion
}
