package cofh.thermal.cultivation.init;

import cofh.thermal.cultivation.tileentity.DeviceHiveExtractorTile;
import net.minecraft.tileentity.TileEntityType;

import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
import static cofh.thermal.cultivation.init.TCulReferences.ID_DEVICE_HIVE_EXTRACTOR;
import static cofh.thermal.cultivation.init.TCulReferences.DEVICE_HIVE_EXTRACTOR_BLOCK;

public class TCulTileEntities {

    private TCulTileEntities() {

    }

    public static void register() {

        TILE_ENTITIES.register(ID_DEVICE_HIVE_EXTRACTOR, () -> TileEntityType.Builder.create(DeviceHiveExtractorTile::new, DEVICE_HIVE_EXTRACTOR_BLOCK).build(null));
    }

}
