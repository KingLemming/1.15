package cofh.archersparadox.potion;

import cofh.core.potion.EffectCoFH;
import cofh.lib.util.Utils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

import static cofh.archersparadox.init.APReferences.CHALLENGE_COMPLETE;

public class ChallengeEffect extends EffectCoFH {

    public static int maxExperience = 500;

    public ChallengeEffect(EffectType typeIn, int liquidColorIn) {

        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {

        int level = amplifier + 1;

        if (entityLivingBaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
            if (Utils.isServerWorld(entityLivingBaseIn.world)) {
                player.giveExperiencePoints(Math.min(level * level + level, maxExperience));
            }
            player.addPotionEffect(new EffectInstance(CHALLENGE_COMPLETE, 960 * level, 0, false, false));
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {

        return duration == 1;
    }

}
