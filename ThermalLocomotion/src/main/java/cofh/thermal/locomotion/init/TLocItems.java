package cofh.thermal.locomotion.init;

import cofh.lib.item.MinecartItemCoFH;
import cofh.thermal.core.common.ThermalItemGroups;
import cofh.thermal.locomotion.entity.*;
import cofh.thermal.locomotion.item.UnderwaterMinecartItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocItems {

    private TLocItems() {

    }

    public static void register() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        ITEMS.register(ID_UNDERWATER_CART, () -> new UnderwaterMinecartItem(new Item.Properties().group(group)));

        ITEMS.register(ID_PHYTO_TNT_CART, () -> new MinecartItemCoFH(PhytoTNTMinecartEntity::new, new Item.Properties().group(group)));

        ITEMS.register(ID_FIRE_TNT_CART, () -> new MinecartItemCoFH(FireTNTMinecartEntity::new, new Item.Properties().group(group)));
        ITEMS.register(ID_EARTH_TNT_CART, () -> new MinecartItemCoFH(EarthTNTMinecartEntity::new, new Item.Properties().group(group)));
        ITEMS.register(ID_ICE_TNT_CART, () -> new MinecartItemCoFH(IceTNTMinecartEntity::new, new Item.Properties().group(group)));
        ITEMS.register(ID_LIGHTNING_TNT_CART, () -> new MinecartItemCoFH(LightningTNTMinecartEntity::new, new Item.Properties().group(group)));

        ITEMS.register(ID_NUKE_TNT_CART, () -> new MinecartItemCoFH(NukeTNTMinecartEntity::new, new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
    }

}
