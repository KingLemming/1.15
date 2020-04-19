package cofh.lib.data;

import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

import java.util.function.Supplier;

public abstract class ItemModelProviderCoFH extends ItemModelProvider {

    public ItemModelProviderCoFH(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {

        super(generator, modid, existingFileHelper);
    }

    protected void standardToolSet(DeferredRegisterCoFH<Item> items, String prefix) {

        handheld(items.getSup(prefix + "_shovel"), "tools");
        handheld(items.getSup(prefix + "_pickaxe"), "tools");
        handheld(items.getSup(prefix + "_axe"), "tools");
        handheld(items.getSup(prefix + "_hoe"), "tools");
        handheld(items.getSup(prefix + "_sword"), "tools");
    }

    // region HELPERS
    protected String name(Supplier<? extends IItemProvider> item) {

        return item.get().asItem().getRegistryName().getPath();
    }

    protected ResourceLocation itemTexture(Supplier<? extends IItemProvider> item) {

        return modLoc("item/" + name(item));
    }

    protected ResourceLocation itemTexture(Supplier<? extends IItemProvider> item, String subfolder) {

        return modLoc("item/" + subfolder + "/" + name(item));
    }

    protected ItemModelBuilder blockItem(Supplier<? extends Block> block) {

        return blockItem(block, "");
    }

    protected ItemModelBuilder blockItem(Supplier<? extends Block> block, String suffix) {

        return withExistingParent(name(block), modLoc("block/" + name(block) + suffix));
    }

    protected ItemModelBuilder blockWithInventoryModel(Supplier<? extends Block> block) {

        return withExistingParent(name(block), modLoc("block/" + name(block) + "_inventory"));
    }

    protected ItemModelBuilder blockSprite(Supplier<? extends Block> block) {

        return blockSprite(block, modLoc("block/" + name(block)));
    }

    protected ItemModelBuilder blockSprite(Supplier<? extends Block> block, ResourceLocation texture) {

        return generated(() -> block.get().asItem(), texture);
    }

    protected ItemModelBuilder generated(Supplier<? extends IItemProvider> item, String subfolder) {

        return generated(item, itemTexture(item, subfolder));
    }

    protected ItemModelBuilder generated(Supplier<? extends IItemProvider> item) {

        return generated(item, itemTexture(item));
    }

    protected ItemModelBuilder generated(Supplier<? extends IItemProvider> item, ResourceLocation texture) {

        return getBuilder(name(item)).parent(new UncheckedModelFile("item/generated")).texture("layer0", texture);
    }

    protected ItemModelBuilder handheld(Supplier<? extends IItemProvider> item) {

        return handheld(item, itemTexture(item));
    }

    protected ItemModelBuilder handheld(Supplier<? extends IItemProvider> item, String subfolder) {

        return handheld(item, itemTexture(item, subfolder));
    }

    protected ItemModelBuilder handheld(Supplier<? extends IItemProvider> item, ResourceLocation texture) {

        return withExistingParent(name(item), "item/handheld").texture("layer0", texture);
    }
    // endregion
}
