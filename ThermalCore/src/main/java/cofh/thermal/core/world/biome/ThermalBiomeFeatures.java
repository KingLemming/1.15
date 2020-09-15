package cofh.thermal.core.world.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.common.ThermalFeatures.*;
import static cofh.thermal.core.init.TCoreIDs.*;
import static cofh.thermal.core.init.TCoreReferences.*;
import static cofh.thermal.core.world.ThermalWorld.*;

// TODO: This is a temporary class in general. CoFH World is getting a rewrite for 1.16.2.
public class ThermalBiomeFeatures {

    private ThermalBiomeFeatures() {

    }

    public static void addOverworldOres(Biome biomeIn) {

        if (getFeature(FLAG_RESOURCE_APATITE).getAsBoolean() && getFeature(FLAG_GEN_APATITE).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_APATITE_ORE).getDefaultState(), 13)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(4, 16, 16))));
        }
        if (getFeature(FLAG_RESOURCE_CINNABAR).getAsBoolean() && getFeature(FLAG_GEN_CINNABAR).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_CINNABAR_ORE).getDefaultState(), 5)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 16, 16))));
        }
        if (getFeature(FLAG_RESOURCE_NITER).getAsBoolean() && getFeature(FLAG_GEN_NITER).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_NITER_ORE).getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(2, 40, 12))));
        }
        if (getFeature(FLAG_RESOURCE_SULFUR).getAsBoolean() && getFeature(FLAG_GEN_SULFUR).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_SULFUR_ORE).getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(2, 24, 12))));
        }

        if (getFeature(FLAG_RESOURCE_COPPER).getAsBoolean() && getFeature(FLAG_GEN_COPPER).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_COPPER_ORE).getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 40, 0, 60))));
        }
        if (getFeature(FLAG_RESOURCE_TIN).getAsBoolean() && getFeature(FLAG_GEN_TIN).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_TIN_ORE).getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 20, 0, 40))));
        }
        if (getFeature(FLAG_RESOURCE_LEAD).getAsBoolean() && getFeature(FLAG_GEN_LEAD).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_LEAD_ORE).getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 32))));
        }
        if (getFeature(FLAG_RESOURCE_SILVER).getAsBoolean() && getFeature(FLAG_GEN_SILVER).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_SILVER_ORE).getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 32))));
        }
        if (getFeature(FLAG_RESOURCE_NICKEL).getAsBoolean() && getFeature(FLAG_GEN_NICKEL).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_NICKEL_ORE).getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 128))));
        }
    }

    public static void addHostileSpawns(Biome biomeIn) {

        if (biomeIn.getSpawns(EntityClassification.MONSTER).isEmpty()) {
            return;
        }
        Biome.Category category = biomeIn.getCategory();

        if (category == Biome.Category.EXTREME_HILLS || category == Biome.Category.MESA) {
            biomeIn.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(BASALZ_ENTITY, 10, 1, 3));
        }
        if (category == Biome.Category.DESERT || category == Biome.Category.MESA || category == Biome.Category.SAVANNA) {
            biomeIn.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(BLITZ_ENTITY, 10, 1, 3));
        }
        if (biomeIn.getPrecipitation() == Biome.RainType.SNOW & biomeIn.getTempCategory() == Biome.TempCategory.COLD) {
            biomeIn.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(BLIZZ_ENTITY, 10, 1, 3));
        }
    }

}
