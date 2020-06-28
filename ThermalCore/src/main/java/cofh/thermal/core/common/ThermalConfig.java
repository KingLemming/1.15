package cofh.thermal.core.common;

import cofh.lib.util.control.IReconfigurable.SideConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.control.IReconfigurable.SideConfig.SIDE_NONE;

public class ThermalConfig {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(ThermalConfig.class);
        registered = true;

        genServerConfig();
        genClientConfig();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
    }

    private ThermalConfig() {

    }

    // region CONFIG SPEC
    private static final ForgeConfigSpec.Builder SERVER_CONFIG = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec serverSpec;

    private static final ForgeConfigSpec.Builder CLIENT_CONFIG = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec clientSpec;

    private static void genServerConfig() {

        //        SERVER_CONFIG.push("Machines");
        //
        //        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Global Options");

        keepEnergy = SERVER_CONFIG
                .comment("If TRUE, Thermal Blocks will retain Energy when dropped.")
                .define("Blocks Retain Energy", true);
        keepItems = SERVER_CONFIG
                .comment("If TRUE, Thermal Blocks will retain Inventory Contents when dropped.")
                .define("Blocks Retain Inventory", false);
        keepFluids = SERVER_CONFIG
                .comment("If TRUE, Thermal Blocks will retain Tank Contents when dropped.")
                .define("Blocks Retain Tanks", false);
        keepAugments = SERVER_CONFIG
                .comment("If TRUE, Thermal Blocks will retain Augments when dropped.")
                .define("Blocks Retain Augments", true);
        keepRSControl = SERVER_CONFIG
                .comment("If TRUE, Thermal Blocks will retain Redstone Control configuration when dropped.")
                .define("Blocks Retain Redstone Control", true);
        keepSideConfig = SERVER_CONFIG
                .comment("If TRUE, Thermal Blocks will retain Side configuration when dropped.")
                .define("Blocks Retain Side Configuration", true);
        keepTransferControl = SERVER_CONFIG
                .comment("If TRUE, Thermal Blocks will retain Transfer Control configuration when dropped.")
                .define("Blocks Retain Transfer Control", true);

        SERVER_CONFIG.pop();

        serverSpec = SERVER_CONFIG.build();
    }

    private static void genClientConfig() {

        clientSpec = CLIENT_CONFIG.build();
    }

    private static void genMachineConfig() {

    }

    private static void refreshServerConfig() {

    }

    private static void refreshClientConfig() {

    }

    // region GLOBALS
    public static final SideConfig[] DEFAULT_MACHINE_SIDES = new SideConfig[]{SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE};
    public static final byte[] DEFAULT_MACHINE_SIDES_RAW = new byte[]{0, 0, 0, 0, 0, 0};
    // endregion

    // region VARIABLES
    public static int dynamoAugments = 4;
    public static int machineAugments = 4;
    public static int workbenchAugments = 1;

    public static BooleanValue keepEnergy;
    public static BooleanValue keepItems;
    public static BooleanValue keepFluids;

    public static BooleanValue keepAugments;
    public static BooleanValue keepRSControl;
    public static BooleanValue keepSideConfig;
    public static BooleanValue keepTransferControl;
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
    public static void configReloading(final net.minecraftforge.fml.config.ModConfig.Reloading event) {

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
