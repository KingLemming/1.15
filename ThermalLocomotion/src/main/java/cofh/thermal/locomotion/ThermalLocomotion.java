package cofh.thermal.locomotion;

import cofh.thermal.locomotion.init.TLocBlocks;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.constants.Constants.ID_THERMAL_LOCOMOTION;

@Mod(ID_THERMAL_LOCOMOTION)
public class ThermalLocomotion {

    public ThermalLocomotion() {

        TLocBlocks.register();
    }

}
