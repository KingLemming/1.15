package cofh.core.fluid;

import cofh.lib.fluid.FluidCoFH;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import static cofh.core.CoFHCore.FLUIDS;
import static cofh.lib.util.references.CoreReferences.ID_FLUID_STEAM;

public class SteamFluid extends FluidCoFH {

    public static SteamFluid create() {

        return new SteamFluid(ID_FLUID_STEAM, "cofh_core:block/fluids/steam_still", "cofh_core:block/fluids/steam_flow");
    }

    protected SteamFluid(String key, String stillTexture, String flowTexture) {

        stillFluid = FLUIDS.register(key, () -> new ForgeFlowingFluid.Source(properties));
        flowingFluid = FLUIDS.register(flowing(key), () -> new ForgeFlowingFluid.Flowing(properties));

        properties = new ForgeFlowingFluid.Properties(stillFluid, flowingFluid, FluidAttributes.builder(new ResourceLocation(stillTexture), new ResourceLocation(flowTexture)).density(-60).temperature(750).viscosity(200).gaseous());
    }

}