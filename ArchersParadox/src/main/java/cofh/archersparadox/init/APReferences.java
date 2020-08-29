package cofh.archersparadox.init;

import cofh.archersparadox.entity.projectile.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.archersparadox.init.APIDs.*;
import static cofh.lib.util.constants.Constants.ID_ARCHERS_PARADOX;

@ObjectHolder(ID_ARCHERS_PARADOX)
public class APReferences {

    private APReferences() {

    }

    // region EFFECTS
    @ObjectHolder(ID_EFFECT_CHALLENGE_COMPLETE)
    public static final Effect CHALLENGE_COMPLETE = null;

    @ObjectHolder(ID_EFFECT_CHALLENGE_MISS)
    public static final Effect CHALLENGE_MISS = null;

    @ObjectHolder(ID_EFFECT_CHALLENGE_STREAK)
    public static final Effect CHALLENGE_STREAK = null;

    @ObjectHolder(ID_EFFECT_TRAINING_MISS)
    public static final Effect TRAINING_MISS = null;

    @ObjectHolder(ID_EFFECT_TRAINING_STREAK)
    public static final Effect TRAINING_STREAK = null;
    // endregion

    // region ENTITIES
    @ObjectHolder(ID_BLAZE_ARROW)
    public static final EntityType<BlazeArrowEntity> BLAZE_ARROW_ENTITY = null;

    @ObjectHolder(ID_CHALLENGE_ARROW)
    public static final EntityType<ChallengeArrowEntity> CHALLENGE_ARROW_ENTITY = null;

    @ObjectHolder(ID_DIAMOND_ARROW)
    public static final EntityType<DiamondArrowEntity> DIAMOND_ARROW_ENTITY = null;

    @ObjectHolder(ID_DISPLACEMENT_ARROW)
    public static final EntityType<DisplacementArrowEntity> DISPLACEMENT_ARROW_ENTITY = null;

    @ObjectHolder(ID_ENDER_ARROW)
    public static final EntityType<EnderArrowEntity> ENDER_ARROW_ENTITY = null;

    @ObjectHolder(ID_EXPLOSIVE_ARROW)
    public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW_ENTITY = null;

    @ObjectHolder(ID_FROST_ARROW)
    public static final EntityType<FrostArrowEntity> FROST_ARROW_ENTITY = null;

    @ObjectHolder(ID_GLOWSTONE_ARROW)
    public static final EntityType<GlowstoneArrowEntity> GLOWSTONE_ARROW_ENTITY = null;

    @ObjectHolder(ID_LIGHTNING_ARROW)
    public static final EntityType<LightningArrowEntity> LIGHTNING_ARROW_ENTITY = null;

    @ObjectHolder(ID_MAGMA_ARROW)
    public static final EntityType<MagmaArrowEntity> MAGMA_ARROW_ENTITY = null;

    @ObjectHolder(ID_PHANTASMAL_ARROW)
    public static final EntityType<PhantasmalArrowEntity> PHANTASMAL_ARROW_ENTITY = null;

    @ObjectHolder(ID_PRISMARINE_ARROW)
    public static final EntityType<PrismarineArrowEntity> PRISMARINE_ARROW_ENTITY = null;

    @ObjectHolder(ID_QUARTZ_ARROW)
    public static final EntityType<QuartzArrowEntity> QUARTZ_ARROW_ENTITY = null;

    @ObjectHolder(ID_REDSTONE_ARROW)
    public static final EntityType<RedstoneArrowEntity> REDSTONE_ARROW_ENTITY = null;

    @ObjectHolder(ID_SHULKER_ARROW)
    public static final EntityType<ShulkerArrowEntity> SHULKER_ARROW_ENTITY = null;

    @ObjectHolder(ID_SLIME_ARROW)
    public static final EntityType<SlimeArrowEntity> SLIME_ARROW_ENTITY = null;

    @ObjectHolder(ID_SPORE_ARROW)
    public static final EntityType<SporeArrowEntity> SPORE_ARROW_ENTITY = null;

    @ObjectHolder(ID_TRAINING_ARROW)
    public static final EntityType<TrainingArrowEntity> TRAINING_ARROW_ENTITY = null;

    @ObjectHolder(ID_VERDANT_ARROW)
    public static final EntityType<VerdantArrowEntity> VERDANT_ARROW_ENTITY = null;
    // endregion

    // region ITEMS
    @ObjectHolder(ID_BLAZE_ARROW)
    public static final Item BLAZE_ARROW_ITEM = null;

    @ObjectHolder(ID_CHALLENGE_ARROW)
    public static final Item CHALLENGE_ARROW_ITEM = null;

    @ObjectHolder(ID_DIAMOND_ARROW)
    public static final Item DIAMOND_ARROW_ITEM = null;

    @ObjectHolder(ID_DISPLACEMENT_ARROW)
    public static final Item DISPLACEMENT_ARROW_ITEM = null;

    @ObjectHolder(ID_ENDER_ARROW)
    public static final Item ENDER_ARROW_ITEM = null;

    @ObjectHolder(ID_EXPLOSIVE_ARROW)
    public static final Item EXPLOSIVE_ARROW_ITEM = null;

    @ObjectHolder(ID_FROST_ARROW)
    public static final Item FROST_ARROW_ITEM = null;

    @ObjectHolder(ID_GLOWSTONE_ARROW)
    public static final Item GLOWSTONE_ARROW_ITEM = null;

    @ObjectHolder(ID_LIGHTNING_ARROW)
    public static final Item LIGHTNING_ARROW_ITEM = null;

    @ObjectHolder(ID_MAGMA_ARROW)
    public static final Item MAGMA_ARROW_ITEM = null;

    @ObjectHolder(ID_PHANTASMAL_ARROW)
    public static final Item PHANTASMAL_ARROW_ITEM = null;

    @ObjectHolder(ID_PRISMARINE_ARROW)
    public static final Item PRISMARINE_ARROW_ITEM = null;

    @ObjectHolder(ID_QUARTZ_ARROW)
    public static final Item QUARTZ_ARROW_ITEM = null;

    @ObjectHolder(ID_REDSTONE_ARROW)
    public static final Item REDSTONE_ARROW_ITEM = null;

    @ObjectHolder(ID_SHULKER_ARROW)
    public static final Item SHULKER_ARROW_ITEM = null;

    @ObjectHolder(ID_SLIME_ARROW)
    public static final Item SLIME_ARROW_ITEM = null;

    @ObjectHolder(ID_SPORE_ARROW)
    public static final Item SPORE_ARROW_ITEM = null;

    @ObjectHolder(ID_TRAINING_ARROW)
    public static final Item TRAINING_ARROW_ITEM = null;

    @ObjectHolder(ID_VERDANT_ARROW)
    public static final Item VERDANT_ARROW_ITEM = null;
    // endregion
}
