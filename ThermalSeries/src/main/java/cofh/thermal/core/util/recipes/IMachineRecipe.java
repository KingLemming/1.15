package cofh.thermal.core.util.recipes;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public interface IMachineRecipe {

    List<ItemStack> getInputItems();

    List<FluidStack> getInputFluids();

    List<ItemStack> getOutputItems(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks);

    List<FluidStack> getOutputFluids(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks);

    List<Float> getOutputItemChances(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks);

    List<Integer> getInputItemCounts(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks);

    List<Integer> getInputFluidCounts(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks);

    int getEnergy(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks);

}
