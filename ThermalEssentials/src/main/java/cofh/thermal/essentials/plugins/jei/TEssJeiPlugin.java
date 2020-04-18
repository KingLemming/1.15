package cofh.thermal.essentials.plugins.jei;

import cofh.thermal.core.common.ThermalReferences;
import cofh.thermal.essentials.client.gui.BasicPulverizerScreen;
import cofh.thermal.essentials.client.gui.BasicSawmillScreen;
import cofh.thermal.essentials.plugins.jei.machine.PulverizerRecipeCategory;
import cofh.thermal.essentials.plugins.jei.machine.SawmillRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.essentials.init.TEssReferences.BASIC_PULVERIZER_BLOCK;
import static cofh.thermal.essentials.init.TEssReferences.BASIC_SAWMILL_BLOCK;

@JeiPlugin
public class TEssJeiPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        registration.addRecipeCategories(new SawmillRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_BASIC_SAWMILL));
        registration.addRecipeCategories(new PulverizerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_BASIC_PULVERIZER));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        int progressY = 34;
        int progressW = 24;
        int progressH = 16;

        registration.addRecipeClickArea(BasicSawmillScreen.class, 72, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_BASIC_SAWMILL);
        registration.addRecipeClickArea(BasicPulverizerScreen.class, 72, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_BASIC_PULVERIZER);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

        registration.addRecipeCatalyst(new ItemStack(BASIC_SAWMILL_BLOCK), ThermalReferences.ID_RECIPE_SAWMILL);
        registration.addRecipeCatalyst(new ItemStack(BASIC_PULVERIZER_BLOCK), ThermalReferences.ID_RECIPE_PULVERIZER);
    }

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(ID_THERMAL, "essentials");
    }

}
