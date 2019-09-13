package cofh.lib.inventory;

import cofh.lib.block.ITileCallback;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ManagedItemHandler extends SimpleItemHandler {

    protected List<ItemStorageCoFH> inputSlots;
    protected List<ItemStorageCoFH> outputSlots;

    public ManagedItemHandler(@Nullable ITileCallback tile, @Nonnull List<ItemStorageCoFH> inputSlots, @Nonnull List<ItemStorageCoFH> outputSlots) {

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
        ItemStack ret = slots.get(slot).insertItem(slot, stack, simulate);

        if (tile != null && !simulate) {
            tile.onInventoryChange(slot);
        }
        return ret;
    }
    // endregion
}
