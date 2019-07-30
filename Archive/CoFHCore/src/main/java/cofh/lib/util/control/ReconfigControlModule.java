package cofh.lib.util.control;

import cofh.lib.util.Utils;
import net.minecraft.util.Direction;

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

    public SideConfig[] getSideConfig() {

        return sides;
    }

    public void setSideConfig(SideConfig[] sides) {

        this.sides = sides;
    }

    // TODO: Fix
    //	// region NETWORK
    //	public void readFromBuffer(PacketBufferCoFH buffer) {
    //
    //		for (int i = 0; i < 6; i++) {
    //			sides[i] = SideConfig.VALUES[buffer.readByte()];
    //		}
    //	}
    //
    //	public void writeToBuffer(PacketBufferCoFH buffer) {
    //
    //		for (int i = 0; i < 6; i++) {
    //			buffer.writeByte(sides[i].ordinal());
    //		}
    //	}
    //	// endregion
    //
    //	// region NBT
    //	public ReconfigControlModule readFromNBT(NBTTagCompound nbt) {
    //
    //		byte[] bSides = nbt.getByteArray(TAG_SIDES);
    //		if (bSides.length == 6) {
    //			for (int i = 0; i < 6; i++) {
    //				if (bSides[i] > SideConfig.VALUES.length) {
    //					bSides[i] = 0;
    //				}
    //				sides[i] = SideConfig.VALUES[bSides[i]];
    //			}
    //		}
    //		return this;
    //	}
    //
    //	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
    //
    //		byte[] bSides = new byte[6];
    //		for (int i = 0; i < 6; i++) {
    //			bSides[i] = (byte) sides[i].ordinal();
    //		}
    //		nbt.setByteArray(TAG_SIDES, bSides);
    //		return nbt;
    //	}
    //	// endregion

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

        if (side == null) {
            return SIDE_ACCESSIBLE;
        }
        return sides[side.ordinal()];
    }

    @Override
    public boolean prevSideConfig(Direction side) {

        if (side == null) {
            return false;
        }
        sides[side.ordinal()] = sides[side.ordinal()].prev();

        if (Utils.isClientWorld(tile.world())) {
            // TODO: Fix
            //PacketSideConfig.sendToServer(this.tile);
        } else {
            tile.onControlUpdate();
        }
        return true;
    }

    @Override
    public boolean nextSideConfig(Direction side) {

        if (side == null) {
            return false;
        }
        sides[side.ordinal()] = sides[side.ordinal()].next();

        if (Utils.isClientWorld(tile.world())) {
            // TODO: Fix
            //PacketSideConfig.sendToServer(this.tile);
        } else {
            tile.onControlUpdate();
        }
        return true;
    }

    @Override
    public boolean setSideConfig(Direction side, SideConfig config) {

        if (side == null || config == null) {
            return false;
        }
        sides[side.ordinal()] = config;

        if (Utils.isClientWorld(tile.world())) {
            // TODO: Fix
            //PacketSideConfig.sendToServer(this.tile);
        } else {
            tile.onControlUpdate();
        }
        return true;
    }

    @Override
    public boolean clearAllSides() {

        sides = new SideConfig[]{SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE};

        if (Utils.isClientWorld(tile.world())) {
            // TODO: Fix
            //PacketSideConfig.sendToServer(this.tile);
        } else {
            tile.onControlUpdate();
        }
        return true;
    }

    @Override
    public boolean hasInputSide() {

        for (SideConfig side : sides) {
            if (side.isInput()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasOutputSide() {

        for (SideConfig side : sides) {
            if (side.isOutput()) {
                return true;
            }
        }
        return false;
    }
    // endregion
}
