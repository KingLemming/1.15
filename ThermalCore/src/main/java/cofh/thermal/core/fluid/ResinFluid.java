package cofh.thermal.core.fluid;

import cofh.lib.fluid.FluidCoFH;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;

import static cofh.thermal.core.ThermalCore.FLUIDS;
import static cofh.thermal.core.init.TCoreReferences.ID_FLUID_RESIN;

public class ResinFluid extends FluidCoFH {

    public static ResinFluid create() {

        return new ResinFluid(ID_FLUID_RESIN, "thermal:block/fluids/resin_still", "thermal:block/fluids/resin_flow");
    }

    protected ResinFluid(String key, String stillTexture, String flowTexture) {

        super(FLUIDS, key, FluidAttributes.builder(new ResourceLocation(stillTexture), new ResourceLocation(flowTexture)).density(900).viscosity(3000));
    }

}
