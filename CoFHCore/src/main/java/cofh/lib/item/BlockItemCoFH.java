package cofh.lib.item;

import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockItemCoFH extends BlockItem {

    protected boolean showInItemGroup = true;
    protected boolean creative;

    public BlockItemCoFH(Block blockIn, Properties builder) {

        super(blockIn, builder);
    }

    public BlockItemCoFH setCreative(boolean creative) {

        this.creative = creative;
        return this;
    }

    public final boolean isCreative() {

        return creative;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (!showInItemGroup) {
            return;
        }
        super.fillItemGroup(group, items);
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {

        return SecurityHelper.hasSecurity(stack);
    }

}
