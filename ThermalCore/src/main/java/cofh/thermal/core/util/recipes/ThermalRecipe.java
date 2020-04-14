package cofh.thermal.core.util.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;

public abstract class ThermalRecipe extends ThermalRecipeBase {

    protected final List<Ingredient> inputItems = new ArrayList<>();
    protected final List<FluidStack> inputFluids = new ArrayList<>();

    protected final List<ItemStack> outputItems = new ArrayList<>();
    protected final List<FluidStack> outputFluids = new ArrayList<>();
    protected final List<Float> outputItemChances = new ArrayList<>();

    protected int energy;
    protected float experience;

    protected ThermalRecipe(ResourceLocation recipeId, int energy, float experience, List<Ingredient> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> outputItemChances, List<FluidStack> outputFluids) {

        super(recipeId);

        this.energy = energy;
        this.experience = Math.max(0.0F, experience);

        if (inputItems != null) {
            this.inputItems.addAll(inputItems);
        }
        if (inputFluids != null) {
            this.inputFluids.addAll(inputFluids);
        }
        if (outputItems != null) {
            this.outputItems.addAll(outputItems);

            if (outputItemChances != null) {
                this.outputItemChances.addAll(outputItemChances);
            }
            if (this.outputItemChances.size() < this.outputItems.size()) {
                for (int i = this.outputItemChances.size(); i < this.outputItems.size(); ++i) {
                    this.outputItemChances.add(BASE_CHANCE_LOCKED);
                }
            }
        }
        if (outputFluids != null) {
            this.outputFluids.addAll(outputFluids);
        }
    }

    // region GETTERS
    public List<Ingredient> getInputItems() {

        return inputItems;
    }

    public List<FluidStack> getInputFluids() {

        return inputFluids;
    }

    public List<ItemStack> getOutputItems() {

        return outputItems;
    }

    public List<FluidStack> getOutputFluids() {

        return outputFluids;
    }

    public List<Float> getOutputItemChances() {

        return outputItemChances;
    }

    public int getEnergy() {

        return energy;
    }

    public float getExperience() {

        return experience;
    }
    // endregion
}
