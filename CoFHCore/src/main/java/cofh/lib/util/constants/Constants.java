package cofh.lib.util.constants;

import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction.Plane;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidAttributes;

import java.util.UUID;

import static net.minecraft.inventory.EquipmentSlotType.*;

public class Constants {

    private Constants() {

    }

    // region AABBs
    // @formatter:off
    public static final VoxelShape[] CROPS_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    // @formatter:on
    // endregion

    // region MOD IDS
    public static final String ID_MINECRAFT = "minecraft";
    public static final String ID_COFH = "cofh";
    public static final String ID_COFH_CORE = "cofh_core";
    public static final String ID_COFH_LIB = "cofh_lib";

    public static final String ID_COFH_ARCHERY = "cofh_archery";
    public static final String ID_COFH_POTIONS = "cofh_potions";

    public static final String ID_THERMAL_SERIES = "thermal";

    public static final String ID_THERMAL_ATOMICS = "thermal_atomics";
    public static final String ID_THERMAL_CULTIVATION = "thermal_cultivation";
    public static final String ID_THERMAL_DYNAMICS = "thermal_dynamics";
    public static final String ID_THERMAL_FOUNDATION = "thermal_foundation";
    public static final String ID_THERMAL_EXPANSION = "thermal_expansion";
    public static final String ID_THERMAL_INNOVATION = "thermal_innovation";
    public static final String ID_THERMAL_LOCOMOTION = "thermal_locomotion";

    public static final String ID_ENSORCELLATION = "ensorcellation";
    public static final String ID_ENSTORAGEMENT = "enstoragement";
    public static final String ID_ORBULATION = "orbulation";
    public static final String ID_QUARTERMASTERY = "quartermastery";
    public static final String ID_REDSTONE_ARSENAL = "redstonearsenal";
    // endregion

    // region BLOCK PROPERTIES
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final BooleanProperty TILLED = BooleanProperty.create("tilled");
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
    public static final IntegerProperty AGE_PERENNIAL = IntegerProperty.create("age", 0, 10);
    public static final IntegerProperty AGE_TALL = IntegerProperty.create("age", 0, 11);
    public static final IntegerProperty AGE_TALL_PERENNIAL = IntegerProperty.create("age", 0, 15);
    public static final DirectionProperty FACING_ALL = DirectionProperty.create("facing");
    public static final DirectionProperty FACING_HORIZONTAL = DirectionProperty.create("facing", Plane.HORIZONTAL);
    // endregion

    // region GLOBALS
    public static final int AOE_BREAK_FACTOR = 8;
    public static final int BOTTLE_VOLUME = 250;
    public static final int ENTITY_TRACKING_DISTANCE = 64;
    public static final int ITEM_ACTIVE_DURATION = 40;
    public static final int MAGMATIC_TEMPERATURE = 1000;
    public static final int MAX_ENCHANT_LEVEL = 10;
    public static final int MB_PER_XP = 20;
    public static final int NETWORK_UPDATE_DISTANCE = 192;
    public static final int RF_PER_FURNACE_UNIT = 10;

    public static final float BASE_CHANCE = 1.0F;
    public static final float BASE_CHANCE_LOCKED = -1.0F;

    public static final int TANK_SMALL = FluidAttributes.BUCKET_VOLUME * 4;
    public static final int TANK_MEDIUM = FluidAttributes.BUCKET_VOLUME * 8;
    public static final int TANK_LARGE = FluidAttributes.BUCKET_VOLUME * 10;

    public static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]{HEAD, CHEST, LEGS, FEET};
    // endregion

    // region GUI
    public static final int GUI_TILE = 0;
    public static final int GUI_TILE_CONFIG = 1;
    // endregion

    // region PACKET
    public static final int PACKET_CONTROL = 1;
    public static final int PACKET_GUI = 2;
    public static final int PACKET_STATE = 3;
    public static final int PACKET_CHAT = 12;
    public static final int PACKET_SECURITY = 16;
    public static final int PACKET_FILTER = 17;
    public static final int PACKET_SECURITY_CONTROL = 24;
    public static final int PACKET_REDSTONE_CONTROL = 25;
    public static final int PACKET_TRANSFER_CONTROL = 26;
    public static final int PACKET_SIDE_CONFIG = 27;
    public static final int PACKET_KEY_MULTIMODE = 32;
    // endregion

    // region PREFIXES
    public static final String PREFIX_BLOCK = "block";
    public static final String PREFIX_COIN = "coin";
    public static final String PREFIX_CROP = "crop";
    public static final String PREFIX_DUST = "dust";
    public static final String PREFIX_GEAR = "gear";
    public static final String PREFIX_GEM = "gem";
    public static final String PREFIX_INGOT = "ingot";
    public static final String PREFIX_LOG = "log";
    public static final String PREFIX_NUGGET = "nugget";
    public static final String PREFIX_ORE = "ore";
    public static final String PREFIX_PLANK = "plank";
    public static final String PREFIX_PLATE = "plate";
    public static final String PREFIX_SEED = "seed";
    // endregion

    // region CONSTANTS
    public static final int TINT_INDEX_1 = 1;
    public static final int TINT_INDEX_2 = 2;
    public static final int TINT_INDEX_3 = 3;

    public static final String DAMAGE_ARROW = "arrow";
    public static final String DAMAGE_PLAYER = "player";

    public static final String FLUID_ESSENCE = "essence";
    public static final String FLUID_XPJUICE = "xpjuice";

    public static final String GROUP_DYNAMOS = "dynamos";
    public static final String GROUP_MACHINES = "machines";

    public static final String GROUP_COINS = "coins";
    public static final String GROUP_CROPS = "crops";
    public static final String GROUP_DECO = "deco";
    public static final String GROUP_DUSTS = "dusts";
    public static final String GROUP_FOODS = "foods";
    public static final String GROUP_GEARS = "gears";
    public static final String GROUP_GEMS = "gems";
    public static final String GROUP_INGOTS = "ingots";
    public static final String GROUP_ORES = "ores";
    public static final String GROUP_NUGGETS = "nuggets";
    public static final String GROUP_PLATES = "plates";
    public static final String GROUP_RAILS = "rails";
    public static final String GROUP_RESOURCES = "resources";
    public static final String GROUP_RODS = "rods";
    public static final String GROUP_SEEDS = "seeds";
    public static final String GROUP_STORAGE = "storage";
    public static final String GROUP_TOOLS = "tools";
    public static final String GROUP_UTILS = "utils";

    public static final String KEY_MULTIMODE = "cofh.multimode";

    public static final UUID UUID_ARMOR_TOUGHNESS = UUID.fromString("D95FB972-0426-4BB7-8A01-7416EA211884");

    public static final UUID UUID_EFFECT_CHILLED_MOVEMENT_SPEED = UUID.fromString("D99513AE-6F0E-4987-82DE-80DCBAF058BC");
    public static final UUID UUID_EFFECT_CHILLED_ATTACK_DAMAGE = UUID.fromString("DF93E7E2-5056-49FA-B425-0D8C46902105");

    public static final UUID UUID_ENCH_BULWARK_KNOCKBACK_RESISTANCE = UUID.fromString("DA9976EC-C764-4450-B5AA-1B11B10143E2");
    public static final UUID UUID_ENCH_PHALANX_MOVEMENT_SPEED = UUID.fromString("DBFFDB74-4607-4C2E-9573-A14D6A73397A");
    public static final UUID UUID_ENCH_REACH_DISTANCE = UUID.fromString("D7476206-4A89-4522-9D1D-9BB34076FFDA");

    public static final int RGB_DURABILITY_FLUX = 0xD01010;
    public static final int RGB_DURABILITY_WATER = 0x4060FF;
    public static final int RGB_DURABILITY_ENDER = 0x14594D;
    public static final int RGB_DURABILITY_EXP = 0x7AAC52;
    // endregion

    // region TEXTURES
    public static final String PATH_GFX = "cofh:textures/";
    public static final String PATH_GUI = PATH_GFX + "gui/";
    public static final String PATH_ELEMENTS = PATH_GUI + "elements/";
    public static final String PATH_GUI_STORAGE = PATH_GUI + "storage/";
    public static final String PATH_GUI_FILTER = PATH_GUI + "filter/";
    // endregion
}
