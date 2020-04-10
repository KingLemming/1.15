package cofh.thermal.expansion.init;

import cofh.lib.block.TileBlock4Way;
import cofh.lib.block.TileBlock6Way;
import cofh.thermal.expansion.tileentity.dynamo.DynamoLapidaryTile;
import cofh.thermal.expansion.tileentity.dynamo.DynamoNumismaticTile;
import cofh.thermal.expansion.tileentity.dynamo.DynamoStirlingTile;
import cofh.thermal.expansion.tileentity.machine.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static cofh.thermal.expansion.init.TExpReferences.*;

public class TExpBlocks {

    private TExpBlocks() {

    }

    public static void register() {

        registerBlock(ID_MACHINE_FURNACE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineFurnaceTile::new));
        registerBlock(ID_MACHINE_SAWMILL, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineSawmillTile::new));
        registerBlock(ID_MACHINE_PULVERIZER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachinePulverizerTile::new));
        registerBlock(ID_MACHINE_INSOLATOR, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(15), MachineInsolatorTile::new));
        registerBlock(ID_MACHINE_CENTRIFUGE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineCentrifugeTile::new));
        registerBlock(ID_MACHINE_PRESS, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachinePressTile::new));
        registerBlock(ID_MACHINE_CRUCIBLE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineCrucibleTile::new));
        registerBlock(ID_MACHINE_CHILLER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(1), MachineChillerTile::new));
        registerBlock(ID_MACHINE_REFINERY, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(12), MachineRefineryTile::new));
        registerBlock(ID_MACHINE_BREWER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(1), MachineBrewerTile::new));

        registerBlock(ID_DYNAMO_STIRLING, () -> new TileBlock6Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoStirlingTile::new));
        //        registerBlock(ID_DYNAMO_COMPRESSION, () -> new TileBlock6Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoCompressionTile::new));
        //        registerBlock(ID_DYNAMO_MAGMATIC, () -> new TileBlock6Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoMagmaticTile::new));
        registerBlock(ID_DYNAMO_NUMISMATIC, () -> new TileBlock6Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoNumismaticTile::new));
        registerBlock(ID_DYNAMO_LAPIDARY, () -> new TileBlock6Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoLapidaryTile::new));
    }

}
