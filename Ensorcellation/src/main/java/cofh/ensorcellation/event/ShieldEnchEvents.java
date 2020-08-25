package cofh.ensorcellation.event;

import cofh.ensorcellation.enchantment.DisplacementEnchantment;
import cofh.ensorcellation.enchantment.FireRebukeEnchantment;
import cofh.ensorcellation.enchantment.FrostRebukeEnchantment;
import cofh.ensorcellation.enchantment.PhalanxEnchantment;
import cofh.ensorcellation.enchantment.override.ThornsEnchantmentImp;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.constants.Constants.*;
import static cofh.lib.util.references.EnsorcIDs.ID_BULWARK;
import static cofh.lib.util.references.EnsorcIDs.ID_PHALANX;
import static cofh.lib.util.references.EnsorcReferences.*;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.THORNS;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.ADDITION;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_TOTAL;

@Mod.EventBusSubscriber(modid = ID_ENSORCELLATION)
public class ShieldEnchEvents {

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

        if (canBlockDamageSource(entity, source) && attacker != null) {
            // THORNS
            int encThorns = getEnchantmentLevel(THORNS, stack);
            if (ThornsEnchantmentImp.shouldHit(encThorns, entity.getRNG())) {
                attacker.attackEntityFrom(DamageSource.causeThornsDamage(entity), ThornsEnchantment.getDamage(encThorns, entity.getRNG()));
            }
            // DISPLACEMENT
            int encDisplacement = getEnchantmentLevel(DISPLACEMENT, stack);
            if (DisplacementEnchantment.shouldHit(encDisplacement, entity.getRNG())) {
                DisplacementEnchantment.onHit(entity, attacker, encDisplacement);
            }
            // FIRE REBUKE
            int encFireRebuke = getEnchantmentLevel(FIRE_REBUKE, stack);
            if (FireRebukeEnchantment.shouldHit(encFireRebuke, entity.getRNG())) {
                FireRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
            }
            // FROST REBUKE
            int encFrostRebuke = getEnchantmentLevel(FROST_REBUKE, stack);
            if (FrostRebukeEnchantment.shouldHit(encFrostRebuke, entity.getRNG())) {
                FrostRebukeEnchantment.onHit(entity, attacker, encFrostRebuke);
            }
            // BULWARK
            int encBulwark = getEnchantmentLevel(BULWARK, stack);
            if (encBulwark > 0 && attacker instanceof PlayerEntity) {
                PlayerEntity playerAttacker = (PlayerEntity) attacker;
                if (playerAttacker.getRNG().nextFloat() < 0.5F) {
                    playerAttacker.getCooldownTracker().setCooldown(playerAttacker.getHeldItemMainhand().getItem(), 100);
                    attacker.world.setEntityState(attacker, (byte) 30);
                }
            }
        }
    }

    @SubscribeEvent
    public static void handleLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        ItemStack stack = entity.getActiveItemStack();

        if (stack.getItem().isShield(stack, entity)) {
            Multimap<String, AttributeModifier> attributes = HashMultimap.create();
            int encBulwark = getEnchantmentLevel(BULWARK, stack);
            int encPhalanx = getEnchantmentLevel(PHALANX, stack);
            // BULWARK
            if (encBulwark > 0) {
                attributes.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(UUID_ENCH_BULWARK_KNOCKBACK_RESISTANCE, ID_BULWARK, 1.0D, ADDITION).setSaved(false));
            }
            // PHALANX
            if (encPhalanx > 0) {
                attributes.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID_ENCH_PHALANX_MOVEMENT_SPEED, ID_PHALANX, PhalanxEnchantment.SPEED * encPhalanx, MULTIPLY_TOTAL).setSaved(false));
            }
            if (!attributes.isEmpty()) {
                entity.getAttributes().applyAttributeModifiers(attributes);
            }
        } else {
            entity.getAttributes().removeAttributeModifiers(SHIELD_ATTRIBUTES);
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
                Vec3d vec3d1 = vec3d2.subtractReverse(new Vec3d(living.getPosX(), living.getPosY(), living.getPosZ())).normalize();
                vec3d1 = new Vec3d(vec3d1.x, 0.0D, vec3d1.z);
                return vec3d1.dotProduct(vec3d) < 0.0D;
            }
        }
        return false;
    }

    private static final Multimap<String, AttributeModifier> SHIELD_ATTRIBUTES = HashMultimap.create();

    static {
        SHIELD_ATTRIBUTES.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(UUID_ENCH_BULWARK_KNOCKBACK_RESISTANCE, ID_BULWARK, 1.0D, ADDITION).setSaved(false));
        SHIELD_ATTRIBUTES.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID_ENCH_PHALANX_MOVEMENT_SPEED, ID_PHALANX, PhalanxEnchantment.SPEED, MULTIPLY_TOTAL).setSaved(false));
    }
    // endregion
}