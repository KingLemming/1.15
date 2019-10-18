package cofh.lib.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.ArrayList;

public abstract class BlockTileCoFH extends Block implements IDismantleable {

    public BlockTileCoFH(Properties properties) {

        super(properties);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        addBlockStateProperties(builder);
    }

    protected void addBlockStateProperties(StateContainer.Builder<Block, BlockState> builder) {

    }

    @Override
    public boolean hasTileEntity(BlockState state) {

        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return null;
    }

    // TODO: So much to add.

    // region IDismantleable
    @Override
    public ArrayList<ItemStack> dismantleBlock(IBlockReader world, BlockPos pos, BlockState state, PlayerEntity player, boolean returnDrops) {

        return null;
    }

    @Override
    public boolean canDismantle(IBlockReader world, BlockPos pos, BlockState state, PlayerEntity player) {

        return false;
    }
    // endregion
}
