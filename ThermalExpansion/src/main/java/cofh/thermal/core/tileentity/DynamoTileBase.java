package cofh.thermal.core.tileentity;

import cofh.thermal.core.util.recipes.IDynamoFuel;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.fluids.FluidStack;

import static cofh.lib.util.constants.NBTTags.TAG_COOLANT;
import static cofh.lib.util.constants.NBTTags.TAG_FUEL;

public abstract class DynamoTileBase extends ThermalTileBase implements ITickableTileEntity {

    protected Direction facing;
    protected FluidStack renderFluid = FluidStack.EMPTY.copy();

    protected IDynamoFuel curFuel;

    protected int fuel;
    protected int coolant;

    protected int baseGen = 40;

    public DynamoTileBase(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    @Override
    public void tick() {

    }

    protected void transferEnergy(int energy) {

        // EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(this, getFacing(), energy, false);
    }

    // region PROCESS
    protected boolean canProcessStart() {

        return false;
    }

    protected boolean canProcessFinish() {

        return fuel <= 0;
    }

    protected void processStart() {

    }

    protected void processFinish() {

    }

    protected void processOff() {

        isActive = false;
        wasActive = true;
        if (world != null) {
            timeTracker.markTime(world);
        }
    }

    protected int processTick() {

        if (fuel <= 0) {
            return 0;
        }
        int energy = Math.min(fuel, baseGen);
        fuel -= energy;
        transferEnergy(energy);
        return energy;
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        fuel = nbt.getInt(TAG_FUEL);
        coolant = nbt.getInt(TAG_COOLANT);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putInt(TAG_FUEL, fuel);
        nbt.putInt(TAG_COOLANT, coolant);

        return nbt;
    }
    // endregion
}
