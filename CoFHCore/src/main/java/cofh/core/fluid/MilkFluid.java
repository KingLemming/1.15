package cofh.core.fluid;

import cofh.lib.fluid.FluidCoFH;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import static cofh.core.CoFHCore.FLUIDS;
import static cofh.lib.util.references.CoreReferences.ID_FLUID_MILK;

public class MilkFluid extends FluidCoFH {

    public static MilkFluid create() {

        return new MilkFluid(ID_FLUID_MILK, "cofh_core:block/fluids/milk_still", "cofh_core:block/fluids/milk_flow");
    }

    protected MilkFluid(String key, String stillTexture, String flowTexture) {

        stillFluid = FLUIDS.register(key, () -> new ForgeFlowingFluid.Source(properties));
        flowingFluid = FLUIDS.register(flowing(key), () -> new ForgeFlowingFluid.Flowing(properties));

        properties = new ForgeFlowingFluid.Properties(stillFluid, flowingFluid, FluidAttributes.builder(new ResourceLocation(stillTexture), new ResourceLocation(flowTexture)).density(1005).viscosity(2300));
    }

}