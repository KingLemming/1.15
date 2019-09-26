package cofh.lib.block.crops;

import cofh.lib.block.BlockCoFH;
import cofh.lib.block.IHarvestable;
import cofh.lib.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;
import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.AGE;
import static cofh.lib.util.constants.Constants.CROPS_BY_AGE;

public class CropsBlockCoFH extends BlockCoFH implements IGrowable, IHarvestable, IPlantable {

    protected static final Supplier<Item> AIR_SUPPLY = () -> Items.AIR;

    protected final PlantType type;
    protected int reqLight = 9;

    protected Supplier<Item> crop = AIR_SUPPLY;
    protected Supplier<Item> seed = AIR_SUPPLY;

    public CropsBlockCoFH(Properties properties) {

        this(properties, PlantType.Crop);
    }

    public CropsBlockCoFH(Properties properties, PlantType type) {

        super(properties);
        this.type = type;
    }

    public CropsBlockCoFH setRequiredLight(int reqLight) {

        this.reqLight = reqLight;
        return this;
    }

    public CropsBlockCoFH setCrop(Supplier<Item> crop) {

        this.crop = crop;
        return this;
    }

    public CropsBlockCoFH setSeed(Supplier<Item> seed) {

        this.seed = seed;
        return this;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        return CROPS_BY_AGE[MathHelper.clamp(state.get(getAgeProperty()), 0, CROPS_BY_AGE.length)];
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {

        return state.getBlock() == Blocks.FARMLAND;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        builder.add(getAgeProperty());
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {

        if ((worldIn.getLightSubtracted(pos, 0) >= reqLight - 1 || worldIn.isSkyLightMax(pos))) {
            BlockPos blockpos = pos.down();
            if (state.getBlock() == this)
                return worldIn.getBlockState(blockpos).canSustainPlant(worldIn, blockpos, Direction.UP, this);
            return isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
        }
        return false;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        return harvest(worldIn, pos, state, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItem(handIn)));
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {

        if (entityIn instanceof RavagerEntity && ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn)) {
            worldIn.destroyBlock(pos, true);
        }
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {

        return new ItemStack(getSeedsItem());
    }

    @Override
    public BlockRenderLayer getRenderLayer() {

        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {

        return true;
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {

        if (!worldIn.isAreaLoaded(pos, 1)) {
            return;
        }
        if (worldIn.getLightSubtracted(pos, 0) >= reqLight) {
            if (!canHarvest(state)) {
                int age = getAge(state);
                float growthChance = getGrowthChance(this, worldIn, pos);
                if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / growthChance) + 1) == 0)) {
                    int newAge = age + 1 > getMaximumAge() ? getHarvestAge() : age + 1;
                    worldIn.setBlockState(pos, withAge(newAge), 2);
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {

        return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    // region AGE
    protected IntegerProperty getAgeProperty() {

        return AGE;
    }

    protected int getAge(BlockState state) {

        return state.get(getAgeProperty());
    }

    protected int getHarvestAge() {

        return 7;
    }

    protected int getMaximumAge() {

        return 7;
    }

    protected int getPostHarvestAge() {

        return -1;
    }

    public BlockState withAge(int age) {

        return getDefaultState().with(getAgeProperty(), age);
    }
    // endregion

    // region HELPERS
    protected IItemProvider getCropItem() {

        return crop.get();
    }

    protected IItemProvider getSeedsItem() {

        return seed.get();
    }

    protected int getBonemealAgeIncrease(World worldIn) {

        return MathHelper.nextInt(worldIn.rand, 2, 5);
    }

    protected float getGrowthChance(Block blockIn, World worldIn, BlockPos pos) {

        float f = 1.0F;
        BlockPos blockpos = pos.down();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState state = worldIn.getBlockState(blockpos.add(i, 0, j));
                if (state.getBlock().canSustainPlant(state, worldIn, blockpos.add(i, 0, j), Direction.UP, (net.minecraftforge.common.IPlantable) blockIn)) {
                    f1 = 1.0F;
                    if (state.getBlock().isFertile(state, worldIn, blockpos.add(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }
                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }
                f += f1;
            }
        }
        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();

        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2) {
                f /= 2.0F;
            }
        }
        return f;
    }
    // endregion

    // region IGrowable
    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {

        return !canHarvest(state);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {

        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {

        if (canHarvest(state)) {
            return;
        }
        int age = getAge(state);
        int boost = getBonemealAgeIncrease(worldIn);

        int newAge = age + boost > getMaximumAge() ? getHarvestAge() : age + boost;
        worldIn.setBlockState(pos, withAge(newAge), 2);
    }
    // endregion

    // region IHarvestable
    @Override
    public boolean canHarvest(BlockState state) {

        return getAge(state) == getHarvestAge();
    }

    @Override
    public boolean harvest(World world, BlockPos pos, BlockState state, int fortune) {

        if (!canHarvest(state)) {
            return false;
        }
        if (Utils.isClientWorld(world)) {
            return true;
        }
        if (getPostHarvestAge() >= 0) {
            Utils.dropItemStackIntoWorldWithVelocity(new ItemStack(getCropItem()), world, pos);
            world.setBlockState(pos, withAge(getPostHarvestAge()), 2);
        } else {
            world.destroyBlock(pos, true);
        }
        return true;
    }
    // endregion

    // region IPlantable
    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {

        return type;
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {

        BlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }
    // endregion
}
