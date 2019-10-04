package cofh.lib.util.modhelpers;

import net.minecraft.potion.Effect;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

public class CoreHelper {

    private CoreHelper() {

    }

    // region IDS
    public static final String ID_ANCHORING = ID_COFH_CORE + ":anchoring";
    // endregion

    // region EFFECTS
    @ObjectHolder(ID_ANCHORING)
    public static final Effect ANCHORING = null;
    // endregion
}
