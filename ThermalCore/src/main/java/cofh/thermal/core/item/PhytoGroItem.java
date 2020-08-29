package cofh.thermal.core.item;

import cofh.lib.item.ItemCoFH;
import net.minecraft.block.*;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;

public class PhytoGroItem extends ItemCoFH {

    protected static final int CLOUD_DURATION = 20;

    protected int radius = 2;

    public PhytoGroItem(Properties builder) {

        super(builder);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        World world = context.getWorld();
        BlockPos pos = context.getPos();
        if (growPlants(context.getItem(), world, pos, context, radius)) {
            if (!world.isRemote) {
                makeAreaOfEffectCloud(world, pos, radius);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    protected static boolean growPlants(ItemStack stack, World worldIn, BlockPos pos, ItemUseContext context, int radius) {

        BlockState state = worldIn.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            int hook = ForgeEventFactory.onApplyBonemeal(player, worldIn, pos, state, stack);
            if (hook != 0) {
                return hook > 0;
            }
        }
        boolean used = false;
        for (BlockPos iterPos : BlockPos.getAllInBoxMutable(pos.add(-radius, -1, -radius), pos.add(radius, 1, radius))) {
            used |= growPlant(worldIn, iterPos, worldIn.getBlockState(iterPos));
        }
        used |= growSeagrass(worldIn, pos, context.getFace());
        if (used) {
            stack.shrink(1);
        }
        return used;
    }

    protected static boolean growPlant(World worldIn, BlockPos pos, BlockState state) {

        if (state.getBlock() instanceof IGrowable) {
            IGrowable growable = (IGrowable) state.getBlock();
            if (growable.canGrow(worldIn, pos, state, worldIn.isRemote)) {
                if (worldIn instanceof ServerWorld) {
                    if (growable.canUseBonemeal(worldIn, worldIn.rand, pos, state)) {
                        // TODO: Remove try/catch when Mojang fixes base issue.
                        try {
                            growable.grow((ServerWorld) worldIn, worldIn.rand, pos, state);
                        } catch (Exception e) {
                            // Vanilla issue causes bamboo to crash if grown close to world height
                            if (!(growable instanceof BambooBlock)) {
                                throw e;
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    // TODO: Revisit
    //    private static boolean growCoral(World worldIn, BlockPos pos, BlockState state) {
    //
    //        if (state.getBlock().isIn(BlockTags.WALL_CORALS)) {
    //            for (int i = 0; !state.isValidPosition(worldIn, pos) && i < 4; ++i) {
    //                state = state.with(DeadCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.random(random));
    //            }
    //        }
    //        return false;
    //    }

    public static boolean growSeagrass(World worldIn, BlockPos pos, @Nullable Direction side) {

        if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER && worldIn.getFluidState(pos).getLevel() == 8) {
            if (worldIn instanceof ServerWorld) {
                label80:
                for (int i = 0; i < 128; ++i) {
                    BlockPos blockpos = pos;
                    Biome biome = worldIn.getBiome(pos);
                    BlockState blockstate = Blocks.SEAGRASS.getDefaultState();

                    for (int j = 0; j < i / 16; ++j) {
                        blockpos = blockpos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                        biome = worldIn.getBiome(blockpos);
                        if (worldIn.getBlockState(blockpos).isCollisionShapeOpaque(worldIn, blockpos)) {
                            continue label80;
                        }
                    }
                    // FORGE: Use BiomeDictionary here to allow modded warm ocean biomes to spawn coral from bonemeal
                    if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.HOT)) {
                        if (i == 0 && side != null && side.getAxis().isHorizontal()) {
                            blockstate = BlockTags.WALL_CORALS.getRandomElement(worldIn.rand).getDefaultState().with(DeadCoralWallFanBlock.FACING, side);
                        } else if (random.nextInt(4) == 0) {
                            blockstate = BlockTags.UNDERWATER_BONEMEALS.getRandomElement(random).getDefaultState();
                        }
                    }
                    if (blockstate.getBlock().isIn(BlockTags.WALL_CORALS)) {
                        for (int k = 0; !blockstate.isValidPosition(worldIn, blockpos) && k < 4; ++k) {
                            blockstate = blockstate.with(DeadCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.random(random));
                        }
                    }
                    if (blockstate.isValidPosition(worldIn, blockpos)) {
                        BlockState blockstate1 = worldIn.getBlockState(blockpos);
                        if (blockstate1.getBlock() == Blocks.WATER && worldIn.getFluidState(blockpos).getLevel() == 8) {
                            worldIn.setBlockState(blockpos, blockstate, 3);
                        } else if (blockstate1.getBlock() == Blocks.SEAGRASS && random.nextInt(10) == 0) {
                            ((IGrowable) Blocks.SEAGRASS).grow((ServerWorld) worldIn, random, blockpos, blockstate1);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    protected static void makeAreaOfEffectCloud(World world, BlockPos pos, int radius) {

        boolean isPlant = world.getBlockState(pos).getBlock() instanceof IPlantable;
        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, pos.getX() + 0.5D, pos.getY() + (isPlant ? 0.0D : 1.0D), pos.getZ() + 0.5D);
        cloud.setRadius(1);
        cloud.setParticleData(ParticleTypes.HAPPY_VILLAGER);
        cloud.setDuration(CLOUD_DURATION);
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick((1 + radius - cloud.getRadius()) / (float) cloud.getDuration());

        world.addEntity(cloud);
    }

}
