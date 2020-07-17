package cofh.thermal.expansion.init;

import cofh.lib.item.ItemCoFH;
import cofh.thermal.core.common.ThermalFeatures;
import cofh.thermal.core.common.ThermalItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.thermal.core.util.RegistrationHelper.registerItem;

public class TExpItems {

    private TExpItems() {

    }

    public static void register() {

        ThermalFeatures.registerBasicParts();
        ThermalFeatures.registerToolParts();

        ThermalFeatures.registerUpgradeAugments();
        ThermalFeatures.registerStorageAugments();
        ThermalFeatures.registerMachineAugments();
        ThermalFeatures.registerDynamoAugments();

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerItem("press_coin_die", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("press_gear_die", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));

        // TODO: Chiller Molds
    }

}
