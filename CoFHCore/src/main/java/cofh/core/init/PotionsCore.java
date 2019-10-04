package cofh.core.init;

import cofh.core.potion.EffectCoFH;
import net.minecraft.potion.EffectType;

import static cofh.core.CoFHCore.POTIONS;
import static cofh.lib.util.modhelpers.CoreHelper.ID_ANCHORING;

public class PotionsCore {

    private PotionsCore() {

    }

    public static void register() {

        POTIONS.registerSpec(ID_ANCHORING, () -> new EffectCoFH(EffectType.NEUTRAL, 0x571B25));
    }

}
