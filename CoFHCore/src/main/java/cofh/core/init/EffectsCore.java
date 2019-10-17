package cofh.core.init;

import cofh.core.potion.EffectCoFH;
import net.minecraft.potion.EffectType;

import static cofh.core.CoFHCore.POTIONS;
import static cofh.lib.util.references.CoreReferences.ID_ENDERFERENCE;
import static cofh.lib.util.references.CoreReferences.ID_EPIPHANY;

public class EffectsCore {

    private EffectsCore() {

    }

    public static void register() {

        POTIONS.register(ID_ENDERFERENCE, () -> new EffectCoFH(EffectType.NEUTRAL, 0x1B574D));
        POTIONS.register(ID_EPIPHANY, () -> new EffectCoFH(EffectType.BENEFICIAL, 0x80BF00));
    }

}
