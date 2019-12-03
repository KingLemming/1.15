package cofh.lib.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShearsItem;

public class ShearsItemCoFH extends ShearsItem {

    protected int enchantability = 1;

    public ShearsItemCoFH(Properties builder) {

        super(builder);
    }

    public ShearsItemCoFH(IItemTier tier, Properties builder) {

        super(builder);
        setParams(tier);
    }

    public ShearsItemCoFH setParams(IItemTier tier) {

        this.enchantability = tier.getEnchantability();
        return this;
    }

    @Override
    public int getItemEnchantability() {

        return enchantability;
    }

}
