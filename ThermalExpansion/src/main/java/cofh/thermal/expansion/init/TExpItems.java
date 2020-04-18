package cofh.thermal.expansion.init;

import cofh.thermal.core.common.ThermalItemGroups;
import net.minecraft.item.ItemGroup;

import static cofh.thermal.core.util.RegistrationHelper.registerItem;

public class TExpItems {

    private TExpItems() {

    }

    public static void register() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerItem("press_coin_die", group);
        registerItem("press_gear_die", group);
    }

}
