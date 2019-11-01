package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.server.ServerWorld;

import java.util.Map;
import java.util.Random;

import static cofh.lib.util.constants.Constants.ARMOR_SLOTS;
import static cofh.lib.util.references.EnsorcellationReferences.*;
import static net.minecraft.enchantment.Enchantments.THORNS;

public class FrostRebukeEnchantment extends EnchantmentCoFH {

    public static int chance = 20;

    public FrostRebukeEnchantment() {

        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_CHEST, ARMOR_SLOTS);
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 5 + 15 * (level - 1);
    }

    @Override
    public int getMaxEnchantability(int level) {

        return super.getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof ArmorItem || item instanceof HorseArmorItem || item.isShield(stack, null) || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != THORNS && ench != DISPLACEMENT && ench != FIRE_REBUKE;
    }

    // region HELPERS
    @Override
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {

        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        Map.Entry<EquipmentSlotType, ItemStack> stack = EnchantmentHelper.func_222189_b(FROST_REBUKE, user);
        if (shouldHit(level, user.getRNG())) {
            onHit(user, attacker, level);
            if (stack != null) {
                (stack.getValue()).damageItem(2, user, (consumer) -> consumer.sendBreakAnimation(stack.getKey()));
            }
        }
    }

    public static void onHit(LivingEntity user, Entity attacker, int level) {

        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        Random rand = user.getRNG();
        ((LivingEntity) attacker).knockBack(user, 0.5F * level, user.posX - attacker.posX, user.posZ - attacker.posZ);
        int i = 20 + 20 * rand.nextInt(3 * level);
        if (attacker.isBurning()) {
            attacker.extinguish();
        }
        ((LivingEntity) attacker).addPotionEffect(new EffectInstance(Effects.SLOWNESS, i, level - 1));
        ((LivingEntity) attacker).addPotionEffect(new EffectInstance(Effects.WEAKNESS, i, level - 1));
        if (attacker.world instanceof ServerWorld) {
            for (int j = 0; j < 3 * level; ++j) {
                Utils.spawnParticles(attacker.world, ParticleTypes.ITEM_SNOWBALL, attacker.posX + rand.nextDouble(), attacker.posY + 1.0D + rand.nextDouble(), attacker.posZ + rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }

    public static boolean shouldHit(int level, Random rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion
}
