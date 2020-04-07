package cofh.thermal.core.util.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;

/**
 * This class really just serves as a way to ride on Mojang's automated recipe syncing and datapack functionality.
 * Nothing in Thermal actually uses any of this for logic whatsoever. It's part of a shim layer, nothing more.
 */
public abstract class ThermalRecipe extends ThermalRecipeBase {

    protected final List<Ingredient> inputItems = new ArrayList<>();
    protected final List<FluidStack> inputFluids = new ArrayList<>();

    protected final List<ItemStack> outputItems = new ArrayList<>();
    protected final List<FluidStack> outputFluids = new ArrayList<>();
    protected final List<Float> outputItemChances = new ArrayList<>();

    protected int energy;
    protected float experience;

    protected ThermalRecipe(ResourceLocation id, int energy, float experience, @Nullable List<Ingredient> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> chance, @Nullable List<FluidStack> outputFluids) {

        super(id);

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

            if (chance != null) {
                this.outputItemChances.addAll(chance);
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

    protected ThermalRecipe(ResourceLocation id, int energy, float experience, Ingredient input, ItemStack output, float chance) {

        this(id, energy, experience, Collections.singletonList(input), Collections.emptyList(), Collections.singletonList(output), Collections.singletonList(chance), Collections.emptyList());
    }

    protected ThermalRecipe(ResourceLocation id, int energy, float experience, Ingredient input, @Nullable List<ItemStack> outputItems, @Nullable List<Float> chance) {

        this(id, energy, experience, Collections.singletonList(input), Collections.emptyList(), outputItems, chance, Collections.emptyList());
    }

    protected ThermalRecipe(ResourceLocation id, int energy, float experience, Ingredient input, @Nullable List<ItemStack> outputItems, @Nullable List<Float> chance, @Nullable List<FluidStack> outputFluids) {

        this(id, energy, experience, Collections.singletonList(input), Collections.emptyList(), outputItems, chance, outputFluids);
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
