package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ProtectionEnchantmentImp extends EnchantmentOverride {

    private final Type protectionType;

    public ProtectionEnchantmentImp(Rarity rarityIn, Type protectionTypeIn, EquipmentSlotType[] slots) {

        super(rarityIn, EnchantmentType.ARMOR, slots);
        this.protectionType = protectionTypeIn;

        if (protectionTypeIn == Type.FALL) {
            this.type = EnchantmentType.ARMOR_FEET;
        }
        maxLevel = 4;
    }

    @Override
    public int calcModifierDamage(int level, DamageSource source) {

        if (level <= 0 || source.canHarmInCreative()) {
            return 0;
        } else if (this.protectionType == Type.ALL) {
            return level;
        } else if (this.protectionType == Type.FALL && source == DamageSource.FALL) {
            return level * 3;
        } else if (this.protectionType == Type.FIRE && source.isFireDamage()) {
            return level * 2;
        } else if (this.protectionType == Type.EXPLOSION && source.isExplosion()) {
            return level * 2;
        } else if (this.protectionType == Type.MAGIC && source.isMagicDamage()) {
            return level * 2;
        } else {
            return this.protectionType == Type.PROJECTILE && source.isProjectile() ? level * 2 : 0;
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
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!enable) {
            return super.canApplyAtEnchantingTable(stack);
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
                return this.protectionType == Type.FALL || enchProtection.protectionType == Type.FALL;
            }
        } else {
            return super.canApplyTogether(ench);
        }
    }

    @Override
    public boolean isAllowedOnBooks() {

        return this.protectionType == Type.MAGIC ? enable && allowOnBooks : allowOnBooks;
    }

    // region TYPE
    public enum Type {
        ALL("all", 1, 11),
        FALL("fall", 5, 6),
        FIRE("fire", 10, 8),
        EXPLOSION("explosion", 5, 8),
        MAGIC("magic", 10, 8),
        PROJECTILE("projectile", 3, 6);

        private final String typeName;
        private final int minEnchantability;
        private final int levelCost;

        Type(String typeName, int minEnchantability, int levelCost) {

            this.typeName = typeName;
            this.minEnchantability = minEnchantability;
            this.levelCost = levelCost;
        }

        public int getMinimalEnchantability() {

            return this.minEnchantability;
        }

        public int getEnchantIncreasePerLevel() {

            return this.levelCost;
        }
    }
    // endregion
}
