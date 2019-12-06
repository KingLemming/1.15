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

    public static final String ID_COPPER_BLOCK = ID_THERMAL + ":copper_block";
    public static final String ID_TIN_BLOCK = ID_THERMAL + ":tin_block";
    public static final String ID_SILVER_BLOCK = ID_THERMAL + ":silver_block";
    public static final String ID_LEAD_BLOCK = ID_THERMAL + ":lead_block";
    public static final String ID_NICKEL_BLOCK = ID_THERMAL + ":nickel_block";
    public static final String ID_PLATINUM_BLOCK = ID_THERMAL + ":platinum_block";

    public static final String ID_BRONZE_BLOCK = ID_THERMAL + ":bronze_block";
    public static final String ID_ELECTRUM_BLOCK = ID_THERMAL + ":electrum_block";
    public static final String ID_INVAR_BLOCK = ID_THERMAL + ":invar_block";
    public static final String ID_CONSTANTAN_BLOCK = ID_THERMAL + ":constantan_block";

    public static final String ID_RUBY_BLOCK = ID_THERMAL + ":ruby_block";
    public static final String ID_SAPPHIRE_BLOCK = ID_THERMAL + ":sapphire_block";

    public static final String ID_REDSTONE_FLUID = ID_THERMAL + ":fluid_redstone";
    public static final String ID_GLOWSTONE_FLUID = ID_THERMAL + ":fluid_glowstone";
    public static final String ID_ENDER_FLUID = ID_THERMAL + ":fluid_ender";
    // endregion

    // region BLOCKS
    @ObjectHolder(ID_SIGNALUM_BLOCK)
    public static final Block SIGNALUM_BLOCK = null;

    @ObjectHolder(ID_LUMIUM_BLOCK)
    public static final Block LUMIUM_BLOCK = null;

    @ObjectHolder(ID_ENDERIUM_BLOCK)
    public static final Block ENDERIUM_BLOCK = null;

    @ObjectHolder(ID_COPPER_BLOCK)
    public static final Block COPPER_BLOCK = null;

    @ObjectHolder(ID_TIN_BLOCK)
    public static final Block TIN_BLOCK = null;

    @ObjectHolder(ID_SILVER_BLOCK)
    public static final Block SILVER_BLOCK = null;

    @ObjectHolder(ID_LEAD_BLOCK)
    public static final Block LEAD_BLOCK = null;

    @ObjectHolder(ID_NICKEL_BLOCK)
    public static final Block NICKEL_BLOCK = null;

    @ObjectHolder(ID_PLATINUM_BLOCK)
    public static final Block PLATINUM_BLOCK = null;

    @ObjectHolder(ID_BRONZE_BLOCK)
    public static final Block BRONZE_BLOCK = null;

    @ObjectHolder(ID_ELECTRUM_BLOCK)
    public static final Block ELECTRUM_BLOCK = null;

    @ObjectHolder(ID_INVAR_BLOCK)
    public static final Block INVAR_BLOCK = null;

    @ObjectHolder(ID_CONSTANTAN_BLOCK)
    public static final Block CONSTANTAN_BLOCK = null;

    @ObjectHolder(ID_RUBY_BLOCK)
    public static final Block RUBY_BLOCK = null;

    @ObjectHolder(ID_SAPPHIRE_BLOCK)
    public static final Block SAPPHIRE_BLOCK = null;
    // endregion
}
