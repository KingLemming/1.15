package cofh.cofh_archery.init;

import cofh.cofh_archery.item.projectile.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.cofh_archery.CoFHArchery.COFH_ARCHERY_GROUP;
import static cofh.cofh_archery.CoFHArchery.ITEMS;
import static cofh.cofh_archery.init.ModReferences.*;

public class ModItems {

    private ModItems() {

    }

    public static void register() {

        ItemGroup group = COFH_ARCHERY_GROUP;

        ITEMS.register(ID_BLAZE_ARROW, () -> new BlazeArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_DIAMOND_ARROW, () -> new DiamondArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_ENDER_ARROW, () -> new EnderArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_EXPLOSIVE_ARROW, () -> new ExplosiveArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_FROST_ARROW, () -> new FrostArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_LIGHTNING_ARROW, () -> new LightningArrowItem(new Item.Properties().group(group)));
        // ITEMS.register(ID_MAGMA_ARROW, () -> new MagmaArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_PHANTASMAL_ARROW, () -> new PhantasmalArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_PRISMARINE_ARROW, () -> new PrismarineArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_QUARTZ_ARROW, () -> new PrismarineArrowItem(new Item.Properties().group(group)));
        // ITEMS.register(ID_REDSTONE_ARROW, () -> new RedstoneArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_SHULKER_ARROW, () -> new ShulkerArrowItem(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register(ID_SLIME_ARROW, () -> new SlimeArrowItem(new Item.Properties().group(group)));
        // ITEMS.register(ID_SPORE_ARROW, () -> new SporeArrowItem(new Item.Properties().group(group)));
        ITEMS.register(ID_TRAINING_ARROW, () -> new TrainingArrowItem(new Item.Properties().group(group)));
        // ITEMS.register(ID_VERDANT_ARROW, () -> new VerdantArrowItem(new Item.Properties().group(group)));
    }

}
