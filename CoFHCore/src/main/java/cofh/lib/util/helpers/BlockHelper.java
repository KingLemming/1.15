package cofh.lib.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumMap;

/**
 * Contains various helper functions to assist with {@link Block} and Block-related manipulation and interaction.
 *
 * @author King Lemming
 */
public class BlockHelper {

    private BlockHelper() {

    }

    public static final Direction[] DIR_VALUES = Direction.values();

    public static final byte[] SIDE_LEFT = {4, 5, 5, 4, 2, 3};
    public static final byte[] SIDE_RIGHT = {5, 4, 4, 5, 3, 2};
    public static final byte[] SIDE_OPPOSITE = {1, 0, 3, 2, 5, 4};
    public static final byte[] SIDE_ABOVE = {3, 2, 1, 1, 1, 1};
    public static final byte[] SIDE_BELOW = {2, 3, 0, 0, 0, 0};

    private static final EnumMap<Direction, Direction> SIDE_LEFT_LOOKUP = computeMap(SIDE_LEFT);
    private static final EnumMap<Direction, Direction> SIDE_RIGHT_LOOKUP = computeMap(SIDE_RIGHT);
    private static final EnumMap<Direction, Direction> SIDE_OPPOSITE_LOOKUP = computeMap(SIDE_OPPOSITE);
    private static final EnumMap<Direction, Direction> SIDE_ABOVE_LOOKUP = computeMap(SIDE_ABOVE);
    private static final EnumMap<Direction, Direction> SIDE_BELOW_LOOKUP = computeMap(SIDE_BELOW);

    // These assume facing is towards negative - looking AT side 1, 3, or 5.
    public static final byte[] ROTATE_CLOCK_Y = {0, 1, 4, 5, 3, 2};
    public static final byte[] ROTATE_CLOCK_Z = {5, 4, 2, 3, 0, 1};
    public static final byte[] ROTATE_CLOCK_X = {2, 3, 1, 0, 4, 5};

    public static final byte[] ROTATE_COUNTER_Y = {0, 1, 5, 4, 2, 3};
    public static final byte[] ROTATE_COUNTER_Z = {4, 5, 2, 3, 1, 0};
    public static final byte[] ROTATE_COUNTER_X = {3, 2, 0, 1, 4, 5};

    public static final byte[] INVERT_AROUND_Y = {0, 1, 3, 2, 5, 4};
    public static final byte[] INVERT_AROUND_Z = {1, 0, 2, 3, 5, 4};
    public static final byte[] INVERT_AROUND_X = {1, 0, 3, 2, 4, 5};

    // region TILE ENTITIES
    public static TileEntity getAdjacentTileEntity(World world, BlockPos pos, Direction dir) {

        pos = pos.offset(dir);
        return world == null || !world.isBlockLoaded(pos) ? null : world.getTileEntity(pos);
    }

    public static TileEntity getAdjacentTileEntity(World world, BlockPos pos, int side) {

        return world == null ? null : getAdjacentTileEntity(world, pos, DIR_VALUES[side]);
    }

    public static TileEntity getAdjacentTileEntity(TileEntity refTile, Direction dir) {

        return refTile == null ? null : getAdjacentTileEntity(refTile.getWorld(), refTile.getPos(), dir);
    }
    // endregion

    // region ROTATION
    public static Direction left(Direction face) {

        return SIDE_LEFT_LOOKUP.get(face);
    }

    public static Direction right(Direction face) {

        return SIDE_RIGHT_LOOKUP.get(face);
    }

    public static Direction opposite(Direction face) {

        return SIDE_OPPOSITE_LOOKUP.get(face);
    }

    public static Direction above(Direction face) {

        return SIDE_ABOVE_LOOKUP.get(face);
    }

    public static Direction below(Direction face) {

        return SIDE_BELOW_LOOKUP.get(face);
    }
    // endregion

    // region INTERNAL

    // Convert a byte[] side lookup to an EnumMap.
    private static EnumMap<Direction, Direction> computeMap(byte[] arr) {

        EnumMap<Direction, Direction> map = new EnumMap<>(Direction.class);
        for (int i = 0; i < 6; i++) {
            map.put(DIR_VALUES[i], DIR_VALUES[arr[i]]);
        }
        return map;
    }
    // endregion
}
