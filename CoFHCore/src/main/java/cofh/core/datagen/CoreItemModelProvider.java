package cofh.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.core.CoFHCore.ITEMS;
import static cofh.core.util.constants.Constants.ID_COFH_CORE;
import static cofh.core.util.references.CoreIDs.ID_ECTOPLASM;

public class CoreItemModelProvider extends ItemModelProviderCoFH {

    public CoreItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_COFH_CORE, existingFileHelper);
    }

    @Override
    public String getName() {

        return "CoFH Core: Item Models";
    }

    @Override
    protected void registerModels() {

        // blockItem(BLOCKS.getSup(ID_GLOSSED_MAGMA));

        generated(ITEMS.getSup(ID_ECTOPLASM));
    }

}
