package cofh.lib.inventory;

import cofh.lib.tileentity.ITileCallback;
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
    protected List<ItemStorageCoFH> internalSlots = new ArrayList<>();

    protected IItemHandler inputHandler;
    protected IItemHandler outputHandler;
    protected IItemHandler accessibleHandler;
    protected IItemHandler internalHandler;
    protected IItemHandler allHandler;

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

        if (allHandler != null) {
            return;
        }
        slots.add(slot);
        switch (group) {
            case CATALYST:
                catalystSlots.add(slot);
                inputSlots.add(slot);
            case INPUT:
                inputSlots.add(slot);
                break;
            case OUTPUT:
                outputSlots.add(slot);
                break;
            case INTERNAL:
                internalSlots.add(slot);
                break;
            default:
        }
    }

    protected void optimize() {

        ((ArrayList<ItemStorageCoFH>) slots).trimToSize();
        ((ArrayList<ItemStorageCoFH>) inputSlots).trimToSize();
        ((ArrayList<ItemStorageCoFH>) catalystSlots).trimToSize();
        ((ArrayList<ItemStorageCoFH>) outputSlots).trimToSize();
        ((ArrayList<ItemStorageCoFH>) internalSlots).trimToSize();
    }

    public void initHandlers() {

        optimize();

        inputHandler = new ManagedItemHandler(tile, inputSlots, Collections.emptyList());
        outputHandler = new ManagedItemHandler(tile, Collections.emptyList(), outputSlots);
        accessibleHandler = new ManagedItemHandler(tile, inputSlots, outputSlots);
        internalHandler = new SimpleItemHandler(tile, internalSlots);
        allHandler = new SimpleItemHandler(tile, slots);
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

    public List<ItemStorageCoFH> getInternalSlots() {

        return internalSlots;
    }

    public IItemHandler getHandler(StorageGroup group) {

        if (allHandler == null) {
            initHandlers();
        }
        switch (group) {
            case INPUT:
                return inputHandler;
            case OUTPUT:
                return outputHandler;
            case ACCESSIBLE:
                return accessibleHandler;
            case INTERNAL:
                return internalHandler;
            case ALL:
                return allHandler;
            default:
        }
        return EmptyHandler.INSTANCE;
    }

}
