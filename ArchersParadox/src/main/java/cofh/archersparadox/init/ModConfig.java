package cofh.archersparadox.init;

import cofh.archersparadox.entity.projectile.*;
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
        comment = "Adjust this to set the burn duration for the Blaze Arrow (in seconds). Nearby targets will burn for 5 seconds less than a direct target.";
        blazeArrowDuration = SERVER_CONFIG.comment(comment).defineInRange("Burn Duration", BlazeArrowEntity.effectDuration, 5, 30);
        comment = "Adjust this to set the effect radius for the Blaze Arrow. Set to 0 to disable, but that would be boring.";
        blazeArrowRadius = SERVER_CONFIG.comment(comment).defineInRange("Radius", BlazeArrowEntity.effectRadius, 0, 16);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Diamond");
        comment = "Adjust this to set the damage for the Diamond Arrow. Base Arrow value is 2.0.";
        diamondArrowDamage = SERVER_CONFIG.comment(comment).defineInRange("Damage", DiamondArrowEntity.baseDamage, 0.5, 16.0);
        comment = "Adjust this to set the inherent knockback strength of the Diamond Arrow. Base Arrow value is 0.";
        diamondArrowKnockback = SERVER_CONFIG.comment(comment).defineInRange("Knockback", DiamondArrowEntity.baseKnockback, 0, 16);
        comment = "Adjust this to set the inherent pierce of the Diamond Arrow. Base Arrow value is 0.";
        diamondArrowPierce = SERVER_CONFIG.comment(comment).defineInRange("Piercing", DiamondArrowEntity.basePierce, 0, 16);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Displacement");
        comment = "Adjust this to set the effect radius for the Displacement Arrow. Set to 0 to disable, but that would be boring (and make the arrow useless).";
        displacementArrowRadius = SERVER_CONFIG.comment(comment).defineInRange("Radius", DisplacementArrowEntity.effectRadius, 0, 16);
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
        frostArrowRadius = SERVER_CONFIG.comment(comment).defineInRange("Radius", FrostArrowEntity.effectRadius, 0, 16);
        comment = "If TRUE, Frost Arrows will convert Lava into Obsidian. If FALSE, Glossed Magma.";
        frostArrowPermanentLava = SERVER_CONFIG.comment(comment).define("Permanent Lava Freeze", FrostArrowEntity.permanentLava);
        comment = "If TRUE, Frost Arrows will convert Water into Ice. If FALSE, Frosted Ice.";
        frostArrowPermanentWater = SERVER_CONFIG.comment(comment).define("Permanent Water Freeze", FrostArrowEntity.permanentWater);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Glowstone");
        comment = "Adjust this to set the effect radius for the Glowstone Arrow. Set to 0 to disable, but that would be boring (and make the arrow useless).";
        glowstoneArrowRadius = SERVER_CONFIG.comment(comment).defineInRange("Radius", GlowstoneArrowEntity.effectRadius, 0, 16);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Prismarine");
        comment = "Adjust this to set the damage for the Prismarine Arrow. Base Arrow value is 2.0.";
        prismarineArrowDamage = SERVER_CONFIG.comment(comment).defineInRange("Damage", PrismarineArrowEntity.baseDamage, 0.5, 16.0);
        comment = "Adjust this to set the inherent knockback strength of the Prismarine Arrow. Base Arrow value is 0.";
        prismarineArrowKnockback = SERVER_CONFIG.comment(comment).defineInRange("Knockback", PrismarineArrowEntity.baseKnockback, 0, 16);
        comment = "Adjust this to set the inherent pierce of the Prismarine Arrow. Base Arrow value is 0.";
        prismarineArrowPierce = SERVER_CONFIG.comment(comment).defineInRange("Piercing", PrismarineArrowEntity.basePierce, 0, 16);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Quartz");
        comment = "Adjust this to set the damage for the Quartz Arrow. Base Arrow value is 2.0.";
        quartzArrowDamage = SERVER_CONFIG.comment(comment).defineInRange("Damage", QuartzArrowEntity.baseDamage, 0.5, 16.0);
        comment = "Adjust this to set the inherent knockback strength of the Quartz Arrow. Base Arrow value is 0.";
        quartzArrowKnockback = SERVER_CONFIG.comment(comment).defineInRange("Knockback", QuartzArrowEntity.baseKnockback, 0, 16);
        comment = "Adjust this to set the inherent pierce of the Quartz Arrow. Base Arrow value is 0.";
        quartzArrowPierce = SERVER_CONFIG.comment(comment).defineInRange("Piercing", QuartzArrowEntity.basePierce, 0, 16);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Redstone");
        comment = "Adjust this to set the effect radius for the Redstone Arrow. Set to 0 to disable, but that would be boring (and make the arrow useless).";
        redstoneArrowRadius = SERVER_CONFIG.comment(comment).defineInRange("Radius", RedstoneArrowEntity.effectRadius, 0, 16);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Shulker");
        comment = "Adjust this to set the effect duration (Levitation) for the Shulker Arrow. Set to 0 to disable.";
        shulkerArrowDuration = SERVER_CONFIG.comment(comment).defineInRange("Effect Duration", ShulkerArrowEntity.effectDuration, 0, 9600);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Slime");
        comment = "Adjust this to set the number of bounces for the Slime Arrow.";
        slimeArrowBounces = SERVER_CONFIG.comment(comment).defineInRange("Bounces", SlimeArrowEntity.baseBounces, 1, 16);
        comment = "Adjust this to set the inherent knockback strength of the Slime Arrow. Base Arrow value is 0.";
        slimeArrowKnockback = SERVER_CONFIG.comment(comment).defineInRange("Knockback", SlimeArrowEntity.baseKnockback, 0, 16);
        comment = "If TRUE, bounces are modified by knockback bonuses, such as the Punch Enchantment.";
        slimeArrowKnockbackBoost = SERVER_CONFIG.comment(comment).define("Knockback Boost", SlimeArrowEntity.knockbackBoost);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Spore");
        comment = "Adjust this to set the effect radius for the Spore Arrow. Set to 0 to disable, but that would be boring (and make the arrow useless).";
        sporeArrowRadius = SERVER_CONFIG.comment(comment).defineInRange("Radius", SporeArrowEntity.effectRadius, 0, 16);
        SERVER_CONFIG.pop();

        SERVER_CONFIG.push("Verdant");
        comment = "Adjust this to set the effect radius for the Verdant Arrow. Set to 0 to disable, but that would be boring (and make the arrow useless).";
        verdantArrowRadius = SERVER_CONFIG.comment(comment).defineInRange("Radius", VerdantArrowEntity.effectRadius, 0, 16);
        SERVER_CONFIG.pop();
    }

    private static void refreshServerConfig() {

        refreshArrowConfig();
    }

    private static void refreshClientConfig() {

    }

    private static void refreshArrowConfig() {

        BlazeArrowEntity.effectDuration = blazeArrowDuration.get();
        BlazeArrowEntity.effectRadius = blazeArrowRadius.get();

        DiamondArrowEntity.baseDamage = diamondArrowDamage.get().floatValue();
        DiamondArrowEntity.baseKnockback = diamondArrowKnockback.get();
        DiamondArrowEntity.basePierce = diamondArrowPierce.get().byteValue();

        DisplacementArrowEntity.effectRadius = displacementArrowRadius.get();

        ExplosiveArrowEntity.explosionStrength = explosiveArrowStrength.get();
        ExplosiveArrowEntity.explosionsBreakBlocks = explosiveArrowBreakBlocks.get();
        ExplosiveArrowEntity.explosionsCauseFire = explosiveArrowCauseFire.get();
        ExplosiveArrowEntity.knockbackBoost = explosiveArrowKnockbackBoost.get();

        FrostArrowEntity.effectRadius = frostArrowRadius.get();
        FrostArrowEntity.permanentLava = frostArrowPermanentLava.get();
        FrostArrowEntity.permanentWater = frostArrowPermanentWater.get();

        GlowstoneArrowEntity.effectRadius = glowstoneArrowRadius.get();

        PrismarineArrowEntity.baseDamage = prismarineArrowDamage.get().floatValue();
        PrismarineArrowEntity.baseKnockback = prismarineArrowKnockback.get();
        PrismarineArrowEntity.basePierce = prismarineArrowPierce.get().byteValue();

        QuartzArrowEntity.baseDamage = quartzArrowDamage.get().floatValue();
        QuartzArrowEntity.baseKnockback = quartzArrowKnockback.get();
        QuartzArrowEntity.basePierce = quartzArrowPierce.get().byteValue();

        RedstoneArrowEntity.effectRadius = redstoneArrowRadius.get();

        ShulkerArrowEntity.effectDuration = shulkerArrowDuration.get();

        SlimeArrowEntity.baseBounces = slimeArrowBounces.get();
        SlimeArrowEntity.baseKnockback = slimeArrowKnockback.get();
        SlimeArrowEntity.knockbackBoost = slimeArrowKnockbackBoost.get();

        SporeArrowEntity.effectRadius = sporeArrowRadius.get();

        VerdantArrowEntity.effectRadius = verdantArrowRadius.get();
    }
    // endregion

    // region VARIABLES
    private static IntValue blazeArrowDuration;
    private static IntValue blazeArrowRadius;

    private static DoubleValue diamondArrowDamage;
    private static IntValue diamondArrowKnockback;
    private static IntValue diamondArrowPierce;

    private static IntValue displacementArrowRadius;

    private static DoubleValue explosiveArrowStrength;
    private static BooleanValue explosiveArrowBreakBlocks;
    private static BooleanValue explosiveArrowCauseFire;
    private static BooleanValue explosiveArrowKnockbackBoost;

    private static IntValue frostArrowRadius;
    private static BooleanValue frostArrowPermanentLava;
    private static BooleanValue frostArrowPermanentWater;

    private static IntValue glowstoneArrowRadius;

    private static DoubleValue prismarineArrowDamage;
    private static IntValue prismarineArrowKnockback;
    private static IntValue prismarineArrowPierce;

    private static DoubleValue quartzArrowDamage;
    private static IntValue quartzArrowKnockback;
    private static IntValue quartzArrowPierce;

    private static IntValue redstoneArrowRadius;

    private static IntValue slimeArrowBounces;
    private static IntValue slimeArrowKnockback;
    private static BooleanValue slimeArrowKnockbackBoost;

    private static IntValue shulkerArrowDuration;

    private static IntValue sporeArrowRadius;

    private static IntValue verdantArrowRadius;
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
