package cofh.thermal.core.data;

import cofh.lib.data.ItemModelProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
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

        blockItem(BLOCKS.getSup(ID_SULFUR_ORE));
        blockItem(BLOCKS.getSup(ID_NITER_ORE));

        blockItem(BLOCKS.getSup(ID_SIGNALUM_BLOCK));
        blockItem(BLOCKS.getSup(ID_LUMIUM_BLOCK));
        blockItem(BLOCKS.getSup(ID_ENDERIUM_BLOCK));

        blockItem(BLOCKS.getSup(ID_SIGNALUM_GLASS));
        blockItem(BLOCKS.getSup(ID_LUMIUM_GLASS));
        blockItem(BLOCKS.getSup(ID_ENDERIUM_GLASS));

        DeferredRegisterCoFH<Item> itemReg = ITEMS;

        metalSet(itemReg, "iron", true);
        metalSet(itemReg, "gold", true);

        gemSet(itemReg, "diamond", true);
        gemSet(itemReg, "emerald", true);

        metalSet(itemReg, "signalum");
        metalSet(itemReg, "lumium");
        metalSet(itemReg, "enderium");
    }

}
