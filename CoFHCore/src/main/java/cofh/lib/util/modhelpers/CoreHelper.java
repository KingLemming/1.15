package cofh.lib.util.modhelpers;

import net.minecraft.block.Block;
import net.minecraft.potion.Effect;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

public class CoreHelper {

    private CoreHelper() {

    }

    // region BLOCKS
    public static final String ID_GLOSSED_MAGMA = ID_COFH_CORE + ":glossed_magma";

    @ObjectHolder(ID_GLOSSED_MAGMA)
    public static final Block GLOSSED_MAGMA = null;
    // endregion

    // region EFFECTS
    public static final String ID_ENDERFERENCE = ID_COFH_CORE + ":enderference";
    public static final String ID_EUREKA = ID_COFH_CORE + ":eureka";

    @ObjectHolder(ID_ENDERFERENCE)
    public static final Effect ENDERFERENCE = null;

    @ObjectHolder(ID_EUREKA)
    public static final Effect EUREKA = null;
    // endregion
}
