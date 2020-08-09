package cofh.thermal.expansion.datagen;

import cofh.lib.datagen.ItemModelProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;

public class TExpItemModels extends ItemModelProviderCoFH {

    public TExpItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_THERMAL, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Expansion: Item Models";
    }

    @Override
    protected void registerModels() {

        registerBlockItemModels();

        DeferredRegisterCoFH<Item> reg = ITEMS;

        generated(reg.getSup("chiller_ball_cast"), CRAFTING);

        generated(reg.getSup("press_coin_die"), CRAFTING);
        generated(reg.getSup("press_gear_die"), CRAFTING);
    }

    private void registerBlockItemModels() {

    }

}
