package cofh.core.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.storage.WorldSavedData;

import java.util.*;

import static cofh.lib.util.constants.NBTTags.TAG_NAME;
import static net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND;

public class SocialUtils {

    private SocialUtils() {

    }

    private static final String TAG_FRIENDS = "cofh:friends";
    private static final String TAG_TEAMS = "cofh:teams";

    private static FriendData friends(ServerPlayerEntity player) {

        return player.getServerWorld().getSavedData().getOrCreate(() -> new FriendData(TAG_FRIENDS), TAG_FRIENDS);
    }

    private static TeamData teams(ServerPlayerEntity player) {

        return player.getServerWorld().getSavedData().getOrCreate(() -> new TeamData(TAG_TEAMS), TAG_TEAMS);
    }

    // region FRIEND PASSTHROUGH
    public synchronized static boolean addFriend(ServerPlayerEntity player, String friendName) {

        return friends(player).addFriend(player, friendName);
    }

    public synchronized static boolean removeFriend(ServerPlayerEntity player, String friendName) {

        return friends(player).removeFriend(player, friendName);
    }

    public synchronized static boolean clearFriendList(ServerPlayerEntity player) {

        return friends(player).clearFriendList(player);
    }

    public synchronized static boolean clearAllFriendLists(ServerPlayerEntity player) {

        return friends(player).clearAllFriendLists(player);
    }

    public static boolean isFriendOrSelf(GameProfile owner, PlayerEntity friend) {

        if (friend instanceof ServerPlayerEntity) {
            return friends((ServerPlayerEntity) friend).isFriendOrSelf(owner, friend);
        }
        return false;
    }
    // endregion

    // region FRIEND DATA
    private static class FriendData extends WorldSavedData {

        private final Map<String, Set<String>> friendLists = new TreeMap<>();

        FriendData(String name) {

            super(name);
        }

        boolean addFriend(PlayerEntity player, String friendName) {

            if (player == null || friendName == null) {
                return false;
            }
            String playerUUID = player.getGameProfile().getId().toString();
            friendName = friendName.toLowerCase(Locale.US);
            Set<String> set = friendLists.get(playerUUID);

            if (set != null) {
                set.add(friendName);
            } else {
                set = new HashSet<>();
                set.add(friendName);
            }
            friendLists.put(playerUUID, set);
            this.markDirty();
            return true;
        }

        boolean removeFriend(PlayerEntity player, String friendName) {

            if (player == null || friendName == null) {
                return false;
            }
            String playerUUID = player.getGameProfile().getId().toString();
            friendName = friendName.toLowerCase(Locale.US);
            Set<String> set = friendLists.get(playerUUID);
            this.markDirty();
            return set != null && set.remove(friendName);
        }

        public boolean clearFriendList(PlayerEntity player) {

            if (player == null) {
                return false;
            }
            friendLists.remove(player.getGameProfile().getId().toString());
            this.markDirty();
            return true;
        }

        boolean clearAllFriendLists(PlayerEntity player) {

            if (!player.hasPermissionLevel(4)) {
                return false;
            }
            friendLists.clear();
            this.markDirty();
            return true;
        }

        boolean isFriendOrSelf(GameProfile owner, PlayerEntity friend) {

            if (owner == null || friend == null) {
                return false;
            }
            String friendName = friend.getGameProfile().getName();
            if (friendName.equals(owner.getName())) {
                return true;
            }
            String playerUUID = owner.getId().toString();
            friendName = friendName.toLowerCase(Locale.US);
            Set<String> set = friendLists.get(playerUUID);
            return set != null && set.contains(friendName);
        }

        @Override
        public void read(CompoundNBT nbt) {

            for (String player : nbt.keySet()) {
                ListNBT list = nbt.getList(player, TAG_COMPOUND);
                Set<String> friendList = new HashSet<>();
                for (int i = 0; i < list.size(); ++i) {
                    CompoundNBT friendInfo = list.getCompound(i);
                    friendList.add(friendInfo.getString(TAG_NAME));
                }
                friendLists.put(player, friendList);
            }
        }

        @Override
        public CompoundNBT write(CompoundNBT nbt) {

            for (Map.Entry<String, Set<String>> player : friendLists.entrySet()) {
                ListNBT friendList = new ListNBT();
                for (String friend : player.getValue()) {
                    CompoundNBT friendInfo = new CompoundNBT();
                    friendInfo.putString(TAG_NAME, friend);
                    friendList.add(friendInfo);
                }
                nbt.put(player.getKey(), friendList);
            }
            return nbt;
        }

    }
    // endregion

    // region TEAM DATA
    private static class TeamData extends WorldSavedData {

        TeamData(String name) {

            super(name);
        }

        @Override
        public void read(CompoundNBT nbt) {

        }

        @Override
        public CompoundNBT write(CompoundNBT nbt) {

            return nbt;
        }

    }
    // endregion
}
