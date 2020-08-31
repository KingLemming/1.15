package cofh.thermal.core.util;

import cofh.core.fluid.IFluidStackAccess;
import cofh.core.inventory.IItemStackAccess;

import java.util.List;

/**
 * Simple interface to allow access to input slots and tanks when handed the containing object. Used in Recipes/Fuels.
 */
public interface IThermalInventory {

    List<? extends IItemStackAccess> inputSlots();

    List<? extends IFluidStackAccess> inputTanks();

}
