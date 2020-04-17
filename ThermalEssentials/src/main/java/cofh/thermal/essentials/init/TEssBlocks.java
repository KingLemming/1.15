package cofh.thermal.essentials.init;

import cofh.lib.block.TileBlock4Way;
import cofh.thermal.essentials.tileentity.BasicPulverizerTile;
import cofh.thermal.essentials.tileentity.BasicSawmillTile;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static cofh.thermal.essentials.init.TEssReferences.ID_BASIC_PULVERIZER;
import static cofh.thermal.essentials.init.TEssReferences.ID_BASIC_SAWMILL;

public class TEssBlocks {

    private TEssBlocks() {

    }

    public static void register() {

        //        registerBlock(ID_MACHINE_FURNACE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineFurnaceTile::new));
        registerBlock(ID_BASIC_SAWMILL, () -> new TileBlock4Way(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.5F).lightValue(0), BasicSawmillTile::new));
        registerBlock(ID_BASIC_PULVERIZER, () -> new TileBlock4Way(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.5F).lightValue(0), BasicPulverizerTile::new));
        //        registerBlock(ID_MACHINE_INSOLATOR, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(15), MachineInsolatorTile::new));
        //        registerBlock(ID_MACHINE_CENTRIFUGE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineCentrifugeTile::new));
        //        registerBlock(ID_MACHINE_PRESS, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachinePressTile::new));
        //        registerBlock(ID_MACHINE_CRUCIBLE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineCrucibleTile::new));
        //        registerBlock(ID_MACHINE_CHILLER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(1), MachineChillerTile::new));
        //        registerBlock(ID_MACHINE_REFINERY, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(12), MachineRefineryTile::new));
        //        registerBlock(ID_MACHINE_BREWER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(1), MachineBrewerTile::new));
        //        registerBlock(ID_MACHINE_BOTTLER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(1), MachineBottlerTile::new));
    }

}
