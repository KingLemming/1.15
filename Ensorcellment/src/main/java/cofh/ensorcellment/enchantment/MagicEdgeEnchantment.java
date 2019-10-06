package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class MagicEdgeEnchantment extends DamageEnchantmentCoFH {

    public MagicEdgeEnchantment() {

        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }

    public static float getExtraDamage(int level) {

        return level * 0.5F;
    }

}
