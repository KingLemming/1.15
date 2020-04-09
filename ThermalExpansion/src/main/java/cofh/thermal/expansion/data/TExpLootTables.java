package cofh.thermal.expansion.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

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

    }

}
