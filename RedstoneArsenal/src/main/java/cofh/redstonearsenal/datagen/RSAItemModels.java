package cofh.redstonearsenal.datagen;

import cofh.core.datagen.ItemModelProviderCoFH;
import cofh.core.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.core.util.constants.Constants.ID_REDSTONE_ARSENAL;
import static cofh.redstonearsenal.RedstoneArsenal.BLOCKS;
import static cofh.redstonearsenal.RedstoneArsenal.ITEMS;

public class RSAItemModels extends ItemModelProviderCoFH {

    public RSAItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_REDSTONE_ARSENAL, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Redstone Arsenal: Item Models";
    }

    @Override
    protected void registerModels() {

        registerBlockItemModels();

        DeferredRegisterCoFH<Item> reg = ITEMS;
    }

    // region HELPERS
    private void registerBlockItemModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

    }
    // endregion
}
