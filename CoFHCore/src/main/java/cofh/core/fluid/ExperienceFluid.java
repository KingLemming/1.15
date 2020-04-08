package cofh.core.fluid;

import cofh.lib.fluid.FluidCoFH;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import static cofh.core.CoFHCore.FLUIDS;
import static cofh.lib.util.references.CoreReferences.ID_FLUID_EXPERIENCE;

public class ExperienceFluid extends FluidCoFH {

    public static ExperienceFluid create() {

        return new ExperienceFluid(ID_FLUID_EXPERIENCE, "cofh_core:block/fluids/experience_still", "cofh_core:block/fluids/experience_flow");
    }

    protected ExperienceFluid(String key, String stillTexture, String flowTexture) {

        stillFluid = FLUIDS.register(key, () -> new ForgeFlowingFluid.Source(properties));
        flowingFluid = FLUIDS.register(flowing(key), () -> new ForgeFlowingFluid.Flowing(properties));

        properties = new ForgeFlowingFluid.Properties(stillFluid, flowingFluid, FluidAttributes.builder(new ResourceLocation(stillTexture), new ResourceLocation(flowTexture)).luminosity(10).density(250).viscosity(500).rarity(Rarity.UNCOMMON));
    }

}
