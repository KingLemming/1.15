package cofh.core.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ConfigCore {

    private static final ConfigCore INSTANCE = new ConfigCore();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(INSTANCE);
        registered = true;

        genCommonConfig();
        genClientConfig();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
    }

    private ConfigCore() {

    }

    // region CONFIG SPEC
    private static final ForgeConfigSpec.Builder COMMON_CONFIG = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec commonSpec;

    private static final ForgeConfigSpec.Builder CLIENT_CONFIG = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec clientSpec;

    private static void genCommonConfig() {

        String comment;
        COMMON_CONFIG.push("Options");

        COMMON_CONFIG.push("Fishing");
        comment = "If TRUE, Fishing will cause exhaustion.";
        commonEnableFishingExhaustion = COMMON_CONFIG.comment(comment).define("Fishing Exhaustion", enableFishingExhaustion);
        comment = "This option sets the amount of exhaustion caused by fishing.";
        commonAmountFishingExhaustion = COMMON_CONFIG.comment(comment).defineInRange("Fishing Exhaustion Amount", amountFishingExhaustion, 0.0D, 10.0D);
        COMMON_CONFIG.pop();

        comment = "If TRUE, Feather Falling will prevent Farmland from being trampled. This option will work with alternative versions (overrides) of Feather Falling.";
        commonPreventFarmlandTrampling = COMMON_CONFIG.comment(comment).define("Prevent Farmland Trampling with Feather Falling", preventFarmlandTrampling);

        COMMON_CONFIG.pop();
        commonSpec = COMMON_CONFIG.build();
    }

    private static void genClientConfig() {

        String comment;
        CLIENT_CONFIG.push("Options");

        comment = "If TRUE, Enchantment descriptions will be added to the tooltip for Enchanted Books containing only a single enchantment.";
        clientEnableEnchantmentDescriptions = CLIENT_CONFIG.comment(comment).define("Show Enchantment Descriptions", enableEnchantmentDescriptions);

        CLIENT_CONFIG.pop();
        clientSpec = CLIENT_CONFIG.build();
    }

    private void refreshCommonConfig() {

        enableFishingExhaustion = commonEnableFishingExhaustion.get();
        amountFishingExhaustion = commonAmountFishingExhaustion.get().floatValue();

        preventFarmlandTrampling = commonPreventFarmlandTrampling.get();
    }

    private void refreshClientConfig() {

        enableEnchantmentDescriptions = clientEnableEnchantmentDescriptions.get();
    }
    // endregion

    // region VARIABLES
    public static boolean enableFishingExhaustion = false;
    public static float amountFishingExhaustion = 0.01F;
    public static boolean preventFarmlandTrampling = true;
    public static boolean enableEnchantmentDescriptions = true;

    private static BooleanValue commonEnableFishingExhaustion;
    private static DoubleValue commonAmountFishingExhaustion;
    private static BooleanValue commonPreventFarmlandTrampling;
    private static BooleanValue clientEnableEnchantmentDescriptions;
    // endregion

    // region CONFIGURATION
    @SubscribeEvent
    public void configLoading(final ModConfig.Loading event) {

        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            refreshClientConfig();
            return;
        }
        refreshCommonConfig();
    }

    @SubscribeEvent
    public void configReloading(final ModConfig.ConfigReloading event) {

        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            refreshClientConfig();
            return;
        }
        refreshCommonConfig();
    }
    // endregion
}
