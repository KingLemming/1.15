package cofh.cofh_potions.init;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;

import static cofh.cofh_potions.CoFHPotions.POTIONS;
import static cofh.lib.util.references.CoreReferences.*;

public class ModPotions {

    private ModPotions() {

    }

    public static void register() {

        POTIONS.register("explosion_resistance", () -> new Potion(new EffectInstance(EXPLOSION_RESISTANCE, 3600)));
        POTIONS.register("explosion_resistance_long", () -> new Potion("explosion_resistance", new EffectInstance(EXPLOSION_RESISTANCE, 9600)));

        POTIONS.register("lightning_resistance", () -> new Potion(new EffectInstance(LIGHTNING_RESISTANCE, 3600)));
        POTIONS.register("lightning_resistance_long", () -> new Potion("lightning_resistance", new EffectInstance(LIGHTNING_RESISTANCE, 9600)));

        POTIONS.register("magic_resistance", () -> new Potion(new EffectInstance(MAGIC_RESISTANCE, 3600)));
        POTIONS.register("magic_resistance_long", () -> new Potion("magic_resistance", new EffectInstance(MAGIC_RESISTANCE, 9600)));

        POTIONS.register("clarity", () -> new Potion(new EffectInstance(CLARITY, 3600)));
        POTIONS.register("clarity_long", () -> new Potion("clarity", new EffectInstance(CLARITY, 9600)));
        POTIONS.register("clarity_strong", () -> new Potion("clarity", new EffectInstance(CLARITY, 1800, 1)));

        POTIONS.register("enderference", () -> new Potion(new EffectInstance(ENDERFERENCE, 3600)));
        POTIONS.register("enderference_long", () -> new Potion("enderference", new EffectInstance(ENDERFERENCE, 9600)));

        POTIONS.register("love", () -> new Potion(new EffectInstance(LOVE, 1)));

        POTIONS.register("panacea", () -> new Potion(new EffectInstance(PANACEA, 100)));
    }

}
