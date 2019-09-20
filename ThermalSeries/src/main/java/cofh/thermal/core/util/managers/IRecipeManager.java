package cofh.thermal.core.util.managers;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;
import cofh.thermal.core.util.recipes.IMachineRecipe;

import java.util.List;

public interface IRecipeManager extends IManager {

    default boolean validRecipe(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        return getRecipe(inputSlots, inputTanks) != null;
    }

    IMachineRecipe getRecipe(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks);

    List<IMachineRecipe> getRecipeList();

}
