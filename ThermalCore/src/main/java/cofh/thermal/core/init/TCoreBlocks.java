package cofh.thermal.core.init;

import cofh.core.block.GunpowderBlock;
import cofh.lib.block.OreBlockCoFH;
import cofh.lib.block.storage.MetalStorageBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.item.Rarity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.init.TCoreReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static net.minecraft.block.Block.Properties.create;

public class TCoreBlocks {

    private TCoreBlocks() {

    }

    public static void register() {

        registerVanillaSupplemental();
        registerResources();
        registerMetals();
    }

    public static void setup() {

        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFireInfo(BLOCKS.get(ID_CHARCOAL_BLOCK), 5, 5);
        fire.setFireInfo(BLOCKS.get(ID_BAMBOO_BLOCK), 60, 20);
        fire.setFireInfo(BLOCKS.get(ID_SUGAR_CANE_BLOCK), 60, 20);
        fire.setFireInfo(BLOCKS.get(ID_GUNPOWDER_BLOCK), 15, 100);
    }

    private static void registerVanillaSupplemental() {

        registerBlock(ID_CHARCOAL_BLOCK, () -> new Block(create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.STONE)));
        registerBlock(ID_BAMBOO_BLOCK, () -> new RotatedPillarBlock(create(Material.ORGANIC, MaterialColor.FOLIAGE).hardnessAndResistance(1.0F).sound(SoundType.WOOD)) {

            @Override
            public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {

                entityIn.onLivingFall(fallDistance, 0.8F);
            }
        });
        registerBlock(ID_SUGAR_CANE_BLOCK, () -> new RotatedPillarBlock(create(Material.ORGANIC, MaterialColor.FOLIAGE).hardnessAndResistance(1.0F).sound(SoundType.CROP)) {

            @Override
            public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {

                entityIn.onLivingFall(fallDistance, 0.6F);
            }
        });
        registerBlock(ID_GUNPOWDER_BLOCK, () -> new GunpowderBlock(create(Material.TNT, MaterialColor.GRAY).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
    }

    private static void registerResources() {

        registerBlock(ID_APATITE_ORE, () -> new OreBlockCoFH(1).xp(0, 2));
        registerBlock(ID_CINNABAR_ORE, () -> new OreBlockCoFH(1).xp(0, 2));
        registerBlock(ID_NITER_ORE, () -> new OreBlockCoFH(1).xp(0, 2));
        registerBlock(ID_SULFUR_ORE, () -> new OreBlockCoFH(1).xp(0, 2));

        registerBlock(ID_APATITE_BLOCK, () -> new Block(create(Material.ROCK, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE)));
        registerBlock(ID_CINNABAR_BLOCK, () -> new Block(create(Material.ROCK, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE)));
        registerBlock(ID_NITER_BLOCK, () -> new Block(create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE)));
        registerBlock(ID_SULFUR_BLOCK, () -> new Block(create(Material.ROCK, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE)) {

            @Override
            public boolean isFireSource(BlockState state, IBlockReader world, BlockPos pos, Direction side) {

                return side == Direction.UP;
            }
        });
    }

    private static void registerMetals() {

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

}
