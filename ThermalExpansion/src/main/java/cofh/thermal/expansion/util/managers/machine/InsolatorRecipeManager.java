package cofh.thermal.expansion.util.managers.machine;

import cofh.core.inventory.FalseIInventory;
import cofh.thermal.core.util.IMachineInventory;
import cofh.thermal.core.util.managers.SingleItemRecipeManager;
import cofh.thermal.core.util.recipes.ThermalCatalyst;
import cofh.thermal.core.util.recipes.internal.CatalyzedMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IRecipeCatalyst;
import cofh.thermal.expansion.init.TExpRecipeTypes;
import cofh.thermal.expansion.util.recipes.machine.InsolatorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static cofh.core.util.constants.Constants.BUCKET_VOLUME;

public class InsolatorRecipeManager extends SingleItemRecipeManager.Catalyzed {

    private static final InsolatorRecipeManager INSTANCE = new InsolatorRecipeManager();
    protected static final int DEFAULT_ENERGY = 20000;

    protected int defaultWater = BUCKET_VOLUME / 2;

    public static InsolatorRecipeManager instance() {

        return INSTANCE;
    }

    private InsolatorRecipeManager() {

        super(DEFAULT_ENERGY, 4, 0);
    }

    public int getDefaultWater() {

        return defaultWater;
    }

    // region RECIPES
    @Override
    protected IMachineRecipe addRecipe(int energy, float experience, int minTicks, List<ItemStack> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> chance, List<FluidStack> outputFluids) {

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
        energy = (int) (energy * getDefaultScale());

        InternalInsolatorRecipe recipe = new InternalInsolatorRecipe(energy, experience, minTicks, inputItems, inputFluids, outputItems, chance, outputFluids);
        recipeMap.put(convert(input), recipe);
        return recipe;
    }
    // endregion

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipeTypes.RECIPE_INSOLATOR);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addRecipe((InsolatorRecipe) entry.getValue());
        }
        Map<ResourceLocation, IRecipe<FalseIInventory>> catalysts = recipeManager.getRecipes(TExpRecipeTypes.CATALYST_INSOLATOR);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : catalysts.entrySet()) {
            addCatalyst((ThermalCatalyst) entry.getValue());
        }
    }
    // endregion

    // region CATALYZED RECIPE
    protected static class InternalInsolatorRecipe extends CatalyzedMachineRecipe {

        public InternalInsolatorRecipe(int energy, float experience, int minTicks, @Nullable List<ItemStack> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> chance, @Nullable List<FluidStack> outputFluids) {

            super(energy, experience, minTicks, inputItems, inputFluids, outputItems, chance, outputFluids);
        }

        @Override
        public List<Integer> getInputFluidCounts(IMachineInventory inventory) {

            // Ingredient order is guaranteed.
            if (inputFluids.isEmpty()) {
                return Collections.emptyList();
            }
            ArrayList<Integer> ret = new ArrayList<>(inputFluids.size());
            for (FluidStack input : inputFluids) {
                ret.add(input.getAmount());
            }
            return ret;
        }

        @Override
        public IRecipeCatalyst getCatalyst(ItemStack input) {

            return instance().getCatalyst(input);
        }

    }
    // endregion
}
