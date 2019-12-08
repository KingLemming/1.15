package cofh.thermal.locomotion.init;

import cofh.lib.block.rails.RailBlockCoFH;
import cofh.lib.block.rails.RailBlockCrossover;
import cofh.lib.item.BlockItemCoFH;
import cofh.thermal.core.init.ThermalItemGroups;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.locomotion.ThermalLocomotion.BLOCKS;
import static cofh.thermal.locomotion.ThermalLocomotion.ITEMS;
import static net.minecraft.block.Block.Properties.create;

public class TLocBlocks {

    private TLocBlocks() {

    }

    public static void register() {

        BLOCKS.register(ID_LUMIUM_RAIL, () -> new RailBlockCoFH(create(Material.IRON).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(15)));
        BLOCKS.register(ID_LUMIUM_RAIL_CROSSOVER, () -> new RailBlockCrossover(create(Material.IRON).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL).lightValue(15)));

        ItemGroup group = ThermalItemGroups.THERMAL_BLOCKS;

        ITEMS.register(ID_LUMIUM_RAIL, () -> new BlockItemCoFH(LUMIUM_RAIL, new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register(ID_LUMIUM_RAIL_CROSSOVER, () -> new BlockItemCoFH(LUMIUM_RAIL_CROSSOVER, new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
    }

}
