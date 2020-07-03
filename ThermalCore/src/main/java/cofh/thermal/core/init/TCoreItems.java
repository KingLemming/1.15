package cofh.thermal.core.init;

import cofh.lib.item.ArmorMaterialCoFH;
import cofh.lib.item.AugmentItem;
import cofh.lib.item.SpawnEggItemCoFH;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.thermal.core.common.ThermalItemGroups;
import cofh.thermal.core.item.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_TOOLS;
import static cofh.thermal.core.init.TCoreReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.*;

public class TCoreItems {

    private TCoreItems() {

    }

    public static void register() {

        registerVanillaItems();
        registerResources();
        registerTools();
        registerAugments();
        registerSpawnEggs();
    }

    // region HELPERS
    private static void registerVanillaItems() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerMetalSet("iron", group, true);
        registerMetalSet("gold", group, true);

        registerGemSet("diamond", group, true);
        registerGemSet("emerald", group, true);
    }

    private static void registerResources() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        //        registerItem("cinnabar_dust", group);
        //        registerItem("niter_dust", group);
        //        registerItem("sulfur_dust", group);
        //        registerItem("obsidian_dust", group);
        registerItem("wood_dust", group);

        registerItem("apatite", group);
        registerItem("cinnabar", group);
        registerItem("niter", group);
        registerItem("sulfur", group);

        registerItem("basalz_rod", group);
        registerItem("basalz_powder", group);
        registerItem("blizz_rod", group);
        registerItem("blizz_powder", group);

        Rarity rarity = Rarity.UNCOMMON;

        registerMetalSet("signalum", group, rarity);
        registerMetalSet("lumium", group, rarity);
        registerMetalSet("enderium", group, rarity);
    }

    private static void registerTools() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("wrench", () -> new WrenchItem(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("redprint", () -> new RedprintItem(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("lock", () -> new LockItem(new Item.Properties().group(group)));
        registerItem("phytogro", () -> new FertilizerItem(new Item.Properties().group(group)).setRadius(2));
    }

    private static void registerAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        // Additive
        final float[] upgradeMods = new float[]{0.0F, 0.5F, 1.5F, 3.0F, 5.0F, 6.5F};
        final float[] storageMods = new float[]{1.0F, 2.0F, 3.0F, 4.0F, 5.0F, 6.0F};

        for (int i = 1; i <= 4; ++i) {
            int tier = i;
            registerItem("upgrade_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
                    AugmentDataHelper.builder()
                            .type(TAG_AUGMENT_TYPE_UPGRADE)
                            .mod(TAG_AUGMENT_BASE_MOD, upgradeMods[tier])
                            .build()));
        }
        for (int i = 1; i <= 4; ++i) {
            int tier = i;
            registerItem("rf_coil_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
                    AugmentDataHelper.builder()
                            .type(TAG_AUGMENT_TYPE_RF)
                            .mod(TAG_AUGMENT_ENERGY_STORAGE, storageMods[tier])
                            .mod(TAG_AUGMENT_ENERGY_XFER, storageMods[tier])
                            .build()));
        }
        for (int i = 1; i <= 4; ++i) {
            int tier = i;
            registerItem("rf_coil_stor_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
                    AugmentDataHelper.builder()
                            .type(TAG_AUGMENT_TYPE_RF)
                            .mod(TAG_AUGMENT_ENERGY_STORAGE, storageMods[tier + 1])
                            .mod(TAG_AUGMENT_ENERGY_XFER, storageMods[tier - 1])
                            .build()));
        }
        for (int i = 1; i <= 4; ++i) {
            int tier = i;
            registerItem("rf_coil_xfer_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
                    AugmentDataHelper.builder()
                            .type(TAG_AUGMENT_TYPE_RF)
                            .mod(TAG_AUGMENT_ENERGY_STORAGE, storageMods[tier - 1])
                            .mod(TAG_AUGMENT_ENERGY_XFER, storageMods[tier + 1])
                            .build()));
        }
        for (int i = 1; i <= 4; ++i) {
            int tier = i;
            registerItem("fluid_tank_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
                    AugmentDataHelper.builder()
                            .type(TAG_AUGMENT_TYPE_FLUID)
                            .mod(TAG_AUGMENT_FLUID_STORAGE, storageMods[tier])
                            .build()));
        }

        registerItem("machine_speed_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_MACHINE)
                        .mod(TAG_AUGMENT_MACHINE_POWER, 1.0F)
                        .mod(TAG_AUGMENT_MACHINE_ENERGY, 1.1F)
                        .build()));

        registerItem("machine_output_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_MACHINE)
                        .mod(TAG_AUGMENT_MACHINE_SECONDARY, 0.15F)
                        .mod(TAG_AUGMENT_MACHINE_ENERGY, 1.25F)
                        .build()));

        registerItem("machine_catalyst_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_MACHINE)
                        .mod(TAG_AUGMENT_MACHINE_CATALYST, 0.8F)
                        .mod(TAG_AUGMENT_MACHINE_ENERGY, 1.25F)
                        .build()));

        registerItem("dynamo_output_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_DYNAMO)
                        .mod(TAG_AUGMENT_DYNAMO_PRODUCTION, 1.0F)
                        .mod(TAG_AUGMENT_DYNAMO_EFFICIENCY, 0.9F)
                        .build()));

        registerItem("dynamo_fuel_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_DYNAMO)
                        .mod(TAG_AUGMENT_DYNAMO_EFFICIENCY, 1.10F)
                        .build()));
    }

    private static void registerSpawnEggs() {

        ITEMS.register("blizz_spawn_egg", () -> new SpawnEggItemCoFH(() -> BLIZZ_ENTITY, 0xE0FBFF, 0x6BE6FF, new Item.Properties().group(ItemGroup.MISC)));
    }
    // endregion

    // region EXTERNAL REGISTRATION
    public static void registerBeekeeperArmor() {

        if (registerBeekeeperArmor) {
            return;
        }
        registerBeekeeperArmor = true;

        ItemGroup group = THERMAL_TOOLS;

        ITEMS.register(ID_BEEKEEPER_HELMET, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.HEAD, new Item.Properties().group(group)));
        ITEMS.register(ID_BEEKEEPER_CHESTPLATE, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.CHEST, new Item.Properties().group(group)));
        ITEMS.register(ID_BEEKEEPER_LEGGINGS, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.LEGS, new Item.Properties().group(group)));
        ITEMS.register(ID_BEEKEEPER_BOOTS, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.FEET, new Item.Properties().group(group)));
    }

    public static void registerHazmatArmor() {

        if (registerHazmatArmor) {
            return;
        }
        registerHazmatArmor = true;

        ItemGroup group = THERMAL_TOOLS;

        ITEMS.register(ID_HAZMAT_HELMET, () -> new HazmatArmorItem(HAZMAT, EquipmentSlotType.HEAD, new Item.Properties().group(group)));
        ITEMS.register(ID_HAZMAT_CHESTPLATE, () -> new HazmatArmorItem(HAZMAT, EquipmentSlotType.CHEST, new Item.Properties().group(group)));
        ITEMS.register(ID_HAZMAT_LEGGINGS, () -> new HazmatArmorItem(HAZMAT, EquipmentSlotType.LEGS, new Item.Properties().group(group)));
        ITEMS.register(ID_HAZMAT_BOOTS, () -> new HazmatArmorItem(HAZMAT, EquipmentSlotType.FEET, new Item.Properties().group(group)));
    }

    private static boolean registerBeekeeperArmor;
    private static boolean registerHazmatArmor;

    public static final ArmorMaterialCoFH BEEKEEPER = new ArmorMaterialCoFH("thermal:beekeeper", 3, new int[]{1, 1, 1, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, 0.0F, () -> Ingredient.fromItems(Items.STRING));
    public static final ArmorMaterialCoFH HAZMAT = new ArmorMaterialCoFH("thermal:hazmat", 4, new int[]{1, 2, 3, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(Items.STRING));
    // endregion

}
