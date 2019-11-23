package cofh.ensorcellation.init;

import cofh.ensorcellation.enchantment.*;
import cofh.ensorcellation.enchantment.override.FrostWalkerEnchantmentImp;
import cofh.ensorcellation.enchantment.override.MendingEnchantmentAlt;
import cofh.ensorcellation.enchantment.override.ThornsEnchantmentImp;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.MAX_ENCHANT_LEVEL;
import static cofh.lib.util.references.EnsorcellationReferences.*;
import static net.minecraft.enchantment.Enchantments.*;

public class ModConfig {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(ModConfig.class);
        registered = true;

        genServerConfig();
        // genClientConfig();

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, serverSpec);
        // ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
    }

    private ModConfig() {

    }

    // region CONFIG SPEC
    private static final Builder SERVER_CONFIG = new Builder();
    private static ForgeConfigSpec serverSpec;

    private static final Builder CLIENT_CONFIG = new Builder();
    private static ForgeConfigSpec clientSpec;

    private static void genServerConfig() {

        genEnchantmentConfig();
        genOverrideConfig();

        serverSpec = SERVER_CONFIG.build();
    }

    private static void genClientConfig() {

        clientSpec = CLIENT_CONFIG.build();
    }

    private static void genEnchantmentConfig() {

        String comment;
        SERVER_CONFIG.push("Enchantments");

        // ARMOR
        SERVER_CONFIG.push("Magic Protection");
        comment = "If TRUE, the Magic Protection Enchantment is available for Armor and Horse Armor.";
        enableProtectionMagic = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionMagic = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Displacement");
        comment = "If TRUE, the Displacement Enchantment is available for Armor, Shields, and Horse Armor.";
        enableDisplacement = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDisplacement = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance per level of the Enchantment firing (in percentage).";
        chanceDisplacement = SERVER_CONFIG.comment(comment).defineInRange("Effect Chance", 20, 1, 100);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Flaming Rebuke");
        comment = "If TRUE, the Flaming Rebuke Enchantment is available for Armor, Shields, and Horse Armor.";
        enableFireRebuke = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFireRebuke = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance per level of the Enchantment firing (in percentage).";
        chanceFireRebuke = SERVER_CONFIG.comment(comment).defineInRange("Effect Chance", 20, 1, 100);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Chilling Rebuke");
        comment = "If TRUE, the Chilling Rebuke Enchantment is available for Armor, Shields, and Horse Armor.";
        enableFrostRebuke = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFrostRebuke = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance per level of the Enchantment firing (in percentage).";
        chanceFrostRebuke = SERVER_CONFIG.comment(comment).defineInRange("Effect Chance", 20, 1, 100);
        SERVER_CONFIG.pop();

        // HELMET
        SERVER_CONFIG.push("Air Affinity");
        comment = "If TRUE, the Air Affinity Enchantment is available for Helmets.";
        enableAirWorker = SERVER_CONFIG.comment(comment).define("Enable", true);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Gourmand");
        comment = "If TRUE, the Gourmand Enchantment is available for Helmets.";
        enableGourmand = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelGourmand = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        // WEAPONS
        SERVER_CONFIG.push("Ender Disruption");
        comment = "If TRUE, the Ender Disruption Enchantment is available for various Weapons.";
        enableDamageEnder = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDamageEnder = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Vigilante");
        comment = "If TRUE, the Vigilante Enchantment is available for various Weapons.";
        enableDamageIllager = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDamageIllager = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Outlaw");
        comment = "If TRUE, the Outlaw Enchantment is available for various Weapons.";
        enableDamageVillager = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDamageVillager = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        comment = "If TRUE, the Outlaw Enchantment causes Villagers (and Iron Golems) to drop Emeralds when killed.";
        dropsDamageVillager = SERVER_CONFIG.comment(comment).define("Emerald Drops", true);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Cavalier");
        comment = "If TRUE, the Cavalier Enchantment is available for various Weapons.";
        enableCavalier = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelCavalier = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Frost Aspect");
        comment = "If TRUE, the Frost Aspect Enchantment is available for various Weapons.";
        enableFrostAspect = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFrostAspect = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Leech");
        comment = "If TRUE, the Leech Enchantment is available for various Weapons.";
        enableLeech = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelLeech = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Magic Edge");
        comment = "If TRUE, the Magic Edge Enchantment is available for various Weapons.";
        enableMagicEdge = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelMagicEdge = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Vorpal");
        comment = "If TRUE, the Vorpal Enchantment is available for various Weapons.";
        enableVorpal = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelVorpal = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the base critical hit chance of the Enchantment (in percentage).";
        critBaseVorpal = SERVER_CONFIG.comment(comment).defineInRange("Base Critical Chance", 5, 1, 100);
        comment = "Adjust this value to set the additional critical hit chance per level of the Enchantment (in percentage).";
        critLevelVorpal = SERVER_CONFIG.comment(comment).defineInRange("Critical Chance / Level", 5, 1, 100);
        comment = "Adjust this value to set the critical hit damage multiplier.";
        critDamageVorpal = SERVER_CONFIG.comment(comment).defineInRange("Critical Damage Multiplier", 5, 2, 1000);
        comment = "Adjust this value to set the base critical hit chance for the Enchantment (in percentage).";
        headBaseVorpal = SERVER_CONFIG.comment(comment).defineInRange("Base Head Drop Chance", 10, 1, 100);
        comment = "Adjust this value to set the critical hit chance per level of the Enchantment (in percentage).";
        headLevelVorpal = SERVER_CONFIG.comment(comment).defineInRange("Head Drop Chance / Level", 10, 1, 100);
        SERVER_CONFIG.pop();

        // TOOLS
        SERVER_CONFIG.push("Excavating");
        comment = "If TRUE, the Excavating Enchantment is available for various Tools.";
        enableExcavating = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelExcavating = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Insight");
        comment = "If TRUE, the Insight Enchantment is available for various Tools and Weapons.";
        enableExpBoost = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelExpBoost = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this to change the max experience awarded per level of the Enchantment.";
        amountExpBoost = SERVER_CONFIG.comment(comment).defineInRange("Experience Amount", 4, 1, 1000);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Reach");
        comment = "If TRUE, the Reach Enchantment is available for various Tools.";
        enableReach = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelReach = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        // BOWS
        SERVER_CONFIG.push("Hunter's Bounty");
        comment = "If TRUE, the Hunter's Bounty Enchantment is available for Bows.";
        enableHunter = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelHunter = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance of an additional drop per level of the Enchantment (in percentage).";
        chanceHunter = SERVER_CONFIG.comment(comment).defineInRange("Effect Chance", 50, 1, 100);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Quick Draw");
        comment = "If TRUE, the Quick Draw Enchantment is available for various Bows.";
        enableQuickDraw = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelQuickDraw = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Trueshot");
        comment = "If TRUE, the Trueshot Enchantment is available for various Bows.";
        enableTrueshot = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelTrueshot = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Volley");
        comment = "If TRUE, the Volley Enchantment is available for various Bows.";
        enableVolley = SERVER_CONFIG.comment(comment).define("Enable", true);
        SERVER_CONFIG.pop();

        // FISHING RODS
        SERVER_CONFIG.push("Angler's Bounty");
        comment = "If TRUE, the Angler's Bounty Enchantment is available for Fishing Rods.";
        enableAngler = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelAngler = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance of an additional drop per level of the Enchantment (in percentage).";
        chanceAngler = SERVER_CONFIG.comment(comment).defineInRange("Effect Chance", 50, 1, 100);
        SERVER_CONFIG.pop();

        // HOES
        SERVER_CONFIG.push("Furrowing");
        comment = "If TRUE, the Furrowing Enchantment is available for Hoes.";
        enableFurrowing = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFurrowing = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Tilling");
        comment = "If TRUE, the Tilling Enchantment is available for Hoes.";
        enableTilling = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelTilling = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Weeding");
        comment = "If TRUE, the Weeding Enchantment is available for Hoes.";
        enableWeeding = SERVER_CONFIG.comment(comment).define("Enable", true);
        SERVER_CONFIG.pop();

        // SHIELDS
        SERVER_CONFIG.push("Bulwark");
        comment = "If TRUE, the Bulwark Enchantment is available for Shields.";
        enableBulwark = SERVER_CONFIG.comment(comment).define("Enable", true);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Phalanx");
        comment = "If TRUE, the Phalanx Enchantment is available for Shields.";
        enablePhalanx = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelPhalanx = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        // MISC
        // TODO: Revisit
        //        COMMON_CONFIG.push("Holding");
        //        comment = "If TRUE, the Holding Enchantment is available for various Storage Items.";
        //        enableHolding = COMMON_CONFIG.comment(comment).define("Enable", true);
        //        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        //        levelHolding = COMMON_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        //        COMMON_CONFIG.pop();

        SERVER_CONFIG.push("Soulbound");
        comment = "If TRUE, the Soulbound Enchantment is available.";
        enableSoulbound = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment. If permanent, this setting is ignored.";
        levelSoulbound = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "If TRUE, the Soulbound Enchantment is permanent (and will remove excess levels when triggered).";
        permanentSoulbound = SERVER_CONFIG.comment(comment).define("Permanent", true);
        SERVER_CONFIG.pop();

        // CURSES
        SERVER_CONFIG.push("Curse of Foolishness");
        comment = "If TRUE, the Curse of Foolishness Enchantment is available for various Tools and Weapons.";
        enableCurseFool = SERVER_CONFIG.comment(comment).define("Enable", true);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Curse of Mercy");
        comment = "If TRUE, the Curse of Mercy Enchantment is available for various Weapons.";
        enableCurseMercy = SERVER_CONFIG.comment(comment).define("Enable", true);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.pop();
    }

    private static void genOverrideConfig() {

        String comment;
        SERVER_CONFIG.push("Overrides");

        SERVER_CONFIG.push("Protection");
        comment = "If TRUE, the Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtection = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtection = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Blast Protection");
        comment = "If TRUE, the Blast Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtectionBlast = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionBlast = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Feather Falling");
        comment = "If TRUE, the Feather Falling Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtectionFall = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionFall = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Fire Protection");
        comment = "If TRUE, the Fire Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtectionFire = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionFire = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Projectile Protection");
        comment = "If TRUE, the Projectile Protection Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        enableProtectionProjectile = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelProtectionProjectile = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Fire Aspect");
        comment = "If TRUE, the Fire Aspect Enchantment is replaced with a more configurable version which works on more items, such as Axes.";
        enableFireAspect = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFireAspect = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Frost Walker");
        comment = "If TRUE, the Frost Walker Enchantment is replaced with an improved and more configurable version which works on more items, such as Horse Armor.";
        enableFrostWalker = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFrostWalker = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        comment = "If TRUE, the Frost Walker Enchantment will also chill Lava into Glossed Magma.";
        enableFreezeLava = SERVER_CONFIG.comment(comment).define("Freeze Lava", true);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Knockback");
        comment = "If TRUE, the Knockback Enchantment is replaced with a more configurable version which works on more items, such as Axes.";
        enableKnockback = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelKnockback = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Looting");
        comment = "If TRUE, the Looting Enchantment is replaced with a more configurable version which works on more items, such as Axes.";
        enableLooting = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelLooting = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Thorns");
        comment = "If TRUE, the Thorns Enchantment is replaced with a more configurable version which works on more items, such as Shields and Horse Armor.";
        enableThorns = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelThorns = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance per level of the Enchantment firing (in percentage).";
        chanceThorns = SERVER_CONFIG.comment(comment).defineInRange("Effect Chance", 15, 1, 100);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Mending");
        comment = "If TRUE, the Mending Enchantment is replaced with a new Enchantment - Preservation. This enchantment allows you to repair items at an Anvil without paying an increasing XP cost for every time you repair it. Additionally, these repairs have a much lower chance of damaging the anvil.";
        alternateMending = SERVER_CONFIG.comment(comment).define("Alternate Mending", false);
        comment = "Adjust this value to set the chance of an Anvil being damaged when used to repair an item with Preservation (in percentage). Only used if Alternate Mending (Preservation) is enabled.";
        damageMending = SERVER_CONFIG.comment(comment).defineInRange("Anvil Damage Chance", 3, 0, 12);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.pop();
    }

    private static void refreshServerConfig() {

        enableMendingOverride = alternateMending.get();

        refreshEnchantmentConfig();
        refreshOverrideConfig();
        refreshPotionConfig();
    }

    private static void refreshClientConfig() {

    }

    private static void refreshEnchantmentConfig() {

        // These should NEVER actually be null, but who knows in a multi-mod setup. ¯\_(ツ)_/¯

        // ARMOR
        if (PROTECTION_MAGIC instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PROTECTION_MAGIC).setEnable(enableProtectionMagic.get());
            ((EnchantmentCoFH) PROTECTION_MAGIC).setMaxLevel(levelProtectionMagic.get());
        }
        if (DISPLACEMENT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DISPLACEMENT).setEnable(enableDisplacement.get());
            ((EnchantmentCoFH) DISPLACEMENT).setMaxLevel(levelDisplacement.get());
            DisplacementEnchantment.chance = chanceDisplacement.get();
        }
        if (FIRE_REBUKE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FIRE_REBUKE).setEnable(enableFireRebuke.get());
            ((EnchantmentCoFH) FIRE_REBUKE).setMaxLevel(levelFireRebuke.get());
            FireRebukeEnchantment.chance = chanceFireRebuke.get();
        }
        if (FROST_REBUKE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FROST_REBUKE).setEnable(enableFrostRebuke.get());
            ((EnchantmentCoFH) FROST_REBUKE).setMaxLevel(levelFrostRebuke.get());
            FrostRebukeEnchantment.chance = chanceFrostRebuke.get();
        }
        // HELMET
        if (AIR_AFFINITY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) AIR_AFFINITY).setEnable(enableAirWorker.get());
        }
        if (GOURMAND instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) GOURMAND).setEnable(enableGourmand.get());
            ((EnchantmentCoFH) GOURMAND).setMaxLevel(levelGourmand.get());
        }
        // WEAPONS
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
        if (CAVALIER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) CAVALIER).setEnable(enableCavalier.get());
            ((EnchantmentCoFH) CAVALIER).setMaxLevel(levelCavalier.get());
        }
        if (FROST_ASPECT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FROST_ASPECT).setEnable(enableFrostAspect.get());
            ((EnchantmentCoFH) FROST_ASPECT).setMaxLevel(levelFrostAspect.get());
        }
        if (LEECH instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) LEECH).setEnable(enableLeech.get());
            ((EnchantmentCoFH) LEECH).setMaxLevel(levelLeech.get());
        }
        if (MAGIC_EDGE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) MAGIC_EDGE).setEnable(enableMagicEdge.get());
            ((EnchantmentCoFH) MAGIC_EDGE).setMaxLevel(levelMagicEdge.get());
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
        // TOOLS
        if (EXCAVATING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) EXCAVATING).setEnable(enableExcavating.get());
            ((EnchantmentCoFH) EXCAVATING).setMaxLevel(levelExcavating.get());
        }
        if (EXP_BOOST instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) EXP_BOOST).setEnable(enableExpBoost.get());
            ((EnchantmentCoFH) EXP_BOOST).setMaxLevel(levelExpBoost.get());
            ExpBoostEnchantment.experience = amountExpBoost.get();
        }
        if (REACH instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) REACH).setEnable(enableReach.get());
            ((EnchantmentCoFH) REACH).setMaxLevel(levelReach.get());
        }
        // BOWS
        if (HUNTER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) HUNTER).setEnable(enableHunter.get());
            ((EnchantmentCoFH) HUNTER).setMaxLevel(levelHunter.get());
            HunterEnchantment.chance = chanceHunter.get();
        }
        if (QUICK_DRAW instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) QUICK_DRAW).setEnable(enableQuickDraw.get());
            ((EnchantmentCoFH) QUICK_DRAW).setMaxLevel(levelQuickDraw.get());
        }
        if (TRUESHOT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) TRUESHOT).setEnable(enableTrueshot.get());
            ((EnchantmentCoFH) TRUESHOT).setMaxLevel(levelTrueshot.get());
        }
        if (VOLLEY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) VOLLEY).setEnable(enableVolley.get());
        }
        // FISHING RODS
        if (ANGLER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) ANGLER).setEnable(enableAngler.get());
            ((EnchantmentCoFH) ANGLER).setMaxLevel(levelAngler.get());
            AnglerEnchantment.chance = chanceAngler.get();
        }
        // HOES
        if (FURROWING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FURROWING).setEnable(enableFurrowing.get());
            ((EnchantmentCoFH) FURROWING).setMaxLevel(levelFurrowing.get());
        }
        if (TILLING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) TILLING).setEnable(enableTilling.get());
            ((EnchantmentCoFH) TILLING).setMaxLevel(levelTilling.get());
        }
        if (WEEDING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) WEEDING).setEnable(enableWeeding.get());
        }
        // SHIELDS
        if (BULWARK instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) BULWARK).setEnable(enableBulwark.get());
        }
        if (PHALANX instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PHALANX).setEnable(enablePhalanx.get());
            ((EnchantmentCoFH) PHALANX).setMaxLevel(levelPhalanx.get());
        }
        // MISC
        // TODO: Revisit
        //        if (HOLDING instanceof EnchantmentCoFH) {
        //            ((EnchantmentCoFH) HOLDING).setEnable(enableHolding.get());
        //            ((EnchantmentCoFH) HOLDING).setMaxLevel(levelHolding.get());
        //        }
        if (SOULBOUND instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) SOULBOUND).setEnable(enableSoulbound.get());
            ((EnchantmentCoFH) SOULBOUND).setMaxLevel(levelSoulbound.get());
            SoulboundEnchantment.permanent = permanentSoulbound.get();
        }
        // CURSES
        if (CURSE_FOOL instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) CURSE_FOOL).setEnable(enableCurseFool.get());
        }
        if (CURSE_MERCY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) CURSE_MERCY).setEnable(enableCurseMercy.get());
        }
    }

    private static void refreshOverrideConfig() {

        // These should not cast incorrectly, but who knows in a multi-mod setup. ¯\_(ツ)_/¯
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
    }

    private static void refreshPotionConfig() {

    }
    // endregion

    // region VARIABLES
    public static boolean enableMendingOverride = false;

    // ARMOR
    private static BooleanValue enableProtectionMagic;
    private static IntValue levelProtectionMagic;

    private static BooleanValue enableDisplacement;
    private static IntValue levelDisplacement;
    private static IntValue chanceDisplacement;

    private static BooleanValue enableFireRebuke;
    private static IntValue levelFireRebuke;
    private static IntValue chanceFireRebuke;

    private static BooleanValue enableFrostRebuke;
    private static IntValue levelFrostRebuke;
    private static IntValue chanceFrostRebuke;

    // HELMET
    private static BooleanValue enableAirWorker;

    private static BooleanValue enableGourmand;
    private static IntValue levelGourmand;

    // WEAPONS
    private static BooleanValue enableDamageEnder;
    private static IntValue levelDamageEnder;

    private static BooleanValue enableDamageIllager;
    private static IntValue levelDamageIllager;

    private static BooleanValue enableDamageVillager;
    private static IntValue levelDamageVillager;
    private static BooleanValue dropsDamageVillager;

    private static BooleanValue enableCavalier;
    private static IntValue levelCavalier;

    private static BooleanValue enableFrostAspect;
    private static IntValue levelFrostAspect;

    private static BooleanValue enableLeech;
    private static IntValue levelLeech;

    private static BooleanValue enableMagicEdge;
    private static IntValue levelMagicEdge;

    private static BooleanValue enableVorpal;
    private static IntValue levelVorpal;
    private static IntValue critBaseVorpal;
    private static IntValue critLevelVorpal;
    private static IntValue critDamageVorpal;
    private static IntValue headBaseVorpal;
    private static IntValue headLevelVorpal;

    // TOOLS
    private static BooleanValue enableExcavating;
    private static IntValue levelExcavating;

    private static BooleanValue enableExpBoost;
    private static IntValue levelExpBoost;
    private static IntValue amountExpBoost;

    private static BooleanValue enableReach;
    private static IntValue levelReach;

    // BOWS
    private static BooleanValue enableHunter;
    private static IntValue levelHunter;
    private static IntValue chanceHunter;

    private static BooleanValue enableQuickDraw;
    private static IntValue levelQuickDraw;

    private static BooleanValue enableTrueshot;
    private static IntValue levelTrueshot;

    private static BooleanValue enableVolley;

    // FISHING RODS
    private static BooleanValue enableAngler;
    private static IntValue levelAngler;
    private static IntValue chanceAngler;

    // HOES
    private static BooleanValue enableFurrowing;
    private static IntValue levelFurrowing;

    private static BooleanValue enableTilling;
    private static IntValue levelTilling;

    private static BooleanValue enableWeeding;

    // SHIELDS
    private static BooleanValue enableBulwark;

    private static BooleanValue enablePhalanx;
    private static IntValue levelPhalanx;

    // MISC
    // TODO: Revisit
    //    private static BooleanValue enableHolding;
    //    private static IntValue levelHolding;

    private static BooleanValue enableSoulbound;
    private static IntValue levelSoulbound;
    private static BooleanValue permanentSoulbound;

    // CURSES
    private static BooleanValue enableCurseFool;

    private static BooleanValue enableCurseMercy;

    // OVERRIDES
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

    private static BooleanValue alternateMending;
    private static IntValue damageMending;
    // endregion

    // region CONFIGURATION
    @SubscribeEvent
    public static void configLoading(final net.minecraftforge.fml.config.ModConfig.Loading event) {

        switch (event.getConfig().getType()) {
            case CLIENT:
                refreshClientConfig();
                break;
            case SERVER:
                refreshServerConfig();
        }
    }

    @SubscribeEvent
    public static void configReloading(final net.minecraftforge.fml.config.ModConfig.ConfigReloading event) {

        switch (event.getConfig().getType()) {
            case CLIENT:
                refreshClientConfig();
                break;
            case SERVER:
                refreshServerConfig();
        }
    }
    // endregion
}
