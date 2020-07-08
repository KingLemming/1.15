package cofh.thermal.innovation.init;

import cofh.thermal.core.init.TCoreItems;
import cofh.thermal.innovation.item.*;
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

        int energy = 40000;
        int xfer = 1000;
        int fluid = 4000;
        int arrows = 80;

        registerItem("flux_drill", () -> new RFDrillItem(new Item.Properties().maxStackSize(1).group(group), energy * 100, xfer / 10));
        registerItem("flux_saw", () -> new RFSawItem(new Item.Properties().maxStackSize(1).group(group), energy, xfer));
        registerItem("flux_capacitor", () -> new RFCapacitorItem(new Item.Properties().maxStackSize(1).group(group), energy * 10, xfer));
        registerItem("flux_magnet", () -> new RFMagnetItem(new Item.Properties().maxStackSize(1).group(group), energy, xfer));
        registerItem("potion_infuser", () -> new PotionInfuserItem(new Item.Properties().maxStackSize(1).group(group), fluid));
        registerItem("potion_quiver", () -> new PotionQuiverItem(new Item.Properties().maxStackSize(1).group(group), fluid, arrows));
    }

    private static void registerArmor() {

        ItemGroup group = THERMAL_TOOLS;

        TCoreItems.registerHazmatArmor();
    }
    // endregion
}
