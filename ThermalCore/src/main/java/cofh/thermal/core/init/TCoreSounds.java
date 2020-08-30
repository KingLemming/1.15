package cofh.thermal.core.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.SOUND_EVENTS;

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

    // region IDs
    public static final String ID_SOUND_BASALZ_AMBIENT = ID_THERMAL + ":mob.basalz.ambient";
    public static final String ID_SOUND_BASALZ_DEATH = ID_THERMAL + ":mob.basalz.death";
    public static final String ID_SOUND_BASALZ_HURT = ID_THERMAL + ":mob.basalz.hurt";
    public static final String ID_SOUND_BASALZ_ROAM = ID_THERMAL + ":mob.basalz.roam";
    public static final String ID_SOUND_BASALZ_SHOOT = ID_THERMAL + ":mob.basalz.shoot";

    public static final String ID_SOUND_BLITZ_AMBIENT = ID_THERMAL + ":mob.blitz.ambient";
    public static final String ID_SOUND_BLITZ_DEATH = ID_THERMAL + ":mob.blitz.death";
    public static final String ID_SOUND_BLITZ_HURT = ID_THERMAL + ":mob.blitz.hurt";
    public static final String ID_SOUND_BLITZ_ROAM = ID_THERMAL + ":mob.blitz.roam";
    public static final String ID_SOUND_BLITZ_SHOOT = ID_THERMAL + ":mob.blitz.shoot";

    public static final String ID_SOUND_BLIZZ_AMBIENT = ID_THERMAL + ":mob.blizz.ambient";
    public static final String ID_SOUND_BLIZZ_DEATH = ID_THERMAL + ":mob.blizz.death";
    public static final String ID_SOUND_BLIZZ_HURT = ID_THERMAL + ":mob.blizz.hurt";
    public static final String ID_SOUND_BLIZZ_ROAM = ID_THERMAL + ":mob.blizz.roam";
    public static final String ID_SOUND_BLIZZ_SHOOT = ID_THERMAL + ":mob.blizz.shoot";

    public static final String ID_SOUND_MAGNET = ID_THERMAL + ":item.magnet";
    public static final String ID_SOUND_TINKER = ID_THERMAL + ":misc.tinker";
    // endregion

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
