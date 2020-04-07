package cofh.thermal.expansion;

import cofh.thermal.core.init.ThermalRecipeManager;
import cofh.thermal.expansion.gui.client.*;
import cofh.thermal.expansion.init.TExpBlocks;
import cofh.thermal.expansion.init.TExpItems;
import cofh.thermal.expansion.init.TExpRecipes;
import cofh.thermal.expansion.util.managers.machine.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_EXPANSION;
import static cofh.thermal.expansion.init.TExpReferences.*;

@Mod(ID_THERMAL_EXPANSION)
public class ThermalExpansion {

    public ThermalExpansion() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);

        TExpBlocks.register();
        TExpItems.register();
        TExpRecipes.register();

        ThermalRecipeManager.register(FurnaceRecipeManager.instance());
        ThermalRecipeManager.register(SawmillRecipeManager.instance());
        ThermalRecipeManager.register(PulverizerRecipeManager.instance());
        ThermalRecipeManager.register(InsolatorRecipeManager.instance());
        ThermalRecipeManager.register(CentrifugeRecipeManager.instance());
        ThermalRecipeManager.register(CrucibleRecipeManager.instance());
        ThermalRecipeManager.register(RefineryRecipeManager.instance());
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ScreenManager.registerFactory(MACHINE_FURNACE_CONTAINER, MachineFurnaceScreen::new);
        ScreenManager.registerFactory(MACHINE_SAWMILL_CONTAINER, MachineSawmillScreen::new);
        ScreenManager.registerFactory(MACHINE_PULVERIZER_CONTAINER, MachinePulverizerScreen::new);
        ScreenManager.registerFactory(MACHINE_INSOLATOR_CONTAINER, MachineInsolatorScreen::new);
        ScreenManager.registerFactory(MACHINE_CENTRIFUGE_CONTAINER, MachineCentrifugeScreen::new);
        ScreenManager.registerFactory(MACHINE_CRUCIBLE_CONTAINER, MachineCrucibleScreen::new);
        ScreenManager.registerFactory(MACHINE_REFINERY_CONTAINER, MachineRefineryScreen::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }
    // endregion
}
