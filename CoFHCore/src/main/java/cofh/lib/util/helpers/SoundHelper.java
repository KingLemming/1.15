package cofh.lib.util.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

/**
 * Contains various helper functions to assist with Sound manipulation.
 *
 * @author King Lemming
 */
public class SoundHelper {

    private SoundHelper() {

    }

    private static SoundHandler manager() {

        return Minecraft.getInstance().getSoundHandler();
    }

    /**
     * This allows you to have some tricky functionality with Tile Entities. Just be sure you aren't dumb.
     */
    public static void playSound(Object sound) {

        if (sound instanceof ISound) {
            manager().play((ISound) sound);
        }
    }

    public static void playSound(ISound sound) {

        manager().play(sound);
    }

    public static void playSound(String soundName, SoundCategory category, float x, float y, float z, float volume, float pitch) {

        // TODO: Fix
        // manager().play(new SoundBase(soundName, category, volume, pitch, x, y, z));
    }

    public static void playClickSound(float pitch) {

        playClickSound(pitch, 0.3F);
    }

    public static void playClickSound(float pitch, float volume) {

        playSimpleSound(SoundEvents.UI_BUTTON_CLICK, pitch, volume);
    }

    public static void playSimpleSound(SoundEvent sound, float volume, float pitch) {

        manager().play(SimpleSound.master(sound, pitch, volume));
    }

}
