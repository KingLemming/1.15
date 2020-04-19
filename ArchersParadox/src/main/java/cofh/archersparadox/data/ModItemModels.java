package cofh.archersparadox.data;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

import java.util.function.Supplier;

import static cofh.archersparadox.ArchersParadox.ITEMS;
import static cofh.archersparadox.init.ModReferences.*;
import static cofh.lib.util.constants.Constants.ID_ARCHERS_PARADOX;

public class ModItemModels extends ItemModelProvider {

    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_ARCHERS_PARADOX, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Archer's Paradox: Item Models";
    }

    @Override
    protected void registerModels() {

        String projectiles = "projectiles";

        generated(ITEMS.getSup(ID_BLAZE_ARROW), projectiles);
        generated(ITEMS.getSup(ID_CHALLENGE_ARROW), projectiles);
        generated(ITEMS.getSup(ID_DIAMOND_ARROW), projectiles);
        generated(ITEMS.getSup(ID_DISPLACEMENT_ARROW), projectiles);
        generated(ITEMS.getSup(ID_ENDER_ARROW), projectiles);
        generated(ITEMS.getSup(ID_EXPLOSIVE_ARROW), projectiles);
        generated(ITEMS.getSup(ID_FROST_ARROW), projectiles);
        generated(ITEMS.getSup(ID_GLOWSTONE_ARROW), projectiles);
        generated(ITEMS.getSup(ID_LIGHTNING_ARROW), projectiles);
        // generated(ITEMS.getSup(ID_MAGMA_ARROW),projectiles);
        generated(ITEMS.getSup(ID_PHANTASMAL_ARROW), projectiles);
        generated(ITEMS.getSup(ID_PRISMARINE_ARROW), projectiles);
        generated(ITEMS.getSup(ID_QUARTZ_ARROW), projectiles);
        generated(ITEMS.getSup(ID_REDSTONE_ARROW), projectiles);
        generated(ITEMS.getSup(ID_SHULKER_ARROW), projectiles);
        generated(ITEMS.getSup(ID_SLIME_ARROW), projectiles);
        generated(ITEMS.getSup(ID_SPORE_ARROW), projectiles);
        generated(ITEMS.getSup(ID_TRAINING_ARROW), projectiles);
        generated(ITEMS.getSup(ID_VERDANT_ARROW), projectiles);
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

    private ItemModelBuilder handheld(Supplier<? extends IItemProvider> item, ResourceLocation texture) {

        return withExistingParent(name(item), "item/handheld").texture("layer0", texture);
    }
    // endregion
}
