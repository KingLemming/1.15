package cofh.thermal.core.data;

import cofh.lib.data.ItemModelProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalReferences.*;

public class TCoreItemModels extends ItemModelProviderCoFH {

    public TCoreItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_THERMAL, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Core: Item Models";
    }

    @Override
    protected void registerModels() {

        registerBlockItemModels();

        DeferredRegisterCoFH<Item> reg = ITEMS;

        metalSet(reg, "iron", true);
        metalSet(reg, "gold", true);

        gemSet(reg, "diamond", true);
        gemSet(reg, "emerald", true);

        metalSet(reg, "signalum");
        metalSet(reg, "lumium");
        metalSet(reg, "enderium");

        generated(reg.getSup("wood_dust"), DUSTS);

        generated(reg.getSup("apatite"), RESOURCES);
        generated(reg.getSup("niter"), RESOURCES);
        generated(reg.getSup("sulfur"), RESOURCES);

        generated(reg.getSup("wrench"), TOOLS);
        generated(reg.getSup("lock"), TOOLS);
        generated(reg.getSup("phytogro"), TOOLS);
    }

    private void registerBlockItemModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

        blockItem(reg.getSup(ID_CHARCOAL_BLOCK));
        blockItem(reg.getSup(ID_BAMBOO_BLOCK));
        blockItem(reg.getSup(ID_SUGAR_CANE_BLOCK));

        blockItem(reg.getSup(ID_APATITE_ORE));
        blockItem(reg.getSup(ID_NITER_ORE));
        blockItem(reg.getSup(ID_SULFUR_ORE));

        blockItem(reg.getSup(ID_APATITE_BLOCK));
        blockItem(reg.getSup(ID_NITER_BLOCK));
        blockItem(reg.getSup(ID_SULFUR_BLOCK));

        blockItem(reg.getSup(ID_SIGNALUM_BLOCK));
        blockItem(reg.getSup(ID_LUMIUM_BLOCK));
        blockItem(reg.getSup(ID_ENDERIUM_BLOCK));

        blockItem(reg.getSup(ID_SIGNALUM_GLASS));
        blockItem(reg.getSup(ID_LUMIUM_GLASS));
        blockItem(reg.getSup(ID_ENDERIUM_GLASS));
    }

}
