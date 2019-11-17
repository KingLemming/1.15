package cofh.cofh_archery.init;

import cofh.cofh_archery.entity.projectile.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import static cofh.cofh_archery.CoFHArchery.ENTITIES;
import static cofh.cofh_archery.init.ModReferences.*;

public class ModEntities {

    private ModEntities() {

    }

    public static void register() {

        ENTITIES.register(ID_BLAZE_ARROW, () -> EntityType.Builder.<BlazeArrowEntity>create(BlazeArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_BLAZE_ARROW));
        ENTITIES.register(ID_DIAMOND_ARROW, () -> EntityType.Builder.<DiamondArrowEntity>create(DiamondArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_DIAMOND_ARROW));
        ENTITIES.register(ID_ENDER_ARROW, () -> EntityType.Builder.<EnderArrowEntity>create(EnderArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_ENDER_ARROW));
        ENTITIES.register(ID_EXPLOSIVE_ARROW, () -> EntityType.Builder.<ExplosiveArrowEntity>create(ExplosiveArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_EXPLOSIVE_ARROW));
        ENTITIES.register(ID_FROST_ARROW, () -> EntityType.Builder.<FrostArrowEntity>create(FrostArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_FROST_ARROW));
        ENTITIES.register(ID_LIGHTNING_ARROW, () -> EntityType.Builder.<LightningArrowEntity>create(LightningArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_LIGHTNING_ARROW));
        // ENTITIES.register(ID_MAGMA_ARROW, () -> EntityType.Builder.<MagmaArrowEntity>create(MagmaArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_MAGMA_ARROW));
        ENTITIES.register(ID_PHANTASMAL_ARROW, () -> EntityType.Builder.<PhantasmalArrowEntity>create(PhantasmalArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_PHANTASMAL_ARROW));
        ENTITIES.register(ID_PRISMARINE_ARROW, () -> EntityType.Builder.<PrismarineArrowEntity>create(PrismarineArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_PRISMARINE_ARROW));
        ENTITIES.register(ID_QUARTZ_ARROW, () -> EntityType.Builder.<QuartzArrowEntity>create(QuartzArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_QUARTZ_ARROW));
        // ENTITIES.register(ID_REDSTONE_ARROW, () -> EntityType.Builder.<RedstoneArrowEntity>create(RedstoneArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_REDSTONE_ARROW));
        ENTITIES.register(ID_SHULKER_ARROW, () -> EntityType.Builder.<ShulkerArrowEntity>create(ShulkerArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_SHULKER_ARROW));
        ENTITIES.register(ID_SLIME_ARROW, () -> EntityType.Builder.<SlimeArrowEntity>create(SlimeArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_SLIME_ARROW));
        // ENTITIES.register(ID_SPORE_ARROW, () -> EntityType.Builder.<SporeArrowEntity>create(SporeArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_SPORE_ARROW));
        ENTITIES.register(ID_TRAINING_ARROW, () -> EntityType.Builder.<TrainingArrowEntity>create(TrainingArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_TRAINING_ARROW));
        // ENTITIES.register(ID_VERDANT_ARROW, () -> EntityType.Builder.<VerdantArrowEntity>create(VerdantArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ID_VERDANT_ARROW));
    }

}
