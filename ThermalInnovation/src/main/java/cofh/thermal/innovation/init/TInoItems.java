package cofh.thermal.innovation.init;

import cofh.thermal.core.init.TCoreItems;
import cofh.thermal.innovation.item.FluxCapacitorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_TOOLS;
import static cofh.thermal.core.util.RegistrationHelper.registerItem;

public class TInoItems {

    private TInoItems() {

    }

    public static void register() {

        registerTools();
        registerArmor();
    }

    // region HELPERS
    private static void registerTools() {

        ItemGroup group = THERMAL_TOOLS;

        registerItem("flux_capacitor", () -> new FluxCapacitorItem(new Item.Properties().maxStackSize(1).group(group), 200000, 1000));
    }

    private static void registerArmor() {

        ItemGroup group = THERMAL_TOOLS;

        TCoreItems.registerHazmatArmor();
    }
    // endregion
}
