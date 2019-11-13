package cofh.cofh_archery.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModConfig {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(ModConfig.class);
        registered = true;

        genServerConfig();
        // genClientConfig();

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, serverSpec);
        // ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
    }

    private ModConfig() {

    }

    // region CONFIG SPEC
    private static final Builder SERVER_CONFIG = new Builder();
    private static ForgeConfigSpec serverSpec;

    private static final Builder CLIENT_CONFIG = new Builder();
    private static ForgeConfigSpec clientSpec;

    private static void genServerConfig() {

        genArrowConfig();

        serverSpec = SERVER_CONFIG.build();
    }

    private static void genClientConfig() {

        clientSpec = CLIENT_CONFIG.build();
    }

    private static void genArrowConfig() {

        String comment;
        SERVER_CONFIG.push("Arrows");

        SERVER_CONFIG.push("Blaze");
        comment = "If TRUE, Blaze Arrows are craftable.";

        SERVER_CONFIG.push("Diamond");
        comment = "If TRUE, Diamond Arrows are craftable.";

        SERVER_CONFIG.push("Ender");
        comment = "If TRUE, Ender Arrows are craftable.";

        SERVER_CONFIG.push("Explosive");
        comment = "If TRUE, Explosive Arrows are craftable.";

        SERVER_CONFIG.push("Frost");
        comment = "If TRUE, Frost Arrows are craftable.";

        SERVER_CONFIG.push("Lightning");
        comment = "If TRUE, Lightning Arrows are craftable.";

        SERVER_CONFIG.push("Magma");
        comment = "If TRUE, Magma Arrows are craftable.";

        SERVER_CONFIG.push("Prismarine");
        comment = "If TRUE, Prismarine Arrows are craftable.";

        SERVER_CONFIG.push("Redstone");
        comment = "If TRUE, Redstone Arrows are craftable.";

        SERVER_CONFIG.push("Shulker");
        comment = "If TRUE, Shulker Arrows are craftable.";

        SERVER_CONFIG.push("Slime");
        comment = "If TRUE, Slime Arrows are craftable.";

        SERVER_CONFIG.push("Training");
        comment = "If TRUE, Training Arrows are craftable.";
    }

    private static void refreshServerConfig() {

    }

    private static void refreshClientConfig() {

    }
    // endregion

    // region VARIABLES
    private static BooleanValue enableBlaze;
    private static BooleanValue enableDiamond;
    private static BooleanValue enableEnder;
    private static BooleanValue enableExplosive;
    private static BooleanValue enableFrost;
    private static BooleanValue enableLightning;
    private static BooleanValue enableMagma;
    private static BooleanValue enablePrismarine;
    private static BooleanValue enableRedstone;
    private static BooleanValue enableShulker;
    private static BooleanValue enableSlime;
    private static BooleanValue enableTraining;
    // endregion

    // region CONFIGURATION
    @SubscribeEvent
    public static void configLoading(final net.minecraftforge.fml.config.ModConfig.Loading event) {

        switch (event.getConfig().getType()) {
            case CLIENT:
                refreshClientConfig();
                break;
            case SERVER:
                refreshServerConfig();
        }
    }

    @SubscribeEvent
    public static void configReloading(final net.minecraftforge.fml.config.ModConfig.ConfigReloading event) {

        switch (event.getConfig().getType()) {
            case CLIENT:
                refreshClientConfig();
                break;
            case SERVER:
                refreshServerConfig();
        }
    }
    // endregion
}
