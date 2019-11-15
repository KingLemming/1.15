package cofh.cofh_archery.init;

import cofh.core.potion.TrainingEffect;
import net.minecraft.potion.EffectType;

import static cofh.cofh_archery.CoFHArchery.EFFECTS;
import static cofh.cofh_archery.init.ModReferences.*;

public class ModEffects {

    private ModEffects() {

    }

    public static void register() {

        EFFECTS.register(ID_EFFECT_TRAINING_MISS, () -> new TrainingEffect(EffectType.NEUTRAL, 0x888888));
        EFFECTS.register(ID_EFFECT_TRAINING_STREAK, () -> new TrainingEffect(EffectType.NEUTRAL, 0x888888));
        EFFECTS.register(ID_EFFECT_TRAINING_TARGET, () -> new TrainingEffect(EffectType.NEUTRAL, 0x888888));
    }

}
