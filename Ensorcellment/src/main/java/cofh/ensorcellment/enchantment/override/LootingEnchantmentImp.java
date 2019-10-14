package cofh.ensorcellment.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

import static cofh.lib.util.modhelpers.EnsorcellmentHelper.EXCAVATING;

public class LootingEnchantmentImp extends EnchantmentOverride {

    public LootingEnchantmentImp() {

        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15 + (level - 1) * 9;
    }

    @Override
    public int getMaxEnchantability(int level) {

        return super.getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        if (!enable) {
            return stack.canApplyAtEnchantingTable(this);
        }
        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.SILK_TOUCH && ench != EXCAVATING;
    }

}
