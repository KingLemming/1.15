package cofh.core.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ConfigCore {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(ConfigCore.class);
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

        COMMON_CONFIG.push("Enchantments");
        comment = "If TRUE, Feather Falling will prevent Farmland from being trampled. This option will work with alternative versions (overrides) of Feather Falling.";
        commonImprovedFeatherFalling = COMMON_CONFIG.comment(comment).define("Improved Feather Falling", improvedFeatherFalling);

        comment = "If TRUE, Mending behavior is altered so that XP orbs always repair items if possible, and the most damaged item is prioritized. This option may not work with alternative versions (overrides) of Mending.";
        commonImprovedMending = COMMON_CONFIG.comment(comment).define("Improved Mending", improvedMending);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Fishing");
        comment = "If TRUE, Fishing will cause exhaustion.";
        commonEnableFishingExhaustion = COMMON_CONFIG.comment(comment).define("Fishing Exhaustion", enableFishingExhaustion);
        comment = "This option sets the amount of exhaustion caused by fishing, if enabled.";
        commonAmountFishingExhaustion = COMMON_CONFIG.comment(comment).defineInRange("Fishing Exhaustion Amount", amountFishingExhaustion, 0.0D, 10.0D);
        COMMON_CONFIG.pop();

        commonSpec = COMMON_CONFIG.build();
    }

    private static void genClientConfig() {

        String comment;
        CLIENT_CONFIG.push("Tooltips");

        comment = "If TRUE, Enchantment descriptions will be added to the tooltip for Enchanted Books containing only a single enchantment.";
        clientEnableEnchantmentDescriptions = CLIENT_CONFIG.comment(comment).define("Show Enchantment Descriptions", enableEnchantmentDescriptions);

        CLIENT_CONFIG.pop();
        clientSpec = CLIENT_CONFIG.build();
    }

    private static void refreshCommonConfig() {

        enableFishingExhaustion = commonEnableFishingExhaustion.get();
        amountFishingExhaustion = commonAmountFishingExhaustion.get().floatValue();

        improvedFeatherFalling = commonImprovedFeatherFalling.get();
        improvedMending = commonImprovedMending.get();
    }

    private static void refreshClientConfig() {

        enableEnchantmentDescriptions = clientEnableEnchantmentDescriptions.get();
    }
    // endregion

    // region VARIABLES
    public static boolean enableFishingExhaustion = false;
    public static float amountFishingExhaustion = 0.125F;

    public static boolean improvedFeatherFalling = true;
    public static boolean improvedMending = true;

    public static boolean enableEnchantmentDescriptions = true;

    private static BooleanValue commonEnableFishingExhaustion;
    private static DoubleValue commonAmountFishingExhaustion;

    private static BooleanValue commonImprovedFeatherFalling;
    private static BooleanValue commonImprovedMending;

    private static BooleanValue clientEnableEnchantmentDescriptions;
    // endregion

    // region CONFIGURATION
    @SubscribeEvent
    public static void configLoading(final ModConfig.Loading event) {

        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            refreshClientConfig();
            return;
        }
        refreshCommonConfig();
    }

    @SubscribeEvent
    public static void configReloading(final ModConfig.ConfigReloading event) {

        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            refreshClientConfig();
            return;
        }
        refreshCommonConfig();
    }
    // endregion
}
