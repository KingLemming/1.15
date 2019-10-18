package cofh.test.init;

import cofh.lib.item.BlockNamedItemCoFH;
import cofh.lib.item.ItemCoFH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import static cofh.test.CoFHTest.COFH_TEST_GROUP;
import static cofh.test.CoFHTest.ITEMS;
import static cofh.test.init.BlocksTCultivation.*;
import static cofh.test.init.FoodsTCultivation.TEMP;

public class ItemsTCultivation {

    private ItemsTCultivation() {

    }

    public static void register() {

        registerCrops();
        registerSeeds();
    }

    private static void registerCrops() {

        ItemGroup group = COFH_TEST_GROUP;

        cropBarley = ITEMS.register("crop_barley", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropOnion = ITEMS.register("crop_onion", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropRice = ITEMS.register("crop_rice", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropSadiroot = ITEMS.register("crop_sadiroot", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropSpinach = ITEMS.register("crop_spinach", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //
        //        ITEMS.register("crop_corn", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //
        cropBellPepper = ITEMS.register("crop_bell_pepper", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropGreenBean = ITEMS.register("crop_green_bean", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropPeanut = ITEMS.register("crop_peanut", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropStrawberry = ITEMS.register("crop_strawberry", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        cropTomato = ITEMS.register("crop_tomato", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //
        //        ITEMS.register("crop_coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("crop_tea", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("crop_hops", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
    }

    private static void registerSeeds() {

        ItemGroup group = COFH_TEST_GROUP;

        seedBarley = ITEMS.register("seed_barley", () -> new BlockNamedItemCoFH(plantBarley.get(), new Item.Properties().group(group)));
        seedOnion = ITEMS.register("seed_onion", () -> new BlockNamedItemCoFH(plantOnion.get(), new Item.Properties().group(group)));
        seedRice = ITEMS.register("seed_rice", () -> new BlockNamedItemCoFH(plantRice.get(), new Item.Properties().group(group)));
        seedSadiroot = ITEMS.register("seed_sadiroot", () -> new BlockNamedItemCoFH(plantSadiroot.get(), new Item.Properties().group(group)));
        seedSpinach = ITEMS.register("seed_spinach", () -> new BlockNamedItemCoFH(plantSpinach.get(), new Item.Properties().group(group)));

        seedBellPepper = ITEMS.register("seed_bell_pepper", () -> new BlockNamedItemCoFH(plantBellPepper.get(), new Item.Properties().group(group)));
        seedGreenBean = ITEMS.register("seed_green_bean", () -> new BlockNamedItemCoFH(plantGreenBean.get(), new Item.Properties().group(group)));
        seedPeanut = ITEMS.register("seed_peanut", () -> new BlockNamedItemCoFH(plantPeanut.get(), new Item.Properties().group(group)));
        seedStrawberry = ITEMS.register("seed_strawberry", () -> new BlockNamedItemCoFH(plantStrawberry.get(), new Item.Properties().group(group)));
        seedTomato = ITEMS.register("seed_tomato", () -> new BlockNamedItemCoFH(plantTomato.get(), new Item.Properties().group(group)));
    }

    private static void registerFoods() {

        ItemGroup group = COFH_TEST_GROUP;

        //        ITEMS.register("food_coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_dough", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_flour", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    private static void registerTools() {

    }

    public static RegistryObject<Item> cropBarley;
    public static RegistryObject<Item> cropOnion;
    public static RegistryObject<Item> cropRice;
    public static RegistryObject<Item> cropSadiroot;
    public static RegistryObject<Item> cropSpinach;

    public static RegistryObject<Item> cropBellPepper;
    public static RegistryObject<Item> cropGreenBean;
    public static RegistryObject<Item> cropPeanut;
    public static RegistryObject<Item> cropStrawberry;
    public static RegistryObject<Item> cropTomato;

    public static RegistryObject<Item> seedBarley;
    public static RegistryObject<Item> seedOnion;
    public static RegistryObject<Item> seedRice;
    public static RegistryObject<Item> seedSadiroot;
    public static RegistryObject<Item> seedSpinach;

    public static RegistryObject<Item> seedBellPepper;
    public static RegistryObject<Item> seedGreenBean;
    public static RegistryObject<Item> seedPeanut;
    public static RegistryObject<Item> seedStrawberry;
    public static RegistryObject<Item> seedTomato;

}
