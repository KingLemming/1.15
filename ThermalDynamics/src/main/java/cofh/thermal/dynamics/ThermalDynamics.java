package cofh.thermal.dynamics;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_DYNAMICS;

@Mod(ID_THERMAL_DYNAMICS)
public class ThermalDynamics {

    public ThermalDynamics() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

}
