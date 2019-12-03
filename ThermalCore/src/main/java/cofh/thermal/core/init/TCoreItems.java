package cofh.thermal.core.init;

import cofh.lib.item.CoinItem;
import cofh.lib.item.CountedItem;
import cofh.lib.item.ItemCoFH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

import static cofh.thermal.core.ThermalCore.ITEMS;

public class TCoreItems {

    private TCoreItems() {

    }

    public static void register() {

        //        registerVanillaItems();
        registerThermalItems();
    }

    private static void registerVanillaItems() {

        ItemGroup group = ItemGroup.MISC;

        register("dust_coal", group);
        register("dust_charcoal", group);
        register("dust_obsidian", group);

        register("ingot_wood", group);
        register("dust_wood", group);
        register("gear_wood", group);

        // register("ingot_stone", group);
        // register("dust_stone", group);
        register("gear_stone", group);

        // Ingot
        // Nugget
        register("dust_iron", group);
        register("gear_iron", group);
        register("plate_iron", group);

        // Ingot
        // Nugget
        register("dust_gold", group);
        register("gear_gold", group);
        register("plate_gold", group);

        // Gem
        register("nugget_diamond", group);
        register("dust_diamond", group);
        register("gear_diamond", group);
        register("plate_diamond", group);

        // Gem
        register("nugget_emerald", group);
        register("dust_emerald", group);
        register("gear_emerald", group);
        register("plate_emerald", group);
    }

    private static void registerThermalItems() {

        ItemGroup group = TCoreItemGroups.THERMAL_ITEMS;
        Rarity rarity = Rarity.UNCOMMON;

        registerMetalSet("signalum", group, rarity);
        registerMetalSet("lumium", group, rarity);
        registerMetalSet("enderium", group, rarity);
    }

    // region HELPERS
    private static RegistryObject<Item> register(String name, ItemGroup group) {

        return ITEMS.register(name, () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    private static RegistryObject<Item> register(String name, ItemGroup group, Rarity rarity) {

        return ITEMS.register(name, () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
    }

    private static void registerMetalSet(String prefix, ItemGroup group) {

        registerMetalSet(prefix, group, Rarity.COMMON);
    }

    private static void registerGemSet(String prefix, ItemGroup group) {

        registerGemSet(prefix, group, Rarity.COMMON);
    }

    private static void registerMetalSet(String prefix, ItemGroup group, Rarity rarity) {

        ITEMS.register(prefix + "_ingot", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_nugget", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_dust", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_gear", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_plate", () -> new CountedItem(new Item.Properties().group(group).rarity(rarity)));

        ITEMS.register(prefix + "_coin", () -> new CoinItem(new Item.Properties().group(group).rarity(rarity)));
    }

    private static void registerGemSet(String prefix, ItemGroup group, Rarity rarity) {

        ITEMS.register(prefix + "_gem", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_nugget", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_dust", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_gear", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        ITEMS.register(prefix + "_plate", () -> new CountedItem(new Item.Properties().group(group).rarity(rarity)));

        ITEMS.register(prefix + "_coin", () -> new CoinItem(new Item.Properties().group(group).rarity(rarity)));
    }
    // endregion
}
