package cofh.thermal.cultivation.init;

import cofh.thermal.cultivation.inventory.container.device.DeviceSoilInfuserContainer;
import cofh.thermal.cultivation.tileentity.DeviceSoilInfuserTile;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.cultivation.init.TCulIDs.*;

@ObjectHolder(ID_THERMAL)
public class TCulReferences {

    private TCulReferences() {

    }

    // region CROPS
    @ObjectHolder(ID_FROST_MELON_STEM)
    public static final Block FROST_MELON_STEM = null;
    @ObjectHolder(ID_FROST_MELON_STEM_ATTACHED)
    public static final Block FROST_MELON_STEM_ATTACHED = null;
    // endregion

    // region BLOCKS
    @ObjectHolder(ID_PHYTOSOIL)
    public static final Block PHYTOSOIL_BLOCK = null;

    @ObjectHolder(ID_PHYTOSOIL_TILLED)
    public static final Block PHYTOSOIL_CHARGED_BLOCK = null;
    // endregion

    // region DEVICES
    @ObjectHolder(ID_DEVICE_SOIL_INFUSER)
    public static final Block DEVICE_SOIL_INFUSER_BLOCK = null;
    @ObjectHolder(ID_DEVICE_SOIL_INFUSER)
    public static final TileEntityType<DeviceSoilInfuserTile> DEVICE_SOIL_INFUSER_TILE = null;
    @ObjectHolder(ID_DEVICE_SOIL_INFUSER)
    public static final ContainerType<DeviceSoilInfuserContainer> DEVICE_SOIL_INFUSER_CONTAINER = null;
    // endregion

    // region DYNAMOS

    // endregion
}
