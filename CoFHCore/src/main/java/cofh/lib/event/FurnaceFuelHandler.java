package cofh.lib.event;

import net.minecraftforge.common.MinecraftForge;

public class FurnaceFuelHandler {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(FurnaceFuelHandler.class);
        registered = true;
    }

    private FurnaceFuelHandler() {

    }

    // TODO: Finish
    //    public static void addAllWoodBlocks(String modId) {
    //
    //        ForgeRegistries.BLOCKS.getKeys()
    //                .stream()
    //                .filter(r -> r.getNamespace().equals(modId))
    //                .map(ForgeRegistries.BLOCKS::getValue)
    //                .filter(b -> b.getMaterial(b.getDefaultState()) == Material.WOOD)
    //                .forEach();
    //    }

}
