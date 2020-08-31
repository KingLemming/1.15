package cofh.ensorcellation.enchantment;

import cofh.core.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class ExpBoostEnchantment extends EnchantmentCoFH {

    public static int experience = 4;

    public ExpBoostEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{EquipmentSlotType.HEAD});
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return enable && (type != null && type.canEnchantItem(stack.getItem()) || supportsEnchantment(stack));
    }

    // region HELPERS
    public static int getExp(int baseExp, int level, Random rand) {

        return baseExp + level + rand.nextInt(1 + level * experience);
    }
    // endregion
}
