package cofh.thermal.core.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemsTSeries {

    private ItemsTSeries() {

    }

    public static void registerItems(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> registry = event.getRegistry();
    }

}
