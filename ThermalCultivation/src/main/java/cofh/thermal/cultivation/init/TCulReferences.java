package cofh.thermal.cultivation.init;

import cofh.thermal.cultivation.inventory.container.DeviceHiveExtractorContainer;
import cofh.thermal.cultivation.tileentity.DeviceHiveExtractorTile;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TCulReferences {

    private TCulReferences() {

    }

    // region CROPS
    public static final String ID_BARLEY = "barley";
    public static final String ID_ONION = "onion";
    public static final String ID_RADISH = "radish";
    public static final String ID_RICE = "rice";
    public static final String ID_SADIROOT = "sadiroot";
    public static final String ID_SPINACH = "spinach";

    public static final String ID_BELL_PEPPER = "bell_pepper";
    public static final String ID_EGGPLANT = "eggplant";
    public static final String ID_GREEN_BEAN = "green_bean";
    public static final String ID_PEANUT = "peanut";
    public static final String ID_STRAWBERRY = "strawberry";
    public static final String ID_TOMATO = "tomato";

    public static final String ID_COFFEE = "coffee";
    public static final String ID_TEA = "tea";

    public static final String ID_FROST_MELON = ID_THERMAL + ":frost_melon";
    public static final String ID_FROST_MELON_STEM = ID_THERMAL + ":frost_melon_stem";
    public static final String ID_FROST_MELON_STEM_ATTACHED = ID_THERMAL + ":frost_melon_stem_attached";

    public static final String ID_FROST_MELON_SLICE = ID_THERMAL + ":frost_melon_slice";

    @ObjectHolder(ID_FROST_MELON)
    public static Block FROST_MELON;
    @ObjectHolder(ID_FROST_MELON_STEM)
    public static Block FROST_MELON_STEM;
    @ObjectHolder(ID_FROST_MELON_STEM_ATTACHED)
    public static Block FROST_MELON_STEM_ATTACHED;
    // endregion

    // region DEVICES
    public static final String ID_DEVICE_HIVE_EXTRACTOR = ID_THERMAL + ":device_hive_extractor";
    public static final String ID_DEVICE_TREE_EXTRACTOR = ID_THERMAL + ":device_tree_extractor";

    @ObjectHolder(ID_DEVICE_HIVE_EXTRACTOR)
    public static Block DEVICE_HIVE_EXTRACTOR_BLOCK;
    @ObjectHolder(ID_DEVICE_HIVE_EXTRACTOR)
    public static TileEntityType<DeviceHiveExtractorTile> DEVICE_HIVE_EXTRACTOR_TILE;
    @ObjectHolder(ID_DEVICE_HIVE_EXTRACTOR)
    public static ContainerType<DeviceHiveExtractorContainer> DEVICE_HIVE_EXTRACTOR_CONTAINER;
    // endregion

    // region DYNAMOS

    // endregion
}
