package cofh.lib.block.rails;

import net.minecraft.block.BlockState;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RailBlockStraight extends RailBlockDefault {

    @Override
    public IProperty<RailShape> getShapeProperty() {

        return RAIL_STRAIGHT;
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {

    }

    @Override
    @Deprecated
    public BlockState rotate(BlockState state, Rotation rot) {

        IProperty<RailShape> shape = getShapeProperty();

        switch (rot) {
            case CLOCKWISE_180:
                switch (state.get(shape)) {
                    case ASCENDING_EAST:
                        return state.with(shape, RailShape.ASCENDING_WEST);
                    case ASCENDING_WEST:
                        return state.with(shape, RailShape.ASCENDING_EAST);
                    case ASCENDING_NORTH:
                        return state.with(shape, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH:
                        return state.with(shape, RailShape.ASCENDING_NORTH);
                }
            case COUNTERCLOCKWISE_90:
                switch (state.get(shape)) {
                    case ASCENDING_EAST:
                        return state.with(shape, RailShape.ASCENDING_NORTH);
                    case ASCENDING_WEST:
                        return state.with(shape, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_NORTH:
                        return state.with(shape, RailShape.ASCENDING_WEST);
                    case ASCENDING_SOUTH:
                        return state.with(shape, RailShape.ASCENDING_EAST);
                    case NORTH_SOUTH:
                        return state.with(shape, RailShape.EAST_WEST);
                    case EAST_WEST:
                        return state.with(shape, RailShape.NORTH_SOUTH);
                }
            case CLOCKWISE_90:
                switch (state.get(shape)) {
                    case ASCENDING_EAST:
                        return state.with(shape, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_WEST:
                        return state.with(shape, RailShape.ASCENDING_NORTH);
                    case ASCENDING_NORTH:
                        return state.with(shape, RailShape.ASCENDING_EAST);
                    case ASCENDING_SOUTH:
                        return state.with(shape, RailShape.ASCENDING_WEST);
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

        IProperty<RailShape> shape = getShapeProperty();
        RailShape direction = state.get(shape);

        switch (mirrorIn) {
            case LEFT_RIGHT:
                switch (direction) {
                    case ASCENDING_NORTH:
                        return state.with(shape, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH:
                        return state.with(shape, RailShape.ASCENDING_NORTH);
                }

            case FRONT_BACK:
                switch (direction) {
                    case ASCENDING_EAST:
                        return state.with(shape, RailShape.ASCENDING_WEST);
                    case ASCENDING_WEST:
                        return state.with(shape, RailShape.ASCENDING_EAST);
                }
        }
        return super.mirror(state, mirrorIn);
    }

}
