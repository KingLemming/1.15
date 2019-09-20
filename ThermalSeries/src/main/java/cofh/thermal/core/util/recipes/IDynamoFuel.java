package cofh.thermal.core.util.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public interface IDynamoFuel {

    List<ItemStack> getInputItems();

    List<FluidStack> getInputFluids();

    int getEnergy();

}
