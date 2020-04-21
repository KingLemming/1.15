package cofh.thermal.foundation.data;

import cofh.lib.data.LootTableProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.foundation.init.TFndReferences.*;

public class TFndLootTables extends LootTableProviderCoFH {

    public TFndLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Thermal Foundation: Loot Tables";
    }

    @Override
    protected void addTables() {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        lootTables.put(regBlocks.get(ID_COPPER_ORE), createSelfDropTable(regBlocks.get(ID_COPPER_ORE)));
        lootTables.put(regBlocks.get(ID_TIN_ORE), createSelfDropTable(regBlocks.get(ID_TIN_ORE)));
        lootTables.put(regBlocks.get(ID_SILVER_ORE), createSelfDropTable(regBlocks.get(ID_SILVER_ORE)));
        lootTables.put(regBlocks.get(ID_LEAD_ORE), createSelfDropTable(regBlocks.get(ID_LEAD_ORE)));
        lootTables.put(regBlocks.get(ID_NICKEL_ORE), createSelfDropTable(regBlocks.get(ID_NICKEL_ORE)));
        lootTables.put(regBlocks.get(ID_PLATINUM_ORE), createSelfDropTable(regBlocks.get(ID_PLATINUM_ORE)));

        lootTables.put(regBlocks.get(ID_RUBY_ORE), createSilkTouchOreTable(regBlocks.get(ID_RUBY_ORE), regItems.get("ruby_gem")));
        lootTables.put(regBlocks.get(ID_SAPPHIRE_ORE), createSilkTouchOreTable(regBlocks.get(ID_SAPPHIRE_ORE), regItems.get("sapphire_gem")));

        lootTables.put(regBlocks.get(ID_COPPER_BLOCK), createSelfDropTable(regBlocks.get(ID_COPPER_BLOCK)));
        lootTables.put(regBlocks.get(ID_TIN_BLOCK), createSelfDropTable(regBlocks.get(ID_TIN_BLOCK)));
        lootTables.put(regBlocks.get(ID_SILVER_BLOCK), createSelfDropTable(regBlocks.get(ID_SILVER_BLOCK)));
        lootTables.put(regBlocks.get(ID_LEAD_BLOCK), createSelfDropTable(regBlocks.get(ID_LEAD_BLOCK)));
        lootTables.put(regBlocks.get(ID_NICKEL_BLOCK), createSelfDropTable(regBlocks.get(ID_NICKEL_BLOCK)));
        lootTables.put(regBlocks.get(ID_PLATINUM_BLOCK), createSelfDropTable(regBlocks.get(ID_PLATINUM_BLOCK)));

        lootTables.put(regBlocks.get(ID_BRONZE_BLOCK), createSelfDropTable(regBlocks.get(ID_BRONZE_BLOCK)));
        lootTables.put(regBlocks.get(ID_ELECTRUM_BLOCK), createSelfDropTable(regBlocks.get(ID_ELECTRUM_BLOCK)));
        lootTables.put(regBlocks.get(ID_INVAR_BLOCK), createSelfDropTable(regBlocks.get(ID_INVAR_BLOCK)));
        lootTables.put(regBlocks.get(ID_CONSTANTAN_BLOCK), createSelfDropTable(regBlocks.get(ID_CONSTANTAN_BLOCK)));

        lootTables.put(regBlocks.get(ID_RUBY_BLOCK), createSelfDropTable(regBlocks.get(ID_RUBY_BLOCK)));
        lootTables.put(regBlocks.get(ID_SAPPHIRE_BLOCK), createSelfDropTable(regBlocks.get(ID_SAPPHIRE_BLOCK)));
    }

}
