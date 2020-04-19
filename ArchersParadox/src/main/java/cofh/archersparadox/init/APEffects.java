package cofh.archersparadox.init;

import cofh.archersparadox.potion.ChallengeEffect;
import cofh.core.potion.NeutralEffect;
import net.minecraft.potion.EffectType;

import static cofh.archersparadox.ArchersParadox.EFFECTS;
import static cofh.archersparadox.init.APReferences.*;

public class APEffects {

    private APEffects() {

    }

    public static void register() {

        EFFECTS.register(ID_EFFECT_CHALLENGE_COMPLETE, () -> new NeutralEffect(EffectType.NEUTRAL, 0x888888));
        EFFECTS.register(ID_EFFECT_CHALLENGE_MISS, () -> new NeutralEffect(EffectType.NEUTRAL, 0x888888));
        EFFECTS.register(ID_EFFECT_CHALLENGE_STREAK, () -> new ChallengeEffect(EffectType.NEUTRAL, 0x888888));

        EFFECTS.register(ID_EFFECT_TRAINING_MISS, () -> new NeutralEffect(EffectType.NEUTRAL, 0x888888));
        EFFECTS.register(ID_EFFECT_TRAINING_STREAK, () -> new NeutralEffect(EffectType.NEUTRAL, 0x888888));
    }

}
