package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

public class CurseFoolEnchant extends EnchantmentCoFH {

    public CurseFoolEnchant() {

        super(Rarity.VERY_RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
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
        return enable && (item instanceof SwordItem || item instanceof ToolItem || item instanceof BowItem || item instanceof CrossbowItem || item instanceof TridentItem || supportsEnchantment(stack));
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
