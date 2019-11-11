package cofh.lib.block.rails;

import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Predicate;

import static net.minecraft.state.properties.RailShape.NORTH_SOUTH;

public class RailBlockDefault extends AbstractRailBlock {

    private static final Predicate<RailShape> PRED_STRAIGHT = dir -> dir != RailShape.NORTH_EAST && dir != RailShape.NORTH_WEST && dir != RailShape.SOUTH_EAST && dir != RailShape.SOUTH_WEST;
    private static final Predicate<RailShape> PRED_NO_SLOPE = dir -> dir != RailShape.ASCENDING_EAST && dir != RailShape.ASCENDING_WEST && dir != RailShape.ASCENDING_NORTH && dir != RailShape.ASCENDING_SOUTH;

    protected static final EnumProperty<RailShape> RAIL_DEFAULT = EnumProperty.create("shape", RailShape.class);
    protected static final EnumProperty<RailShape> RAIL_STRAIGHT = EnumProperty.create("shape", RailShape.class, PRED_STRAIGHT::test);
    protected static final EnumProperty<RailShape> RAIL_FLAT = EnumProperty.create("shape", RailShape.class, PRED_NO_SLOPE::test);
    protected static final EnumProperty<RailShape> RAIL_STRAIGHT_FLAT = EnumProperty.create("shape", RailShape.class, dir -> PRED_STRAIGHT.test(dir) && PRED_NO_SLOPE.test(dir));
    protected static final EnumProperty<RailShape> RAIL_SINGLE = EnumProperty.create("shape", RailShape.class, NORTH_SOUTH);

    protected float maxSpeed = 0.4F;

    public RailBlockDefault() {

        this(false);
    }

    public RailBlockDefault(boolean disableCorners) {

        this(disableCorners, Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL));
    }

    public RailBlockDefault(boolean disableCorners, Properties builder) {

        super(disableCorners, builder);
    }

    public RailBlockDefault setMaxSpeed(float maxSpeed) {

        this.maxSpeed = MathHelper.clamp(maxSpeed, 0F, 1F);
        return this;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(getShapeProperty());
    }

    @Override
    public IProperty<RailShape> getShapeProperty() {

        return RAIL_DEFAULT;
    }

    @Override
    public float getRailMaxSpeed(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {

        return maxSpeed;
    }

    @Override
    public void onMinecartPass(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {

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
                    case SOUTH_EAST:
                        return state.with(shape, RailShape.NORTH_WEST);
                    case SOUTH_WEST:
                        return state.with(shape, RailShape.NORTH_EAST);
                    case NORTH_WEST:
                        return state.with(shape, RailShape.SOUTH_EAST);
                    case NORTH_EAST:
                        return state.with(shape, RailShape.SOUTH_WEST);
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
                    case SOUTH_EAST:
                        return state.with(shape, RailShape.NORTH_EAST);
                    case SOUTH_WEST:
                        return state.with(shape, RailShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return state.with(shape, RailShape.SOUTH_WEST);
                    case NORTH_EAST:
                        return state.with(shape, RailShape.NORTH_WEST);
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
                    case SOUTH_EAST:
                        return state.with(shape, RailShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return state.with(shape, RailShape.NORTH_WEST);
                    case NORTH_WEST:
                        return state.with(shape, RailShape.NORTH_EAST);
                    case NORTH_EAST:
                        return state.with(shape, RailShape.SOUTH_EAST);
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
                    case SOUTH_EAST:
                        return state.with(shape, RailShape.NORTH_EAST);
                    case SOUTH_WEST:
                        return state.with(shape, RailShape.NORTH_WEST);
                    case NORTH_WEST:
                        return state.with(shape, RailShape.SOUTH_WEST);
                    case NORTH_EAST:
                        return state.with(shape, RailShape.SOUTH_EAST);
                    default:
                        return super.mirror(state, mirrorIn);
                }

            case FRONT_BACK:
                switch (direction) {
                    case ASCENDING_EAST:
                        return state.with(shape, RailShape.ASCENDING_WEST);
                    case ASCENDING_WEST:
                        return state.with(shape, RailShape.ASCENDING_EAST);
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    default:
                        break;
                    case SOUTH_EAST:
                        return state.with(shape, RailShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return state.with(shape, RailShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return state.with(shape, RailShape.NORTH_EAST);
                    case NORTH_EAST:
                        return state.with(shape, RailShape.NORTH_WEST);
                }
        }
        return super.mirror(state, mirrorIn);
    }

}
