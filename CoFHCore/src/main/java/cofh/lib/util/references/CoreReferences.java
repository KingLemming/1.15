package cofh.lib.util.references;

import net.minecraft.block.Block;
import net.minecraft.potion.Effect;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

public class CoreReferences {

    private CoreReferences() {

    }

    // region IDS
    public static final String ID_GLOSSED_MAGMA = ID_COFH_CORE + ":glossed_magma";

    public static final String ID_ENDERFERENCE = ID_COFH_CORE + ":enderference";
    public static final String ID_EPIPHANY = ID_COFH_CORE + ":epiphany";
    // endregion

    // region BLOCKS
    @ObjectHolder(ID_GLOSSED_MAGMA)
    public static final Block GLOSSED_MAGMA = null;
    // endregion

    // region EFFECTS
    @ObjectHolder(ID_ENDERFERENCE)
    public static final Effect ENDERFERENCE = null;

    @ObjectHolder(ID_EPIPHANY)
    public static final Effect EPIPHANY = null;
    // endregion
}
