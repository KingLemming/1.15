package cofh.thermal.core.init;

import cofh.core.block.GunpowderBlock;
import cofh.core.util.ProxyUtils;
import cofh.lib.block.OreBlockCoFH;
import cofh.lib.block.TileBlock4Way;
import cofh.lib.block.storage.MetalStorageBlock;
import cofh.thermal.core.common.ThermalConfig;
import cofh.thermal.core.inventory.container.workbench.TinkerBenchContainer;
import cofh.thermal.core.tileentity.workbench.TinkerBenchTile;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;

import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.thermal.core.ThermalCore.*;
import static cofh.thermal.core.common.ThermalFeatures.enableTinkerBench;
import static cofh.thermal.core.common.ThermalFeatures.enableVanillaBlocks;
import static cofh.thermal.core.common.ThermalReferences.ID_TINKER_BENCH;
import static cofh.thermal.core.common.ThermalReferences.TINKER_BENCH_BLOCK;
import static cofh.thermal.core.init.TCoreReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.registerAugBlock;
import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static net.minecraft.block.Block.Properties.create;

public class TCoreBlocks {

    private TCoreBlocks() {

    }

    public static void register() {

        registerVanilla();
        registerResources();
        registerStorage();
        registerBuildingBlocks();

        registerTileBlocks();
        registerTileContainers();
        registerTileEntities();
    }

    public static void setup() {

        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFireInfo(BLOCKS.get(ID_CHARCOAL_BLOCK), 5, 5);
        fire.setFireInfo(BLOCKS.get(ID_GUNPOWDER_BLOCK), 15, 100);
        fire.setFireInfo(BLOCKS.get(ID_SUGAR_CANE_BLOCK), 60, 20);
        fire.setFireInfo(BLOCKS.get(ID_BAMBOO_BLOCK), 60, 20);
    }

    // region HELPERS
    private static void registerVanilla() {

        registerBlock(ID_CHARCOAL_BLOCK, () -> new Block(create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.STONE)), enableVanillaBlocks);
        registerBlock(ID_GUNPOWDER_BLOCK, () -> new GunpowderBlock(create(Material.TNT, MaterialColor.GRAY).hardnessAndResistance(0.5F).sound(SoundType.SAND)), enableVanillaBlocks);
        registerBlock(ID_SUGAR_CANE_BLOCK, () -> new RotatedPillarBlock(create(Material.ORGANIC, MaterialColor.FOLIAGE).hardnessAndResistance(1.0F).sound(SoundType.CROP)) {

            @Override
            public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {

                entityIn.onLivingFall(fallDistance, 0.6F);
            }
        }, enableVanillaBlocks);
        registerBlock(ID_BAMBOO_BLOCK, () -> new RotatedPillarBlock(create(Material.ORGANIC, MaterialColor.FOLIAGE).hardnessAndResistance(1.0F).sound(SoundType.WOOD)) {

            @Override
            public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {

                entityIn.onLivingFall(fallDistance, 0.8F);
            }
        }, enableVanillaBlocks);

        registerBlock(ID_APPLE_BLOCK, () -> new Block(create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), enableVanillaBlocks);
        registerBlock(ID_CARROT_BLOCK, () -> new Block(create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), enableVanillaBlocks);
        registerBlock(ID_POTATO_BLOCK, () -> new Block(create(Material.WOOD, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), enableVanillaBlocks);
        registerBlock(ID_BEETROOT_BLOCK, () -> new Block(create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), enableVanillaBlocks);
    }

    private static void registerResources() {

        registerBlock(ID_APATITE_ORE, () -> new OreBlockCoFH(1).xp(0, 2));
        registerBlock(ID_CINNABAR_ORE, () -> new OreBlockCoFH(1).xp(0, 2));
        registerBlock(ID_NITER_ORE, () -> new OreBlockCoFH(1).xp(0, 2));
        registerBlock(ID_SULFUR_ORE, () -> new OreBlockCoFH(1).xp(0, 2));

        registerBlock(ID_COPPER_ORE, () -> new OreBlockCoFH(1));
        registerBlock(ID_TIN_ORE, () -> new OreBlockCoFH(1));
        registerBlock(ID_LEAD_ORE, () -> new OreBlockCoFH(2));
        registerBlock(ID_SILVER_ORE, () -> new OreBlockCoFH(2));
        registerBlock(ID_NICKEL_ORE, () -> new OreBlockCoFH(2));

        registerBlock(ID_RUBY_ORE, () -> new OreBlockCoFH(2).xp(3, 7));
        registerBlock(ID_SAPPHIRE_ORE, () -> new OreBlockCoFH(2).xp(3, 7));
    }

    private static void registerStorage() {

        registerBlock(ID_APATITE_BLOCK, () -> new Block(create(Material.ROCK, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE)));
        registerBlock(ID_CINNABAR_BLOCK, () -> new Block(create(Material.ROCK, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE)));
        registerBlock(ID_NITER_BLOCK, () -> new Block(create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE)));
        registerBlock(ID_SULFUR_BLOCK, () -> new Block(create(Material.ROCK, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE)) {

            @Override
            public boolean isFireSource(BlockState state, IBlockReader world, BlockPos pos, Direction side) {

                return side == Direction.UP;
            }
        });

        registerBlock(ID_COPPER_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_TIN_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_LEAD_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_SILVER_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_NICKEL_BLOCK, () -> new MetalStorageBlock(1));

        registerBlock(ID_BRONZE_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_ELECTRUM_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_INVAR_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_CONSTANTAN_BLOCK, () -> new MetalStorageBlock(1));

        registerBlock(ID_SIGNALUM_BLOCK, () -> new MetalStorageBlock(create(Material.IRON, MaterialColor.RED).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestLevel(1).harvestTool(ToolType.PICKAXE).lightValue(7)) {

            @Override
            public boolean canProvidePower(BlockState state) {

                return true;
            }

            @Override
            public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {

                return 15;
            }
        }, Rarity.UNCOMMON);
        registerBlock(ID_LUMIUM_BLOCK, () -> new MetalStorageBlock(create(Material.IRON, MaterialColor.YELLOW).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestLevel(1).harvestTool(ToolType.PICKAXE).lightValue(15)), Rarity.UNCOMMON);
        registerBlock(ID_ENDERIUM_BLOCK, () -> new MetalStorageBlock(create(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(25.0F, 30.0F).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE).lightValue(3)), Rarity.UNCOMMON);

        registerBlock(ID_RUBY_BLOCK, () -> new MetalStorageBlock(MaterialColor.RED, 1));
        registerBlock(ID_SAPPHIRE_BLOCK, () -> new MetalStorageBlock(MaterialColor.BLUE, 1));
    }

    private static void registerBuildingBlocks() {

        registerBlock(ID_MACHINE_FRAME, () -> new Block(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0).notSolid()));

        registerBlock(ID_SIGNALUM_GLASS, () -> new GlassBlock(create(Material.GLASS, MaterialColor.RED).hardnessAndResistance(0.5F).sound(SoundType.GLASS).lightValue(7).notSolid()) {

            @Override
            public boolean canProvidePower(BlockState state) {

                return true;
            }

            @Override
            public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {

                return 15;
            }
        }, Rarity.UNCOMMON);
        registerBlock(ID_LUMIUM_GLASS, () -> new GlassBlock(create(Material.GLASS, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.GLASS).lightValue(15)), Rarity.UNCOMMON);
        registerBlock(ID_ENDERIUM_GLASS, () -> new GlassBlock(create(Material.GLASS, MaterialColor.CYAN).hardnessAndResistance(2.5F).sound(SoundType.GLASS).lightValue(3)), Rarity.UNCOMMON);
    }

    private static void registerTileBlocks() {

        IntSupplier workbenchAugs = () -> ThermalConfig.workbenchAugments;
        Predicate<ItemStack> workbenchValidator = (e) -> true;

        registerAugBlock(ID_TINKER_BENCH, () -> new TileBlock4Way(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F), TinkerBenchTile::new), workbenchAugs, workbenchValidator, enableTinkerBench);
    }

    private static void registerTileContainers() {

        CONTAINERS.register(ID_TINKER_BENCH, () -> IForgeContainerType.create((windowId, inv, data) -> new TinkerBenchContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

    private static void registerTileEntities() {

        TILE_ENTITIES.register(ID_TINKER_BENCH, () -> TileEntityType.Builder.create(TinkerBenchTile::new, TINKER_BENCH_BLOCK).build(null));
    }
    // endregion
}
