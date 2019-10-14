package cofh.lib.util.helpers;

import cofh.lib.util.RayTracer;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.ArrayList;

import static cofh.lib.capability.CapabilityAreaEffect.AOE_ITEM_CAPABILITY;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.EXCAVATING;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;

public class AreaEffectHelper {

    private AreaEffectHelper() {

    }

    public static boolean validAreaEffectItem(ItemStack stack) {

        return stack.getCapability(AOE_ITEM_CAPABILITY).isPresent() || stack.getItem() instanceof PickaxeItem || stack.getItem() instanceof ShovelItem || stack.getItem() instanceof AxeItem;
    }

    public static ImmutableList<BlockPos> getAreaEffectBlocks(ItemStack stack, BlockPos pos, PlayerEntity player) {

        int encExcavating = getEnchantmentLevel(EXCAVATING, stack);
        if (encExcavating > 0) {
            return getAreaEffectBlocksRadius(stack, pos, player, encExcavating);
        }
        return ImmutableList.of();
    }

    public static ImmutableList<BlockPos> getAreaEffectBlocksRadius(ItemStack stack, BlockPos pos, PlayerEntity player, int radius) {

        ArrayList<BlockPos> area = new ArrayList<>();
        World world = player.getEntityWorld();
        Item tool = stack.getItem();

        BlockRayTraceResult traceResult = RayTracer.retrace(player, RayTraceContext.FluidMode.NONE);
        if (traceResult.getType() == RayTraceResult.Type.MISS || player.isSneaking() || !canToolHarvest(tool, stack, world.getBlockState(pos)) || radius <= 0) {
            return ImmutableList.of();
        }
        BlockPos harvestPos;

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        switch (traceResult.getFace()) {
            case DOWN:
            case UP:
                for (int i = x - radius; i <= x + radius; ++i) {
                    for (int k = z - radius; k <= z + radius; ++k) {
                        if (i == x && k == z) {
                            continue;
                        }
                        harvestPos = new BlockPos(i, y, k);
                        if (canToolHarvest(tool, stack, world.getBlockState(harvestPos))) {
                            area.add(harvestPos);
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
                        harvestPos = new BlockPos(i, j, z);
                        if (canToolHarvest(tool, stack, world.getBlockState(harvestPos))) {
                            area.add(harvestPos);
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
                        harvestPos = new BlockPos(x, j, k);
                        if (canToolHarvest(tool, stack, world.getBlockState(harvestPos))) {
                            area.add(harvestPos);
                        }
                    }
                }
                break;
        }
        return ImmutableList.copyOf(area);
    }

    public static boolean canToolHarvest(Item toolItem, ItemStack toolStack, BlockState state) {

        return toolItem.canHarvestBlock(toolStack, state) || toolItem.getDestroySpeed(toolStack, state) > 1.0F;
    }

    //    public static boolean harvestBlock(World world, BlockPos pos, PlayerEntity player) {
    //
    //        if (world.isAirBlock(pos)) {
    //            return false;
    //        }
    //        ServerPlayerEntity playerMP = null;
    //        if (player instanceof ServerPlayerEntity) {
    //            playerMP = (ServerPlayerEntity) player;
    //        }
    //        BlockState state = world.getBlockState(pos);
    //        Block block = state.getBlock();
    //
    //        if (!effectiveMaterials.contains(state.getMaterial()) || !ForgeHooks.canHarvestBlock(block, player, world, pos)) {
    //            return false;
    //        }
    //        // Send the Break Event
    //        int xpToDrop = 0;
    //        if (playerMP != null) {
    //            xpToDrop = ForgeHooks.onBlockBreakEvent(world, playerMP.interactionManager.getGameType(), playerMP, pos);
    //            if (xpToDrop == -1) {
    //                return false;
    //            }
    //        }
    //        if (Utils.isServerWorld(world)) {
    //            if (block.removedByPlayer(state, world, pos, player, !player.capabilities.isCreativeMode)) {
    //                block.onBlockDestroyedByPlayer(world, pos, state);
    //                if (!player.capabilities.isCreativeMode) {
    //                    block.harvestBlock(world, player, pos, state, world.getTileEntity(pos), player.getHeldItemMainhand());
    //                    if (xpToDrop > 0) {
    //                        block.dropXpOnBlockBreak(world, pos, xpToDrop);
    //                    }
    //                }
    //            }
    //            if (playerMP != null) {
    //                playerMP.connection.sendPacket(new SPacketBlockChange(world, pos));
    //            }
    //        } else {
    //            if (block.removedByPlayer(state, world, pos, player, !player.capabilities.isCreativeMode)) {
    //                block.onBlockDestroyedByPlayer(world, pos, state);
    //            }
    //            NetHandlerPlayClient client = Minecraft.getMinecraft().getConnection();
    //            if (client != null) {
    //                Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, Minecraft.getMinecraft().objectMouseOver.sideHit));
    //            }
    //        }
    //        return true;
    //    }

}
