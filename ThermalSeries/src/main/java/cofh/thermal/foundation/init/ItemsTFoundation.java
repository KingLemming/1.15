package cofh.thermal.foundation.init;

import cofh.lib.item.ItemCoFH;
import cofh.thermal.core.init.ItemGroupsTSeries;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.ThermalSeries.ITEMS;

public class ItemsTFoundation {

    private ItemsTFoundation() {

    }

    public static void register() {

        registerThermalItems();
        registerAdditionalItems();
    }

    // region HELPERS
    private static void registerThermalItems() {

        ItemGroup group = ItemGroupsTSeries.THERMAL_ITEMS;

        ITEMS.register("ingot_copper", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_copper", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_copper", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_copper", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_copper", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_silver", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_silver", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_silver", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_silver", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_silver", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_lead", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_lead", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_lead", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_lead", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_lead", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_nickel", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_nickel", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_nickel", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_nickel", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_nickel", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_invar", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_invar", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_invar", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_invar", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_invar", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_constantan", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_constantan", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_constantan", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_constantan", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_constantan", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_electrum", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_electrum", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_electrum", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_electrum", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_electrum", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    private static void registerAdditionalItems() {

        ItemGroup group = ItemGroupsTSeries.THERMAL_ITEMS;

        ITEMS.register("ingot_tin", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_tin", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_tin", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_tin", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_tin", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_aluminum", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_aluminum", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_aluminum", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_aluminum", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_aluminum", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_platinum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("nugget_platinum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("dust_platinum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("gear_platinum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("plate_platinum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));

        ITEMS.register("ingot_iridium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("nugget_iridium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("dust_iridium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("gear_iridium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("plate_iridium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));

        ITEMS.register("ingot_steel", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_steel", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_steel", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_steel", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_steel", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_bronze", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("nugget_bronze", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_bronze", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_bronze", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_bronze", () -> new ItemCoFH(new Item.Properties().group(group)));
    }
    // endregion
}
