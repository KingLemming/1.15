package cofh.lib.block.rails;

import cofh.lib.block.IDismantleable;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.*;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

import static cofh.lib.util.constants.Constants.RAIL_DEFAULT;

public class RailBlockCoFH extends AbstractRailBlock implements IDismantleable {

    protected float maxSpeed = 0.4F;

    public RailBlockCoFH(Properties builder) {

        this(false, builder);
    }

    public RailBlockCoFH(boolean disableCorners, Properties builder) {

        super(disableCorners, builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(getShapeProperty(), RailShape.NORTH_SOUTH));
    }

    public RailBlockCoFH setMaxSpeed(float maxSpeed) {

        this.maxSpeed = MathHelper.clamp(maxSpeed, 0F, 1F);
        return this;
    }

    protected void updateState(BlockState state, World worldIn, BlockPos pos, Block blockIn) {

        if (blockIn.getDefaultState().canProvidePower() && (new RailState(worldIn, pos, state)).countAdjacentRails() == 3) {
            this.getUpdatedState(worldIn, pos, state, false);
        }

    }

    @Override
    public IProperty<RailShape> getShapeProperty() {

        return RAIL_DEFAULT;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(getShapeProperty());
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

    // region IDismantleable
    @Override
    public ArrayList<ItemStack> dismantleBlock(World world, BlockPos pos, BlockState state, PlayerEntity player, boolean returnDrops) {

        ItemStack dropBlock = new ItemStack(this);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        if (!returnDrops) {
            Utils.dropDismantleStackIntoWorld(dropBlock, world, pos);
        }
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(dropBlock);
        return ret;
    }

    @Override
    public boolean canDismantle(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        return true;
    }
    // endregion
}
