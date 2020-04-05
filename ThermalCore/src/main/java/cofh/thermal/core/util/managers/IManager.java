package cofh.thermal.core.util.managers;

import net.minecraft.item.crafting.RecipeManager;

public interface IManager {

    void config();

    void refresh(RecipeManager recipeManager);

}
