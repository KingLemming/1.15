package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.DamageEnchantmentCoFH;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class CurseMercyEnchantment extends EnchantmentCoFH {


    public CurseMercyEnchantment() {

        super(Rarity.VERY_RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMinEnchantability(int level) {

        return 25;
    }

    @Override
    protected int maxDelegate(int level) {

        return 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && !(ench instanceof DamageEnchantment) && !(ench instanceof DamageEnchantmentCoFH) && !(ench instanceof VorpalEnchantment);
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }

    @Override
    public boolean isCurse() {

        return true;
    }

}
