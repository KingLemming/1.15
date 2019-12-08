package cofh.thermal.core.util.managers;

import cofh.lib.fluid.FluidStackHolder;
import cofh.lib.fluid.IFluidStackAccess;
import cofh.lib.inventory.IItemStackAccess;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.recipes.IDynamoFuel;
import cofh.thermal.core.util.recipes.SimpleFluidFuel;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Basic fuel manager - single fluid key'd.
 */
public abstract class SimpleFluidFuelManager extends AbstractManager implements IFuelManager {

    public static final int MIN_ENERGY = 10000;
    public static final int MAX_ENERGY = 200000000;

    public static final int FLUID_FUEL_AMOUNT = 100;
    public static final int ENERGY_FACTOR = FluidAttributes.BUCKET_VOLUME / FLUID_FUEL_AMOUNT;

    protected Map<Integer, IDynamoFuel> fuelMap = new Object2ObjectOpenHashMap<>();

    protected SimpleFluidFuelManager(int defaultEnergy) {

        super(defaultEnergy);
    }

    public boolean validFuel(FluidStack input) {

        return getFuel(input) != null;
    }

    public IDynamoFuel getFuel(FluidStack input) {

        return getFuel(Collections.emptyList(), Collections.singletonList(new FluidStackHolder(input)));
    }

    public IDynamoFuel removeFuel(FluidStack input) {

        return fuelMap.remove(FluidHelper.fluidHashcode(input));
    }

    public IDynamoFuel addFuel(FluidStack input) {

        return addFuel(defaultEnergy, input);
    }

    public IDynamoFuel addFuel(int energy, FluidStack input) {

        if (input.isEmpty() || fuelMap.containsKey(FluidHelper.fluidHashcode(input))) {
            return null;
        }
        int amount = input.getAmount();
        if (amount != FLUID_FUEL_AMOUNT) {
            if (amount != FluidAttributes.BUCKET_VOLUME) {
                long normEnergy = energy * FluidAttributes.BUCKET_VOLUME / amount;
                input.setAmount(FLUID_FUEL_AMOUNT);
                energy = (int) normEnergy;
            }
            energy /= ENERGY_FACTOR;
        }
        if (energy < MIN_ENERGY || energy > MAX_ENERGY) {
            return null;
        }
        SimpleFluidFuel fuel = new SimpleFluidFuel(input, energy);
        fuelMap.put(FluidHelper.fluidHashcode(input), fuel);
        return fuel;
    }

    // region IFuelManager
    @Override
    public IDynamoFuel getFuel(IThermalInventory inventory) {

        return getFuel(inventory.inputSlots(), inventory.inputTanks());
    }

    public IDynamoFuel getFuel(List<? extends IItemStackAccess> inputSlots, List<? extends IFluidStackAccess> inputTanks) {

        if (inputTanks.isEmpty() || inputTanks.get(0).isEmpty()) {
            return null;
        }
        return fuelMap.get(FluidHelper.fluidHashcode(inputTanks.get(0).getFluidStack()));
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
