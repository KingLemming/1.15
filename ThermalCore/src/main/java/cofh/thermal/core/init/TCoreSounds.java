package cofh.thermal.core.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import static cofh.thermal.core.ThermalCore.SOUND_EVENTS;
import static cofh.thermal.core.init.TCoreReferences.ID_SOUND_TINKER;

public class TCoreSounds {

    private TCoreSounds() {

    }

    public static void register() {

        SOUND_EVENTS.register(ID_SOUND_TINKER, () -> new SoundEvent(new ResourceLocation(ID_SOUND_TINKER)));
    }

}
