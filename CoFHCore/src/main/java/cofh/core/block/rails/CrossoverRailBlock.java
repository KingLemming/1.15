package cofh.core.block.rails;

import cofh.core.block.IDismantleable;
import cofh.core.util.helpers.MathHelper;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static cofh.core.util.constants.Constants.RAIL_STRAIGHT_FLAT;

public class CrossoverRailBlock extends AbstractRailBlock implements IDismantleable {

    protected float maxSpeed = 0.4F;

    public CrossoverRailBlock(Properties builder) {

        super(true, builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(getShapeProperty(), RailShape.NORTH_SOUTH));
    }

    public CrossoverRailBlock speed(float maxSpeed) {

        this.maxSpeed = MathHelper.clamp(maxSpeed, 0F, 1F);
        return this;
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
    public IProperty<RailShape> getShapeProperty() {

        return RAIL_STRAIGHT_FLAT;
    }

    @Override
    public boolean canMakeSlopes(BlockState state, IBlockReader world, BlockPos pos) {

        return false;
    }

    @Override
    public RailShape getRailDirection(BlockState state, IBlockReader world, BlockPos pos, @Nullable AbstractMinecartEntity cart) {

        if (cart != null) {
            double absX = Math.abs(cart.getMotion().x);
            double absZ = Math.abs(cart.getMotion().z);
            if (absX > absZ) {
                return RailShape.EAST_WEST;
            } else if (absZ > absX) {
                return RailShape.NORTH_SOUTH;
            }
        }
        return state.get(getShapeProperty());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {

        return state;
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {

        return state;
    }

}
