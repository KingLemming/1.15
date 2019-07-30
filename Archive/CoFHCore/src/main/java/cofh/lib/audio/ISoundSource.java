package cofh.lib.audio;

import net.minecraft.client.audio.ISound;

public interface ISoundSource {

    ISound getSound();

    boolean shouldPlaySound();

}
