package cofh.thermal.core.init;

import cofh.lib.block.OreBlockCoFH;
import cofh.lib.block.storage.StorageBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Rarity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import static cofh.thermal.core.common.ThermalReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static net.minecraft.block.Block.Properties.create;

public class TCoreBlocks {

    private TCoreBlocks() {

    }

    public static void register() {

        registerBlock(ID_APATITE_ORE, () -> new OreBlockCoFH(1).xp(0, 2));
        registerBlock(ID_NITER_ORE, () -> new OreBlockCoFH(1).xp(0, 2));
        registerBlock(ID_SULFUR_ORE, () -> new OreBlockCoFH(1).xp(0, 2));

        registerBlock(ID_APATITE_BLOCK, () -> new StorageBlock(create(Material.ROCK, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
        registerBlock(ID_NITER_BLOCK, () -> new StorageBlock(create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
        registerBlock(ID_SULFUR_BLOCK, () -> new StorageBlock(create(Material.ROCK, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)) {

            @Override
            public boolean isFireSource(BlockState state, IBlockReader world, BlockPos pos, Direction side) {

                return side == Direction.UP;
            }
        });

        registerBlock(ID_SIGNALUM_BLOCK, () -> new StorageBlock(create(Material.IRON, MaterialColor.RED).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestLevel(1).harvestTool(ToolType.PICKAXE).lightValue(7)) {

            @Override
            public boolean canProvidePower(BlockState state) {

                return true;
            }

            @Override
            public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {

                return 15;
            }
        }, Rarity.UNCOMMON);

        registerBlock(ID_LUMIUM_BLOCK, () -> new StorageBlock(create(Material.IRON, MaterialColor.YELLOW).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestLevel(1).harvestTool(ToolType.PICKAXE).lightValue(15)), Rarity.UNCOMMON);
        registerBlock(ID_ENDERIUM_BLOCK, () -> new StorageBlock(create(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(25.0F, 30.0F).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE).lightValue(3)), Rarity.UNCOMMON);

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
