package cofh.thermal.expansion.init;

import cofh.thermal.expansion.inventory.container.dynamo.*;
import cofh.thermal.expansion.inventory.container.machine.*;
import cofh.thermal.expansion.tileentity.device.DeviceRockGenTile;
import cofh.thermal.expansion.tileentity.device.DeviceWaterGenTile;
import cofh.thermal.expansion.tileentity.dynamo.*;
import cofh.thermal.expansion.tileentity.machine.*;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TExpReferences {

    private TExpReferences() {

    }

    // region DEVICES
    public static final String ID_DEVICE_ROCK_GEN = ID_THERMAL + ":device_rock_gen";
    public static final String ID_DEVICE_WATER_GEN = ID_THERMAL + ":device_water_gen";

    @ObjectHolder(ID_DEVICE_ROCK_GEN)
    public static final Block DEVICE_ROCK_GEN_BLOCK = null;
    @ObjectHolder(ID_DEVICE_ROCK_GEN)
    public static final TileEntityType<DeviceRockGenTile> DEVICE_ROCK_GEN_TILE = null;

    @ObjectHolder(ID_DEVICE_WATER_GEN)
    public static final Block DEVICE_WATER_GEN_BLOCK = null;
    @ObjectHolder(ID_DEVICE_WATER_GEN)
    public static final TileEntityType<DeviceWaterGenTile> DEVICE_WATER_GEN_TILE = null;
    // endregion

    // region DYNAMOS
    public static final String ID_DYNAMO_STIRLING = ID_THERMAL + ":dynamo_stirling";
    public static final String ID_DYNAMO_COMPRESSION = ID_THERMAL + ":dynamo_compression";
    public static final String ID_DYNAMO_MAGMATIC = ID_THERMAL + ":dynamo_magmatic";
    public static final String ID_DYNAMO_NUMISMATIC = ID_THERMAL + ":dynamo_numismatic";
    public static final String ID_DYNAMO_LAPIDARY = ID_THERMAL + ":dynamo_lapidary";

    @ObjectHolder(ID_DYNAMO_STIRLING)
    public static final Block DYNAMO_STIRLING_BLOCK = null;
    @ObjectHolder(ID_DYNAMO_STIRLING)
    public static final TileEntityType<DynamoStirlingTile> DYNAMO_STIRLING_TILE = null;
    @ObjectHolder(ID_DYNAMO_STIRLING)
    public static final ContainerType<DynamoStirlingContainer> DYNAMO_STIRLING_CONTAINER = null;

    @ObjectHolder(ID_DYNAMO_COMPRESSION)
    public static final Block DYNAMO_COMPRESSION_BLOCK = null;
    @ObjectHolder(ID_DYNAMO_COMPRESSION)
    public static final TileEntityType<DynamoCompressionTile> DYNAMO_COMPRESSION_TILE = null;
    @ObjectHolder(ID_DYNAMO_COMPRESSION)
    public static final ContainerType<DynamoCompressionContainer> DYNAMO_COMPRESSION_CONTAINER = null;

    @ObjectHolder(ID_DYNAMO_MAGMATIC)
    public static final Block DYNAMO_MAGMATIC_BLOCK = null;
    @ObjectHolder(ID_DYNAMO_MAGMATIC)
    public static final TileEntityType<DynamoMagmaticTile> DYNAMO_MAGMATIC_TILE = null;
    @ObjectHolder(ID_DYNAMO_MAGMATIC)
    public static final ContainerType<DynamoMagmaticContainer> DYNAMO_MAGMATIC_CONTAINER = null;

    @ObjectHolder(ID_DYNAMO_NUMISMATIC)
    public static final Block DYNAMO_NUMISMATIC_BLOCK = null;
    @ObjectHolder(ID_DYNAMO_NUMISMATIC)
    public static final TileEntityType<DynamoNumismaticTile> DYNAMO_NUMISMATIC_TILE = null;
    @ObjectHolder(ID_DYNAMO_NUMISMATIC)
    public static final ContainerType<DynamoNumismaticContainer> DYNAMO_NUMISMATIC_CONTAINER = null;

    @ObjectHolder(ID_DYNAMO_LAPIDARY)
    public static final Block DYNAMO_LAPIDARY_BLOCK = null;
    @ObjectHolder(ID_DYNAMO_LAPIDARY)
    public static final TileEntityType<DynamoLapidaryTile> DYNAMO_LAPIDARY_TILE = null;
    @ObjectHolder(ID_DYNAMO_LAPIDARY)
    public static final ContainerType<DynamoLapidaryContainer> DYNAMO_LAPIDARY_CONTAINER = null;
    // endregion

    // region MACHINES
    public static final String ID_MACHINE_FURNACE = ID_THERMAL + ":machine_furnace";
    public static final String ID_MACHINE_SAWMILL = ID_THERMAL + ":machine_sawmill";
    public static final String ID_MACHINE_PULVERIZER = ID_THERMAL + ":machine_pulverizer";
    public static final String ID_MACHINE_SMELTER = ID_THERMAL + ":machine_smelter";
    public static final String ID_MACHINE_INSOLATOR = ID_THERMAL + ":machine_insolator";
    public static final String ID_MACHINE_CENTRIFUGE = ID_THERMAL + ":machine_centrifuge";
    public static final String ID_MACHINE_PRESS = ID_THERMAL + ":machine_press";
    public static final String ID_MACHINE_CRUCIBLE = ID_THERMAL + ":machine_crucible";
    public static final String ID_MACHINE_CHILLER = ID_THERMAL + ":machine_chiller";
    public static final String ID_MACHINE_REFINERY = ID_THERMAL + ":machine_refinery";
    public static final String ID_MACHINE_BREWER = ID_THERMAL + ":machine_brewer";
    public static final String ID_MACHINE_BOTTLER = ID_THERMAL + ":machine_bottler";
    public static final String ID_MACHINE_CRAFTER = ID_THERMAL + ":machine_crafter";

    @ObjectHolder(ID_MACHINE_FURNACE)
    public static final Block MACHINE_FURNACE_BLOCK = null;
    @ObjectHolder(ID_MACHINE_FURNACE)
    public static final TileEntityType<MachineFurnaceTile> MACHINE_FURNACE_TILE = null;
    @ObjectHolder(ID_MACHINE_FURNACE)
    public static final ContainerType<MachineFurnaceContainer> MACHINE_FURNACE_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_SAWMILL)
    public static final Block MACHINE_SAWMILL_BLOCK = null;
    @ObjectHolder(ID_MACHINE_SAWMILL)
    public static final TileEntityType<MachineSawmillTile> MACHINE_SAWMILL_TILE = null;
    @ObjectHolder(ID_MACHINE_SAWMILL)
    public static final ContainerType<MachineSawmillContainer> MACHINE_SAWMILL_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_PULVERIZER)
    public static final Block MACHINE_PULVERIZER_BLOCK = null;
    @ObjectHolder(ID_MACHINE_PULVERIZER)
    public static final TileEntityType<MachinePulverizerTile> MACHINE_PULVERIZER_TILE = null;
    @ObjectHolder(ID_MACHINE_PULVERIZER)
    public static final ContainerType<MachinePulverizerContainer> MACHINE_PULVERIZER_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_SMELTER)
    public static final Block MACHINE_SMELTER_BLOCK = null;
    @ObjectHolder(ID_MACHINE_SMELTER)
    public static final TileEntityType<MachineSmelterTile> MACHINE_SMELTER_TILE = null;
    @ObjectHolder(ID_MACHINE_SMELTER)
    public static final ContainerType<MachineSmelterContainer> MACHINE_SMELTER_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_INSOLATOR)
    public static final Block MACHINE_INSOLATOR_BLOCK = null;
    @ObjectHolder(ID_MACHINE_INSOLATOR)
    public static final TileEntityType<MachineInsolatorTile> MACHINE_INSOLATOR_TILE = null;
    @ObjectHolder(ID_MACHINE_INSOLATOR)
    public static final ContainerType<MachineInsolatorContainer> MACHINE_INSOLATOR_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_CENTRIFUGE)
    public static final Block MACHINE_CENTRIFUGE_BLOCK = null;
    @ObjectHolder(ID_MACHINE_CENTRIFUGE)
    public static final TileEntityType<MachineCentrifugeTile> MACHINE_CENTRIFUGE_TILE = null;
    @ObjectHolder(ID_MACHINE_CENTRIFUGE)
    public static final ContainerType<MachineCentrifugeContainer> MACHINE_CENTRIFUGE_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_PRESS)
    public static final Block MACHINE_PRESS_BLOCK = null;
    @ObjectHolder(ID_MACHINE_PRESS)
    public static final TileEntityType<MachinePressTile> MACHINE_PRESS_TILE = null;
    @ObjectHolder(ID_MACHINE_PRESS)
    public static final ContainerType<MachinePressContainer> MACHINE_PRESS_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_CRUCIBLE)
    public static final Block MACHINE_CRUCIBLE_BLOCK = null;
    @ObjectHolder(ID_MACHINE_CRUCIBLE)
    public static final TileEntityType<MachineCrucibleTile> MACHINE_CRUCIBLE_TILE = null;
    @ObjectHolder(ID_MACHINE_CRUCIBLE)
    public static final ContainerType<MachineCrucibleContainer> MACHINE_CRUCIBLE_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_CHILLER)
    public static final Block MACHINE_CHILLER_BLOCK = null;
    @ObjectHolder(ID_MACHINE_CHILLER)
    public static final TileEntityType<MachineChillerTile> MACHINE_CHILLER_TILE = null;
    @ObjectHolder(ID_MACHINE_CHILLER)
    public static final ContainerType<MachineChillerContainer> MACHINE_CHILLER_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_REFINERY)
    public static final Block MACHINE_REFINERY_BLOCK = null;
    @ObjectHolder(ID_MACHINE_REFINERY)
    public static final TileEntityType<MachineRefineryTile> MACHINE_REFINERY_TILE = null;
    @ObjectHolder(ID_MACHINE_REFINERY)
    public static final ContainerType<MachineRefineryContainer> MACHINE_REFINERY_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_BREWER)
    public static final Block MACHINE_BREWER_BLOCK = null;
    @ObjectHolder(ID_MACHINE_BREWER)
    public static final TileEntityType<MachineBrewerTile> MACHINE_BREWER_TILE = null;
    @ObjectHolder(ID_MACHINE_BREWER)
    public static final ContainerType<MachineBrewerContainer> MACHINE_BREWER_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_BOTTLER)
    public static final Block MACHINE_BOTTLER_BLOCK = null;
    @ObjectHolder(ID_MACHINE_BOTTLER)
    public static final TileEntityType<MachineBottlerTile> MACHINE_BOTTLER_TILE = null;
    @ObjectHolder(ID_MACHINE_BOTTLER)
    public static final ContainerType<MachineBottlerContainer> MACHINE_BOTTLER_CONTAINER = null;

    @ObjectHolder(ID_MACHINE_CRAFTER)
    public static final Block MACHINE_CRAFTER_BLOCK = null;
    @ObjectHolder(ID_MACHINE_CRAFTER)
    public static final TileEntityType<MachineCrafterTile> MACHINE_CRAFTER_TILE = null;
    //    @ObjectHolder(ID_MACHINE_CRAFTER)
    //    public static final ContainerType<MachineCrafterContainer> MACHINE_CRAFTER_CONTAINER=null;
    // endregion
}
