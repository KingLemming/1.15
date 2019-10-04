package cofh.lib.enchantment;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public abstract class EnchantmentOverride extends EnchantmentCoFH {

    protected EnchantmentOverride(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {

        super(rarityIn, typeIn, slots);
    }

    @Override
    public boolean isAllowedOnBooks() {

        return allowOnBooks;
    }

}
