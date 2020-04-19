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

    public static final String COINS = "coins";
    public static final String CROPS = "crops";
    public static final String DUSTS = "dusts";
    public static final String FOODS = "foods";
    public static final String GEARS = "gears";
    public static final String GEMS = "gems";
    public static final String INGOTS = "ingots";
    public static final String NUGGETS = "nuggets";
    public static final String PLATES = "plates";
    public static final String PROJECTILES = "projectiles";
    public static final String SEEDS = "seeds";
    public static final String TOOLS = "tools";

    protected void standardToolSet(DeferredRegisterCoFH<Item> itemReg, String prefix) {

        handheld(itemReg.getSup(prefix + "_shovel"), TOOLS);
        handheld(itemReg.getSup(prefix + "_pickaxe"), TOOLS);
        handheld(itemReg.getSup(prefix + "_axe"), TOOLS);
        handheld(itemReg.getSup(prefix + "_hoe"), TOOLS);
        handheld(itemReg.getSup(prefix + "_sword"), TOOLS);
    }

    protected void metalSet(DeferredRegisterCoFH<Item> itemReg, String prefix) {

        metalSet(itemReg, prefix, false);
    }

    protected void metalSet(DeferredRegisterCoFH<Item> itemReg, String prefix, boolean vanilla) {

        if (!vanilla) {
            generated(itemReg.getSup(prefix + "_ingot"), INGOTS);
            generated(itemReg.getSup(prefix + "_nugget"), NUGGETS);
        }
        generated(itemReg.getSup(prefix + "_dust"), DUSTS);
        generated(itemReg.getSup(prefix + "_gear"), GEARS);
        //        generated(itemReg.getSup(prefix + "_plate"));
        //        generated(itemReg.getSup(prefix + "_coin"));
    }

    protected void gemSet(DeferredRegisterCoFH<Item> itemReg, String prefix) {

        gemSet(itemReg, prefix, false);
    }

    protected void gemSet(DeferredRegisterCoFH<Item> itemReg, String prefix, boolean vanilla) {

        if (!vanilla) {
            generated(itemReg.getSup(prefix + "_gem"), GEMS);
        }
        generated(itemReg.getSup(prefix + "_nugget"), NUGGETS);
        generated(itemReg.getSup(prefix + "_dust"), DUSTS);
        generated(itemReg.getSup(prefix + "_gear"), GEARS);
        //        generated(itemReg.getSup(prefix + "_plate"));
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
