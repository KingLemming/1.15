package cofh.lib.block.rails;

import cofh.lib.block.IDismantleable;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.DetectorRailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DetectorRailBlockCoFH extends DetectorRailBlock implements IDismantleable {

    protected float maxSpeed = 0.4F;

    public DetectorRailBlockCoFH(Properties builder) {

        super(builder);
    }

    public DetectorRailBlockCoFH speed(float maxSpeed) {

        this.maxSpeed = MathHelper.clamp(maxSpeed, 0F, 1F);
        return this;
    }

    @Override
    public float getRailMaxSpeed(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {

        return maxSpeed;
    }

    @Override
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
