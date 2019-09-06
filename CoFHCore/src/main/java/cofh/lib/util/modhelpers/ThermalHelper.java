package cofh.lib.util.modhelpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL_SERIES;

public class ThermalHelper {

    private ThermalHelper() {

    }

    // region BLOCK IDS
    public static final String ID_CHARCOAL_BLOCK = ID_THERMAL_SERIES + ":block_charcoal";
    public static final String ID_COAL_COKE_BLOCK = ID_THERMAL_SERIES + ":block_coal_coke";

    public static final String ID_SIGNALUM_BLOCK = ID_THERMAL_SERIES + ":block_signalum";
    public static final String ID_LUMIUM_BLOCK = ID_THERMAL_SERIES + ":block_lumium";
    public static final String ID_ENDERIUM_BLOCK = ID_THERMAL_SERIES + ":block_enderium";
    // endregion

    // region ITEM IDS
    public static final String ID_SIGNALUM_INGOT = ID_THERMAL_SERIES + ":ingot_signalum";
    public static final String ID_LUMIUM_INGOT = ID_THERMAL_SERIES + ":ingot_lumium";
    public static final String ID_ENDERIUM_INGOT = ID_THERMAL_SERIES + ":ingot_enderium";

    public static final String ID_SIGNALUM_NUGGET = ID_THERMAL_SERIES + ":nugget_signalum";
    public static final String ID_LUMIUM_NUGGET = ID_THERMAL_SERIES + ":nugget_lumium";
    public static final String ID_ENDERIUM_NUGGET = ID_THERMAL_SERIES + ":nugget_enderium";
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

    // region ITEMS
    @ObjectHolder(ID_SIGNALUM_INGOT)
    public static final Item SIGNALUM_INGOT = null;

    @ObjectHolder(ID_LUMIUM_INGOT)
    public static final Item LUMIUM_INGOT = null;

    @ObjectHolder(ID_ENDERIUM_INGOT)
    public static final Item ENDERIUM_INGOT = null;

    @ObjectHolder(ID_SIGNALUM_NUGGET)
    public static final Item SIGNALUM_NUGGET = null;

    @ObjectHolder(ID_LUMIUM_NUGGET)
    public static final Item LUMIUM_NUGGET = null;

    @ObjectHolder(ID_ENDERIUM_NUGGET)
    public static final Item ENDERIUM_NUGGET = null;
    // endregion
}
