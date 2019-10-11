package cofh.lib.util.modhelpers;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_ENSORCELLMENT;

public class EnsorcellmentHelper {

    private EnsorcellmentHelper() {

    }

    // region IDS
    public static final String ID_PROTECTION = "minecraft:protection";
    public static final String ID_PROTECTION_BLAST = "minecraft:blast_protection";
    public static final String ID_PROTECTION_FALL = "minecraft:feather_falling";
    public static final String ID_PROTECTION_FIRE = "minecraft:fire_protection";
    public static final String ID_PROTECTION_PROJECTILE = "minecraft:projectile_protection";

    public static final String ID_FIRE_ASPECT = "minecraft:fire_aspect";
    public static final String ID_FROST_WALKER = "minecraft:frost_walker";
    public static final String ID_KNOCKBACK = "minecraft:knockback";
    public static final String ID_LOOTING = "minecraft:looting";
    public static final String ID_MENDING = "minecraft:mending";
    public static final String ID_THORNS = "minecraft:thorns";

    public static final String ID_PROTECTION_MAGIC = ID_ENSORCELLMENT + ":magic_protection";

    public static final String ID_AIR_WORKER = ID_ENSORCELLMENT + ":air_worker";
    public static final String ID_ANGLER = ID_ENSORCELLMENT + ":angler";
    public static final String ID_BULWARK = ID_ENSORCELLMENT + ":bulwark";
    public static final String ID_CAVALIER = ID_ENSORCELLMENT + ":cavalier";
    public static final String ID_DAMAGE_ENDER = ID_ENSORCELLMENT + ":damage_ender";
    public static final String ID_DAMAGE_ILLAGER = ID_ENSORCELLMENT + ":damage_illager";
    public static final String ID_DAMAGE_VILLAGER = ID_ENSORCELLMENT + ":damage_villager";
    public static final String ID_DISPLACEMENT = ID_ENSORCELLMENT + ":displacement";
    public static final String ID_EXP_BOOST = ID_ENSORCELLMENT + ":exp_boost";
    public static final String ID_GOURMAND = ID_ENSORCELLMENT + ":gourmand";
    public static final String ID_HOLDING = ID_ENSORCELLMENT + ":holding";
    public static final String ID_HUNTER = ID_ENSORCELLMENT + ":hunter";
    public static final String ID_LEECH = ID_ENSORCELLMENT + ":leech";
    public static final String ID_MAGIC_EDGE = ID_ENSORCELLMENT + ":magic_edge";
    public static final String ID_PHALANX = ID_ENSORCELLMENT + ":phalanx";
    public static final String ID_QUICKDRAW = ID_ENSORCELLMENT + ":quickdraw";
    public static final String ID_SMASHING = ID_ENSORCELLMENT + ":smashing";
    public static final String ID_SMELTING = ID_ENSORCELLMENT + ":smelting";
    public static final String ID_SOULBOUND = ID_ENSORCELLMENT + ":soulbound";
    public static final String ID_TRUESHOT = ID_ENSORCELLMENT + ":trueshot";
    public static final String ID_VOLLEY = ID_ENSORCELLMENT + ":volley";
    public static final String ID_VORPAL = ID_ENSORCELLMENT + ":vorpal";

    public static final String ID_CURSE_MERCY = ID_ENSORCELLMENT + ":curse_mercy";
    // endregion

    // region ENCHANTMENTS
    @ObjectHolder(ID_AIR_WORKER)
    public static final Enchantment AIR_WORKER = null;

    @ObjectHolder(ID_ANGLER)
    public static final Enchantment ANGLER = null;

    @ObjectHolder(ID_BULWARK)
    public static final Enchantment BULWARK = null;

    @ObjectHolder(ID_CAVALIER)
    public static final Enchantment CAVALIER = null;

    @ObjectHolder(ID_DAMAGE_ENDER)
    public static final Enchantment DAMAGE_ENDER = null;

    @ObjectHolder(ID_DAMAGE_ILLAGER)
    public static final Enchantment DAMAGE_ILLAGER = null;

    @ObjectHolder(ID_DAMAGE_VILLAGER)
    public static final Enchantment DAMAGE_VILLAGER = null;

    @ObjectHolder(ID_DISPLACEMENT)
    public static final Enchantment DISPLACEMENT = null;

    @ObjectHolder(ID_EXP_BOOST)
    public static final Enchantment EXP_BOOST = null;

    @ObjectHolder(ID_GOURMAND)
    public static final Enchantment GOURMAND = null;

    @ObjectHolder(ID_HOLDING)
    public static final Enchantment HOLDING = null;

    @ObjectHolder(ID_HUNTER)
    public static final Enchantment HUNTER = null;

    @ObjectHolder(ID_LEECH)
    public static final Enchantment LEECH = null;

    @ObjectHolder(ID_MAGIC_EDGE)
    public static final Enchantment MAGIC_EDGE = null;

    @ObjectHolder(ID_PHALANX)
    public static final Enchantment PHALANX = null;

    @ObjectHolder(ID_PROTECTION_MAGIC)
    public static final Enchantment PROTECTION_MAGIC = null;

    @ObjectHolder(ID_QUICKDRAW)
    public static final Enchantment QUICKDRAW = null;

    @ObjectHolder(ID_SMASHING)
    public static final Enchantment SMASHING = null;

    @ObjectHolder(ID_SMELTING)
    public static final Enchantment SMELTING = null;

    @ObjectHolder(ID_SOULBOUND)
    public static final Enchantment SOULBOUND = null;

    @ObjectHolder(ID_TRUESHOT)
    public static final Enchantment TRUESHOT = null;

    @ObjectHolder(ID_VOLLEY)
    public static final Enchantment VOLLEY = null;

    @ObjectHolder(ID_VORPAL)
    public static final Enchantment VORPAL = null;
    // endregion

    // region CURSES
    @ObjectHolder(ID_CURSE_MERCY)
    public static final Enchantment CURSE_MERCY = null;
    // endregion
}
