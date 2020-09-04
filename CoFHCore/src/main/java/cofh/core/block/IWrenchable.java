package cofh.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;

public interface IWrenchable extends IForgeBlock {

    /**
     * Wrenches the block.
     */
    default void wrenchBlock(World world, BlockPos pos, BlockState state, RayTraceResult target, PlayerEntity player) {

        BlockState rotState = this.rotate(state, world, pos, Rotation.CLOCKWISE_90);
        if (rotState != state) {
            world.setBlockState(pos, rotState);
        }
    }

    /**
     * Return true if the block can be wrenched. The criteria for this is entirely up to the block.
     */
    default boolean canWrench(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        return true;
    }

}
