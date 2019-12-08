package cofh.thermal.core.util.managers;

import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.recipes.IMachineRecipe;

import java.util.List;

public interface IRecipeManager extends IManager {

    default boolean validRecipe(IThermalInventory inventory) {

        return getRecipe(inventory) != null;
    }

    IMachineRecipe getRecipe(IThermalInventory inventory);

    List<IMachineRecipe> getRecipeList();

}
