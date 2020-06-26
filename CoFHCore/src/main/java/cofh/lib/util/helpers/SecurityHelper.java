package cofh.lib.util.helpers;

import cofh.lib.util.control.ISecurable;
import cofh.lib.util.control.ISecurable.AccessMode;
import com.google.common.base.Strings;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.UUID;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.StringHelper.localize;

public class SecurityHelper {

    public static final GameProfile DEFAULT_GAME_PROFILE = new GameProfile(UUID.fromString("1ef1a6f0-87bc-4e78-0a0b-c6824eb787ea"), "[None]");

    private static UUID cachedId;

    private SecurityHelper() {

    }

    public static boolean isDefaultUUID(UUID uuid) {

        return uuid == null || (uuid.version() == 4 && uuid.variant() == 0);
    }

    public static boolean isDefaultProfile(GameProfile profile) {

        return DEFAULT_GAME_PROFILE.equals(profile);
    }

    public static UUID getID(PlayerEntity player) {

        if (player == null) {
            return DEFAULT_GAME_PROFILE.getId();
        }
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null && server.isServerRunning()) {
            return player.getGameProfile().getId();
        }
        return getClientID(player);
    }

    private static UUID getClientID(PlayerEntity player) {

        if (player != Minecraft.getInstance().player) {
            return player.getGameProfile().getId();
        }
        if (cachedId == null) {
            cachedId = Minecraft.getInstance().player.getGameProfile().getId();
        }
        return cachedId;
    }

    // region TILE HELPERS
    public static boolean hasSecurity(TileEntity tile) {

        if (tile instanceof ISecurable) {
            return !isDefaultProfile(((ISecurable) tile).getOwner());
        }
        return false;
    }

    public static String getOwnerName(TileEntity tile) {

        if (hasSecurity(tile)) {
            return ((ISecurable) tile).getOwnerName();
        }
        return DEFAULT_GAME_PROFILE.getName();
    }
    // endregion

    // region ITEM HELPERS
    public static CompoundNBT getSecurityTag(ItemStack stack) {

        CompoundNBT blockTag = stack.getChildTag(TAG_BLOCK_ENTITY);
        if (blockTag != null) {
            return blockTag.contains(TAG_SECURITY) ? blockTag.getCompound(TAG_SECURITY) : null;
        }
        return stack.getChildTag(TAG_SECURITY);
    }

    public static boolean hasSecurity(ItemStack stack) {

        return getSecurityTag(stack) != null;
    }

    public static void setAccess(ItemStack stack, AccessMode access) {

        CompoundNBT secureTag = getSecurityTag(stack);
        if (secureTag != null) {
            secureTag.putByte(TAG_SEC_ACCESS, (byte) access.ordinal());
        }
    }

    public static void setOwner(ItemStack stack, GameProfile profile) {

        CompoundNBT secureTag = getSecurityTag(stack);
        if (secureTag != null) {
            secureTag.putString(TAG_SEC_OWNER_UUID, profile.getId().toString());
            secureTag.putString(TAG_SEC_OWNER_NAME, profile.getName());
        }
    }

    public static AccessMode getAccess(ItemStack stack) {

        CompoundNBT secureTag = getSecurityTag(stack);
        if (secureTag != null) {
            return AccessMode.VALUES[secureTag.getByte(TAG_SEC_ACCESS)];
        }
        return AccessMode.PUBLIC;
    }

    public static GameProfile getOwner(ItemStack stack) {

        CompoundNBT secureTag = getSecurityTag(stack);
        if (secureTag != null) {
            String uuid = secureTag.getString(TAG_SEC_OWNER_UUID);
            String name = secureTag.getString(TAG_SEC_OWNER_NAME);
            if (!Strings.isNullOrEmpty(uuid)) {
                return new GameProfile(UUID.fromString(uuid), name);
            } else if (!Strings.isNullOrEmpty(name)) {
                return new GameProfile(UUID.fromString(PreYggdrasilConverter.convertMobOwnerIfNeeded(ServerLifecycleHooks.getCurrentServer(), name)), name);
            }
        }
        return DEFAULT_GAME_PROFILE;
    }

    public static String getOwnerName(ItemStack stack) {

        CompoundNBT secureTag = getSecurityTag(stack);
        if (secureTag != null) {
            return secureTag.getString(TAG_SEC_OWNER_NAME);
        }
        return localize("info.cofh.another_player");
    }
    // endregion
}
