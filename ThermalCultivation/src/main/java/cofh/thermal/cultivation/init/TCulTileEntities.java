package cofh.thermal.cultivation.init;

import cofh.thermal.cultivation.tileentity.MachineHiveExtractorTile;
import net.minecraft.tileentity.TileEntityType;

import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
import static cofh.thermal.cultivation.init.TCulReferences.ID_MACHINE_HIVE_EXTRACTOR;
import static cofh.thermal.cultivation.init.TCulReferences.MACHINE_BEE_EXTRACTOR_BLOCK;

public class TCulTileEntities {

    private TCulTileEntities() {

    }

    public static void register() {

        TILE_ENTITIES.register(ID_MACHINE_HIVE_EXTRACTOR, () -> TileEntityType.Builder.create(MachineHiveExtractorTile::new, MACHINE_BEE_EXTRACTOR_BLOCK).build(null));
    }

}
