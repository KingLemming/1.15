package cofh.archersparadox.init;

import cofh.archersparadox.ArchersParadox;
import cofh.archersparadox.entity.projectile.*;
import cofh.core.item.ArrowItemCoFH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.archersparadox.ArchersParadox.ITEMS;
import static cofh.archersparadox.init.APIDs.*;

public class APItems {

    private APItems() {

    }

    public static void register() {

        ItemGroup group = ItemGroup.COMBAT;

        ITEMS.register(ID_BLAZE_ARROW, () -> new ArrowItemCoFH(BlazeArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_CHALLENGE_ARROW, () -> new ArrowItemCoFH(ChallengeArrowEntity::new, new Item.Properties().group(group).rarity(Rarity.UNCOMMON)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_DIAMOND_ARROW, () -> new ArrowItemCoFH(DiamondArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_DISPLACEMENT_ARROW, () -> new ArrowItemCoFH(DisplacementArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_ENDER_ARROW, () -> new ArrowItemCoFH(EnderArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_EXPLOSIVE_ARROW, () -> new ArrowItemCoFH(ExplosiveArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_FROST_ARROW, () -> new ArrowItemCoFH(FrostArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_GLOWSTONE_ARROW, () -> new ArrowItemCoFH(GlowstoneArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_LIGHTNING_ARROW, () -> new ArrowItemCoFH(LightningArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        // ITEMS.register(ID_MAGMA_ARROW, () -> new ArrowItemCoFH(MagmaArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_PHANTASMAL_ARROW, () -> new ArrowItemCoFH(PhantasmalArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_PRISMARINE_ARROW, () -> new ArrowItemCoFH(PrismarineArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_QUARTZ_ARROW, () -> new ArrowItemCoFH(QuartzArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_REDSTONE_ARROW, () -> new ArrowItemCoFH(RedstoneArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_SHULKER_ARROW, () -> new ArrowItemCoFH(ShulkerArrowEntity::new, new Item.Properties().group(group).rarity(Rarity.UNCOMMON)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_SLIME_ARROW, () -> new ArrowItemCoFH(SlimeArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_SPORE_ARROW, () -> new ArrowItemCoFH(SporeArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
        ITEMS.register(ID_TRAINING_ARROW, () -> new ArrowItemCoFH(TrainingArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup).setInfinitySupport(true));
        ITEMS.register(ID_VERDANT_ARROW, () -> new ArrowItemCoFH(VerdantArrowEntity::new, new Item.Properties().group(group)).setDisplayGroup(() -> ArchersParadox.itemGroup));
    }

}
