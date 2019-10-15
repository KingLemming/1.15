package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;

import static cofh.lib.util.references.CoreReferences.ENDERFERENCE;

public class DamageEnderEnchantment extends DamageEnchantmentCoFH {

    public DamageEnderEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 5;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + (level - 1) * 8;
    }

    @Override
    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 20;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    @Override
    public void onEntityDamaged(LivingEntity user, Entity target, int level) {

        // Should never actually happen.
        if (ENDERFERENCE == null) {
            return;
        }
        if (target instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) target;
            int i = 100 + user.getRNG().nextInt(40 * level);
            living.addPotionEffect(new EffectInstance(ENDERFERENCE, i));
        }
    }

    public static boolean validTarget(Entity entity) {

        return entity instanceof EndermanEntity || entity instanceof EndermiteEntity || entity instanceof EnderDragonEntity || entity instanceof ShulkerEntity;
    }

}
