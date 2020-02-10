package cofh.thermal.expansion.init;

import cofh.core.util.ProxyUtils;
import cofh.lib.block.TileBlock4Way;
import cofh.thermal.expansion.inventory.container.*;
import cofh.thermal.expansion.tileentity.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
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

        TILE_ENTITIES.register(ID_MACHINE_FURNACE, () -> TileEntityType.Builder.create(MachineFurnaceTile::new, MACHINE_FURNACE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_SAWMILL, () -> TileEntityType.Builder.create(MachineSawmillTile::new, MACHINE_SAWMILL_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PULVERIZER, () -> TileEntityType.Builder.create(MachinePulverizerTile::new, MACHINE_PULVERIZER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_INSOLATOR, () -> TileEntityType.Builder.create(MachineInsolatorTile::new, MACHINE_INSOLATOR_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CENTRIFUGE, () -> TileEntityType.Builder.create(MachineCentrifugeTile::new, MACHINE_CENTRIFUGE_BLOCK).build(null));

        CONTAINERS.register(ID_MACHINE_FURNACE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineFurnaceContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_SAWMILL, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineSawmillContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PULVERIZER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachinePulverizerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_INSOLATOR, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineInsolatorContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CENTRIFUGE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineCentrifugeContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

}
