package cofh.cofh_archery.init;

import net.minecraftforge.common.ForgeConfigSpec;
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
        comment = "Adjust this to set the effect radius for the Blaze Arrow.";

        SERVER_CONFIG.push("Explosive");
        comment = "Adjust this to set the explosion strength for the Explosive Arrow.";

        SERVER_CONFIG.push("Frost");
        comment = "Adjust this to set the effect radius for the Frost Arrow.";
    }

    private static void refreshServerConfig() {

    }

    private static void refreshClientConfig() {

    }
    // endregion

    // region VARIABLES

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
