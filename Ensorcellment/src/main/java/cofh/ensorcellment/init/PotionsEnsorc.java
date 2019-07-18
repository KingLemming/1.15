package cofh.ensorcellment.init;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;

public class PotionsEnsorc {

    private PotionsEnsorc() {

    }

    // region REGISTRATION
    public static void registerPotions(RegistryEvent.Register<Potion> event) {

    }

    private static void registerPotion(IForgeRegistry<Potion> registry, EnchantmentCoFH enchantment) {

        COMMON_CONFIG.push("Potion");

        COMMON_CONFIG.pop();
    }
    // endregion
}
