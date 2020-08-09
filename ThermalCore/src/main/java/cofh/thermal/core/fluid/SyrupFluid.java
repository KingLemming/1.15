package cofh.thermal.core.fluid;

import cofh.lib.fluid.FluidCoFH;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;

import static cofh.thermal.core.ThermalCore.FLUIDS;
import static cofh.thermal.core.init.TCoreReferences.ID_FLUID_SYRUP;

public class SyrupFluid extends FluidCoFH {

    public static SyrupFluid create() {

        return new SyrupFluid(ID_FLUID_SYRUP, "thermal:block/fluids/syrup_still", "thermal:block/fluids/syrup_flow");
    }

    protected SyrupFluid(String key, String stillTexture, String flowTexture) {

        super(FLUIDS, key, FluidAttributes.builder(new ResourceLocation(stillTexture), new ResourceLocation(flowTexture)).density(1400).viscosity(2500));
    }

}
