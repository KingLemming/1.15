package cofh.thermal.core.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.init.ThermalReferences.*;

public class TCoreGenLootTables extends LootTableProviderCoFH {

    public TCoreGenLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Thermal Core: Loot Tables";
    }

    @Override
    protected void addTables() {

        lootTables.put(BLOCKS.get(ID_SIGNALUM_BLOCK), createSimpleTable(BLOCKS.get(ID_SIGNALUM_BLOCK)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_BLOCK), createSimpleTable(BLOCKS.get(ID_LUMIUM_BLOCK)));
        lootTables.put(BLOCKS.get(ID_ENDERIUM_BLOCK), createSimpleTable(BLOCKS.get(ID_ENDERIUM_BLOCK)));
    }

}
