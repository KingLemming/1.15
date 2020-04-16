package cofh.lib.util.control;

import cofh.core.network.packet.server.RedstoneControlPacket;
import cofh.lib.util.Utils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

import static cofh.lib.util.constants.NBTTags.*;

public class RedstoneControlModule implements IRedstoneControllable {

    protected IRedstoneControllableTile tile;
    protected boolean enabled;

    protected int power;
    protected int threshold;
    protected ControlMode mode = ControlMode.DISABLED;

    public RedstoneControlModule(IRedstoneControllableTile tile) {

        this(tile, true);
    }

    public RedstoneControlModule(IRedstoneControllableTile tile, boolean enabled) {

        this.tile = tile;
        this.enabled = enabled;
    }

    public RedstoneControlModule setEnabled(boolean enabled) {

        this.enabled = enabled;
        return this;
    }

    // region NETWORK
    public void readFromBuffer(PacketBuffer buffer) {

        power = buffer.readByte();
        threshold = buffer.readByte();
        mode = ControlMode.VALUES[buffer.readByte()];
    }

    public void writeToBuffer(PacketBuffer buffer) {

        buffer.writeByte(power);
        buffer.writeByte(threshold);
        buffer.writeByte(mode.ordinal());
    }
    // endregion

    // region NBT
    public RedstoneControlModule read(CompoundNBT nbt) {

        power = nbt.getByte(TAG_RS_POWER);
        threshold = nbt.getByte(TAG_RS_THRESHOLD);
        mode = !isControllable() ? ControlMode.DISABLED : ControlMode.VALUES[nbt.getByte(TAG_RS_MODE)];

        return this;
    }

    public CompoundNBT write(CompoundNBT nbt) {

        if (enabled) {
            nbt.putByte(TAG_RS_POWER, (byte) power);
            nbt.putByte(TAG_RS_THRESHOLD, (byte) threshold);
            nbt.putByte(TAG_RS_MODE, (byte) mode.ordinal());
        }
        return nbt;
    }
    // endregion

    // region IRedstoneControl
    @Override
    public boolean isControllable() {

        return enabled;
    }

    @Override
    public int getPower() {

        return power;
    }

    @Override
    public int getThreshold() {

        return threshold;
    }

    @Override
    public ControlMode getMode() {

        return mode;
    }

    @Override
    public void setPower(int power) {

        boolean prevState = getState();
        this.power = power;
        if (prevState != getState() && Utils.isClientWorld(tile.world())) {
            tile.callBlockUpdate();
        }
    }

    @Override
    public void setControl(int threshold, ControlMode mode) {

        int curThreshold = this.threshold;
        ControlMode curMode = this.mode;
        this.threshold = threshold;
        this.mode = mode;

        if (Utils.isClientWorld(tile.world())) {
            RedstoneControlPacket.sendToServer(this.tile);
            this.threshold = curThreshold;
            this.mode = curMode;
        } else {
            tile.onControlUpdate();
        }
    }
    // endregion
}
