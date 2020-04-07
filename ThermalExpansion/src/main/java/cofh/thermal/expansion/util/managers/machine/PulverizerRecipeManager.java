package cofh.thermal.expansion.util.managers.machine;

import cofh.lib.inventory.FalseIInventory;
import cofh.thermal.core.util.managers.SimpleItemRecipeManager;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.expansion.init.TExpRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class PulverizerRecipeManager extends SimpleItemRecipeManager.Catalyzed {

    private static final PulverizerRecipeManager INSTANCE = new PulverizerRecipeManager();
    protected static final int DEFAULT_ENERGY = 4000;

    protected static float oreMultiplier = 2.0F;
    protected static boolean defaultOreRecipes = true;
    protected static boolean defaultIngotRecipes = true;

    public static PulverizerRecipeManager instance() {

        return INSTANCE;
    }

    private PulverizerRecipeManager() {

        super(DEFAULT_ENERGY, 4, 0);
    }

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipes.RECIPE_PULVERIZER);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            convertRecipe((ThermalRecipe) entry.getValue());
        }
    }
    // endregion
}
