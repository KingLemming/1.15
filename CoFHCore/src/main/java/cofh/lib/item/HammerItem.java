package cofh.lib.item;

import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ToolItem;

import java.util.Set;

public class HammerItem extends ToolItem {

    protected HammerItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn, Properties builder) {

        super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builder);
    }

}
