package cofh.thermal.cultivation.data;

import cofh.lib.data.ItemModelProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.cultivation.init.TCulReferences.*;

public class TCulItemModels extends ItemModelProviderCoFH {

    public TCulItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_THERMAL, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Cultivation: Item Models";
    }

    @Override
    protected void registerModels() {

        generated(ITEMS.getSup(ID_BARLEY), CROPS);
        generated(ITEMS.getSup(ID_ONION), CROPS);
        generated(ITEMS.getSup(ID_RADISH), CROPS);
        generated(ITEMS.getSup(ID_RICE), CROPS);
        generated(ITEMS.getSup(ID_SADIROOT), CROPS);
        generated(ITEMS.getSup(ID_SPINACH), CROPS);

        generated(ITEMS.getSup(ID_BELL_PEPPER), CROPS);
        generated(ITEMS.getSup(ID_EGGPLANT), CROPS);
        generated(ITEMS.getSup(ID_GREEN_BEAN), CROPS);
        generated(ITEMS.getSup(ID_PEANUT), CROPS);
        generated(ITEMS.getSup(ID_STRAWBERRY), CROPS);
        generated(ITEMS.getSup(ID_TOMATO), CROPS);

        generated(ITEMS.getSup(ID_COFFEE), CROPS);
        generated(ITEMS.getSup(ID_TEA), CROPS);

        generated(ITEMS.getSup(seeds(ID_BARLEY)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_ONION)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_RADISH)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_RICE)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_SADIROOT)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_SPINACH)), SEEDS);

        generated(ITEMS.getSup(seeds(ID_BELL_PEPPER)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_EGGPLANT)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_GREEN_BEAN)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_PEANUT)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_STRAWBERRY)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_TOMATO)), SEEDS);

        generated(ITEMS.getSup(seeds(ID_COFFEE)), SEEDS);
        generated(ITEMS.getSup(seeds(ID_TEA)), SEEDS);

        generated(ITEMS.getSup(seeds(ID_FROST_MELON)), SEEDS);

        generated(ITEMS.getSup(ID_FROST_MELON_SLICE), FOODS);
    }

}
