package cofh.thermal.core.util.managers;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;
import cofh.lib.inventory.ItemStackHolder;
import cofh.lib.util.ComparableItemStack;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.recipes.ThermalIRecipe;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IRecipeCatalyst;
import cofh.thermal.core.util.recipes.internal.SimpleCatalyst;
import cofh.thermal.core.util.recipes.internal.SimpleItemRecipe;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;

/**
 * Basic recipe manager - single item key'd.
 */
public abstract class SimpleItemRecipeManager extends AbstractManager implements IRecipeManager {

    protected Map<ComparableItemStack, IMachineRecipe> recipeMap = new Object2ObjectOpenHashMap<>();

    protected int maxOutputItems;
    protected int maxOutputFluids;

    protected SimpleItemRecipeManager(int defaultEnergy, int maxOutputItems, int maxOutputFluids) {

        super(defaultEnergy);
        this.maxOutputItems = maxOutputItems;
        this.maxOutputFluids = maxOutputFluids;
    }

    protected void clear() {

        recipeMap.clear();
    }

    public boolean validRecipe(ItemStack input) {

        return getRecipe(input) != null;
    }

    public IMachineRecipe getRecipe(ItemStack input) {

        return getRecipe(Collections.singletonList(new ItemStackHolder(input)), Collections.emptyList());
    }

    public IMachineRecipe removeRecipe(ItemStack input) {

        return recipeMap.remove(convert(input));
    }

    public IMachineRecipe addRecipe(ThermalIRecipe recipe) {

        return addRecipe(recipe.getEnergy(), recipe.getInputItems().get(0), recipe.getOutputItems(), recipe.getOutputItemChances(), recipe.getOutputFluids());
    }

    public IMachineRecipe addRecipe(int energy, ItemStack input, ItemStack output) {

        return addRecipe(energy, input, output, BASE_CHANCE_LOCKED);
    }

    public IMachineRecipe addRecipe(int energy, ItemStack input, ItemStack output, float chance) {

        if (maxOutputItems <= 0 || input.isEmpty() || output.isEmpty() || energy <= 0) {
            return null;
        }
        energy = (energy * getDefaultScale()) / 100;

        SimpleItemRecipe recipe = new SimpleItemRecipe(energy, input, output, chance);
        recipeMap.put(convert(input), recipe);
        return recipe;
    }

    public IMachineRecipe addRecipe(int energy, ItemStack input, List<ItemStack> output, List<Float> chance) {

        if (input.isEmpty() || output.isEmpty() || output.size() > maxOutputItems || energy <= 0) {
            return null;
        }
        for (ItemStack stack : output) {
            if (stack.isEmpty()) {
                return null;
            }
        }
        energy = (energy * getDefaultScale()) / 100;

        SimpleItemRecipe recipe = new SimpleItemRecipe(energy, input, output, chance);
        recipeMap.put(convert(input), recipe);
        return recipe;
    }

    public IMachineRecipe addRecipe(int energy, ItemStack input, FluidStack output) {

        if (maxOutputFluids <= 0 || input.isEmpty() || output.isEmpty() || energy <= 0) {
            return null;
        }
        energy = (energy * getDefaultScale()) / 100;

        SimpleItemRecipe recipe = new SimpleItemRecipe(energy, input, output);
        recipeMap.put(convert(input), recipe);
        return recipe;
    }

    public IMachineRecipe addRecipe(int energy, ItemStack input, List<ItemStack> output, List<Float> chance, List<FluidStack> outputFluids) {

        if (input.isEmpty() || output.isEmpty() && outputFluids.isEmpty() || output.size() > maxOutputItems || outputFluids.size() > maxOutputFluids || energy <= 0) {
            return null;
        }
        for (ItemStack stack : output) {
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

        SimpleItemRecipe recipe = new SimpleItemRecipe(energy, input, output, chance, outputFluids);
        recipeMap.put(convert(input), recipe);
        return recipe;
    }

    // region IRecipeManager
    @Override
    public IMachineRecipe getRecipe(IThermalInventory inventory) {

        return getRecipe(inventory.inputSlots(), inventory.inputTanks());
    }

    public IMachineRecipe getRecipe(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        if (inputSlots.isEmpty() || inputSlots.get(0).isEmpty()) {
            return null;
        }
        return recipeMap.get(convert(inputSlots.get(0).getItemStack()));
    }

    @Override
    public List<IMachineRecipe> getRecipeList() {

        return new ArrayList<>(recipeMap.values());
    }
    // endregion

    // region IManager
    @Override
    public void refresh(RecipeManager recipeManager) {

    }
    // endregion

    // region CATALYZED CLASS
    public static abstract class Catalyzed extends SimpleItemRecipeManager {

        protected Map<ComparableItemStack, IRecipeCatalyst> catalystMap = new Object2ObjectOpenHashMap<>();

        protected Catalyzed(int defaultEnergy, int maxOutputItems, int maxOutputFluids) {

            super(defaultEnergy, maxOutputItems, maxOutputFluids);
        }

        // region CATALYSTS
        public IRecipeCatalyst getCatalyst(ItemStack input) {

            return catalystMap.get(convert(input));
        }

        public IRecipeCatalyst addCatalyst(ItemStack input, float primaryMod, float secondaryMod, float energyMod, float minChance, float useChance) {

            SimpleCatalyst catalyst = new SimpleCatalyst(primaryMod, secondaryMod, energyMod, minChance, useChance);
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
