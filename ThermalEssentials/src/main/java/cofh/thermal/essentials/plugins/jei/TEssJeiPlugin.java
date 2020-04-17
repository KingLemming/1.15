package cofh.thermal.essentials.plugins.jei;

import cofh.thermal.core.init.ThermalReferences;
import cofh.thermal.essentials.client.gui.BasicPulverizerScreen;
import cofh.thermal.essentials.client.gui.BasicSawmillScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.constants.Constants.ID_THERMAL_ESSENTIALS;
import static cofh.thermal.core.init.ThermalRecipeTypes.RECIPE_PULVERIZER;
import static cofh.thermal.core.init.ThermalRecipeTypes.RECIPE_SAWMILL;
import static cofh.thermal.essentials.init.TEssReferences.BASIC_PULVERIZER_BLOCK;
import static cofh.thermal.essentials.init.TEssReferences.BASIC_SAWMILL_BLOCK;

@JeiPlugin
public class TEssJeiPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        //        registration.addRecipeCategories(new SawmillRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_SAWMILL));
        //        registration.addRecipeCategories(new PulverizerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_PULVERIZER));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        int progressY = 34;
        int progressW = 24;
        int progressH = 16;

        registration.addRecipeClickArea(BasicSawmillScreen.class, 72, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_SAWMILL);
        registration.addRecipeClickArea(BasicPulverizerScreen.class, 72, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_PULVERIZER);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        RecipeManager recipeManager = getRecipeManager();
        if (recipeManager == null) {
            // TODO: Log an error.
            return;
        }
        registration.addRecipes(recipeManager.getRecipes(RECIPE_SAWMILL).values(), ThermalReferences.ID_RECIPE_SAWMILL);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_PULVERIZER).values(), ThermalReferences.ID_RECIPE_PULVERIZER);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

        registration.addRecipeCatalyst(new ItemStack(BASIC_SAWMILL_BLOCK), ThermalReferences.ID_RECIPE_SAWMILL);
        registration.addRecipeCatalyst(new ItemStack(BASIC_PULVERIZER_BLOCK), ThermalReferences.ID_RECIPE_PULVERIZER);
    }

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(ID_THERMAL_ESSENTIALS, "machines");
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
