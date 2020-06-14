package cofh.thermal.expansion.util.managers.machine;

import cofh.lib.inventory.FalseIInventory;
import cofh.thermal.core.util.managers.SingleItemRecipeManager;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.expansion.util.recipes.TExpRecipeTypes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class CrucibleRecipeManager extends SingleItemRecipeManager {

    private static final CrucibleRecipeManager INSTANCE = new CrucibleRecipeManager();
    protected static final int DEFAULT_ENERGY = 40000;

    public static CrucibleRecipeManager instance() {

        return INSTANCE;
    }

    private CrucibleRecipeManager() {

        super(DEFAULT_ENERGY, 0, 1);
    }

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipeTypes.RECIPE_CRUCIBLE);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addRecipe((ThermalRecipe) entry.getValue());
        }
    }
    // endregion
}
