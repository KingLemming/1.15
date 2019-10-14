package cofh.core.init;

import cofh.core.potion.EffectCoFH;
import net.minecraft.potion.EffectType;

import static cofh.core.CoFHCore.POTIONS;
import static cofh.lib.util.modhelpers.CoreHelper.ID_ENDERFERENCE;
import static cofh.lib.util.modhelpers.CoreHelper.ID_EUREKA;

public class PotionsCore {

    private PotionsCore() {

    }

    public static void register() {

        POTIONS.registerSpec(ID_ENDERFERENCE, () -> new EffectCoFH(EffectType.NEUTRAL, 0x571B25));
        POTIONS.registerSpec(ID_EUREKA, () -> new EffectCoFH(EffectType.BENEFICIAL, 0x80BF00));
    }

}
