package cofh.thermal.cultivation.init;

import cofh.lib.item.BlockNamedItemCoFH;
import cofh.lib.item.ItemCoFH;
import cofh.thermal.core.init.ItemGroupsTSeries;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import static cofh.thermal.core.ThermalSeries.ITEMS;
import static cofh.thermal.cultivation.init.BlocksTCultivation.*;
import static cofh.thermal.cultivation.init.FoodsTCultivation.TEMP;

public class ItemsTCultivation {

    private ItemsTCultivation() {

    }

    public static void register() {

        registerCrops();
        registerSeeds();
    }

    private static void registerCrops() {

        ItemGroup group = ItemGroupsTSeries.THERMAL_ITEMS;

        cropBarley = ITEMS.register("crop_barley", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropOnion = ITEMS.register("crop_onion", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropRice = ITEMS.register("crop_rice", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropSadiroot = ITEMS.register("crop_sadiroot", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropSpinach = ITEMS.register("crop_spinach", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));

        ITEMS.register("crop_corn", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));

        // TODO: BlockItems (Plantable Crops)
        ITEMS.register("crop_bell_pepper", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        ITEMS.register("crop_green_bean", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        ITEMS.register("crop_peanut", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        ITEMS.register("crop_strawberry", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        ITEMS.register("crop_tomato", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));

        ITEMS.register("crop_coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        ITEMS.register("crop_tea", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        ITEMS.register("crop_hops", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
    }

    private static void registerSeeds() {

        ItemGroup group = ItemGroupsTSeries.THERMAL_ITEMS;

        seedBarley = ITEMS.register("seed_barley", () -> new BlockNamedItemCoFH(plantBarley.get(), new Item.Properties().group(group)));
        seedOnion = ITEMS.register("seed_onion", () -> new BlockNamedItemCoFH(plantOnion.get(), new Item.Properties().group(group)));
        seedRice = ITEMS.register("seed_rice", () -> new BlockNamedItemCoFH(plantRice.get(), new Item.Properties().group(group)));
        seedSadiroot = ITEMS.register("seed_sadiroot", () -> new BlockNamedItemCoFH(plantSadiroot.get(), new Item.Properties().group(group)));
        seedSpinach = ITEMS.register("seed_spinach", () -> new BlockNamedItemCoFH(plantSpinach.get(), new Item.Properties().group(group)));
    }

    private static void registerFoods() {

        ItemGroup group = ItemGroupsTSeries.THERMAL_ITEMS;

        ITEMS.register("food_coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        ITEMS.register("food_dough", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        ITEMS.register("food_flour", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    private static void registerTools() {

    }

    public static RegistryObject<Item> cropBarley;
    public static RegistryObject<Item> cropOnion;
    public static RegistryObject<Item> cropRice;
    public static RegistryObject<Item> cropSadiroot;
    public static RegistryObject<Item> cropSpinach;

    public static RegistryObject<Item> seedBarley;
    public static RegistryObject<Item> seedOnion;
    public static RegistryObject<Item> seedRice;
    public static RegistryObject<Item> seedSadiroot;
    public static RegistryObject<Item> seedSpinach;

}
