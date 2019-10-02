package cofh.thermal.core.init;

import cofh.lib.item.ItemCoFH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

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

        ItemGroup group = ItemGroupsTSeries.THERMAL_ITEMS;
        Rarity rarity = Rarity.UNCOMMON;

        register("ingot_signalum", group, rarity);
        register("nugget_signalum", group, rarity);
        register("dust_signalum", group, rarity);
        register("gear_signalum", group, rarity);
        register("plate_signalum", group, rarity);

        register("ingot_lumium", group, rarity);
        register("nugget_lumium", group, rarity);
        register("dust_lumium", group, rarity);
        register("gear_lumium", group, rarity);
        register("plate_lumium", group, rarity);

        register("ingot_enderium", group, rarity);
        register("nugget_enderium", group, rarity);
        register("dust_enderium", group, rarity);
        register("gear_enderium", group, rarity);
        register("plate_enderium", group, rarity);
    }

    private static RegistryObject<Item> register(String name, ItemGroup group) {

        return ITEMS.register(name, () -> new ItemCoFH(new Item.Properties().group(group)));
    }

    private static RegistryObject<Item> register(String name, ItemGroup group, Rarity rarity) {

        return ITEMS.register(name, () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
    }
    // endregion
}
