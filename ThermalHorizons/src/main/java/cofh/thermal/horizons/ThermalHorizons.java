package cofh.thermal.horizons;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.core.util.constants.Constants.ID_THERMAL_HORIZONS;

@Mod(ID_THERMAL_HORIZONS)
public class ThermalHorizons {

    public ThermalHorizons() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

}
