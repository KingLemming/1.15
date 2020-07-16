package cofh.thermal.expansion.init;

import cofh.core.util.ProxyUtils;
import cofh.lib.block.TileBlock4Way;
import cofh.thermal.core.block.TileBlockDynamo;
import cofh.thermal.core.common.ThermalConfig;
import cofh.thermal.core.common.ThermalFeatures;
import cofh.thermal.expansion.inventory.container.dynamo.*;
import cofh.thermal.expansion.inventory.container.machine.*;
import cofh.thermal.expansion.tileentity.dynamo.*;
import cofh.thermal.expansion.tileentity.machine.*;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;

import java.util.function.IntSupplier;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
import static cofh.thermal.core.common.ThermalAugmentRules.DYNAMO_VALIDATOR;
import static cofh.thermal.core.common.ThermalAugmentRules.MACHINE_VALIDATOR;
import static cofh.thermal.core.util.RegistrationHelper.registerAugBlock;
import static cofh.thermal.expansion.init.TExpReferences.*;
import static net.minecraft.block.Block.Properties.create;

public class TExpBlocks {

    private TExpBlocks() {

    }

    public static void register() {

        ThermalFeatures.registerTinkerBench();

        registerTileBlocks();
        registerTileContainers();
        registerTileEntities();
    }

    public static void setup() {

    }

    // region HELPERS
    private static void registerTileBlocks() {

        IntSupplier machineAugs = () -> ThermalConfig.machineAugments;

        registerAugBlock(ID_MACHINE_FURNACE, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineFurnaceTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_SAWMILL, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineSawmillTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_PULVERIZER, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachinePulverizerTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_SMELTER, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineSmelterTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_INSOLATOR, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(15), MachineInsolatorTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_CENTRIFUGE, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineCentrifugeTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_PRESS, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachinePressTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_CRUCIBLE, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineCrucibleTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_CHILLER, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineChillerTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_REFINERY, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(12), MachineRefineryTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_BREWER, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineBrewerTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_BOTTLER, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineBottlerTile::new), machineAugs, MACHINE_VALIDATOR);

        IntSupplier dynamoAugs = () -> ThermalConfig.dynamoAugments;

        registerAugBlock(ID_DYNAMO_STIRLING, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoStirlingTile::new), dynamoAugs, DYNAMO_VALIDATOR);
        registerAugBlock(ID_DYNAMO_COMPRESSION, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoCompressionTile::new), dynamoAugs, DYNAMO_VALIDATOR);
        registerAugBlock(ID_DYNAMO_MAGMATIC, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoMagmaticTile::new), dynamoAugs, DYNAMO_VALIDATOR);
        registerAugBlock(ID_DYNAMO_NUMISMATIC, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoNumismaticTile::new), dynamoAugs, DYNAMO_VALIDATOR);
        registerAugBlock(ID_DYNAMO_LAPIDARY, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoLapidaryTile::new), dynamoAugs, DYNAMO_VALIDATOR);

        // Unaugmented
        //        registerBlock(ID_DEVICE_FLUID_BUFFER, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), DeviceFluidBufferTile::new));
        //        registerBlock(ID_DEVICE_ITEM_BUFFER, () -> new TileBlock4Way(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), DeviceItemBufferTile::new));
        //        registerBlock(ID_DEVICE_ROCK_GEN, () -> new TileBlockActive(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), DeviceRockGenTile::new));
        //        registerBlock(ID_DEVICE_WATER_GEN, () -> new TileBlockActive(create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), DeviceWaterGenTile::new));
    }

    private static void registerTileContainers() {

        CONTAINERS.register(ID_MACHINE_FURNACE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineFurnaceContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_SAWMILL, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineSawmillContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PULVERIZER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachinePulverizerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_SMELTER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineSmelterContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_INSOLATOR, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineInsolatorContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CENTRIFUGE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineCentrifugeContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PRESS, () -> IForgeContainerType.create((windowId, inv, data) -> new MachinePressContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CRUCIBLE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineCrucibleContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CHILLER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineChillerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_REFINERY, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineRefineryContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_BREWER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineBrewerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_BOTTLER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineBottlerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));

        CONTAINERS.register(ID_DYNAMO_STIRLING, () -> IForgeContainerType.create((windowId, inv, data) -> new DynamoStirlingContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_COMPRESSION, () -> IForgeContainerType.create((windowId, inv, data) -> new DynamoCompressionContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_MAGMATIC, () -> IForgeContainerType.create((windowId, inv, data) -> new DynamoMagmaticContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_NUMISMATIC, () -> IForgeContainerType.create((windowId, inv, data) -> new DynamoNumismaticContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_LAPIDARY, () -> IForgeContainerType.create((windowId, inv, data) -> new DynamoLapidaryContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));

        //        CONTAINERS.register(ID_DEVICE_FLUID_BUFFER, () -> IForgeContainerType.create((windowId, inv, data) -> new DeviceFluidBufferContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        //        CONTAINERS.register(ID_DEVICE_ITEM_BUFFER, () -> IForgeContainerType.create((windowId, inv, data) -> new DeviceItemBufferContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

    private static void registerTileEntities() {

        TILE_ENTITIES.register(ID_MACHINE_FURNACE, () -> TileEntityType.Builder.create(MachineFurnaceTile::new, MACHINE_FURNACE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_SAWMILL, () -> TileEntityType.Builder.create(MachineSawmillTile::new, MACHINE_SAWMILL_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PULVERIZER, () -> TileEntityType.Builder.create(MachinePulverizerTile::new, MACHINE_PULVERIZER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_SMELTER, () -> TileEntityType.Builder.create(MachineSmelterTile::new, MACHINE_SMELTER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_INSOLATOR, () -> TileEntityType.Builder.create(MachineInsolatorTile::new, MACHINE_INSOLATOR_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CENTRIFUGE, () -> TileEntityType.Builder.create(MachineCentrifugeTile::new, MACHINE_CENTRIFUGE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PRESS, () -> TileEntityType.Builder.create(MachinePressTile::new, MACHINE_PRESS_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CRUCIBLE, () -> TileEntityType.Builder.create(MachineCrucibleTile::new, MACHINE_CRUCIBLE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CHILLER, () -> TileEntityType.Builder.create(MachineChillerTile::new, MACHINE_CHILLER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_REFINERY, () -> TileEntityType.Builder.create(MachineRefineryTile::new, MACHINE_REFINERY_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_BREWER, () -> TileEntityType.Builder.create(MachineBrewerTile::new, MACHINE_BREWER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_BOTTLER, () -> TileEntityType.Builder.create(MachineBottlerTile::new, MACHINE_BOTTLER_BLOCK).build(null));

        TILE_ENTITIES.register(ID_DYNAMO_STIRLING, () -> TileEntityType.Builder.create(DynamoStirlingTile::new, DYNAMO_STIRLING_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_COMPRESSION, () -> TileEntityType.Builder.create(DynamoCompressionTile::new, DYNAMO_COMPRESSION_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_MAGMATIC, () -> TileEntityType.Builder.create(DynamoMagmaticTile::new, DYNAMO_MAGMATIC_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_NUMISMATIC, () -> TileEntityType.Builder.create(DynamoNumismaticTile::new, DYNAMO_NUMISMATIC_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_LAPIDARY, () -> TileEntityType.Builder.create(DynamoLapidaryTile::new, DYNAMO_LAPIDARY_BLOCK).build(null));

        //        TILE_ENTITIES.register(ID_DEVICE_FLUID_BUFFER, () -> TileEntityType.Builder.create(DeviceFluidBufferTile::new, DEVICE_FLUID_BUFFER_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_DEVICE_ITEM_BUFFER, () -> TileEntityType.Builder.create(DeviceItemBufferTile::new, DEVICE_ITEM_BUFFER_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_DEVICE_ROCK_GEN, () -> TileEntityType.Builder.create(DeviceRockGenTile::new, DEVICE_ROCK_GEN_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_DEVICE_WATER_GEN, () -> TileEntityType.Builder.create(DeviceWaterGenTile::new, DEVICE_WATER_GEN_BLOCK).build(null));
    }
    // endregion
}
