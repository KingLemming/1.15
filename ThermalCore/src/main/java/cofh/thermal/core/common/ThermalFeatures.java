package cofh.thermal.core.common;

import cofh.core.util.ProxyUtils;
import cofh.lib.block.TileBlock4Way;
import cofh.lib.item.ArmorMaterialCoFH;
import cofh.lib.item.AugmentItem;
import cofh.lib.item.ItemCoFH;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.thermal.core.inventory.container.workbench.TinkerBenchContainer;
import cofh.thermal.core.item.BeekeeperArmorItem;
import cofh.thermal.core.item.HazmatArmorItem;
import cofh.thermal.core.tileentity.workbench.TinkerBenchTile;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.extensions.IForgeContainerType;

import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.core.ThermalCore.*;
import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_ITEMS;
import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_TOOLS;
import static cofh.thermal.core.common.ThermalReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.registerAugBlock;
import static cofh.thermal.core.util.RegistrationHelper.registerItem;

public class ThermalFeatures {

    private ThermalFeatures() {

    }

    // region BLOCKS
    public static void registerTinkerBench() {

        if (registerTinkerBench) {
            return;
        }
        registerTinkerBench = true;

        IntSupplier workbenchAugs = () -> ThermalConfig.workbenchAugments;
        Predicate<ItemStack> workbenchValidator = (e) -> true;

        registerAugBlock(ID_TINKER_BENCH, () -> new TileBlock4Way(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F), TinkerBenchTile::new), workbenchAugs, workbenchValidator);
        CONTAINERS.register(ID_TINKER_BENCH, () -> IForgeContainerType.create((windowId, inv, data) -> new TinkerBenchContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        TILE_ENTITIES.register(ID_TINKER_BENCH, () -> TileEntityType.Builder.create(TinkerBenchTile::new, TINKER_BENCH_BLOCK).build(null));
    }

    private static boolean registerTinkerBench;
    // endregion

    // region AUGMENTS
    public static void registerAreaAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("area_radius_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_AREA_EFFECT)
                        .mod(TAG_AUGMENT_RADIUS, 1.0F)
                        .build()));
    }

    public static void registerDynamoAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

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

    public static void registerMachineAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

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
    }

    public static void registerPotionAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("potion_amplifier_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_POTION)
                        .mod(TAG_AUGMENT_POTION_AMPLIFIER, 1.0F)
                        .mod(TAG_AUGMENT_POTION_DURATION, -0.25F)
                        .build()));

        registerItem("potion_duration_augment", () -> new AugmentItem(new Item.Properties().group(group),
                AugmentDataHelper.builder()
                        .type(TAG_AUGMENT_TYPE_POTION)
                        .mod(TAG_AUGMENT_POTION_DURATION, 1.0F)
                        .build()));
    }

    public static void registerStorageAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        final float[] storageMods = new float[]{1.0F, 2.0F, 3.0F, 4.0F, 5.0F, 6.0F};


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
    }

    public static void registerUpgradeAugments() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;
        // final float[] upgradeMods = new float[]{1.0F, 1.5F, 2.5F, 4.0F, 6.0F, 8.5F};
        final float[] upgradeMods = new float[]{1.0F, 1.5F, 2.0F, 2.5F, 3.0F, 3.5F};

        for (int i = 1; i <= 4; ++i) {
            int tier = i;
            registerItem("upgrade_augment_" + i, () -> new AugmentItem(new Item.Properties().group(group),
                    AugmentDataHelper.builder()
                            .type(TAG_AUGMENT_TYPE_UPGRADE)
                            .mod(TAG_AUGMENT_BASE_MOD, upgradeMods[tier])
                            .build()));
        }
    }
    // endregion

    // region ITEMS
    public static void registerBasicParts() {

        ItemGroup group = THERMAL_ITEMS;

        registerItem("redstone_servo", () -> new ItemCoFH(new Item.Properties().group(group)));
        registerItem("rf_coil", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    public static void registerToolParts() {

        registerBasicParts();

        ItemGroup group = THERMAL_ITEMS;

        registerItem("drill_head", () -> new ItemCoFH(new Item.Properties().group(group)));
        registerItem("laser_diode", () -> new ItemCoFH(new Item.Properties().group(group)));
        registerItem("saw_blade", () -> new ItemCoFH(new Item.Properties().group(group)));
    }
    // endregion

    // region TOOLS AND ARMOR
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
