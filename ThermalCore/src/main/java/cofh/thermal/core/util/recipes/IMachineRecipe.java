package cofh.thermal.core.util.recipes;

import cofh.thermal.core.util.IThermalInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public interface IMachineRecipe {

    List<ItemStack> getInputItems();

    List<FluidStack> getInputFluids();

    List<ItemStack> getOutputItems(IThermalInventory inventory);

    List<FluidStack> getOutputFluids(IThermalInventory inventory);

    List<Float> getOutputItemChances(IThermalInventory inventory);

    List<Integer> getInputItemCounts(IThermalInventory inventory);

    List<Integer> getInputFluidCounts(IThermalInventory inventory);

    int getEnergy(IThermalInventory inventory);

}
