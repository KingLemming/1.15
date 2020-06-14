package cofh.lib.energy;

import cofh.lib.util.IResourceStorage;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;

import static cofh.lib.util.constants.NBTTags.TAG_ENERGY;

/**
 * Implementation of an Energy Storage object. See {@link IEnergyStorage}.
 *
 * @author King Lemming
 */
public class EnergyStorageCoFH implements IEnergyStorage, IResourceStorage, INBTSerializable<CompoundNBT> {

    protected int energy;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public EnergyStorageCoFH(int capacity) {

        this(capacity, capacity, capacity, 0);
    }

    public EnergyStorageCoFH(int capacity, int maxTransfer) {

        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public EnergyStorageCoFH(int capacity, int maxReceive, int maxExtract) {

        this(capacity, maxReceive, maxExtract, 0);
    }

    public EnergyStorageCoFH(int capacity, int maxReceive, int maxExtract, int energy) {

        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = Math.max(0, Math.min(capacity, energy));
    }

    public EnergyStorageCoFH setParams(int capacity, int maxReceive, int maxExtract) {

        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = Math.max(0, Math.min(capacity, energy));
        return this;
    }

    public EnergyStorageCoFH setCapacity(int capacity) {

        this.capacity = capacity;
        this.energy = Math.max(0, Math.min(capacity, energy));
        return this;
    }

    public EnergyStorageCoFH setMaxReceive(int maxReceive) {

        this.maxReceive = maxReceive;
        return this;
    }

    public EnergyStorageCoFH setMaxExtract(int maxExtract) {

        this.maxExtract = maxExtract;
        return this;
    }

    public void setEnergyStored(int amount) {

        energy = amount;
        energy = Math.max(0, Math.min(capacity, energy));
    }

    public int getMaxReceive() {

        return maxReceive;
    }

    public int getMaxExtract() {

        return maxExtract;
    }

    public int receiveEnergyOverride(int maxReceive, boolean simulate) {

        int energyReceived = Math.min(capacity - energy, maxReceive);
        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    public int extractEnergyOverride(int maxExtract, boolean simulate) {

        int energyExtracted = Math.min(energy, maxExtract);
        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }

    // region NBT
    public EnergyStorageCoFH readFromNBT(CompoundNBT nbt) {

        this.energy = nbt.getInt(TAG_ENERGY);
        if (energy > capacity) {
            energy = capacity;
        }
        return this;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt) {

        if (energy < 0) {
            energy = 0;
        }
        nbt.putInt(TAG_ENERGY, energy);
        return nbt;
    }

    @Override
    public CompoundNBT serializeNBT() {

        return writeToNBT(new CompoundNBT());
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

        readFromNBT(nbt);
    }
    // endregion

    // region IEnergyStorage
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {

        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {

        return energy;
    }

    @Override
    public int getMaxEnergyStored() {

        return capacity;
    }

    @Override
    public boolean canExtract() {

        return maxExtract > 0;
    }

    @Override
    public boolean canReceive() {

        return maxReceive > 0;
    }
    // endregion

    // region IResourceStorage
    @Override
    public void modify(int amount) {

        energy += amount;
        if (energy > capacity) {
            energy = capacity;
        } else if (energy < 0) {
            energy = 0;
        }
    }

    @Override
    public boolean isEmpty() {

        return energy <= 0 && capacity > 0;
    }

    @Override
    public int getCapacity() {

        return getMaxEnergyStored();
    }

    @Override
    public int getStored() {

        return getEnergyStored();
    }

    @Override
    public String getUnit() {

        return "RF";
    }
    // endregion
}
