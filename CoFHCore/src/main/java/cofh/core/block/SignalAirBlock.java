package cofh.core.block;

import cofh.core.tileentity.SignalAirTile;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class SignalAirBlock extends AirBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public SignalAirBlock(Properties builder) {

        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(WATERLOGGED);
    }

    @Override
    public IFluidState getFluidState(BlockState state) {

        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        boolean flag = context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {

        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {

        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return new SignalAirTile();
    }

    @Override
    public boolean canProvidePower(BlockState state) {

        return true;
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {

        // return side == Direction.DOWN ? blockState.getWeakPower(blockAccess, pos, side) : 0;
        return blockState.getWeakPower(blockAccess, pos, side);
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {

        TileEntity tile = blockAccess.getTileEntity(pos);
        return tile instanceof SignalAirTile ? ((SignalAirTile) tile).getPower() : 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {

    }

}
