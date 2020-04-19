package cofh.cofh_tools.data;

import cofh.lib.data.ItemModelProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.cofh_tools.CoFHTools.ITEMS;
import static cofh.lib.util.constants.Constants.ID_COFH_TOOLS;

public class ModItemModels extends ItemModelProviderCoFH {

    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_COFH_TOOLS, existingFileHelper);
    }

    @Override
    public String getName() {

        return "CoFH Tools: Item Models";
    }

    @Override
    protected void registerModels() {

        DeferredRegisterCoFH<Item> item = ITEMS;

        standardToolSet(item, "copper");
        standardToolSet(item, "tin");
        standardToolSet(item, "silver");
        standardToolSet(item, "lead");
        standardToolSet(item, "nickel");
        standardToolSet(item, "platinum");

        standardToolSet(item, "bronze");
        standardToolSet(item, "electrum");
        standardToolSet(item, "invar");
        standardToolSet(item, "constantan");
    }

}
