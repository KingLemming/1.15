package cofh.cofh_tools.data;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

import java.util.function.Supplier;

import static cofh.cofh_tools.CoFHTools.ITEMS;
import static cofh.lib.util.constants.Constants.ID_COFH_TOOLS;

public class ModItemModels extends ItemModelProvider {

    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_COFH_TOOLS, existingFileHelper);
    }

    @Override
    public String getName() {

        return "CoFH Tools: Item Models";
    }

    @Override
    protected void registerModels() {

        standardToolSet("copper");
        standardToolSet("tin");
        standardToolSet("silver");
        standardToolSet("lead");
        standardToolSet("nickel");
        standardToolSet("platinum");

        standardToolSet("bronze");
        standardToolSet("electrum");
        standardToolSet("invar");
        standardToolSet("constantan");
    }

    private void standardToolSet(String prefix) {

        handheld(ITEMS.getSup(prefix + "_shovel"), "tools");
        handheld(ITEMS.getSup(prefix + "_pickaxe"), "tools");
        handheld(ITEMS.getSup(prefix + "_axe"), "tools");
        handheld(ITEMS.getSup(prefix + "_hoe"), "tools");
        handheld(ITEMS.getSup(prefix + "_sword"), "tools");
    }

    // region HELPERS
    private String name(Supplier<? extends IItemProvider> item) {

        return item.get().asItem().getRegistryName().getPath();
    }

    private ResourceLocation itemTexture(Supplier<? extends IItemProvider> item) {

        return modLoc("item/" + name(item));
    }

    private ResourceLocation itemTexture(Supplier<? extends IItemProvider> item, String subfolder) {

        return modLoc("item/" + subfolder + "/" + name(item));
    }

    private ItemModelBuilder blockItem(Supplier<? extends Block> block) {

        return blockItem(block, "");
    }

    private ItemModelBuilder blockItem(Supplier<? extends Block> block, String suffix) {

        return withExistingParent(name(block), modLoc("block/" + name(block) + suffix));
    }

    private ItemModelBuilder blockWithInventoryModel(Supplier<? extends Block> block) {

        return withExistingParent(name(block), modLoc("block/" + name(block) + "_inventory"));
    }

    private ItemModelBuilder blockSprite(Supplier<? extends Block> block) {

        return blockSprite(block, modLoc("block/" + name(block)));
    }

    private ItemModelBuilder blockSprite(Supplier<? extends Block> block, ResourceLocation texture) {

        return generated(() -> block.get().asItem(), texture);
    }

    private ItemModelBuilder generated(Supplier<? extends IItemProvider> item, String subfolder) {

        return generated(item, itemTexture(item, subfolder));
    }

    private ItemModelBuilder generated(Supplier<? extends IItemProvider> item) {

        return generated(item, itemTexture(item));
    }

    private ItemModelBuilder generated(Supplier<? extends IItemProvider> item, ResourceLocation texture) {

        return getBuilder(name(item)).parent(new UncheckedModelFile("item/generated")).texture("layer0", texture);
    }

    private ItemModelBuilder handheld(Supplier<? extends IItemProvider> item) {

        return handheld(item, itemTexture(item));
    }

    private ItemModelBuilder handheld(Supplier<? extends IItemProvider> item, String subfolder) {

        return handheld(item, itemTexture(item, subfolder));
    }

    private ItemModelBuilder handheld(Supplier<? extends IItemProvider> item, ResourceLocation texture) {

        return withExistingParent(name(item), "item/handheld").texture("layer0", texture);
    }
    // endregion
}
