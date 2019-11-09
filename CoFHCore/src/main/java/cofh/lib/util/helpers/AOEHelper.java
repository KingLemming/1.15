package cofh.lib.util.helpers;

import cofh.lib.util.RayTracer;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.ArrayList;

import static cofh.lib.capability.CapabilityAOE.AOE_ITEM_CAPABILITY;
import static cofh.lib.util.references.EnsorcellationReferences.*;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.util.Direction.DOWN;

public class AOEHelper {

    private AOEHelper() {

    }

    public static boolean validAOEItem(ItemStack stack) {

        return stack.getCapability(AOE_ITEM_CAPABILITY).isPresent() || stack.getItem() instanceof ToolItem || stack.getItem() instanceof HoeItem;
    }

    public static boolean validAOEMiningItem(ItemStack stack) {

        return stack.getCapability(AOE_ITEM_CAPABILITY).isPresent() || stack.getItem() instanceof ToolItem;
    }

    /**
     * Basically the "default" AOE behavior.
     */
    public static ImmutableList<BlockPos> getAOEBlocks(ItemStack stack, BlockPos pos, PlayerEntity player) {

        int encExcavating = getEnchantmentLevel(EXCAVATING, stack);
        if (encExcavating > 0) {
            return getAOEBlocksMiningRadius(stack, pos, player, encExcavating);
        }
        int encTilling = getEnchantmentLevel(TILLING, stack);
        if (encTilling > 0) {
            return getAOEBlocksHoeRadius(stack, pos, player, encTilling);
        }
        int encFurrowing = getEnchantmentLevel(FURROWING, stack);
        if (encFurrowing > 0) {
            return getAOEBlocksHoeLine(stack, pos, player, encFurrowing * 2);
        }
        return ImmutableList.of();
    }

    // region MINING
    public static ImmutableList<BlockPos> getAOEBlocksMiningRadius(ItemStack stack, BlockPos pos, PlayerEntity player, int radius) {

        ArrayList<BlockPos> area = new ArrayList<>();
        World world = player.getEntityWorld();
        Item tool = stack.getItem();

        BlockPos query;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        BlockRayTraceResult traceResult = RayTracer.retrace(player, RayTraceContext.FluidMode.NONE);
        if (traceResult.getType() == RayTraceResult.Type.MISS || player.isSneaking() || !canToolAffect(tool, stack, world, pos) || radius <= 0) {
            return ImmutableList.of();
        }
        switch (traceResult.getFace()) {
            case DOWN:
            case UP:
                for (int i = x - radius; i <= x + radius; ++i) {
                    for (int k = z - radius; k <= z + radius; ++k) {
                        if (i == x && k == z) {
                            continue;
                        }
                        query = new BlockPos(i, y, k);
                        if (canToolAffect(tool, stack, world, query)) {
                            area.add(query);
                        }
                    }
                }
                break;
            case NORTH:
            case SOUTH:
                int posY = y;
                y += (radius - 1);     // Offset for > 3x3
                for (int i = x - radius; i <= x + radius; ++i) {
                    for (int j = y - radius; j <= y + radius; ++j) {
                        if (i == x && j == posY) {
                            continue;
                        }
                        query = new BlockPos(i, j, z);
                        if (canToolAffect(tool, stack, world, query)) {
                            area.add(query);
                        }
                    }
                }
                break;
            default:
                posY = y;
                y += (radius - 1);     // Offset for > 3x3
                for (int j = y - radius; j <= y + radius; ++j) {
                    for (int k = z - radius; k <= z + radius; ++k) {
                        if (j == posY && k == z) {
                            continue;
                        }
                        query = new BlockPos(x, j, k);
                        if (canToolAffect(tool, stack, world, query)) {
                            area.add(query);
                        }
                    }
                }
                break;
        }
        return ImmutableList.copyOf(area);
    }

    public static ImmutableList<BlockPos> getAOEBlocksMiningArea(ItemStack stack, BlockPos pos, PlayerEntity player, int radius, int depth) {

        ArrayList<BlockPos> area = new ArrayList<>();
        World world = player.getEntityWorld();
        Item tool = stack.getItem();

        BlockPos query;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int depth_min = depth;
        int depth_max = 0;

        BlockRayTraceResult traceResult = RayTracer.retrace(player, RayTraceContext.FluidMode.NONE);
        if (traceResult.getType() == RayTraceResult.Type.MISS || player.isSneaking() || !canToolAffect(tool, stack, world, pos) || (radius <= 0 && depth <= 0)) {
            return ImmutableList.of();
        }
        switch (traceResult.getFace()) {
            case DOWN:
                depth_min = 0;
                depth_max = depth;
            case UP:
                for (int i = x - radius; i <= x + radius; ++i) {
                    for (int j = y - depth_min; j <= y + depth_max; ++j) {
                        for (int k = z - radius; k <= z + radius; ++k) {
                            if (i == x && j == y && k == z) {
                                continue;
                            }
                            query = new BlockPos(i, y, k);
                            if (canToolAffect(tool, stack, world, query)) {
                                area.add(query);
                            }
                        }
                    }
                }
                break;
            case NORTH:
                depth_min = 0;
                depth_max = depth;
            case SOUTH:
                int posY = y;
                y += (radius - 1);     // Offset for > 3x3
                for (int i = x - radius; i <= x + radius; ++i) {
                    for (int j = y - radius; j <= y + radius; ++j) {
                        for (int k = z - depth_min; k <= z + depth_max; ++k) {
                            if (i == x && j == posY && k == z) {
                                continue;
                            }
                            query = new BlockPos(i, j, z);
                            if (canToolAffect(tool, stack, world, query)) {
                                area.add(query);
                            }
                        }
                    }
                }
                break;
            case WEST:
                depth_min = 0;
                depth_max = depth;
            case EAST:
                posY = y;
                y += (radius - 1);     // Offset for > 3x3
                for (int i = x - depth_min; i <= x + depth_max; ++i) {
                    for (int j = y - radius; j <= y + radius; ++j) {
                        for (int k = z - radius; k <= z + radius; ++k) {
                            if (i == x && j == posY && k == z) {
                                continue;
                            }
                            query = new BlockPos(x, j, k);
                            if (canToolAffect(tool, stack, world, query)) {
                                area.add(query);
                            }
                        }
                    }
                }
                break;
        }
        return ImmutableList.copyOf(area);
    }

    public static ImmutableList<BlockPos> getAOEBlocksMiningLine(ItemStack stack, BlockPos pos, PlayerEntity player, int length) {

        ArrayList<BlockPos> area = new ArrayList<>();
        World world = player.getEntityWorld();
        Item tool = stack.getItem();

        BlockPos query;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (player.isSneaking() || !canToolAffect(tool, stack, world, pos) || length <= 0) {
            return ImmutableList.of();
        }
        switch (player.getHorizontalFacing()) {
            case SOUTH:
                for (int k = z + 1; k < z + length + 1; ++k) {
                    query = new BlockPos(x, y, k);
                    if (!canToolAffect(tool, stack, world, query)) {
                        break;
                    }
                    area.add(query);
                }
                break;
            case WEST:
                for (int i = x - 1; i > x - length - 1; --i) {
                    query = new BlockPos(i, y, z);
                    if (!canToolAffect(tool, stack, world, query)) {
                        break;
                    }
                    area.add(query);
                }
                break;
            case NORTH:
                for (int k = z - 1; k > z - length - 1; --k) {
                    query = new BlockPos(x, y, k);
                    if (!canToolAffect(tool, stack, world, query)) {
                        break;
                    }
                    area.add(query);
                }
                break;
            case EAST:
                for (int i = x + 1; i < x + length + 1; ++i) {
                    query = new BlockPos(i, y, z);
                    if (!canToolAffect(tool, stack, world, query)) {
                        break;
                    }
                    area.add(query);
                }
                break;
        }
        return ImmutableList.copyOf(area);
    }
    // endregion

    // region HOE
    public static ImmutableList<BlockPos> getAOEBlocksHoeRadius(ItemStack stack, BlockPos pos, PlayerEntity player, int radius) {

        ArrayList<BlockPos> area = new ArrayList<>();
        World world = player.getEntityWorld();
        boolean weeding = getEnchantmentLevel(WEEDING, stack) > 0;

        BlockPos query;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        BlockRayTraceResult traceResult = RayTracer.retrace(player, RayTraceContext.FluidMode.NONE);
        if (traceResult.getType() == RayTraceResult.Type.MISS || traceResult.getFace() == DOWN || player.isSneaking() || !canHoeAffect(world, pos, weeding) || radius <= 0) {
            return ImmutableList.of();
        }
        for (int i = x - radius; i <= x + radius; ++i) {
            for (int k = z - radius; k <= z + radius; ++k) {
                if (i == x && k == z) {
                    continue;
                }
                query = new BlockPos(i, y, k);
                if (canHoeAffect(world, query, weeding)) {
                    area.add(query);
                }
            }
        }
        return ImmutableList.copyOf(area);
    }

    public static ImmutableList<BlockPos> getAOEBlocksHoeLine(ItemStack stack, BlockPos pos, PlayerEntity player, int length) {

        ArrayList<BlockPos> area = new ArrayList<>();
        World world = player.getEntityWorld();
        boolean weeding = getEnchantmentLevel(WEEDING, stack) > 0;

        BlockPos query;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (player.isSneaking() || !canHoeAffect(world, pos, weeding) || length <= 0) {
            return ImmutableList.of();
        }
        switch (player.getHorizontalFacing()) {
            case SOUTH:
                for (int k = z + 1; k < z + length + 1; ++k) {
                    query = new BlockPos(x, y, k);
                    if (canHoeAffect(world, query, weeding)) {
                        area.add(query);
                    }
                }
                break;
            case WEST:
                for (int i = x - 1; i > x - length - 1; --i) {
                    query = new BlockPos(i, y, z);
                    if (canHoeAffect(world, query, weeding)) {
                        area.add(query);
                    }
                }
                break;
            case NORTH:
                for (int k = z - 1; k > z - length - 1; --k) {
                    query = new BlockPos(x, y, k);
                    if (canHoeAffect(world, query, weeding)) {
                        area.add(query);
                    }
                }
                break;
            case EAST:
                for (int i = x + 1; i < x + length + 1; ++i) {
                    query = new BlockPos(i, y, z);
                    if (canHoeAffect(world, query, weeding)) {
                        area.add(query);
                    }
                }
                break;
        }
        return ImmutableList.copyOf(area);
    }
    // endregion

    // region SICKLE
    public static ImmutableList<BlockPos> getAOEBlocksSickle(ItemStack stack, BlockPos pos, PlayerEntity player, int radius, int height) {

        ArrayList<BlockPos> area = new ArrayList<>();
        World world = player.getEntityWorld();
        Item tool = stack.getItem();

        BlockPos query;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        BlockRayTraceResult traceResult = RayTracer.retrace(player, RayTraceContext.FluidMode.NONE);
        if (traceResult.getType() == RayTraceResult.Type.MISS || player.isSneaking() || !canToolAffect(tool, stack, world, pos) || (radius <= 0 && height <= 0)) {
            return ImmutableList.of();
        }
        for (int i = x - radius; i <= x + radius; ++i) {
            for (int j = y - height; j <= y + height; ++j) {
                for (int k = z - radius; k <= z + radius; ++k) {
                    if (i == x && j == y && k == z) {
                        continue;
                    }
                    query = new BlockPos(i, y, k);
                    if (canToolAffect(tool, stack, world, query)) {
                        area.add(query);
                    }
                }
            }
        }
        return ImmutableList.copyOf(area);
    }
    // endregion

    // region HELPERS
    private static boolean canToolAffect(Item toolItem, ItemStack toolStack, World world, BlockPos pos) {

        BlockState state = world.getBlockState(pos);
        return state.getBlockHardness(world, pos) != -1 && toolItem.canHarvestBlock(toolStack, state) || toolItem.getDestroySpeed(toolStack, state) > 1.0F;
    }

    private static boolean canHoeAffect(World world, BlockPos pos, boolean weeding) {

        BlockState state = world.getBlockState(pos);
        if (HoeItem.HOE_LOOKUP.containsKey(state.getBlock())) {
            BlockPos up = pos.up();
            BlockState stateUp = world.getBlockState(up);
            return world.isAirBlock(up) || (weeding && (stateUp.getMaterial() == Material.PLANTS || stateUp.getMaterial() == Material.TALL_PLANTS) && stateUp.getBlockHardness(world, up) <= 0.0F);
        }
        return false;
    }
    // endregion
}
