package cofh.lib.inventory;

import cofh.lib.util.helpers.MathHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InvWrapper implements IInventory {

    protected SimpleItemInv inventory;
    protected int stackLimit;

    public InvWrapper(SimpleItemInv inventory) {

        this(inventory, 64);
    }

    public InvWrapper(SimpleItemInv inventory, int stackLimit) {

        this.inventory = inventory;
        this.stackLimit = MathHelper.clamp(stackLimit, 1, Integer.MAX_VALUE);
    }

    // region IInventory
    @Override
    public int getSizeInventory() {

        return inventory.getSlots();
    }

    @Override
    public boolean isEmpty() {

        return inventory.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {

        return inventory.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {

        ItemStack inSlot = inventory.get(index);
        if (inSlot.isEmpty()) {
            return ItemStack.EMPTY;
        }
        if (inSlot.getCount() <= count) {
            count = inSlot.getCount();
        }
        ItemStack stack = inSlot.split(count);
        if (inSlot.getCount() <= 0) {
            inventory.set(index, ItemStack.EMPTY);
            inventory.tile.onInventoryChange(index);
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {

        ItemStack stack = inventory.get(index);
        inventory.set(index, ItemStack.EMPTY);
        inventory.tile.onInventoryChange(index);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        inventory.set(index, stack);
        inventory.tile.onInventoryChange(index);
    }

    @Override
    public int getInventoryStackLimit() {

        return stackLimit;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {

        return true;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        return inventory.isItemValid(index, stack);
    }

    @Override
    public void clear() {

        inventory.clear();
    }
    // endregion
}
