package cofh.omgourd.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class OMGConfig {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(OMGConfig.class);
        registered = true;

        // genServerConfig();
        genClientConfig();

        // ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
    }

    private OMGConfig() {

    }

    // region CONFIG SPEC
    private static final Builder SERVER_CONFIG = new Builder();
    private static ForgeConfigSpec serverSpec;

    private static final Builder CLIENT_CONFIG = new Builder();
    private static ForgeConfigSpec clientSpec;

    private static void genServerConfig() {

        serverSpec = SERVER_CONFIG.build();
    }

    private static void genClientConfig() {

        String comment;
        comment = "If TRUE, Oh My Gourd! will have its own Item Group (Creative Tab).";
        enableCreativeTab = CLIENT_CONFIG.comment(comment).define("Enable Item Group", true);

        clientSpec = CLIENT_CONFIG.build();
    }

    private static void refreshServerConfig() {

    }

    private static void refreshClientConfig() {

    }
    // endregion

    // region VARIABLES
    public static BooleanValue enableCreativeTab;
    // endregion

    // region CONFIGURATION
    @SubscribeEvent
    public static void configLoading(final ModConfig.Loading event) {

        switch (event.getConfig().getType()) {
            case CLIENT:
                refreshClientConfig();
                break;
            case SERVER:
                refreshServerConfig();
        }
    }

    @SubscribeEvent
    public static void configReloading(final ModConfig.Reloading event) {

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
