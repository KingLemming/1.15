package cofh.lib.block.rails;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import static cofh.lib.util.constants.Constants.RAIL_STRAIGHT_FLAT;

public class RailBlockCrossover extends RailBlockCoFH {

    public RailBlockCrossover(Properties builder) {

        super(true, builder);
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
            if (Math.abs(cart.getMotion().x) > 0) {
                return RailShape.EAST_WEST;
            } else if (Math.abs(cart.getMotion().z) > 0) {
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
