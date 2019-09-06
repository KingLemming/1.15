package cofh.ensorcellment.enchantment.override;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;
import static cofh.lib.util.constants.Constants.MAX_ENCHANT_LEVEL;

public class ProtectionEnchantmentImp extends EnchantmentCoFH {

    public static final int HORSE_MODIFIER = 3;

    private ProtectionEnchantment.Type protectionType;
    private String name;

    public ProtectionEnchantmentImp(String id, Enchantment.Rarity rarityIn, ProtectionEnchantment.Type protectionTypeIn, EquipmentSlotType[] slots, String name) {

        super(id, rarityIn, EnchantmentType.ARMOR, slots);
        this.protectionType = protectionTypeIn;

        if (protectionTypeIn == ProtectionEnchantment.Type.FALL) {
            this.type = EnchantmentType.ARMOR_FEET;
        }
        this.name = name;
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

        return 1 + (level - 1) * 5;
    }

    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

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

    // region IDynamicConfig
    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Override");
        COMMON_CONFIG.push(name);

        String comment = "If TRUE, the " + name + " Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        cfgLevel = COMMON_CONFIG.comment(comment).defineInRange("Max Level", maxLevel, 1, MAX_ENCHANT_LEVEL);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
        maxLevel = cfgLevel.get();
    }
    // endregion
}
