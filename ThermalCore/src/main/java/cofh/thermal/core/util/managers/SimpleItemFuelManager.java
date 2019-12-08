package cofh.thermal.core.util.managers;

import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;
import cofh.lib.inventory.ItemStackHolder;
import cofh.lib.util.ComparableItemStack;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.recipes.IDynamoFuel;
import cofh.thermal.core.util.recipes.SimpleItemFuel;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Basic fuel manager - single item key'd.
 */
public abstract class SimpleItemFuelManager extends AbstractManager implements IFuelManager {

    public static final int MIN_ENERGY = 3000;
    public static final int MAX_ENERGY = 200000000;

    protected Map<ComparableItemStack, IDynamoFuel> fuelMap = new Object2ObjectOpenHashMap<>();

    protected SimpleItemFuelManager(int defaultEnergy) {

        super(defaultEnergy);
    }

    public boolean validFuel(ItemStack input) {

        return getFuel(input) != null;
    }

    public IDynamoFuel getFuel(ItemStack input) {

        return getFuel(Collections.singletonList(new ItemStackHolder(input)), Collections.emptyList());
    }

    public IDynamoFuel removeFuel(ItemStack input) {

        return fuelMap.remove(convert(input));
    }

    public IDynamoFuel addFuel(ItemStack input) {

        return addFuel(defaultEnergy, input);
    }

    public IDynamoFuel addFuel(int energy, ItemStack input) {

        if (input.isEmpty() || energy < MIN_ENERGY || energy > MAX_ENERGY || validFuel(input)) {
            return null;
        }
        energy = (energy * getDefaultScale()) / 100;

        SimpleItemFuel fuel = new SimpleItemFuel(input, energy);
        fuelMap.put(convert(input), fuel);
        return fuel;
    }

    // region IFuelManager
    @Override
    public IDynamoFuel getFuel(IThermalInventory inventory) {

        return getFuel(inventory.inputSlots(), inventory.inputTanks());
    }

    public IDynamoFuel getFuel(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        if (inputSlots.isEmpty() || inputSlots.get(0).isEmpty()) {
            return null;
        }
        return fuelMap.get(convert(inputSlots.get(0).getItemStack()));
    }

    @Override
    public List<IDynamoFuel> getFuelList() {

        return new ArrayList<>(fuelMap.values());
    }
    // endregion

    // region IManager
    @Override
    public void refresh() {

    }
    // endregion
}
