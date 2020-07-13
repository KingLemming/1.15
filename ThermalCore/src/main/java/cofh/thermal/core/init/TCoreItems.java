package cofh.thermal.core.init;

import cofh.lib.item.SpawnEggItemCoFH;
import cofh.lib.util.constants.ToolTypes;
import cofh.thermal.core.common.ThermalItemGroups;
import cofh.thermal.core.item.FertilizerItem;
import cofh.thermal.core.item.LockItem;
import cofh.thermal.core.item.RedprintItem;
import cofh.thermal.core.item.WrenchItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.TCoreReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.*;

public class TCoreItems {

    private TCoreItems() {

    }

    public static void register() {

        registerVanilla();
        registerResources();
        registerTools();
        registerSpawnEggs();
    }

    // region HELPERS
    private static void registerVanilla() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerMetalSet("iron", group, true);
        registerMetalSet("gold", group, true);

        registerGemSet("diamond", group, true);
        registerGemSet("emerald", group, true);
    }

    private static void registerResources() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        //        registerItem("cinnabar_dust", group);
        //        registerItem("niter_dust", group);
        //        registerItem("sulfur_dust", group);
        //        registerItem("obsidian_dust", group);
        registerItem("wood_dust", group);

        registerItem("apatite", group);
        registerItem("cinnabar", group);
        registerItem("niter", group);
        registerItem("sulfur", group);

        registerItem("basalz_rod", group);
        registerItem("basalz_powder", group);
        registerItem("blitz_rod", group);
        registerItem("blitz_powder", group);
        registerItem("blizz_rod", group);
        registerItem("blizz_powder", group);

        Rarity rarity = Rarity.UNCOMMON;

        registerMetalSet("copper");
        registerMetalSet("tin");
        registerMetalSet("lead");
        registerMetalSet("silver");
        registerMetalSet("nickel");

        registerMetalSet("bronze");
        registerMetalSet("electrum");
        registerMetalSet("invar");
        registerMetalSet("constantan");

        registerMetalSet("signalum", group, rarity);
        registerMetalSet("lumium", group, rarity);
        registerMetalSet("enderium", group, rarity);

        registerGemSet("ruby");
        registerGemSet("sapphire");
    }

    private static void registerTools() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("wrench", () -> new WrenchItem(new Item.Properties().maxStackSize(1).group(group).addToolType(ToolTypes.WRENCH, 1)));
        registerItem("redprint", () -> new RedprintItem(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("lock", () -> new LockItem(new Item.Properties().group(group)));
        registerItem("phytogro", () -> new FertilizerItem(new Item.Properties().group(group)).setRadius(2));
    }

    private static void registerSpawnEggs() {

        ITEMS.register("basalz_spawn_egg", () -> new SpawnEggItemCoFH(() -> BASALZ_ENTITY, 0x363840, 0x080407, new Item.Properties().group(ItemGroup.MISC)));
        ITEMS.register("blitz_spawn_egg", () -> new SpawnEggItemCoFH(() -> BLITZ_ENTITY, 0xECFEFC, 0x77A6BE, new Item.Properties().group(ItemGroup.MISC)));
        ITEMS.register("blizz_spawn_egg", () -> new SpawnEggItemCoFH(() -> BLIZZ_ENTITY, 0x7BD4FF, 0x0D6FD9, new Item.Properties().group(ItemGroup.MISC)));
    }
    // endregion
}
