package cofh.thermal.locomotion.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.init.ThermalReferences.*;

public class TLocGenLootTables extends LootTableProviderCoFH {

    public TLocGenLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Thermal Locomotion: Loot Tables";
    }

    @Override
    protected void addTables() {

        lootTables.put(BLOCKS.get(ID_CROSSOVER_RAIL), createSimpleTable(BLOCKS.get(ID_CROSSOVER_RAIL)));

        lootTables.put(BLOCKS.get(ID_PRISMARINE_RAIL), createSimpleTable(BLOCKS.get(ID_PRISMARINE_RAIL)));
        lootTables.put(BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL), createSimpleTable(BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL)));
        lootTables.put(BLOCKS.get(ID_PRISMARINE_POWERED_RAIL), createSimpleTable(BLOCKS.get(ID_PRISMARINE_POWERED_RAIL)));
        lootTables.put(BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL), createSimpleTable(BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL)));
        lootTables.put(BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL), createSimpleTable(BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL)));

        lootTables.put(BLOCKS.get(ID_LUMIUM_RAIL), createSimpleTable(BLOCKS.get(ID_LUMIUM_RAIL)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL), createSimpleTable(BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_POWERED_RAIL), createSimpleTable(BLOCKS.get(ID_LUMIUM_POWERED_RAIL)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL), createSimpleTable(BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL), createSimpleTable(BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL)));
    }

}
