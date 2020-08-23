package cofh.thermal.core.init;

import cofh.lib.item.AugmentItem;
import cofh.lib.item.ItemCoFH;
import cofh.lib.item.SpawnEggItemCoFH;
import cofh.lib.util.constants.ToolTypes;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.thermal.core.common.ThermalItemGroups;
import cofh.thermal.core.item.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalFeatures.*;
import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_TOOLS;
import static cofh.thermal.core.init.TCoreReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.*;

public class TCoreItems {

    private TCoreItems() {

    }

    public static void register() {

        registerVanilla();
        registerResources();
        registerParts();
        registerTools();
        registerArmor();
        registerSpawnEggs();

        registerUpgradeAugments();
        registerStorageAugments();
        registerMachineAugments();
        registerDynamoAugments();
        registerAreaAugments();
        registerPotionAugments();
    }

    // region HELPERS
    private static void registerVanilla() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerMetalSet("iron", group, Rarity.COMMON, getFeature(FLAG_VANILLA_ITEMS), true);
        registerMetalSet("gold", group, Rarity.COMMON, getFeature(FLAG_VANILLA_ITEMS), true);

        registerGemSet("diamond", group, Rarity.COMMON, getFeature(FLAG_VANILLA_ITEMS), true);
        registerGemSet("emerald", group, Rarity.COMMON, getFeature(FLAG_VANILLA_ITEMS), true);
    }

    private static void registerResources() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        //        registerItem("cinnabar_dust", group);
        //        registerItem("niter_dust", group);
        //        registerItem("sulfur_dust", group);
        //        registerItem("obsidian_dust", group);
        registerItem("sawdust", group);
        registerItem("rosin", () -> new ItemCoFH(new Item.Properties().group(group)).setBurnTime(800));
        registerItem("rubber", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_RESOURCE_RUBBER)));
        registerItem("cured_rubber", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_RESOURCE_RUBBER)));
        registerItem("slag", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_RESOURCE_SLAG)));
        registerItem("rich_slag", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_RESOURCE_SLAG)));

        registerItem("beekeeper_fabric", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_BEEKEEPER)));
        registerItem("diving_fabric", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_DIVING)));
        registerItem("hazmat_fabric", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_HAZMAT)));

        registerItem("apatite", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_RESOURCE_APATITE)));
        registerItem("cinnabar", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_RESOURCE_CINNABAR)));
        registerItem("niter", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_RESOURCE_NITER)));
        registerItem("sulfur", () -> new ItemCoFH(new Item.Properties().group(group)).setBurnTime(1200).setShowInGroups(getFeature(FLAG_RESOURCE_SULFUR)));

        registerItem("basalz_rod", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_MOB_BASALZ)));
        registerItem("basalz_powder", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_MOB_BASALZ)));
        registerItem("blitz_rod", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_MOB_BLITZ)));
        registerItem("blitz_powder", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_MOB_BLITZ)));
        registerItem("blizz_rod", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_MOB_BLIZZ)));
        registerItem("blizz_powder", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_MOB_BLIZZ)));

        Rarity rarity = Rarity.UNCOMMON;

        registerMetalSet("copper", group, getFeature(FLAG_RESOURCE_COPPER));
        registerMetalSet("tin", group, getFeature(FLAG_RESOURCE_TIN));
        registerMetalSet("lead", group, getFeature(FLAG_RESOURCE_LEAD));
        registerMetalSet("silver", group, getFeature(FLAG_RESOURCE_SILVER));
        registerMetalSet("nickel", group, getFeature(FLAG_RESOURCE_NICKEL));

        registerMetalSet("bronze", group, getFeature(FLAG_RESOURCE_BRONZE));
        registerMetalSet("electrum", group, getFeature(FLAG_RESOURCE_ELECTRUM));
        registerMetalSet("invar", group, getFeature(FLAG_RESOURCE_INVAR));
        registerMetalSet("constantan", group, getFeature(FLAG_RESOURCE_CONSTANTAN));

        registerMetalSet("signalum", group, rarity);
        registerMetalSet("lumium", group, rarity);
        registerMetalSet("enderium", group, rarity);

        registerGemSet("ruby", group, getFeature(FLAG_RESOURCE_RUBY));
        registerGemSet("sapphire", group, getFeature(FLAG_RESOURCE_SAPPHIRE));
    }

    private static void registerParts() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerItem("redstone_servo", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_BASIC_COMPONENTS)));
        registerItem("rf_coil", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_BASIC_COMPONENTS)));

        registerItem("drill_head", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_TOOL_COMPONENTS)));
        registerItem("saw_blade", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_TOOL_COMPONENTS)));
        registerItem("laser_diode", () -> new ItemCoFH(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_TOOL_COMPONENTS)));
    }

    // region AUGMENTS
    private static void registerAreaAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("area_radius_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_AREA_EFFECT)
                        .mod(TAG_AUGMENT_AREA_RADIUS, 1.0F)
                        .build()).setShowInGroups(getFeature(FLAG_AREA_AUGMENTS)));
    }

    private static void registerDynamoAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("dynamo_output_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_DYNAMO)
                        .mod(TAG_AUGMENT_DYNAMO_PRODUCTION, 1.0F)
                        .mod(TAG_AUGMENT_DYNAMO_EFFICIENCY, 0.9F)
                        .build()).setShowInGroups(getFeature(FLAG_DYNAMO_AUGMENTS)));

        registerItem("dynamo_fuel_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_DYNAMO)
                        .mod(TAG_AUGMENT_DYNAMO_EFFICIENCY, 1.10F)
                        .build()).setShowInGroups(getFeature(FLAG_DYNAMO_AUGMENTS)));
    }

    public static void registerMachineAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("machine_speed_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_MACHINE)
                        .mod(TAG_AUGMENT_MACHINE_POWER, 1.0F)
                        .mod(TAG_AUGMENT_MACHINE_ENERGY, 1.1F)
                        .build()).setShowInGroups(getFeature(FLAG_MACHINE_AUGMENTS)));

        registerItem("machine_output_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_MACHINE)
                        .mod(TAG_AUGMENT_MACHINE_SECONDARY, 0.15F)
                        .mod(TAG_AUGMENT_MACHINE_ENERGY, 1.25F)
                        .build()).setShowInGroups(getFeature(FLAG_MACHINE_AUGMENTS)));

        registerItem("machine_catalyst_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_MACHINE)
                        .mod(TAG_AUGMENT_MACHINE_CATALYST, 0.8F)
                        .mod(TAG_AUGMENT_MACHINE_ENERGY, 1.25F)
                        .build()).setShowInGroups(getFeature(FLAG_MACHINE_AUGMENTS)));
    }

    public static void registerPotionAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("potion_amplifier_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_POTION)
                        .mod(TAG_AUGMENT_POTION_AMPLIFIER, 1.0F)
                        .mod(TAG_AUGMENT_POTION_DURATION, -0.25F)
                        .build()).setShowInGroups(getFeature(FLAG_POTION_AUGMENTS)));

        registerItem("potion_duration_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_POTION)
                        .mod(TAG_AUGMENT_POTION_DURATION, 1.0F)
                        .build()).setShowInGroups(getFeature(FLAG_POTION_AUGMENTS)));
    }

    private static void registerStorageAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;
        final float[] storageMods = new float[]{1.0F, 2.0F, 3.0F, 4.0F, 5.0F, 6.0F};

        for (int i = 1; i <= 4; ++i) {
            int tier = i;
            registerItem("rf_coil_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
                    AugmentDataHelper.builder()
                            .type(TAG_AUGMENT_TYPE_RF)
                            .mod(TAG_AUGMENT_ENERGY_STORAGE, storageMods[tier])
                            .mod(TAG_AUGMENT_ENERGY_XFER, storageMods[tier])
                            .build()).setShowInGroups(getFeature("storage_augments")));
        }
        // TODO: Consider bringing back after adjusting for balance.
        //        for (int i = 1; i <= 4; ++i) {
        //            int tier = i;
        //            registerItem("rf_coil_stor_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
        //                    AugmentDataHelper.builder()
        //                            .type(TAG_AUGMENT_TYPE_RF)
        //                            .mod(TAG_AUGMENT_ENERGY_STORAGE, storageMods[tier + 1])
        //                            .mod(TAG_AUGMENT_ENERGY_XFER, storageMods[tier - 1])
        //                            .build()).setShowInGroups(getFeature(FLAG_STORAGE_AUGMENTS)));
        //        }
        //        for (int i = 1; i <= 4; ++i) {
        //            int tier = i;
        //            registerItem("rf_coil_xfer_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
        //                    AugmentDataHelper.builder()
        //                            .type(TAG_AUGMENT_TYPE_RF)
        //                            .mod(TAG_AUGMENT_ENERGY_STORAGE, storageMods[tier - 1])
        //                            .mod(TAG_AUGMENT_ENERGY_XFER, storageMods[tier + 1])
        //                            .build()).setShowInGroups(getFeature(FLAG_STORAGE_AUGMENTS)));
        //        }
        for (int i = 1; i <= 4; ++i) {
            int tier = i;
            registerItem("fluid_tank_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
                    AugmentDataHelper.builder()
                            .type(TAG_AUGMENT_TYPE_FLUID)
                            .mod(TAG_AUGMENT_FLUID_STORAGE, storageMods[tier])
                            .build()).setShowInGroups(getFeature(FLAG_STORAGE_AUGMENTS)));
        }
    }

    private static void registerUpgradeAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;
        final float[] upgradeMods = new float[]{1.0F, 1.5F, 2.5F, 4.0F, 6.0F, 8.5F};
        // final float[] upgradeMods = new float[]{1.0F, 1.5F, 2.0F, 2.5F, 3.0F, 3.5F};

        for (int i = 1; i <= 4; ++i) {
            int tier = i;
            registerItem("upgrade_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
                    AugmentDataHelper.builder()
                            .type(TAG_AUGMENT_TYPE_UPGRADE)
                            .mod(TAG_AUGMENT_BASE_MOD, upgradeMods[tier])
                            .build()).setShowInGroups(getFeature(FLAG_UPGRADE_AUGMENTS)));
        }
    }
    // endregion

    private static void registerTools() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("wrench", () -> new WrenchItem(new Item.Properties().maxStackSize(1).group(group).addToolType(ToolTypes.WRENCH, 1)));
        registerItem("redprint", () -> new RedprintItem(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("lock", () -> new LockItem(new Item.Properties().group(group)));
        registerItem("phytogro", () -> new FertilizerItem(new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_PHYTOGRO)));

        registerItem("phytogrenade", () -> new FertilizerThrownItem(new Item.Properties().group(group).maxStackSize(16)).setShowInGroups(getFeature(FLAG_PHYTOGRO)));
    }

    private static void registerArmor() {

        ItemGroup group = THERMAL_TOOLS;

        ITEMS.register(ID_BEEKEEPER_HELMET, () -> new BeekeeperArmorItem(TCoreReferences.BEEKEEPER, EquipmentSlotType.HEAD, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_BEEKEEPER)));
        ITEMS.register(ID_BEEKEEPER_CHESTPLATE, () -> new BeekeeperArmorItem(TCoreReferences.BEEKEEPER, EquipmentSlotType.CHEST, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_BEEKEEPER)));
        ITEMS.register(ID_BEEKEEPER_LEGGINGS, () -> new BeekeeperArmorItem(TCoreReferences.BEEKEEPER, EquipmentSlotType.LEGS, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_BEEKEEPER)));
        ITEMS.register(ID_BEEKEEPER_BOOTS, () -> new BeekeeperArmorItem(TCoreReferences.BEEKEEPER, EquipmentSlotType.FEET, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_BEEKEEPER)));

        ITEMS.register(ID_DIVING_HELMET, () -> new DivingArmorItem(TCoreReferences.DIVING, EquipmentSlotType.HEAD, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_DIVING)));
        ITEMS.register(ID_DIVING_CHESTPLATE, () -> new DivingArmorItem(TCoreReferences.DIVING, EquipmentSlotType.CHEST, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_DIVING)));
        ITEMS.register(ID_DIVING_LEGGINGS, () -> new DivingArmorItem(TCoreReferences.DIVING, EquipmentSlotType.LEGS, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_DIVING)));
        ITEMS.register(ID_DIVING_BOOTS, () -> new DivingArmorItem(TCoreReferences.DIVING, EquipmentSlotType.FEET, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_DIVING)));

        ITEMS.register(ID_HAZMAT_HELMET, () -> new HazmatArmorItem(TCoreReferences.HAZMAT, EquipmentSlotType.HEAD, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_HAZMAT)));
        ITEMS.register(ID_HAZMAT_CHESTPLATE, () -> new HazmatArmorItem(TCoreReferences.HAZMAT, EquipmentSlotType.CHEST, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_HAZMAT)));
        ITEMS.register(ID_HAZMAT_LEGGINGS, () -> new HazmatArmorItem(TCoreReferences.HAZMAT, EquipmentSlotType.LEGS, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_HAZMAT)));
        ITEMS.register(ID_HAZMAT_BOOTS, () -> new HazmatArmorItem(TCoreReferences.HAZMAT, EquipmentSlotType.FEET, new Item.Properties().group(group)).setShowInGroups(getFeature(FLAG_ARMOR_HAZMAT)));
    }

    private static void registerSpawnEggs() {

        registerItem("basalz_spawn_egg", () -> new SpawnEggItemCoFH(() -> BASALZ_ENTITY, 0x363840, 0x080407, new Item.Properties().group(ItemGroup.MISC)).setShowInGroups(getFeature(FLAG_MOB_BASALZ)));
        registerItem("blitz_spawn_egg", () -> new SpawnEggItemCoFH(() -> BLITZ_ENTITY, 0xECFEFC, 0xFFD46D, new Item.Properties().group(ItemGroup.MISC)).setShowInGroups(getFeature(FLAG_MOB_BLITZ)));
        registerItem("blizz_spawn_egg", () -> new SpawnEggItemCoFH(() -> BLIZZ_ENTITY, 0x7BD4FF, 0x0D6FD9, new Item.Properties().group(ItemGroup.MISC)).setShowInGroups(getFeature(FLAG_MOB_BLIZZ)));
    }
    // endregion
}
