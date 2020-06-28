package cofh.thermal.expansion.init;

import cofh.core.util.ProxyUtils;
import cofh.lib.block.TileBlock4Way;
import cofh.lib.block.TileBlockActive;
import cofh.thermal.core.block.TileBlockDynamo;
import cofh.thermal.core.common.ThermalConfig;
import cofh.thermal.expansion.inventory.container.dynamo.*;
import cofh.thermal.expansion.inventory.container.machine.*;
import cofh.thermal.expansion.tileentity.device.DeviceRockGenTile;
import cofh.thermal.expansion.tileentity.device.DeviceWaterGenTile;
import cofh.thermal.expansion.tileentity.dynamo.*;
import cofh.thermal.expansion.tileentity.machine.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;

import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
import static cofh.thermal.core.util.RegistrationHelper.registerAugBlock;
import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static cofh.thermal.expansion.init.TExpReferences.*;

public class TExpBlocks {

    private TExpBlocks() {

    }

    public static void register() {

        registerTileBlocks();
        registerTileContainers();
        registerTileEntities();
    }

    public static void setup() {

    }

    // region HELPERS
    private static void registerTileBlocks() {

        IntSupplier machineAugs = () -> ThermalConfig.machineAugments;
        Predicate<ItemStack> machineValidator = (e) -> true;

        registerAugBlock(ID_MACHINE_FURNACE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineFurnaceTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_SAWMILL, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineSawmillTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_PULVERIZER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachinePulverizerTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_SMELTER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineSmelterTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_INSOLATOR, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(15), MachineInsolatorTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_CENTRIFUGE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineCentrifugeTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_PRESS, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachinePressTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_CRUCIBLE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(14), MachineCrucibleTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_CHILLER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineChillerTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_REFINERY, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(12), MachineRefineryTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_BREWER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineBrewerTile::new), machineAugs, machineValidator);
        registerAugBlock(ID_MACHINE_BOTTLER, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), MachineBottlerTile::new), machineAugs, machineValidator);

        IntSupplier dynamoAugs = () -> ThermalConfig.dynamoAugments;
        Predicate<ItemStack> dynamoValidator = (e) -> true;

        registerAugBlock(ID_DYNAMO_STIRLING, () -> new TileBlockDynamo(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoStirlingTile::new), dynamoAugs, dynamoValidator);
        registerAugBlock(ID_DYNAMO_COMPRESSION, () -> new TileBlockDynamo(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoCompressionTile::new), dynamoAugs, dynamoValidator);
        registerAugBlock(ID_DYNAMO_MAGMATIC, () -> new TileBlockDynamo(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoMagmaticTile::new), dynamoAugs, dynamoValidator);
        registerAugBlock(ID_DYNAMO_NUMISMATIC, () -> new TileBlockDynamo(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoNumismaticTile::new), dynamoAugs, dynamoValidator);
        registerAugBlock(ID_DYNAMO_LAPIDARY, () -> new TileBlockDynamo(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(7), DynamoLapidaryTile::new), dynamoAugs, dynamoValidator);

        registerBlock(ID_DEVICE_ROCK_GEN, () -> new TileBlockActive(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), DeviceRockGenTile::new));
        registerBlock(ID_DEVICE_WATER_GEN, () -> new TileBlockActive(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0F).lightValue(0), DeviceWaterGenTile::new));
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

        TILE_ENTITIES.register(ID_DEVICE_ROCK_GEN, () -> TileEntityType.Builder.create(DeviceRockGenTile::new, DEVICE_ROCK_GEN_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DEVICE_WATER_GEN, () -> TileEntityType.Builder.create(DeviceWaterGenTile::new, DEVICE_WATER_GEN_BLOCK).build(null));

    }
    // endregion
}
