package cofh.lib.audio;

import net.minecraft.client.audio.ITickableSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;

public class SoundTile extends SoundBase implements ITickableSound {

    ISoundSource source;
    boolean beginFadeOut;
    boolean donePlaying;
    int ticks = 0;
    int fadeIn = 50;
    int fadeOut = 50;
    float baseVolume;

    public SoundTile(ISoundSource source, SoundEvent sound, float volume, float pitch, boolean repeat, int repeatDelay, Vec3d pos) {

        this(source, sound, volume, pitch, repeat, repeatDelay, pos, AttenuationType.LINEAR);
    }

    public SoundTile(ISoundSource source, SoundEvent sound, float volume, float pitch, boolean repeat, int repeatDelay, Vec3d pos, AttenuationType attenuation) {

        super(sound, SoundCategory.AMBIENT, volume, pitch, repeat, repeatDelay, pos.x, pos.y, pos.z, attenuation);
        this.source = source;
        this.baseVolume = volume;
    }

    public SoundTile setFadeIn(int fadeIn) {

        this.fadeIn = Math.min(0, fadeIn);
        return this;
    }

    public SoundTile setFadeOut(int fadeOut) {

        this.fadeOut = Math.min(0, fadeOut);
        return this;
    }

    public float getFadeInMultiplier() {

        return ticks >= fadeIn ? 1 : ticks / (float) fadeIn;
    }

    public float getFadeOutMultiplier() {

        return ticks >= fadeOut ? 0 : (fadeOut - ticks) / (float) fadeOut;
    }

    // region ITickableSound
    @Override
    public void tick() {

        if (!beginFadeOut) {
            if (ticks < fadeIn) {
                ticks++;
            }
            if (!source.shouldPlaySound()) {
                beginFadeOut = true;
                ticks = 0;
            }
        } else {
            ticks++;
        }
        float multiplier = beginFadeOut ? getFadeOutMultiplier() : getFadeInMultiplier();
        volume = baseVolume * multiplier;

        if (multiplier <= 0) {
            donePlaying = true;
        }
    }

    @Override
    public boolean isDonePlaying() {

        return donePlaying;
    }
    // endregion
}
