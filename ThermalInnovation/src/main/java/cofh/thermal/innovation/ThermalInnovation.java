package cofh.thermal.innovation;

import cofh.thermal.innovation.init.TInoBlocks;
import cofh.thermal.innovation.init.TInoItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_INNOVATION;
import static cofh.thermal.core.common.ThermalFeatures.*;

@Mod(ID_THERMAL_INNOVATION)
public class ThermalInnovation {

    public ThermalInnovation() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        TInoBlocks.register();
        TInoItems.register();

        setFeature(FLAG_ELEMENTAL_EXPLOSIVES, true);

        setFeature(FLAG_BASIC_COMPONENTS, true);
        setFeature(FLAG_TOOL_COMPONENTS, true);

        setFeature(FLAG_AREA_AUGMENTS, true);
        setFeature(FLAG_POTION_AUGMENTS, true);
        setFeature(FLAG_STORAGE_AUGMENTS, true);
        setFeature(FLAG_UPGRADE_AUGMENTS, true);

        setFeature(FLAG_ARMOR_DIVING, true);
        setFeature(FLAG_ARMOR_HAZMAT, true);
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }
    // endregion
}
