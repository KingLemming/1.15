package cofh.core.util;

import cofh.core.CoFHCore;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ProxyUtils {

    private ProxyUtils() {

    }

    public static PlayerEntity getClientPlayer() {

        return CoFHCore.proxy.getClientPlayer();
    }

    public static World getClientWorld() {

        return CoFHCore.proxy.getClientWorld();
    }

    public static boolean isClient() {

        return CoFHCore.proxy.isClient();
    }

}
