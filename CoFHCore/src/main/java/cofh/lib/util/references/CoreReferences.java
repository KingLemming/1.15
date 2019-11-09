package cofh.lib.util.references;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Effect;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

public class CoreReferences {

    private CoreReferences() {

    }

    // region IDS
    public static final String ID_GLOSSED_MAGMA = ID_COFH_CORE + ":glossed_magma";

    public static final String ID_EFFECT_LIGHTNING_RESISTANCE = ID_COFH_CORE + ":lightning_resistance";
    public static final String ID_EFFECT_MAGIC_RESISTANCE = ID_COFH_CORE + ":magic_resistance";

    public static final String ID_EFFECT_AMPLIFICATION = ID_COFH_CORE + ":amplification";
    public static final String ID_EFFECT_CLARITY = ID_COFH_CORE + ":clarity";
    public static final String ID_EFFECT_ENDERFERENCE = ID_COFH_CORE + ":enderference";
    public static final String ID_EFFECT_LOVE = ID_COFH_CORE + ":love";
    public static final String ID_EFFECT_PANACEA = ID_COFH_CORE + ":panacea";

    public static final String ID_HOLDING = ID_COFH_CORE + ":holding";
    // endregion

    // region BLOCKS
    @ObjectHolder(ID_GLOSSED_MAGMA)
    public static final Block GLOSSED_MAGMA = null;
    // endregion

    // region EFFECTS
    @ObjectHolder(ID_EFFECT_LIGHTNING_RESISTANCE)
    public static final Effect LIGHTNING_RESISTANCE = null;

    @ObjectHolder(ID_EFFECT_MAGIC_RESISTANCE)
    public static final Effect MAGIC_RESISTANCE = null;

    @ObjectHolder(ID_EFFECT_AMPLIFICATION)
    public static final Effect AMPLIFICATION = null;

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
}
