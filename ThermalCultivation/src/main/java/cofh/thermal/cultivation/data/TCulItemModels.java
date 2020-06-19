package cofh.thermal.cultivation.data;

import cofh.lib.data.ItemModelProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
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

        DeferredRegisterCoFH<Item> reg = ITEMS;

        generated(reg.getSup(ID_BARLEY), CROPS);
        generated(reg.getSup(ID_ONION), CROPS);
        generated(reg.getSup(ID_RADISH), CROPS);
        generated(reg.getSup(ID_RICE), CROPS);
        generated(reg.getSup(ID_SADIROOT), CROPS);
        generated(reg.getSup(ID_SPINACH), CROPS);

        generated(reg.getSup(ID_BELL_PEPPER), CROPS);
        generated(reg.getSup(ID_EGGPLANT), CROPS);
        generated(reg.getSup(ID_GREEN_BEAN), CROPS);
        generated(reg.getSup(ID_PEANUT), CROPS);
        generated(reg.getSup(ID_STRAWBERRY), CROPS);
        generated(reg.getSup(ID_TOMATO), CROPS);

        generated(reg.getSup(ID_COFFEE), CROPS);
        generated(reg.getSup(ID_TEA), CROPS);

        generated(reg.getSup(seeds(ID_BARLEY)), SEEDS);
        generated(reg.getSup(seeds(ID_ONION)), SEEDS);
        generated(reg.getSup(seeds(ID_RADISH)), SEEDS);
        generated(reg.getSup(seeds(ID_RICE)), SEEDS);
        generated(reg.getSup(seeds(ID_SADIROOT)), SEEDS);
        generated(reg.getSup(seeds(ID_SPINACH)), SEEDS);

        generated(reg.getSup(seeds(ID_BELL_PEPPER)), SEEDS);
        generated(reg.getSup(seeds(ID_EGGPLANT)), SEEDS);
        generated(reg.getSup(seeds(ID_GREEN_BEAN)), SEEDS);
        generated(reg.getSup(seeds(ID_PEANUT)), SEEDS);
        generated(reg.getSup(seeds(ID_STRAWBERRY)), SEEDS);
        generated(reg.getSup(seeds(ID_TOMATO)), SEEDS);

        generated(reg.getSup(seeds(ID_COFFEE)), SEEDS);
        generated(reg.getSup(seeds(ID_TEA)), SEEDS);

        generated(reg.getSup(seeds(ID_FROST_MELON)), SEEDS);

        generated(reg.getSup(ID_FROST_MELON_SLICE), FOODS);
    }

}
