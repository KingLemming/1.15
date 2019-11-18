package cofh.core.init;

import cofh.core.potion.ChilledEffect;
import cofh.core.potion.EffectCoFH;
import cofh.core.potion.LoveEffect;
import cofh.core.potion.PanaceaEffect;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.EffectType;

import static cofh.core.CoFHCore.EFFECTS;
import static cofh.lib.util.constants.Constants.UUID_EFFECT_CHILLED_ATTACK_DAMAGE;
import static cofh.lib.util.constants.Constants.UUID_EFFECT_CHILLED_MOVEMENT_SPEED;
import static cofh.lib.util.references.CoreReferences.*;

public class CoreEffects {

    private CoreEffects() {

    }

    public static void register() {

        EFFECTS.register(ID_EFFECT_EXPLOSION_RESISTANCE, () -> new EffectCoFH(EffectType.BENEFICIAL, 0x0F0A18));
        EFFECTS.register(ID_EFFECT_LIGHTNING_RESISTANCE, () -> new EffectCoFH(EffectType.BENEFICIAL, 0xA0A0A0));
        EFFECTS.register(ID_EFFECT_MAGIC_RESISTANCE, () -> new EffectCoFH(EffectType.BENEFICIAL, 0x580058));

        EFFECTS.register(ID_EFFECT_CLARITY, () -> new EffectCoFH(EffectType.BENEFICIAL, 0x70FF00));
        EFFECTS.register(ID_EFFECT_CHILLED, () -> new ChilledEffect(EffectType.HARMFUL, 0x86AEFD).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, UUID_EFFECT_CHILLED_MOVEMENT_SPEED.toString(), -0.20F, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, UUID_EFFECT_CHILLED_ATTACK_DAMAGE.toString(), -3.0D, AttributeModifier.Operation.ADDITION));
        EFFECTS.register(ID_EFFECT_ENDERFERENCE, () -> new EffectCoFH(EffectType.NEUTRAL, 0x1B574D));
        EFFECTS.register(ID_EFFECT_LOVE, () -> new LoveEffect(EffectType.BENEFICIAL, 0xFF7099));
        EFFECTS.register(ID_EFFECT_PANACEA, () -> new PanaceaEffect(EffectType.BENEFICIAL, 0x769CD7));
    }

}
