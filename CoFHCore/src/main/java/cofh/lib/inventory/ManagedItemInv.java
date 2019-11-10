package cofh.lib.inventory;

import cofh.lib.block.ITileCallback;
import cofh.lib.util.StorageGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.EmptyHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ManagedItemInv extends SimpleItemInv {

    protected List<ItemStorageCoFH> inputSlots = new ArrayList<>();
    protected List<ItemStorageCoFH> catalystSlots = new ArrayList<>();
    protected List<ItemStorageCoFH> outputSlots = new ArrayList<>();
    protected List<ItemStorageCoFH> accessibleSlots = new ArrayList<>();
    protected List<ItemStorageCoFH> internalSlots = new ArrayList<>();

    public ManagedItemInv(ITileCallback tile) {

        super(tile);
    }

    public ManagedItemInv(ITileCallback tile, String tag) {

        super(tile, tag);
    }

    public void addSlot(StorageGroup group) {

        addSlot(new ItemStorageCoFH(), group);
    }

    public void addSlots(StorageGroup group, int amount) {

        for (int i = 0; i < amount; ++i) {
            addSlot(new ItemStorageCoFH(), group);
        }
    }

    public void addSlot(ItemStorageCoFH slot, StorageGroup group) {

        slots.add(slot);
        switch (group) {
            case CATALYST:
                catalystSlots.add(slot);
                inputSlots.add(slot);
                accessibleSlots.add(slot);
            case INPUT:
                inputSlots.add(slot);
                accessibleSlots.add(slot);
                break;
            case OUTPUT:
                outputSlots.add(slot);
                accessibleSlots.add(slot);
                break;
            case ACCESSIBLE:
                accessibleSlots.add(slot);
                break;
            case INTERNAL:
                internalSlots.add(slot);
                break;
            default:
        }
    }

    public void addSlot(Predicate<ItemStack> validator, StorageGroup group) {

        addSlot(new ItemStorageCoFH(validator), group);
    }

    public List<ItemStorageCoFH> getInputSlots() {

        return inputSlots;
    }

    public List<ItemStorageCoFH> getOutputSlots() {

        return outputSlots;
    }

    public List<ItemStorageCoFH> getAccessibleSlots() {

        return accessibleSlots;
    }

    public List<ItemStorageCoFH> getInternalSlots() {

        return internalSlots;
    }

    public IItemHandler getHandler(StorageGroup group) {

        switch (group) {
            case INPUT:
                return new ManagedItemHandler(tile, inputSlots, Collections.emptyList());
            case OUTPUT:
                return new ManagedItemHandler(tile, Collections.emptyList(), outputSlots);
            case ACCESSIBLE:
                return new ManagedItemHandler(tile, inputSlots, outputSlots);
            case INTERNAL:
                return new SimpleItemHandler(tile, internalSlots);
            case ALL:
                return new SimpleItemHandler(tile, slots);
            default:
        }
        return EmptyHandler.INSTANCE;
    }

}
