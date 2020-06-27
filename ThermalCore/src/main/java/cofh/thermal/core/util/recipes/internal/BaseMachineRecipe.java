package cofh.thermal.core.util.recipes.internal;

import cofh.thermal.core.util.IThermalInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;
import static cofh.lib.util.helpers.FluidHelper.fluidsEqual;
import static cofh.lib.util.helpers.ItemHelper.itemsEqual;

public class BaseMachineRecipe implements IMachineRecipe {

    protected final List<ItemStack> inputItems = new ArrayList<>();
    protected final List<FluidStack> inputFluids = new ArrayList<>();

    protected final List<ItemStack> outputItems = new ArrayList<>();
    protected final List<FluidStack> outputFluids = new ArrayList<>();
    protected final List<Float> outputItemChances = new ArrayList<>();

    protected final int energy;
    protected final float experience;

    public BaseMachineRecipe(int energy, float experience) {

        this.energy = energy;
        this.experience = Math.max(0.0F, experience);
    }

    public BaseMachineRecipe(int energy, float experience, @Nullable List<ItemStack> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> chance, @Nullable List<FluidStack> outputFluids) {

        this(energy, experience);

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
        trim();
    }

    protected void trim() {

        ((ArrayList<ItemStack>) this.inputItems).trimToSize();
        ((ArrayList<FluidStack>) this.inputFluids).trimToSize();

        ((ArrayList<ItemStack>) this.outputItems).trimToSize();
        ((ArrayList<FluidStack>) this.outputFluids).trimToSize();
        ((ArrayList<Float>) this.outputItemChances).trimToSize();
    }

    // region IMachineRecipe
    @Override
    public List<ItemStack> getInputItems() {

        return inputItems;
    }

    @Override
    public List<FluidStack> getInputFluids() {

        return inputFluids;
    }

    @Override
    public List<ItemStack> getOutputItems(IThermalInventory inventory) {

        return outputItems;
    }

    @Override
    public List<FluidStack> getOutputFluids(IThermalInventory inventory) {

        return outputFluids;
    }

    /**
     * Okay so there's a bit of trickery happening here - internally "unmodifiable" chance is stored as a negative. Saves some memory and is kinda clever.
     * This shouldn't ever cause problems because you're relying on this method call and not hacking around in the recipe, right? ;)
     */
    @Override
    public List<Float> getOutputItemChances(IThermalInventory inventory) {

        ArrayList<Float> modifiedChances = new ArrayList<>(outputItemChances);
        for (int i = 0; i < modifiedChances.size(); ++i) {
            if (modifiedChances.get(i) < 0.0F) {
                modifiedChances.set(i, Math.abs(modifiedChances.get(i)));
            } else {
                modifiedChances.set(i, Math.max(modifiedChances.get(i) * (i == 0 ? inventory.getPrimaryMod() : inventory.getSecondaryMod()), inventory.getMinOutputChance()));
            }
        }
        return modifiedChances;
    }

    @Override
    public List<Integer> getInputItemCounts(IThermalInventory inventory) {

        if (inputItems.isEmpty()) {
            return Collections.emptyList();
        }
        int[] ret = new int[inventory.inputSlots().size()];
        for (ItemStack input : inputItems) {
            for (int j = 0; j < ret.length; ++j) {
                if (itemsEqual(input, inventory.inputSlots().get(j).getItemStack())) {
                    ret[j] = input.getCount();
                    break;
                }
            }
        }
        return IntStream.of(ret).boxed().collect(Collectors.toList());

        // Code below is SIMPLE implementation, for reference. Used for TRIVIAL cases where ingredient order is guaranteed.
        //        if (inputItems.isEmpty()) {
        //            return Collections.emptyList();
        //        }
        //        ArrayList<Integer> ret = new ArrayList<>(inputItems.size());
        //        for (ItemStack input : inputItems) {
        //            ret.add(input.getCount());
        //        }
        //        return ret;
    }

    @Override
    public List<Integer> getInputFluidCounts(IThermalInventory inventory) {

        if (inputFluids.isEmpty()) {
            return Collections.emptyList();
        }
        int[] ret = new int[inventory.inputTanks().size()];
        for (FluidStack input : inputFluids) {
            for (int j = 0; j < ret.length; ++j) {
                if (fluidsEqual(input, inventory.inputTanks().get(j).getFluidStack())) {
                    ret[j] = input.getAmount();
                    break;
                }
            }
        }
        return IntStream.of(ret).boxed().collect(Collectors.toList());

        // Code below is SIMPLE implementation, for reference. Used for TRIVIAL cases where ingredient order is guaranteed.
        //        if (inputFluids.isEmpty()) {
        //            return Collections.emptyList();
        //        }
        //        ArrayList<Integer> ret = new ArrayList<>(inputFluids.size());
        //        for (FluidStack input : inputFluids) {
        //            ret.add(input.getAmount());
        //        }
        //        return ret;
    }

    @Override
    public int getEnergy(IThermalInventory inventory) {

        return Math.abs(Math.round(energy * inventory.getEnergyMod()));
    }

    @Override
    public float getExperience(IThermalInventory inventory) {

        return experience * inventory.getExperienceMod();
    }
    // endregion
}
