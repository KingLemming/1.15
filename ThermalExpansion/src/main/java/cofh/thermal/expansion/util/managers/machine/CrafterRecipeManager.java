package cofh.thermal.expansion.util.managers.machine;

import cofh.thermal.expansion.util.recipes.machine.CrafterRecipe;
import net.minecraft.item.crafting.IRecipe;

import java.util.IdentityHashMap;

public class CrafterRecipeManager {

    private static final CrafterRecipeManager INSTANCE = new CrafterRecipeManager();
    protected static final int DEFAULT_ENERGY = 400;

    protected IdentityHashMap<IRecipe<?>, CrafterRecipe> recipeMap = new IdentityHashMap<>();

    public static CrafterRecipeManager instance() {

        return INSTANCE;
    }

    public CrafterRecipe getRecipe(IRecipe<?> recipe) {

        if (recipe == null || recipe.isDynamic()) {
            return null;
        }
        if (!recipeMap.containsKey(recipe)) {
            recipeMap.put(recipe, new CrafterRecipe(DEFAULT_ENERGY, recipe));
        }
        return recipeMap.get(recipe);
    }

}
