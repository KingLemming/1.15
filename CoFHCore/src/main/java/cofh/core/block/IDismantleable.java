package cofh.core.block;

import cofh.core.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;

/**
 * Implemented on Blocks which have some method of being instantly dismantled.
 *
 * @author King Lemming
 */
public interface IDismantleable extends IForgeBlock {

    /**
     * Dismantles the block. If returnDrops is true, the drop(s) should be placed into the player's inventory.
     */
    default void dismantleBlock(World world, BlockPos pos, BlockState state, RayTraceResult target, PlayerEntity player, boolean returnDrops) {

        ItemStack dropBlock = this.getPickBlock(state, target, world, pos, player);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        if (!returnDrops || player == null) {
            Utils.dropDismantleStackIntoWorld(dropBlock, world, pos);
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
