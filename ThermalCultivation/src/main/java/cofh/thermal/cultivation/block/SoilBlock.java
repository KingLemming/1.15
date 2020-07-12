package cofh.thermal.cultivation.block;

import net.minecraft.block.*;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class SoilBlock extends Block {

    public static final BooleanProperty TILLED = BooleanProperty.create("tilled");
    protected static final VoxelShape SHAPE_TILLED = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    protected int boost = 2;

    public SoilBlock(Properties properties) {

        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(TILLED, false));
    }

    public SoilBlock setBoost(int boost) {

        this.boost = boost;
        return this;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        super.fillStateContainer(builder);
        builder.add(TILLED);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {

        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.setBlockState(pos, state.with(TILLED, false), 2);
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

        BlockPos abovePos = pos.up();
        BlockState aboveState = worldIn.getBlockState(abovePos);

        if (aboveState.getBlock() instanceof IPlantable && aboveState.ticksRandomly()) {
            for (int i = 0; i < boost; ++i) {
                aboveState.randomTick(worldIn, abovePos, rand);
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        return state.get(TILLED) ? SHAPE_TILLED : VoxelShapes.fullCube();
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {

        return false;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {

        boolean tilled = state.get(TILLED);
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

    @Override
    public boolean isTransparent(BlockState state) {

        return state.get(TILLED);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {

        BlockState blockstate = worldIn.getBlockState(pos.up());
        return !blockstate.getMaterial().isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isViewBlocking(BlockState state, IBlockReader worldIn, BlockPos pos) {

        return true;
    }

}
