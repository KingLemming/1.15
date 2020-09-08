package cofh.core.event;

import cofh.core.init.CoreAttributes;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
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

import static cofh.core.util.constants.Constants.ID_COFH_CORE;
import static cofh.core.util.references.CoreReferences.CHILLED;
import static cofh.core.util.references.CoreReferences.SHOCKED;
import static net.minecraft.potion.Effects.POISON;
import static net.minecraft.potion.Effects.WITHER;

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
        float amount = event.getAmount();

        double hazRes = entity.getAttribute(CoreAttributes.HAZARD_RESISTANCE).getValue();
        if (hazRes > 0.0D) {
            if (source.isFireDamage() || HAZARD_DAMAGE_TYPES.contains(source.getDamageType())) {
                if (entity.getRNG().nextDouble() < hazRes) {
                    entity.extinguish();
                    attemptDamageArmor(entity, amount);
                    event.setCanceled(true);
                }
            }
        }
        double stingRes = entity.getAttribute(CoreAttributes.STING_RESISTANCE).getValue();
        if (stingRes > 0.0D) {
            if (STING_DAMAGE_TYPES.contains(source.getDamageType())) {
                if (entity.getRNG().nextDouble() < stingRes) {
                    attemptDamageArmor(entity, amount);
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
                    attemptDamageArmor(entity, (1 + effect.getAmplifier()) * effect.getDuration() / 40F);
                    event.setResult(Event.Result.DENY);
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

    public static void attemptDamageArmor(Entity entity, float amount) {

        if (entity instanceof PlayerEntity) {
            if (100 * entity.world.rand.nextFloat() < amount) {
                ((PlayerEntity) entity).inventory.damageArmor(Math.min(20.0F, amount));
            }
        }
    }

    private static final Set<String> STING_DAMAGE_TYPES = new ObjectOpenHashSet<>();

    private static final Set<String> HAZARD_DAMAGE_TYPES = new ObjectOpenHashSet<>();
    private static final Set<Effect> HAZARD_EFFECTS = new ObjectOpenHashSet<>();

    public static void setup() {

        STING_DAMAGE_TYPES.add("sting");
        STING_DAMAGE_TYPES.add("cactus");
        STING_DAMAGE_TYPES.add("sweetBerryBush");

        HAZARD_DAMAGE_TYPES.add("lightningBolt");
        HAZARD_DAMAGE_TYPES.add("ice");
        HAZARD_DAMAGE_TYPES.add("lightning");

        HAZARD_EFFECTS.add(POISON);
        HAZARD_EFFECTS.add(WITHER);

        HAZARD_EFFECTS.add(CHILLED);
        HAZARD_EFFECTS.add(SHOCKED);
    }

}
