package cofh.lib.enchantment;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public abstract class DamageEnchantmentCoFH extends EnchantmentCoFH {

    protected DamageEnchantmentCoFH(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {

        super(rarityIn, typeIn, slots);
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && !(ench instanceof DamageEnchantment) && !(ench instanceof DamageEnchantmentCoFH);
    }

    public static float getExtraDamage(int level) {

        return level * 2.5F;
    }

}
