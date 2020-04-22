package cofh.lib.util.control;

import cofh.core.network.packet.server.SideConfigPacket;
import cofh.lib.util.Utils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;

import static cofh.lib.util.constants.NBTTags.TAG_SIDES;
import static cofh.lib.util.control.IReconfigurable.SideConfig.SIDE_ACCESSIBLE;
import static cofh.lib.util.control.IReconfigurable.SideConfig.SIDE_NONE;

public class ReconfigControlModule implements IReconfigurable {

    protected IReconfigurableTile tile;
    protected boolean enabled;

    protected SideConfig[] sides = {SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE};

    public ReconfigControlModule(IReconfigurableTile tile) {

        this(tile, true);
    }

    public ReconfigControlModule(IReconfigurableTile tile, boolean enabled) {

        this.tile = tile;
        this.enabled = enabled;
    }

    public ReconfigControlModule setEnabled(IReconfigurableTile tile, boolean enabled) {

        this.enabled = enabled;
        return this;
    }

    public SideConfig[] getSideConfig() {

        return sides;
    }

    public void initSideConfig(Direction side, SideConfig config) {

        if (side == null || config == null) {
            return;
        }
        this.sides[side.ordinal()] = config;
    }

    public void initSideConfig(SideConfig[] sides) {

        if (sides == null || sides.length != 6) {
            return;
        }
        this.sides = sides;
    }

    public void setSideConfig(SideConfig[] sides) {

        this.sides = sides;
        tile.onControlUpdate();
    }

    // region NETWORK
    public void readFromBuffer(PacketBuffer buffer) {

        for (int i = 0; i < 6; ++i) {
            sides[i] = SideConfig.VALUES[buffer.readByte()];
        }
    }

    public void writeToBuffer(PacketBuffer buffer) {

        for (int i = 0; i < 6; ++i) {
            buffer.writeByte(sides[i].ordinal());
        }
    }
    // endregion

    // region NBT
    public ReconfigControlModule read(CompoundNBT nbt) {

        byte[] bSides = nbt.getByteArray(TAG_SIDES);
        if (bSides.length == 6) {
            for (int i = 0; i < 6; ++i) {
                if (bSides[i] > SideConfig.VALUES.length) {
                    bSides[i] = 0;
                }
                sides[i] = SideConfig.VALUES[bSides[i]];
            }
        }
        return this;
    }

    public CompoundNBT write(CompoundNBT nbt) {

        byte[] bSides = new byte[6];
        for (int i = 0; i < 6; ++i) {
            bSides[i] = (byte) sides[i].ordinal();
        }
        nbt.putByteArray(TAG_SIDES, bSides);
        return nbt;
    }
    // endregion

    public SideConfig getSideConfig(int side) {

        if (side > 5) {
            return SIDE_ACCESSIBLE;
        }
        return sides[side];
    }

    // region IReconfigurable
    @Override
    public boolean isReconfigurable() {

        return enabled;
    }

    @Override
    public SideConfig getSideConfig(Direction side) {

        if (side == null || !isReconfigurable()) {
            return SIDE_ACCESSIBLE;
        }
        return sides[side.ordinal()];
    }

    @Override
    public boolean prevSideConfig(Direction side) {

        if (!isReconfigurable() || side == null) {
            return false;
        }
        sides[side.ordinal()] = sides[side.ordinal()].prev();

        if (Utils.isClientWorld(tile.world())) {
            SideConfigPacket.sendToServer(tile);
        } else {
            tile.onControlUpdate();
        }
        return true;
    }

    @Override
    public boolean nextSideConfig(Direction side) {

        if (!isReconfigurable() || side == null) {
            return false;
        }
        sides[side.ordinal()] = sides[side.ordinal()].next();

        if (Utils.isClientWorld(tile.world())) {
            SideConfigPacket.sendToServer(tile);
        } else {
            tile.onControlUpdate();
        }
        return true;
    }

    @Override
    public boolean setSideConfig(Direction side, SideConfig config) {

        if (!isReconfigurable() || side == null || config == null) {
            return false;
        }
        sides[side.ordinal()] = config;

        if (Utils.isClientWorld(tile.world())) {
            SideConfigPacket.sendToServer(tile);
        } else {
            tile.onControlUpdate();
        }
        return true;
    }

    @Override
    public boolean clearAllSides() {

        sides = new SideConfig[]{SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE};

        if (Utils.isClientWorld(tile.world())) {
            SideConfigPacket.sendToServer(tile);
        } else {
            tile.onControlUpdate();
        }
        return true;
    }

    @Override
    public boolean hasInputSide() {

        if (!isReconfigurable()) {
            return false;
        }
        for (SideConfig side : sides) {
            if (side.isInput()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasOutputSide() {

        if (!isReconfigurable()) {
            return false;
        }
        for (SideConfig side : sides) {
            if (side.isOutput()) {
                return true;
            }
        }
        return false;
    }
    // endregion
}
