package cofh.thermal.core.fluid;

import cofh.lib.fluid.FluidCoFH;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;

import static cofh.thermal.core.ThermalCore.FLUIDS;
import static cofh.thermal.core.init.TCoreReferences.ID_FLUID_TREE_OIL;

public class TreeOilFluid extends FluidCoFH {

    public static TreeOilFluid create() {

        return new TreeOilFluid(ID_FLUID_TREE_OIL, "thermal:block/fluids/tree_oil_still", "thermal:block/fluids/tree_oil_flow");
    }

    protected TreeOilFluid(String key, String stillTexture, String flowTexture) {

        super(FLUIDS, key, FluidAttributes.builder(new ResourceLocation(stillTexture), new ResourceLocation(flowTexture)).density(900).viscosity(1200));
    }

}
