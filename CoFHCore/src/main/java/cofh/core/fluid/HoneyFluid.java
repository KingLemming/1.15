package cofh.core.fluid;

import cofh.lib.fluid.FluidCoFH;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import static cofh.core.CoFHCore.FLUIDS;
import static cofh.lib.util.references.CoreReferences.ID_FLUID_HONEY;

public class HoneyFluid extends FluidCoFH {

    public static HoneyFluid create() {

        return new HoneyFluid(ID_FLUID_HONEY, "cofh_core:block/fluids/honey_still", "cofh_core:block/fluids/honey_flow");
    }

    protected HoneyFluid(String key, String stillTexture, String flowTexture) {

        stillFluid = FLUIDS.register(key, () -> new ForgeFlowingFluid.Source(properties));
        flowingFluid = FLUIDS.register(flowing(key), () -> new ForgeFlowingFluid.Flowing(properties));

        properties = new ForgeFlowingFluid.Properties(stillFluid, flowingFluid, FluidAttributes.builder(new ResourceLocation(stillTexture), new ResourceLocation(flowTexture)).density(1500).viscosity(10000000));
    }

}