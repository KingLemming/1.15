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

// TODO: This is a temporary class in general. CoFH World is getting a rewrite for 1.16.2.
public class ThermalBiomeFeatures {

    private ThermalBiomeFeatures() {

    }

    public static void addOverworldOres(Biome biomeIn) {

        if (getFeature(FLAG_RESOURCE_APATITE).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_APATITE_ORE).getDefaultState(), 13)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(4, 16, 16))));
        }
        if (getFeature(FLAG_RESOURCE_CINNABAR).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_CINNABAR_ORE).getDefaultState(), 5)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(1, 16, 16))));
        }
        if (getFeature(FLAG_RESOURCE_NITER).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_NITER_ORE).getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(2, 40, 12))));
        }
        if (getFeature(FLAG_RESOURCE_SULFUR).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_SULFUR_ORE).getDefaultState(), 7)).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(2, 24, 12))));
        }

        if (getFeature(FLAG_RESOURCE_COPPER).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_COPPER_ORE).getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 40, 0, 60))));
        }
        if (getFeature(FLAG_RESOURCE_TIN).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_TIN_ORE).getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 20, 0, 40))));
        }
        if (getFeature(FLAG_RESOURCE_LEAD).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_LEAD_ORE).getDefaultState(), 8)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 32))));
        }
        if (getFeature(FLAG_RESOURCE_SILVER).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_SILVER_ORE).getDefaultState(), 9)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 32))));
        }
        if (getFeature(FLAG_RESOURCE_NICKEL).getAsBoolean()) {
            biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BLOCKS.get(ID_NICKEL_ORE).getDefaultState(), 7)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 0, 0, 128))));
        }
    }

    public static void addHostileSpawns(Biome biomeIn) {

        if (biomeIn.getSpawns(EntityClassification.MONSTER).isEmpty()) {
            return;
        }
        Biome.Category category = biomeIn.getCategory();

        if (getFeature(FLAG_MOB_BASALZ).getAsBoolean()) {
            if (category == Biome.Category.EXTREME_HILLS || category == Biome.Category.MESA) {
                biomeIn.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(BASALZ_ENTITY, 10, 2, 3));
            }
        }
        if (getFeature(FLAG_MOB_BLITZ).getAsBoolean()) {
            if (category == Biome.Category.DESERT || category == Biome.Category.MESA || category == Biome.Category.SAVANNA) {
                biomeIn.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(BLITZ_ENTITY, 10, 2, 3));
            }
        }
        if (getFeature(FLAG_MOB_BLIZZ).getAsBoolean()) {
            if (biomeIn.getPrecipitation() == Biome.RainType.SNOW) {
                biomeIn.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(BLIZZ_ENTITY, 10, 2, 3));
            }
        }
    }

}
