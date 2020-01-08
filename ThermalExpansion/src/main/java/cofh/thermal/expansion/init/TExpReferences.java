package cofh.thermal.expansion.init;

import cofh.thermal.expansion.block.tutorial.FirstBlock;
import cofh.thermal.expansion.block.tutorial.FirstBlockContainer;
import cofh.thermal.expansion.block.tutorial.FirstBlockTile;
import cofh.thermal.expansion.inventory.container.MachineFurnaceContainer;
import cofh.thermal.expansion.tileentity.MachineFurnaceTile;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TExpReferences {

    private TExpReferences() {

    }

    // region MACHINES
    public static final String ID_MACHINE_FURNACE = ID_THERMAL + ":machine_furnace";
    // endregion

    @ObjectHolder(ID_MACHINE_FURNACE)
    public static Block MACHINE_FURNACE_BLOCK;

    @ObjectHolder(ID_MACHINE_FURNACE)
    public static TileEntityType<MachineFurnaceTile> MACHINE_FURNACE_TILE;

    @ObjectHolder(ID_MACHINE_FURNACE)
    public static ContainerType<MachineFurnaceContainer> MACHINE_FURNACE_CONTAINER;

    @ObjectHolder("thermal:firstblock")
    public static FirstBlock FIRSTBLOCK;

    @ObjectHolder("thermal:firstblock")
    public static TileEntityType<FirstBlockTile> FIRSTBLOCK_TILE;

    @ObjectHolder("thermal:firstblock")
    public static ContainerType<FirstBlockContainer> FIRSTBLOCK_CONTAINER;

}