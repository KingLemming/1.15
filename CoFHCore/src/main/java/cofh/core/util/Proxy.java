package cofh.core.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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

    public Object addModel(String loc, Object model) {

        return addModel(new ResourceLocation(loc), model);
    }

    public Object addModel(Item item, Object model) {

        return item == null ? null : addModel(item.getRegistryName(), model);
    }

    public Object addModel(ResourceLocation loc, Object model) {

        return null;
    }

    public Object getModel(ResourceLocation loc) {

        return null;
    }
    // endregion
}
