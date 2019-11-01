package cofh.core.potion;

import cofh.lib.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EffectLove extends EffectCoFH {

    public EffectLove(EffectType typeIn, int liquidColorIn) {

        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isInstant() {

        return true;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {

        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {

        World world = entityLivingBaseIn.world;
        if (Utils.isServerWorld(world)) {
            if (entityLivingBaseIn instanceof AnimalEntity) {
                setLoveFlag(null, (AnimalEntity) entityLivingBaseIn);
            }
        }
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {

        if (entityLivingBaseIn instanceof AnimalEntity) {
            setLoveFlag(indirectSource, (AnimalEntity) entityLivingBaseIn);
        }
    }

    // region HELPERS
    private void setLoveFlag(Entity indirectSource, AnimalEntity animal) {

        PlayerEntity player = indirectSource instanceof PlayerEntity ? (PlayerEntity) indirectSource : null;
        if (animal.getGrowingAge() == 0 && !animal.isInLove()) {
            animal.setInLove(player);
            for (int i = 0; i < 4; ++i) {
                Utils.spawnParticles(animal.world, ParticleTypes.HEART, animal.posX + animal.world.rand.nextDouble(), animal.posY + 1.0D + animal.world.rand.nextDouble(), animal.posZ + animal.world.rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }
    // endregion
}
