package cofh.ensorcellment.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import cofh.lib.util.modhelpers.EnsorcellmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FrostWalkerEnchantmentImp extends EnchantmentOverride {

    public FrostWalkerEnchantmentImp() {

        super(Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{EquipmentSlotType.FEET});
        maxLevel = 2;
    }

    @Override
    public int getMinEnchantability(int level) {

        return level * 10;
    }

    @Override
    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 15;
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

        return super.canApplyTogether(ench) && ench != Enchantments.DEPTH_STRIDER && ench != EnsorcellmentHelper.MAGMA_WALKER;
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }

}