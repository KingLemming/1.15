package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class SoulboundEnchantment extends EnchantmentCoFH {

    public static boolean permanent = true;

    public SoulboundEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.ALL, EquipmentSlotType.values());
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 1 + (level - 1) * 5;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public int getMaxLevel() {

        return permanent ? 1 : maxLevel;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        return enable;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.VANISHING_CURSE;
    }

}
