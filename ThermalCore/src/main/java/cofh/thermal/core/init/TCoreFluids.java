package cofh.thermal.core.init;

public class TCoreFluids {

    private TCoreFluids() {

    }

    public static void register() {

    }

    // region HELPERS
    private static String flowing(String fluid) {

        return fluid + "_flowing";
    }

    private static String bucket(String fluid) {

        return fluid + "_bucket";
    }
    // endregion

    private static final String ID_REDSTONE_FLUID = "fluid_redstone";
    private static final String ID_GLOWSTONE_FLUID = "fluid_glowstone";
    private static final String ID_ENDER_FLUID = "fluid_ender";

    //    public static RegistryObject<FlowingFluid> fluidRedstoneStill = FLUIDS.register(ID_REDSTONE_FLUID, () -> new ForgeFlowingFluid.Source(FluidsTSeries.redstoneProperties));
    //    public static RegistryObject<FlowingFluid> fluidRedstoneFlowing = FLUIDS.register(flowing(ID_REDSTONE_FLUID), () -> new ForgeFlowingFluid.Flowing(FluidsTSeries.redstoneProperties));
    //
    //    public static RegistryObject<FlowingFluidBlock> fluidRedstoneBlock = BLOCKS.register(ID_REDSTONE_FLUID, () -> new FlowingFluidBlock(fluidRedstoneStill, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));
    //    public static RegistryObject<Item> fluidRedstoneBucket = ITEMS.register(bucket(ID_REDSTONE_FLUID), () -> new BucketItem(fluidRedstoneStill, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroupsTSeries.THERMAL_TOOLS)));
    //
    //    public static final ResourceLocation FLUID_STILL = new ResourceLocation("minecraft:block/brown_mushroom_block");
    //    public static final ResourceLocation FLUID_FLOWING = new ResourceLocation("minecraft:block/mushroom_stem");
    //
    //    public static final ForgeFlowingFluid.Properties redstoneProperties = new ForgeFlowingFluid.Properties(fluidRedstoneStill, fluidRedstoneFlowing, FluidAttributes.builder(FLUID_STILL, FLUID_FLOWING)).bucket(fluidRedstoneBucket).block(fluidRedstoneBlock);

}
