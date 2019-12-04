package cofh.thermal.cultivation.init;

import cofh.lib.item.BlockNamedItemCoFH;
import cofh.lib.item.ItemCoFH;
import cofh.thermal.core.init.ThermalItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.cultivation.init.TCulFoods.*;
import static cofh.thermal.cultivation.init.TCulReferences.*;

public class TCulItems {

    private TCulItems() {

    }

    public static void register() {

        registerCrops();
        registerFoods();
    }

    private static void registerCrops() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        // ANNUAL
        cropBarley = ITEMS.register("barley", () -> new ItemCoFH(new Item.Properties().group(group)));
        seedBarley = ITEMS.register("barley_seeds", () -> new BlockNamedItemCoFH(BARLEY_PLANT, new Item.Properties().group(group)));

        // cropCorn = ITEMS.register("corn", () -> new ItemCoFH(new Item.Properties().group(group).food(CORN)));
        // seedCorn = ITEMS.register("corn_seeds", () -> new BlockNamedItemCoFH(plantCorn.get(), new Item.Properties().group(group)));

        cropOnion = ITEMS.register("onion", () -> new ItemCoFH(new Item.Properties().group(group).food(ONION)));
        seedOnion = ITEMS.register("onion_seeds", () -> new BlockNamedItemCoFH(ONION_PLANT, new Item.Properties().group(group)));

        cropRice = ITEMS.register("rice", () -> new ItemCoFH(new Item.Properties().group(group)));
        seedRice = ITEMS.register("rice_seeds", () -> new BlockNamedItemCoFH(RICE_PLANT, new Item.Properties().group(group)));

        cropSadiroot = ITEMS.register("sadiroot", () -> new ItemCoFH(new Item.Properties().group(group)));
        seedSadiroot = ITEMS.register("sadiroot_seeds", () -> new BlockNamedItemCoFH(SADIROOT_PLANT, new Item.Properties().group(group)));

        cropSpinach = ITEMS.register("spinach", () -> new ItemCoFH(new Item.Properties().group(group).food(SPINACH)));
        seedSpinach = ITEMS.register("spinach_seeds", () -> new BlockNamedItemCoFH(SPINACH_PLANT, new Item.Properties().group(group)));

        // PERENNIAL
        cropBellPepper = ITEMS.register("bell_pepper", () -> new ItemCoFH(new Item.Properties().group(group).food(BELL_PEPPER)));
        seedBellPepper = ITEMS.register("bell_pepper_seeds", () -> new BlockNamedItemCoFH(BELL_PEPPER_PLANT, new Item.Properties().group(group)));

        cropGreenBean = ITEMS.register("green_bean", () -> new ItemCoFH(new Item.Properties().group(group).food(GREEN_BEAN)));
        seedGreenBean = ITEMS.register("green_bean_seeds", () -> new BlockNamedItemCoFH(GREEN_BEAN_PLANT, new Item.Properties().group(group)));

        cropPeanut = ITEMS.register("peanut", () -> new ItemCoFH(new Item.Properties().group(group).food(PEANUT)));
        seedPeanut = ITEMS.register("peanut_seeds", () -> new BlockNamedItemCoFH(PEANUT_PLANT, new Item.Properties().group(group)));

        cropStrawberry = ITEMS.register("strawberry", () -> new ItemCoFH(new Item.Properties().group(group).food(STRAWBERRY)));
        seedStrawberry = ITEMS.register("strawberry_seeds", () -> new BlockNamedItemCoFH(STRAWBERRY_PLANT, new Item.Properties().group(group)));

        cropTomato = ITEMS.register("tomato", () -> new ItemCoFH(new Item.Properties().group(group).food(TOMATO)));
        seedTomato = ITEMS.register("tomato_seeds", () -> new BlockNamedItemCoFH(TOMATO_PLANT, new Item.Properties().group(group)));

        // BREWING
        cropCoffee = ITEMS.register("coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(COFFEE_CHERRY)));
        seedCoffee = ITEMS.register("coffee_seeds", () -> new BlockNamedItemCoFH(COFFEE_PLANT, new Item.Properties().group(group)));

        cropTea = ITEMS.register("tea", () -> new ItemCoFH(new Item.Properties().group(group)));
        seedTea = ITEMS.register("tea_seeds", () -> new BlockNamedItemCoFH(TEA_PLANT, new Item.Properties().group(group)));

        // cropHops = ITEMS.register("hops", () -> new ItemCoFH(new Item.Properties().group(group)));
        // seedHops = ITEMS.register("hops_seeds", () -> new BlockNamedItemCoFH(plantHops.get(), new Item.Properties().group(group)));
    }

    private static void registerFoods() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        //        ITEMS.register("food_coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_dough", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_flour", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    public static RegistryObject<Item> cropBarley;
    public static RegistryObject<Item> seedBarley;

    public static RegistryObject<Item> cropOnion;
    public static RegistryObject<Item> seedOnion;

    public static RegistryObject<Item> cropRice;
    public static RegistryObject<Item> seedRice;

    public static RegistryObject<Item> cropSadiroot;
    public static RegistryObject<Item> seedSadiroot;

    public static RegistryObject<Item> cropSpinach;
    public static RegistryObject<Item> seedSpinach;

    public static RegistryObject<Item> cropCorn;
    public static RegistryObject<Item> seedCorn;

    public static RegistryObject<Item> cropBellPepper;
    public static RegistryObject<Item> seedBellPepper;

    public static RegistryObject<Item> cropGreenBean;
    public static RegistryObject<Item> seedGreenBean;

    public static RegistryObject<Item> cropPeanut;
    public static RegistryObject<Item> seedPeanut;

    public static RegistryObject<Item> cropStrawberry;
    public static RegistryObject<Item> seedStrawberry;

    public static RegistryObject<Item> cropTomato;
    public static RegistryObject<Item> seedTomato;

    public static RegistryObject<Item> cropCoffee;
    public static RegistryObject<Item> seedCoffee;

    public static RegistryObject<Item> cropTea;
    public static RegistryObject<Item> seedTea;

    public static RegistryObject<Item> cropHops;
    public static RegistryObject<Item> seedHops;

}
