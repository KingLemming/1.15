package cofh.core.init;

import cofh.core.command.*;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CoreConfig {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(CoreConfig.class);
        registered = true;

        genServerConfig();
        genClientConfig();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
    }

    private CoreConfig() {

    }

    // region CONFIG SPEC
    private static final ForgeConfigSpec.Builder SERVER_CONFIG = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec serverSpec;

    private static final ForgeConfigSpec.Builder CLIENT_CONFIG = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec clientSpec;

    private static void genServerConfig() {

        String comment;

        SERVER_CONFIG.push("Commands");
        comment = "The required permission level for the '/cofh crafting' command.";
        permissionCrafting = SERVER_CONFIG.comment(comment).defineInRange("Crafting Permission Level", SubCommandCrafting.permissionLevel, 0, 4);
        comment = "The required permission level for the '/cofh enderchest' command.";
        permissionEnderChest = SERVER_CONFIG.comment(comment).defineInRange("EnderChest Permission Level", SubCommandEnderChest.permissionLevel, 0, 4);
        comment = "The required permission level for the '/cofh heal' command.";
        permissionHeal = SERVER_CONFIG.comment(comment).defineInRange("Heal Permission Level", SubCommandHeal.permissionLevel, 0, 4);
        comment = "The required permission level for the '/cofh ignite' command.";
        permissionIgnite = SERVER_CONFIG.comment(comment).defineInRange("Ignite Permission Level", SubCommandIgnite.permissionLevel, 0, 4);
        comment = "The required permission level for the '/cofh repair' command.";
        permissionRepair = SERVER_CONFIG.comment(comment).defineInRange("Repair Permission Level", SubCommandRepair.permissionLevel, 0, 4);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Enchantments");
        comment = "If TRUE, Feather Falling will prevent Farmland from being trampled. This option will work with alternative versions (overrides) of Feather Falling.";
        serverImprovedFeatherFalling = SERVER_CONFIG.comment(comment).define("Improved Feather Falling", improvedFeatherFalling);

        comment = "If TRUE, Mending behavior is altered so that XP orbs always repair items if possible, and the most damaged item is prioritized. This option may not work with alternative versions (overrides) of Mending.";
        serverImprovedMending = SERVER_CONFIG.comment(comment).define("Improved Mending", improvedMending);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Fishing");
        comment = "If TRUE, Fishing will cause exhaustion.";
        serverEnableFishingExhaustion = SERVER_CONFIG.comment(comment).define("Fishing Exhaustion", enableFishingExhaustion);
        comment = "This option sets the amount of exhaustion caused by fishing, if enabled.";
        serverAmountFishingExhaustion = SERVER_CONFIG.comment(comment).defineInRange("Fishing Exhaustion Amount", amountFishingExhaustion, 0.0D, 10.0D);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("World");
        comment = "If TRUE, Sapling growth will be slowed by a configurable factor.";
        serverEnableSaplingGrowthMod = SERVER_CONFIG.comment(comment).define("Sapling Growth Reduction", enableSaplingGrowthMod);
        comment = "This option sets the growth factor for saplings - they will only grow 1 in N times.";
        serverAmountSaplingGrowthMod = SERVER_CONFIG.comment(comment).defineInRange("Sapling Growth Reduction Factor", amountSaplingGrowthMod, 1, Integer.MAX_VALUE);
        SERVER_CONFIG.pop();

        serverSpec = SERVER_CONFIG.build();
    }

    private static void genClientConfig() {

        String comment;

        CLIENT_CONFIG.push("Tooltips");
        comment = "If TRUE, Enchantment descriptions will be added to the tooltip for Enchanted Books containing only a single enchantment.";
        clientEnableEnchantmentDescriptions = CLIENT_CONFIG.comment(comment).define("Show Enchantment Descriptions", enableEnchantmentDescriptions);

        comment = "If TRUE, Item descriptions will be added to their tooltips if possible.";
        clientEnableItemDescriptions = CLIENT_CONFIG.comment(comment).define("Show Item Descriptions", enableItemDescriptions);
        CLIENT_CONFIG.pop();

        clientSpec = CLIENT_CONFIG.build();
    }

    private static void refreshServerConfig() {

        SubCommandCrafting.permissionLevel = permissionCrafting.get();
        SubCommandEnderChest.permissionLevel = permissionEnderChest.get();
        SubCommandHeal.permissionLevel = permissionHeal.get();
        SubCommandIgnite.permissionLevel = permissionIgnite.get();
        SubCommandRepair.permissionLevel = permissionRepair.get();

        improvedFeatherFalling = serverImprovedFeatherFalling.get();
        improvedMending = serverImprovedMending.get();

        enableFishingExhaustion = serverEnableFishingExhaustion.get();
        amountFishingExhaustion = serverAmountFishingExhaustion.get().floatValue();

        enableSaplingGrowthMod = serverEnableSaplingGrowthMod.get();
        amountSaplingGrowthMod = serverAmountSaplingGrowthMod.get();
    }

    private static void refreshClientConfig() {

        enableEnchantmentDescriptions = clientEnableEnchantmentDescriptions.get();
        enableItemDescriptions = clientEnableItemDescriptions.get();
    }
    // endregion

    // region VARIABLES
    public static IntValue permissionCrafting;
    public static IntValue permissionEnderChest;
    public static IntValue permissionHeal;
    public static IntValue permissionIgnite;
    public static IntValue permissionRepair;

    public static boolean improvedFeatherFalling = true;
    public static boolean improvedMending = true;

    public static boolean enableFishingExhaustion = false;
    public static float amountFishingExhaustion = 0.125F;

    public static boolean enableSaplingGrowthMod = false;
    public static int amountSaplingGrowthMod = 4;

    public static boolean enableEnchantmentDescriptions = true;
    public static boolean enableItemDescriptions = true;

    private static BooleanValue serverImprovedFeatherFalling;
    private static BooleanValue serverImprovedMending;

    private static BooleanValue serverEnableFishingExhaustion;
    private static DoubleValue serverAmountFishingExhaustion;

    private static BooleanValue serverEnableSaplingGrowthMod;
    private static IntValue serverAmountSaplingGrowthMod;

    private static BooleanValue clientEnableEnchantmentDescriptions;
    private static BooleanValue clientEnableItemDescriptions;
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
