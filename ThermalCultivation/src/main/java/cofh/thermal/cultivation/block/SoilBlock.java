package cofh.thermal.cultivation.block;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

import static cofh.lib.util.constants.Constants.CHARGED;

public class SoilBlock extends Block {

    protected static final VoxelShape SHAPE_TILLED = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public SoilBlock(Properties properties) {

        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(CHARGED, 3));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        super.fillStateContainer(builder);
        builder.add(CHARGED);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

        BlockPos abovePos = pos.up();
        BlockState aboveState = worldIn.getBlockState(abovePos);

        if (aboveState.getBlock() instanceof IPlantable && aboveState.ticksRandomly()) {
            int charge = state.get(CHARGED);
            int boost = 1 + charge;
            for (int i = 0; i < boost; ++i) {
                aboveState.randomTick(worldIn, abovePos, rand);
            }
            if (rand.nextInt(boost) > 0) {
                worldIn.setBlockState(pos, state.with(CHARGED, charge - 1), 2);
            }
        }
    }

    @Override
    public int getLightValue(BlockState state) {

        return state.get(CHARGED) > 0 ? super.getLightValue(state) : 0;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {

        return canSustainPlant(world, pos, facing, plantable, false);
    }

    protected boolean canSustainPlant(IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable, boolean tilled) {

        if (plantable.getPlant(world, pos.offset(facing)).getBlock() instanceof AttachedStemBlock) {
            return true;
        }
        switch (plantable.getPlantType(world, pos.up())) {
            case Crop:
                return tilled;
            case Cave:
            case Desert:
            case Plains:
                return !tilled;
            case Beach:
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockPos qPos = pos.offset(direction);
                    if (world.getFluidState(qPos).isTagged(FluidTags.WATER) || world.getBlockState(qPos).getBlock() == Blocks.FROSTED_ICE) {
                        return true;
                    }
                }
                break;
            case Nether:
            case Water:
                return false;
        }
        return false;
    }

    @Override
    public boolean isFertile(BlockState state, IBlockReader world, BlockPos pos) {

        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isViewBlocking(BlockState state, IBlockReader worldIn, BlockPos pos) {

        return true;
    }

}
