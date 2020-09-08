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

    // TODO Temporary Generation Flags
    public static String FLAG_GEN_APATITE = "gen_apatite";
    public static String FLAG_GEN_CINNABAR = "gen_cinnabar";
    public static String FLAG_GEN_NITER = "gen_niter";
    public static String FLAG_GEN_SULFUR = "gen_sulfur";

    public static String FLAG_GEN_COPPER = "gen_copper";
    public static String FLAG_GEN_TIN = "gen_tin";
    public static String FLAG_GEN_LEAD = "gen_lead";
    public static String FLAG_GEN_SILVER = "gen_silver";
    public static String FLAG_GEN_NICKEL = "gen_nickel";

    public static String FLAG_GEN_RUBY = "gen_ruby";
    public static String FLAG_GEN_SAPPHIRE = "gen_sapphire";

}
