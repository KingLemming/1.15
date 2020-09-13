package cofh.core.inventory;

import cofh.core.util.IInventoryCallback;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ManagedItemHandler extends SimpleItemHandler {

    protected List<ItemStorageCoFH> inputSlots;
    protected List<ItemStorageCoFH> outputSlots;

    public ManagedItemHandler(@Nullable IInventoryCallback tile, @Nonnull List<ItemStorageCoFH> inputSlots, @Nonnull List<ItemStorageCoFH> outputSlots) {

        super(tile);

        this.inputSlots = inputSlots;
        this.outputSlots = outputSlots;
        this.slots.addAll(inputSlots);
        this.slots.addAll(outputSlots);
    }

    // region IItemHandler
    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {

        if (slot < 0 || slot >= inputSlots.size()) {
            return stack;
        }
        ItemStack cur = slots.get(slot).getItemStack();
        ItemStack ret = slots.get(slot).insertItem(slot, stack, simulate);
        if (!simulate && cur.getItem() != slots.get(slot).getItemStack().getItem()) {
            onInventoryChange(slot);
        }
        return ret;
    }
    // endregion
}
