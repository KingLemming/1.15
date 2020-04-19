package cofh.archersparadox.data;

import cofh.lib.data.ItemModelProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.archersparadox.ArchersParadox.ITEMS;
import static cofh.archersparadox.init.ModReferences.*;
import static cofh.lib.util.constants.Constants.ID_ARCHERS_PARADOX;

public class ModItemModels extends ItemModelProviderCoFH {

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

}
