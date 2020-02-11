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

import java.util.List;
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

    public static boolean canPlayerAccess(ItemStack stack, PlayerEntity player) {

        return !hasSecurity(stack) || getAccess(stack).matches(getOwner(stack), player);
    }

    /**
     * Adds security information to an NBT tag, based on a tile.
     */
    public static CompoundNBT setItemStackTagSecurity(CompoundNBT tag, ISecurable tile) {

        if (tile == null) {
            return tag;
        }
        if (tag == null) {
            tag = new CompoundNBT();
        }
        CompoundNBT secureTag = new CompoundNBT();
        secureTag.putByte(TAG_ACCESS, (byte) tile.getAccess().ordinal());
        secureTag.putString(TAG_OWNER_UUID, tile.getOwner().getId().toString());
        secureTag.putString(TAG_OWNER_NAME, tile.getOwner().getName());
        tag.put(TAG_SECURITY, secureTag);
        return tag;
    }

    /**
     * Adds Access information to Tooltips.
     */
    public static void addAccessInformation(ItemStack stack, List<String> tooltip) {

        if (hasSecurity(stack)) {
            String accessString = "";
            switch (AccessMode.VALUES[stack.getChildTag(TAG_SECURITY).getByte(TAG_ACCESS)]) {
                case PUBLIC:
                    accessString = localize("info.cofh.access_public");
                    break;
                case PRIVATE:
                    accessString = localize("info.cofh.access_private");
                    break;
                case FRIENDS:
                    accessString = localize("info.cofh.access_friends");
                    break;
                case TEAM:
                    accessString = localize("info.cofh.access_team");
                    break;
            }
            tooltip.add(localize("info.cofh.access") + ": " + accessString);
        }
    }

    /**
     * Adds Owner information to Tooltips.
     */
    public static void addOwnerInformation(ItemStack stack, List<String> tooltip) {

        if (hasSecurity(stack)) {
            CompoundNBT secureTag = stack.getChildTag(TAG_SECURITY);
            boolean hasUUID = secureTag.contains(TAG_OWNER_UUID);
            if (!secureTag.contains(TAG_OWNER_NAME) && !hasUUID) {
                tooltip.add(localize("info.cofh.owner") + ": " + localize("info.cofh.none"));
            } else {
                if (hasUUID && secureTag.contains(TAG_OWNER_NAME)) {
                    tooltip.add(localize("info.cofh.owner") + ": " + secureTag.getString(TAG_OWNER_NAME) + " \u0378");
                } else {
                    tooltip.add(localize("info.cofh.owner") + ": " + localize("info.cofh.another_player"));
                }
            }
        }
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
    public static boolean hasSecurity(ItemStack stack) {

        return stack.getChildTag(TAG_SECURITY) != null;
    }

    public static ItemStack addSecurity(ItemStack stack) {

        if (hasSecurity(stack)) {
            return stack;
        }
        CompoundNBT secureTag = new CompoundNBT();
        secureTag.putByte(TAG_ACCESS, (byte) 0);
        stack.setTagInfo(TAG_SECURITY, secureTag);
        return stack;
    }

    public static ItemStack removeSecurity(ItemStack stack) {

        if (!hasSecurity(stack)) {
            return stack;
        }
        stack.removeChildTag(TAG_SECURITY);
        return stack;
    }

    public static void setAccess(ItemStack stack, AccessMode access) {

        if (!hasSecurity(stack)) {
            return;
        }
        CompoundNBT secureTag = stack.getChildTag(TAG_SECURITY);
        secureTag.putByte(TAG_ACCESS, (byte) access.ordinal());
    }

    public static void setOwner(ItemStack stack, GameProfile profile) {

        if (!hasSecurity(stack)) {
            return;
        }
        CompoundNBT secureTag = stack.getChildTag(TAG_SECURITY);
        secureTag.putString(TAG_OWNER_UUID, profile.getId().toString());
        secureTag.putString(TAG_OWNER_NAME, profile.getName());
    }

    public static AccessMode getAccess(ItemStack stack) {

        return !hasSecurity(stack) ? AccessMode.PUBLIC : AccessMode.VALUES[stack.getChildTag(TAG_SECURITY).getByte(TAG_ACCESS)];
    }

    public static GameProfile getOwner(ItemStack stack) {

        if (hasSecurity(stack)) {
            CompoundNBT secureTag = stack.getChildTag(TAG_SECURITY);
            String uuid = secureTag.getString(TAG_OWNER_UUID);
            String name = secureTag.getString(TAG_OWNER_NAME);
            if (!Strings.isNullOrEmpty(uuid)) {
                return new GameProfile(UUID.fromString(uuid), name);
            } else if (!Strings.isNullOrEmpty(name)) {
                return new GameProfile(UUID.fromString(PreYggdrasilConverter.convertMobOwnerIfNeeded(ServerLifecycleHooks.getCurrentServer(), name)), name);
            }
        }
        return DEFAULT_GAME_PROFILE;
    }

    public static GameProfile getProfile(UUID uuid, String name) {

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        GameProfile owner = server.getPlayerProfileCache().getProfileByUUID(uuid);
        if (owner == null) {
            GameProfile temp = new GameProfile(uuid, name);
            owner = server.getMinecraftSessionService().fillProfileProperties(temp, true);
            if (owner != temp) {
                server.getPlayerProfileCache().addEntry(owner);
            }
        }
        return owner;
    }

    public static String getOwnerName(ItemStack stack) {

        CompoundNBT secureTag = stack.getChildTag(TAG_SECURITY);
        boolean hasUUID;
        if (secureTag == null || (!(hasUUID = secureTag.contains(TAG_OWNER_UUID)) && !secureTag.contains(TAG_OWNER_NAME))) {
            return DEFAULT_GAME_PROFILE.getName();
        }
        return hasUUID ? secureTag.getString(TAG_OWNER_NAME) : localize("info.cofh.another_player");
    }
    // endregion
}
