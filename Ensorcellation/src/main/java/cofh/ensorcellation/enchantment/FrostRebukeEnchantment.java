package cofh.ensorcellation.enchantment;

import cofh.core.enchantment.EnchantmentCoFH;
import cofh.core.util.Utils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.server.ServerWorld;

import java.util.Map;
import java.util.Random;

import static cofh.core.util.constants.Constants.ARMOR_SLOTS;
import static cofh.core.util.references.CoreReferences.CHILLED;
import static cofh.core.util.references.EnsorcReferences.*;
import static net.minecraft.enchantment.Enchantments.THORNS;

public class FrostRebukeEnchantment extends EnchantmentCoFH {

    public static int chance = 20;
    public static boolean mobsAffectPlayers = false;

    public FrostRebukeEnchantment() {

        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_CHEST, ARMOR_SLOTS);
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 5 + 15 * (level - 1);
    }

    @Override
    protected int maxDelegate(int level) {

        return super.getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

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
        Map.Entry<EquipmentSlotType, ItemStack> stack = EnchantmentHelper.getRandomItemWithEnchantment(FROST_REBUKE, user);
        if (shouldHit(level, user.getRNG())) {
            onHit(user, attacker, level);
            if (stack != null) {
                (stack.getValue()).damageItem(2, user, (entity) -> entity.sendBreakAnimation(stack.getKey()));
            }
        }
    }

    public static void onHit(LivingEntity user, Entity attacker, int level) {

        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        if (user instanceof PlayerEntity || !(attacker instanceof PlayerEntity) || mobsAffectPlayers) {
            ((LivingEntity) attacker).knockBack(user, 0.5F * level, user.getPosX() - attacker.getPosX(), user.getPosZ() - attacker.getPosZ());
        }
        Random rand = user.getRNG();
        int i = 20 + 20 * rand.nextInt(3 * level);
        if (attacker.isBurning()) {
            attacker.extinguish();
        }
        ((LivingEntity) attacker).addPotionEffect(new EffectInstance(CHILLED, i, level - 1, false, false));
        if (attacker.world instanceof ServerWorld) {
            for (int j = 0; j < 3 * level; ++j) {
                Utils.spawnParticles(attacker.world, ParticleTypes.ITEM_SNOWBALL, attacker.getPosX() + rand.nextDouble(), attacker.getPosY() + 1.0D + rand.nextDouble(), attacker.getPosZ() + rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }

    public static boolean shouldHit(int level, Random rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion
}
