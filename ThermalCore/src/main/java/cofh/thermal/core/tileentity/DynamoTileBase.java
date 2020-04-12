package cofh.thermal.core.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.fluids.FluidStack;

import static cofh.lib.util.constants.NBTTags.TAG_COOLANT;
import static cofh.lib.util.constants.NBTTags.TAG_FUEL;

public abstract class DynamoTileBase extends ThermalTileBase implements ITickableTileEntity {

    protected Direction facing;
    protected FluidStack renderFluid = FluidStack.EMPTY.copy();

    protected int fuel;
    protected int coolant;

    protected int energyGen = 40;

    public DynamoTileBase(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    @Override
    public void tick() {

        boolean curActive = isActive;

        if (isActive) {
            processTick();
            if (canProcessFinish()) {
                processFinish();
                if (!redstoneControl.getState() || !canProcessStart()) {
                    processOff();
                } else {
                    processStart();
                }
            }
        } else if (redstoneControl.getState()) {
            if (timeCheck() && canProcessStart()) {
                processStart();
                processTick();
                isActive = true;
            }
        }
        updateActiveState(curActive);
    }

    protected void transferEnergy(int energy) {

        // EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(this, getFacing(), energy, false);
    }

    // region PROCESS
    protected abstract boolean canProcessStart();
    //    {
    //        return false;
    //    }

    protected boolean canProcessFinish() {

        return fuel <= 0;
    }

    protected abstract void processStart();
    //    {
    //        if (cacheRenderFluid()) {
    //            TileStatePacket.sendToClient(this);
    //        }
    //    }

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
        int energy = Math.min(fuel, energyGen);
        fuel -= energy;
        transferEnergy(energy);
        return energy;
    }
    // endregion

    // region HELPERS
    protected boolean cacheRenderFluid() {

        return false;
    }

    public FluidStack getRenderFluid() {

        return renderFluid;
    }
    // endregion

    // region NETWORK
    @Override
    public PacketBuffer getControlPacket(PacketBuffer buffer) {

        super.getControlPacket(buffer);

        buffer.writeFluidStack(renderFluid);

        return buffer;
    }

    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeFluidStack(renderFluid);

        return buffer;
    }

    @Override
    public PacketBuffer getStatePacket(PacketBuffer buffer) {

        super.getControlPacket(buffer);

        buffer.writeFluidStack(renderFluid);

        return buffer;
    }

    @Override
    public void handleControlPacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        renderFluid = buffer.readFluidStack();
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        renderFluid = buffer.readFluidStack();
    }

    @Override
    public void handleStatePacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        renderFluid = buffer.readFluidStack();
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
