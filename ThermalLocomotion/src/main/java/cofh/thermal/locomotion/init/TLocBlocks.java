package cofh.thermal.locomotion.init;

import cofh.lib.block.rails.RailBlockCoFH;
import cofh.lib.block.rails.RailBlockCrossover;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.init.ThermalReferences.ID_LUMIUM_RAIL;
import static cofh.thermal.core.init.ThermalReferences.ID_LUMIUM_RAIL_CROSSOVER;
import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static net.minecraft.block.Block.Properties.create;

public class TLocBlocks {

    private TLocBlocks() {

    }

    public static void register() {

        registerBlock(ID_LUMIUM_RAIL, () -> new RailBlockCoFH(create(Material.IRON).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(15)), Rarity.UNCOMMON);
        registerBlock(ID_LUMIUM_RAIL_CROSSOVER, () -> new RailBlockCrossover(create(Material.IRON).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(15)), Rarity.UNCOMMON);
    }

}
