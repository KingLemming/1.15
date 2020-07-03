package cofh.core.event;

import cofh.core.init.CoreAttributes;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

@Mod.EventBusSubscriber(modid = ID_COFH_CORE)
public class AttributeEvents {

    private AttributeEvents() {

    }

    @SubscribeEvent
    public static void handleEntityConstructingEvent(EntityEvent.EntityConstructing event) {

        if (event.getEntity() instanceof LivingEntity) {
            ((LivingEntity) event.getEntity()).getAttributes().registerAttribute(CoreAttributes.FALL_DISTANCE);
            ((LivingEntity) event.getEntity()).getAttributes().registerAttribute(CoreAttributes.HAZARD_RESISTANCE);
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

        double hazRes = entity.getAttribute(CoreAttributes.HAZARD_RESISTANCE).getValue();
        if (hazRes > 0.0D) {
            if (source.isFireDamage()) {
                if (entity.getRNG().nextDouble() < hazRes) {
                    entity.extinguish();
                    event.setCanceled(true);
                }
            }
        }
        double stingRes = entity.getAttribute(CoreAttributes.STING_RESISTANCE).getValue();
        if (stingRes > 0.0D) {
            if (attacker instanceof BeeEntity || source == DamageSource.CACTUS || source == DamageSource.SWEET_BERRY_BUSH) {
                if (entity.getRNG().nextDouble() < stingRes) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleLivingFallEvent(LivingFallEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();

        double fallMod = entity.getAttribute(CoreAttributes.FALL_DISTANCE).getValue();
        if (fallMod != 0.0D) {
            event.setDistance(Math.max(0, event.getDistance() - (float) fallMod));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handlePotionApplicableEvent(PotionEvent.PotionApplicableEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        EffectInstance effect = event.getPotionEffect();

        double hazRes = entity.getAttribute(CoreAttributes.HAZARD_RESISTANCE).getValue();
        if (hazRes > 0.0D) {
            if (HAZARD_EFFECTS.contains(effect.getPotion())) {
                if (entity.getRNG().nextDouble() < hazRes) {
                    event.setResult(Event.Result.DENY);
                    event.setCanceled(true);
                }
            }
        }
    }

    // TODO: Revisit
    //    @SubscribeEvent(priority = EventPriority.HIGH)
    //    public static void handlePotionAddedEvent(PotionEvent.PotionAddedEvent event) {
    //
    //        if (event.isCanceled()) {
    //            return;
    //        }
    //        LivingEntity entity = event.getEntityLiving();
    //        EffectInstance effect = event.getPotionEffect();
    //
    //        double hazRes = entity.getAttribute(CoreAttributes.HAZARD_RESISTANCE).getValue();
    //        if (hazRes > 0.0D) {
    //            if (HAZARD_EFFECTS.contains(effect.getPotion())) {
    //                if (entity.getRNG().nextDouble() < hazRes) {
    //                }
    //            }
    //        }
    //    }

    private static final Set<Effect> HAZARD_EFFECTS = new ObjectOpenHashSet<>();

    static {
        HAZARD_EFFECTS.add(Effects.POISON);
        HAZARD_EFFECTS.add(Effects.WITHER);
    }

}
