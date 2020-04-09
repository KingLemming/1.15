package cofh.thermal.cultivation.init;

import net.minecraft.item.ItemGroup;

import static cofh.thermal.core.init.ThermalItemGroups.THERMAL_ITEMS;
import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.registerCropAndSeed;
import static cofh.thermal.cultivation.init.TCulFoods.*;

public class TCulItems {

    private TCulItems() {

    }

    public static void register() {

        registerCrops();
        registerFoods();
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
    }

    private static void registerFoods() {

        ItemGroup group = THERMAL_ITEMS;

        //        ITEMS.register("food_coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_dough", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_flour", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

}
