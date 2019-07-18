package cofh.ensorcellment.init;

import cofh.lib.util.IConfigSupport;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

public class ConfigEnsorc {

    private static final ConfigEnsorc INSTANCE = new ConfigEnsorc();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(INSTANCE);
        registered = true;
    }

    private ConfigEnsorc() {

    }

    static List<IConfigSupport> configurables = new ArrayList<>();

    public static final Builder COMMON_CONFIG = new Builder();
    public static ForgeConfigSpec commonSpec;

    public static final Builder CLIENT_CONFIG = new Builder();
    public static ForgeConfigSpec clientSpec;

    public static void genConfigs() {

        for (IConfigSupport configurable : configurables) {
            configurable.genConfig();
        }
        genCommonConfig();
        genClientConfig();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
    }

    private static void genCommonConfig() {

        COMMON_CONFIG.push("Options");

        String comment = "If TRUE, Feather Falling will prevent Farmland from being trampled. This option will work with either the Standard or Override version of Feather Falling.";
        cfgPreventFarmlandTrampling = COMMON_CONFIG.comment(comment).define("Prevent Farmland Trampling with Feather Falling", true);

        COMMON_CONFIG.pop();
        commonSpec = COMMON_CONFIG.build();
    }

    private static void genClientConfig() {

        CLIENT_CONFIG.push("Options");

        String comment = "If TRUE, Enchantment descriptions will be added to the tooltip for Enchanted Books containing only a single enchantment.";
        cfgShowEnchDescriptions = CLIENT_CONFIG.comment(comment).define("Show Enchantment Descriptions", true);

        CLIENT_CONFIG.pop();
        clientSpec = CLIENT_CONFIG.build();
    }

    private void refreshCommonConfig() {

        preventFarmlandTrampling = cfgPreventFarmlandTrampling.get();
    }

    private void refreshClientConfig() {

        showEnchDescriptions = cfgShowEnchDescriptions.get();
    }

    public static boolean preventFarmlandTrampling = true;
    private static ForgeConfigSpec.BooleanValue cfgPreventFarmlandTrampling;

    public static boolean showEnchDescriptions = true;
    private static ForgeConfigSpec.BooleanValue cfgShowEnchDescriptions;

    // region CONFIGURATION
    @SubscribeEvent
    public void configLoading(final ModConfig.Loading event) {

        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            refreshClientConfig();
            return;
        }
        for (IConfigSupport configurable : configurables) {
            configurable.refreshConfig();
        }
        refreshCommonConfig();
    }

    @SubscribeEvent
    public void configReloading(final ModConfig.ConfigReloading event) {

        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            refreshClientConfig();
            return;
        }
        for (IConfigSupport configurable : configurables) {
            configurable.refreshConfig();
        }
        refreshCommonConfig();
    }
    // endregion
}
