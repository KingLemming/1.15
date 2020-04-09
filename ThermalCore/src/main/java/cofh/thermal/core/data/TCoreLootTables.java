package cofh.thermal.core.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.init.ThermalReferences.*;

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

        lootTables.put(BLOCKS.get(ID_SIGNALUM_BLOCK), createSelfDropTable(BLOCKS.get(ID_SIGNALUM_BLOCK)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_BLOCK), createSelfDropTable(BLOCKS.get(ID_LUMIUM_BLOCK)));
        lootTables.put(BLOCKS.get(ID_ENDERIUM_BLOCK), createSelfDropTable(BLOCKS.get(ID_ENDERIUM_BLOCK)));
    }

}
