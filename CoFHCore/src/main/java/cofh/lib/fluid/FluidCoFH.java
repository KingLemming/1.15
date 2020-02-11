package cofh.lib.fluid;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

public abstract class FluidCoFH {

    protected RegistryObject<FlowingFluid> stillFluid;
    protected RegistryObject<FlowingFluid> flowingFluid;

    protected RegistryObject<FlowingFluidBlock> block;
    protected RegistryObject<Item> bucket;

    protected ForgeFlowingFluid.Properties properties;

    // region HELPERS
    public static String flowing(String fluid) {

        return fluid + "_flowing";
    }

    public static String bucket(String fluid) {

        return fluid + "_bucket";
    }
    // endregion
}
