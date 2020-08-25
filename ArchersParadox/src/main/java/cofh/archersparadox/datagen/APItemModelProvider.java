package cofh.archersparadox.datagen;

import cofh.lib.datagen.ItemModelProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.archersparadox.ArchersParadox.ITEMS;
import static cofh.archersparadox.init.APIDs.*;
import static cofh.lib.util.constants.Constants.ID_ARCHERS_PARADOX;

public class APItemModelProvider extends ItemModelProviderCoFH {

    public APItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_ARCHERS_PARADOX, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Archer's Paradox: Item Models";
    }

    @Override
    protected void registerModels() {

        generated(ITEMS.getSup(ID_BLAZE_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_CHALLENGE_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_DIAMOND_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_DISPLACEMENT_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_ENDER_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_EXPLOSIVE_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_FROST_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_GLOWSTONE_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_LIGHTNING_ARROW), PROJECTILES);
        // generated(ITEMS.getSup(ID_MAGMA_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_PHANTASMAL_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_PRISMARINE_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_QUARTZ_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_REDSTONE_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_SHULKER_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_SLIME_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_SPORE_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_TRAINING_ARROW), PROJECTILES);
        generated(ITEMS.getSup(ID_VERDANT_ARROW), PROJECTILES);
    }

}
