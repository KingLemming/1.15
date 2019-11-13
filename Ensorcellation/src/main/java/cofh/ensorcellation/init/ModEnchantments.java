package cofh.ensorcellation.init;

import cofh.ensorcellation.enchantment.*;
import cofh.ensorcellation.enchantment.override.*;

import static cofh.ensorcellation.Ensorcellation.ENCHANTMENTS;
import static cofh.ensorcellation.enchantment.override.ProtectionEnchantmentImp.Type.*;
import static cofh.lib.util.constants.Constants.ARMOR_SLOTS;
import static cofh.lib.util.references.EnsorcellationReferences.*;
import static net.minecraft.enchantment.Enchantment.Rarity.*;

public class ModEnchantments {

    private ModEnchantments() {

    }

    public static void register() {

        registerOverrides();

        // ARMOR
        ENCHANTMENTS.register(ID_PROTECTION_MAGIC, () -> new ProtectionEnchantmentImp(UNCOMMON, MAGIC, ARMOR_SLOTS));

        ENCHANTMENTS.register(ID_DISPLACEMENT, DisplacementEnchantment::new);
        ENCHANTMENTS.register(ID_FIRE_REBUKE, FireRebukeEnchantment::new);
        ENCHANTMENTS.register(ID_FROST_REBUKE, FrostRebukeEnchantment::new);

        // HELMET
        ENCHANTMENTS.register(ID_AIR_AFFINITY, AirAffinityEnchantment::new);
        ENCHANTMENTS.register(ID_GOURMAND, GourmandEnchantment::new);

        // WEAPONS
        ENCHANTMENTS.register(ID_DAMAGE_ENDER, DamageEnderEnchantment::new);
        ENCHANTMENTS.register(ID_DAMAGE_ILLAGER, DamageIllagerEnchantment::new);
        ENCHANTMENTS.register(ID_DAMAGE_VILLAGER, DamageVillagerEnchantment::new);

        ENCHANTMENTS.register(ID_CAVALIER, CavalierEnchantment::new);
        ENCHANTMENTS.register(ID_FROST_ASPECT, FrostAspectEnchantment::new);
        ENCHANTMENTS.register(ID_LEECH, LeechEnchantment::new);
        ENCHANTMENTS.register(ID_MAGIC_EDGE, MagicEdgeEnchantment::new);
        ENCHANTMENTS.register(ID_VORPAL, VorpalEnchantment::new);

        // TOOLS
        ENCHANTMENTS.register(ID_EXCAVATING, ExcavatingEnchantment::new);
        ENCHANTMENTS.register(ID_EXP_BOOST, ExpBoostEnchantment::new);
        ENCHANTMENTS.register(ID_REACH, ReachEnchantment::new);
        // ENCHANTMENTS.register(ID_PROSPECTOR, ProspectorEnchantment::new);
        // ENCHANTMENTS.register(ID_SMASHING, SmashingEnchantment::new);
        // ENCHANTMENTS.register(ID_SMELTING, SmeltingEnchantment::new);

        // BOWS
        ENCHANTMENTS.register(ID_HUNTER, HunterEnchantment::new);
        ENCHANTMENTS.register(ID_QUICK_DRAW, QuickdrawEnchantment::new);
        ENCHANTMENTS.register(ID_TRUESHOT, TrueshotEnchantment::new);
        ENCHANTMENTS.register(ID_VOLLEY, VolleyEnchantment::new);

        // FISHING RODS
        ENCHANTMENTS.register(ID_ANGLER, AnglerEnchantment::new);

        // HOES
        ENCHANTMENTS.register(ID_FURROWING, FurrowingEnchantment::new);
        ENCHANTMENTS.register(ID_TILLING, TillingEnchantment::new);
        ENCHANTMENTS.register(ID_WEEDING, WeedingEnchantment::new);

        // SHIELDS
        ENCHANTMENTS.register(ID_BULWARK, BulwarkEnchantment::new);
        ENCHANTMENTS.register(ID_PHALANX, PhalanxEnchantment::new);

        // MISC
        // TODO: Revisit
        // ENCHANTMENTS.register(ID_HOLDING, HoldingEnchantment::new);
        ENCHANTMENTS.register(ID_SOULBOUND, SoulboundEnchantment::new);

        // CURSES
        ENCHANTMENTS.register(ID_CURSE_FOOL, CurseFoolEnchant::new);
        ENCHANTMENTS.register(ID_CURSE_MERCY, CurseMercyEnchantment::new);
    }

    private static void registerOverrides() {

        ENCHANTMENTS.register(ID_PROTECTION, () -> new ProtectionEnchantmentImp(COMMON, ALL, ARMOR_SLOTS));
        ENCHANTMENTS.register(ID_PROTECTION_BLAST, () -> new ProtectionEnchantmentImp(RARE, EXPLOSION, ARMOR_SLOTS));
        ENCHANTMENTS.register(ID_PROTECTION_FALL, () -> new ProtectionEnchantmentImp(UNCOMMON, FALL, ARMOR_SLOTS));
        ENCHANTMENTS.register(ID_PROTECTION_FIRE, () -> new ProtectionEnchantmentImp(UNCOMMON, FIRE, ARMOR_SLOTS));
        ENCHANTMENTS.register(ID_PROTECTION_PROJECTILE, () -> new ProtectionEnchantmentImp(UNCOMMON, PROJECTILE, ARMOR_SLOTS));

        ENCHANTMENTS.register(ID_FIRE_ASPECT, FireAspectEnchantmentImp::new);
        ENCHANTMENTS.register(ID_FROST_WALKER, FrostWalkerEnchantmentImp::new);
        ENCHANTMENTS.register(ID_KNOCKBACK, KnockbackEnchantmentImp::new);
        ENCHANTMENTS.register(ID_LOOTING, LootingEnchantmentImp::new);
        ENCHANTMENTS.register(ID_THORNS, ThornsEnchantmentImp::new);

        ENCHANTMENTS.register(ID_MENDING, MendingEnchantmentAlt::new);
    }

}
