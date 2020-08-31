package cofh.thermal.expansion;

import cofh.thermal.expansion.client.gui.dynamo.*;
import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.init.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.core.util.constants.Constants.ID_THERMAL_EXPANSION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.common.ThermalFeatures.*;
import static cofh.thermal.expansion.init.TExpIDs.*;
import static cofh.thermal.expansion.init.TExpReferences.*;

@Mod(ID_THERMAL_EXPANSION)
public class ThermalExpansion {

    public ThermalExpansion() {

        setFeatureFlags();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        TExpBlocks.register();
        TExpItems.register();

        TExpRecipeManagers.register();
        TExpRecipeSerializers.register();
        TExpRecipeTypes.register();
    }

    private void setFeatureFlags() {

        setFeature(FLAG_RESOURCE_CINNABAR, true);

        setFeature(FLAG_DYNAMO_AUGMENTS, true);
        setFeature(FLAG_MACHINE_AUGMENTS, true);
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ScreenManager.registerFactory(MACHINE_FURNACE_CONTAINER, MachineFurnaceScreen::new);
        ScreenManager.registerFactory(MACHINE_SAWMILL_CONTAINER, MachineSawmillScreen::new);
        ScreenManager.registerFactory(MACHINE_PULVERIZER_CONTAINER, MachinePulverizerScreen::new);
        ScreenManager.registerFactory(MACHINE_SMELTER_CONTAINER, MachineSmelterScreen::new);
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

        //        ScreenManager.registerFactory(DEVICE_FLUID_BUFFER_CONTAINER, DeviceFluidBufferScreen::new);
        //        ScreenManager.registerFactory(DEVICE_ITEM_BUFFER_CONTAINER, DeviceItemBufferScreen::new);

        RenderType cutout = RenderType.getCutout();

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_FURNACE), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_SAWMILL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_PULVERIZER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_SMELTER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_INSOLATOR), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_CENTRIFUGE), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_PRESS), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_CRUCIBLE), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_CHILLER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_REFINERY), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_BREWER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_BOTTLER), cutout);

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_STIRLING), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_COMPRESSION), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_MAGMATIC), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_NUMISMATIC), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_LAPIDARY), cutout);

        //        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DEVICE_FLUID_BUFFER), cutout);
        //        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DEVICE_ITEM_BUFFER), cutout);
    }
    // endregion
}
