package cofh.thermal.core.block;

import cofh.lib.block.TileBlockCoFH;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import static cofh.lib.util.constants.Constants.ACTIVE;
import static cofh.lib.util.constants.Constants.FACING_HORIZONTAL;

public class Abstract4WayBlock extends TileBlockCoFH {

    public Abstract4WayBlock(Properties builder) {

        super(builder);
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

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return null;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        return this.getDefaultState().with(FACING_HORIZONTAL, context.getPlayer().getAdjustedHorizontalFacing().getOpposite()).with(ACTIVE, false);
    }

}
