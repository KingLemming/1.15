package cofh.lib.inventory.container;

import cofh.lib.inventory.container.slot.SlotFalseCopy;
import cofh.lib.util.helpers.InventoryHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ContainerCoFH extends Container {

    protected String name = "";
    protected boolean falseSlotSupport = true;

    public ContainerCoFH(@Nullable ContainerType<?> type, int id) {

        super(type, id);
    }

    // region HELPERS
    protected void bindPlayerInventory(PlayerInventory inventory) {

        int xOffset = getPlayerInventoryHorizontalOffset();
        int yOffset = getPlayerInventoryVerticalOffset();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(inventory, j + i * 9 + 9, xOffset + j * 18, yOffset + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(inventory, i, xOffset + i * 18, yOffset + 58));
        }
    }

    protected int getPlayerInventoryHorizontalOffset() {

        return 8;
    }

    protected abstract int getPlayerInventoryVerticalOffset();

    protected abstract int getSizeInventory();

    protected boolean supportsShiftClick(PlayerEntity player, int slotIndex) {

        return supportsShiftClick(slotIndex);
    }

    protected boolean supportsShiftClick(int slotIndex) {

        return true;
    }

    protected boolean performMerge(int slotIndex, ItemStack stack) {

        int invBase = getSizeInventory();
        int invFull = inventorySlots.size();

        if (slotIndex < invBase) {
            return mergeItemStack(stack, invBase, invFull, true);
        }
        return mergeItemStack(stack, 0, invBase, false);
    }
    // endregion

    // region OVERRIDES
    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int slotIndex) {

        if (!supportsShiftClick(player, slotIndex)) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            stack = stackInSlot.copy();

            if (!performMerge(slotIndex, stackInSlot)) {
                return ItemStack.EMPTY;
            }
            slot.onSlotChange(stackInSlot, stack);

            if (stackInSlot.getCount() <= 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.putStack(stackInSlot);
            }
            if (stackInSlot.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stackInSlot);
        }
        return stack;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {

        if (!falseSlotSupport) {
            return super.slotClick(slotId, dragType, clickTypeIn, player);
        }
        Slot slot = slotId < 0 ? null : inventorySlots.get(slotId);
        if (slot instanceof SlotFalseCopy) {
            if (dragType == 2) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.putStack(player.inventory.getItemStack().isEmpty() ? ItemStack.EMPTY : player.inventory.getItemStack().copy());
            }
            return player.inventory.getItemStack();
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void setAll(List<ItemStack> stacks) {

        for (int i = 0; i < stacks.size(); ++i) {
            putStackInSlot(i, stacks.get(i));
        }
    }

    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {

        return InventoryHelper.mergeItemStack(inventorySlots, stack, startIndex, endIndex, reverseDirection);
    }
    // endregion
}
