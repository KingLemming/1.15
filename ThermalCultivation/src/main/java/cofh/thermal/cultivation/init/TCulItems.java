package cofh.thermal.cultivation.init;

import net.minecraft.item.ItemGroup;

import static cofh.thermal.core.init.ThermalItemGroups.THERMAL_ITEMS;
import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.registerCropAndSeed;

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
        registerCropAndSeed(ID_ONION);
        registerCropAndSeed(ID_RICE);
        registerCropAndSeed(ID_SADIROOT);
        registerCropAndSeed(ID_SPINACH);

        // PERENNIAL
        registerCropAndSeed(ID_BELL_PEPPER);
        registerCropAndSeed(ID_GREEN_BEAN);
        registerCropAndSeed(ID_PEANUT);
        registerCropAndSeed(ID_STRAWBERRY);
        registerCropAndSeed(ID_TOMATO);

        // BREWING
        registerCropAndSeed(ID_COFFEE);
        registerCropAndSeed(ID_TEA);
    }

    private static void registerFoods() {

        ItemGroup group = THERMAL_ITEMS;

        //        ITEMS.register("food_coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_dough", () -> new ItemCoFH(new Item.Properties().group(group).food(TEMP)));
        //        ITEMS.register("food_flour", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

}
