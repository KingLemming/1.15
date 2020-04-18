package cofh.thermal.essentials.init;

import cofh.thermal.essentials.inventory.container.BasicCrucibleContainer;
import cofh.thermal.essentials.inventory.container.BasicPulverizerContainer;
import cofh.thermal.essentials.inventory.container.BasicSawmillContainer;
import cofh.thermal.essentials.tileentity.BasicCrucibleTile;
import cofh.thermal.essentials.tileentity.BasicPulverizerTile;
import cofh.thermal.essentials.tileentity.BasicSawmillTile;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TEssReferences {

    private TEssReferences() {

    }

    // region MACHINES
    public static final String ID_BASIC_SAWMILL = ID_THERMAL + ":basic_sawmill";
    public static final String ID_BASIC_PULVERIZER = ID_THERMAL + ":basic_pulverizer";
    public static final String ID_BASIC_CRUCIBLE = ID_THERMAL + ":basic_crucible";
    //    public static final String ID_BASIC_REFINERY = ID_THERMAL + ":basic_refinery";

    @ObjectHolder(ID_BASIC_SAWMILL)
    public static Block BASIC_SAWMILL_BLOCK;
    @ObjectHolder(ID_BASIC_SAWMILL)
    public static TileEntityType<BasicSawmillTile> BASIC_SAWMILL_TILE;
    @ObjectHolder(ID_BASIC_SAWMILL)
    public static ContainerType<BasicSawmillContainer> BASIC_SAWMILL_CONTAINER;

    @ObjectHolder(ID_BASIC_PULVERIZER)
    public static Block BASIC_PULVERIZER_BLOCK;
    @ObjectHolder(ID_BASIC_PULVERIZER)
    public static TileEntityType<BasicPulverizerTile> BASIC_PULVERIZER_TILE;
    @ObjectHolder(ID_BASIC_PULVERIZER)
    public static ContainerType<BasicPulverizerContainer> BASIC_PULVERIZER_CONTAINER;

    @ObjectHolder(ID_BASIC_CRUCIBLE)
    public static Block BASIC_CRUCIBLE_BLOCK;
    @ObjectHolder(ID_BASIC_CRUCIBLE)
    public static TileEntityType<BasicCrucibleTile> BASIC_CRUCIBLE_TILE;
    @ObjectHolder(ID_BASIC_CRUCIBLE)
    public static ContainerType<BasicCrucibleContainer> BASIC_CRUCIBLE_CONTAINER;
    //
    //    @ObjectHolder(ID_BASIC_REFINERY)
    //    public static Block BASIC_REFINERY_BLOCK;
    //    @ObjectHolder(ID_BASIC_REFINERY)
    //    public static TileEntityType<MachineRefineryTile> BASIC_REFINERY_TILE;
    //    @ObjectHolder(ID_BASIC_REFINERY)
    //    public static ContainerType<MachineRefineryContainer> BASIC_REFINERY_CONTAINER;
    // endregion
}
