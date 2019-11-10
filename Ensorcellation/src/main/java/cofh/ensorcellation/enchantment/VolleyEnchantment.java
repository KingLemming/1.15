package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import static cofh.lib.util.helpers.ArcheryHelper.validBow;
import static cofh.lib.util.references.EnsorcellationReferences.TRUESHOT;

public class VolleyEnchantment extends EnchantmentCoFH {

    public VolleyEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.BOW, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 4;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 1 + (level - 1) * 10;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return enable && (validBow(stack) || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != TRUESHOT;
    }

}
