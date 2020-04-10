package cofh.thermal.cultivation.init;

import cofh.lib.block.crops.StemBlockAttached;
import cofh.lib.block.crops.StemBlockCoFH;
import cofh.thermal.cultivation.block.FrostMelonBlock;
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

        registerBlock(ID_FROST_MELON, () -> new FrostMelonBlock(Block.Properties.create(Material.GOURD, MaterialColor.CYAN).hardnessAndResistance(1.0F).sound(SoundType.SNOW).tickRandomly()), Rarity.UNCOMMON);
        registerBlockOnly(ID_FROST_MELON_STEM, () -> new StemBlockCoFH(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WOOD)).crop(BLOCKS.getSup(ID_FROST_MELON)).seed(ITEMS.getSup(seeds(ID_FROST_MELON))));
        registerBlockOnly(ID_FROST_MELON_STEM_ATTACHED, () -> new StemBlockAttached(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.STEM)).crop(BLOCKS.getSup(ID_FROST_MELON)).seed(ITEMS.getSup(seeds(ID_FROST_MELON))));
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
    }

    //    public static final Block MELON = register("melon", new MelonBlock(Block.Properties.create(Material.GOURD, MaterialColor.LIME).hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
    //    public static final Block ATTACHED_PUMPKIN_STEM = register("attached_pumpkin_stem", new AttachedStemBlock((StemGrownBlock)PUMPKIN, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WOOD)));
    //    public static final Block ATTACHED_MELON_STEM = register("attached_melon_stem", new AttachedStemBlock((StemGrownBlock)MELON, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WOOD)));
    //    public static final Block PUMPKIN_STEM = register("pumpkin_stem", new StemBlock((StemGrownBlock)PUMPKIN, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.STEM)));
    //    public static final Block MELON_STEM = register("melon_stem", new StemBlock((StemGrownBlock)MELON, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.STEM)));

}
