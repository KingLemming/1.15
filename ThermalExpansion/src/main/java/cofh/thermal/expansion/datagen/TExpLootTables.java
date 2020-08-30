package cofh.thermal.expansion.datagen;

import cofh.lib.datagen.LootTableProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.thermal.core.util.loot.TileNBTSync;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.expansion.init.TExpIDs.*;

public class TExpLootTables extends LootTableProviderCoFH {

    public TExpLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Thermal Expansion: Loot Tables";
    }

    @Override
    protected void addTables() {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        lootTables.put(regBlocks.get(ID_MACHINE_FURNACE), createSyncDropTable(regBlocks.get(ID_MACHINE_FURNACE)));
        lootTables.put(regBlocks.get(ID_MACHINE_SAWMILL), createSyncDropTable(regBlocks.get(ID_MACHINE_SAWMILL)));
        lootTables.put(regBlocks.get(ID_MACHINE_PULVERIZER), createSyncDropTable(regBlocks.get(ID_MACHINE_PULVERIZER)));
        lootTables.put(regBlocks.get(ID_MACHINE_SMELTER), createSyncDropTable(regBlocks.get(ID_MACHINE_SMELTER)));
        lootTables.put(regBlocks.get(ID_MACHINE_INSOLATOR), createSyncDropTable(regBlocks.get(ID_MACHINE_INSOLATOR)));
        lootTables.put(regBlocks.get(ID_MACHINE_CENTRIFUGE), createSyncDropTable(regBlocks.get(ID_MACHINE_CENTRIFUGE)));
        lootTables.put(regBlocks.get(ID_MACHINE_PRESS), createSyncDropTable(regBlocks.get(ID_MACHINE_PRESS)));
        lootTables.put(regBlocks.get(ID_MACHINE_CRUCIBLE), createSyncDropTable(regBlocks.get(ID_MACHINE_CRUCIBLE)));
        lootTables.put(regBlocks.get(ID_MACHINE_CHILLER), createSyncDropTable(regBlocks.get(ID_MACHINE_CHILLER)));
        lootTables.put(regBlocks.get(ID_MACHINE_REFINERY), createSyncDropTable(regBlocks.get(ID_MACHINE_REFINERY)));
        lootTables.put(regBlocks.get(ID_MACHINE_BREWER), createSyncDropTable(regBlocks.get(ID_MACHINE_BREWER)));
        lootTables.put(regBlocks.get(ID_MACHINE_BOTTLER), createSyncDropTable(regBlocks.get(ID_MACHINE_BOTTLER)));

        lootTables.put(regBlocks.get(ID_DYNAMO_STIRLING), createSyncDropTable(regBlocks.get(ID_DYNAMO_STIRLING)));
        lootTables.put(regBlocks.get(ID_DYNAMO_COMPRESSION), createSyncDropTable(regBlocks.get(ID_DYNAMO_COMPRESSION)));
        lootTables.put(regBlocks.get(ID_DYNAMO_MAGMATIC), createSyncDropTable(regBlocks.get(ID_DYNAMO_MAGMATIC)));
        lootTables.put(regBlocks.get(ID_DYNAMO_NUMISMATIC), createSyncDropTable(regBlocks.get(ID_DYNAMO_NUMISMATIC)));
        lootTables.put(regBlocks.get(ID_DYNAMO_LAPIDARY), createSyncDropTable(regBlocks.get(ID_DYNAMO_LAPIDARY)));
    }

    protected LootTable.Builder createSyncDropTable(Block block) {

        LootPool.Builder builder = LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                        .acceptFunction(TileNBTSync.builder()))
                .acceptCondition(SurvivesExplosion.builder());
        return LootTable.builder().addLootPool(builder);
    }

}
