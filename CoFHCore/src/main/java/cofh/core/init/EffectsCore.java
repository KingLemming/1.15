package cofh.core.init;

import cofh.core.potion.EffectCoFH;
import cofh.core.potion.EffectLove;
import cofh.core.potion.EffectPanacea;
import net.minecraft.potion.EffectType;

import static cofh.core.CoFHCore.EFFECTS;
import static cofh.lib.util.references.CoreReferences.*;

public class EffectsCore {

    private EffectsCore() {

    }

    public static void register() {

        EFFECTS.register(ID_EFFECT_EXPLOSION_RESISTANCE, () -> new EffectCoFH(EffectType.BENEFICIAL, 0x0F0A18));
        EFFECTS.register(ID_EFFECT_LIGHTNING_RESISTANCE, () -> new EffectCoFH(EffectType.BENEFICIAL, 0xA0A0A0));
        EFFECTS.register(ID_EFFECT_MAGIC_RESISTANCE, () -> new EffectCoFH(EffectType.BENEFICIAL, 0x580058));

        EFFECTS.register(ID_EFFECT_ENDERFERENCE, () -> new EffectCoFH(EffectType.NEUTRAL, 0x1B574D));
        EFFECTS.register(ID_EFFECT_CLARITY, () -> new EffectCoFH(EffectType.BENEFICIAL, 0x70FF00));
        EFFECTS.register(ID_EFFECT_LOVE, () -> new EffectLove(EffectType.BENEFICIAL, 0xFF7099));
        EFFECTS.register(ID_EFFECT_PANACEA, () -> new EffectPanacea(EffectType.BENEFICIAL, 0x30B1FF));
    }

}
