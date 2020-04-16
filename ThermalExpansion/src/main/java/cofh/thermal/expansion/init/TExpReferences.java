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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TExpReferences {

    private TExpReferences() {

    }

    // region DEVICES
    public static final String ID_DEVICE_ROCK_GEN = ID_THERMAL + ":device_rock_gen";
    public static final String ID_DEVICE_WATER_GEN = ID_THERMAL + ":device_water_gen";
    // endregion

    // region MACHINES
    public static final String ID_MACHINE_FURNACE = ID_THERMAL + ":machine_furnace";
    public static final String ID_MACHINE_SAWMILL = ID_THERMAL + ":machine_sawmill";
    public static final String ID_MACHINE_PULVERIZER = ID_THERMAL + ":machine_pulverizer";
    public static final String ID_MACHINE_INSOLATOR = ID_THERMAL + ":machine_insolator";
    public static final String ID_MACHINE_CENTRIFUGE = ID_THERMAL + ":machine_centrifuge";
    public static final String ID_MACHINE_PRESS = ID_THERMAL + ":machine_press";
    public static final String ID_MACHINE_CRUCIBLE = ID_THERMAL + ":machine_crucible";
    public static final String ID_MACHINE_CHILLER = ID_THERMAL + ":machine_chiller";
    public static final String ID_MACHINE_REFINERY = ID_THERMAL + ":machine_refinery";
    public static final String ID_MACHINE_BREWER = ID_THERMAL + ":machine_brewer";
    public static final String ID_MACHINE_BOTTLER = ID_THERMAL + ":machine_bottler";
    // endregion

    // region RECIPES
    public static final ResourceLocation ID_RECIPE_FURNACE = new ResourceLocation(ID_THERMAL, "furnace");
    public static final ResourceLocation ID_RECIPE_SAWMILL = new ResourceLocation(ID_THERMAL, "sawmill");
    public static final ResourceLocation ID_RECIPE_PULVERIZER = new ResourceLocation(ID_THERMAL, "pulverizer");
    public static final ResourceLocation ID_RECIPE_INSOLATOR = new ResourceLocation(ID_THERMAL, "insolator");
    public static final ResourceLocation ID_RECIPE_CENTRIFUGE = new ResourceLocation(ID_THERMAL, "centrifuge");
    public static final ResourceLocation ID_RECIPE_PRESS = new ResourceLocation(ID_THERMAL, "press");
    public static final ResourceLocation ID_RECIPE_CRUCIBLE = new ResourceLocation(ID_THERMAL, "crucible");
    public static final ResourceLocation ID_RECIPE_CHILLER = new ResourceLocation(ID_THERMAL, "chiller");
    public static final ResourceLocation ID_RECIPE_REFINERY = new ResourceLocation(ID_THERMAL, "refinery");
    public static final ResourceLocation ID_RECIPE_BREWER = new ResourceLocation(ID_THERMAL, "brewer");
    public static final ResourceLocation ID_RECIPE_BOTTLER = new ResourceLocation(ID_THERMAL, "bottler");

    public static final ResourceLocation ID_CATALYST_PULVERIZER = new ResourceLocation(ID_THERMAL, "pulverizer_catalyst");
    public static final ResourceLocation ID_CATALYST_INSOLATOR = new ResourceLocation(ID_THERMAL, "insolator_catalyst");
    // endregion

    // region DYNAMOS
    public static final String ID_DYNAMO_STIRLING = ID_THERMAL + ":dynamo_stirling";
    public static final String ID_DYNAMO_COMPRESSION = ID_THERMAL + ":dynamo_compression";
    public static final String ID_DYNAMO_MAGMATIC = ID_THERMAL + ":dynamo_magmatic";
    public static final String ID_DYNAMO_NUMISMATIC = ID_THERMAL + ":dynamo_numismatic";
    public static final String ID_DYNAMO_LAPIDARY = ID_THERMAL + ":dynamo_lapidary";
    // endregion

    // region FUELS
    public static final ResourceLocation ID_FUEL_STIRLING = new ResourceLocation(ID_THERMAL, "stirling_fuel");
    public static final ResourceLocation ID_FUEL_COMPRESSION = new ResourceLocation(ID_THERMAL, "compression_fuel");
    public static final ResourceLocation ID_FUEL_MAGMATIC = new ResourceLocation(ID_THERMAL, "magmatic_fuel");
    public static final ResourceLocation ID_FUEL_NUMISMATIC = new ResourceLocation(ID_THERMAL, "numismatic_fuel");
    public static final ResourceLocation ID_FUEL_LAPIDARY = new ResourceLocation(ID_THERMAL, "lapidary_fuel");
    // endregion

    // region FUELS

    // endregion

    // region REFERENCES
    @ObjectHolder(ID_MACHINE_FURNACE)
    public static Block MACHINE_FURNACE_BLOCK;
    @ObjectHolder(ID_MACHINE_FURNACE)
    public static TileEntityType<MachineFurnaceTile> MACHINE_FURNACE_TILE;
    @ObjectHolder(ID_MACHINE_FURNACE)
    public static ContainerType<MachineFurnaceContainer> MACHINE_FURNACE_CONTAINER;

    @ObjectHolder(ID_MACHINE_SAWMILL)
    public static Block MACHINE_SAWMILL_BLOCK;
    @ObjectHolder(ID_MACHINE_SAWMILL)
    public static TileEntityType<MachineSawmillTile> MACHINE_SAWMILL_TILE;
    @ObjectHolder(ID_MACHINE_SAWMILL)
    public static ContainerType<MachineSawmillContainer> MACHINE_SAWMILL_CONTAINER;

    @ObjectHolder(ID_MACHINE_PULVERIZER)
    public static Block MACHINE_PULVERIZER_BLOCK;
    @ObjectHolder(ID_MACHINE_PULVERIZER)
    public static TileEntityType<MachinePulverizerTile> MACHINE_PULVERIZER_TILE;
    @ObjectHolder(ID_MACHINE_PULVERIZER)
    public static ContainerType<MachinePulverizerContainer> MACHINE_PULVERIZER_CONTAINER;

    @ObjectHolder(ID_MACHINE_INSOLATOR)
    public static Block MACHINE_INSOLATOR_BLOCK;
    @ObjectHolder(ID_MACHINE_INSOLATOR)
    public static TileEntityType<MachineInsolatorTile> MACHINE_INSOLATOR_TILE;
    @ObjectHolder(ID_MACHINE_INSOLATOR)
    public static ContainerType<MachineInsolatorContainer> MACHINE_INSOLATOR_CONTAINER;

    @ObjectHolder(ID_MACHINE_CENTRIFUGE)
    public static Block MACHINE_CENTRIFUGE_BLOCK;
    @ObjectHolder(ID_MACHINE_CENTRIFUGE)
    public static TileEntityType<MachineCentrifugeTile> MACHINE_CENTRIFUGE_TILE;
    @ObjectHolder(ID_MACHINE_CENTRIFUGE)
    public static ContainerType<MachineCentrifugeContainer> MACHINE_CENTRIFUGE_CONTAINER;

    @ObjectHolder(ID_MACHINE_PRESS)
    public static Block MACHINE_PRESS_BLOCK;
    @ObjectHolder(ID_MACHINE_PRESS)
    public static TileEntityType<MachinePressTile> MACHINE_PRESS_TILE;
    @ObjectHolder(ID_MACHINE_PRESS)
    public static ContainerType<MachinePressContainer> MACHINE_PRESS_CONTAINER;

    @ObjectHolder(ID_MACHINE_CRUCIBLE)
    public static Block MACHINE_CRUCIBLE_BLOCK;
    @ObjectHolder(ID_MACHINE_CRUCIBLE)
    public static TileEntityType<MachineCrucibleTile> MACHINE_CRUCIBLE_TILE;
    @ObjectHolder(ID_MACHINE_CRUCIBLE)
    public static ContainerType<MachineCrucibleContainer> MACHINE_CRUCIBLE_CONTAINER;

    @ObjectHolder(ID_MACHINE_CHILLER)
    public static Block MACHINE_CHILLER_BLOCK;
    @ObjectHolder(ID_MACHINE_CHILLER)
    public static TileEntityType<MachineChillerTile> MACHINE_CHILLER_TILE;
    @ObjectHolder(ID_MACHINE_CHILLER)
    public static ContainerType<MachineChillerContainer> MACHINE_CHILLER_CONTAINER;

    @ObjectHolder(ID_MACHINE_REFINERY)
    public static Block MACHINE_REFINERY_BLOCK;
    @ObjectHolder(ID_MACHINE_REFINERY)
    public static TileEntityType<MachineRefineryTile> MACHINE_REFINERY_TILE;
    @ObjectHolder(ID_MACHINE_REFINERY)
    public static ContainerType<MachineRefineryContainer> MACHINE_REFINERY_CONTAINER;

    @ObjectHolder(ID_MACHINE_BREWER)
    public static Block MACHINE_BREWER_BLOCK;
    @ObjectHolder(ID_MACHINE_BREWER)
    public static TileEntityType<MachineBrewerTile> MACHINE_BREWER_TILE;
    @ObjectHolder(ID_MACHINE_BREWER)
    public static ContainerType<MachineBrewerContainer> MACHINE_BREWER_CONTAINER;

    @ObjectHolder(ID_MACHINE_BOTTLER)
    public static Block MACHINE_BOTTLER_BLOCK;
    @ObjectHolder(ID_MACHINE_BOTTLER)
    public static TileEntityType<MachineBottlerTile> MACHINE_BOTTLER_TILE;
    @ObjectHolder(ID_MACHINE_BOTTLER)
    public static ContainerType<MachineBottlerContainer> MACHINE_BOTTLER_CONTAINER;

    @ObjectHolder(ID_DYNAMO_STIRLING)
    public static Block DYNAMO_STIRLING_BLOCK;
    @ObjectHolder(ID_DYNAMO_STIRLING)
    public static TileEntityType<DynamoStirlingTile> DYNAMO_STIRLING_TILE;
    @ObjectHolder(ID_DYNAMO_STIRLING)
    public static ContainerType<DynamoStirlingContainer> DYNAMO_STIRLING_CONTAINER;

    @ObjectHolder(ID_DYNAMO_COMPRESSION)
    public static Block DYNAMO_COMPRESSION_BLOCK;
    @ObjectHolder(ID_DYNAMO_COMPRESSION)
    public static TileEntityType<DynamoCompressionTile> DYNAMO_COMPRESSION_TILE;
    @ObjectHolder(ID_DYNAMO_COMPRESSION)
    public static ContainerType<DynamoCompressionContainer> DYNAMO_COMPRESSION_CONTAINER;

    @ObjectHolder(ID_DYNAMO_MAGMATIC)
    public static Block DYNAMO_MAGMATIC_BLOCK;
    @ObjectHolder(ID_DYNAMO_MAGMATIC)
    public static TileEntityType<DynamoMagmaticTile> DYNAMO_MAGMATIC_TILE;
    @ObjectHolder(ID_DYNAMO_MAGMATIC)
    public static ContainerType<DynamoMagmaticContainer> DYNAMO_MAGMATIC_CONTAINER;

    @ObjectHolder(ID_DYNAMO_NUMISMATIC)
    public static Block DYNAMO_NUMISMATIC_BLOCK;
    @ObjectHolder(ID_DYNAMO_NUMISMATIC)
    public static TileEntityType<DynamoNumismaticTile> DYNAMO_NUMISMATIC_TILE;
    @ObjectHolder(ID_DYNAMO_NUMISMATIC)
    public static ContainerType<DynamoNumismaticContainer> DYNAMO_NUMISMATIC_CONTAINER;

    @ObjectHolder(ID_DYNAMO_LAPIDARY)
    public static Block DYNAMO_LAPIDARY_BLOCK;
    @ObjectHolder(ID_DYNAMO_LAPIDARY)
    public static TileEntityType<DynamoLapidaryTile> DYNAMO_LAPIDARY_TILE;
    @ObjectHolder(ID_DYNAMO_LAPIDARY)
    public static ContainerType<DynamoLapidaryContainer> DYNAMO_LAPIDARY_CONTAINER;

    @ObjectHolder(ID_DEVICE_ROCK_GEN)
    public static Block DEVICE_ROCK_GEN_BLOCK;
    @ObjectHolder(ID_DEVICE_ROCK_GEN)
    public static TileEntityType<DeviceRockGenTile> DEVICE_ROCK_GEN_TILE;

    @ObjectHolder(ID_DEVICE_WATER_GEN)
    public static Block DEVICE_WATER_GEN_BLOCK;
    @ObjectHolder(ID_DEVICE_WATER_GEN)
    public static TileEntityType<DeviceWaterGenTile> DEVICE_WATER_GEN_TILE;
    // endregion
}
