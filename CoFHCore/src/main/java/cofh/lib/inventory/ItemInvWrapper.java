package cofh.lib.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;

import static cofh.lib.util.constants.NBTTags.TAG_SLOT;

public class ItemInvWrapper implements IInventory {

    private NonNullList<ItemStack> stackList;
    private ItemStack invContainer = ItemStack.EMPTY;
    private ListNBT invContents;

    public ItemInvWrapper(int size) {

        this.stackList = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public ItemInvWrapper setInvContainer(ItemStack invContainer, ListNBT contents, int size) {

        this.invContainer = invContainer;
        this.stackList = NonNullList.withSize(size, ItemStack.EMPTY);
        readFromContainerInv(contents);
        return this;
    }

    // region NBT
    public void readFromContainerInv(ListNBT list) {

        this.stackList.clear();
        for (int i = 0; i < list.size(); ++i) {
            CompoundNBT slotTag = list.getCompound(i);
            // If items are not saved w/ slot numbers, read into first available slots.
            int slot = slotTag.contains(TAG_SLOT) ? slotTag.getByte(TAG_SLOT) : i;
            if (slot >= 0 && slot < stackList.size()) {
                this.stackList.set(slot, ItemStack.read(slotTag));
            }
        }
    }

    public ListNBT writeToContainerInv() {

        ListNBT list = new ListNBT();

        for (int i = 0; i < stackList.size(); ++i) {
            if (!stackList.get(i).isEmpty()) {
                CompoundNBT slotTag = new CompoundNBT();
                slotTag.putByte(TAG_SLOT, (byte) i);
                this.stackList.get(i).write(slotTag);
                list.add(slotTag);
            }
        }
        return list;
    }

    // region IInventory
    @Override
    public int getSizeInventory() {

        return this.stackList.size();
    }

    public boolean isEmpty() {

        for (ItemStack stack : this.stackList) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {

        return index >= this.getSizeInventory() ? ItemStack.EMPTY : this.stackList.get(index);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {

        return ItemStackHelper.getAndRemove(this.stackList, index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {

        ItemStack stack = ItemStackHelper.getAndSplit(this.stackList, index, count);
        if (!stack.isEmpty()) {
            // this.eventHandler.onCraftMatrixChanged(this);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        this.stackList.set(index, stack);
        // this.eventHandler.onCraftMatrixChanged(this);
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {

        return !invContainer.isEmpty();
    }

    @Override
    public void clear() {

        this.invContainer = ItemStack.EMPTY;
        this.stackList.clear();
    }
    // endregion
}