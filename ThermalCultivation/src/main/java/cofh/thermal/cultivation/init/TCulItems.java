package cofh.thermal.cultivation.init;

import cofh.lib.item.BlockNamedItemCoFH;
import cofh.lib.item.ItemCoFH;
import cofh.thermal.core.init.ThermalItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import static cofh.thermal.cultivation.ThermalCultivation.ITEMS;
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
        cropBarley = ITEMS.register(ID_BARLEY, () -> new ItemCoFH(new Item.Properties().group(group)));
        seedBarley = ITEMS.register(seeds(ID_BARLEY), () -> new BlockNamedItemCoFH(BARLEY_PLANT, new Item.Properties().group(group)));

        // cropCorn = ITEMS.register(ID_CORN, () -> new ItemCoFH(new Item.Properties().group(group).food(CORN)));
        // seedCorn = ITEMS.register(seeds(ID_CORN), () -> new BlockNamedItemCoFH(plantCorn.get(), new Item.Properties().group(group)));

        cropOnion = ITEMS.register(ID_ONION, () -> new ItemCoFH(new Item.Properties().group(group).food(ONION)));
        seedOnion = ITEMS.register(seeds(ID_ONION), () -> new BlockNamedItemCoFH(ONION_PLANT, new Item.Properties().group(group)));

        cropRice = ITEMS.register(ID_RICE, () -> new ItemCoFH(new Item.Properties().group(group)));
        seedRice = ITEMS.register(seeds(ID_RICE), () -> new BlockNamedItemCoFH(RICE_PLANT, new Item.Properties().group(group)));

        cropSadiroot = ITEMS.register(ID_SADIROOT, () -> new ItemCoFH(new Item.Properties().group(group)));
        seedSadiroot = ITEMS.register(seeds(ID_SADIROOT), () -> new BlockNamedItemCoFH(SADIROOT_PLANT, new Item.Properties().group(group)));

        cropSpinach = ITEMS.register(ID_SPINACH, () -> new ItemCoFH(new Item.Properties().group(group).food(SPINACH)));
        seedSpinach = ITEMS.register(seeds(ID_SPINACH), () -> new BlockNamedItemCoFH(SPINACH_PLANT, new Item.Properties().group(group)));

        // PERENNIAL
        cropBellPepper = ITEMS.register(ID_BELL_PEPPER, () -> new ItemCoFH(new Item.Properties().group(group).food(BELL_PEPPER)));
        seedBellPepper = ITEMS.register(seeds(ID_BELL_PEPPER), () -> new BlockNamedItemCoFH(BELL_PEPPER_PLANT, new Item.Properties().group(group)));

        cropGreenBean = ITEMS.register(ID_GREEN_BEAN, () -> new ItemCoFH(new Item.Properties().group(group).food(GREEN_BEAN)));
        seedGreenBean = ITEMS.register(seeds(ID_GREEN_BEAN), () -> new BlockNamedItemCoFH(GREEN_BEAN_PLANT, new Item.Properties().group(group)));

        cropPeanut = ITEMS.register(ID_PEANUT, () -> new ItemCoFH(new Item.Properties().group(group).food(PEANUT)));
        seedPeanut = ITEMS.register(seeds(ID_PEANUT), () -> new BlockNamedItemCoFH(PEANUT_PLANT, new Item.Properties().group(group)));

        cropStrawberry = ITEMS.register(ID_STRAWBERRY, () -> new ItemCoFH(new Item.Properties().group(group).food(STRAWBERRY)));
        seedStrawberry = ITEMS.register(seeds(ID_STRAWBERRY), () -> new BlockNamedItemCoFH(STRAWBERRY_PLANT, new Item.Properties().group(group)));

        cropTomato = ITEMS.register(ID_TOMATO, () -> new ItemCoFH(new Item.Properties().group(group).food(TOMATO)));
        seedTomato = ITEMS.register(seeds(ID_TOMATO), () -> new BlockNamedItemCoFH(TOMATO_PLANT, new Item.Properties().group(group)));

        // BREWING
        cropCoffee = ITEMS.register(ID_COFFEE, () -> new ItemCoFH(new Item.Properties().group(group).food(COFFEE_CHERRY)));
        seedCoffee = ITEMS.register(seeds(ID_COFFEE), () -> new BlockNamedItemCoFH(COFFEE_PLANT, new Item.Properties().group(group)));

        cropTea = ITEMS.register(ID_TEA, () -> new ItemCoFH(new Item.Properties().group(group)));
        seedTea = ITEMS.register(seeds(ID_TEA), () -> new BlockNamedItemCoFH(TEA_PLANT, new Item.Properties().group(group)));

        // cropHops = ITEMS.register(ID_HOPS, () -> new ItemCoFH(new Item.Properties().group(group)));
        // seedHops = ITEMS.register(seeds(ID_HOPS), () -> new BlockNamedItemCoFH(plantHops.get(), new Item.Properties().group(group)));
    }

    private static void registerFoods() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        //        ITEMS.register("food_coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_dough", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_flour", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    private static String seeds(String id) {

        return id + "_seeds";
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
