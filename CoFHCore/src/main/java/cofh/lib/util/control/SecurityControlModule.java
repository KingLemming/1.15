package cofh.lib.util.control;

import cofh.lib.util.Utils;
import cofh.lib.util.helpers.SecurityHelper;
import com.mojang.authlib.GameProfile;
import net.minecraft.nbt.CompoundNBT;

import java.util.UUID;

import static cofh.lib.util.constants.Tags.*;

public class SecurityControlModule implements ISecurable {

    protected ISecurableTile tile;
    protected boolean enabled;

    protected GameProfile owner = SecurityHelper.DEFAULT_GAME_PROFILE;
    protected AccessMode access = AccessMode.PUBLIC;

    public SecurityControlModule(ISecurableTile tile) {

        this(tile, true);
    }

    public SecurityControlModule(ISecurableTile tile, boolean enabled) {

        this.tile = tile;
        this.enabled = enabled;
    }

    // TODO: Fix
    //	// region NETWORK
    //	public void readFromBuffer(PacketBufferCoFH buffer) {
    //
    //		access = AccessMode.VALUES[buffer.readByte()];
    //		owner = SecurityHelper.DEFAULT_GAME_PROFILE;
    //		setOwner(new GameProfile(buffer.readUniqueId(), buffer.readString(1024)));
    //	}
    //
    //	public void writeToBuffer(PacketBufferCoFH buffer) {
    //
    //		buffer.writeByte(access.ordinal());
    //		buffer.writeUniqueId(owner.getId());
    //		buffer.writeString(owner.getName());
    //	}
    //	// endregion

    // region NBT
    public SecurityControlModule read(CompoundNBT nbt) {

        if (nbt.contains(TAG_OWNER_UUID)) {
            String uuid = nbt.getString(TAG_OWNER_UUID);
            String name = nbt.getString(TAG_OWNER_NAME);
            owner = new GameProfile(UUID.fromString(uuid), name);
        } else {
            owner = SecurityHelper.DEFAULT_GAME_PROFILE;
        }
        access = isSecurable() ? AccessMode.VALUES[nbt.getByte(TAG_ACCESS)] : AccessMode.PUBLIC;

        return this;
    }

    public CompoundNBT write(CompoundNBT nbt) {

        nbt.putString(TAG_OWNER_UUID, owner.getId().toString());
        nbt.putString(TAG_OWNER_NAME, owner.getName());
        nbt.putByte(TAG_ACCESS, (byte) access.ordinal());

        return nbt;
    }
    // endregion

    // region ISecurable
    @Override
    public boolean isSecurable() {

        return enabled;
    }

    @Override
    public AccessMode getAccess() {

        return access;
    }

    @Override
    public GameProfile getOwner() {

        return owner;
    }

    @Override
    public void setAccess(AccessMode access) {

        this.access = access;
        if (Utils.isClientWorld(tile.world())) {
            // TODO: Fix
            // PacketSecurityControl.sendToServer(tile);
        } else {
            tile.onControlUpdate();
        }
    }

    @Override
    public boolean setOwner(GameProfile profile) {

        if (SecurityHelper.isDefaultProfile(owner) && !SecurityHelper.isDefaultProfile(profile)) {
            owner = profile;
            if (Utils.isServerWorld(tile.world())) {
                tile.onControlUpdate();
            }
            return true;
        }
        return false;
    }
    // endregion
}
