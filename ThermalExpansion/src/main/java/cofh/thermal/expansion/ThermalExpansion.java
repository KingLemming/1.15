package cofh.thermal.expansion;

import cofh.thermal.expansion.client.gui.dynamo.*;
import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.data.*;
import cofh.thermal.expansion.init.TExpBlocks;
import cofh.thermal.expansion.init.TExpContainers;
import cofh.thermal.expansion.init.TExpItems;
import cofh.thermal.expansion.init.TExpTileEntities;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_EXPANSION;
import static cofh.thermal.expansion.init.TExpReferences.*;

@Mod(ID_THERMAL_EXPANSION)
public class ThermalExpansion {

    public ThermalExpansion() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);

        TExpBlocks.register();
        TExpItems.register();

        TExpTileEntities.register();
        TExpContainers.register();
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
        ScreenManager.registerFactory(MACHINE_PRESS_CONTAINER, MachinePressScreen::new);
        ScreenManager.registerFactory(MACHINE_CRUCIBLE_CONTAINER, MachineCrucibleScreen::new);
        ScreenManager.registerFactory(MACHINE_CHILLER_CONTAINER, MachineChillerScreen::new);
        ScreenManager.registerFactory(MACHINE_REFINERY_CONTAINER, MachineRefineryScreen::new);
        ScreenManager.registerFactory(MACHINE_BREWER_CONTAINER, MachineBrewerScreen::new);
        ScreenManager.registerFactory(MACHINE_BOTTLER_CONTAINER, MachineBottlerScreen::new);

        ScreenManager.registerFactory(DYNAMO_STIRLING_CONTAINER, DynamoStirlingScreen::new);
        ScreenManager.registerFactory(DYNAMO_COMPRESSION_CONTAINER, DynamoCompressionScreen::new);
        ScreenManager.registerFactory(DYNAMO_MAGMATIC_CONTAINER, DynamoMagmaticScreen::new);
        ScreenManager.registerFactory(DYNAMO_NUMISMATIC_CONTAINER, DynamoNumismaticScreen::new);
        ScreenManager.registerFactory(DYNAMO_LAPIDARY_CONTAINER, DynamoLapidaryScreen::new);
    }
    // endregion

    // region DATA
    private void gatherData(final GatherDataEvent event) {

        if (event.includeServer()) {
            registerServerProviders(event.getGenerator());
        }
        if (event.includeClient()) {
            registerClientProviders(event.getGenerator(), event);
        }
    }

    private void registerServerProviders(DataGenerator generator) {

        generator.addProvider(new TExpTags.Block(generator));
        generator.addProvider(new TExpTags.Item(generator));
        generator.addProvider(new TExpTags.Fluid(generator));

        generator.addProvider(new TExpLootTables(generator));
        generator.addProvider(new TExpRecipes(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new TExpBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(new TExpItemModels(generator, event.getExistingFileHelper()));
    }
    // endregion
}
