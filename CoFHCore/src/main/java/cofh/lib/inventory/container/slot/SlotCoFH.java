package cofh.lib.inventory.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.BooleanSupplier;

import static cofh.lib.util.constants.Constants.TRUE;

public class SlotCoFH extends Slot {

    protected int slotStackLimit;
    protected BooleanSupplier enabled = TRUE;

    public SlotCoFH(IInventory inventoryIn, int index, int xPosition, int yPosition) {

        super(inventoryIn, index, xPosition, yPosition);
        slotStackLimit = inventoryIn.getInventoryStackLimit();
    }

    public SlotCoFH(IInventory inventoryIn, int index, int xPosition, int yPosition, int slotStackLimit) {

        super(inventoryIn, index, xPosition, yPosition);
        this.slotStackLimit = slotStackLimit;
    }

    public SlotCoFH setEnabled(BooleanSupplier enabled) {

        this.enabled = enabled;
        return this;
    }

    @Override
    public int getSlotStackLimit() {

        return slotStackLimit;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return inventory.isItemValidForSlot(slotIndex, stack);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isEnabled() {

        return enabled.getAsBoolean();
    }

}
