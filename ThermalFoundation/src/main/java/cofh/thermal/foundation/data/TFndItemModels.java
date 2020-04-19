package cofh.thermal.foundation.data;

import cofh.lib.data.ItemModelProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.foundation.init.TFndReferences.*;

public class TFndItemModels extends ItemModelProviderCoFH {

    public TFndItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_THERMAL, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Foundation: Item Models";
    }

    @Override
    protected void registerModels() {

        blockItem(BLOCKS.getSup(ID_COPPER_ORE));
        blockItem(BLOCKS.getSup(ID_TIN_ORE));
        blockItem(BLOCKS.getSup(ID_SILVER_ORE));
        blockItem(BLOCKS.getSup(ID_LEAD_ORE));
        blockItem(BLOCKS.getSup(ID_NICKEL_ORE));
        blockItem(BLOCKS.getSup(ID_PLATINUM_ORE));

        blockItem(BLOCKS.getSup(ID_RUBY_ORE));
        blockItem(BLOCKS.getSup(ID_SAPPHIRE_ORE));

        blockItem(BLOCKS.getSup(ID_COPPER_BLOCK));
        blockItem(BLOCKS.getSup(ID_TIN_BLOCK));
        blockItem(BLOCKS.getSup(ID_SILVER_BLOCK));
        blockItem(BLOCKS.getSup(ID_LEAD_BLOCK));
        blockItem(BLOCKS.getSup(ID_NICKEL_BLOCK));
        blockItem(BLOCKS.getSup(ID_PLATINUM_BLOCK));

        blockItem(BLOCKS.getSup(ID_BRONZE_BLOCK));
        blockItem(BLOCKS.getSup(ID_ELECTRUM_BLOCK));
        blockItem(BLOCKS.getSup(ID_INVAR_BLOCK));
        blockItem(BLOCKS.getSup(ID_CONSTANTAN_BLOCK));

        blockItem(BLOCKS.getSup(ID_RUBY_BLOCK));
        blockItem(BLOCKS.getSup(ID_SAPPHIRE_BLOCK));

        DeferredRegisterCoFH<Item> itemReg = ITEMS;

        metalSet(itemReg, "copper");
        metalSet(itemReg, "tin");
        metalSet(itemReg, "silver");
        metalSet(itemReg, "lead");
        metalSet(itemReg, "nickel");
        metalSet(itemReg, "platinum");

        metalSet(itemReg, "bronze");
        metalSet(itemReg, "electrum");
        metalSet(itemReg, "invar");
        metalSet(itemReg, "constantan");

        gemSet(itemReg, "ruby");
        gemSet(itemReg, "sapphire");
    }

}
