package cofh.thermal.foundation.init;

import cofh.lib.block.storage.MetalStorageBlock;
import cofh.lib.item.BlockItemCoFH;
import cofh.thermal.core.init.ThermalItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.foundation.ThermalFoundation.BLOCKS;
import static cofh.thermal.foundation.ThermalFoundation.ITEMS;
import static net.minecraft.block.material.MaterialColor.BLUE;
import static net.minecraft.block.material.MaterialColor.RED;

public class TFndBlocks {

    private TFndBlocks() {

    }

    public static void register() {

        registerThermalMetals();
        registerThermalGems();
    }

    private static void registerThermalMetals() {

        BLOCKS.register(ID_COPPER_BLOCK, () -> new MetalStorageBlock());
        BLOCKS.register(ID_TIN_BLOCK, () -> new MetalStorageBlock());
        BLOCKS.register(ID_SILVER_BLOCK, () -> new MetalStorageBlock());
        BLOCKS.register(ID_LEAD_BLOCK, () -> new MetalStorageBlock());
        BLOCKS.register(ID_NICKEL_BLOCK, () -> new MetalStorageBlock());
        BLOCKS.register(ID_PLATINUM_BLOCK, () -> new MetalStorageBlock());

        BLOCKS.register(ID_BRONZE_BLOCK, () -> new MetalStorageBlock());
        BLOCKS.register(ID_ELECTRUM_BLOCK, () -> new MetalStorageBlock());
        BLOCKS.register(ID_INVAR_BLOCK, () -> new MetalStorageBlock());
        BLOCKS.register(ID_CONSTANTAN_BLOCK, () -> new MetalStorageBlock());

        ItemGroup group = ThermalItemGroups.THERMAL_BLOCKS;

        ITEMS.register(ID_COPPER_BLOCK, () -> new BlockItemCoFH(COPPER_BLOCK, new Item.Properties().group(group)));
        ITEMS.register(ID_TIN_BLOCK, () -> new BlockItemCoFH(TIN_BLOCK, new Item.Properties().group(group)));
        ITEMS.register(ID_SILVER_BLOCK, () -> new BlockItemCoFH(SILVER_BLOCK, new Item.Properties().group(group)));
        ITEMS.register(ID_LEAD_BLOCK, () -> new BlockItemCoFH(LEAD_BLOCK, new Item.Properties().group(group)));
        ITEMS.register(ID_NICKEL_BLOCK, () -> new BlockItemCoFH(NICKEL_BLOCK, new Item.Properties().group(group)));
        ITEMS.register(ID_PLATINUM_BLOCK, () -> new BlockItemCoFH(PLATINUM_BLOCK, new Item.Properties().group(group)));

        ITEMS.register(ID_BRONZE_BLOCK, () -> new BlockItemCoFH(BRONZE_BLOCK, new Item.Properties().group(group)));
        ITEMS.register(ID_ELECTRUM_BLOCK, () -> new BlockItemCoFH(ELECTRUM_BLOCK, new Item.Properties().group(group)));
        ITEMS.register(ID_INVAR_BLOCK, () -> new BlockItemCoFH(INVAR_BLOCK, new Item.Properties().group(group)));
        ITEMS.register(ID_CONSTANTAN_BLOCK, () -> new BlockItemCoFH(CONSTANTAN_BLOCK, new Item.Properties().group(group)));
    }

    private static void registerThermalGems() {

        BLOCKS.register(ID_RUBY_BLOCK, () -> new MetalStorageBlock(RED));
        BLOCKS.register(ID_SAPPHIRE_BLOCK, () -> new MetalStorageBlock(BLUE));

        ItemGroup group = ThermalItemGroups.THERMAL_BLOCKS;

        ITEMS.register(ID_RUBY_BLOCK, () -> new BlockItemCoFH(RUBY_BLOCK, new Item.Properties().group(group)));
        ITEMS.register(ID_SAPPHIRE_BLOCK, () -> new BlockItemCoFH(SAPPHIRE_BLOCK, new Item.Properties().group(group)));
    }

    private static void registerExtraMetals() {

    }

}
