package cofh.thermal.core.init;

import cofh.thermal.core.fluid.ThermalFluid;

public class TCoreFluids {

    private TCoreFluids() {

    }

    public static void register() {

        // ThermalFluid.create("fluid_redstone", "minecraft:block/brown_mushroom_block", "minecraft:block/mushroom_stem");
    }

    // region HELPERS
    private static String flowing(String fluid) {

        return fluid + "_flowing";
    }

    private static String bucket(String fluid) {

        return fluid + "_bucket";
    }
    // endregion

    //    private static final String ID_REDSTONE_FLUID = "fluid_redstone";
    //    private static final String ID_GLOWSTONE_FLUID = "fluid_glowstone";
    //    private static final String ID_ENDER_FLUID = "fluid_ender";
    //
    //    public static final RegistryObject<FlowingFluid> fluidRedstoneStill = FLUIDS.register(ID_REDSTONE_FLUID, () -> new ForgeFlowingFluid.Source(TCoreFluids.redstoneProperties));
    //    public static final RegistryObject<FlowingFluid> fluidRedstoneFlowing = FLUIDS.register(flowing(ID_REDSTONE_FLUID), () -> new ForgeFlowingFluid.Flowing(TCoreFluids.redstoneProperties));
    //
    //    public static final RegistryObject<FlowingFluidBlock> fluidRedstoneBlock = BLOCKS.register(ID_REDSTONE_FLUID, () -> new FlowingFluidBlock(fluidRedstoneStill, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));
    //    public static final RegistryObject<Item> fluidRedstoneBucket = ITEMS.register(bucket(ID_REDSTONE_FLUID), () -> new BucketItem(fluidRedstoneStill, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ThermalItemGroups.THERMAL_TOOLS)));
    //
    //    public static final ForgeFlowingFluid.Properties redstoneProperties = new ForgeFlowingFluid.Properties(fluidRedstoneStill, fluidRedstoneFlowing, FluidAttributes.builder(new ResourceLocation("minecraft:block/brown_mushroom_block"), new ResourceLocation("minecraft:block/mushroom_stem"))).bucket(fluidRedstoneBucket).block(fluidRedstoneBlock);
}
