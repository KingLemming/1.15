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
import static cofh.lib.util.references.EnsorcReferences.*;
import static net.minecraft.enchantment.Enchantments.*;

public class EnsorcConfig {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        FMLJavaModLoadingContext.get().getModEventBus().register(EnsorcConfig.class);
        registered = true;

        genServerConfig();
        // genClientConfig();

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, serverSpec);
        // ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientSpec);
    }

    private EnsorcConfig() {

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

        String treasure = "This sets whether or not the Enchantment is considered a 'treasure' enchantment.";
        String level = "This option adjusts the maximum allowable level for the Enchantment.";

        // TODO: Remove/refactor
        String comment;

        SERVER_CONFIG.push("Enchantments");

        // ARMOR
        SERVER_CONFIG.push("Magic Protection");
        enableProtectionMagic = SERVER_CONFIG
                .comment("If TRUE, the Magic Protection Enchantment is available for Armor and Horse Armor.")
                .define("Enable", true);
        treasureProtectionMagic = SERVER_CONFIG
                .comment(treasure)
                .define("Treasure", false);
        levelProtectionMagic = SERVER_CONFIG
                .comment(level)
                .defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Displacement");
        enableDisplacement = SERVER_CONFIG
                .comment("If TRUE, the Displacement Enchantment is available for Armor, Shields, and Horse Armor.")
                .define("Enable", true);
        treasureDisplacement = SERVER_CONFIG
                .comment(treasure)
                .define("Treasure", false);
        levelDisplacement = SERVER_CONFIG
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        chanceDisplacement = SERVER_CONFIG
                .comment("Adjust this value to set the chance per level of the Enchantment firing (in percentage).")
                .defineInRange("Effect Chance", 20, 1, 100);
        allowMobsDisplacement = SERVER_CONFIG
                .comment("If TRUE, mobs wearing armor with this Enchantment can teleport players.")
                .define("Mobs Teleport Players", DisplacementEnchantment.mobsAffectPlayers);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Flaming Rebuke");
        enableFireRebuke = SERVER_CONFIG
                .comment("If TRUE, the Flaming Rebuke Enchantment is available for Armor, Shields, and Horse Armor.")
                .define("Enable", true);
        treasureFireRebuke = SERVER_CONFIG
                .comment(treasure)
                .define("Treasure", false);
        levelFireRebuke = SERVER_CONFIG
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        chanceFireRebuke = SERVER_CONFIG
                .comment("Adjust this value to set the chance per level of the Enchantment firing (in percentage).")
                .defineInRange("Effect Chance", 20, 1, 100);
        allowMobsFireRebuke = SERVER_CONFIG
                .comment("If TRUE, mobs wearing armor with this Enchantment can knockback players.")
                .define("Mobs Knockback Players", FireRebukeEnchantment.mobsAffectPlayers);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Chilling Rebuke");
        enableFrostRebuke = SERVER_CONFIG
                .comment("If TRUE, the Chilling Rebuke Enchantment is available for Armor, Shields, and Horse Armor.")
                .define("Enable", true);
        treasureFrostRebuke = SERVER_CONFIG
                .comment(treasure)
                .define("Treasure", false);
        levelFrostRebuke = SERVER_CONFIG
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        chanceFrostRebuke = SERVER_CONFIG
                .comment("Adjust this value to set the chance per level of the Enchantment firing (in percentage).")
                .defineInRange("Effect Chance", 20, 1, 100);
        allowMobsFrostRebuke = SERVER_CONFIG
                .comment("If TRUE, mobs wearing armor with this Enchantment can knockback players.")
                .define("Mobs Knockback Players", FrostRebukeEnchantment.mobsAffectPlayers);
        SERVER_CONFIG.pop();

        // HELMET
        SERVER_CONFIG.push("Air Affinity");
        enableAirAffinity = SERVER_CONFIG
                .comment("If TRUE, the Air Affinity Enchantment is available for Helmets.")
                .define("Enable", true);
        treasureAirAffinity = SERVER_CONFIG
                .comment(treasure)
                .define("Treasure", false);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Insight");
        enableExpBoost = SERVER_CONFIG
                .comment("If TRUE, the Insight Enchantment is available for Helmets.").define("Enable", true);
        treasureExpBoost = SERVER_CONFIG
                .comment(treasure)
                .define("Treasure", false);
        levelExpBoost = SERVER_CONFIG
                .comment(level)
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        amountExpBoost = SERVER_CONFIG
                .comment("Adjust this to change the max experience awarded per level of the Enchantment.")
                .defineInRange("Experience Amount", 4, 1, 1000);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Gourmand");
        comment = "If TRUE, the Gourmand Enchantment is available for Helmets.";
        enableGourmand = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureGourmand = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelGourmand = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        // CHESTPLATE
        SERVER_CONFIG.push("Reach");
        comment = "If TRUE, the Reach Enchantment is available for Chestplates.";
        enableReach = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureReach = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelReach = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Vitality");
        comment = "If TRUE, the Vitality Enchantment is available for Chestplates.";
        enableVitality = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureVitality = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelVitality = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the health granted level of the Enchantment. (There are 2 health per heart icon.)";
        healthLevelVitality = SERVER_CONFIG.comment(comment).defineInRange("Health / Level", 4, 1, 10);
        SERVER_CONFIG.pop();

        // WEAPONS
        SERVER_CONFIG.push("Ender Disruption");
        comment = "If TRUE, the Ender Disruption Enchantment is available for various Weapons.";
        enableDamageEnder = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureDamageEnder = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDamageEnder = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Vigilante");
        comment = "If TRUE, the Vigilante Enchantment is available for various Weapons.";
        enableDamageIllager = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureDamageIllager = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDamageIllager = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Outlaw");
        comment = "If TRUE, the Outlaw Enchantment is available for various Weapons.";
        enableDamageVillager = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureDamageVillager = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelDamageVillager = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 5, 1, MAX_ENCHANT_LEVEL);
        comment = "If TRUE, the Outlaw Enchantment causes Villagers (and Iron Golems) to drop Emeralds when killed.";
        dropsDamageVillager = SERVER_CONFIG.comment(comment).define("Emerald Drops", true);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Cavalier");
        comment = "If TRUE, the Cavalier Enchantment is available for various Weapons.";
        enableCavalier = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureCavalier = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelCavalier = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Frost Aspect");
        comment = "If TRUE, the Frost Aspect Enchantment is available for various Weapons.";
        enableFrostAspect = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureFrostAspect = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFrostAspect = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Leech");
        comment = "If TRUE, the Leech Enchantment is available for various Weapons.";
        enableLeech = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureLeech = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelLeech = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Magic Edge");
        comment = "If TRUE, the Magic Edge Enchantment is available for various Weapons.";
        enableMagicEdge = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureMagicEdge = SERVER_CONFIG.comment(comment).define("Treasure", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelMagicEdge = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Vorpal");
        comment = "If TRUE, the Vorpal Enchantment is available for various Weapons.";
        enableVorpal = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureVorpal = SERVER_CONFIG.comment(comment).define("Treasure", false);
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
        comment = treasure;
        treasureExcavating = SERVER_CONFIG.comment(comment).define("Treasure", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelExcavating = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        // BOWS
        SERVER_CONFIG.push("Hunter's Bounty");
        comment = "If TRUE, the Hunter's Bounty Enchantment is available for Bows.";
        enableHunter = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureHunter = SERVER_CONFIG.comment(comment).define("Treasure", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelHunter = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance of an additional drop per level of the Enchantment (in percentage).";
        chanceHunter = SERVER_CONFIG.comment(comment).defineInRange("Effect Chance", 50, 1, 100);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Quick Draw");
        comment = "If TRUE, the Quick Draw Enchantment is available for various Bows.";
        enableQuickDraw = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureQuickDraw = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelQuickDraw = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Trueshot");
        comment = "If TRUE, the Trueshot Enchantment is available for various Bows.";
        enableTrueshot = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureTrueshot = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelTrueshot = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Volley");
        comment = "If TRUE, the Volley Enchantment is available for various Bows.";
        enableVolley = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureVolley = SERVER_CONFIG.comment(comment).define("Treasure", false);
        SERVER_CONFIG.pop();

        // FISHING RODS
        SERVER_CONFIG.push("Angler's Bounty");
        comment = "If TRUE, the Angler's Bounty Enchantment is available for Fishing Rods.";
        enableAngler = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureAngler = SERVER_CONFIG.comment(comment).define("Treasure", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelAngler = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 2, 1, MAX_ENCHANT_LEVEL);
        comment = "Adjust this value to set the chance of an additional drop per level of the Enchantment (in percentage).";
        chanceAngler = SERVER_CONFIG.comment(comment).defineInRange("Effect Chance", 50, 1, 100);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Pilfering");
        comment = "If TRUE, the Pilfering Enchantment is available for Fishing Rods.";
        enablePilfering = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasurePilfering = SERVER_CONFIG.comment(comment).define("Treasure", true);
        comment = "This sets whether or not the Enchantment works on Players.";
        playerStealPilfering = SERVER_CONFIG.comment(comment).define("Allow Player Stealing", true);
        SERVER_CONFIG.pop();

        // HOES
        SERVER_CONFIG.push("Furrowing");
        comment = "If TRUE, the Furrowing Enchantment is available for Hoes.";
        enableFurrowing = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureFurrowing = SERVER_CONFIG.comment(comment).define("Treasure", false);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelFurrowing = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Tilling");
        comment = "If TRUE, the Tilling Enchantment is available for Hoes.";
        enableTilling = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureTilling = SERVER_CONFIG.comment(comment).define("Treasure", true);
        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        levelTilling = SERVER_CONFIG.comment(comment).defineInRange("Max Level", 4, 1, MAX_ENCHANT_LEVEL);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Weeding");
        comment = "If TRUE, the Weeding Enchantment is available for Hoes.";
        enableWeeding = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureWeeding = SERVER_CONFIG.comment(comment).define("Treasure", false);
        SERVER_CONFIG.pop();

        // SHIELDS
        SERVER_CONFIG.push("Bulwark");
        comment = "If TRUE, the Bulwark Enchantment is available for Shields.";
        enableBulwark = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasureBulwark = SERVER_CONFIG.comment(comment).define("Treasure", false);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Phalanx");
        comment = "If TRUE, the Phalanx Enchantment is available for Shields.";
        enablePhalanx = SERVER_CONFIG.comment(comment).define("Enable", true);
        comment = treasure;
        treasurePhalanx = SERVER_CONFIG.comment(comment).define("Treasure", false);
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
        enableSoulbound = SERVER_CONFIG
                .comment("If TRUE, the Soulbound Enchantment is available.")
                .define("Enable", true);
        treasureSoulbound = SERVER_CONFIG
                .comment(treasure)
                .define("Treasure", false);
        levelSoulbound = SERVER_CONFIG
                .comment("This option adjusts the maximum allowable level for the Enchantment. If permanent, this setting is ignored.")
                .defineInRange("Max Level", 3, 1, MAX_ENCHANT_LEVEL);
        permanentSoulbound = SERVER_CONFIG
                .comment("If TRUE, the Soulbound Enchantment is permanent (and will remove excess levels when triggered).")
                .define("Permanent", true);
        SERVER_CONFIG.pop();

        // CURSES
        SERVER_CONFIG.push("Curse of Foolishness");
        enableCurseFool = SERVER_CONFIG
                .comment("If TRUE, the Curse of Foolishness Enchantment is available for various Tools and Weapons.")
                .define("Enable", true);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Curse of Mercy");
        enableCurseMercy = SERVER_CONFIG
                .comment("If TRUE, the Curse of Mercy Enchantment is available for various Weapons.")
                .define("Enable", true);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.pop();
    }

    private static void genOverrideConfig() {

        String treasure = "This sets whether or not the Enchantment is considered a 'treasure' enchantment.";
        String level = "This option adjusts the maximum allowable level for the Enchantment.";

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
        comment = treasure;
        treasureFrostWalker = SERVER_CONFIG.comment(comment).define("Treasure", true);
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
        comment = treasure;
        treasureMending = SERVER_CONFIG.comment(comment).define("Treasure", true);
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
            ((EnchantmentCoFH) PROTECTION_MAGIC).setTreasure(treasureProtectionMagic.get());
            ((EnchantmentCoFH) PROTECTION_MAGIC).setMaxLevel(levelProtectionMagic.get());
        }
        if (DISPLACEMENT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DISPLACEMENT).setEnable(enableDisplacement.get());
            ((EnchantmentCoFH) DISPLACEMENT).setTreasure(treasureDisplacement.get());
            ((EnchantmentCoFH) DISPLACEMENT).setMaxLevel(levelDisplacement.get());
            DisplacementEnchantment.chance = chanceDisplacement.get();
            DisplacementEnchantment.mobsAffectPlayers = allowMobsDisplacement.get();
        }
        if (FIRE_REBUKE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FIRE_REBUKE).setEnable(enableFireRebuke.get());
            ((EnchantmentCoFH) FIRE_REBUKE).setTreasure(treasureFireRebuke.get());
            ((EnchantmentCoFH) FIRE_REBUKE).setMaxLevel(levelFireRebuke.get());
            FireRebukeEnchantment.chance = chanceFireRebuke.get();
            FireRebukeEnchantment.mobsAffectPlayers = allowMobsFireRebuke.get();
        }
        if (FROST_REBUKE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FROST_REBUKE).setEnable(enableFrostRebuke.get());
            ((EnchantmentCoFH) FROST_REBUKE).setTreasure(treasureFrostRebuke.get());
            ((EnchantmentCoFH) FROST_REBUKE).setMaxLevel(levelFrostRebuke.get());
            FrostRebukeEnchantment.chance = chanceFrostRebuke.get();
            FrostRebukeEnchantment.mobsAffectPlayers = allowMobsFrostRebuke.get();
        }
        // HELMET
        if (AIR_AFFINITY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) AIR_AFFINITY).setEnable(enableAirAffinity.get());
            ((EnchantmentCoFH) AIR_AFFINITY).setTreasure(treasureAirAffinity.get());
        }
        if (EXP_BOOST instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) EXP_BOOST).setEnable(enableExpBoost.get());
            ((EnchantmentCoFH) EXP_BOOST).setTreasure(treasureExpBoost.get());
            ((EnchantmentCoFH) EXP_BOOST).setMaxLevel(levelExpBoost.get());
            ExpBoostEnchantment.experience = amountExpBoost.get();
        }
        if (GOURMAND instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) GOURMAND).setEnable(enableGourmand.get());
            ((EnchantmentCoFH) GOURMAND).setTreasure(treasureGourmand.get());
            ((EnchantmentCoFH) GOURMAND).setMaxLevel(levelGourmand.get());
        }
        // CHESTPLATE
        if (REACH instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) REACH).setEnable(enableReach.get());
            ((EnchantmentCoFH) REACH).setTreasure(treasureReach.get());
            ((EnchantmentCoFH) REACH).setMaxLevel(levelReach.get());
        }
        if (VITALITY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) VITALITY).setEnable(enableVitality.get());
            ((EnchantmentCoFH) VITALITY).setTreasure(treasureVitality.get());
            ((EnchantmentCoFH) VITALITY).setMaxLevel(levelVitality.get());
            VitalityEnchantment.health = healthLevelVitality.get();
        }
        // WEAPONS
        if (DAMAGE_ENDER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DAMAGE_ENDER).setEnable(enableDamageEnder.get());
            ((EnchantmentCoFH) DAMAGE_ENDER).setTreasure(treasureDamageEnder.get());
            ((EnchantmentCoFH) DAMAGE_ENDER).setMaxLevel(levelDamageEnder.get());
        }
        if (DAMAGE_ILLAGER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DAMAGE_ILLAGER).setEnable(enableDamageIllager.get());
            ((EnchantmentCoFH) DAMAGE_ILLAGER).setTreasure(treasureDamageIllager.get());
            ((EnchantmentCoFH) DAMAGE_ILLAGER).setMaxLevel(levelDamageIllager.get());
        }
        if (DAMAGE_VILLAGER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) DAMAGE_VILLAGER).setEnable(enableDamageVillager.get());
            ((EnchantmentCoFH) DAMAGE_VILLAGER).setTreasure(treasureDamageVillager.get());
            ((EnchantmentCoFH) DAMAGE_VILLAGER).setMaxLevel(levelDamageVillager.get());
            DamageVillagerEnchantment.enableEmeraldDrops = dropsDamageVillager.get();
        }
        if (CAVALIER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) CAVALIER).setEnable(enableCavalier.get());
            ((EnchantmentCoFH) CAVALIER).setTreasure(treasureCavalier.get());
            ((EnchantmentCoFH) CAVALIER).setMaxLevel(levelCavalier.get());
        }
        if (FROST_ASPECT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FROST_ASPECT).setEnable(enableFrostAspect.get());
            ((EnchantmentCoFH) FROST_ASPECT).setTreasure(treasureFrostAspect.get());
            ((EnchantmentCoFH) FROST_ASPECT).setMaxLevel(levelFrostAspect.get());
        }
        if (LEECH instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) LEECH).setEnable(enableLeech.get());
            ((EnchantmentCoFH) LEECH).setTreasure(treasureLeech.get());
            ((EnchantmentCoFH) LEECH).setMaxLevel(levelLeech.get());
        }
        if (MAGIC_EDGE instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) MAGIC_EDGE).setEnable(enableMagicEdge.get());
            ((EnchantmentCoFH) MAGIC_EDGE).setTreasure(treasureMagicEdge.get());
            ((EnchantmentCoFH) MAGIC_EDGE).setMaxLevel(levelMagicEdge.get());
        }
        if (VORPAL instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) VORPAL).setEnable(enableVorpal.get());
            ((EnchantmentCoFH) VORPAL).setTreasure(treasureVorpal.get());
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
            ((EnchantmentCoFH) EXCAVATING).setTreasure(treasureExcavating.get());
            ((EnchantmentCoFH) EXCAVATING).setMaxLevel(levelExcavating.get());
        }
        // BOWS
        if (HUNTER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) HUNTER).setEnable(enableHunter.get());
            ((EnchantmentCoFH) HUNTER).setTreasure(treasureHunter.get());
            ((EnchantmentCoFH) HUNTER).setMaxLevel(levelHunter.get());
            HunterEnchantment.chance = chanceHunter.get();
        }
        if (QUICK_DRAW instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) QUICK_DRAW).setEnable(enableQuickDraw.get());
            ((EnchantmentCoFH) QUICK_DRAW).setTreasure(treasureQuickDraw.get());
            ((EnchantmentCoFH) QUICK_DRAW).setMaxLevel(levelQuickDraw.get());
        }
        if (TRUESHOT instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) TRUESHOT).setEnable(enableTrueshot.get());
            ((EnchantmentCoFH) TRUESHOT).setTreasure(treasureTrueshot.get());
            ((EnchantmentCoFH) TRUESHOT).setMaxLevel(levelTrueshot.get());
        }
        if (VOLLEY instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) VOLLEY).setEnable(enableVolley.get());
            ((EnchantmentCoFH) VOLLEY).setTreasure(treasureVolley.get());
        }
        // FISHING RODS
        if (ANGLER instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) ANGLER).setEnable(enableAngler.get());
            ((EnchantmentCoFH) ANGLER).setTreasure(treasureAngler.get());
            ((EnchantmentCoFH) ANGLER).setMaxLevel(levelAngler.get());
            AnglerEnchantment.chance = chanceAngler.get();
        }
        if (PILFERING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PILFERING).setEnable(enablePilfering.get());
            ((EnchantmentCoFH) PILFERING).setTreasure(treasurePilfering.get());
            PilferingEnchantment.allowPlayerStealing = playerStealPilfering.get();
        }
        // HOES
        if (FURROWING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) FURROWING).setEnable(enableFurrowing.get());
            ((EnchantmentCoFH) FURROWING).setTreasure(treasureFurrowing.get());
            ((EnchantmentCoFH) FURROWING).setMaxLevel(levelFurrowing.get());
        }
        if (TILLING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) TILLING).setEnable(enableTilling.get());
            ((EnchantmentCoFH) TILLING).setTreasure(treasureTilling.get());
            ((EnchantmentCoFH) TILLING).setMaxLevel(levelTilling.get());
        }
        if (WEEDING instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) WEEDING).setEnable(enableWeeding.get());
            ((EnchantmentCoFH) WEEDING).setTreasure(treasureWeeding.get());
        }
        // SHIELDS
        if (BULWARK instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) BULWARK).setEnable(enableBulwark.get());
            ((EnchantmentCoFH) BULWARK).setTreasure(treasureBulwark.get());
        }
        if (PHALANX instanceof EnchantmentCoFH) {
            ((EnchantmentCoFH) PHALANX).setEnable(enablePhalanx.get());
            ((EnchantmentCoFH) PHALANX).setTreasure(treasurePhalanx.get());
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
            ((EnchantmentCoFH) SOULBOUND).setTreasure(treasureSoulbound.get());
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
            ((EnchantmentCoFH) FROST_WALKER).setTreasure(treasureFrostWalker.get());
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
            ((EnchantmentCoFH) MENDING).setTreasure(treasureMending.get());
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
    private static BooleanValue treasureProtectionMagic;
    private static IntValue levelProtectionMagic;

    private static BooleanValue enableDisplacement;
    private static BooleanValue treasureDisplacement;
    private static IntValue levelDisplacement;
    private static IntValue chanceDisplacement;
    private static BooleanValue allowMobsDisplacement;

    private static BooleanValue enableFireRebuke;
    private static BooleanValue treasureFireRebuke;
    private static IntValue levelFireRebuke;
    private static IntValue chanceFireRebuke;
    private static BooleanValue allowMobsFireRebuke;

    private static BooleanValue enableFrostRebuke;
    private static BooleanValue treasureFrostRebuke;
    private static IntValue levelFrostRebuke;
    private static IntValue chanceFrostRebuke;
    private static BooleanValue allowMobsFrostRebuke;

    // HELMET
    private static BooleanValue enableAirAffinity;
    private static BooleanValue treasureAirAffinity;

    private static BooleanValue enableExpBoost;
    private static BooleanValue treasureExpBoost;
    private static IntValue levelExpBoost;
    private static IntValue amountExpBoost;

    private static BooleanValue enableGourmand;
    private static BooleanValue treasureGourmand;
    private static IntValue levelGourmand;

    // CHESTPLATE
    private static BooleanValue enableReach;
    private static BooleanValue treasureReach;
    private static IntValue levelReach;

    private static BooleanValue enableVitality;
    private static BooleanValue treasureVitality;
    private static IntValue levelVitality;
    private static IntValue healthLevelVitality;

    // WEAPONS
    private static BooleanValue enableDamageEnder;
    private static BooleanValue treasureDamageEnder;
    private static IntValue levelDamageEnder;

    private static BooleanValue enableDamageIllager;
    private static BooleanValue treasureDamageIllager;
    private static IntValue levelDamageIllager;

    private static BooleanValue enableDamageVillager;
    private static BooleanValue treasureDamageVillager;
    private static IntValue levelDamageVillager;
    private static BooleanValue dropsDamageVillager;

    private static BooleanValue enableCavalier;
    private static BooleanValue treasureCavalier;
    private static IntValue levelCavalier;

    private static BooleanValue enableFrostAspect;
    private static BooleanValue treasureFrostAspect;
    private static IntValue levelFrostAspect;

    private static BooleanValue enableLeech;
    private static BooleanValue treasureLeech;
    private static IntValue levelLeech;

    private static BooleanValue enableMagicEdge;
    private static BooleanValue treasureMagicEdge;
    private static IntValue levelMagicEdge;

    private static BooleanValue enableVorpal;
    private static BooleanValue treasureVorpal;
    private static IntValue levelVorpal;
    private static IntValue critBaseVorpal;
    private static IntValue critLevelVorpal;
    private static IntValue critDamageVorpal;
    private static IntValue headBaseVorpal;
    private static IntValue headLevelVorpal;

    // TOOLS
    private static BooleanValue enableExcavating;
    private static BooleanValue treasureExcavating;
    private static IntValue levelExcavating;

    // BOWS
    private static BooleanValue enableHunter;
    private static BooleanValue treasureHunter;
    private static IntValue levelHunter;
    private static IntValue chanceHunter;

    private static BooleanValue enableQuickDraw;
    private static BooleanValue treasureQuickDraw;
    private static IntValue levelQuickDraw;

    private static BooleanValue enableTrueshot;
    private static BooleanValue treasureTrueshot;
    private static IntValue levelTrueshot;

    private static BooleanValue enableVolley;
    private static BooleanValue treasureVolley;

    // FISHING RODS
    private static BooleanValue enableAngler;
    private static BooleanValue treasureAngler;
    private static IntValue levelAngler;
    private static IntValue chanceAngler;

    private static BooleanValue enablePilfering;
    private static BooleanValue treasurePilfering;
    private static BooleanValue playerStealPilfering;

    // HOES
    private static BooleanValue enableFurrowing;
    private static BooleanValue treasureFurrowing;
    private static IntValue levelFurrowing;

    private static BooleanValue enableTilling;
    private static BooleanValue treasureTilling;
    private static IntValue levelTilling;

    private static BooleanValue enableWeeding;
    private static BooleanValue treasureWeeding;

    // SHIELDS
    private static BooleanValue enableBulwark;
    private static BooleanValue treasureBulwark;

    private static BooleanValue enablePhalanx;
    private static BooleanValue treasurePhalanx;
    private static IntValue levelPhalanx;

    // MISC
    // TODO: Revisit
    //    private static BooleanValue enableHolding;
    //    private static IntValue levelHolding;

    private static BooleanValue enableSoulbound;
    private static BooleanValue treasureSoulbound;
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
    private static BooleanValue treasureFrostWalker;
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
    private static BooleanValue treasureMending;
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
    public static void configReloading(final net.minecraftforge.fml.config.ModConfig.Reloading event) {

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
