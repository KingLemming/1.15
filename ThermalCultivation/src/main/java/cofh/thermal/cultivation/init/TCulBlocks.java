package cofh.thermal.cultivation.init;

import cofh.lib.block.crops.CropsBlockCoFH;
import cofh.lib.block.crops.CropsBlockPerennial;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import static cofh.thermal.cultivation.ThermalCultivation.BLOCKS;
import static cofh.thermal.cultivation.init.TCulItems.*;
import static cofh.thermal.cultivation.init.TCulReferences.*;
import static net.minecraft.block.Block.Properties.create;

public class TCulBlocks {

    private TCulBlocks() {

    }

    public static void register() {

        registerPlants();
    }

    private static void registerPlants() {

        // ANNUAL
        BLOCKS.register(ID_BARLEY, () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropBarley).seed(seedBarley));
        BLOCKS.register(ID_ONION, () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropOnion).seed(seedOnion));
        BLOCKS.register(ID_RICE, () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropRice).seed(seedRice));
        BLOCKS.register(ID_SADIROOT, () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropSadiroot).seed(seedSadiroot));
        BLOCKS.register(ID_SPINACH, () -> new CropsBlockCoFH(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropSpinach).seed(seedSpinach));

        // PERENNIAL
        BLOCKS.register(ID_BELL_PEPPER, () -> new CropsBlockPerennial(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropBellPepper).seed(seedBellPepper));
        BLOCKS.register(ID_GREEN_BEAN, () -> new CropsBlockPerennial(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropGreenBean).seed(seedGreenBean));
        BLOCKS.register(ID_PEANUT, () -> new CropsBlockPerennial(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropPeanut).seed(seedPeanut));
        BLOCKS.register(ID_STRAWBERRY, () -> new CropsBlockPerennial(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropStrawberry).seed(seedStrawberry));
        BLOCKS.register(ID_TOMATO, () -> new CropsBlockPerennial(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropTomato).seed(seedTomato));

        // BREWING
        BLOCKS.register(ID_COFFEE, () -> new CropsBlockPerennial(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropCoffee).seed(seedCoffee));
        BLOCKS.register(ID_TEA, () -> new CropsBlockPerennial(create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F, 0.0F).sound(SoundType.CROP)).crop(cropTea).seed(seedTea));
    }

}
