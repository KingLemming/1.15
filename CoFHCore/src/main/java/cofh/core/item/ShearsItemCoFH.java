package cofh.core.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.math.BlockPos;

public class ShearsItemCoFH extends ShearsItem implements ICoFHItem {

    protected int enchantability = 0;

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

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, net.minecraft.world.IWorldReader world, BlockPos pos, PlayerEntity player) {

        return true;
    }

}
