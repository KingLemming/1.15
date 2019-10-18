package cofh.thermal.foundation.init;

import cofh.lib.block.storage.ResourceStorageBlock;
import cofh.lib.item.BlockItemCoFH;
import cofh.thermal.core.init.ItemGroupsTSeries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;

import static cofh.thermal.core.ThermalSeries.BLOCKS;
import static cofh.thermal.core.ThermalSeries.ITEMS;
import static net.minecraft.block.Block.Properties.from;

public class BlocksTFoundation {

    private BlocksTFoundation() {

    }

    public static void register() {

        blockCharcoal = BLOCKS.register(ID_CHARCOAL_BLOCK, () -> new ResourceStorageBlock(from(Blocks.COAL_BLOCK)) {

            @Override
            public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {

                return 5;
            }

            @Override
            public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {

                return 5;
            }
        });
        blockCoalCoke = BLOCKS.register(ID_COAL_COKE_BLOCK, () -> new ResourceStorageBlock(from(Blocks.COAL_BLOCK)) {

            @Override
            public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {

                return 5;
            }

            @Override
            public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {

                return 5;
            }
        });

        ItemGroup group = ItemGroupsTSeries.THERMAL_BLOCKS;

        ITEMS.register(ID_CHARCOAL_BLOCK, () -> new BlockItemCoFH(blockCharcoal.get(), new Item.Properties().group(group)));
        ITEMS.register(ID_COAL_COKE_BLOCK, () -> new BlockItemCoFH(blockCoalCoke.get(), new Item.Properties().group(group)));
    }

    private static RegistryObject<Block> blockCharcoal;
    private static RegistryObject<Block> blockCoalCoke;

    private static final String ID_CHARCOAL_BLOCK = "block_charcoal";
    private static final String ID_COAL_COKE_BLOCK = "block_coal_coke";

}
