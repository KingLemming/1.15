package cofh.thermal.cultivation.init;

import cofh.lib.item.ArmorMaterialCoFH;
import cofh.lib.item.BlockNamedItemCoFH;
import cofh.lib.item.ItemCoFH;
import cofh.thermal.cultivation.item.BeekeeperArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_ITEMS;
import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_TOOLS;
import static cofh.thermal.core.util.RegistrationHelper.registerCropAndSeed;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.cultivation.init.TCulFoods.*;
import static cofh.thermal.cultivation.init.TCulReferences.*;

public class TCulItems {

    private TCulItems() {

    }

    public static void register() {

        registerCrops();
        registerFoods();
        registerTools();
    }

    private static void registerCrops() {

        // ANNUAL
        registerCropAndSeed(ID_BARLEY);
        registerCropAndSeed(ID_ONION, ONION);
        registerCropAndSeed(ID_RADISH, RADISH);
        registerCropAndSeed(ID_RICE);
        registerCropAndSeed(ID_SADIROOT);
        registerCropAndSeed(ID_SPINACH, SPINACH);

        // PERENNIAL
        registerCropAndSeed(ID_BELL_PEPPER, BELL_PEPPER);
        registerCropAndSeed(ID_EGGPLANT, EGGPLANT);
        registerCropAndSeed(ID_GREEN_BEAN, GREEN_BEAN);
        registerCropAndSeed(ID_PEANUT, PEANUT);
        registerCropAndSeed(ID_STRAWBERRY, STRAWBERRY);
        registerCropAndSeed(ID_TOMATO, TOMATO);

        // BREWING
        registerCropAndSeed(ID_COFFEE, COFFEE_CHERRY);
        registerCropAndSeed(ID_TEA);

        // OTHER
        ITEMS.register(ID_FROST_MELON_SLICE, () -> new ItemCoFH(new Item.Properties().group(THERMAL_ITEMS).food(FROST_MELON_SLICE).rarity(Rarity.UNCOMMON)));
        ITEMS.register(seeds(ID_FROST_MELON), () -> new BlockNamedItemCoFH(BLOCKS.get(ID_FROST_MELON_STEM), new Item.Properties().group(THERMAL_ITEMS).rarity(Rarity.UNCOMMON)));
    }

    private static void registerFoods() {

        ItemGroup group = THERMAL_ITEMS;

        //        ITEMS.register("food_coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_dough", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_flour", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    private static void registerTools() {

        ItemGroup group = THERMAL_TOOLS;

        ITEMS.register(ID_BEEKEEPER_HELMET, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.HEAD, new Item.Properties().group(group)));
        ITEMS.register(ID_BEEKEEPER_CHESTPLATE, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.CHEST, new Item.Properties().group(group)));
        ITEMS.register(ID_BEEKEEPER_LEGGINGS, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.LEGS, new Item.Properties().group(group)));
        ITEMS.register(ID_BEEKEEPER_BOOTS, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.FEET, new Item.Properties().group(group)));
    }

    public static final ArmorMaterialCoFH BEEKEEPER = new ArmorMaterialCoFH("thermal:beekeeper", 3, new int[]{1, 1, 1, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, 0.0F, () -> Ingredient.fromItems(Items.STRING));

}
