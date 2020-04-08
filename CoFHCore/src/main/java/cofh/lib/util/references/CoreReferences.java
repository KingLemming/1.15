package cofh.lib.util.references;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

public class CoreReferences {

    private CoreReferences() {

    }

    // region IDS
    public static final String ID_GLOSSED_MAGMA = ID_COFH_CORE + ":glossed_magma";
    public static final String ID_SIGNAL_AIR = ID_COFH_CORE + ":signal_air";
    public static final String ID_GLOW_AIR = ID_COFH_CORE + ":glow_air";
    public static final String ID_ENDER_AIR = ID_COFH_CORE + ":ender_air";

    public static final String ID_FLUID_POTION = ID_COFH_CORE + ":potion";

    public static final String ID_EFFECT_EXPLOSION_RESISTANCE = ID_COFH_CORE + ":explosion_resistance";
    public static final String ID_EFFECT_LIGHTNING_RESISTANCE = ID_COFH_CORE + ":lightning_resistance";
    public static final String ID_EFFECT_MAGIC_RESISTANCE = ID_COFH_CORE + ":magic_resistance";

    public static final String ID_EFFECT_AMPLIFICATION = ID_COFH_CORE + ":amplification";
    public static final String ID_EFFECT_CHILLED = ID_COFH_CORE + ":chilled";
    public static final String ID_EFFECT_CLARITY = ID_COFH_CORE + ":clarity";
    public static final String ID_EFFECT_ENDERFERENCE = ID_COFH_CORE + ":enderference";
    public static final String ID_EFFECT_LOVE = ID_COFH_CORE + ":love";
    public static final String ID_EFFECT_PANACEA = ID_COFH_CORE + ":panacea";
    public static final String ID_EFFECT_REDERGIZED = ID_COFH_CORE + ":redergized";

    public static final String ID_HOLDING = ID_COFH_CORE + ":holding";

    public static final String ID_ECTOPLASM = ID_COFH_CORE + ":ectoplasm";

    // public static final String ID_PARTICLE_SNOW = ID_COFH_CORE + ":snow";
    // endregion

    // region BLOCKS
    @ObjectHolder(ID_GLOSSED_MAGMA)
    public static final Block GLOSSED_MAGMA = null;

    @ObjectHolder(ID_SIGNAL_AIR)
    public static final Block SIGNAL_AIR = null;

    @ObjectHolder(ID_GLOW_AIR)
    public static final Block GLOW_AIR = null;

    @ObjectHolder(ID_ENDER_AIR)
    public static final Block ENDER_AIR = null;
    // endregion

    // region FLUIDS
    @ObjectHolder(ID_FLUID_POTION)
    public static final FlowingFluid FLUID_POTION = null;
    // endregion

    // region TILES
    @ObjectHolder(ID_SIGNAL_AIR)
    public static final TileEntityType<?> SIGNAL_AIR_TILE = null;

    @ObjectHolder(ID_GLOW_AIR)
    public static final TileEntityType<?> GLOW_AIR_TILE = null;

    @ObjectHolder(ID_ENDER_AIR)
    public static final TileEntityType<?> ENDER_AIR_TILE = null;
    // endregion

    // region EFFECTS
    @ObjectHolder(ID_EFFECT_EXPLOSION_RESISTANCE)
    public static final Effect EXPLOSION_RESISTANCE = null;

    @ObjectHolder(ID_EFFECT_LIGHTNING_RESISTANCE)
    public static final Effect LIGHTNING_RESISTANCE = null;

    @ObjectHolder(ID_EFFECT_MAGIC_RESISTANCE)
    public static final Effect MAGIC_RESISTANCE = null;

    @ObjectHolder(ID_EFFECT_AMPLIFICATION)
    public static final Effect AMPLIFICATION = null;

    @ObjectHolder(ID_EFFECT_CHILLED)
    public static final Effect CHILLED = null;

    @ObjectHolder(ID_EFFECT_CLARITY)
    public static final Effect CLARITY = null;

    @ObjectHolder(ID_EFFECT_ENDERFERENCE)
    public static final Effect ENDERFERENCE = null;

    @ObjectHolder(ID_EFFECT_LOVE)
    public static final Effect LOVE = null;

    @ObjectHolder(ID_EFFECT_PANACEA)
    public static final Effect PANACEA = null;
    // endregion

    // region ENCHANTMENTS
    @ObjectHolder(ID_HOLDING)
    public static final Enchantment HOLDING = null;
    // endregion

    // region ITEMS
    @ObjectHolder(ID_ECTOPLASM)
    public static final Item ECTOPLASM = null;
    // endregion

    // region PARTICLES
    //    @ObjectHolder(ID_PARTICLE_SNOW)
    //    public static final BasicParticleType SNOW = null;
    // endregion
}
