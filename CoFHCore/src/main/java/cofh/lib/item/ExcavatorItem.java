package cofh.lib.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;

import static cofh.lib.util.constants.ToolTypes.EXCAVATOR;

public class ExcavatorItem extends ShovelItem {

    public ExcavatorItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Properties builder) {

        super(tier, (int) attackDamageIn, attackSpeedIn, builder.addToolType(EXCAVATOR, tier.getHarvestLevel()));
    }

}
