package cofh.thermal.core.init;

import cofh.lib.item.ItemCoFH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static cofh.lib.util.modhelpers.ThermalHelper.*;

public class ItemsTSeries {

    private ItemsTSeries() {

    }

    public static void registerItems(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> registry = event.getRegistry();

        ItemGroup group = ItemGroupsTSeries.THERMAL_ITEMS;

        registry.register(new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)).setRegistryName(ID_SIGNALUM_INGOT));
        registry.register(new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)).setRegistryName(ID_LUMIUM_INGOT));
        registry.register(new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.RARE)).setRegistryName(ID_ENDERIUM_INGOT));

        registry.register(new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)).setRegistryName(ID_SIGNALUM_NUGGET));
        registry.register(new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)).setRegistryName(ID_LUMIUM_NUGGET));
        registry.register(new ItemCoFH(new Item.Properties().group(group).rarity(Rarity.RARE)).setRegistryName(ID_ENDERIUM_NUGGET));
    }

}
