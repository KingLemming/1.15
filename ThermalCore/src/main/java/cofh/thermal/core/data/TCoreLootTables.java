package cofh.thermal.core.data;

import cofh.lib.data.LootTableProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.SetCount;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.TCoreReferences.*;

public class TCoreLootTables extends LootTableProviderCoFH {

    public TCoreLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Thermal Core: Loot Tables";
    }

    @Override
    protected void addTables() {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        lootTables.put(regBlocks.get(ID_CHARCOAL_BLOCK), createSimpleDropTable(regBlocks.get(ID_CHARCOAL_BLOCK)));
        lootTables.put(regBlocks.get(ID_BAMBOO_BLOCK), createSimpleDropTable(regBlocks.get(ID_BAMBOO_BLOCK)));
        lootTables.put(regBlocks.get(ID_SUGAR_CANE_BLOCK), createSimpleDropTable(regBlocks.get(ID_SUGAR_CANE_BLOCK)));
        lootTables.put(regBlocks.get(ID_GUNPOWDER_BLOCK), createSimpleDropTable(regBlocks.get(ID_GUNPOWDER_BLOCK)));

        lootTables.put(regBlocks.get(ID_APATITE_BLOCK), createSimpleDropTable(regBlocks.get(ID_APATITE_BLOCK)));
        lootTables.put(regBlocks.get(ID_CINNABAR_BLOCK), createSimpleDropTable(regBlocks.get(ID_CINNABAR_BLOCK)));
        lootTables.put(regBlocks.get(ID_NITER_BLOCK), createSimpleDropTable(regBlocks.get(ID_NITER_BLOCK)));
        lootTables.put(regBlocks.get(ID_SULFUR_BLOCK), createSimpleDropTable(regBlocks.get(ID_SULFUR_BLOCK)));

        lootTables.put(regBlocks.get(ID_SIGNALUM_BLOCK), createSimpleDropTable(regBlocks.get(ID_SIGNALUM_BLOCK)));
        lootTables.put(regBlocks.get(ID_LUMIUM_BLOCK), createSimpleDropTable(regBlocks.get(ID_LUMIUM_BLOCK)));
        lootTables.put(regBlocks.get(ID_ENDERIUM_BLOCK), createSimpleDropTable(regBlocks.get(ID_ENDERIUM_BLOCK)));

        lootTables.put(regBlocks.get(ID_SIGNALUM_GLASS), BlockLootTables.onlyWithSilkTouch(regBlocks.get(ID_SIGNALUM_GLASS)));
        lootTables.put(regBlocks.get(ID_LUMIUM_GLASS), BlockLootTables.onlyWithSilkTouch(regBlocks.get(ID_LUMIUM_GLASS)));
        lootTables.put(regBlocks.get(ID_ENDERIUM_GLASS), BlockLootTables.onlyWithSilkTouch(regBlocks.get(ID_ENDERIUM_GLASS)));

        lootTables.put(regBlocks.get(ID_APATITE_ORE), BlockLootTables.droppingWithSilkTouch(regBlocks.get(ID_APATITE_ORE), BlockLootTables.withExplosionDecay(regBlocks.get(ID_APATITE_ORE), ItemLootEntry.builder(regItems.get("apatite"))
                .acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F)))
                .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
        lootTables.put(regBlocks.get(ID_CINNABAR_ORE), createSilkTouchOreTable(regBlocks.get(ID_CINNABAR_ORE), regItems.get("cinnabar")));
        lootTables.put(regBlocks.get(ID_NITER_ORE), BlockLootTables.droppingWithSilkTouch(regBlocks.get(ID_NITER_ORE), BlockLootTables.withExplosionDecay(regBlocks.get(ID_NITER_ORE), ItemLootEntry.builder(regItems.get("niter"))
                .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F)))
                .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
        lootTables.put(regBlocks.get(ID_SULFUR_ORE), BlockLootTables.droppingWithSilkTouch(regBlocks.get(ID_SULFUR_ORE), BlockLootTables.withExplosionDecay(regBlocks.get(ID_SULFUR_ORE), ItemLootEntry.builder(regItems.get("sulfur"))
                .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F)))
                .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));

    }

}
