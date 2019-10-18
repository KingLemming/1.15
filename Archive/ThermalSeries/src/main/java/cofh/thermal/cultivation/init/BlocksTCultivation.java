package cofh.thermal.cultivation.init;

import cofh.lib.block.crops.CropsBlockCoFH;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;

import static cofh.thermal.core.ThermalSeries.BLOCKS;
import static cofh.thermal.cultivation.init.ItemsTCultivation.cropBarley;
import static cofh.thermal.cultivation.init.ItemsTCultivation.seedBarley;
import static net.minecraft.block.Block.Properties.create;

public class BlocksTCultivation {

    private BlocksTCultivation() {

    }

    public static void register() {

        plantBarley = BLOCKS.register("plant_barley", () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).setCrop(cropBarley).setSeed(seedBarley));
        plantOnion = BLOCKS.register("plant_onion", () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)));
        plantRice = BLOCKS.register("plant_rice", () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)));
        plantSadiroot = BLOCKS.register("plant_sadiroot", () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)));
        plantSpinach = BLOCKS.register("plant_spinach", () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)));
    }

    public static RegistryObject<Block> plantBarley;
    public static RegistryObject<Block> plantOnion;
    public static RegistryObject<Block> plantRice;
    public static RegistryObject<Block> plantSadiroot;
    public static RegistryObject<Block> plantSpinach;

}
