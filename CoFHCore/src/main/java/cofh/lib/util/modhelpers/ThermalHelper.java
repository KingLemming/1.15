package cofh.lib.util.modhelpers;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.Constants.ID_THERMAL_SERIES;

public class ThermalHelper {

    private ThermalHelper() {

    }

    // region BLOCK IDS
    public static final String ID_CHARCOAL_BLOCK = ID_THERMAL_SERIES + "charcoal_block";
    public static final String ID_COAL_COKE_BLOCK = ID_THERMAL_SERIES + "coal_coke_block";

    public static final String ID_SIGNALUM_BLOCK = ID_THERMAL_SERIES + "signalum_block";
    public static final String ID_LUMIUM_BLOCK = ID_THERMAL_SERIES + "lumium_block";
    public static final String ID_ENDERIUM_BLOCK = ID_THERMAL_SERIES + "enderium_block";
    // endregion

    // region BLOCKS
    @ObjectHolder(ID_CHARCOAL_BLOCK)
    public static final Block CHARCOAL_BLOCK = null;

    @ObjectHolder(ID_COAL_COKE_BLOCK)
    public static final Block COAL_COKE_BLOCK = null;

    @ObjectHolder(ID_SIGNALUM_BLOCK)
    public static final Block SIGNALUM_BLOCK = null;

    @ObjectHolder(ID_LUMIUM_BLOCK)
    public static final Block LUMIUM_BLOCK = null;

    @ObjectHolder(ID_ENDERIUM_BLOCK)
    public static final Block ENDERIUM_BLOCK = null;
    // endregion
}
