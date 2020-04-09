package cofh.thermal.cultivation.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.seeds;

public class TCulLootTables extends LootTableProviderCoFH {

    public TCulLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Thermal Cultivation: Loot Tables";
    }

    @Override
    protected void addTables() {

        registerDefaultCropTable(ID_BARLEY);
        registerDefaultCropTable(ID_ONION);
        registerDefaultCropTable(ID_RADISH);
        registerDefaultCropTable(ID_RICE);
        registerDefaultCropTable(ID_SADIROOT);
        registerDefaultCropTable(ID_SPINACH);

        registerDefaultCropTable(ID_BELL_PEPPER);
        registerDefaultCropTable(ID_EGGPLANT);
        registerDefaultCropTable(ID_GREEN_BEAN);
        registerDefaultCropTable(ID_PEANUT);
        registerDefaultCropTable(ID_STRAWBERRY);
        registerDefaultCropTable(ID_TOMATO);

        registerDefaultCropTable(ID_COFFEE);
        registerDefaultCropTable(ID_TEA);
    }

    protected void registerDefaultCropTable(String id) {

        lootTables.put(BLOCKS.get(id), createCropTable(BLOCKS.get(id), ITEMS.get(id), ITEMS.get(seeds(id))));
    }

}
