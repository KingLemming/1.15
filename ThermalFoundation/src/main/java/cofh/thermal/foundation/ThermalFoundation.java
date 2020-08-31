package cofh.thermal.foundation;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.core.util.constants.Constants.ID_THERMAL_FOUNDATION;

@Mod(ID_THERMAL_FOUNDATION)
public class ThermalFoundation {

    public ThermalFoundation() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

}
