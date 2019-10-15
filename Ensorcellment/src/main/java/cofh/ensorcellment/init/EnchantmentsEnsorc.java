package cofh.ensorcellment.init;

import cofh.ensorcellment.enchantment.*;
import cofh.ensorcellment.enchantment.override.*;

import static cofh.ensorcellment.Ensorcellment.ENCHANTMENTS;
import static cofh.ensorcellment.enchantment.override.ProtectionEnchantmentImp.Type.*;
import static cofh.lib.util.constants.Constants.ARMOR_SLOTS;
import static cofh.lib.util.references.EnsorcellmentReferences.*;
import static net.minecraft.enchantment.Enchantment.Rarity.*;

public class EnchantmentsEnsorc {

    private EnchantmentsEnsorc() {

    }

    public static void register() {

        ENCHANTMENTS.registerSpec(ID_PROTECTION, () -> new ProtectionEnchantmentImp(COMMON, ALL, ARMOR_SLOTS));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_BLAST, () -> new ProtectionEnchantmentImp(RARE, EXPLOSION, ARMOR_SLOTS));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_FALL, () -> new ProtectionEnchantmentImp(UNCOMMON, FALL, ARMOR_SLOTS));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_FIRE, () -> new ProtectionEnchantmentImp(UNCOMMON, FIRE, ARMOR_SLOTS));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_PROJECTILE, () -> new ProtectionEnchantmentImp(UNCOMMON, PROJECTILE, ARMOR_SLOTS));

        ENCHANTMENTS.registerSpec(ID_PROTECTION_MAGIC, () -> new ProtectionEnchantmentImp(UNCOMMON, MAGIC, ARMOR_SLOTS));

        ENCHANTMENTS.registerSpec(ID_FIRE_ASPECT, FireAspectEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_FROST_WALKER, FrostWalkerEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_KNOCKBACK, KnockbackEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_LOOTING, LootingEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_THORNS, ThornsEnchantmentImp::new);

        ENCHANTMENTS.registerSpec(ID_MENDING, MendingEnchantmentAlt::new);

        ENCHANTMENTS.registerSpec(ID_AIR_AFFINITY, AirAffinityEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_ANGLER, AnglerEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_BULWARK, BulwarkEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_CAVALIER, CavalierEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DAMAGE_ENDER, DamageEnderEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DAMAGE_ILLAGER, DamageIllagerEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DAMAGE_VILLAGER, DamageVillagerEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DISPLACEMENT, DisplacementEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_EXCAVATING, ExcavatingEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_EXP_BOOST, ExpBoostEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_FIRE_REBUKE, FireRebukeEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_FROST_ASPECT, FrostAspectEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_FROST_REBUKE, FrostRebukeEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_GOURMAND, GourmandEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_HOLDING, HoldingEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_HUNTER, HunterEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_LEECH, LeechEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_MAGIC_EDGE, MagicEdgeEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_PHALANX, PhalanxEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_QUICKDRAW, QuickdrawEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_REACH, ReachEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_SOULBOUND, SoulboundEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_TILLING, TillingEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_TRUESHOT, TrueshotEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_VOLLEY, VolleyEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_VORPAL, VorpalEnchantment::new);

        ENCHANTMENTS.registerSpec(ID_CURSE_MERCY, CurseMercyEnchantment::new);

        // ENCHANTMENTS.registerSpec(ID_PROSPECTOR, ProspectorEnchantment::new);
        // ENCHANTMENTS.registerSpec(ID_SMASHING, SmashingEnchantment::new);
        // ENCHANTMENTS.registerSpec(ID_SMELTING, SmeltingEnchantment::new);
    }

}
