package cofh.test.init;

import cofh.lib.item.ExcavatorItem;
import cofh.lib.item.HammerItem;
import cofh.lib.item.SickleItem;
import cofh.test.item.WrenchItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;

import static cofh.test.CoFHTest.COFH_TEST_GROUP;
import static cofh.test.CoFHTest.ITEMS;

public class ItemsTest {

    private ItemsTest() {

    }

    public static void register() {

        ItemGroup group = COFH_TEST_GROUP;

        ITEMS.register("wrench", () -> new WrenchItem(new Item.Properties().group(group)));

        ITEMS.register("diamond_excavator", () -> new ExcavatorItem(ItemTier.DIAMOND, new Item.Properties().group(group)));
        ITEMS.register("diamond_hammer", () -> new HammerItem(ItemTier.DIAMOND, new Item.Properties().group(group)));
        ITEMS.register("diamond_sickle", () -> new SickleItem(ItemTier.DIAMOND, new Item.Properties().group(group)));

        ItemsTCultivation.register();
    }

}
