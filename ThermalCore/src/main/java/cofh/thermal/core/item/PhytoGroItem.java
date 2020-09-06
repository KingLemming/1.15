package cofh.thermal.core.item;

import cofh.core.item.ItemCoFH;
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

public class PhytoGroItem extends ItemCoFH {

    protected static final int CLOUD_DURATION = 20;

    protected int strength = 4;

    public PhytoGroItem(Properties builder) {

        super(builder);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        World world = context.getWorld();
        BlockPos pos = context.getPos();

        if (attemptGrowPlant(world, pos, context, strength)) {
            if (!world.isRemote) {
                world.playEvent(2005, pos, 0);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    protected static boolean attemptGrowPlant(World world, BlockPos pos, ItemUseContext context, int strength) {

        ItemStack stack = context.getItem();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            int hook = ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack);
            if (hook != 0) {
                return hook > 0;
            }
        }
        boolean used;
        used = growPlant(world, pos, state, strength);
        used |= growSeagrass(world, pos, context.getFace());
        if (used) {
            stack.shrink(1);
        }
        return used;
    }

    protected static boolean growPlant(World worldIn, BlockPos pos, BlockState state, int strength) {

        if (state.getBlock() instanceof IGrowable) {
            IGrowable growable = (IGrowable) state.getBlock();
            boolean used = false;
            for (int i = 0; i < strength; ++i) {
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
                    used = true;
                }
            }
            return used;
        }
        return false;
    }

    public static boolean growSeagrass(World worldIn, BlockPos pos, Direction side) {

        BlockState state = worldIn.getBlockState(pos);
        if (!state.isSolidSide(worldIn, pos, side)) {
            return false;
        }
        if (state.getBlock() == Blocks.WATER && worldIn.getFluidState(pos).getLevel() == 8) {
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
