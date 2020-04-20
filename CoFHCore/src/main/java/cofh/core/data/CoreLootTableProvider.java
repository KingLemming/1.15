package cofh.core.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.lib.util.references.CoreReferences.GLOSSED_MAGMA;

public class CoreLootTableProvider extends LootTableProviderCoFH {

    public CoreLootTableProvider(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "CoFH Core: Loot Tables";
    }

    @Override
    protected void addTables() {

        lootTables.put(GLOSSED_MAGMA, createEmptyTable());
    }

}
