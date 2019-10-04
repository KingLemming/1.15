package cofh.lib.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;

import static cofh.lib.util.constants.ToolTypes.HAMMER;

public class HammerItem extends PickaxeItem {

    public HammerItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Properties builder) {

        super(tier, (int) attackDamageIn, attackSpeedIn, builder.addToolType(HAMMER, tier.getHarvestLevel()));
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        return super.canApplyAtEnchantingTable(stack, enchantment) || enchantment.canApply(new ItemStack(Items.IRON_PICKAXE));
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {

        return true;
    }

}
