package cofh.lib.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.ACTIVE;
import static cofh.lib.util.constants.Constants.FACING_HORIZONTAL;

public class TileBlock4Way extends TileBlockCoFH {

    public TileBlock4Way(Properties builder, Supplier<? extends TileEntity> supplier) {

        super(builder, supplier);
        this.setDefaultState(this.stateContainer.getBaseState().with(ACTIVE, false));

        System.out.println("CHECK: " + supplier);
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

        return this.getDefaultState().with(FACING_HORIZONTAL, context.getPlayer().getAdjustedHorizontalFacing().getOpposite()).with(ACTIVE, false);
    }

}
