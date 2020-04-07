package cofh.thermal.expansion.util.managers.machine;

import cofh.lib.inventory.FalseIInventory;
import cofh.thermal.core.util.managers.SingleItemRecipeManager;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.expansion.init.TExpRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class SawmillRecipeManager extends SingleItemRecipeManager {

    private static final SawmillRecipeManager INSTANCE = new SawmillRecipeManager();
    protected static final int DEFAULT_ENERGY = 2000;
    protected static float logMultiplier = 1.5F;
    protected static boolean defaultLogRecipes = true;

    public static SawmillRecipeManager instance() {

        return INSTANCE;
    }

    private SawmillRecipeManager() {

        super(DEFAULT_ENERGY, 4, 0);
    }

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipes.RECIPE_SAWMILL);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addRecipe((ThermalRecipe) entry.getValue());
        }
    }
    // endregion
}
