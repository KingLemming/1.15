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

        DeferredRegisterCoFH<Item> itemReg = ITEMS;

        standardToolSet(itemReg, "copper");
        standardToolSet(itemReg, "tin");
        standardToolSet(itemReg, "silver");
        standardToolSet(itemReg, "lead");
        standardToolSet(itemReg, "nickel");
        standardToolSet(itemReg, "platinum");

        standardToolSet(itemReg, "bronze");
        standardToolSet(itemReg, "electrum");
        standardToolSet(itemReg, "invar");
        standardToolSet(itemReg, "constantan");
    }

}
