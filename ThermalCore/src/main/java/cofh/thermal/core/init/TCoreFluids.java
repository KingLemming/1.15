package cofh.thermal.core.init;

import cofh.thermal.core.fluid.EnderFluid;
import cofh.thermal.core.fluid.GlowstoneFluid;
import cofh.thermal.core.fluid.RedstoneFluid;

public class TCoreFluids {

    private TCoreFluids() {

    }

    public static void register() {

        RedstoneFluid.create("redstone_fluid");
        GlowstoneFluid.create("glowstone_fluid");
        EnderFluid.create("ender_fluid");
    }

}
