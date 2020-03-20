package cofh.archersparadox.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_ARCHERS_PARADOX;

public class ModReferences {

    private ModReferences() {

    }

    // region IDS
    public static final String ID_BLAZE_ARROW = ID_ARCHERS_PARADOX + ":blaze_arrow";
    public static final String ID_CHALLENGE_ARROW = ID_ARCHERS_PARADOX + ":challenge_arrow";
    public static final String ID_DIAMOND_ARROW = ID_ARCHERS_PARADOX + ":diamond_arrow";
    public static final String ID_DISPLACEMENT_ARROW = ID_ARCHERS_PARADOX + ":displacement_arrow";
    public static final String ID_ENDER_ARROW = ID_ARCHERS_PARADOX + ":ender_arrow";
    public static final String ID_EXPLOSIVE_ARROW = ID_ARCHERS_PARADOX + ":explosive_arrow";
    public static final String ID_FROST_ARROW = ID_ARCHERS_PARADOX + ":frost_arrow";
    public static final String ID_GLOWSTONE_ARROW = ID_ARCHERS_PARADOX + ":glowstone_arrow";
    public static final String ID_LIGHTNING_ARROW = ID_ARCHERS_PARADOX + ":lightning_arrow";
    public static final String ID_MAGMA_ARROW = ID_ARCHERS_PARADOX + ":magma_arrow";
    public static final String ID_PHANTASMAL_ARROW = ID_ARCHERS_PARADOX + ":phantasmal_arrow";
    public static final String ID_PRISMARINE_ARROW = ID_ARCHERS_PARADOX + ":prismarine_arrow";
    public static final String ID_QUARTZ_ARROW = ID_ARCHERS_PARADOX + ":quartz_arrow";
    public static final String ID_REDSTONE_ARROW = ID_ARCHERS_PARADOX + ":redstone_arrow";
    public static final String ID_SHULKER_ARROW = ID_ARCHERS_PARADOX + ":shulker_arrow";
    public static final String ID_SLIME_ARROW = ID_ARCHERS_PARADOX + ":slime_arrow";
    public static final String ID_SPORE_ARROW = ID_ARCHERS_PARADOX + ":spore_arrow";
    public static final String ID_TRAINING_ARROW = ID_ARCHERS_PARADOX + ":training_arrow";
    public static final String ID_VERDANT_ARROW = ID_ARCHERS_PARADOX + ":verdant_arrow";

    public static final String ID_EFFECT_CHALLENGE_COMPLETE = ID_ARCHERS_PARADOX + ":challenge_complete";
    public static final String ID_EFFECT_CHALLENGE_MISS = ID_ARCHERS_PARADOX + ":challenge_miss";
    public static final String ID_EFFECT_CHALLENGE_STREAK = ID_ARCHERS_PARADOX + ":challenge_streak";
    public static final String ID_EFFECT_TRAINING_MISS = ID_ARCHERS_PARADOX + ":training_miss";
    public static final String ID_EFFECT_TRAINING_STREAK = ID_ARCHERS_PARADOX + ":training_streak";
    // endregion

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
    public static final EntityType BLAZE_ARROW_ENTITY = null;

    @ObjectHolder(ID_CHALLENGE_ARROW)
    public static final EntityType CHALLENGE_ARROW_ENTITY = null;

    @ObjectHolder(ID_DIAMOND_ARROW)
    public static final EntityType DIAMOND_ARROW_ENTITY = null;

    @ObjectHolder(ID_DISPLACEMENT_ARROW)
    public static final EntityType DISPLACEMENT_ARROW_ENTITY = null;

    @ObjectHolder(ID_ENDER_ARROW)
    public static final EntityType ENDER_ARROW_ENTITY = null;

    @ObjectHolder(ID_EXPLOSIVE_ARROW)
    public static final EntityType EXPLOSIVE_ARROW_ENTITY = null;

    @ObjectHolder(ID_FROST_ARROW)
    public static final EntityType FROST_ARROW_ENTITY = null;

    @ObjectHolder(ID_GLOWSTONE_ARROW)
    public static final EntityType GLOWSTONE_ARROW_ENTITY = null;

    @ObjectHolder(ID_LIGHTNING_ARROW)
    public static final EntityType LIGHTNING_ARROW_ENTITY = null;

    @ObjectHolder(ID_MAGMA_ARROW)
    public static final EntityType MAGMA_ARROW_ENTITY = null;

    @ObjectHolder(ID_PHANTASMAL_ARROW)
    public static final EntityType PHANTASMAL_ARROW_ENTITY = null;

    @ObjectHolder(ID_PRISMARINE_ARROW)
    public static final EntityType PRISMARINE_ARROW_ENTITY = null;

    @ObjectHolder(ID_QUARTZ_ARROW)
    public static final EntityType QUARTZ_ARROW_ENTITY = null;

    @ObjectHolder(ID_REDSTONE_ARROW)
    public static final EntityType REDSTONE_ARROW_ENTITY = null;

    @ObjectHolder(ID_SHULKER_ARROW)
    public static final EntityType SHULKER_ARROW_ENTITY = null;

    @ObjectHolder(ID_SLIME_ARROW)
    public static final EntityType SLIME_ARROW_ENTITY = null;

    @ObjectHolder(ID_SPORE_ARROW)
    public static final EntityType SPORE_ARROW_ENTITY = null;

    @ObjectHolder(ID_TRAINING_ARROW)
    public static final EntityType TRAINING_ARROW_ENTITY = null;

    @ObjectHolder(ID_VERDANT_ARROW)
    public static final EntityType VERDANT_ARROW_ENTITY = null;
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
