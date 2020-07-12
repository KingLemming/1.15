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
import static cofh.thermal.core.init.TCoreReferences.*;

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
        generated(reg.getSup("cinnabar"), RESOURCES);
        generated(reg.getSup("niter"), RESOURCES);
        generated(reg.getSup("sulfur"), RESOURCES);

        generated(reg.getSup("basalz_rod"), RESOURCES);
        generated(reg.getSup("basalz_powder"), RESOURCES);
        generated(reg.getSup("blitz_rod"), RESOURCES);
        generated(reg.getSup("blitz_powder"), RESOURCES);
        generated(reg.getSup("blizz_rod"), RESOURCES);
        generated(reg.getSup("blizz_powder"), RESOURCES);

        handheld(reg.getSup("wrench"), TOOLS);
        generated(reg.getSup("redprint"), TOOLS);
        generated(reg.getSup("lock"), TOOLS);
        generated(reg.getSup("phytogro"), TOOLS);

        for (int i = 1; i <= 4; ++i) {
            generated(reg.getSup("upgrade_augment_" + i), AUGMENTS);
            generated(reg.getSup("fluid_tank_augment_" + i), AUGMENTS);
            generated(reg.getSup("rf_coil_augment_" + i), AUGMENTS);
            generated(reg.getSup("rf_coil_stor_augment_" + i), AUGMENTS);
            generated(reg.getSup("rf_coil_xfer_augment_" + i), AUGMENTS);
        }
        //        generated(reg.getSup("area_radius_augment"), AUGMENTS);
        //        generated(reg.getSup("potion_amplifier_augment"), AUGMENTS);
        //        generated(reg.getSup("potion_duration_augment"), AUGMENTS);

        generated(reg.getSup("machine_speed_augment"), AUGMENTS);
        generated(reg.getSup("machine_output_augment"), AUGMENTS);
        generated(reg.getSup("machine_catalyst_augment"), AUGMENTS);
        generated(reg.getSup("dynamo_output_augment"), AUGMENTS);
        generated(reg.getSup("dynamo_fuel_augment"), AUGMENTS);

        generated(reg.getSup(ID_BEEKEEPER_HELMET), ARMOR);
        generated(reg.getSup(ID_BEEKEEPER_CHESTPLATE), ARMOR);
        generated(reg.getSup(ID_BEEKEEPER_LEGGINGS), ARMOR);
        generated(reg.getSup(ID_BEEKEEPER_BOOTS), ARMOR);

        generated(reg.getSup(ID_HAZMAT_HELMET), ARMOR);
        generated(reg.getSup(ID_HAZMAT_CHESTPLATE), ARMOR);
        generated(reg.getSup(ID_HAZMAT_LEGGINGS), ARMOR);
        generated(reg.getSup(ID_HAZMAT_BOOTS), ARMOR);
    }

    private void registerBlockItemModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

        blockItem(reg.getSup(ID_CHARCOAL_BLOCK));
        blockItem(reg.getSup(ID_BAMBOO_BLOCK));
        blockItem(reg.getSup(ID_SUGAR_CANE_BLOCK));
        blockItem(reg.getSup(ID_GUNPOWDER_BLOCK));

        blockItem(reg.getSup(ID_APPLE_BLOCK));
        blockItem(reg.getSup(ID_BEETROOT_BLOCK));
        blockItem(reg.getSup(ID_CARROT_BLOCK));
        blockItem(reg.getSup(ID_POTATO_BLOCK));

        blockItem(reg.getSup(ID_APATITE_ORE));
        blockItem(reg.getSup(ID_CINNABAR_ORE));
        blockItem(reg.getSup(ID_NITER_ORE));
        blockItem(reg.getSup(ID_SULFUR_ORE));

        blockItem(reg.getSup(ID_APATITE_BLOCK));
        blockItem(reg.getSup(ID_CINNABAR_BLOCK));
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
