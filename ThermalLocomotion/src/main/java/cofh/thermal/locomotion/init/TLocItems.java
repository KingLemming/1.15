package cofh.thermal.locomotion.init;

import cofh.thermal.core.common.ThermalItemGroups;
import cofh.thermal.locomotion.item.UnderwaterMinecartItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.TLocIDs.ID_UNDERWATER_CART;

public class TLocItems {

    private TLocItems() {

    }

    public static void register() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        ITEMS.register(ID_UNDERWATER_CART, () -> new UnderwaterMinecartItem(new Item.Properties().group(group)));
    }

}
