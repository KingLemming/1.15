package cofh.ensorcellment.init;

import cofh.ensorcellment.enchantment.*;
import cofh.ensorcellment.enchantment.override.FrostWalkerEnchantmentImp;
import cofh.ensorcellment.enchantment.override.MendingEnchantmentAlt;
import cofh.ensorcellment.enchantment.override.ThornsEnchantmentImp;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.MAX_ENCHANT_LEVEL;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.*;
import static net.minecraft.enchantment.Enchantments.*;

public class ConfigEnsorc {

    private static final ConfigEnsorc INSTANCE = new ConfigEnsorc();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(INSTANCE);
        registered = true;

        genCommonConfig();
        // genClientConfig();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonSpec);
        // ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
    }

    private ConfigEnsorc() {

    }

    // region CONFIG SPEC
    private static final Builder COMMON_CONFIG = new Builder();
    private static ForgeConfigSpec commonSpec;

    private static final Builder CLIENT_CONFIG = new Builder();
    private static ForgeConfigSpec clientSpec;

    private static void genCommonConfig() {

        genEnchantmentConfig();
        genOverrideConfig();

        commonSpec = COMMON_CONFIG.build();
    }

    private static void genClientConfig() {

        clientSpec = CLIENT_CONFIG.build();
    }

    private static void genEnchantmentConfig() {

        String comment;
        // COMMON_CONFIG.push("Enchantment");

        COMMON_CONFIG.push("Air Affinity");
        comment = "If TRUE, the Air Affinity Enchantment is available for Helmets.";
        enableAirWorker = COMMON_CONFIG.comment(comment).define("Enable", true);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Angler's Bounty");
        comment = "If TRUE, the Angler's Bounty Enchantment is available for Fishing Rods.";
        enableAngler = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelAngler = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance of an additional drop per level of the Enchantment (in percentage).";
        chanceAngler = COMMON_CONFIG.comment(comment).defineInRange("Effect Chance", 50, 1, 100);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Bulwark");
        comment = "If TRUE, the Bulwark Enchantment is available for Shields.";
        enableBulwark = COMMON_CONFIG.comment(comment).define("Enable", true);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Cavalier");
        comment = "If TRUE, the Cavalier Enchantment is available for various Weapons.";
        enableCavalier = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelCavalier = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Ender Disruption");
        comment = "If TRUE, the Ender Disruption Enchantment is available for various Weapons.";
        enableDamageEnder = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDamageEnder = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Vigilante");
        comment = "If TRUE, the Vigilante Enchantment is available for various Weapons.";
        enableDamageIllager = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDamageIllager = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Outlaw");
        comment = "If TRUE, the Outlaw Enchantment is available for various Weapons.";
        enableDamageVillager = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDamageVillager = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        comment = "If TRUE, the Outlaw Enchantment causes Villagers (and Iron Golems) to drop Emeralds when killed.";
        dropsDamageVillager = COMMON_CONFIG.comment(comment).define("Emerald Drops", true);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Displacement");
        comment = "If TRUE, the Displacement Enchantment is available for Armor, Shields, and Horse Armor.";
        enableDisplacement = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDisplacement = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance per level of the Enchantment firing (in percentage).";
        chanceDisplacement = COMMON_CONFIG.comment(comment).defineInRange("Effect Chance", 20, 1, 100);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Insight");
        comment = "If TRUE, the Insight Enchantment is available for various Tools and Weapons.";
        enableExpBoost = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelExpBoost = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this to change the base experience awarded per level of the Enchantment.";
        amountExpBoost = COMMON_CONFIG.comment(comment).defineInRange("Effect Chance", 4, 1, 1000);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Flaming Rebuke");
        comment = "If TRUE, the Flaming Rebuke Enchantment is available for Armor, Shields, and Horse Armor.";
        enableFireRebuke = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFireRebuke = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Chilling Rebuke");
        comment = "If TRUE, the Chilling Rebuke Enchantment is available for Armor, Shields, and Horse Armor.";
        enableFrostRebuke = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFrostRebuke = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Gourmand");
        comment = "If TRUE, the Gourmand Enchantment is available for Helmets.";
        enableGourmand = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelGourmand = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Holding");
        comment = "If TRUE, the Holding Enchantment is available for various Storage Items.";
        enableHolding = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelHolding = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Hunter's Bounty");
        comment = "If TRUE, the Hunter's Bounty Enchantment is available for Bows.";
        enableHunter = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelHunter = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance of an additional drop per level of the Enchantment (in percentage).";
        chanceHunter = COMMON_CONFIG.comment(comment).defineInRange("Effect Chance", 50, 1, 100);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Leech");
        comment = "If TRUE, the Leech Enchantment is available for various Weapons.";
        enableLeech = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelLeech = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Magic Edge");
        comment = "If TRUE, the Magic Edge Enchantment is available for various Weapons.";
        enableMagicEdge = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelMagicEdge = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Phalanx");
        comment = "If TRUE, the Phalanx Enchantment is available for Shields.";
        enablePhalanx = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelPhalanx = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Magic Protection");
        comment = "If TRUE, the Magic Protection Enchantment is available for Armor and Horse Armor.";
        enableProtectionMagic = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionMagic = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Quickdraw");
        comment = "If TRUE, the Quickdraw Enchantment is available for various Bows.";
        enableQuickdraw = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelQuickdraw = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Reach");
        comment = "If TRUE, the Reach Enchantment is available for various Tools.";
        enableReach = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelReach = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Soulbound");
        comment = "If TRUE, the Soulbound Enchantment is available.";
        enableSoulbound = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment. If permanent, this setting is ignored.";
        levelSoulbound = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "If TRUE, the Soulbound Enchantment is permanent (and will remove excess levels when triggered).";
        permanentSoulbound = COMMON_CONFIG.comment(comment).define("Permanent", true);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Trueshot");
        comment = "If TRUE, the Trueshot Enchantment is available for various Bows.";
        enableTrueshot = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelTrueshot = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Volley");
        comment = "If TRUE, the Volley Enchantment is available for various Bows.";
        enableVolley = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelVolley = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Vorpal");
        comment = "If TRUE, the Vorpal Enchantment is available for various Weapons.";
        enableVorpal = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelVorpal = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the base critical hit chance of the Enchantment (in percentage).";
        critBaseVorpal = COMMON_CONFIG.comment(comment).defineInRange("Base Critical Chance", 5, 1, 100);
        comment = "Adjust this value to set the additional critical hit chance per level of the Enchantment (in percentage).";
        critLevelVorpal = COMMON_CONFIG.comment(comment).defineInRange("Critical Chance / Level", 5, 1, 100);
        comment = "Adjust this value to set the critical hit damage multiplier.";
        critDamageVorpal = COMMON_CONFIG.comment(comment).defineInRange("Critical Damage Multiplier", 5, 2, 1000);
        comment = "Adjust this value to set the base critical hit chance for the Enchantment (in percentage).";
        headBaseVorpal = COMMON_CONFIG.comment(comment).defineInRange("Base Head Drop Chance", 10, 1, 100);
        comment = "Adjust this value to set the critical hit chance per level of the Enchantment (in percentage).";
        headLevelVorpal = COMMON_CONFIG.comment(comment).defineInRange("Head Drop Chance / Level", 10, 1, 100);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Curse of Mercy");
        comment = "If TRUE, the Curse of Mercy Enchantment is available for various Weapons.";
        enableCurseMercy = COMMON_CONFIG.comment(comment).define("Enable", true);
        COMMON_CONFIG.pop();

        // COMMON_CONFIG.pop();
    }

    private static void genOverrideConfig() {

        String comment;
        // COMMON_CONFIG.push("Override");

        COMMON_CONFIG.push("Fire Aspect");
        comment = "If TRUE, the Fire Aspect Enchantment is replaced with a more configurable version which works on more items, such as Axes.";
        enableFireAspect = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFireAspect = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Frost Walker");
        comment = "If TRUE, the Frost Walker Enchantment is replaced with an improved and more configurable version which works on more items, such as Horse Armor.";
        enableFrostWalker = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFrostWalker = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        comment = "If TRUE, the Frost Walker Enchantment will also chill Lava into Cooled Magma.";
        enableFreezeLava = COMMON_CONFIG.comment(comment).define("Freeze Lava", true);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Knockback");
        comment = "If TRUE, the Knockback Enchantment is replaced with a more configurable version which works on more items, such as Axes.";
        enableKnockback = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelKnockback = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Looting");
        comment = "If TRUE, the Looting Enchantment is replaced with a more configurable version which works on more items, such as Axes.";
        enableLooting = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelLooting = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Thorns");
        comment = "If TRUE, the Thorns Enchantment is replaced with a more configurable version which works on more items, such as Shields and Horse Armor.";
        enableThorns = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelThorns = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance per level of the Enchantment firing (in percentage).";
        chanceThorns = COMMON_CONFIG.comment(comment).defineInRange("Effect Chance", 15, 1, 100);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Protection");
        comment = "If TRUE, the Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtection = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtection = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Blast Protection");
        comment = "If TRUE, the Blast Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtectionBlast = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionBlast = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Feather Falling");
        comment = "If TRUE, the Feather Falling Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtectionFall = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionFall = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Fire Protection");
        comment = "If TRUE, the Fire Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtectionFire = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionFire = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Projectile Protection");
        comment = "If TRUE, the Projectile Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtectionProjectile = COMMON_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionProjectile = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        COMMON_CONFIG.pop();

        COMMON_CONFIG.push("Mending");
        comment = "If TRUE, the Mending Enchantment is replaced with a new Enchantment - Preservation. This enchantment allows you to repair items at an Anvil without paying an increasing XP cost for every time you repair it. Additionally, these repairs have a much lower chance of damaging the anvil.";
        alternateMending = COMMON_CONFIG.comment(comment).define("Alternate Mending", false);
        comment = "If TRUE, the Mending Enchantment is improved so that XP orbs always repair items if possible, and the most damaged item is prioritized. If Alternate Mending (Preservation) is enabled, this does nothing.";
        improvedMending = COMMON_CONFIG.comment(comment).define("Improved Mending", true);
        comment = "Adjust this value to set the chance of an Anvil being damaged when used to repair an item with Preservation (in percentage). Only used if Alternate Mending (Preservation) is enabled.";
        damageMending = COMMON_CONFIG.comment(comment).defineInRange("Anvil Damage Chance", 3, 0, 12);
        COMMON_CONFIG.pop();

        // COMMON_CONFIG.pop();
    }

    private void refreshCommonConfig() {

        enableMendingImprovement = improvedMending.get();
        enableMendingOverride = alternateMending.get();

        refreshEnchantmentConfig();
        refreshOverrideConfig();
    }

    private void refreshClientConfig() {

    }

    private void refreshEnchantmentConfig() {

        // These should NEVER actually be null, but who knows in a multi-mod setup. ¯\_(ツ)_/¯
        if (AIR_AFFINITY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) AIR_AFFINITY).setEnable(enableAirWorker.get());
        }
        if (ANGLER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) ANGLER).setEnable(enableAngler.get());
            ((EnchantmentCoFH) ANGLER).setMaxLevel(levelAngler.get());
            AnglerEnchantment.chance = chanceAngler.get();
        }
        if (BULWARK instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) BULWARK).setEnable(enableBulwark.get());
        }
        if (CAVALIER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) CAVALIER).setEnable(enableCavalier.get());
            ((EnchantmentCoFH) CAVALIER).setMaxLevel(levelCavalier.get());
        }
        if (DAMAGE_ENDER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DAMAGE_ENDER).setEnable(enableDamageEnder.get());
            ((EnchantmentCoFH) DAMAGE_ENDER).setMaxLevel(levelDamageEnder.get());
        }
        if (DAMAGE_ILLAGER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DAMAGE_ILLAGER).setEnable(enableDamageIllager.get());
            ((EnchantmentCoFH) DAMAGE_ILLAGER).setMaxLevel(levelDamageIllager.get());
        }
        if (DAMAGE_VILLAGER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DAMAGE_VILLAGER).setEnable(enableDamageVillager.get());
            ((EnchantmentCoFH) DAMAGE_VILLAGER).setMaxLevel(levelDamageVillager.get());
            DamageVillagerEnchantment.enableEmeraldDrops = dropsDamageVillager.get();
        }
        if (DISPLACEMENT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DISPLACEMENT).setEnable(enableDisplacement.get());
            ((EnchantmentCoFH) DISPLACEMENT).setMaxLevel(levelDisplacement.get());
            DisplacementEnchantment.chance = chanceDisplacement.get();
        }
        if (EXP_BOOST instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) EXP_BOOST).setEnable(enableExpBoost.get());
            ((EnchantmentCoFH) EXP_BOOST).setMaxLevel(levelExpBoost.get());
            ExpBoostEnchantment.experience = amountExpBoost.get();
        }
        if (FIRE_REBUKE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FIRE_REBUKE).setEnable(enableFireRebuke.get());
            ((EnchantmentCoFH) FIRE_REBUKE).setMaxLevel(levelFireRebuke.get());
        }
        if (FROST_REBUKE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FROST_REBUKE).setEnable(enableFrostRebuke.get());
            ((EnchantmentCoFH) FROST_REBUKE).setMaxLevel(levelFrostRebuke.get());
        }
        if (GOURMAND instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) GOURMAND).setEnable(enableGourmand.get());
            ((EnchantmentCoFH) GOURMAND).setMaxLevel(levelGourmand.get());
        }
        if (HOLDING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) HOLDING).setEnable(enableHolding.get());
            ((EnchantmentCoFH) HOLDING).setMaxLevel(levelHolding.get());
        }
        if (HUNTER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) HUNTER).setEnable(enableHunter.get());
            ((EnchantmentCoFH) HUNTER).setMaxLevel(levelHunter.get());
            HunterEnchantment.chance = chanceHunter.get();
        }
        if (LEECH instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) LEECH).setEnable(enableLeech.get());
            ((EnchantmentCoFH) LEECH).setMaxLevel(levelLeech.get());
        }
        if (MAGIC_EDGE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) MAGIC_EDGE).setEnable(enableMagicEdge.get());
            ((EnchantmentCoFH) MAGIC_EDGE).setMaxLevel(levelMagicEdge.get());
        }
        if (PHALANX instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PHALANX).setEnable(enablePhalanx.get());
            ((EnchantmentCoFH) PHALANX).setMaxLevel(levelPhalanx.get());
        }
        if (PROTECTION_MAGIC instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PROTECTION_MAGIC).setEnable(enableProtectionMagic.get());
            ((EnchantmentCoFH) PROTECTION_MAGIC).setMaxLevel(levelProtectionMagic.get());
        }
        if (QUICKDRAW instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) QUICKDRAW).setEnable(enableQuickdraw.get());
            ((EnchantmentCoFH) QUICKDRAW).setMaxLevel(levelQuickdraw.get());
        }
        if (REACH instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) REACH).setEnable(enableReach.get());
            ((EnchantmentCoFH) REACH).setMaxLevel(levelReach.get());
        }
        if (SOULBOUND instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) SOULBOUND).setEnable(enableSoulbound.get());
            ((EnchantmentCoFH) SOULBOUND).setMaxLevel(levelSoulbound.get());
            SoulboundEnchantment.permanent = permanentSoulbound.get();
        }
        if (TRUESHOT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) TRUESHOT).setEnable(enableTrueshot.get());
            ((EnchantmentCoFH) TRUESHOT).setMaxLevel(levelTrueshot.get());
        }
        if (VOLLEY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) VOLLEY).setEnable(enableVolley.get());
            ((EnchantmentCoFH) VOLLEY).setMaxLevel(levelVolley.get());
        }
        if (VORPAL instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) VORPAL).setEnable(enableVorpal.get());
            ((EnchantmentCoFH) VORPAL).setMaxLevel(levelVorpal.get());
            VorpalEnchantment.critBase = critBaseVorpal.get();
            VorpalEnchantment.critLevel = critLevelVorpal.get();
            VorpalEnchantment.critDamage = critDamageVorpal.get();
            VorpalEnchantment.headBase = headBaseVorpal.get();
            VorpalEnchantment.headLevel = headLevelVorpal.get();
        }
        if (CURSE_MERCY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) CURSE_MERCY).setEnable(enableCurseMercy.get());
        }
    }

    private void refreshOverrideConfig() {

        // These should not cast incorrectly, but who knows in a multi-mod setup. ¯\_(ツ)_/¯
        if (FIRE_ASPECT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FIRE_ASPECT).setEnable(enableFireAspect.get());
            ((EnchantmentCoFH) FIRE_ASPECT).setMaxLevel(levelFireAspect.get());
        }
        if (FROST_WALKER instanceof FrostWalkerEnchantmentImp) {
            ((EnchantmentCoFH) FROST_WALKER).setEnable(enableFrostWalker.get());
            ((EnchantmentCoFH) FROST_WALKER).setMaxLevel(levelFrostWalker.get());
            ((FrostWalkerEnchantmentImp) FROST_WALKER).setFreezeLava(enableFreezeLava.get());
        }
        if (KNOCKBACK instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) KNOCKBACK).setEnable(enableKnockback.get());
            ((EnchantmentCoFH) KNOCKBACK).setMaxLevel(levelKnockback.get());
        }
        if (LOOTING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) LOOTING).setEnable(enableLooting.get());
            ((EnchantmentCoFH) LOOTING).setMaxLevel(levelLooting.get());
        }
        if (THORNS instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) THORNS).setEnable(enableThorns.get());
            ((EnchantmentCoFH) THORNS).setMaxLevel(levelThorns.get());
            ThornsEnchantmentImp.chance = chanceThorns.get();
        }
        if (MENDING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) MENDING).setEnable(alternateMending.get());
            MendingEnchantmentAlt.anvilDamage = damageMending.get() / 100F;
        }
        if (PROTECTION instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PROTECTION).setEnable(enableProtection.get());
            ((EnchantmentCoFH) PROTECTION).setMaxLevel(levelProtection.get());
        }
        if (BLAST_PROTECTION instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) BLAST_PROTECTION).setEnable(enableProtectionBlast.get());
            ((EnchantmentCoFH) BLAST_PROTECTION).setMaxLevel(levelProtectionBlast.get());
        }
        if (FEATHER_FALLING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FEATHER_FALLING).setEnable(enableProtectionFall.get());
            ((EnchantmentCoFH) FEATHER_FALLING).setMaxLevel(levelProtectionFall.get());
        }
        if (FIRE_PROTECTION instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FIRE_PROTECTION).setEnable(enableProtectionFire.get());
            ((EnchantmentCoFH) FIRE_PROTECTION).setMaxLevel(levelProtectionFire.get());
        }
        if (PROJECTILE_PROTECTION instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PROJECTILE_PROTECTION).setEnable(enableProtectionProjectile.get());
            ((EnchantmentCoFH) PROJECTILE_PROTECTION).setMaxLevel(levelProtectionProjectile.get());
        }
    }
    // endregion

    // region VARIABLES
    public static boolean enableMendingImprovement = true;
    public static boolean enableMendingOverride = false;

    private static BooleanValue enableAirWorker;

    private static BooleanValue enableAngler;
    private static IntValue levelAngler;
    private static IntValue chanceAngler;

    private static BooleanValue enableBulwark;

    private static BooleanValue enableCavalier;
    private static IntValue levelCavalier;

    private static BooleanValue enableDamageEnder;
    private static IntValue levelDamageEnder;

    private static BooleanValue enableDamageIllager;
    private static IntValue levelDamageIllager;

    private static BooleanValue enableDamageVillager;
    private static IntValue levelDamageVillager;
    private static BooleanValue dropsDamageVillager;

    private static BooleanValue enableDisplacement;
    private static IntValue levelDisplacement;
    private static IntValue chanceDisplacement;

    private static BooleanValue enableExpBoost;
    private static IntValue levelExpBoost;
    private static IntValue amountExpBoost;

    private static BooleanValue enableFireRebuke;
    private static IntValue levelFireRebuke;

    private static BooleanValue enableFrostRebuke;
    private static IntValue levelFrostRebuke;

    private static BooleanValue enableGourmand;
    private static IntValue levelGourmand;

    private static BooleanValue enableHolding;
    private static IntValue levelHolding;

    private static BooleanValue enableHunter;
    private static IntValue levelHunter;
    private static IntValue chanceHunter;

    private static BooleanValue enableLeech;
    private static IntValue levelLeech;

    private static BooleanValue enableMagicEdge;
    private static IntValue levelMagicEdge;

    private static BooleanValue enableProtectionMagic;
    private static IntValue levelProtectionMagic;

    private static BooleanValue enablePhalanx;
    private static IntValue levelPhalanx;

    private static BooleanValue enableQuickdraw;
    private static IntValue levelQuickdraw;

    private static BooleanValue enableReach;
    private static IntValue levelReach;

    private static BooleanValue enableSoulbound;
    private static IntValue levelSoulbound;
    private static BooleanValue permanentSoulbound;

    private static BooleanValue enableTrueshot;
    private static IntValue levelTrueshot;

    private static BooleanValue enableVolley;
    private static IntValue levelVolley;

    private static BooleanValue enableVorpal;
    private static IntValue levelVorpal;
    private static IntValue critBaseVorpal;
    private static IntValue critLevelVorpal;
    private static IntValue critDamageVorpal;
    private static IntValue headBaseVorpal;
    private static IntValue headLevelVorpal;

    private static BooleanValue enableCurseMercy;

    private static BooleanValue enableFireAspect;
    private static IntValue levelFireAspect;

    private static BooleanValue enableFrostWalker;
    private static IntValue levelFrostWalker;
    private static BooleanValue enableFreezeLava;

    private static BooleanValue enableKnockback;
    private static IntValue levelKnockback;

    private static BooleanValue enableLooting;
    private static IntValue levelLooting;

    private static BooleanValue enableThorns;
    private static IntValue levelThorns;
    private static IntValue chanceThorns;

    private static BooleanValue enableProtection;
    private static IntValue levelProtection;

    private static BooleanValue enableProtectionBlast;
    private static IntValue levelProtectionBlast;

    private static BooleanValue enableProtectionFall;
    private static IntValue levelProtectionFall;

    private static BooleanValue enableProtectionFire;
    private static IntValue levelProtectionFire;

    private static BooleanValue enableProtectionProjectile;
    private static IntValue levelProtectionProjectile;

    private static BooleanValue alternateMending;
    private static BooleanValue improvedMending;
    private static IntValue damageMending;
    // endregion

    // region CONFIGURATION
    @SubscribeEvent
    public void configLoading(final ModConfig.Loading event) {

        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            refreshClientConfig();
            return;
        }
        refreshCommonConfig();
    }

    @SubscribeEvent
    public void configReloading(final ModConfig.ConfigReloading event) {

        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            refreshClientConfig();
            return;
        }
        refreshCommonConfig();
    }
    // endregion
}
