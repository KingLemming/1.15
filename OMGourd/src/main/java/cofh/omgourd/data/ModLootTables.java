package cofh.omgourd.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.omgourd.OMGourd.BLOCKS;

public class ModLootTables extends LootTableProviderCoFH {

    public ModLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "OMGourd: Loot Tables";
    }

    @Override
    protected void addTables() {

        for (int i = 1; i <= 24; ++i) {
            lootTables.put(BLOCKS.get("carved_pumpkin_" + i), createSelfDropTable(BLOCKS.get("carved_pumpkin_" + i)));
            lootTables.put(BLOCKS.get("jack_o_lantern_" + i), createSelfDropTable(BLOCKS.get("jack_o_lantern_" + i)));
        }
    }

}