package cofh.lib.inventory.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotCoFH extends Slot {

    protected int slotStackLimit;

    public SlotCoFH(IInventory inventoryIn, int index, int xPosition, int yPosition) {

        super(inventoryIn, index, xPosition, yPosition);
        slotStackLimit = inventoryIn.getInventoryStackLimit();
    }

    public SlotCoFH(IInventory inventoryIn, int index, int xPosition, int yPosition, int slotStackLimit) {

        super(inventoryIn, index, xPosition, yPosition);
        this.slotStackLimit = slotStackLimit;
    }

    @Override
    public int getSlotStackLimit() {

        return slotStackLimit;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return inventory.isItemValidForSlot(slotIndex, stack);
    }

}
