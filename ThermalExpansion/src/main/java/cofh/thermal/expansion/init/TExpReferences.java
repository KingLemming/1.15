package cofh.thermal.expansion.init;

import cofh.thermal.expansion.inventory.container.*;
import cofh.thermal.expansion.tileentity.*;
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
    public static final String ID_MACHINE_SAWMILL = ID_THERMAL + ":machine_sawmill";
    public static final String ID_MACHINE_PULVERIZER = ID_THERMAL + ":machine_pulverizer";
    public static final String ID_MACHINE_INSOLATOR = ID_THERMAL + ":machine_insolator";
    public static final String ID_MACHINE_CENTRIFUGE = ID_THERMAL + ":machine_centrifuge";
    // endregion

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

}