package cofh.lib.inventory;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

import static cofh.lib.util.helpers.ItemHelper.cloneStack;

public class ItemStorageInfinite extends ItemStorageCoFH {

    public ItemStorageInfinite() {

        super();
    }

    public ItemStorageInfinite(int capacity) {

        super(capacity);
    }

    public ItemStorageInfinite(Predicate<ItemStack> validator) {

        super(validator);
    }

    public ItemStorageInfinite(int capacity, Predicate<ItemStack> validator) {

        super(capacity, validator);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {

        if (amount <= 0 || item.isEmpty()) {
            return ItemStack.EMPTY;
        }
        int retCount = Math.min(item.getCount(), amount);
        return cloneStack(item, retCount);
    }

}
