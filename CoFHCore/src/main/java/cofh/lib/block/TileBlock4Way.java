package cofh.lib.block;

import cofh.lib.tileentity.TileCoFH;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.ACTIVE;
import static cofh.lib.util.constants.Constants.FACING_HORIZONTAL;

public class TileBlock4Way extends TileBlockCoFH {

    public TileBlock4Way(Properties builder, Supplier<? extends TileCoFH> supplier) {

        super(builder, supplier);
        this.setDefaultState(this.stateContainer.getBaseState().with(ACTIVE, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        super.fillStateContainer(builder);
        builder.add(FACING_HORIZONTAL);
        builder.add(ACTIVE);
    }

    @Override
    public int getLightValue(BlockState state) {

        return state.get(ACTIVE) ? super.getLightValue(state) : 0;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        return this.getDefaultState().with(FACING_HORIZONTAL, context.getPlayer() != null ? context.getPlayer().getAdjustedHorizontalFacing().getOpposite() : Direction.NORTH);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {

        return state.with(FACING_HORIZONTAL, rot.rotate(state.get(FACING_HORIZONTAL)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {

        return state.rotate(mirrorIn.toRotation(state.get(FACING_HORIZONTAL)));
    }

}
