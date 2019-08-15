package cofh.lib.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;

import static cofh.lib.util.constants.ToolTypes.HAMMER;

public class HammerItem extends PickaxeItem {

    public HammerItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Properties builder) {

        super(tier, (int) attackDamageIn, attackSpeedIn, builder.addToolType(HAMMER, tier.getHarvestLevel()));
    }

}
