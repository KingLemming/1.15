package cofh.thermal.core.util.managers;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;
import cofh.thermal.core.util.recipes.IDynamoFuel;

import java.util.List;

public interface IFuelManager extends IManager {

    boolean validFuel(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks);

    IDynamoFuel getFuel(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks);

    List<IDynamoFuel> getFuelList();

}
