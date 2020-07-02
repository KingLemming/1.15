package cofh.lib.util.constants;

import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Plane;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidAttributes;

import java.util.UUID;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

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
    public static final String ID_FORGE = "forge";

    public static final String ID_COFH_CORE = "cofh_core";

    public static final String ID_COFH_POTIONS = "cofh_potions";
    public static final String ID_COFH_TOOLS = "cofh_tools";

    public static final String ID_THERMAL = "thermal";

    public static final String ID_THERMAL_ATOMICS = "thermal_atomics";
    public static final String ID_THERMAL_CULTIVATION = "thermal_cultivation";
    public static final String ID_THERMAL_DYNAMICS = "thermal_dynamics";
    public static final String ID_THERMAL_ESSENTIALS = "thermal_essentials";
    public static final String ID_THERMAL_EXPANSION = "thermal_expansion";
    public static final String ID_THERMAL_FOUNDATION = "thermal_foundation";
    public static final String ID_THERMAL_INNOVATION = "thermal_innovation";
    public static final String ID_THERMAL_LOCOMOTION = "thermal_locomotion";

    public static final String ID_ARCHERS_PARADOX = "archers_paradox";
    public static final String ID_ENSORCELLATION = "ensorcellation";
    public static final String ID_OMGOURD = "omgourd";
    public static final String ID_ENSTORAGEMENT = "enstoragement";
    public static final String ID_ORBULATION = "orbulation";
    public static final String ID_QUARTERMASTERY = "quartermastery";
    public static final String ID_REDSTONE_ARSENAL = "redstonearsenal";
    // endregion

    // region BLOCKSTATE PROPERTIES
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final BooleanProperty CHARGED = BooleanProperty.create("charged");
    public static final BooleanProperty TILLED = BooleanProperty.create("tilled");
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
    public static final IntegerProperty AGE_PERENNIAL = IntegerProperty.create("age", 0, 10);
    public static final IntegerProperty AGE_TALL = IntegerProperty.create("age", 0, 11);
    public static final IntegerProperty AGE_TALL_PERENNIAL = IntegerProperty.create("age", 0, 15);
    public static final DirectionProperty FACING_ALL = DirectionProperty.create("facing", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN);
    public static final DirectionProperty FACING_HORIZONTAL = DirectionProperty.create("facing", Plane.HORIZONTAL);

    private static final Predicate<RailShape> PRED_STRAIGHT = dir -> dir != RailShape.NORTH_EAST && dir != RailShape.NORTH_WEST && dir != RailShape.SOUTH_EAST && dir != RailShape.SOUTH_WEST;
    private static final Predicate<RailShape> PRED_NO_SLOPE = dir -> dir != RailShape.ASCENDING_EAST && dir != RailShape.ASCENDING_WEST && dir != RailShape.ASCENDING_NORTH && dir != RailShape.ASCENDING_SOUTH;

    public static final EnumProperty<RailShape> RAIL_DEFAULT = EnumProperty.create("shape", RailShape.class);
    public static final EnumProperty<RailShape> RAIL_STRAIGHT = EnumProperty.create("shape", RailShape.class, PRED_STRAIGHT);
    public static final EnumProperty<RailShape> RAIL_FLAT = EnumProperty.create("shape", RailShape.class, PRED_NO_SLOPE);
    public static final EnumProperty<RailShape> RAIL_STRAIGHT_FLAT = EnumProperty.create("shape", RailShape.class, dir -> PRED_STRAIGHT.test(dir) && PRED_NO_SLOPE.test(dir));
    // endregion

    // region GLOBALS
    public static final int AOE_BREAK_FACTOR = 8;
    public static final int BOTTLE_VOLUME = 250;
    public static final int ENTITY_TRACKING_DISTANCE = 64;
    public static final int ITEM_ACTIVE_DURATION = 40;
    public static final int MAGMATIC_TEMPERATURE = 1000;
    public static final int MAX_AUGMENTS = 9;
    public static final int MAX_ENCHANT_LEVEL = 10;
    public static final int MAX_FOOD_LEVEL = 20;
    public static final int MB_PER_XP = 20;
    public static final int NETWORK_UPDATE_DISTANCE = 192;
    public static final int RF_PER_FURNACE_UNIT = 10;

    public static final float BASE_CHANCE = 1.0F;
    public static final float BASE_CHANCE_LOCKED = -1.0F;

    public static final int TANK_SMALL = FluidAttributes.BUCKET_VOLUME * 4;
    public static final int TANK_MEDIUM = FluidAttributes.BUCKET_VOLUME * 8;
    public static final int TANK_LARGE = FluidAttributes.BUCKET_VOLUME * 16;

    public static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]{HEAD, CHEST, LEGS, FEET};
    public static final Direction[] DIRECTIONS = Direction.values();
    // endregion

    // region COMMANDS
    public static final String CMD_DURATION = "duration";
    public static final String CMD_FLAG = "flag";
    public static final String CMD_PLAYER = "player";
    public static final String CMD_TARGETS = "targets";
    // endregion

    // region PACKET
    public static final int PACKET_CONTROL = 1;
    public static final int PACKET_GUI = 2;
    public static final int PACKET_REDSTONE = 3;
    public static final int PACKET_STATE = 4;
    public static final int PACKET_CHAT = 12;
    public static final int PACKET_SECURITY = 16;
    public static final int PACKET_FILTER = 17;
    public static final int PACKET_SECURITY_CONTROL = 24;
    public static final int PACKET_REDSTONE_CONTROL = 25;
    public static final int PACKET_TRANSFER_CONTROL = 26;
    public static final int PACKET_SIDE_CONFIG = 27;
    public static final int PACKET_STORAGE_CLEAR = 28;
    public static final int PACKET_KEY_MULTIMODE = 32;
    // endregion

    // region CONSTANTS
    public static final BooleanSupplier TRUE = () -> true;
    public static final BooleanSupplier FALSE = () -> false;

    public static final String DAMAGE_ARROW = "arrow";
    public static final String DAMAGE_PLAYER = "player";

    public static final String KEY_MULTIMODE = "cofh.multimode";

    public static final UUID UUID_ARMOR_TOUGHNESS = UUID.fromString("D1ADBCE5-95CE-470C-BF99-6C59843084A2");

    public static final UUID UUID_EFFECT_CHILLED_MOVEMENT_SPEED = UUID.fromString("D99513AE-6F0E-4987-82DE-80DCBAF058BC");
    public static final UUID UUID_EFFECT_CHILLED_ATTACK_DAMAGE = UUID.fromString("DF93E7E2-5056-49FA-B425-0D8C46902105");

    public static final UUID UUID_ENCH_BULWARK_KNOCKBACK_RESISTANCE = UUID.fromString("DA9976EC-C764-4450-B5AA-1B11B10143E2");
    public static final UUID UUID_ENCH_PHALANX_MOVEMENT_SPEED = UUID.fromString("DBFFDB74-4607-4C2E-9573-A14D6A73397A");
    public static final UUID UUID_ENCH_REACH_DISTANCE = UUID.fromString("D7476206-4A89-4522-9D1D-9BB34076FFDA");
    public static final UUID UUID_ENCH_VITALITY_HEALTH = UUID.fromString("D9239D8A-AC3A-4A0D-8834-D50A80DFEE2B");

    public static final int RGB_DURABILITY_FLUX = 0xD01010;
    public static final int RGB_DURABILITY_WATER = 0x4060FF;
    public static final int RGB_DURABILITY_ENDER = 0x14594D;
    public static final int RGB_DURABILITY_EXP = 0x7AAC52;

    public static float AUG_SCALE_MIN = 0.0F;
    public static float AUG_SCALE_MAX = 100.0F;
    // endregion

    // region TEXTURES
    public static final String PATH_GFX = ID_COFH_CORE + ":textures/";
    public static final String PATH_GUI = PATH_GFX + "gui/";
    public static final String PATH_ELEMENTS = PATH_GUI + "elements/";
    // endregion
}
