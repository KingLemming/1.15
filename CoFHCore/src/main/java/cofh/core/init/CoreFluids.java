package cofh.core.init;

import cofh.core.fluid.ExperienceFluid;
import cofh.core.fluid.HoneyFluid;
import cofh.core.fluid.MilkFluid;
import cofh.core.fluid.PotionFluid;

public class CoreFluids {

    private CoreFluids() {

    }

    public static void register() {

        ExperienceFluid.create();
        HoneyFluid.create();
        // MilkFluid.create();
        PotionFluid.create();
    }

}
