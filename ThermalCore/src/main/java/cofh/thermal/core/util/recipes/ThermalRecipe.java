package cofh.thermal.core.util.recipes;

import cofh.lib.inventory.FalseIInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;

/**
 * This class really just serves as a way to ride on Mojang's automated recipe syncing and datapack functionality.
 * Nothing in Thermal actually uses any of this for logic whatsoever. It's part of a shim layer, nothing more.
 */
public abstract class ThermalRecipe implements IRecipe<FalseIInventory> {

    protected final ResourceLocation id;

    protected final List<Ingredient> inputItems = new ArrayList<>();
    protected final List<FluidStack> inputFluids = new ArrayList<>();

    protected final List<ItemStack> outputItems = new ArrayList<>();
    protected final List<FluidStack> outputFluids = new ArrayList<>();
    protected final List<Float> outputItemChances = new ArrayList<>();

    protected int energy;
    protected float experience;

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

    // region SINGLE ITEM OUTPUT
    public ThermalRecipe(ResourceLocation id, int energy, float experience, Ingredient input, ItemStack output, float chance) {

        this.id = id;
        this.energy = energy;
        this.experience = Math.max(0.0F, experience);
        this.inputItems.add(input);
        this.outputItems.add(output);
        this.outputItemChances.add(chance);
    }

    public ThermalRecipe(ResourceLocation id, int energy, Ingredient input, ItemStack output, float chance) {

        this(id, energy, 0.0F, input, output, chance);
    }
    // endregion

    // region MULTIPLE ITEM OUTPUT
    public ThermalRecipe(ResourceLocation id, int energy, float experience, Ingredient input, List<ItemStack> output, @Nullable List<Float> chance) {

        this.id = id;
        this.energy = energy;
        this.experience = Math.max(0.0F, experience);
        this.inputItems.add(input);
        this.outputItems.addAll(output);

        if (chance != null) {
            this.outputItemChances.addAll(chance);
        }
        if (this.outputItemChances.size() < this.outputItems.size()) {
            for (int i = this.outputItemChances.size(); i < this.outputItems.size(); ++i) {
                this.outputItemChances.add(BASE_CHANCE_LOCKED);
            }
        }
    }

    public ThermalRecipe(ResourceLocation id, int energy, Ingredient input, List<ItemStack> output, @Nullable List<Float> chance) {

        this(id, energy, 0.0F, input, output, chance);
    }
    // endregion

    // region SINGLE FLUID OUTPUT
    public ThermalRecipe(ResourceLocation id, int energy, float experience, Ingredient input, FluidStack output) {

        this.id = id;
        this.energy = energy;
        this.experience = Math.max(0.0F, experience);
        this.inputItems.add(input);
        this.outputFluids.add(output);
    }

    public ThermalRecipe(ResourceLocation id, int energy, Ingredient input, FluidStack output) {

        this(id, energy, 0.0F, input, output);
    }
    // endregion

    // region ITEM + FLUID OUTPUT
    public ThermalRecipe(ResourceLocation id, int energy, float experience, Ingredient input, List<ItemStack> output, @Nullable List<Float> chance, @Nullable List<FluidStack> fluids) {

        this.id = id;
        this.energy = energy;
        this.experience = Math.max(0.0F, experience);
        this.inputItems.add(input);
        this.outputItems.addAll(output);

        if (chance != null) {
            this.outputItemChances.addAll(chance);
        }
        if (this.outputItemChances.size() < this.outputItems.size()) {
            for (int i = this.outputItemChances.size(); i < this.outputItems.size(); ++i) {
                this.outputItemChances.add(BASE_CHANCE_LOCKED);
            }
        }
        if (fluids != null) {
            this.outputFluids.addAll(fluids);
        }
    }

    public ThermalRecipe(ResourceLocation id, int energy, Ingredient input, List<ItemStack> output, @Nullable List<Float> chance, @Nullable List<FluidStack> fluids) {

        this(id, energy, 0.0F, input, output, chance, fluids);
    }
    // endregion

    // region IRecipe
    @Override
    public boolean matches(FalseIInventory inv, World worldIn) {

        return true;
    }

    @Override
    public ItemStack getCraftingResult(FalseIInventory inv) {

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {

        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {

        return ItemStack.EMPTY;
    }

    @Override
    public boolean isDynamic() {

        return true;
    }

    @Override
    public ResourceLocation getId() {

        return id;
    }

    @Override
    public abstract IRecipeSerializer<?> getSerializer();

    @Override
    public abstract IRecipeType<?> getType();
    // endregion
}
