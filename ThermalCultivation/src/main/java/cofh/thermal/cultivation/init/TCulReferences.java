package cofh.thermal.cultivation.init;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TCulReferences {

    private TCulReferences() {

    }

    // region IDS
    public static final String ID_BARLEY = ID_THERMAL + ":barley";
    public static final String ID_ONION = ID_THERMAL + ":onion";
    public static final String ID_RICE = ID_THERMAL + ":rice";
    public static final String ID_SADIROOT = ID_THERMAL + ":sadiroot";
    public static final String ID_SPINACH = ID_THERMAL + ":spinach";

    public static final String ID_BELL_PEPPER = ID_THERMAL + ":bell_pepper";
    public static final String ID_GREEN_BEAN = ID_THERMAL + ":green_bean";
    public static final String ID_PEANUT = ID_THERMAL + ":peanut";
    public static final String ID_STRAWBERRY = ID_THERMAL + ":strawberry";
    public static final String ID_TOMATO = ID_THERMAL + ":tomato";

    public static final String ID_COFFEE = ID_THERMAL + ":coffee";
    public static final String ID_TEA = ID_THERMAL + ":tea";


    // endregion

    // region BLOCKS
    @ObjectHolder(ID_BARLEY)
    public static final Block BARLEY_PLANT = null;

    @ObjectHolder(ID_ONION)
    public static final Block ONION_PLANT = null;

    @ObjectHolder(ID_RICE)
    public static final Block RICE_PLANT = null;

    @ObjectHolder(ID_SADIROOT)
    public static final Block SADIROOT_PLANT = null;

    @ObjectHolder(ID_SPINACH)
    public static final Block SPINACH_PLANT = null;

    @ObjectHolder(ID_BELL_PEPPER)
    public static final Block BELL_PEPPER_PLANT = null;

    @ObjectHolder(ID_GREEN_BEAN)
    public static final Block GREEN_BEAN_PLANT = null;

    @ObjectHolder(ID_PEANUT)
    public static final Block PEANUT_PLANT = null;

    @ObjectHolder(ID_STRAWBERRY)
    public static final Block STRAWBERRY_PLANT = null;

    @ObjectHolder(ID_TOMATO)
    public static final Block TOMATO_PLANT = null;

    @ObjectHolder(ID_COFFEE)
    public static final Block COFFEE_PLANT = null;

    @ObjectHolder(ID_TEA)
    public static final Block TEA_PLANT = null;
    // endregion
}
