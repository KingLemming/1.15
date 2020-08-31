package cofh.thermal.innovation;

import cofh.thermal.innovation.init.TInoBlocks;
import cofh.thermal.innovation.init.TInoItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.core.util.constants.Constants.ID_THERMAL_INNOVATION;
import static cofh.thermal.core.common.ThermalFeatures.*;

@Mod(ID_THERMAL_INNOVATION)
public class ThermalInnovation {

    public ThermalInnovation() {

        setFeatureFlags();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        TInoBlocks.register();
        TInoItems.register();
    }

    private void setFeatureFlags() {

        setFeature(FLAG_DIVING_ARMOR, true);
        setFeature(FLAG_HAZMAT_ARMOR, true);

        setFeature(FLAG_POTION_AUGMENTS, true);

        setFeature(FLAG_TOOL_COMPONENTS, true);

        setFeature(FLAG_ELEMENTAL_EXPLOSIVES, true);
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }
    // endregion
}
