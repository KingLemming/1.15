package cofh.lib.audio;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.LocatableSound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

/**
 * This is essentially a PositionedSound class with a ton more constructors. Because Mojang didn't provide enough.
 *
 * @author skyboy, brandon3055
 */
public class SoundBase extends LocatableSound {

    public SoundBase(SoundBase other) {

        this(other.getSoundLocation(), other.category, other.volume, other.pitch, other.repeat, other.repeatDelay, other.x, other.y, other.z, other.attenuationType);
    }

    // region SoundEvent
    public SoundBase(SoundEvent sound, SoundCategory category) {

        this(sound.getName(), category, 1.0F, 1.0F, false, 0, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(SoundEvent sound, SoundCategory category, float volume) {

        this(sound.getName(), category, volume, 1.0F, false, 0, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(SoundEvent sound, SoundCategory category, float volume, float pitch) {

        this(sound.getName(), category, volume, pitch, false, 0, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(SoundEvent sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay) {

        this(sound.getName(), category, volume, pitch, repeat, repeatDelay, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(SoundEvent sound, SoundCategory category, float volume, float pitch, double x, double y, double z) {

        this(sound.getName(), category, volume, pitch, false, 0, x, y, z, ISound.AttenuationType.LINEAR);
    }

    public SoundBase(SoundEvent sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z) {

        this(sound.getName(), category, volume, pitch, repeat, repeatDelay, x, y, z, ISound.AttenuationType.LINEAR);
    }

    public SoundBase(SoundEvent sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z, ISound.AttenuationType attenuation) {

        this(sound.getName(), category, volume, pitch, repeat, repeatDelay, x, y, z, attenuation);
    }
    // endregion

    // region String
    public SoundBase(String sound, SoundCategory category) {

        this(new ResourceLocation(sound), category, 1.0F, 1.0F, false, 0, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(String sound, SoundCategory category, float volume) {

        this(new ResourceLocation(sound), category, volume, 1.0F, false, 0, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(String sound, SoundCategory category, float volume, float pitch) {

        this(new ResourceLocation(sound), category, volume, pitch, false, 0, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(String sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay) {

        this(new ResourceLocation(sound), category, volume, pitch, repeat, repeatDelay, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(String sound, SoundCategory category, float volume, float pitch, double x, double y, double z) {

        this(new ResourceLocation(sound), category, volume, pitch, false, 0, x, y, z, ISound.AttenuationType.LINEAR);
    }

    public SoundBase(String sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z) {

        this(new ResourceLocation(sound), category, volume, pitch, repeat, repeatDelay, x, y, z, ISound.AttenuationType.LINEAR);
    }

    public SoundBase(String sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z, ISound.AttenuationType attenuation) {

        this(new ResourceLocation(sound), category, volume, pitch, repeat, repeatDelay, x, y, z, attenuation);
    }
    // endregion

    // region ResourceLocation
    public SoundBase(ResourceLocation sound, SoundCategory category) {

        this(sound, category, 1.0F, 1.0F, false, 0, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(ResourceLocation sound, SoundCategory category, float volume) {

        this(sound, category, volume, 1.0F, false, 0, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(ResourceLocation sound, SoundCategory category, float volume, float pitch) {

        this(sound, category, volume, pitch, false, 0, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(ResourceLocation sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay) {

        this(sound, category, volume, pitch, repeat, repeatDelay, 0, 0, 0, ISound.AttenuationType.NONE);
    }

    public SoundBase(ResourceLocation sound, SoundCategory category, float volume, float pitch, double x, double y, double z) {

        this(sound, category, volume, pitch, false, 0, x, y, z, ISound.AttenuationType.LINEAR);
    }

    public SoundBase(ResourceLocation sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z) {

        this(sound, category, volume, pitch, repeat, repeatDelay, x, y, z, ISound.AttenuationType.LINEAR);
    }

    public SoundBase(ResourceLocation sound, SoundCategory category, float volume, float pitch, boolean repeat, int repeatDelay, double x, double y, double z, ISound.AttenuationType attenuation) {

        super(sound, category);
        this.volume = volume;
        this.pitch = pitch;
        this.repeat = repeat;
        this.repeatDelay = repeatDelay;
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
        this.attenuationType = attenuation;
    }
    // endregion
}
