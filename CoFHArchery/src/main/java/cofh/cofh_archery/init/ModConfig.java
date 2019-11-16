package cofh.cofh_archery.init;

import cofh.cofh_archery.entity.projectile.BlazeArrowEntity;
import cofh.cofh_archery.entity.projectile.ExplosiveArrowEntity;
import cofh.cofh_archery.entity.projectile.FrostArrowEntity;
import cofh.cofh_archery.entity.projectile.SlimeArrowEntity;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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

        genArrowConfig();

        serverSpec = SERVER_CONFIG.build();
    }

    private static void genClientConfig() {

        clientSpec = CLIENT_CONFIG.build();
    }

    private static void genArrowConfig() {

        String comment;
        SERVER_CONFIG.push("Arrows");

        SERVER_CONFIG.push("Blaze");
        comment = "Adjust this to set the effect radius for the Blaze Arrow. Set to 0 to disable, but that would be boring.";
        blazeArrowRadius = SERVER_CONFIG.comment(comment).defineInRange("Radius", BlazeArrowEntity.radius, 0, 16);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Explosive");
        comment = "Adjust this to set the explosion strength for the Explosive Arrow.";
        explosiveArrowStrength = SERVER_CONFIG.comment(comment).defineInRange("Strength", ExplosiveArrowEntity.explosionStrength, 0.5D, 16.0D);
        comment = "If TRUE, explosions break blocks.";
        explosiveArrowBreakBlocks = SERVER_CONFIG.comment(comment).define("Break Blocks", ExplosiveArrowEntity.explosionsBreakBlocks);
        comment = "If TRUE, explosions cause fires if the arrow is on fire.";
        explosiveArrowCauseFire = SERVER_CONFIG.comment(comment).define("Cause Fires", ExplosiveArrowEntity.explosionsCauseFire);
        comment = "If TRUE, explosion strength is modified by knockback bonuses, such as the Punch Enchantment.";
        explosiveArrowKnockbackBoost = SERVER_CONFIG.comment(comment).define("Knockback Boost", ExplosiveArrowEntity.knockbackBoost);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Frost");
        comment = "Adjust this to set the effect radius for the Frost Arrow. Set to 0 to disable, but that would be boring.";
        frostArrowRadius = SERVER_CONFIG.comment(comment).defineInRange("Radius", FrostArrowEntity.radius, 0, 16);
        comment = "If TRUE, Frost Arrows will convert Lava into Obsidian. If FALSE, Glossed Magma.";
        frostArrowPermanentLava = SERVER_CONFIG.comment(comment).define("Permanent Lava Freeze", FrostArrowEntity.permanentLava);
        comment = "If TRUE, Frost Arrows will convert Water into Ice. If FALSE, Frosted Ice.";
        frostArrowPermanentWater = SERVER_CONFIG.comment(comment).define("Permanent Water Freeze", FrostArrowEntity.permanentWater);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Slime");
        comment = "Adjust this to set the number of bounces for the Slime Arrow.";
        slimeArrowBounces = SERVER_CONFIG.comment(comment).defineInRange("Bounces", SlimeArrowEntity.bounces, 1, 16);
        comment = "Adjust this to set the knockback strength for the Slime Arrow.";
        slimeArrowKnockback = SERVER_CONFIG.comment(comment).defineInRange("Knockback", SlimeArrowEntity.knockback, 0, 16);
        SERVER_CONFIG.pop();
    }

    private static void refreshServerConfig() {

        refreshArrowConfig();
    }

    private static void refreshClientConfig() {

    }

    private static void refreshArrowConfig() {

        BlazeArrowEntity.radius = blazeArrowRadius.get();

        ExplosiveArrowEntity.explosionStrength = explosiveArrowStrength.get();
        ExplosiveArrowEntity.explosionsBreakBlocks = explosiveArrowBreakBlocks.get();
        ExplosiveArrowEntity.explosionsCauseFire = explosiveArrowCauseFire.get();
        ExplosiveArrowEntity.knockbackBoost = explosiveArrowKnockbackBoost.get();

        FrostArrowEntity.radius = frostArrowRadius.get();
        FrostArrowEntity.permanentLava = frostArrowPermanentLava.get();
        FrostArrowEntity.permanentWater = frostArrowPermanentWater.get();

        SlimeArrowEntity.bounces = slimeArrowBounces.get();
        SlimeArrowEntity.knockback = slimeArrowKnockback.get();
    }
    // endregion

    // region VARIABLES
    private static IntValue blazeArrowRadius;

    private static DoubleValue explosiveArrowStrength;
    private static BooleanValue explosiveArrowBreakBlocks;
    private static BooleanValue explosiveArrowCauseFire;
    private static BooleanValue explosiveArrowKnockbackBoost;

    private static IntValue frostArrowRadius;
    private static BooleanValue frostArrowPermanentLava;
    private static BooleanValue frostArrowPermanentWater;

    private static IntValue slimeArrowBounces;
    private static IntValue slimeArrowKnockback;
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
