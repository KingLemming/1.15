package cofh.core.init;

import cofh.lib.item.ExcavatorItem;
import cofh.lib.item.HammerItem;
import cofh.lib.item.SickleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;

import static cofh.core.CoFHCore.ITEMS;

public class ItemsCoreTest {

    public static void register() {

        ITEMS.register("diamond_excavator", () -> new ExcavatorItem(ItemTier.DIAMOND, new Item.Properties().group(ItemGroup.TOOLS)));
        ITEMS.register("diamond_hammer", () -> new HammerItem(ItemTier.DIAMOND, new Item.Properties().group(ItemGroup.TOOLS)));
        ITEMS.register("diamond_sickle", () -> new SickleItem(ItemTier.DIAMOND, new Item.Properties().group(ItemGroup.TOOLS)));
    }

}
