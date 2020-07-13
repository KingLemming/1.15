package cofh.core.util;

import com.mojang.authlib.GameProfile;

import java.nio.file.Path;
import java.util.*;

public class SocialUtils {

    // TODO: This needs a way to save.

    private SocialUtils() {

    }

    public static final Map<String, Set<String>> FRIENDS = new TreeMap<>();
    public static final Map<String, Set<String>> TEAMS = new TreeMap<>();

    public static final List<String> CLIENT_FRIENDS = new LinkedList<>();
    public static final List<String> CLIENT_TEAMS = new LinkedList<>();

    private static Path friendFile;
    private static Path teamFile;

    // region FRIENDS
    public synchronized static boolean addFriend(GameProfile player, String friendName) {

        if (player == null || friendName == null) {
            return false;
        }
        String playerUUID = player.getId().toString();
        friendName = friendName.toLowerCase(Locale.US);
        Set<String> set = FRIENDS.get(playerUUID);

        if (set != null) {
            set.add(friendName);
        } else {
            set = new HashSet<>();
            set.add(friendName);
            FRIENDS.put(playerUUID, set);
        }
        return true;
    }

    public synchronized static boolean removeFriend(GameProfile player, String friendName) {

        if (player == null || friendName == null) {
            return false;
        }
        String playerUUID = player.getId().toString();
        friendName = friendName.toLowerCase(Locale.US);
        Set<String> set = FRIENDS.get(playerUUID);

        return set != null && set.remove(friendName);
    }

    public static boolean isFriendOrSelf(GameProfile player, String friendName) {

        if (player == null || friendName == null) {
            return false;
        }
        if (friendName.equals(player.getName())) {
            return true;
        }
        String playerUUID = player.getId().toString();
        friendName = friendName.toLowerCase(Locale.US);
        Set<String> set = FRIENDS.get(playerUUID);

        return set != null && set.contains(friendName);
    }
    // endregion

    // region TEAMS
    public synchronized static boolean createTeam(String teamName) {

        if (teamName == null || teamName.isEmpty()) {
            return false;
        }
        if (TEAMS.containsKey(teamName)) {
            return false;
        } else {
            TEAMS.put(teamName, new HashSet<>());
        }
        return true;
    }
    // endregion
}
