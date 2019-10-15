package cofh.lib.event;

import cofh.lib.util.Utils;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashSet;

import static cofh.lib.capability.CapabilityAOE.AOE_ITEM_CAPABILITY;
import static cofh.lib.capability.CapabilityAOE.DEFAULT_AOE_CAPABILITY;
import static cofh.lib.util.helpers.AOEHelper.validAOEBreakItem;

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
        if (!validAOEBreakItem(stack)) {
            return;
        }
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AOE_ITEM_CAPABILITY).orElse(DEFAULT_AOE_CAPABILITY).getAOEBlocks(stack, event.getPos(), player);
        ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
        // TODO: Revisit if performance issues show. This is the most *proper* way to handle this, but is not particularly friendly.
        for (BlockPos pos : areaBlocks) {
            playerMP.interactionManager.tryHarvestBlock(pos);
        }
    }

    // TODO: Handle Right Clicks

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleTickEndEvent(TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            HARVESTING_PLAYERS.clear();
        }
    }

}
