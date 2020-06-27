package cofh.thermal.expansion.util.managers.machine;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.FalseIInventory;
import cofh.lib.inventory.IItemStackAccess;
import cofh.lib.util.ComparableItemStack;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.managers.AbstractManager;
import cofh.thermal.core.util.managers.IRecipeManager;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.internal.SimpleMachineRecipe;
import cofh.thermal.expansion.util.recipes.TExpRecipeTypes;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;

import static java.util.Arrays.asList;

public class PressRecipeManager extends AbstractManager implements IRecipeManager {

    private static final PressRecipeManager INSTANCE = new PressRecipeManager();
    protected static final int DEFAULT_ENERGY = 2400;

    protected Map<List<ComparableItemStack>, IMachineRecipe> recipeMap = new Object2ObjectOpenHashMap<>();
    protected Set<ComparableItemStack> validInputs = new ObjectOpenHashSet<>();
    protected Set<ComparableItemStack> validDies = new ObjectOpenHashSet<>();

    protected int maxOutputItems;
    protected int maxOutputFluids;

    public static PressRecipeManager instance() {

        return INSTANCE;
    }

    private PressRecipeManager() {

        super(DEFAULT_ENERGY);
        this.maxOutputItems = 1;
        this.maxOutputFluids = 1;
    }

    public void addRecipe(ThermalRecipe recipe) {

        if (recipe.getInputItems().size() == 1) {
            for (ItemStack recipeInput : recipe.getInputItems().get(0).getMatchingStacks()) {
                addRecipe(recipe.getEnergy(), recipe.getExperience(), Collections.singletonList(recipeInput), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
            }
        } else {
            // The die should never have multiple variations but eh, who knows?
            for (ItemStack dieInput : recipe.getInputItems().get(1).getMatchingStacks()) {
                for (ItemStack recipeInput : recipe.getInputItems().get(0).getMatchingStacks()) {
                    addRecipe(recipe.getEnergy(), recipe.getExperience(), asList(recipeInput, dieInput), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
                }
            }
        }
    }

    public boolean validInput(ItemStack item) {

        return validInputs.contains(convert(item));
    }

    public boolean validDie(ItemStack item) {

        return validDies.contains(convert(item));
    }

    protected void clear() {

        recipeMap.clear();
        validInputs.clear();
        validDies.clear();
    }

    protected ArrayList<ComparableItemStack> getKeyFromSlots(List<? extends IItemStackAccess> inputSlots) {

        ArrayList<ComparableItemStack> key = new ArrayList<>();
        for (IItemStackAccess slot : inputSlots) {
            if (!slot.isEmpty()) {
                key.add(convert(slot.getItemStack()));
            }
        }
        return key;
    }

    protected ArrayList<ComparableItemStack> getKeyFromStacks(List<ItemStack> inputStacks) {

        ArrayList<ComparableItemStack> key = new ArrayList<>();
        for (ItemStack stack : inputStacks) {
            if (!stack.isEmpty()) {
                key.add(convert(stack));
            }
        }
        return key;
    }

    protected IMachineRecipe getRecipe(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        if (inputSlots.isEmpty() || inputSlots.get(0).isEmpty()) {
            return null;
        }
        return recipeMap.get(getKeyFromSlots(inputSlots));
    }

    protected IMachineRecipe addRecipe(int energy, float experience, List<ItemStack> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> chance, List<FluidStack> outputFluids) {

        if (inputItems.isEmpty() || outputItems.isEmpty() && outputFluids.isEmpty() || outputItems.size() > maxOutputItems || outputFluids.size() > maxOutputFluids || energy <= 0) {
            return null;
        }
        if (inputItems.get(0).isEmpty()) {
            return null;
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

        SimpleMachineRecipe recipe = new SimpleMachineRecipe(energy, experience, inputItems, inputFluids, outputItems, chance, outputFluids);
        validInputs.add(convert(inputItems.get(0)));

        if (inputItems.size() > 1 && !inputItems.get(1).isEmpty()) {
            validDies.add(convert(inputItems.get(1)));
        }
        recipeMap.put(getKeyFromStacks(inputItems), recipe);
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
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipeTypes.RECIPE_PRESS);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addRecipe((ThermalRecipe) entry.getValue());
        }
    }
    // endregion
}
