package cofh.lib.util.control;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

import static cofh.lib.util.helpers.SecurityHelper.getID;
import static cofh.lib.util.helpers.SecurityHelper.isDefaultUUID;

public interface ISecurable {

    AccessMode getAccess();

    GameProfile getOwner();

    void setAccess(AccessMode access);

    boolean setOwner(GameProfile profile);

    default String getOwnerName() {

        return getOwner().getName();
    }

    default boolean canAccess(PlayerEntity player) {

        return getAccess().matches(getOwner(), player);
    }

    /**
     * This returns whether or not security functionality is enabled at all.
     */
    default boolean isSecurable() {

        return true;
    }

    // region ACCESS MODE
    enum AccessMode {
        PUBLIC, PRIVATE, FRIENDS, TEAM;

        public static final AccessMode[] VALUES = values();

        public boolean matches(GameProfile owner, PlayerEntity player) {

            UUID ownerID = owner.getId();
            if (isDefaultUUID(ownerID)) {
                return true;
            }
            UUID otherID = getID(player);

            switch (this) {
                case PUBLIC:
                    return true;
                case PRIVATE:
                    // return ownerID.equals(otherID);
                case FRIENDS:
                    // TODO: Fix
                    // return ownerID.equals(otherID) || FriendRegistry.playerHasAccess(owner, player);
                case TEAM:
                    return ownerID.equals(otherID);
                default:
                    return true;
            }
        }

        public boolean isPublic() {

            return this == PUBLIC;
        }

        public boolean isPrivate() {

            return this == PRIVATE;
        }

        public boolean isTeamOnly() {

            return this == TEAM;
        }

        public boolean isFriendsOnly() {

            return this == FRIENDS;
        }
    }
    // endregion
}
