package cofh.ensorcellment.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ProtectionEnchantmentImp extends EnchantmentOverride {

    public static final int HORSE_MODIFIER = 3;

    private ProtectionEnchantment.Type protectionType;

    public ProtectionEnchantmentImp(Rarity rarityIn, ProtectionEnchantment.Type protectionTypeIn, EquipmentSlotType[] slots) {

        super(rarityIn, EnchantmentType.ARMOR, slots);
        this.protectionType = protectionTypeIn;

        if (protectionTypeIn == ProtectionEnchantment.Type.FALL) {
            this.type = EnchantmentType.ARMOR_FEET;
        }
        maxLevel = 4;
    }

    @Override
    public int calcModifierDamage(int level, DamageSource source) {

        if (level <= 0 || source.canHarmInCreative()) {
            return 0;
        } else if (this.protectionType == ProtectionEnchantment.Type.ALL) {
            return level;
        } else if (this.protectionType == ProtectionEnchantment.Type.FIRE && source.isFireDamage()) {
            return level * 2;
        } else if (this.protectionType == ProtectionEnchantment.Type.FALL && source == DamageSource.FALL) {
            return level * 3;
        } else if (this.protectionType == ProtectionEnchantment.Type.EXPLOSION && source.isExplosion()) {
            return level * 2;
        } else {
            return this.protectionType == ProtectionEnchantment.Type.PROJECTILE && source.isProjectile() ? level * 2 : 0;
        }
    }

    @Override
    public int getMinEnchantability(int level) {

        return protectionType.getMinimalEnchantability() + (level - 1) * protectionType.getEnchantIncreasePerLevel();
    }

    @Override
    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + protectionType.getEnchantIncreasePerLevel();
    }

    @Override
    public boolean canApply(ItemStack stack) {

        if (!enable) {
            return stack.canApplyAtEnchantingTable(this);
        }
        Item item = stack.getItem();
        return enable && (type != null && type.canEnchantItem(item) || item instanceof HorseArmorItem || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        if (ench instanceof ProtectionEnchantmentImp) {
            ProtectionEnchantmentImp enchProtection = (ProtectionEnchantmentImp) ench;
            if (this.protectionType == enchProtection.protectionType) {
                return false;
            } else {
                return this.protectionType == ProtectionEnchantment.Type.FALL || enchProtection.protectionType == ProtectionEnchantment.Type.FALL;
            }
        } else {
            return super.canApplyTogether(ench);
        }
    }

}
