package cofh.thermal.core.common;

import cofh.core.util.FeatureManager;

import java.util.function.BooleanSupplier;

public class ThermalFeatures {

    private ThermalFeatures() {

    }

    private static final FeatureManager FEATURE_MANAGER = new FeatureManager();

    public static FeatureManager manager() {

        return FEATURE_MANAGER;
    }

    public static void setFeature(String flag, boolean enable) {

        FEATURE_MANAGER.setFeature(flag, enable);
    }

    public static void setFeature(String flag, BooleanSupplier condition) {

        FEATURE_MANAGER.setFeature(flag, condition);
    }

    public static BooleanSupplier getFeature(String flag) {

        return () -> FEATURE_MANAGER.getFeature(flag).getAsBoolean();
    }

    // region SPECIFIC FEATURES
    public static String FLAG_TOOL_WRENCH = "tool_wrench";
    public static String FLAG_TOOL_REDPRINT = "tool_redprint";
    public static String FLAG_TOOL_LOCK = "tool_lock";
    public static String FLAG_TOOL_PHYTOGRO = "tool_phytogro";

    public static String FLAG_ARMOR_BEEKEEPER = "armor_beekeeper";
    public static String FLAG_ARMOR_HAZMAT = "armor_hazmat";

    public static String FLAG_RESOURCE_APATITE = "apatite";
    public static String FLAG_RESOURCE_CINNABAR = "cinnabar";
    public static String FLAG_RESOURCE_NITER = "niter";
    public static String FLAG_RESOURCE_SULFUR = "sulfur";

    public static String FLAG_RESOURCE_COPPER = "copper";
    public static String FLAG_RESOURCE_TIN = "tin";
    public static String FLAG_RESOURCE_LEAD = "lead";
    public static String FLAG_RESOURCE_SILVER = "silver";
    public static String FLAG_RESOURCE_NICKEL = "nickel";
    public static String FLAG_RESOURCE_RUBY = "ruby";
    public static String FLAG_RESOURCE_SAPPHIRE = "sapphire";

    public static String FLAG_RESOURCE_BRONZE = "bronze";
    public static String FLAG_RESOURCE_ELECTRUM = "electrum";
    public static String FLAG_RESOURCE_INVAR = "invar";
    public static String FLAG_RESOURCE_CONSTANTAN = "constantan";

    public static String FLAG_MOB_BASALZ = "basalz";
    public static String FLAG_MOB_BLITZ = "blitz";
    public static String FLAG_MOB_BLIZZ = "blizz";
    // endregion

    // region CATEGORIES
    public static String FLAG_VANILLA_BLOCKS = "vanilla_blocks";
    public static String FLAG_VANILLA_ITEMS = "vanilla_items";

    public static String FLAG_BASIC_COMPONENTS = "basic_components";
    public static String FLAG_TOOL_COMPONENTS = "tool_components";

    public static String FLAG_AREA_AUGMENTS = "area_augments";
    public static String FLAG_DYNAMO_AUGMENTS = "dynamo_augments";
    public static String FLAG_MACHINE_AUGMENTS = "machine_augments";
    public static String FLAG_POTION_AUGMENTS = "potion_augments";
    public static String FLAG_STORAGE_AUGMENTS = "storage_augments";
    public static String FLAG_UPGRADE_AUGMENTS = "upgrade_augments";
    // endregion

    static {
        setFeature(FLAG_RESOURCE_BRONZE, () -> getFeature(FLAG_RESOURCE_COPPER).getAsBoolean() && getFeature(FLAG_RESOURCE_TIN).getAsBoolean());
        setFeature(FLAG_RESOURCE_ELECTRUM, getFeature(FLAG_RESOURCE_SILVER));
        setFeature(FLAG_RESOURCE_INVAR, getFeature(FLAG_RESOURCE_NICKEL));
        setFeature(FLAG_RESOURCE_CONSTANTAN, () -> getFeature(FLAG_RESOURCE_COPPER).getAsBoolean() && getFeature(FLAG_RESOURCE_NICKEL).getAsBoolean());
    }

}
