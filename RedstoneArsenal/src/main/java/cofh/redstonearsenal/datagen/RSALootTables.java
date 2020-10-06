package cofh.redstonearsenal.datagen;

import cofh.core.datagen.LootTableProviderCoFH;
import cofh.core.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;

import static cofh.redstonearsenal.RedstoneArsenal.BLOCKS;
import static cofh.redstonearsenal.RedstoneArsenal.ITEMS;

public class RSALootTables extends LootTableProviderCoFH {

    public RSALootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Redstone Arsenal: Loot Tables";
    }

    @Override
    protected void addTables() {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        lootTables.put(regBlocks.get("flux_metal_block"), createSimpleDropTable(regBlocks.get("flux_metal_block")));
        lootTables.put(regBlocks.get("flux_gem_block"), createSimpleDropTable(regBlocks.get("flux_gem_block")));
    }

}