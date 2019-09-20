package cofh.lib.block.crops;

import cofh.lib.block.BlockCoFH;
import cofh.lib.block.IHarvestable;
import cofh.lib.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

import static cofh.lib.util.Constants.CROPS_AABB;
import static cofh.lib.util.constants.Constants.AGE;

public class BlockCrop extends BlockCoFH implements IGrowable, IPlantable, IHarvestable {

    protected final PlantType type;
    protected int reqLight = 9;

    protected ItemStack crop = ItemStack.EMPTY;
    protected ItemStack seed = ItemStack.EMPTY;

    public BlockCrop(Properties properties) {

        this(properties, PlantType.Crop);
    }

    public BlockCrop(Properties properties, PlantType type) {

        super(properties);
        this.type = type;
    }

    public BlockCrop setRequiredLight(int reqLight) {

        this.reqLight = reqLight;
        return this;
    }

    public BlockCrop setCrop(ItemStack crop) {

        this.crop = crop;
        return this;
    }

    public BlockCrop setSeed(ItemStack seed) {

        this.seed = seed;
        return this;
    }

    protected IntegerProperty getAgeProperty() {

        return AGE;
    }

    protected int getAge(BlockState state) {

        return state.get(this.getAgeProperty());
    }

    protected ItemStack getCrop() {

        return crop;
    }

    protected ItemStack getSeed() {

        return seed;
    }

    protected boolean canBlockStay(World worldIn, BlockPos pos, BlockState state) {

        if (state.getBlock() == this) {
            BlockState below = worldIn.getBlockState(pos.down());
            return below.getBlock().canSustainPlant(below, worldIn, pos.down(), Direction.UP, this);
        }
        return true;
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

        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

    @Override
    protected BlockStateContainer createBlockState() {

        BlockStateContainer.Builder builder = new BlockStateContainer.Builder(this);
        builder.add(getAgeProperty());
        return builder.build();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, BlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        return harvest(worldIn, pos, state, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, playerIn.getHeldItem(hand)));
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {

        if (!canBlockStay(worldIn, pos, state)) {
            dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {

        super.tick(state, worldIn, pos, random);
        if (!worldIn.isAreaLoaded(pos, 1)) return;
        if (worldIn.getLightSubtracted(pos, 0) >= reqLight) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }

    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, BlockState state, Random rand) {

        if (!worldIn.isAreaLoaded(pos, 1)) {
            return;
        }
        if (worldIn.getLightFromNeighbors(pos.up()) >= reqLight) {
            if (!canHarvest(state)) {
                int age = getAge(state);
                float growthChance = getGrowthChance(this, worldIn, pos);
                if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / growthChance) + 1) == 0)) {
                    int newAge = age + 1 > getMaximumAge() ? getHarvestAge() : age + 1;
                    worldIn.setBlockState(pos, this.withAge(newAge), 2);
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, BlockState state, BlockPos pos, EnumFacing face) {

        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {

        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos) {

        return CROPS_AABB[MathHelper.clamp(state.get(this.getAgeProperty()), 0, 7)];
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(BlockState blockState, IBlockAccess worldIn, BlockPos pos) {

        return NULL_AABB;
    }

    // region HELPERS
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
                if (state.getBlock().canSustainPlant(state, worldIn, blockpos.add(i, 0, j), Direction.UP, (IPlantable) blockIn)) {
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
        int age = this.getAge(state);
        int boost = this.getBonemealAgeIncrease(worldIn);

        int newAge = age + boost > getMaximumAge() ? getHarvestAge() : age + boost;
        worldIn.setBlockState(pos, this.withAge(newAge), 2);
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
        if (state.getBlock() != this) {
            return getDefaultState();
        }
        return state;
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
            Utils.dropItemStackIntoWorldWithVelocity(getCrop(), world, pos);
            world.setBlockState(pos, this.withAge(getPostHarvestAge()), 2);
        } else {
            world.destroyBlock(pos, true);
        }
        return true;
    }
    // endregion
}
