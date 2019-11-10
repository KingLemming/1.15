package cofh.lib.util.helpers;

import cofh.lib.inventory.container.slot.SlotFalseCopy;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

import static cofh.lib.util.helpers.ItemHelper.itemsIdentical;

/**
 * This class contains helper functions related to Inventories and Inventory manipulation.
 *
 * @author King Lemming
 */
public class InventoryHelper {

    private InventoryHelper() {

    }

    /**
     * Add an ItemStack to an inventory. Return true if the entire stack was added.
     *
     * @param inventory  The inventory.
     * @param stack      ItemStack to add.
     * @param startIndex First slot to attempt to add into. Does not loop around fully.
     * @param endIndex   Final slot to attempt to add into. Should be at most length - 1
     */
    public static boolean addItemStackToInventory(ItemStack[] inventory, ItemStack stack, int startIndex, int endIndex) {

        if (stack.isEmpty()) {
            return true;
        }
        int openSlot = -1;
        for (int i = startIndex; i <= endIndex; ++i) {
            if (itemsIdentical(stack, inventory[i]) && inventory[i].getMaxStackSize() > inventory[i].getCount()) {
                int hold = inventory[i].getMaxStackSize() - inventory[i].getCount();
                if (hold >= stack.getCount()) {
                    inventory[i].grow(stack.getCount());
                    return true;
                } else {
                    stack.shrink(hold);
                    inventory[i].grow(hold);
                }
            } else if (inventory[i].isEmpty() && openSlot == -1) {
                openSlot = i;
            }
        }
        if (openSlot > -1) {
            inventory[openSlot] = stack;
        } else {
            return false;
        }
        return true;
    }

    /**
     * Shortcut method for above, assumes ending slot is length - 1
     */
    public static boolean addItemStackToInventory(ItemStack[] inventory, ItemStack stack, int startIndex) {

        return addItemStackToInventory(inventory, stack, startIndex, inventory.length - 1);
    }

    /**
     * Shortcut method for above, assumes starting slot is 0.
     */
    public static boolean addItemStackToInventory(ItemStack[] inventory, ItemStack stack) {

        return addItemStackToInventory(inventory, stack, 0);
    }

    public static ItemStack insertStackIntoInventory(IItemHandler handler, ItemStack stack, boolean simulate) {

        return insertStackIntoInventory(handler, stack, simulate, false);
    }

    public static ItemStack insertStackIntoInventory(IItemHandler handler, ItemStack stack, boolean simulate, boolean forceEmptySlot) {

        return forceEmptySlot ? ItemHandlerHelper.insertItem(handler, stack, simulate) : ItemHandlerHelper.insertItemStacked(handler, stack, simulate);
    }

    public static boolean mergeItemStack(List<Slot> slots, ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {

        boolean successful = false;
        int i = !reverseDirection ? startIndex : endIndex - 1;
        int iterOrder = !reverseDirection ? 1 : -1;

        Slot slot;
        ItemStack existingStack;

        if (stack.isStackable()) {
            while (stack.getCount() > 0 && (!reverseDirection && i <= endIndex || reverseDirection && i >= startIndex)) {
                slot = slots.get(i);
                if (slot instanceof SlotFalseCopy) {
                    i += iterOrder;
                    continue;
                }
                existingStack = slot.getStack();
                if (!existingStack.isEmpty()) {
                    int maxStack = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());
                    int rmv = Math.min(maxStack, stack.getCount());
                    if (slot.isItemValid(ItemHelper.cloneStack(stack, rmv)) && itemsIdentical(existingStack, stack)) {
                        int existingSize = existingStack.getCount() + stack.getCount();
                        if (existingSize <= maxStack) {
                            stack.setCount(0);
                            existingStack.setCount(existingSize);
                            slot.putStack(existingStack);
                            successful = true;
                        } else if (existingStack.getCount() < maxStack) {
                            stack.shrink(maxStack - existingStack.getCount());
                            existingStack.setCount(maxStack);
                            slot.putStack(existingStack);
                            successful = true;
                        }
                    }
                }
                i += iterOrder;
            }
        }
        if (stack.getCount() > 0) {
            i = !reverseDirection ? startIndex : endIndex - 1;
            while (stack.getCount() > 0 && (!reverseDirection && i <= endIndex || reverseDirection && i >= startIndex)) {
                slot = slots.get(i);
                if (slot instanceof SlotFalseCopy) {
                    i += iterOrder;
                    continue;
                }
                existingStack = slot.getStack();
                if (existingStack.isEmpty()) {
                    int maxStack = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());
                    int rmv = Math.min(maxStack, stack.getCount());

                    if (slot.isItemValid(ItemHelper.cloneStack(stack, rmv))) {
                        existingStack = stack.split(rmv);
                        slot.putStack(existingStack);
                        successful = true;
                    }
                }
                i += iterOrder;
            }
        }
        return successful;
    }

    // TODO: Fix
    //    // region BLOCK TRANSFER
    //    public static boolean extractFromAdjacent(TileEntity tile, ItemStorageCoFH slot, int amount, Direction side) {
    //
    //        TileEntity adjTile = BlockHelper.getAdjacentTileEntity(tile, side);
    //        Direction opposite = side.getOpposite();
    //
    //        if (hasItemHandlerCap(adjTile, opposite)) {
    //            IItemHandler handler = getItemHandlerCap(adjTile, opposite);
    //            if (handler == null) {
    //                return false;
    //            }
    //            int initialAmount = amount;
    //            for (int i = 0; i < handler.getSlots() && amount > 0; ++i) {
    //                ItemStack query = handler.extractItem(i, amount, true);
    //                if (query.isEmpty()) {      // Skip empty slots.
    //                    continue;
    //                }
    //                ItemStack ret = slot.insertItem(0, query, true);
    //                if (ret.getCount() != query.getCount()) {       // If the slot accepted items.
    //                    slot.insertItem(0, handler.extractItem(i, amount, false), false);
    //                    amount -= query.getCount() - ret.getCount();
    //                }
    //            }
    //            return amount != initialAmount;
    //        }
    //        return false;
    //    }
    //
    //    public static boolean insertIntoAdjacent(TileEntity tile, ItemStorageCoFH slot, int amount, Direction side) {
    //
    //        if (slot.isEmpty()) {
    //            return false;
    //        }
    //        ItemStack initialStack = slot.getItemStack().copy();
    //        initialStack.setCount(Math.min(amount, initialStack.getCount()));
    //        TileEntity adjTile = BlockHelper.getAdjacentTileEntity(tile, side);
    //        Direction opposite = side.getOpposite();
    //
    //        if (hasItemHandlerCap(adjTile, opposite)) {
    //            // OPTIMIZATION: This is used instead of addToInventory because prechecks have already happened.
    //            ItemStack inserted = insertStackIntoInventory(getItemHandlerCap(adjTile, opposite), initialStack, false);
    //            if (inserted.getCount() >= initialStack.getCount()) {
    //                return false;
    //            }
    //            slot.modify(inserted.getCount() - initialStack.getCount());
    //            return true;
    //        }
    //        return false;
    //    }
    //    // endregion
    //
    //    // region HELPERS
    //    public static ItemStack addToInventory(TileEntity tile, Direction side, ItemStack stack) {
    //
    //        if (stack.isEmpty()) {
    //            return ItemStack.EMPTY;
    //        }
    //        if (hasItemHandlerCap(tile, side.getOpposite())) {
    //            stack = insertStackIntoInventory(getItemHandlerCap(tile, side.getOpposite()), stack, false);
    //        }
    //        return stack;
    //    }
    //
    //    public static boolean hasItemHandlerCap(TileEntity tile, Direction face) {
    //
    //        return tile != null && (tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face) || tile instanceof IInventory);
    //    }
    //
    //    public static IItemHandler getItemHandlerCap(TileEntity tile, Direction face) {
    //
    //        if (tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face)) {
    //            return tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face);
    //        } else if (tile instanceof ISidedInventory && face != null) {
    //            return new SidedInvWrapper(((ISidedInventory) tile), face);
    //        } else if (tile instanceof IInventory) {
    //            return new InvWrapper(((IInventory) tile));
    //        }
    //        return EmptyHandler.INSTANCE;
    //    }
    //
    //    public static boolean isEmpty(ItemStack[] inventory) {
    //
    //        for (ItemStack stack : inventory) {
    //            if (!stack.isEmpty()) {
    //                return false;
    //            }
    //        }
    //        return true;
    //    }
    //    // endregion
}
