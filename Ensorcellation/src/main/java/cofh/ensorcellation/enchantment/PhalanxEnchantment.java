package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class PhalanxEnchantment extends EnchantmentCoFH {

    public static final double SPEED = 1.25D;

    public PhalanxEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.BREAKABLE, new EquipmentSlotType[]{EquipmentSlotType.OFFHAND});
        maxLevel = 2;
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
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return enable && (stack.getItem().isShield(stack, null) || supportsEnchantment(stack));
    }

}
