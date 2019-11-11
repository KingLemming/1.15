package cofh.core.block;

import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MagmaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class GlossedMagmaBlock extends MagmaBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;

    public GlossedMagmaBlock(Properties builder) {

        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(AGE);
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {

        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            Material material = worldIn.getBlockState(pos.down()).getMaterial();
            if (material.blocksMovement() || material.isLiquid()) {
                worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {

        if (blockIn == this && this.shouldMelt(worldIn, pos, 2)) {
            this.turnIntoLava(state, worldIn, pos);
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {

        if ((random.nextInt(9) == 0 || this.shouldMelt(worldIn, pos, 4)) && this.slightlyMelt(state, worldIn, pos)) {
            try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
                for (Direction direction : Direction.values()) {
                    blockpos$pooledmutableblockpos.setPos(pos).move(direction);
                    BlockState blockstate = worldIn.getBlockState(blockpos$pooledmutableblockpos);
                    if (blockstate.getBlock() == this && !this.slightlyMelt(blockstate, worldIn, blockpos$pooledmutableblockpos)) {
                        worldIn.getPendingBlockTicks().scheduleTick(blockpos$pooledmutableblockpos, this, MathHelper.nextInt(random, 20, 40));
                    }
                }
            }
        } else {
            worldIn.getPendingBlockTicks().scheduleTick(pos, this, MathHelper.nextInt(random, 20, 40));
        }
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {

        return ItemStack.EMPTY;
    }

    // region HELPERS
    protected void turnIntoLava(BlockState state, World worldIn, BlockPos pos) {

        worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
        worldIn.neighborChanged(pos, Blocks.LAVA, pos);
    }

    protected boolean shouldMelt(IBlockReader worldIn, BlockPos pos, int neighborsRequired) {

        int i = 0;
        try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
            for (Direction direction : Direction.values()) {
                blockpos$pooledmutableblockpos.setPos(pos).move(direction);
                if (worldIn.getBlockState(blockpos$pooledmutableblockpos).getBlock() == this) {
                    ++i;
                    if (i >= neighborsRequired) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    protected boolean slightlyMelt(BlockState state, World worldIn, BlockPos pos) {

        int i = state.get(AGE);
        if (i < 3) {
            worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
            return false;
        } else {
            this.turnIntoLava(state, worldIn, pos);
            return true;
        }
    }
    // endregion
}
