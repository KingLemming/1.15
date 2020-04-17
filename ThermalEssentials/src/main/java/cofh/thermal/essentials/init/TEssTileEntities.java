package cofh.thermal.essentials.init;

import cofh.thermal.essentials.tileentity.BasicPulverizerTile;
import cofh.thermal.essentials.tileentity.BasicSawmillTile;
import net.minecraft.tileentity.TileEntityType;

import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
import static cofh.thermal.essentials.init.TEssReferences.*;

public class TEssTileEntities {

    private TEssTileEntities() {

    }

    public static void register() {

        //        TILE_ENTITIES.register(ID_MACHINE_FURNACE, () -> TileEntityType.Builder.create(MachineFurnaceTile::new, MACHINE_FURNACE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_BASIC_SAWMILL, () -> TileEntityType.Builder.create(BasicSawmillTile::new, BASIC_SAWMILL_BLOCK).build(null));
        TILE_ENTITIES.register(ID_BASIC_PULVERIZER, () -> TileEntityType.Builder.create(BasicPulverizerTile::new, BASIC_PULVERIZER_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_MACHINE_INSOLATOR, () -> TileEntityType.Builder.create(MachineInsolatorTile::new, MACHINE_INSOLATOR_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_MACHINE_CENTRIFUGE, () -> TileEntityType.Builder.create(MachineCentrifugeTile::new, MACHINE_CENTRIFUGE_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_MACHINE_PRESS, () -> TileEntityType.Builder.create(MachinePressTile::new, MACHINE_PRESS_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_MACHINE_CRUCIBLE, () -> TileEntityType.Builder.create(MachineCrucibleTile::new, MACHINE_CRUCIBLE_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_MACHINE_CHILLER, () -> TileEntityType.Builder.create(MachineChillerTile::new, MACHINE_CHILLER_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_MACHINE_REFINERY, () -> TileEntityType.Builder.create(MachineRefineryTile::new, MACHINE_REFINERY_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_MACHINE_BREWER, () -> TileEntityType.Builder.create(MachineBrewerTile::new, MACHINE_BREWER_BLOCK).build(null));
        //        TILE_ENTITIES.register(ID_MACHINE_BOTTLER, () -> TileEntityType.Builder.create(MachineBottlerTile::new, MACHINE_BOTTLER_BLOCK).build(null));
    }

}
