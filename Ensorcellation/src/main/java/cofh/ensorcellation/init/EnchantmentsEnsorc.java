package cofh.ensorcellation.init;

import cofh.core.enchantment.HoldingEnchantment;
import cofh.ensorcellation.enchantment.*;
import cofh.ensorcellation.enchantment.override.*;

import static cofh.ensorcellation.Ensorcellation.ENCHANTMENTS;
import static cofh.ensorcellation.enchantment.override.ProtectionEnchantmentImp.Type.*;
import static cofh.lib.util.constants.Constants.ARMOR_SLOTS;
import static cofh.lib.util.references.EnsorcellationReferences.*;
import static net.minecraft.enchantment.Enchantment.Rarity.*;

public class EnchantmentsEnsorc {

    private EnchantmentsEnsorc() {

    }

    public static void register() {

        registerOverrides();

        // ARMOR
        ENCHANTMENTS.registerSpec(ID_PROTECTION_MAGIC, () -> new ProtectionEnchantmentImp(UNCOMMON, MAGIC, ARMOR_SLOTS));

        // HEAD ARMOR
        ENCHANTMENTS.registerSpec(ID_AIR_AFFINITY, AirAffinityEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_GOURMAND, GourmandEnchantment::new);

        // CHEST ARMOR
        ENCHANTMENTS.registerSpec(ID_DISPLACEMENT, DisplacementEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_FIRE_REBUKE, FireRebukeEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_FROST_REBUKE, FrostRebukeEnchantment::new);

        // WEAPONS
        ENCHANTMENTS.registerSpec(ID_DAMAGE_ENDER, DamageEnderEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DAMAGE_ILLAGER, DamageIllagerEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DAMAGE_VILLAGER, DamageVillagerEnchantment::new);

        ENCHANTMENTS.registerSpec(ID_CAVALIER, CavalierEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_FROST_ASPECT, FrostAspectEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_LEECH, LeechEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_MAGIC_EDGE, MagicEdgeEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_VORPAL, VorpalEnchantment::new);

        // TOOLS
        ENCHANTMENTS.registerSpec(ID_EXCAVATING, ExcavatingEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_EXP_BOOST, ExpBoostEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_REACH, ReachEnchantment::new);
        // ENCHANTMENTS.registerSpec(ID_PROSPECTOR, ProspectorEnchantment::new);
        // ENCHANTMENTS.registerSpec(ID_SMASHING, SmashingEnchantment::new);
        // ENCHANTMENTS.registerSpec(ID_SMELTING, SmeltingEnchantment::new);

        // BOWS
        ENCHANTMENTS.registerSpec(ID_HUNTER, HunterEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_QUICK_DRAW, QuickdrawEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_TRUESHOT, TrueshotEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_VOLLEY, VolleyEnchantment::new);

        // FISHING RODS
        ENCHANTMENTS.registerSpec(ID_ANGLER, AnglerEnchantment::new);

        // HOES
        ENCHANTMENTS.registerSpec(ID_FURROWING, FurrowingEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_TILLING, TillingEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_WEEDING, WeedingEnchantment::new);

        // SHIELDS
        ENCHANTMENTS.registerSpec(ID_BULWARK, BulwarkEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_PHALANX, PhalanxEnchantment::new);

        // MISC
        ENCHANTMENTS.registerSpec(ID_HOLDING, HoldingEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_SOULBOUND, SoulboundEnchantment::new);

        // CURSES
        ENCHANTMENTS.registerSpec(ID_CURSE_FOOL, CurseFoolEnchant::new);
        ENCHANTMENTS.registerSpec(ID_CURSE_MERCY, CurseMercyEnchantment::new);
    }

    private static void registerOverrides() {

        ENCHANTMENTS.registerSpec(ID_PROTECTION, () -> new ProtectionEnchantmentImp(COMMON, ALL, ARMOR_SLOTS));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_BLAST, () -> new ProtectionEnchantmentImp(RARE, EXPLOSION, ARMOR_SLOTS));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_FALL, () -> new ProtectionEnchantmentImp(UNCOMMON, FALL, ARMOR_SLOTS));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_FIRE, () -> new ProtectionEnchantmentImp(UNCOMMON, FIRE, ARMOR_SLOTS));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_PROJECTILE, () -> new ProtectionEnchantmentImp(UNCOMMON, PROJECTILE, ARMOR_SLOTS));

        ENCHANTMENTS.registerSpec(ID_FIRE_ASPECT, FireAspectEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_FROST_WALKER, FrostWalkerEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_KNOCKBACK, KnockbackEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_LOOTING, LootingEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_THORNS, ThornsEnchantmentImp::new);

        ENCHANTMENTS.registerSpec(ID_MENDING, MendingEnchantmentAlt::new);
    }

}
