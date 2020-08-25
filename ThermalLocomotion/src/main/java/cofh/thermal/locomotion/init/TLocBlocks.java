package cofh.thermal.locomotion.init;

import cofh.lib.block.rails.*;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static cofh.thermal.locomotion.init.TLocIDs.*;
import static net.minecraft.block.Block.Properties.create;

public class TLocBlocks {

    private TLocBlocks() {

    }

    public static void register() {

        registerBlock(ID_CROSSOVER_RAIL, () -> new CrossoverRailBlock(create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL)));

        registerBlock(ID_PRISMARINE_RAIL, () -> new RailBlockWL(create(Material.IRON, MaterialColor.AIR).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(8)));
        registerBlock(ID_PRISMARINE_CROSSOVER_RAIL, () -> new CrossoverRailBlockWL(create(Material.IRON, MaterialColor.AIR).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(8)));
        registerBlock(ID_PRISMARINE_POWERED_RAIL, () -> new PoweredRailBlockWL(create(Material.IRON, MaterialColor.AIR).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(8), true));
        registerBlock(ID_PRISMARINE_ACTIVATOR_RAIL, () -> new PoweredRailBlockWL(create(Material.IRON, MaterialColor.AIR).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(8)));
        registerBlock(ID_PRISMARINE_DETECTOR_RAIL, () -> new DetectorRailBlockWL(create(Material.IRON, MaterialColor.AIR).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(8)));

        registerBlock(ID_LUMIUM_RAIL, () -> new RailBlockCoFH(create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(15)), Rarity.UNCOMMON);
        registerBlock(ID_LUMIUM_CROSSOVER_RAIL, () -> new CrossoverRailBlock(create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(15)), Rarity.UNCOMMON);
        registerBlock(ID_LUMIUM_POWERED_RAIL, () -> new PoweredRailBlockCoFH(create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(15), true), Rarity.UNCOMMON);
        registerBlock(ID_LUMIUM_ACTIVATOR_RAIL, () -> new PoweredRailBlockCoFH(create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(15)), Rarity.UNCOMMON);
        registerBlock(ID_LUMIUM_DETECTOR_RAIL, () -> new DetectorRailBlockCoFH(create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(15)), Rarity.UNCOMMON);
    }

}
