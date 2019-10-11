package cofh.thermal.core.util;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;

import java.util.List;

public interface IStuffContainer {

    List<? extends IItemStackAccess> getInputSlots();

    List<? extends IFluidStackAccess> inputTanks();

}
