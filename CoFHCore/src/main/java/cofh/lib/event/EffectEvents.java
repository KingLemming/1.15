package cofh.lib.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static cofh.lib.util.references.CoreReferences.*;

public class EffectEvents {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(EffectEvents.class);
        registered = true;
    }

    private EffectEvents() {

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleEnderTeleportEvent(EnderTeleportEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        if (entity.isPotionActive(ENDERFERENCE)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleEntityStruckByLightningEvent(EntityStruckByLightningEvent event) {

        if (event.isCanceled()) {
            return;
        }
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity && ((LivingEntity) entity).isPotionActive(LIGHTNING_RESISTANCE)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleLivingAttackEvent(LivingAttackEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        if (source.isExplosion() && entity.isPotionActive(EXPLOSION_RESISTANCE)) {
            event.setCanceled(true);
        } else if (source.isMagicDamage() && entity.isPotionActive(MAGIC_RESISTANCE)) {
            event.setCanceled(true);
        } else if (source == DamageSource.LIGHTNING_BOLT && entity.isPotionActive(LIGHTNING_RESISTANCE)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handlePlayerPickupXpEvent(PlayerPickupXpEvent event) {

        if (event.isCanceled()) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        ExperienceOrbEntity orb = event.getOrb();

        EffectInstance clarityEffect = player.getActivePotionEffect(CLARITY);
        if (clarityEffect == null || orb.getPersistentData().contains(ID_EFFECT_CLARITY)) {
            return;
        }
        orb.xpValue = getXPValue(orb.xpValue, clarityEffect.getAmplifier());
        orb.getPersistentData().putBoolean(ID_EFFECT_CLARITY, true);
    }

    private static int getXPValue(int baseExp, int amplifier) {

        return baseExp * (100 + clarityMod * (1 + amplifier)) / 100;
    }

    private static int clarityMod = 40;

}
