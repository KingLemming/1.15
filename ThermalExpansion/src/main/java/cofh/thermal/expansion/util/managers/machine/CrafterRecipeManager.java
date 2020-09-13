package cofh.thermal.expansion.util.managers.machine;

import cofh.thermal.core.util.managers.IManager;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.expansion.util.recipes.machine.CrafterRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;

import java.util.IdentityHashMap;

public class CrafterRecipeManager implements IManager {

    private static final CrafterRecipeManager INSTANCE = new CrafterRecipeManager();
    protected static final int DEFAULT_ENERGY = 400;

    protected IdentityHashMap<IRecipe<?>, CrafterRecipe> recipeMap = new IdentityHashMap<>();

    public static CrafterRecipeManager instance() {

        return INSTANCE;
    }

    public boolean validItem(ItemStack item, IMachineRecipe recipe) {

        return recipe instanceof CrafterRecipe && ((CrafterRecipe) recipe).validItem(item);
    }

    public CrafterRecipe getRecipe(IRecipe<?> recipe) {

        if (recipe == null || recipe.isDynamic() || recipe.getRecipeOutput().isEmpty()) {
            return null;
        }
        if (!recipeMap.containsKey(recipe)) {
            recipeMap.put(recipe, new CrafterRecipe(DEFAULT_ENERGY, recipe));
        }
        return recipeMap.get(recipe);
    }

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        recipeMap.clear();
    }
    // endregion
}
