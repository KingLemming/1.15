package cofh.thermal.core.world;

import cofh.thermal.core.world.biome.ThermalBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

// TODO: This is a temporary class in general. CoFH World is getting a rewrite for 1.16.2.
public class ThermalWorld {

    private ThermalWorld() {

    }

    public static void setup() {

        ForgeRegistries.BIOMES.forEach(biome -> {
            if (isOverworldBiome(biome)) {
                ThermalBiomeFeatures.addOverworldOres(biome);
                ThermalBiomeFeatures.addHostileSpawns(biome);
            }
        });
    }

    private static boolean isOverworldBiome(Biome biome) {

        Biome.Category category = biome.getCategory();
        return category != Biome.Category.NONE && category != Biome.Category.THEEND && category != Biome.Category.NETHER;
    }

}
