package cofh.ensorcellation.event;

import cofh.ensorcellation.enchantment.DisplacementEnchantment;
import cofh.ensorcellation.enchantment.FireRebukeEnchantment;
import cofh.ensorcellation.enchantment.FrostRebukeEnchantment;
import cofh.ensorcellation.enchantment.PhalanxEnchantment;
import cofh.ensorcellation.enchantment.override.ThornsEnchantmentImp;
import cofh.lib.util.helpers.MathHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static cofh.lib.util.constants.Constants.UUID_KNOCKBACK_RESISTANCE;
import static cofh.lib.util.constants.Constants.UUID_MOVEMENT_SPEED;
import static cofh.lib.util.references.CoreReferences.ENDERFERENCE;
import static cofh.lib.util.references.EnsorcellationReferences.*;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.THORNS;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.ADDITION;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_TOTAL;

public class ShieldEnchEvents {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(ShieldEnchEvents.class);
        registered = true;
    }

    private ShieldEnchEvents() {

    }

    @SubscribeEvent
    public static void handleLivingAttackEvent(LivingAttackEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity attacker = source.getTrueSource();

        ItemStack stack = entity.getActiveItemStack();

        if (canBlockDamageSource(entity, source)) {
            // DISPLACEMENT
            int encDisplacement = getEnchantmentLevel(DISPLACEMENT, stack);
            if (DisplacementEnchantment.shouldHit(encDisplacement, MathHelper.RANDOM) && attacker != null) {
                DisplacementEnchantment.teleportEntity(encDisplacement, MathHelper.RANDOM, attacker);
                event.setCanceled(true);
                return;
            }
            // THORNS
            int encThorns = getEnchantmentLevel(THORNS, stack);
            if (ThornsEnchantmentImp.shouldHit(encThorns, MathHelper.RANDOM) && attacker != null) {
                attacker.attackEntityFrom(DamageSource.causeThornsDamage(entity), ThornsEnchantment.getDamage(encThorns, MathHelper.RANDOM));
            }
            // FIRE REBUKE
            int encFireRebuke = getEnchantmentLevel(FIRE_REBUKE, stack);
            if (encFireRebuke > 0) {
                FireRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
            }
            // FROST REBUKE
            int encFrostRebuke = getEnchantmentLevel(FROST_REBUKE, stack);
            if (encFrostRebuke > 0) {
                FrostRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
            }
        }
    }

    @SubscribeEvent
    public static void handleLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();

        ItemStack shield = entity.getActiveItemStack();
        if (shield.getItem().isShield(shield, entity)) {
            entity.addPotionEffect(new EffectInstance(ENDERFERENCE, 6000));

            int encBulwark = getEnchantmentLevel(BULWARK, shield);
            int encPhalanx = getEnchantmentLevel(PHALANX, shield);

            Multimap<String, AttributeModifier> attributes = HashMultimap.create();
            if (encBulwark > 0) {
                attributes.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(UUID_KNOCKBACK_RESISTANCE, ID_BULWARK, 1.0D, ADDITION).setSaved(false));
            }
            if (encPhalanx > 0) {
                attributes.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID_MOVEMENT_SPEED, ID_PHALANX, PhalanxEnchantment.SPEED * encPhalanx, MULTIPLY_TOTAL).setSaved(false));
            }
            if (!attributes.isEmpty()) {
                entity.getAttributes().applyAttributeModifiers(attributes);
            }
        } else {
            Multimap<String, AttributeModifier> attributes = HashMultimap.create();
            attributes.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(UUID_KNOCKBACK_RESISTANCE, ID_BULWARK, 1.0D, ADDITION).setSaved(false));
            attributes.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID_MOVEMENT_SPEED, ID_PHALANX, PhalanxEnchantment.SPEED, MULTIPLY_TOTAL).setSaved(false));
            entity.getAttributes().removeAttributeModifiers(attributes);
        }
    }

    // region HELPERS
    private static boolean canBlockDamageSource(LivingEntity living, DamageSource source) {

        Entity entity = source.getImmediateSource();
        if (entity instanceof AbstractArrowEntity) {
            AbstractArrowEntity arrow = (AbstractArrowEntity) entity;
            if (arrow.getPierceLevel() > 0) {
                return false;
            }
        }
        if (!source.isUnblockable() && living.isActiveItemStackBlocking()) {
            Vec3d vec3d2 = source.getDamageLocation();
            if (vec3d2 != null) {
                Vec3d vec3d = living.getLook(1.0F);
                Vec3d vec3d1 = vec3d2.subtractReverse(new Vec3d(living.posX, living.posY, living.posZ)).normalize();
                vec3d1 = new Vec3d(vec3d1.x, 0.0D, vec3d1.z);
                return vec3d1.dotProduct(vec3d) < 0.0D;
            }
        }
        return false;
    }
    // endregion
}