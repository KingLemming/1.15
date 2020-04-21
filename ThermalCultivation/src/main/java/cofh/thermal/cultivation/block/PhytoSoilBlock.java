package cofh.thermal.cultivation.block;

import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class PhytoSoilBlock extends Block {

    public static final BooleanProperty TILLED = BooleanProperty.create("tilled");
    protected static final VoxelShape SHAPE_TILLED = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    protected int boost = 2;

    public PhytoSoilBlock(Properties properties) {

        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(TILLED, false));
    }

    public PhytoSoilBlock setBoost(int boost) {

        this.boost = boost;
        return this;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        super.fillStateContainer(builder);
        builder.add(TILLED);
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
            case Beach:
            case Cave:
            case Desert:
            case Plains:
                return !tilled;
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

}
