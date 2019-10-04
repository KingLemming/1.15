package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class HoldingEnchantment extends EnchantmentCoFH {

    public HoldingEnchantment() {

        super(Rarity.COMMON, EnchantmentType.ALL, EquipmentSlotType.values());
        maxLevel = 4;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 1 + (level - 1) * 5;
    }

    @Override
    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        return enable && supportsEnchantment(stack);
    }

}
