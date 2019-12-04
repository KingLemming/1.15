package cofh.thermal.cultivation;

import cofh.thermal.cultivation.init.TCulBlocks;
import cofh.thermal.cultivation.init.TCulItems;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.constants.Constants.ID_THERMAL_CULTIVATION;

@Mod(ID_THERMAL_CULTIVATION)
public class ThermalCultivation {

    public ThermalCultivation() {

        TCulBlocks.register();
        TCulItems.register();
    }

}
