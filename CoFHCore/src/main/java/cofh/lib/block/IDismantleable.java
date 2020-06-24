package cofh.lib.block;

import cofh.lib.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Implemented on Blocks which have some method of being instantly dismantled.
 *
 * @author King Lemming
 */
public interface IDismantleable {

    /**
     * Dismantles the block. If returnDrops is true, the drop(s) should be placed into the player's inventory.
     */
    default List<ItemStack> dismantleBlock(World worldIn, BlockPos pos, BlockState state, PlayerEntity player, boolean returnDrops) {

        ItemStack dropBlock = new ItemStack((IItemProvider) this);
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());

        if (!returnDrops) {
            Utils.dropDismantleStackIntoWorld(dropBlock, worldIn, pos);
        }
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(dropBlock);
        return ret;
    }

    /**
     * Return true if the block can be dismantled. The criteria for this is entirely up to the block.
     */
    default boolean canDismantle(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        return true;
    }

}
