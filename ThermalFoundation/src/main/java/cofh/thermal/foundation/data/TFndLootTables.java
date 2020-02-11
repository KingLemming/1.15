package cofh.thermal.foundation.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.ThermalReferences.*;

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

        lootTables.put(BLOCKS.get(ID_COPPER_ORE), createSimpleTable(BLOCKS.get(ID_COPPER_ORE)));
        lootTables.put(BLOCKS.get(ID_TIN_ORE), createSimpleTable(BLOCKS.get(ID_TIN_ORE)));
        lootTables.put(BLOCKS.get(ID_SILVER_ORE), createSimpleTable(BLOCKS.get(ID_SILVER_ORE)));
        lootTables.put(BLOCKS.get(ID_LEAD_ORE), createSimpleTable(BLOCKS.get(ID_LEAD_ORE)));
        lootTables.put(BLOCKS.get(ID_NICKEL_ORE), createSimpleTable(BLOCKS.get(ID_NICKEL_ORE)));
        lootTables.put(BLOCKS.get(ID_PLATINUM_ORE), createSimpleTable(BLOCKS.get(ID_PLATINUM_ORE)));

        lootTables.put(BLOCKS.get(ID_RUBY_ORE), createSilkTouchOreTable(BLOCKS.get(ID_RUBY_ORE), ITEMS.get("ruby_gem")));
        lootTables.put(BLOCKS.get(ID_SAPPHIRE_ORE), createSilkTouchOreTable(BLOCKS.get(ID_SAPPHIRE_ORE), ITEMS.get("sapphire_gem")));

        lootTables.put(BLOCKS.get(ID_COPPER_BLOCK), createSimpleTable(BLOCKS.get(ID_COPPER_BLOCK)));
        lootTables.put(BLOCKS.get(ID_TIN_BLOCK), createSimpleTable(BLOCKS.get(ID_TIN_BLOCK)));
        lootTables.put(BLOCKS.get(ID_SILVER_BLOCK), createSimpleTable(BLOCKS.get(ID_SILVER_BLOCK)));
        lootTables.put(BLOCKS.get(ID_LEAD_BLOCK), createSimpleTable(BLOCKS.get(ID_LEAD_BLOCK)));
        lootTables.put(BLOCKS.get(ID_NICKEL_BLOCK), createSimpleTable(BLOCKS.get(ID_NICKEL_BLOCK)));
        lootTables.put(BLOCKS.get(ID_PLATINUM_BLOCK), createSimpleTable(BLOCKS.get(ID_PLATINUM_BLOCK)));

        lootTables.put(BLOCKS.get(ID_BRONZE_BLOCK), createSimpleTable(BLOCKS.get(ID_BRONZE_BLOCK)));
        lootTables.put(BLOCKS.get(ID_ELECTRUM_BLOCK), createSimpleTable(BLOCKS.get(ID_ELECTRUM_BLOCK)));
        lootTables.put(BLOCKS.get(ID_INVAR_BLOCK), createSimpleTable(BLOCKS.get(ID_INVAR_BLOCK)));
        lootTables.put(BLOCKS.get(ID_CONSTANTAN_BLOCK), createSimpleTable(BLOCKS.get(ID_CONSTANTAN_BLOCK)));

        lootTables.put(BLOCKS.get(ID_RUBY_BLOCK), createSimpleTable(BLOCKS.get(ID_RUBY_BLOCK)));
        lootTables.put(BLOCKS.get(ID_SAPPHIRE_BLOCK), createSimpleTable(BLOCKS.get(ID_SAPPHIRE_BLOCK)));
    }

}
