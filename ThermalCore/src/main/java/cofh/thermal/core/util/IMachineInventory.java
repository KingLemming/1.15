package cofh.thermal.core.util;

import cofh.thermal.core.util.recipes.internal.IRecipeCatalyst;

/**
 * Empty interface which combines base Thermal Inventory functionality along with Recipe Catalyst callbacks.
 */
public interface IMachineInventory extends IThermalInventory, IRecipeCatalyst {

}
