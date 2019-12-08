package cofh.thermal.core.util;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;

import java.util.List;

public interface IThermalInventory {

    List<? extends IItemStackAccess> inputSlots();

    List<? extends IFluidStackAccess> inputTanks();

}
