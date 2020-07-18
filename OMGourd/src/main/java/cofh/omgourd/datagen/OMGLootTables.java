package cofh.omgourd.datagen;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.omgourd.OMGourd.BLOCKS;

public class OMGLootTables extends LootTableProviderCoFH {

    public OMGLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "OMGourd: Loot Tables";
    }

    @Override
    protected void addTables() {

        for (int i = 1; i <= 24; ++i) {
            lootTables.put(BLOCKS.get("carved_pumpkin_" + i), createSimpleDropTable(BLOCKS.get("carved_pumpkin_" + i)));
            lootTables.put(BLOCKS.get("jack_o_lantern_" + i), createSimpleDropTable(BLOCKS.get("jack_o_lantern_" + i)));
        }
    }

}
