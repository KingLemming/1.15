package cofh.thermal.core.util.recipes.internal;

import cofh.thermal.core.util.IMachineInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface IMachineRecipe {

    List<ItemStack> getInputItems();

    List<FluidStack> getInputFluids();

    List<ItemStack> getOutputItems(IMachineInventory inventory);

    List<FluidStack> getOutputFluids(IMachineInventory inventory);

    List<Float> getOutputItemChances(IMachineInventory inventory);

    List<Integer> getInputItemCounts(IMachineInventory inventory);

    List<Integer> getInputFluidCounts(IMachineInventory inventory);

    default Pair<List<Integer>, List<Integer>> getInputItemAndFluidCounts(IMachineInventory inventory) {

        return Pair.of(getInputItemCounts(inventory), getInputFluidCounts(inventory));
    }

    int getEnergy(IMachineInventory inventory);

    float getExperience(IMachineInventory inventory);

    int getMinTicks();

}
