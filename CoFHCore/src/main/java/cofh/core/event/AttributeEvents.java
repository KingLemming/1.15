package cofh.core.event;

import cofh.core.init.CoreAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AttributeEvents {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(AttributeEvents.class);
        registered = true;
    }

    private AttributeEvents() {

    }

    @SubscribeEvent
    public static void handleEntityConstructingEvent(EntityEvent.EntityConstructing event) {

        if (event.getEntity() instanceof LivingEntity) {
            ((LivingEntity) event.getEntity()).getAttributes().registerAttribute(CoreAttributes.STING_RESISTANCE);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleLivingAttackEvent(LivingAttackEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity attacker = source.getTrueSource();

        if (attacker instanceof BeeEntity) {
            double resistance = entity.getAttribute(CoreAttributes.STING_RESISTANCE).getValue();
            if (resistance > 0.0D && entity.getRNG().nextDouble() < resistance) {
                event.setCanceled(true);
            }
        }
    }

}
