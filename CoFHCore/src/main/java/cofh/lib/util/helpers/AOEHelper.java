package cofh.lib.util.helpers;

import cofh.lib.util.RayTracer;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
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
import static cofh.lib.util.references.EnsorcellmentReferences.EXCAVATING;
import static cofh.lib.util.references.EnsorcellmentReferences.TILLING;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;

public class AOEHelper {

    private AOEHelper() {

    }

    public static boolean validAOEItem(ItemStack stack) {

        return stack.getCapability(AOE_ITEM_CAPABILITY).isPresent() || stack.getItem() instanceof ToolItem || stack.getItem() instanceof HoeItem;
    }

    public static boolean validAOEBreakItem(ItemStack stack) {

        return stack.getCapability(AOE_ITEM_CAPABILITY).isPresent() || stack.getItem() instanceof ToolItem;
    }

    public static ImmutableList<BlockPos> getAOEBlocks(ItemStack stack, BlockPos pos, PlayerEntity player) {

        int encExcavating = getEnchantmentLevel(EXCAVATING, stack);
        if (encExcavating > 0) {
            return getAOEBlocksRadius(stack, pos, player, encExcavating);
        }
        int encTilling = getEnchantmentLevel(TILLING, stack);
        if (encTilling > 0) {
            return getAOEBlocksLine(stack, pos, player, 1 + encTilling * 2);
        }
        return ImmutableList.of();
    }

    public static ImmutableList<BlockPos> getAOEBlocksRadius(ItemStack stack, BlockPos pos, PlayerEntity player, int radius) {

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

    public static ImmutableList<BlockPos> getAOEBlocksLine(ItemStack stack, BlockPos pos, PlayerEntity player, int length) {

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
                for (int k = z; k < z + length; ++k) {
                    query = new BlockPos(x, y, k);
                    if (!canToolAffect(tool, stack, world, query)) {
                        break;
                    }
                    area.add(query);
                }
                break;
            case WEST:
                for (int i = x; i > x - length; i--) {
                    query = new BlockPos(i, y, z);
                    if (!canToolAffect(tool, stack, world, query)) {
                        break;
                    }
                    area.add(query);
                }
                break;
            case NORTH:
                for (int k = z; k > z - length; k--) {
                    query = new BlockPos(x, y, k);
                    if (!canToolAffect(tool, stack, world, query)) {
                        break;
                    }
                    area.add(query);
                }
                break;
            case EAST:
                for (int i = x; i < x + length; ++i) {
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

    // region HELPERS
    private static boolean canToolAffect(Item toolItem, ItemStack toolStack, World world, BlockPos pos) {

        BlockState state = world.getBlockState(pos);
        if (toolItem instanceof HoeItem) {
            return HoeItem.HOE_LOOKUP.containsKey(state.getBlock()) && world.isAirBlock(pos.up());
        }
        return toolItem.canHarvestBlock(toolStack, state) || toolItem.getDestroySpeed(toolStack, state) > 1.0F;
    }
    // endregion
}
