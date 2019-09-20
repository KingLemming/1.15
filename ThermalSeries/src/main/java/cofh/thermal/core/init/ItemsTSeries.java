package cofh.thermal.core.init;

import cofh.lib.item.ItemCoFH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.ThermalSeries.ITEMS;

public class ItemsTSeries {

    private ItemsTSeries() {

    }

    public static void register() {

        registerVanillaItems();
        registerThermalItems();
    }

    // region HELPERS
    private static void registerVanillaItems() {

        ItemGroup group = ItemGroup.MISC;

        ITEMS.register("dust_coal", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_charcoal", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_obsidian", () -> new ItemCoFH(new Item.Properties().group(group)));

        ITEMS.register("ingot_wood", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_wood", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_wood", () -> new ItemCoFH(new Item.Properties().group(group)));

        // ITEMS.register("ingot_stone", () -> new ItemCoFH(new Item.Properties().group(group)));
        // ITEMS.register("dust_stone", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_stone", () -> new ItemCoFH(new Item.Properties().group(group)));

        // Ingot
        // Nugget
        ITEMS.register("dust_iron", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_iron", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_iron", () -> new ItemCoFH(new Item.Properties().group(group)));

        // Ingot
        // Nugget
        ITEMS.register("dust_gold", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_gold", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_gold", () -> new ItemCoFH(new Item.Properties().group(group)));

        // Gem
        ITEMS.register("nugget_diamond", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_diamond", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_diamond", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_diamond", () -> new ItemCoFH(new Item.Properties().group(group)));

        // Gem
        ITEMS.register("nugget_emerald", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("dust_emerald", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("gear_emerald", () -> new ItemCoFH(new Item.Properties().group(group)));
        ITEMS.register("plate_emerald", () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    private static void registerThermalItems() {

        ItemGroup group = ItemGroupsTSeries.THERMAL_ITEMS;

        ITEMS.register("ingot_signalum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("nugget_signalum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("dust_signalum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("gear_signalum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("plate_signalum", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));

        ITEMS.register("ingot_lumium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("nugget_lumium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("dust_lumium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("gear_lumium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("plate_lumium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));

        ITEMS.register("ingot_enderium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("nugget_enderium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("dust_enderium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("gear_enderium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register("plate_enderium", () -> new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
    }
    // endregion
}
