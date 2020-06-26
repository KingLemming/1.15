package cofh.thermal.cultivation.init;

import cofh.lib.block.CakeBlockCoFH;
import cofh.lib.block.TileBlock4Way;
import cofh.lib.block.crops.StemBlockAttached;
import cofh.lib.block.crops.StemBlockCoFH;
import cofh.thermal.cultivation.block.FrostMelonBlock;
import cofh.thermal.cultivation.block.SoilBlock;
import cofh.thermal.cultivation.tileentity.DeviceHiveExtractorTile;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.util.RegistrationHelper.*;
import static cofh.thermal.cultivation.init.TCulReferences.*;

public class TCulBlocks {

    private TCulBlocks() {

    }

    public static void register() {

        registerPlants();
        registerFoods();

        registerBlock(ID_PHYTOSOIL, () -> new SoilBlock(Block.Properties.create(Material.EARTH).tickRandomly().hardnessAndResistance(0.8F).sound(SoundType.GROUND)));
        registerBlockOnly(ID_PHYTOSOIL_CHARGED, () -> new SoilBlock(Block.Properties.create(Material.EARTH).tickRandomly().hardnessAndResistance(0.8F).sound(SoundType.GROUND).lightValue(7)));

        registerBlock(ID_DEVICE_HIVE_EXTRACTOR, () -> new TileBlock4Way(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F), DeviceHiveExtractorTile::new));
    }

    public static void setup() {

        PHYTOSOIL_BLOCK.setBoost(2);
        PHYTOSOIL_CHARGED_BLOCK.setBoost(4);
    }

    private static void registerPlants() {

        // ANNUAL
        registerAnnual(ID_BARLEY);
        registerAnnual(ID_ONION);
        registerAnnual(ID_RADISH);
        registerAnnual(ID_RICE);
        registerAnnual(ID_SADIROOT);
        registerAnnual(ID_SPINACH);

        // PERENNIAL
        registerPerennial(ID_BELL_PEPPER);
        registerPerennial(ID_EGGPLANT);
        registerPerennial(ID_GREEN_BEAN);
        registerPerennial(ID_PEANUT);
        registerPerennial(ID_STRAWBERRY);
        registerPerennial(ID_TOMATO);

        // BREWING
        registerPerennial(ID_COFFEE);
        registerPerennial(ID_TEA);

        // STEM
        registerBlock(ID_FROST_MELON, () -> new FrostMelonBlock(Block.Properties.create(Material.GOURD, MaterialColor.CYAN).hardnessAndResistance(1.0F).sound(SoundType.SNOW).tickRandomly()), Rarity.UNCOMMON);
        registerBlockOnly(ID_FROST_MELON_STEM, () -> new StemBlockCoFH(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WOOD)).crop(BLOCKS.getSup(ID_FROST_MELON)).seed(ITEMS.getSup(seeds(ID_FROST_MELON))));
        registerBlockOnly(ID_FROST_MELON_STEM_ATTACHED, () -> new StemBlockAttached(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.STEM)).crop(BLOCKS.getSup(ID_FROST_MELON)).seed(ITEMS.getSup(seeds(ID_FROST_MELON))));
    }

    private static void registerFoods() {

        registerBlock(ID_SPICE_CAKE, () -> new CakeBlockCoFH(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH), TCulFoods.SPICE_CAKE));
    }

}
