package cofh.thermal.core.init;

import cofh.lib.item.WrenchItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.util.RegistrationHelper.*;

public class TCoreItems {

    private TCoreItems() {

    }

    public static void register() {

        //        registerVanillaItems();
        registerThermalItems();
    }

    private static void registerVanillaItems() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        //        register("dust_coal", group);
        //        register("dust_charcoal", group);
        //        register("dust_obsidian", group);
        //
        //        register("ingot_wood", group);
        //        register("dust_wood", group);
        //        register("gear_wood", group);
        //
        //        // register("ingot_stone", group);
        //        // register("dust_stone", group);
        //        register("gear_stone", group);

        registerMetalSet("iron", group, true);
        registerMetalSet("gold", group, true);

        registerGemSet("diamond", group, true);
        registerGemSet("emerald", group, true);
    }

    private static void registerThermalItems() {

        registerItem("wrench", () -> new WrenchItem(new Item.Properties().group(ThermalItemGroups.THERMAL_TOOLS)));

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;
        Rarity rarity = Rarity.UNCOMMON;

        registerMetalSet("signalum", group, rarity);
        registerMetalSet("lumium", group, rarity);
        registerMetalSet("enderium", group, rarity);
    }

}
