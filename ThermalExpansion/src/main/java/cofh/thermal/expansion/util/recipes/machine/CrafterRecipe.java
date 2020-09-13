package cofh.thermal.expansion.util.recipes.machine;

import cofh.thermal.core.util.recipes.internal.BaseMachineRecipe;
import net.minecraft.item.crafting.IRecipe;

import static cofh.core.util.constants.Constants.BASE_CHANCE_LOCKED;

public class CrafterRecipe extends BaseMachineRecipe {

    public CrafterRecipe(int energy, IRecipe<?> recipe) {

        super(energy, 0, 0);

        recipe.getIngredients();

        outputItems.add(recipe.getRecipeOutput());
        outputItemChances.add(BASE_CHANCE_LOCKED);
    }

}
