package cofh.lib.event;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
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

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void handlePlayerPickupXpEvent(PlayerPickupXpEvent event) {

        PlayerEntity player = event.getPlayer();
        ExperienceOrbEntity orb = event.getOrb();

        EffectInstance eurekaEffect = player.getActivePotionEffect(EPIPHANY);
        if (eurekaEffect == null || orb.getPersistentData().contains(ID_EPIPHANY)) {
            return;
        }
        orb.xpValue = orb.xpValue * (120 + 20 * eurekaEffect.getAmplifier()) / 100;
        orb.getPersistentData().putBoolean(ID_EPIPHANY, true);
    }

}
