package cofh.lib.util;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fluids.Fluid;

import java.util.UUID;

public class Constants {

    private Constants() {

    }

    // region AABBs
    // @formatter:off
    public static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
    // @formatter:on
    // endregion

    // region MOD IDS
    public static final String ID_COFH = "cofh";
    public static final String ID_COFH_CORE = "cofh_core";
    public static final String ID_COFH_LIB = "cofh_lib";

    public static final String ID_THERMAL_SERIES = "thermal";

    public static final String ID_THERMAL_ATOMICS = "thermal_atomics";
    public static final String ID_THERMAL_CULTIVATION = "thermal_cultivation";
    public static final String ID_THERMAL_DYNAMICS = "thermal_dynamics";
    public static final String ID_THERMAL_FOUNDATION = "thermal_foundation";
    public static final String ID_THERMAL_EXPANSION = "thermal_expansion";
    public static final String ID_THERMAL_INNOVATION = "thermal_innovation";
    public static final String ID_THERMAL_LOCOMOTION = "thermal_locomotion";

    public static final String ID_ENSORCELLMENT = "ensorcellment";
    public static final String ID_ENSTORAGEMENT = "enstoragement";
    public static final String ID_ORBULATION = "orbulation";
    public static final String ID_QUARTERMASTERY = "quartermastery";
    public static final String ID_REDSTONE_ARSENAL = "redstonearsenal";
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

    public static final int TANK_SMALL = Fluid.BUCKET_VOLUME * 4;
    public static final int TANK_MEDIUM = Fluid.BUCKET_VOLUME * 8;
    public static final int TANK_LARGE = Fluid.BUCKET_VOLUME * 10;
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
    public static final int PACKET_KEY = 32;
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

    // region TAGS
    public static final String TAG_ACCESS = "Access";
    public static final String TAG_ACCESSIBLE = "Accessible";
    public static final String TAG_ACTIVE = "Active";
    public static final String TAG_ACTIVE_TRACK = "WasActive";
    public static final String TAG_AMOUNT = "Amount";
    public static final String TAG_ARROWS = "Arrows";
    public static final String TAG_AUGMENTS = "Augments";
    public static final String TAG_COLORS = "Colors";
    public static final String TAG_COOLANT = "Coolant";
    public static final String TAG_CREATIVE = "Creative";
    public static final String TAG_ENCHANTMENTS = "Enchantments";
    public static final String TAG_STORED_ENCHANTMENTS = "StoredEnchantments";
    public static final String TAG_ENERGY = "Energy";
    public static final String TAG_EXP = "Experience";
    public static final String TAG_FUEL = "Fuel";
    public static final String TAG_FILTER = "Filter";
    public static final String TAG_FLUID = "Fluid";
    public static final String TAG_INDEX = "Index";
    public static final String TAG_INVENTORY = "Inventory";
    public static final String TAG_MODE = "Mode";
    public static final String TAG_OWNER_NAME = "OwnerName";
    public static final String TAG_OWNER_UUID = "OwnerUUID";
    public static final String TAG_POTION = "Potion";
    public static final String TAG_PROCESS = "Proc";
    public static final String TAG_PROCESS_MAX = "ProcMax";
    public static final String TAG_REDSTONE = "Redstone";
    public static final String TAG_RENDER_FLUID = "RenderFluid";
    public static final String TAG_RS_MODE = "RSMode";
    public static final String TAG_RS_POWER = "RSPower";
    public static final String TAG_RS_THRESHOLD = "RSThreshold";
    public static final String TAG_SECURE = "Secure";
    public static final String TAG_SECURITY = "Security";
    public static final String TAG_SKULL_OWNER = "SkullOwner";
    public static final String TAG_SIDE_CONFIG = "SideConfig";
    public static final String TAG_SIDES = "Sides";
    public static final String TAG_SLOT = "Slot";
    public static final String TAG_TANK = "Tank";
    public static final String TAG_TANK_ARRAY = "TankArray";
    public static final String TAG_TRACK_IN = "TrackIn";
    public static final String TAG_TRACK_OUT = "TrackOut";
    public static final String TAG_TRANSFER = "Transfer";
    public static final String TAG_TYPE = "Type";

    public static final String TAG_DEMAGNETIZE_COMPAT = "AllowMachineRemoteMovement";
    public static final String TAG_CONVEYOR_COMPAT = "PreventRemoteMovement";
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

    public static final UUID UUID_KNOCKBACK_RESISTANCE = UUID.fromString("DA9976EC-C764-4450-B5AA-1B11B10143E2");
    public static final UUID UUID_MOVEMENT_SPEED = UUID.fromString("DBFFDB74-4607-4C2E-9573-A14D6A73397A");

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
