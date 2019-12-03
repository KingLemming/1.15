package cofh.thermal.core.init;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class ThermalReferences {

    private ThermalReferences() {

    }

    // region IDS
    public static final String ID_SIGNALUM_BLOCK = ID_THERMAL + ":signalum_block";
    public static final String ID_LUMIUM_BLOCK = ID_THERMAL + ":lumium_block";
    public static final String ID_ENDERIUM_BLOCK = ID_THERMAL + ":enderium_block";
    // endregion

    // region BLOCKS
    @ObjectHolder(ID_SIGNALUM_BLOCK)
    public static final Block SIGNALUM_BLOCK = null;

    @ObjectHolder(ID_LUMIUM_BLOCK)
    public static final Block LUMIUM_BLOCK = null;

    @ObjectHolder(ID_ENDERIUM_BLOCK)
    public static final Block ENDERIUM_BLOCK = null;
    // endregion
}
