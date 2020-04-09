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

        lootTables.put(BLOCKS.get(ID_COPPER_ORE), createSelfDropTable(BLOCKS.get(ID_COPPER_ORE)));
        lootTables.put(BLOCKS.get(ID_TIN_ORE), createSelfDropTable(BLOCKS.get(ID_TIN_ORE)));
        lootTables.put(BLOCKS.get(ID_SILVER_ORE), createSelfDropTable(BLOCKS.get(ID_SILVER_ORE)));
        lootTables.put(BLOCKS.get(ID_LEAD_ORE), createSelfDropTable(BLOCKS.get(ID_LEAD_ORE)));
        lootTables.put(BLOCKS.get(ID_NICKEL_ORE), createSelfDropTable(BLOCKS.get(ID_NICKEL_ORE)));
        lootTables.put(BLOCKS.get(ID_PLATINUM_ORE), createSelfDropTable(BLOCKS.get(ID_PLATINUM_ORE)));

        lootTables.put(BLOCKS.get(ID_RUBY_ORE), createSilkTouchOreTable(BLOCKS.get(ID_RUBY_ORE), ITEMS.get("ruby_gem")));
        lootTables.put(BLOCKS.get(ID_SAPPHIRE_ORE), createSilkTouchOreTable(BLOCKS.get(ID_SAPPHIRE_ORE), ITEMS.get("sapphire_gem")));

        lootTables.put(BLOCKS.get(ID_COPPER_BLOCK), createSelfDropTable(BLOCKS.get(ID_COPPER_BLOCK)));
        lootTables.put(BLOCKS.get(ID_TIN_BLOCK), createSelfDropTable(BLOCKS.get(ID_TIN_BLOCK)));
        lootTables.put(BLOCKS.get(ID_SILVER_BLOCK), createSelfDropTable(BLOCKS.get(ID_SILVER_BLOCK)));
        lootTables.put(BLOCKS.get(ID_LEAD_BLOCK), createSelfDropTable(BLOCKS.get(ID_LEAD_BLOCK)));
        lootTables.put(BLOCKS.get(ID_NICKEL_BLOCK), createSelfDropTable(BLOCKS.get(ID_NICKEL_BLOCK)));
        lootTables.put(BLOCKS.get(ID_PLATINUM_BLOCK), createSelfDropTable(BLOCKS.get(ID_PLATINUM_BLOCK)));

        lootTables.put(BLOCKS.get(ID_BRONZE_BLOCK), createSelfDropTable(BLOCKS.get(ID_BRONZE_BLOCK)));
        lootTables.put(BLOCKS.get(ID_ELECTRUM_BLOCK), createSelfDropTable(BLOCKS.get(ID_ELECTRUM_BLOCK)));
        lootTables.put(BLOCKS.get(ID_INVAR_BLOCK), createSelfDropTable(BLOCKS.get(ID_INVAR_BLOCK)));
        lootTables.put(BLOCKS.get(ID_CONSTANTAN_BLOCK), createSelfDropTable(BLOCKS.get(ID_CONSTANTAN_BLOCK)));

        lootTables.put(BLOCKS.get(ID_RUBY_BLOCK), createSelfDropTable(BLOCKS.get(ID_RUBY_BLOCK)));
        lootTables.put(BLOCKS.get(ID_SAPPHIRE_BLOCK), createSelfDropTable(BLOCKS.get(ID_SAPPHIRE_BLOCK)));
    }

}
