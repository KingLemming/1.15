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

import static cofh.lib.capability.CapabilityAreaEffect.AOE_ITEM_CAPABILITY;
import static cofh.lib.capability.CapabilityAreaEffect.DEFAULT_AOE_CAPABILITY;
import static cofh.lib.util.helpers.AreaEffectHelper.validAreaEffectItem;

public class AreaEffectEvents {

    private static final AreaEffectEvents INSTANCE = new AreaEffectEvents();
    private static boolean registered = false;

    private static HashSet<PlayerEntity> HARVESTING_PLAYERS = new HashSet<>();

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        registered = true;
    }

    private AreaEffectEvents() {

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void handleBlockBreakEvent(BlockEvent.BreakEvent event) {

        PlayerEntity player = event.getPlayer();
        if (!(player instanceof ServerPlayerEntity)) {
            return;
        }
        if (HARVESTING_PLAYERS.contains(player)) {
            return;
        }
        HARVESTING_PLAYERS.add(player);
        ItemStack stack = player.getHeldItemMainhand();
        if (!validAreaEffectItem(stack) || Utils.isClientWorld(player.world)) {
            return;
        }
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AOE_ITEM_CAPABILITY).orElse(DEFAULT_AOE_CAPABILITY).getAreaEffectBlocks(stack, event.getPos(), player);
        ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
        // TODO: Revisit if performance issues show. This is the most *proper* way to handle this, but is not particularly friendly.
        for (BlockPos pos : areaBlocks) {
            playerMP.interactionManager.tryHarvestBlock(pos);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void handleTickEndEvent(TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            HARVESTING_PLAYERS.clear();
        }
    }

}
