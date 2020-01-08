package cofh.lib.event;

import cofh.lib.util.Utils;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashSet;

import static cofh.lib.capability.CapabilityAOE.AOE_ITEM_CAPABILITY;
import static cofh.lib.capability.CapabilityAOE.DEFAULT_AOE_CAPABILITY;
import static cofh.lib.util.helpers.AOEHelper.validAOEMiningItem;
import static cofh.lib.util.references.EnsorcellationReferences.WEEDING;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.item.HoeItem.HOE_LOOKUP;

public class AOEEvents {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(AOEEvents.class);
        registered = true;
    }

    private static final HashSet<PlayerEntity> HARVESTING_PLAYERS = new HashSet<>();
    private static final HashSet<PlayerEntity> TILLING_PLAYERS = new HashSet<>();

    private AOEEvents() {

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void handleBlockBreakEvent(BlockEvent.BreakEvent event) {

        PlayerEntity player = event.getPlayer();
        if (!(player instanceof ServerPlayerEntity) || Utils.isClientWorld(player.world)) {
            return;
        }
        if (HARVESTING_PLAYERS.contains(player)) {
            return;
        }
        HARVESTING_PLAYERS.add(player);
        ItemStack stack = player.getHeldItemMainhand();
        if (!validAOEMiningItem(stack)) {
            return;
        }
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AOE_ITEM_CAPABILITY).orElse(DEFAULT_AOE_CAPABILITY).getAOEBlocks(stack, event.getPos(), player);
        ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
        // TODO: Revisit if performance issues show. This is the most *proper* way to handle this, but is not particularly friendly.
        for (BlockPos pos : areaBlocks) {
            if (stack.isEmpty()) {
                break;
            }
            playerMP.interactionManager.tryHarvestBlock(pos);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void handleUseHoeEvent(UseHoeEvent event) {

        if (event.isCanceled() || event.getResult() == Event.Result.ALLOW) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getContext().getItem();
        BlockPos target = event.getContext().getPos();
        World world = player.world;
        BlockState targetTilled = HOE_LOOKUP.get(world.getBlockState(target).getBlock());
        BlockPos up = target.up();
        boolean weeding = getEnchantmentLevel(WEEDING, stack) > 0;

        if (targetTilled == null || !world.isAirBlock(up) && !weeding) {
            return;
        }
        if (Utils.isClientWorld(world)) {
            player.swingArm(event.getContext().getHand());
            return;
        }
        if (TILLING_PLAYERS.contains(player)) {
            return;
        }
        TILLING_PLAYERS.add(player);
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AOE_ITEM_CAPABILITY).orElse(DEFAULT_AOE_CAPABILITY).getAOEBlocks(stack, target, player);
        for (BlockPos pos : areaBlocks) {
            if (stack.isEmpty()) {
                break;
            }
            BlockState tilled = HOE_LOOKUP.get(world.getBlockState(pos).getBlock());
            if (tilled != null) {
                world.setBlockState(pos, tilled);
                if (weeding) {
                    up = pos.up();
                    if (!world.isAirBlock(up)) {
                        world.destroyBlock(up, !player.abilities.isCreativeMode);
                    }
                }
                stack.damageItem(1, player, (consumer) -> {
                    consumer.sendBreakAnimation(event.getContext().getHand());
                });
            }
        }
        world.setBlockState(target, targetTilled);
        if (weeding) {
            up = target.up();
            if (!world.isAirBlock(up)) {
                world.destroyBlock(up, !player.abilities.isCreativeMode);
            }
        }
        world.playSound(player, target, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        event.setResult(Event.Result.ALLOW);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleTickEndEvent(TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            HARVESTING_PLAYERS.clear();
            TILLING_PLAYERS.clear();
        }
    }

}
