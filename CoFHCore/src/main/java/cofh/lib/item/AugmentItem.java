package cofh.lib.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nullable;

public class AugmentItem extends ItemCoFH implements IAugmentItem {

    private final CompoundNBT augmentData;

    public AugmentItem(Properties builder, CompoundNBT augmentData) {

        super(builder);
        this.augmentData = augmentData;
    }

    // region IAugmentItem
    @Nullable
    @Override
    public CompoundNBT getAugmentData(ItemStack augment) {

        return augmentData;
    }
    // endregion
}
