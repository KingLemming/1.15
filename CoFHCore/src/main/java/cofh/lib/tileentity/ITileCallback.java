package cofh.lib.tileentity;

import cofh.lib.util.IInventoryCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Mostly a sneaky way to reduce some stupid but useful boilerplate. :)
 */
public interface ITileCallback extends IInventoryCallback {

    Block block();

    BlockState state();

    BlockPos pos();

    World world();

    default int invSize() {

        return 0;
    }

    default int augSize() {

        return 0;
    }

    default void callBlockUpdate() {

        if (world() == null) {
            return;
        }
        BlockState state = world().getBlockState(pos());
        world().notifyBlockUpdate(pos(), state, state, 3);
    }

    default void callNeighborStateChange() {

        if (world() == null) {
            return;
        }
        world().notifyNeighborsOfStateChange(pos(), block());
    }

    default void updateComparatorOutputLevel() {

        if (world() == null) {
            return;
        }
        world().updateComparatorOutputLevel(pos(), block());
    }

    default void onControlUpdate() {

    }

    default void onRedstoneUpdate() {

    }

    default void onWrench() {

    }

}
