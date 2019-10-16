package cofh.ensorcellation.enchantment.nyi;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PilferingEnchantment extends EnchantmentCoFH {

    public static int chance = 50;

    public PilferingEnchantment() {

        super(Rarity.RARE, EnchantmentType.FISHING_ROD, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15;
    }

    @Override
    protected int maxDelegate(int level) {

        return this.getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof FishingRodItem || supportsEnchantment(stack));
    }

}
