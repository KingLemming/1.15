package cofh.thermal.cultivation.init;

import cofh.lib.item.BlockNamedItemCoFH;
import cofh.lib.item.ItemCoFH;
import cofh.thermal.cultivation.item.WateringCanItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

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
        registerCropAndSeed(ID_CORN, CORN);
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
        registerCropAndSeed(ID_HOPS);
        registerCropAndSeed(ID_TEA);

        // OTHER
        ITEMS.register(ID_FROST_MELON_SLICE, () -> new ItemCoFH(new Item.Properties().group(THERMAL_ITEMS).food(FROST_MELON_SLICE).rarity(Rarity.UNCOMMON)));
        ITEMS.register(seeds(ID_FROST_MELON), () -> new BlockNamedItemCoFH(BLOCKS.get(ID_FROST_MELON_STEM), new Item.Properties().group(THERMAL_ITEMS).rarity(Rarity.UNCOMMON)));
    }

    private static void registerFoods() {

        ItemGroup group = THERMAL_ITEMS;

        //        ITEMS.register("coffee", () -> new ItemCoFH(new Item.Properties().group(group).food(COFFEE)));
        ITEMS.register("dough", () -> new ItemCoFH(new Item.Properties().group(group).food(DOUGH)));
        ITEMS.register("flour", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    private static void registerTools() {

        ItemGroup group = THERMAL_TOOLS;

        ITEMS.register("watering_can", () -> new WateringCanItem(new Item.Properties().maxStackSize(1).group(group), 4000));
    }

}
