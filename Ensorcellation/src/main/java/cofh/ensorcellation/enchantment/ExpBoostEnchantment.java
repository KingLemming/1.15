package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

import java.util.Random;

public class ExpBoostEnchantment extends EnchantmentCoFH {

    public static int experience = 4;

    public ExpBoostEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.DIGGER, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
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

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof ToolItem || item instanceof BowItem || item instanceof FishingRodItem || item instanceof CrossbowItem || item instanceof TridentItem || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.SILK_TOUCH;
    }

    // region HELPERS
    public static int getExp(int baseExp, int level, Random rand) {

        return baseExp + level + rand.nextInt(1 + level * experience);
    }
    // endregion
}
