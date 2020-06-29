package cofh.thermal.core.inventory.container.workbench;

import cofh.lib.inventory.InvWrapperCoFH;
import cofh.lib.inventory.ItemInvWrapper;
import cofh.lib.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.util.helpers.AugmentableHelper;
import cofh.thermal.core.tileentity.workbench.TinkerBenchTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static cofh.lib.util.constants.Constants.MAX_AUGMENTS;
import static cofh.thermal.core.init.TCoreReferences.TINKER_BENCH_CONTAINER;

public class TinkerBenchContainer extends TileContainer {

    public final TinkerBenchTile tile;

    protected SlotCoFH tinkerSlot;
    protected List<SlotCoFH> tinkerAugmentSlots = new ArrayList<>(MAX_AUGMENTS);
    protected ItemInvWrapper itemInventory = new ItemInvWrapper(MAX_AUGMENTS) {

        @Override
        public boolean isItemValidForSlot(int index, ItemStack stack) {

            return tinkerSlot.getHasStack() && index < AugmentableHelper.getAugmentSlots(tinkerSlot.getStack()) && AugmentableHelper.validAugment(tinkerSlot.getStack(), stack);
        }
    };

    public TinkerBenchContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(TINKER_BENCH_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (TinkerBenchTile) world.getTileEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        tinkerSlot = new SlotCoFH(tileInv, 0, 44, 35) {

            @Override
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {

                writeAugmentsToItem(stack);
                itemInventory.clear();
                return super.onTake(thePlayer, stack);
            }

            @Override
            public void putStack(ItemStack stack) {

                ItemStack curStack = tinkerSlot.getStack();
                if (!curStack.isEmpty() && !curStack.equals(stack)) {
                    writeAugmentsToItem(curStack);
                }
                super.putStack(stack);
                if (!stack.isEmpty()) {
                    readAugmentsFromItem(stack);
                }
            }
        };
        addSlot(tinkerSlot);

        addSlot(new SlotCoFH(tileInv, 1, 8, 53));
        addSlot(new SlotCoFH(tileInv, 2, 152, 53));

        bindAugmentSlots(tileInv, 3, this.tile.augSize());
        bindTinkerSlots(itemInventory, 0, MAX_AUGMENTS);
        bindPlayerInventory(inventory);

        readAugmentsFromItem(tinkerSlot.getStack());
    }

    private void readAugmentsFromItem(ItemStack stack) {

        if (!stack.isEmpty()) {
            itemInventory.setInvContainer(stack, AugmentableHelper.getAugments(stack), AugmentableHelper.getAugmentSlots(stack));
        }
    }

    private void writeAugmentsToItem(ItemStack stack) {

        if (!stack.isEmpty()) {
            AugmentableHelper.setAugments(stack, itemInventory.getStacks());
            tile.markDirty();
        }
    }

    private void bindTinkerSlots(IInventory inventory, int startIndex, int numSlots) {

        for (int i = 0; i < numSlots; ++i) {
            SlotCoFH slot = new SlotCoFH(inventory, i + startIndex, 0, 0, 1) {

                public void onSlotChanged() {

                    this.inventory.markDirty();
                }
            };
            tinkerAugmentSlots.add(slot);
            addSlot(slot);
        }
        ((ArrayList<SlotCoFH>) tinkerAugmentSlots).trimToSize();
    }

    public int getNumTinkerAugmentSlots() {

        return AugmentableHelper.getAugmentSlots(tinkerSlot.getStack());
    }

    public List<SlotCoFH> getTinkerAugmentSlots() {

        return tinkerAugmentSlots;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {

        writeAugmentsToItem(tinkerSlot.getStack());
        super.onContainerClosed(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {

        if (index == tinkerSlot.getSlotIndex()) {
            writeAugmentsToItem(tinkerSlot.getStack());
        }
        return super.transferStackInSlot(player, index);
    }

}
