package cofh.thermal.core.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.thermal.core.ThermalCore.SOUND_EVENTS;
import static cofh.thermal.core.init.TCoreReferences.*;

public class TCoreSounds {

    private TCoreSounds() {

    }

    public static void register() {

        SOUND_EVENTS.register(ID_SOUND_BASALZ_AMBIENT, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BASALZ_AMBIENT)));
        SOUND_EVENTS.register(ID_SOUND_BASALZ_ROAM, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BASALZ_ROAM)));
        SOUND_EVENTS.register(ID_SOUND_BASALZ_DEATH, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BASALZ_DEATH)));
        SOUND_EVENTS.register(ID_SOUND_BASALZ_HURT, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BASALZ_HURT)));
        SOUND_EVENTS.register(ID_SOUND_BASALZ_SHOOT, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BASALZ_SHOOT)));

        SOUND_EVENTS.register(ID_SOUND_BLITZ_AMBIENT, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLITZ_AMBIENT)));
        SOUND_EVENTS.register(ID_SOUND_BLITZ_ROAM, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLITZ_ROAM)));
        SOUND_EVENTS.register(ID_SOUND_BLITZ_DEATH, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLITZ_DEATH)));
        SOUND_EVENTS.register(ID_SOUND_BLITZ_HURT, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLITZ_HURT)));
        SOUND_EVENTS.register(ID_SOUND_BLITZ_SHOOT, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLITZ_SHOOT)));

        SOUND_EVENTS.register(ID_SOUND_BLIZZ_AMBIENT, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLIZZ_AMBIENT)));
        SOUND_EVENTS.register(ID_SOUND_BLIZZ_ROAM, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLIZZ_ROAM)));
        SOUND_EVENTS.register(ID_SOUND_BLIZZ_DEATH, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLIZZ_DEATH)));
        SOUND_EVENTS.register(ID_SOUND_BLIZZ_HURT, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLIZZ_HURT)));
        SOUND_EVENTS.register(ID_SOUND_BLIZZ_SHOOT, () -> new SoundEvent(new ResourceLocation(ID_SOUND_BLIZZ_SHOOT)));

        SOUND_EVENTS.register(ID_SOUND_MAGNET, () -> new SoundEvent(new ResourceLocation(ID_SOUND_MAGNET)));
        SOUND_EVENTS.register(ID_SOUND_TINKER, () -> new SoundEvent(new ResourceLocation(ID_SOUND_TINKER)));
    }

    // region REFERENCES
    @ObjectHolder(ID_SOUND_BASALZ_AMBIENT)
    public static final SoundEvent SOUND_BASALZ_AMBIENT = null;
    @ObjectHolder(ID_SOUND_BASALZ_DEATH)
    public static final SoundEvent SOUND_BASALZ_DEATH = null;
    @ObjectHolder(ID_SOUND_BASALZ_HURT)
    public static final SoundEvent SOUND_BASALZ_HURT = null;
    @ObjectHolder(ID_SOUND_BASALZ_ROAM)
    public static final SoundEvent SOUND_BASALZ_ROAM = null;
    @ObjectHolder(ID_SOUND_BASALZ_SHOOT)
    public static final SoundEvent SOUND_BASALZ_SHOOT = null;

    @ObjectHolder(ID_SOUND_BLITZ_AMBIENT)
    public static final SoundEvent SOUND_BLITZ_AMBIENT = null;
    @ObjectHolder(ID_SOUND_BLITZ_DEATH)
    public static final SoundEvent SOUND_BLITZ_DEATH = null;
    @ObjectHolder(ID_SOUND_BLITZ_HURT)
    public static final SoundEvent SOUND_BLITZ_HURT = null;
    @ObjectHolder(ID_SOUND_BLITZ_ROAM)
    public static final SoundEvent SOUND_BLITZ_ROAM = null;
    @ObjectHolder(ID_SOUND_BLITZ_SHOOT)
    public static final SoundEvent SOUND_BLITZ_SHOOT = null;

    @ObjectHolder(ID_SOUND_BLIZZ_AMBIENT)
    public static final SoundEvent SOUND_BLIZZ_AMBIENT = null;
    @ObjectHolder(ID_SOUND_BLIZZ_DEATH)
    public static final SoundEvent SOUND_BLIZZ_DEATH = null;
    @ObjectHolder(ID_SOUND_BLIZZ_HURT)
    public static final SoundEvent SOUND_BLIZZ_HURT = null;
    @ObjectHolder(ID_SOUND_BLIZZ_ROAM)
    public static final SoundEvent SOUND_BLIZZ_ROAM = null;
    @ObjectHolder(ID_SOUND_BLIZZ_SHOOT)
    public static final SoundEvent SOUND_BLIZZ_SHOOT = null;

    @ObjectHolder(ID_SOUND_MAGNET)
    public static final SoundEvent SOUND_MAGNET = null;

    @ObjectHolder(ID_SOUND_TINKER)
    public static final SoundEvent SOUND_TINKER = null;
    // endregion
}
