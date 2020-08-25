package cofh.thermal.core.fluid;

import cofh.lib.fluid.FluidCoFH;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;

import static cofh.thermal.core.ThermalCore.FLUIDS;
import static cofh.thermal.core.init.TCoreIDs.ID_FLUID_LATEX;

public class LatexFluid extends FluidCoFH {

    public static LatexFluid create() {

        return new LatexFluid(ID_FLUID_LATEX, "thermal:block/fluids/latex_still", "thermal:block/fluids/latex_flow");
    }

    protected LatexFluid(String key, String stillTexture, String flowTexture) {

        super(FLUIDS, key, FluidAttributes.builder(new ResourceLocation(stillTexture), new ResourceLocation(flowTexture)).density(950).viscosity(2500));
    }

}
