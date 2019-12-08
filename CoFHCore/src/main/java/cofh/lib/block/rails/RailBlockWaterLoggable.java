package cofh.lib.block.rails;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.RailShape;

public class RailBlockWaterLoggable extends RailBlockCoFH implements IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public RailBlockWaterLoggable(Properties builder) {

        this(false, builder);
    }

    public RailBlockWaterLoggable(boolean disableCorners, Properties builder) {

        super(disableCorners, builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(getShapeProperty(), RailShape.NORTH_SOUTH).with(WATERLOGGED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(getShapeProperty(), WATERLOGGED);
    }

    @Override
    public IFluidState getFluidState(BlockState state) {

        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

}
