package cofh.thermal.expansion.tileentity.device;

import cofh.core.fluid.FluidStorageCoFH;
import cofh.core.util.helpers.FluidHelper;
import cofh.core.util.helpers.MathHelper;
import cofh.thermal.core.tileentity.ReconfigurableTile4Way;
import cofh.thermal.expansion.inventory.container.device.DeviceFluidBufferContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;

import javax.annotation.Nullable;

import static cofh.core.util.StorageGroup.ACCESSIBLE;
import static cofh.core.util.constants.Constants.*;
import static cofh.core.util.constants.NBTTags.TAG_AMOUNT_IN;
import static cofh.core.util.constants.NBTTags.TAG_AMOUNT_OUT;
import static cofh.thermal.expansion.init.TExpReferences.DEVICE_FLUID_BUFFER_TILE;

public class DeviceFluidBufferTile extends ReconfigurableTile4Way implements ITickableTileEntity {

    public static int XFER_MAX = TANK_MEDIUM;

    public int amountInput = BUCKET_VOLUME;
    public int amountOutput = BUCKET_VOLUME;

    public DeviceFluidBufferTile() {

        super(DEVICE_FLUID_BUFFER_TILE);

        tankInv.addTank(new FluidStorageCoFH(TANK_MEDIUM), ACCESSIBLE);
        tankInv.addTank(new FluidStorageCoFH(TANK_MEDIUM), ACCESSIBLE);
        tankInv.addTank(new FluidStorageCoFH(TANK_MEDIUM), ACCESSIBLE);

        initHandlers();
    }

    @Override
    public void tick() {

        boolean curActive = isActive;

        if (isActive) {
            if (timeCheckHalf()) {
                transferOutput();
                transferInput();
            }
            if (!redstoneControl.getState()) {
                isActive = false;
            }
        } else if (redstoneControl.getState()) {
            isActive = true;
        }
        updateActiveState(curActive);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DeviceFluidBufferContainer(i, world, pos, inventory, player);
    }

    @Override
    protected void transferInput() {

        if (!transferControl.getTransferIn()) {
            return;
        }
        int newTracker = inputTracker;
        boolean updateTracker = false;

        for (int i = inputTracker + 1; i <= inputTracker + 6; ++i) {
            Direction side = DIRECTIONS[i % 6];
            if (reconfigControl.getSideConfig(side).isInput()) {
                for (FluidStorageCoFH tank : inputTanks()) {
                    if (tank.getSpace() > 0) {
                        if (FluidHelper.extractFromAdjacent(this, tank, Math.min(getInputFluidAmount(), tank.getSpace()), side)) {
                            break;
                        }
                    }
                }
                if (!updateTracker) {
                    newTracker = side.ordinal();
                    updateTracker = true;
                }
            }
        }
        inputTracker = newTracker;
    }

    @Override
    protected void transferOutput() {

        if (!transferControl.getTransferOut()) {
            return;
        }
        int newTracker = outputTracker;
        boolean updateTracker = false;

        for (int i = outputTracker + 1; i <= outputTracker + 6; ++i) {
            Direction side = DIRECTIONS[i % 6];
            if (reconfigControl.getSideConfig(side).isOutput()) {
                for (int j = outputTanks().size(); j-- > 0; ) {
                    if (FluidHelper.insertIntoAdjacent(this, outputTanks().get(j), getOutputFluidAmount(), side)) {
                        break;
                    }
                }
                if (!updateTracker) {
                    newTracker = side.ordinal();
                    updateTracker = true;
                }
            }
        }
        outputTracker = newTracker;
    }

    @Override
    protected int getInputFluidAmount() {

        return amountInput;
    }

    @Override
    protected int getOutputFluidAmount() {

        return amountOutput;
    }

    @Override
    public boolean clearTank(int tank) {

        if (tank >= tankInv.getTanks()) {
            return false;
        }
        return tankInv.getTank(tank).clear();
    }

    // region NETWORK
    @Override
    public PacketBuffer getConfigPacket(PacketBuffer buffer) {

        super.getConfigPacket(buffer);

        buffer.writeInt(amountInput);
        buffer.writeInt(amountOutput);

        return buffer;
    }

    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeInt(amountInput);
        buffer.writeInt(amountOutput);

        return buffer;
    }

    @Override
    public void handleConfigPacket(PacketBuffer buffer) {

        super.handleConfigPacket(buffer);

        amountInput = MathHelper.clamp(buffer.readInt(), 0, XFER_MAX);
        amountOutput = MathHelper.clamp(buffer.readInt(), 0, XFER_MAX);
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        amountInput = buffer.readInt();
        amountOutput = buffer.readInt();
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        amountInput = nbt.getInt(TAG_AMOUNT_IN);
        amountOutput = nbt.getInt(TAG_AMOUNT_OUT);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putInt(TAG_AMOUNT_IN, amountInput);
        nbt.putInt(TAG_AMOUNT_OUT, amountOutput);

        return nbt;
    }
    // endregion

}
