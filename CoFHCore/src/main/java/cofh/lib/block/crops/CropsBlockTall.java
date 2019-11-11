package cofh.lib.block.crops;

import cofh.lib.util.Utils;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

import java.util.Random;

import static cofh.lib.util.constants.Constants.AGE_TALL;

public class CropsBlockTall extends CropsBlockCoFH {

    protected int splitAge = 4;
    protected int splitOffset;

    public CropsBlockTall(Properties builder, PlantType type, int growLight, float growMod) {

        super(builder, type, growLight, growMod);
        this.splitOffset = getSplitAge() + getMaximumAge() - getHarvestAge();
    }

    public CropsBlockTall(Properties builder, int growLight, float growMod) {

        super(builder, growLight, growMod);
        this.splitOffset = getSplitAge() + getMaximumAge() - getHarvestAge();
    }

    public CropsBlockTall(Properties builder) {

        super(builder);
        this.splitOffset = getSplitAge() + getMaximumAge() - getHarvestAge();
    }

    protected boolean isTop(BlockState state) {

        return getAge(state) > getMaximumAge();
    }

    protected int getSplitAge() {

        return splitAge;
    }

    protected int getSplitOffset() {

        return splitOffset;
    }

    @Override
    public IntegerProperty getAgeProperty() {

        return AGE_TALL;
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {

        if (!worldIn.isAreaLoaded(pos, 1) || isTop(state)) {
            return;
        }
        BlockPos above = pos.up();
        if (worldIn.getBlockState(above).getBlock() != this && !worldIn.isAirBlock(above)) {
            return;
        }
        if (worldIn.getLightSubtracted(above, 0) >= growLight) {
            if (!canHarvest(state)) {
                int age = getAge(state);
                float growthChance = MathHelper.maxF(getGrowthChance(this, worldIn, pos) * growMod, 0.1F);
                if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / growthChance) + 1) == 0)) {
                    int newAge = age + 1 > getMaximumAge() ? getHarvestAge() : age + 1;
                    worldIn.setBlockState(pos, this.withAge(newAge), 2);
                    if (newAge >= getSplitAge()) {
                        worldIn.setBlockState(pos.up(), this.withAge(newAge + getSplitOffset()), 2);
                    }
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {

        return super.isValidPosition(state, worldIn, pos) && worldIn.isAirBlock(pos.up());
    }

    // region IGrowable
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {

        if (canHarvest(state)) {
            return;
        }
        if (isTop(state)) {
            int age = this.getAge(state) - getSplitOffset();
            int boost = this.getBonemealAgeIncrease(worldIn);
            int newAge = age + boost > getMaximumAge() ? getHarvestAge() : age + boost;
            worldIn.setBlockState(pos, this.withAge(newAge + getSplitOffset()), 2);
            worldIn.setBlockState(pos.down(), this.withAge(newAge), 2);
        } else {
            BlockPos above = pos.up();
            if (worldIn.getBlockState(above).getBlock() != this && !worldIn.isAirBlock(above)) {
                return;
            }
            int age = this.getAge(state);
            int boost = this.getBonemealAgeIncrease(worldIn);
            int newAge = age + boost > getMaximumAge() ? getHarvestAge() : age + boost;
            worldIn.setBlockState(pos, this.withAge(newAge), 2);
            if (newAge >= getSplitAge()) {
                worldIn.setBlockState(above, this.withAge(newAge + getSplitOffset()), 2);
            }
        }
    }
    // endregion

    // region IHarvestable
    @Override
    public boolean canHarvest(BlockState state) {

        return getAge(state) == getHarvestAge() + (isTop(state) ? getSplitOffset() : 0);
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
            Utils.dropItemStackIntoWorldWithVelocity(new ItemStack(getCropItem(), 2 + MathHelper.binomialDist(fortune, 0.5D)), world, pos);
            if (isTop(state)) {
                world.setBlockState(pos, this.withAge(getPostHarvestAge() + getSplitOffset()), 2);
                world.setBlockState(pos.down(), this.withAge(getPostHarvestAge()), 2);
                Utils.dropItemStackIntoWorldWithVelocity(new ItemStack(getCropItem(), 2 + MathHelper.binomialDist(fortune, 0.5D)), world, pos.down());
            } else {
                world.setBlockState(pos, this.withAge(getPostHarvestAge()), 2);
                world.setBlockState(pos.up(), this.withAge(getPostHarvestAge() + getSplitOffset()), 2);
                Utils.dropItemStackIntoWorldWithVelocity(new ItemStack(getCropItem(), 2 + MathHelper.binomialDist(fortune, 0.5D)), world, pos.up());
            }
        } else {
            world.destroyBlock(pos, true);
            world.destroyBlock(isTop(state) ? pos.down() : pos.up(), true);
        }
        return true;
    }
    // endregion
}
