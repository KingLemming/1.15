package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.server.ServerWorld;

public class FrostAspectEnchantment extends EnchantmentCoFH {

    public FrostAspectEnchantment() {

        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 2;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxEnchantability(int level) {

        return super.getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.FIRE_ASPECT;
    }

    // region HELPERS
    public static void onHit(LivingEntity entity, int level) {

        int i = 80 * level;
        if (entity.isBurning()) {
            entity.extinguish();
        }
        entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, i, level - 1));
        entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, i, level - 1));
        for (int j = 0; j < 4 * level; ++j) {
            ((ServerWorld) entity.world).spawnParticle(ParticleTypes.ITEM_SNOWBALL, entity.posX + entity.world.rand.nextDouble(), entity.posY + 1.0D + entity.world.rand.nextDouble(), entity.posZ + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
        }
    }
    // endregion
}
