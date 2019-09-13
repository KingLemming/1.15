package cofh.lib.inventory;

import cofh.lib.block.ITileCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static cofh.lib.util.constants.Tags.TAG_ITEM_INV;
import static cofh.lib.util.constants.Tags.TAG_SLOT;

/**
 * Inventory abstraction using CoFH Item Storage objects.
 */
public class SimpleItemInv extends SimpleItemHandler {

    protected String tag;

    public SimpleItemInv(@Nullable ITileCallback tile) {

        this(tile, 0, TAG_ITEM_INV);
    }

    public SimpleItemInv(@Nullable ITileCallback tile, int size) {

        this(tile, size, TAG_ITEM_INV);
    }

    public SimpleItemInv(@Nullable ITileCallback tile, @Nonnull List<ItemStorageCoFH> slots) {

        this(tile, slots, TAG_ITEM_INV);
    }

    public SimpleItemInv(@Nullable ITileCallback tile, @Nonnull String tag) {

        this(tile, 0, tag);
    }

    public SimpleItemInv(@Nullable ITileCallback tile, @Nonnull List<ItemStorageCoFH> slots, @Nonnull String tag) {

        super(tile, slots);
        this.tag = tag;
    }

    public SimpleItemInv(@Nullable ITileCallback tile, int size, @Nonnull String tag) {

        super(tile, new ArrayList<>(size));
        this.tile = tile;
        this.tag = tag;
        for (int i = 0; i < size; i++) {
            slots.add(new ItemStorageCoFH());
        }
    }

    public void clear() {

        for (ItemStorageCoFH slot : slots) {
            slot.setItemStack(ItemStack.EMPTY);
        }
    }

    public void set(int slot, ItemStack stack) {

        slots.get(slot).setItemStack(stack);
    }

    public ItemStack get(int slot) {

        return slots.get(slot).getItemStack();
    }

    public ItemStorageCoFH getSlot(int slot) {

        return slots.get(slot);
    }

    // region NBT
    public SimpleItemInv readFromNBT(CompoundNBT nbt) {

        for (ItemStorageCoFH slot : slots) {
            slot.setItemStack(ItemStack.EMPTY);
        }
        ListNBT list = nbt.getList(tag, 10);
        for (int i = 0; i < list.size(); ++i) {
            CompoundNBT tag = list.getCompound(i);
            int slot = tag.getByte(TAG_SLOT);
            if (slot >= 0 && slot < slots.size()) {
                slots.get(slot).readFromNBT(tag);
            }
        }
        return this;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt) {

        if (slots.size() <= 0) {
            return nbt;
        }
        ListNBT list = new ListNBT();
        for (int i = 0; i < slots.size(); ++i) {
            if (!slots.get(i).isEmpty()) {
                CompoundNBT tag = new CompoundNBT();
                tag.putByte(TAG_SLOT, (byte) i);
                slots.get(i).writeToNBT(tag);
                list.add(tag);
            }
        }
        if (!list.isEmpty()) {
            nbt.put(tag, list);
        }
        return nbt;
    }
    // endregion
}
