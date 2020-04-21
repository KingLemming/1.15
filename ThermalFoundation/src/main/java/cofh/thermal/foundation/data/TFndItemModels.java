package cofh.thermal.foundation.data;

import cofh.lib.data.ItemModelProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
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

        DeferredRegisterCoFH<Item> reg = ITEMS;

        metalSet(reg, "copper");
        metalSet(reg, "tin");
        metalSet(reg, "silver");
        metalSet(reg, "lead");
        metalSet(reg, "nickel");
        metalSet(reg, "platinum");

        metalSet(reg, "bronze");
        metalSet(reg, "electrum");
        metalSet(reg, "invar");
        metalSet(reg, "constantan");

        gemSet(reg, "ruby");
        gemSet(reg, "sapphire");
    }

    private void registerBlockItemModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

        blockItem(reg.getSup(ID_COPPER_ORE));
        blockItem(reg.getSup(ID_TIN_ORE));
        blockItem(reg.getSup(ID_SILVER_ORE));
        blockItem(reg.getSup(ID_LEAD_ORE));
        blockItem(reg.getSup(ID_NICKEL_ORE));
        blockItem(reg.getSup(ID_PLATINUM_ORE));

        blockItem(reg.getSup(ID_RUBY_ORE));
        blockItem(reg.getSup(ID_SAPPHIRE_ORE));

        blockItem(reg.getSup(ID_COPPER_BLOCK));
        blockItem(reg.getSup(ID_TIN_BLOCK));
        blockItem(reg.getSup(ID_SILVER_BLOCK));
        blockItem(reg.getSup(ID_LEAD_BLOCK));
        blockItem(reg.getSup(ID_NICKEL_BLOCK));
        blockItem(reg.getSup(ID_PLATINUM_BLOCK));

        blockItem(reg.getSup(ID_BRONZE_BLOCK));
        blockItem(reg.getSup(ID_ELECTRUM_BLOCK));
        blockItem(reg.getSup(ID_INVAR_BLOCK));
        blockItem(reg.getSup(ID_CONSTANTAN_BLOCK));

        blockItem(reg.getSup(ID_RUBY_BLOCK));
        blockItem(reg.getSup(ID_SAPPHIRE_BLOCK));
    }

}
