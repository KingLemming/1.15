package cofh.thermal.core.plugins.jei;

import cofh.thermal.core.util.managers.machine.BrewerRecipeManager;
import cofh.thermal.core.util.managers.machine.FurnaceRecipeManager;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.common.ThermalRecipeTypes.*;

@JeiPlugin
public class ThermalJeiPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        RecipeManager recipeManager = getRecipeManager();
        if (recipeManager == null) {
            // TODO: Log an error.
            return;
        }
        registration.addRecipes(recipeManager.getRecipes(RECIPE_FURNACE).values(), ID_RECIPE_FURNACE);
        registration.addRecipes(FurnaceRecipeManager.instance().getConvertedRecipes(), ID_RECIPE_FURNACE);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_SAWMILL).values(), ID_RECIPE_SAWMILL);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_PULVERIZER).values(), ID_RECIPE_PULVERIZER);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_INSOLATOR).values(), ID_RECIPE_INSOLATOR);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_CENTRIFUGE).values(), ID_RECIPE_CENTRIFUGE);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_PRESS).values(), ID_RECIPE_PRESS);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_CRUCIBLE).values(), ID_RECIPE_CRUCIBLE);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_CHILLER).values(), ID_RECIPE_CHILLER);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_REFINERY).values(), ID_RECIPE_REFINERY);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_BREWER).values(), ID_RECIPE_BREWER);
        registration.addRecipes(BrewerRecipeManager.instance().getConvertedRecipes(), ID_RECIPE_BREWER);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_BOTTLER).values(), ID_RECIPE_BOTTLER);
    }

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(ID_THERMAL, "core");
    }

    // region HELPERS
    private RecipeManager getRecipeManager() {

        RecipeManager recipeManager = null;
        ClientWorld world = Minecraft.getInstance().world;
        if (world != null) {
            recipeManager = world.getRecipeManager();
        }
        return recipeManager;
    }
    // endregion
}
