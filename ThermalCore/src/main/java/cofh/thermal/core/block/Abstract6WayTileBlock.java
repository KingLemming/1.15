package cofh.thermal.core.block;

import cofh.lib.block.TileBlockCoFH;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.ACTIVE;
import static cofh.lib.util.constants.Constants.FACING_ALL;

public class Abstract6WayTileBlock extends TileBlockCoFH {

    public final Supplier<TileEntity> supplier;

    public Abstract6WayTileBlock(Properties builder, Supplier<TileEntity> supplier) {

        super(builder);
        this.supplier = supplier;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        super.fillStateContainer(builder);
        builder.add(FACING_ALL);
        builder.add(ACTIVE);
    }

    @Override
    public int getLightValue(BlockState state) {

        return state.get(ACTIVE) ? super.getLightValue(state) : 0;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return supplier.get();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        return this.getDefaultState().with(FACING_ALL, context.getNearestLookingDirection().getOpposite()).with(ACTIVE, false);
    }

}
