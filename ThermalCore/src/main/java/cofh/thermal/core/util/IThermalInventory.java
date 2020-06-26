package cofh.thermal.core.util;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;
import cofh.thermal.core.util.recipes.internal.IRecipeCatalyst;

import java.util.List;

public interface IThermalInventory extends IRecipeCatalyst {

    List<? extends IItemStackAccess> inputSlots();

    List<? extends IFluidStackAccess> inputTanks();

    // Note the IRecipeCatalyst Extension! This means that inventories can present any of the catalyst properties and they should be respected by recipes/fuels.
}
