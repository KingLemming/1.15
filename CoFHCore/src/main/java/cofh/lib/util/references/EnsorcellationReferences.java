package cofh.lib.util.references;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_ENSORCELLATION;
import static cofh.lib.util.constants.Constants.ID_MINECRAFT;

public class EnsorcellationReferences {

    private EnsorcellationReferences() {

    }

    // region ENCHANTMENT IDS

    // VANILLA
    public static final String ID_PROTECTION = ID_MINECRAFT + ":protection";
    public static final String ID_PROTECTION_BLAST = ID_MINECRAFT + ":blast_protection";
    public static final String ID_PROTECTION_FALL = ID_MINECRAFT + ":feather_falling";
    public static final String ID_PROTECTION_FIRE = ID_MINECRAFT + ":fire_protection";
    public static final String ID_PROTECTION_PROJECTILE = ID_MINECRAFT + ":projectile_protection";

    public static final String ID_FIRE_ASPECT = ID_MINECRAFT + ":fire_aspect";
    public static final String ID_FROST_WALKER = ID_MINECRAFT + ":frost_walker";
    public static final String ID_KNOCKBACK = ID_MINECRAFT + ":knockback";
    public static final String ID_LOOTING = ID_MINECRAFT + ":looting";
    public static final String ID_THORNS = ID_MINECRAFT + ":thorns";

    public static final String ID_MENDING = ID_MINECRAFT + ":mending";

    // ARMOR
    public static final String ID_PROTECTION_MAGIC = ID_ENSORCELLATION + ":magic_protection";

    public static final String ID_DISPLACEMENT = ID_ENSORCELLATION + ":displacement";
    public static final String ID_FIRE_REBUKE = ID_ENSORCELLATION + ":fire_rebuke";
    public static final String ID_FROST_REBUKE = ID_ENSORCELLATION + ":frost_rebuke";

    // HELMET
    public static final String ID_AIR_AFFINITY = ID_ENSORCELLATION + ":air_affinity";
    public static final String ID_EXP_BOOST = ID_ENSORCELLATION + ":exp_boost";
    public static final String ID_GOURMAND = ID_ENSORCELLATION + ":gourmand";

    // CHESTPLATE
    public static final String ID_REACH = ID_ENSORCELLATION + ":reach";
    public static final String ID_VITALITY = ID_ENSORCELLATION + ":vitality";

    // WEAPONS
    public static final String ID_DAMAGE_ENDER = ID_ENSORCELLATION + ":damage_ender";
    public static final String ID_DAMAGE_ILLAGER = ID_ENSORCELLATION + ":damage_illager";
    public static final String ID_DAMAGE_VILLAGER = ID_ENSORCELLATION + ":damage_villager";

    public static final String ID_CAVALIER = ID_ENSORCELLATION + ":cavalier";
    public static final String ID_FROST_ASPECT = ID_ENSORCELLATION + ":frost_aspect";
    public static final String ID_LEECH = ID_ENSORCELLATION + ":leech";
    public static final String ID_MAGIC_EDGE = ID_ENSORCELLATION + ":magic_edge";
    public static final String ID_VORPAL = ID_ENSORCELLATION + ":vorpal";

    // TOOLS
    public static final String ID_EXCAVATING = ID_ENSORCELLATION + ":excavating";
    public static final String ID_PROSPECTING = ID_ENSORCELLATION + ":prospecting";
    public static final String ID_SMASHING = ID_ENSORCELLATION + ":smashing";
    public static final String ID_SMELTING = ID_ENSORCELLATION + ":smelting";

    // BOWS
    public static final String ID_HUNTER = ID_ENSORCELLATION + ":hunter";
    public static final String ID_QUICK_DRAW = ID_ENSORCELLATION + ":quick_draw";
    public static final String ID_TRUESHOT = ID_ENSORCELLATION + ":trueshot";
    public static final String ID_VOLLEY = ID_ENSORCELLATION + ":volley";

    // FISHING RODS
    public static final String ID_ANGLER = ID_ENSORCELLATION + ":angler";
    public static final String ID_PILFERING = ID_ENSORCELLATION + ":pilfering";

    // HOES
    public static final String ID_FURROWING = ID_ENSORCELLATION + ":furrowing";
    public static final String ID_TILLING = ID_ENSORCELLATION + ":tilling";
    public static final String ID_WEEDING = ID_ENSORCELLATION + ":weeding";

    // SHIELDS
    public static final String ID_BULWARK = ID_ENSORCELLATION + ":bulwark";
    public static final String ID_PHALANX = ID_ENSORCELLATION + ":phalanx";

    // MISC
    public static final String ID_SOULBOUND = ID_ENSORCELLATION + ":soulbound";

    // CURSES
    public static final String ID_CURSE_FOOL = ID_ENSORCELLATION + ":curse_fool";
    public static final String ID_CURSE_MERCY = ID_ENSORCELLATION + ":curse_mercy";
    // endregion

    // region ENCHANTMENTS

    // ARMOR
    @ObjectHolder(ID_PROTECTION_MAGIC)
    public static final Enchantment PROTECTION_MAGIC = null;

    @ObjectHolder(ID_DISPLACEMENT)
    public static final Enchantment DISPLACEMENT = null;

    @ObjectHolder(ID_FIRE_REBUKE)
    public static final Enchantment FIRE_REBUKE = null;

    @ObjectHolder(ID_FROST_REBUKE)
    public static final Enchantment FROST_REBUKE = null;

    // HELMET
    @ObjectHolder(ID_AIR_AFFINITY)
    public static final Enchantment AIR_AFFINITY = null;

    @ObjectHolder(ID_EXP_BOOST)
    public static final Enchantment EXP_BOOST = null;

    @ObjectHolder(ID_GOURMAND)
    public static final Enchantment GOURMAND = null;

    // CHESTPLATE
    @ObjectHolder(ID_REACH)
    public static final Enchantment REACH = null;

    @ObjectHolder(ID_VITALITY)
    public static final Enchantment VITALITY = null;

    // WEAPONS
    @ObjectHolder(ID_DAMAGE_ENDER)
    public static final Enchantment DAMAGE_ENDER = null;

    @ObjectHolder(ID_DAMAGE_ILLAGER)
    public static final Enchantment DAMAGE_ILLAGER = null;

    @ObjectHolder(ID_DAMAGE_VILLAGER)
    public static final Enchantment DAMAGE_VILLAGER = null;

    @ObjectHolder(ID_CAVALIER)
    public static final Enchantment CAVALIER = null;

    @ObjectHolder(ID_FROST_ASPECT)
    public static final Enchantment FROST_ASPECT = null;

    @ObjectHolder(ID_LEECH)
    public static final Enchantment LEECH = null;

    @ObjectHolder(ID_MAGIC_EDGE)
    public static final Enchantment MAGIC_EDGE = null;

    @ObjectHolder(ID_VORPAL)
    public static final Enchantment VORPAL = null;

    // TOOLS
    @ObjectHolder(ID_EXCAVATING)
    public static final Enchantment EXCAVATING = null;

    @ObjectHolder(ID_SMASHING)
    public static final Enchantment SMASHING = null;

    @ObjectHolder(ID_SMELTING)
    public static final Enchantment SMELTING = null;

    // BOWS
    @ObjectHolder(ID_HUNTER)
    public static final Enchantment HUNTER = null;

    @ObjectHolder(ID_QUICK_DRAW)
    public static final Enchantment QUICK_DRAW = null;

    @ObjectHolder(ID_TRUESHOT)
    public static final Enchantment TRUESHOT = null;

    @ObjectHolder(ID_VOLLEY)
    public static final Enchantment VOLLEY = null;

    // FISHING RODS
    @ObjectHolder(ID_ANGLER)
    public static final Enchantment ANGLER = null;

    @ObjectHolder(ID_PILFERING)
    public static final Enchantment PILFERING = null;

    // HOES
    @ObjectHolder(ID_FURROWING)
    public static final Enchantment FURROWING = null;
    @ObjectHolder(ID_TILLING)
    public static final Enchantment TILLING = null;

    @ObjectHolder(ID_WEEDING)
    public static final Enchantment WEEDING = null;

    // SHIELDS
    @ObjectHolder(ID_BULWARK)
    public static final Enchantment BULWARK = null;

    @ObjectHolder(ID_PHALANX)
    public static final Enchantment PHALANX = null;

    // MISC
    @ObjectHolder(ID_SOULBOUND)
    public static final Enchantment SOULBOUND = null;

    // CURSES
    @ObjectHolder(ID_CURSE_FOOL)
    public static final Enchantment CURSE_FOOL = null;

    @ObjectHolder(ID_CURSE_MERCY)
    public static final Enchantment CURSE_MERCY = null;
    // endregion
}
