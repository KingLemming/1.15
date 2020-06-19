package cofh.thermal.expansion.init;

import cofh.lib.item.ItemCoFH;
import cofh.thermal.core.common.ThermalItemGroups;
import cofh.thermal.core.init.TCoreItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_TOOLS;
import static cofh.thermal.core.util.RegistrationHelper.registerItem;

public class TExpItems {

    private TExpItems() {

    }

    public static void register() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerItem("press_coin_die", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("press_gear_die", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));

        registerTools();
    }

    public static void registerTools() {

        ItemGroup group = THERMAL_TOOLS;

        TCoreItems.registerHazmatArmor();
    }

}
