package cofh.lib.block.rails;

import net.minecraft.block.BlockState;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class RailBlockStraightFlat extends RailBlockDefault {

    @Override
    public IProperty<RailShape> getShapeProperty() {

        return RAIL_STRAIGHT_FLAT;
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {

    }

    @Override
    public boolean canMakeSlopes(BlockState state, IBlockReader world, BlockPos pos) {

        return false;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {

        IProperty<RailShape> shape = getShapeProperty();

        switch (rot) {
            case COUNTERCLOCKWISE_90:
                switch (state.get(shape)) {
                    case NORTH_SOUTH:
                        return state.with(shape, RailShape.EAST_WEST);
                    case EAST_WEST:
                        return state.with(shape, RailShape.NORTH_SOUTH);
                }
            case CLOCKWISE_90:
                switch (state.get(shape)) {
                    case NORTH_SOUTH:
                        return state.with(shape, RailShape.EAST_WEST);
                    case EAST_WEST:
                        return state.with(shape, RailShape.NORTH_SOUTH);
                }
            default:
                return state;
        }
    }

    @Override
    @Deprecated
    public BlockState mirror(BlockState state, Mirror mirrorIn) {

        return state;
    }

}
