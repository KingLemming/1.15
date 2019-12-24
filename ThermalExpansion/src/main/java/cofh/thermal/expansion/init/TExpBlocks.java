package cofh.thermal.expansion.init;

import cofh.core.util.ProxyUtils;
import cofh.lib.block.TileBlock4Way;
import cofh.thermal.expansion.block.tutorial.FirstBlock;
import cofh.thermal.expansion.block.tutorial.FirstBlockContainer;
import cofh.thermal.expansion.block.tutorial.FirstBlockTile;
import cofh.thermal.expansion.tileentity.machine.FurnaceTile;
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


        registerBlock(ID_MACHINE_FURNACE, () -> new TileBlock4Way(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0f).lightValue(14), FurnaceTile::new));

        TILE_ENTITIES.register(ID_MACHINE_FURNACE, () -> TileEntityType.Builder.create(FurnaceTile::new, MACHINE_FURNACE_BLOCK).build(null));


        registerBlock("firstblock", () -> new FirstBlock());

        TILE_ENTITIES.register("firstblock", () -> TileEntityType.Builder.create(FirstBlockTile::new, FIRSTBLOCK).build(null));

        CONTAINERS.register("firstblock", () -> IForgeContainerType.create((windowId, inv, data) -> new FirstBlockContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

}
