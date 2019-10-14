package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
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

import static cofh.lib.util.constants.Constants.ARMOR_SLOTS;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.FIRE_REBUKE;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.FROST_REBUKE;

public class FrostRebukeEnchantment extends EnchantmentCoFH {

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

        return super.canApplyTogether(ench) && ench != FIRE_REBUKE;
    }

    // region HELPERS
    @Override
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {

        Map.Entry<EquipmentSlotType, ItemStack> stack = EnchantmentHelper.func_222189_b(FROST_REBUKE, user);
        onHit(user, attacker, level);
        if (stack != null) {
            (stack.getValue()).damageItem(2, user, (consumer) -> consumer.sendBreakAnimation(stack.getKey()));
        }
    }

    public static void onHit(LivingEntity user, Entity attacker, int level) {

        if (attacker instanceof LivingEntity) {
            ((LivingEntity) attacker).knockBack(user, 0.5F * level, user.posX - attacker.posX, user.posZ - attacker.posZ);
            int i = 60 * level;
            if (attacker.isBurning()) {
                attacker.extinguish();
            }
            ((LivingEntity) attacker).addPotionEffect(new EffectInstance(Effects.SLOWNESS, i, level - 1));
            ((LivingEntity) attacker).addPotionEffect(new EffectInstance(Effects.WEAKNESS, i, level - 1));
            for (int j = 0; j < 3 * level; ++j) {
                ((ServerWorld) attacker.world).spawnParticle(ParticleTypes.ITEM_SNOWBALL, attacker.posX + attacker.world.rand.nextDouble(), attacker.posY + 1.0D + attacker.world.rand.nextDouble(), attacker.posZ + attacker.world.rand.nextDouble(), 3 * level, 0, 0, 0, 0);
            }
        }
    }
    // endregion
}
