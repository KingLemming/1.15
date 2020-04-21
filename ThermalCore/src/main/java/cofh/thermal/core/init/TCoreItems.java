package cofh.thermal.core.init;

import cofh.thermal.core.common.ThermalItemGroups;
import cofh.thermal.core.item.FertilizerItem;
import cofh.thermal.core.item.LockItem;
import cofh.thermal.core.item.WrenchItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.util.RegistrationHelper.*;

public class TCoreItems {

    private TCoreItems() {

    }

    public static void register() {

        registerVanillaItems();
        registerThermalItems();
        registerTools();
    }

    private static void registerVanillaItems() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerMetalSet("iron", group, true);
        registerMetalSet("gold", group, true);

        registerGemSet("diamond", group, true);
        registerGemSet("emerald", group, true);
    }

    private static void registerThermalItems() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerItem("wood_dust", group);

        registerItem("apatite", group);
        registerItem("niter", group);
        registerItem("sulfur", group);

        Rarity rarity = Rarity.UNCOMMON;

        registerMetalSet("signalum", group, rarity);
        registerMetalSet("lumium", group, rarity);
        registerMetalSet("enderium", group, rarity);
    }

    private static void registerTools() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("wrench", () -> new WrenchItem(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("lock", () -> new LockItem(new Item.Properties().group(group)));
        registerItem("fertilizer", () -> new FertilizerItem(new Item.Properties().group(group)).setRadius(2));
    }

}
