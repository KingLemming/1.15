package cofh.archersparadox.init;

import cofh.archersparadox.ArchersParadox;
import cofh.archersparadox.item.projectile.*;
import cofh.core.init.CoreFeatures;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.archersparadox.ArchersParadox.ITEMS;
import static cofh.archersparadox.init.APReferences.*;
import static cofh.core.init.CoreFeatures.FLAG_ECTOPLASM;

public class APItems {

    private APItems() {

    }

    public static void register() {

        CoreFeatures.setFeature(FLAG_ECTOPLASM, true);

        ItemGroup group = ItemGroup.COMBAT;

        ITEMS.register(ID_BLAZE_ARROW, () -> new BlazeArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_CHALLENGE_ARROW, () -> new ChallengeArrowItem(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_DIAMOND_ARROW, () -> new DiamondArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_DISPLACEMENT_ARROW, () -> new DisplacementArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_ENDER_ARROW, () -> new EnderArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_EXPLOSIVE_ARROW, () -> new ExplosiveArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_FROST_ARROW, () -> new FrostArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_GLOWSTONE_ARROW, () -> new GlowstoneArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_LIGHTNING_ARROW, () -> new LightningArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        // ITEMS.register(ID_MAGMA_ARROW, () -> new MagmaArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_PHANTASMAL_ARROW, () -> new PhantasmalArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_PRISMARINE_ARROW, () -> new PrismarineArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_QUARTZ_ARROW, () -> new QuartzArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_REDSTONE_ARROW, () -> new RedstoneArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_SHULKER_ARROW, () -> new ShulkerArrowItem(new Item.Properties().group(group).rarity(Rarity.UNCOMMON)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_SLIME_ARROW, () -> new SlimeArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_SPORE_ARROW, () -> new SporeArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_TRAINING_ARROW, () -> new TrainingArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_VERDANT_ARROW, () -> new VerdantArrowItem(new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
    }

}
