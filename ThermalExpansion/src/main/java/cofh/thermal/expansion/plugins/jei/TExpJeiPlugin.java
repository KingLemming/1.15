package cofh.thermal.expansion.plugins.jei;

import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.plugins.jei.machine.*;
import cofh.thermal.expansion.util.managers.machine.BrewerRecipeManager;
import cofh.thermal.expansion.util.managers.machine.FurnaceRecipeManager;
import cofh.thermal.expansion.util.recipes.TExpRecipeTypes;
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

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.expansion.init.TExpReferences.*;
import static cofh.thermal.expansion.util.recipes.TExpRecipeTypes.*;

@JeiPlugin
public class TExpJeiPlugin implements IModPlugin {

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
    public void registerCategories(IRecipeCategoryRegistration registration) {

        registration.addRecipeCategories(new FurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_FURNACE));
        registration.addRecipeCategories(new SawmillRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_SAWMILL));
        registration.addRecipeCategories(new PulverizerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_PULVERIZER));
        registration.addRecipeCategories(new InsolatorRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_INSOLATOR));
        registration.addRecipeCategories(new CentrifugeRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_CENTRIFUGE));
        registration.addRecipeCategories(new PressRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_PRESS));
        registration.addRecipeCategories(new CrucibleRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_CRUCIBLE));
        registration.addRecipeCategories(new ChillerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_CHILLER));
        registration.addRecipeCategories(new RefineryRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_REFINERY));
        registration.addRecipeCategories(new BrewerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_BREWER));
        registration.addRecipeCategories(new BottlerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), TExpRecipeTypes.ID_RECIPE_BOTTLER));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        int progressY = 34;
        int progressW = 24;
        int progressH = 16;

        registration.addRecipeClickArea(MachineFurnaceScreen.class, 79, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_FURNACE);
        registration.addRecipeClickArea(MachineSawmillScreen.class, 72, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_SAWMILL);
        registration.addRecipeClickArea(MachinePulverizerScreen.class, 72, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_PULVERIZER);
        registration.addRecipeClickArea(MachineInsolatorScreen.class, 85, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_INSOLATOR);
        registration.addRecipeClickArea(MachineCentrifugeScreen.class, 72, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_CENTRIFUGE);
        registration.addRecipeClickArea(MachinePressScreen.class, 79, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_PRESS);
        registration.addRecipeClickArea(MachineCrucibleScreen.class, 84, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_CRUCIBLE);
        registration.addRecipeClickArea(MachineChillerScreen.class, 88, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_CHILLER);
        registration.addRecipeClickArea(MachineRefineryScreen.class, 65, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_REFINERY);
        registration.addRecipeClickArea(MachineBrewerScreen.class, 88, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_BREWER);
        registration.addRecipeClickArea(MachineBottlerScreen.class, 88, progressY, progressW, progressH, TExpRecipeTypes.ID_RECIPE_BOTTLER);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

        registration.addRecipeCatalyst(new ItemStack(MACHINE_FURNACE_BLOCK), TExpRecipeTypes.ID_RECIPE_FURNACE);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_SAWMILL_BLOCK), TExpRecipeTypes.ID_RECIPE_SAWMILL);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_PULVERIZER_BLOCK), TExpRecipeTypes.ID_RECIPE_PULVERIZER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_INSOLATOR_BLOCK), TExpRecipeTypes.ID_RECIPE_INSOLATOR);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_CENTRIFUGE_BLOCK), TExpRecipeTypes.ID_RECIPE_CENTRIFUGE);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_PRESS_BLOCK), TExpRecipeTypes.ID_RECIPE_PRESS);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_CRUCIBLE_BLOCK), TExpRecipeTypes.ID_RECIPE_CRUCIBLE);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_CHILLER_BLOCK), TExpRecipeTypes.ID_RECIPE_CHILLER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_REFINERY_BLOCK), TExpRecipeTypes.ID_RECIPE_REFINERY);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_BREWER_BLOCK), TExpRecipeTypes.ID_RECIPE_BREWER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_BOTTLER_BLOCK), TExpRecipeTypes.ID_RECIPE_BOTTLER);
    }

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(ID_THERMAL, "expansion");
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
