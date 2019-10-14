package cofh.core.event;

import cofh.core.init.ConfigCore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static cofh.lib.util.modhelpers.CoreHelper.*;
import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.FEATHER_FALLING;

public class CommonEventsCore {

    private static final CommonEventsCore INSTANCE = new CommonEventsCore();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        registered = true;
    }

    private CommonEventsCore() {

    }

    @SubscribeEvent
    public void handleFarmlandTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {

        if (!ConfigCore.preventFarmlandTrampling) {
            return;
        }
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            int encFeatherFalling = getMaxEnchantmentLevel(FEATHER_FALLING, (LivingEntity) entity);
            if (encFeatherFalling > 0) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void handleEnderTeleportEvent(EnderTeleportEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        if (entity.isPotionActive(ENDERFERENCE)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void handleItemFishedEvent(ItemFishedEvent event) {

        if (!ConfigCore.enableFishingExhaustion) {
            return;
        }
        if (event.isCanceled()) {
            return;
        }
        PlayerEntity player = event.getHookEntity().angler;
        if (player == null || player instanceof FakePlayer) {
            return;
        }
        player.addExhaustion(ConfigCore.amountFishingExhaustion);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void handlePlayerPickupXpEvent(PlayerPickupXpEvent event) {

        PlayerEntity player = event.getPlayer();
        ExperienceOrbEntity orb = event.getOrb();

        EffectInstance eurekaEffect = player.getActivePotionEffect(EUREKA);
        if (eurekaEffect == null || orb.getPersistentData().contains(ID_EUREKA)) {
            return;
        }
        orb.xpValue = orb.xpValue * (120 + 20 * eurekaEffect.getAmplifier()) / 100;
        orb.getPersistentData().putBoolean(ID_EUREKA, true);
    }

}
