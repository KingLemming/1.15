package cofh.thermal.core.util.recipes;

import cofh.thermal.core.util.IThermalInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractRecipe implements IMachineRecipe {

    protected final List<ItemStack> inputItems = new ArrayList<>();
    protected final List<FluidStack> inputFluids = new ArrayList<>();
    protected final List<ItemStack> outputItems = new ArrayList<>();
    protected final List<FluidStack> outputFluids = new ArrayList<>();
    protected final List<Float> outputItemChances = new ArrayList<>();
    protected final int energy;

    public AbstractRecipe(int energy) {

        this.energy = energy;
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
            }
        }
        return modifiedChances;
    }

    @Override
    public List<Integer> getInputItemCounts(IThermalInventory inventory) {

        if (inputItems.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Integer> ret = new ArrayList<>();
        for (ItemStack input : inputItems) {
            ret.add(input.getCount());
        }
        return ret;
    }

    @Override
    public List<Integer> getInputFluidCounts(IThermalInventory inventory) {

        if (inputFluids.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Integer> ret = new ArrayList<>();
        for (FluidStack input : inputFluids) {
            ret.add(input.getAmount());
        }
        return ret;
    }

    @Override
    public int getEnergy(IThermalInventory inventory) {

        return energy;
    }
    // endregion

    // region IRecipe

    // endregion
}
