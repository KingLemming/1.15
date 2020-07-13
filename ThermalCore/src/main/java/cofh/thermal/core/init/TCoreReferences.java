package cofh.thermal.core.init;

import cofh.thermal.core.entity.monster.BasalzEntity;
import cofh.thermal.core.entity.monster.BlitzEntity;
import cofh.thermal.core.entity.monster.BlizzEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TCoreReferences {

    private TCoreReferences() {

    }

    // region VANILLA
    public static final String ID_CHARCOAL_BLOCK = "charcoal_block";
    public static final String ID_BAMBOO_BLOCK = "bamboo_block";
    public static final String ID_SUGAR_CANE_BLOCK = "sugar_cane_block";
    public static final String ID_GUNPOWDER_BLOCK = "gunpowder_block";

    public static final String ID_APPLE_BLOCK = "apple_block";
    public static final String ID_BEETROOT_BLOCK = "beetroot_block";
    public static final String ID_CARROT_BLOCK = "carrot_block";
    public static final String ID_POTATO_BLOCK = "potato_block";
    // endregion

    // region RESOURCES
    public static final String ID_APATITE_ORE = "apatite_ore";
    public static final String ID_CINNABAR_ORE = "cinnabar_ore";
    public static final String ID_NITER_ORE = "niter_ore";
    public static final String ID_SULFUR_ORE = "sulfur_ore";

    public static final String ID_COPPER_ORE = "copper_ore";
    public static final String ID_LEAD_ORE = "lead_ore";
    public static final String ID_NICKEL_ORE = "nickel_ore";
    public static final String ID_SILVER_ORE = "silver_ore";
    public static final String ID_TIN_ORE = "tin_ore";

    public static final String ID_RUBY_ORE = "ruby_ore";
    public static final String ID_SAPPHIRE_ORE = "sapphire_ore";
    // endregion

    // region STORAGE
    public static final String ID_APATITE_BLOCK = "apatite_block";
    public static final String ID_CINNABAR_BLOCK = "cinnabar_block";
    public static final String ID_NITER_BLOCK = "niter_block";
    public static final String ID_SULFUR_BLOCK = "sulfur_block";

    public static final String ID_COPPER_BLOCK = "copper_block";
    public static final String ID_LEAD_BLOCK = "lead_block";
    public static final String ID_NICKEL_BLOCK = "nickel_block";
    public static final String ID_SILVER_BLOCK = "silver_block";
    public static final String ID_TIN_BLOCK = "tin_block";

    public static final String ID_BRONZE_BLOCK = "bronze_block";
    public static final String ID_CONSTANTAN_BLOCK = "constantan_block";
    public static final String ID_ELECTRUM_BLOCK = "electrum_block";
    public static final String ID_INVAR_BLOCK = "invar_block";

    public static final String ID_RUBY_BLOCK = "ruby_block";
    public static final String ID_SAPPHIRE_BLOCK = "sapphire_block";

    public static final String ID_ENDERIUM_BLOCK = "enderium_block";
    public static final String ID_LUMIUM_BLOCK = "lumium_block";
    public static final String ID_SIGNALUM_BLOCK = "signalum_block";
    // endregion

    public static final String ID_ENDERIUM_GLASS = "enderium_glass";
    public static final String ID_LUMIUM_GLASS = "lumium_glass";
    public static final String ID_SIGNALUM_GLASS = "signalum_glass";

    public static final String ID_REDSTONE_FLUID = "fluid_redstone";
    public static final String ID_GLOWSTONE_FLUID = "fluid_glowstone";
    public static final String ID_ENDER_FLUID = "fluid_ender";

    // region ENTITIES
    public static final String ID_BASALZ = ID_THERMAL + ":basalz";
    public static final String ID_BLITZ = ID_THERMAL + ":blitz";
    public static final String ID_BLIZZ = ID_THERMAL + ":blizz";

    @ObjectHolder(ID_BASALZ)
    public static final EntityType<BasalzEntity> BASALZ_ENTITY = null;

    @ObjectHolder(ID_BLITZ)
    public static final EntityType<BlitzEntity> BLITZ_ENTITY = null;

    @ObjectHolder(ID_BLIZZ)
    public static final EntityType<BlizzEntity> BLIZZ_ENTITY = null;
    // endregion

    // region SOUND EVENTS
    public static final String ID_SOUND_MAGNET = ID_THERMAL + ":item.magnet";
    public static final String ID_SOUND_TINKER = ID_THERMAL + ":misc.tinker";

    @ObjectHolder(ID_SOUND_MAGNET)
    public static final SoundEvent SOUND_MAGNET = null;

    @ObjectHolder(ID_SOUND_TINKER)
    public static final SoundEvent SOUND_TINKER = null;
    // endregion
}
