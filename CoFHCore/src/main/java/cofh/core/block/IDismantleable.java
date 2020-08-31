package cofh.core.block;

import cofh.core.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/**
 * Implemented on Blocks which have some method of being instantly dismantled.
 *
 * @author King Lemming
 */
public interface IDismantleable {

    ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state);

    /**
     * Dismantles the block. If returnDrops is true, the drop(s) should be placed into the player's inventory.
     */
    default void dismantleBlock(World worldIn, BlockPos pos, BlockState state, PlayerEntity player, boolean returnDrops) {

        ItemStack dropBlock = getItem(worldIn, pos, state);
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());

        if (!returnDrops || player == null) {
            Utils.dropDismantleStackIntoWorld(dropBlock, worldIn, pos);
        } else {
            player.addItemStackToInventory(dropBlock);
        }
    }

    /**
     * Return true if the block can be dismantled. The criteria for this is entirely up to the block.
     */
    default boolean canDismantle(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        return true;
    }

}
