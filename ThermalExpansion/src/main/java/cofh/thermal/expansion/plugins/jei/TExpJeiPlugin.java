package cofh.thermal.expansion.plugins.jei;

import cofh.thermal.core.common.ThermalReferences;
import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.plugins.jei.machine.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.expansion.init.TExpReferences.*;

@JeiPlugin
public class TExpJeiPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        registration.addRecipeCategories(new FurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_FURNACE));
        registration.addRecipeCategories(new SawmillRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_SAWMILL));
        registration.addRecipeCategories(new PulverizerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_PULVERIZER));
        registration.addRecipeCategories(new InsolatorRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_INSOLATOR));
        registration.addRecipeCategories(new CentrifugeRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_CENTRIFUGE));
        registration.addRecipeCategories(new PressRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_PRESS));
        registration.addRecipeCategories(new CrucibleRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_CRUCIBLE));
        registration.addRecipeCategories(new ChillerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_CHILLER));
        registration.addRecipeCategories(new RefineryRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_REFINERY));
        registration.addRecipeCategories(new BrewerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_BREWER));
        registration.addRecipeCategories(new BottlerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), ThermalReferences.ID_RECIPE_BOTTLER));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        int progressY = 34;
        int progressW = 24;
        int progressH = 16;

        registration.addRecipeClickArea(MachineFurnaceScreen.class, 79, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_FURNACE);
        registration.addRecipeClickArea(MachineSawmillScreen.class, 72, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_SAWMILL);
        registration.addRecipeClickArea(MachinePulverizerScreen.class, 72, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_PULVERIZER);
        registration.addRecipeClickArea(MachineInsolatorScreen.class, 85, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_INSOLATOR);
        registration.addRecipeClickArea(MachineCentrifugeScreen.class, 72, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_CENTRIFUGE);
        registration.addRecipeClickArea(MachinePressScreen.class, 79, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_PRESS);
        registration.addRecipeClickArea(MachineCrucibleScreen.class, 84, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_CRUCIBLE);
        registration.addRecipeClickArea(MachineChillerScreen.class, 88, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_CHILLER);
        registration.addRecipeClickArea(MachineRefineryScreen.class, 65, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_REFINERY);
        registration.addRecipeClickArea(MachineBrewerScreen.class, 88, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_BREWER);
        registration.addRecipeClickArea(MachineBottlerScreen.class, 88, progressY, progressW, progressH, ThermalReferences.ID_RECIPE_BOTTLER);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

        registration.addRecipeCatalyst(new ItemStack(MACHINE_FURNACE_BLOCK), ThermalReferences.ID_RECIPE_FURNACE);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_SAWMILL_BLOCK), ThermalReferences.ID_RECIPE_SAWMILL);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_PULVERIZER_BLOCK), ThermalReferences.ID_RECIPE_PULVERIZER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_INSOLATOR_BLOCK), ThermalReferences.ID_RECIPE_INSOLATOR);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_CENTRIFUGE_BLOCK), ThermalReferences.ID_RECIPE_CENTRIFUGE);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_PRESS_BLOCK), ThermalReferences.ID_RECIPE_PRESS);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_CRUCIBLE_BLOCK), ThermalReferences.ID_RECIPE_CRUCIBLE);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_CHILLER_BLOCK), ThermalReferences.ID_RECIPE_CHILLER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_REFINERY_BLOCK), ThermalReferences.ID_RECIPE_REFINERY);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_BREWER_BLOCK), ThermalReferences.ID_RECIPE_BREWER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_BOTTLER_BLOCK), ThermalReferences.ID_RECIPE_BOTTLER);
    }

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(ID_THERMAL, "expansion");
    }

}
