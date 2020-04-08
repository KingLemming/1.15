package cofh.thermal.core.util.managers;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;
import cofh.lib.inventory.ItemStackHolder;
import cofh.lib.util.ComparableItemStack;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.recipes.ThermalCatalyst;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.core.util.recipes.internal.BaseMachineCatalyst;
import cofh.thermal.core.util.recipes.internal.BaseMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IRecipeCatalyst;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;

/**
 * Basic recipe manager - single item key'd.
 */
public abstract class SingleItemRecipeManager extends AbstractManager implements IRecipeManager {

    protected Map<ComparableItemStack, IMachineRecipe> recipeMap = new Object2ObjectOpenHashMap<>();

    protected int maxOutputItems;
    protected int maxOutputFluids;

    protected SingleItemRecipeManager(int defaultEnergy, int maxOutputItems, int maxOutputFluids) {

        super(defaultEnergy);
        this.maxOutputItems = maxOutputItems;
        this.maxOutputFluids = maxOutputFluids;
    }

    public void addRecipe(ThermalRecipe recipe) {

        for (ItemStack recipeInput : recipe.getInputItems().get(0).getMatchingStacks()) {
            addRecipe(recipe.getEnergy(), recipe.getExperience(), Collections.singletonList(recipeInput), recipe.getInputFluids(), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
        }
    }

    public boolean validRecipe(ItemStack input) {

        return getRecipe(input) != null;
    }

    protected void clear() {

        recipeMap.clear();
    }

    protected IMachineRecipe getRecipe(ItemStack input) {

        return getRecipe(Collections.singletonList(new ItemStackHolder(input)), Collections.emptyList());
    }

    protected IMachineRecipe getRecipe(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        if (inputSlots.isEmpty() || inputSlots.get(0).isEmpty()) {
            return null;
        }
        return recipeMap.get(convert(inputSlots.get(0).getItemStack()));
    }

    protected IMachineRecipe addRecipe(int energy, float experience, List<ItemStack> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> chance, List<FluidStack> outputFluids) {

        if (inputItems.isEmpty() || outputItems.isEmpty() && outputFluids.isEmpty() || outputItems.size() > maxOutputItems || outputFluids.size() > maxOutputFluids || energy <= 0) {
            return null;
        }
        ItemStack input = inputItems.get(0);
        if (input.isEmpty()) {
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

        BaseMachineRecipe recipe = new BaseMachineRecipe(energy, experience, inputItems, inputFluids, outputItems, chance, outputFluids);
        recipeMap.put(convert(input), recipe);
        return recipe;
    }

    protected IMachineRecipe addRecipe(int energy, float experience, ItemStack input, ItemStack output) {

        return addRecipe(energy, experience, Collections.singletonList(input), Collections.emptyList(), Collections.singletonList(output), Collections.singletonList(BASE_CHANCE_LOCKED), Collections.emptyList());
    }

    protected IMachineRecipe addRecipe(int energy, float experience, ItemStack input, ItemStack output, float chance) {

        return addRecipe(energy, experience, Collections.singletonList(input), Collections.emptyList(), Collections.singletonList(output), Collections.singletonList(chance), Collections.emptyList());
    }

    protected IMachineRecipe addRecipe(int energy, float experience, ItemStack input, List<ItemStack> output, List<Float> chance) {

        return addRecipe(energy, experience, Collections.singletonList(input), Collections.emptyList(), output, chance, Collections.emptyList());
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

    // region CATALYZED CLASS
    public static abstract class Catalyzed extends SingleItemRecipeManager {

        protected Map<ComparableItemStack, IRecipeCatalyst> catalystMap = new Object2ObjectOpenHashMap<>();

        protected Catalyzed(int defaultEnergy, int maxOutputItems, int maxOutputFluids) {

            super(defaultEnergy, maxOutputItems, maxOutputFluids);
        }

        protected void clear() {

            super.clear();
            catalystMap.clear();
        }

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
    }
    // endregion
}
