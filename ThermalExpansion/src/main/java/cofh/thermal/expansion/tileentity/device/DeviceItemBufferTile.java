package cofh.thermal.expansion.tileentity.device;

import cofh.thermal.core.tileentity.ReconfigurableTileBase;
import cofh.thermal.expansion.inventory.container.device.DeviceItemBufferContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.ACCESSIBLE;
import static cofh.lib.util.constants.NBTTags.TAG_AMOUNT_IN;
import static cofh.lib.util.constants.NBTTags.TAG_AMOUNT_OUT;
import static cofh.thermal.expansion.init.TExpReferences.DEVICE_ITEM_BUFFER_TILE;

public class DeviceItemBufferTile extends ReconfigurableTileBase implements ITickableTileEntity {

    public int amountInput = 4;
    public int amountOutput = 4;

    public DeviceItemBufferTile() {

        super(DEVICE_ITEM_BUFFER_TILE);

        inventory.addSlots(ACCESSIBLE, 9);

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

        return new DeviceItemBufferContainer(i, world, pos, inventory, player);
    }

    @Override
    protected int getInputItemAmount() {

        return amountInput;
    }

    @Override
    protected int getOutputItemAmount() {

        return amountOutput;
    }

    // region NETWORK
    @Override
    public PacketBuffer getConfigPacket(PacketBuffer buffer) {

        super.getConfigPacket(buffer);

        buffer.writeByte(amountInput);
        buffer.writeByte(amountOutput);

        return buffer;
    }

    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeByte(amountInput);
        buffer.writeByte(amountOutput);

        return buffer;
    }

    @Override
    public void handleConfigPacket(PacketBuffer buffer) {

        super.handleConfigPacket(buffer);

        amountInput = buffer.readByte();
        amountOutput = buffer.readByte();
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        amountInput = buffer.readByte();
        amountOutput = buffer.readByte();
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
