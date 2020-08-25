package cofh.thermal.locomotion.datagen;

import cofh.lib.datagen.ItemModelProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocItemModels extends ItemModelProviderCoFH {

    public TLocItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_THERMAL, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        DeferredRegisterCoFH<Item> reg = ITEMS;

        generated(reg.getSup(ID_UNDERWATER_CART), MINECARTS);

        generated(reg.getSup(ID_PHYTO_TNT_CART), MINECARTS);

        generated(reg.getSup(ID_FIRE_TNT_CART), MINECARTS);
        generated(reg.getSup(ID_EARTH_TNT_CART), MINECARTS);
        generated(reg.getSup(ID_ICE_TNT_CART), MINECARTS);
        generated(reg.getSup(ID_LIGHTNING_TNT_CART), MINECARTS);

        generated(reg.getSup(ID_NUKE_TNT_CART), MINECARTS);
    }

    @Override
    public String getName() {

        return "Thermal Locomotion: Item Models";
    }

}
