package cofh.thermal.core.util.recipes.internal;

import cofh.thermal.core.util.IMachineInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public interface IMachineRecipe {

    List<ItemStack> getInputItems();

    List<FluidStack> getInputFluids();

    List<ItemStack> getOutputItems(IMachineInventory inventory);

    List<FluidStack> getOutputFluids(IMachineInventory inventory);

    List<Float> getOutputItemChances(IMachineInventory inventory);

    List<Integer> getInputItemCounts(IMachineInventory inventory);

    List<Integer> getInputFluidCounts(IMachineInventory inventory);

    int getEnergy(IMachineInventory inventory);

    float getExperience(IMachineInventory inventory);

}
