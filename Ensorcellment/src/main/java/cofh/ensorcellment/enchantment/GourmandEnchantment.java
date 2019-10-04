package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class GourmandEnchantment extends EnchantmentCoFH {

    public GourmandEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{EquipmentSlotType.HEAD});
        maxLevel = 2;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 5 + (level - 1) * 10;
    }

    @Override
    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        return enable && type != null && type.canEnchantItem(stack.getItem());
    }

}
