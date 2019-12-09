package cofh.thermal.foundation;

import cofh.thermal.foundation.init.TFndBlocks;
import cofh.thermal.foundation.init.TFndItems;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.constants.Constants.ID_THERMAL_FOUNDATION;

@Mod(ID_THERMAL_FOUNDATION)
public class ThermalFoundation {

    public ThermalFoundation() {

        TFndBlocks.register();
        TFndItems.register();
    }

}
