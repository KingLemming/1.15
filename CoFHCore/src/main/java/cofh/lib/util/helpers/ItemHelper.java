package cofh.lib.util.helpers;

import cofh.lib.item.IMultiModeItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ItemHelper {

    private ItemHelper() {

    }

    public static ItemStack consumeItem(ItemStack stack) {

        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        Item item = stack.getItem();
        boolean largerStack = stack.getCount() > 1;
        // vanilla only alters the stack passed to hasContainerItem/etc. when the size is > 1

        if (largerStack) {
            stack.shrink(1);
        }
        if (item.hasContainerItem(stack)) {
            ItemStack ret = item.getContainerItem(stack);

            if (ret.isEmpty()) {
                return ItemStack.EMPTY;
            }
            if (ret.isDamageable() && ret.getDamage() > ret.getMaxDamage()) {
                ret = ItemStack.EMPTY;
            }
            return ret;
        }
        return largerStack ? stack : ItemStack.EMPTY;
    }

    // region CLONESTACK
    public static ItemStack cloneStack(Item item) {

        return cloneStack(item, 1);
    }

    public static ItemStack cloneStack(Block block) {

        return cloneStack(block, 1);
    }

    public static ItemStack cloneStack(Item item, int stackSize) {

        if (item == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(item, stackSize);
    }

    public static ItemStack cloneStack(Block block, int stackSize) {

        if (block == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(block, stackSize);
    }

    public static ItemStack cloneStack(ItemStack stack, int stackSize) {

        if (stack.isEmpty() || stackSize <= 0) {
            return ItemStack.EMPTY;
        }
        ItemStack retStack = stack.copy();
        retStack.setCount(stackSize);

        return retStack;
    }

    public static ItemStack cloneStack(ItemStack stack) {

        return stack.isEmpty() ? ItemStack.EMPTY : stack.copy();
    }
    // endregion

    // region NBT TAGS
    public static ItemStack copyTag(ItemStack container, ItemStack other) {

        if (!other.isEmpty() && other.hasTag()) {
            container.setTag(other.getTag().copy());
        }
        return container;
    }

    // TODO: Fix
    //    public static CompoundNBT setItemStackTagName(CompoundNBT tag, String name) {
    //
    //        if (Strings.isNullOrEmpty(name)) {
    //            return null;
    //        }
    //        if (tag == null) {
    //            tag = new CompoundNBT();
    //        }
    //        if (!tag.hasKey("display")) {
    //            tag.setTag("display", new CompoundNBT());
    //        }
    //        tag.getCompoundTag("display").setString("Name", name);
    //        return tag;
    //    }
    // endregion

    // region COMPARISON
    public static boolean itemsIdentical(ItemStack stackA, ItemStack stackB) {

        return itemsEqual(stackA, stackB) && doNBTsMatch(stackA.getTag(), stackB.getTag());
    }

    public static boolean itemsEqual(ItemStack stackA, ItemStack stackB) {

        return itemsEqual(stackA.getItem(), stackB.getItem());
    }

    public static boolean itemsEqual(Item itemA, Item itemB) {

        return itemA != null && itemB != null && (itemA == itemB || itemA.equals(itemB));
    }

    public static boolean doNBTsMatch(CompoundNBT nbtA, CompoundNBT nbtB) {

        return nbtA == null && nbtB == null || nbtA != null && nbtB != null && nbtA.equals(nbtB);
    }

    // TODO: Fix
    //    /**
    //     * Compares item, meta, size and nbt of two stacks while ignoring nbt tag keys provided.
    //     * This is useful in shouldCauseReequipAnimation overrides.
    //     *
    //     * @param stackA          first stack to compare
    //     * @param stackB          second stack to compare
    //     * @param nbtTagsToIgnore tag keys to ignore when comparing the stacks
    //     */
    //    public static boolean areItemStacksEqualIgnoreTags(ItemStack stackA, ItemStack stackB, String... nbtTagsToIgnore) {
    //
    //        if (stackA.isEmpty() && stackB.isEmpty()) {
    //            return true;
    //        }
    //        if (stackA.isEmpty() && !stackB.isEmpty()) {
    //            return false;
    //        }
    //        if (!stackA.isEmpty() && stackB.isEmpty()) {
    //            return false;
    //        }
    //        if (stackA.getItem() != stackB.getItem()) {
    //            return false;
    //        }
    //        if (stackA.getDamage() != stackB.getDamage()) {
    //            return false;
    //        }
    //        if (stackA.getCount() != stackB.getCount()) {
    //            return false;
    //        }
    //        if (stackA.getTag() == null && stackB.getTag() == null) {
    //            return true;
    //        }
    //        if (stackA.getTag() == null || stackB.getTag() == null) {
    //            return false;
    //        }
    //        int numberOfKeys = stackA.getTag().getKeySet().size();
    //        if (numberOfKeys != stackB.getTag().getKeySet().size()) {
    //            return false;
    //        }
    //
    //        CompoundNBT tagA = stackA.getTag();
    //        CompoundNBT tagB = stackB.getTag();
    //
    //        String[] keys = new String[numberOfKeys];
    //        keys = tagA.getKeySet().toArray(keys);
    //
    //        a:
    //        for (int i = 0; i < numberOfKeys; i++) {
    //            for (int j = 0; j < nbtTagsToIgnore.length; j++) {
    //                if (nbtTagsToIgnore[j].equals(keys[i])) {
    //                    continue a;
    //                }
    //            }
    //            if (!tagA.getTag(keys[i]).equals(tagB.getTag(keys[i]))) {
    //                return false;
    //            }
    //        }
    //        return true;
    //    }
    // endregion

    // TODO: Fix
    // region INVENTORY
    //    public static void readInventoryFromNBT(CompoundNBT nbt, ItemStack[] inventory) {
    //
    //        NBTTagList list = nbt.getTagList("Inventory", 10);
    //        inventory = new ItemStack[inventory.length];
    //        Arrays.fill(inventory, ItemStack.EMPTY);
    //        for (int i = 0; i < list.tagCount(); i++) {
    //            CompoundNBT tag = list.getCompoundTagAt(i);
    //            int slot = tag.getInt("Slot");
    //            if (slot >= 0 && slot < inventory.length) {
    //                inventory[slot] = new ItemStack(tag);
    //            }
    //        }
    //    }
    //
    //    public static void writeInventoryToNBT(CompoundNBT nbt, ItemStack[] inventory) {
    //
    //        if (inventory.length <= 0) {
    //            return;
    //        }
    //        NBTTagList list = new NBTTagList();
    //        for (int i = 0; i < inventory.length; i++) {
    //            if (!inventory[i].isEmpty()) {
    //                CompoundNBT tag = new CompoundNBT();
    //                tag.setInteger("Slot", i);
    //                inventory[i].writeToNBT(tag);
    //                list.appendTag(tag);
    //            }
    //        }
    //        if (list.tagCount() > 0) {
    //            nbt.setTag("Inventory", list);
    //        }
    //    }
    // endregion

    // region HELD ITEMS
    public static boolean isPlayerHoldingSomething(PlayerEntity player) {

        return !getHeldStack(player).isEmpty();
    }

    public static ItemStack getMainhandStack(PlayerEntity player) {

        return player.getHeldItemMainhand();
    }

    public static ItemStack getOffhandStack(PlayerEntity player) {

        return player.getHeldItemOffhand();
    }

    public static ItemStack getHeldStack(PlayerEntity player) {

        ItemStack stack = player.getHeldItemMainhand();
        if (stack.isEmpty()) {
            stack = player.getHeldItemOffhand();
        }
        return stack;
    }
    // endregion

    // region MULTIMODE
    public static ItemStack getHeldMultiModeStack(PlayerEntity player) {

        ItemStack stack = player.getHeldItemMainhand();
        if (stack.isEmpty() || !(stack.getItem() instanceof IMultiModeItem)) {
            stack = player.getHeldItemOffhand();
        }
        return stack;
    }

    public static boolean isPlayerHoldingMultiModeItem(PlayerEntity player) {

        if (!isPlayerHoldingSomething(player)) {
            return false;
        }
        ItemStack heldItem = player.getHeldItemMainhand();
        if (heldItem.getItem() instanceof IMultiModeItem) {
            return true;
        } else {
            heldItem = player.getHeldItemOffhand();
            return heldItem.getItem() instanceof IMultiModeItem;
        }
    }

    public static boolean incrHeldMultiModeItemState(PlayerEntity player) {

        if (!isPlayerHoldingSomething(player)) {
            return false;
        }
        ItemStack heldItem = player.getHeldItemMainhand();
        Item equipped = heldItem.getItem();
        if (equipped instanceof IMultiModeItem) {
            IMultiModeItem multiModeItem = (IMultiModeItem) equipped;
            return multiModeItem.incrMode(heldItem);
        } else {
            heldItem = player.getHeldItemOffhand();
            equipped = heldItem.getItem();
            IMultiModeItem multiModeItem = (IMultiModeItem) equipped;
            return multiModeItem.incrMode(heldItem);
        }
    }

    public static boolean decrHeldMultiModeItemState(PlayerEntity player) {

        if (!isPlayerHoldingSomething(player)) {
            return false;
        }
        ItemStack heldItem = player.getHeldItemMainhand();
        Item equipped = heldItem.getItem();
        if (equipped instanceof IMultiModeItem) {
            IMultiModeItem multiModeItem = (IMultiModeItem) equipped;
            return multiModeItem.incrMode(heldItem);
        } else {
            heldItem = player.getHeldItemOffhand();
            equipped = heldItem.getItem();
            IMultiModeItem multiModeItem = (IMultiModeItem) equipped;
            return multiModeItem.incrMode(heldItem);
        }
    }

    public static boolean setHeldMultiModeItemState(PlayerEntity player, int mode) {

        if (!isPlayerHoldingSomething(player)) {
            return false;
        }
        ItemStack heldItem = player.getHeldItemMainhand();
        Item equipped = heldItem.getItem();
        if (equipped instanceof IMultiModeItem) {
            IMultiModeItem multiModeItem = (IMultiModeItem) equipped;
            return multiModeItem.setMode(heldItem, mode);
        } else {
            heldItem = player.getHeldItemOffhand();
            equipped = heldItem.getItem();
            IMultiModeItem multiModeItem = (IMultiModeItem) equipped;
            return multiModeItem.setMode(heldItem, mode);
        }
    }
    // endregion
}
