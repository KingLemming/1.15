package cofh.core.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class Proxy {

    // region HELPERS
    public void addIndexedChatMessage(ITextComponent chat, int index) {

    }

    public int getKeyBind(String key) {

        return 0;
    }

    public PlayerEntity getClientPlayer() {

        return null;
    }

    public World getClientWorld() {

        return null;
    }

    public boolean isClient() {

        return false;
    }
    // endregion
}
