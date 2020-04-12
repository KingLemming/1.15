package cofh.lib.util.control;

import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public interface IReconfigurable {

    SideConfig getSideConfig(Direction side);

    boolean prevSideConfig(Direction side);

    boolean nextSideConfig(Direction side);

    boolean setSideConfig(Direction side, SideConfig config);

    boolean clearAllSides();

    boolean hasInputSide();

    boolean hasOutputSide();

    /**
     * This returns whether or not reconfiguration functionality is enabled at all.
     */
    default boolean isReconfigurable() {

        return true;
    }

    EnumProperty<SideConfig> RECONFIG_NORTH = EnumProperty.create("config_north", SideConfig.class);
    EnumProperty<SideConfig> RECONFIG_EAST = EnumProperty.create("config_east", SideConfig.class);
    EnumProperty<SideConfig> RECONFIG_SOUTH = EnumProperty.create("config_south", SideConfig.class);
    EnumProperty<SideConfig> RECONFIG_WEST = EnumProperty.create("config_west", SideConfig.class);
    EnumProperty<SideConfig> RECONFIG_DOWN = EnumProperty.create("config_down", SideConfig.class);
    EnumProperty<SideConfig> RECONFIG_UP = EnumProperty.create("config_up", SideConfig.class);

    // region CONFIGS
    enum SideConfig implements IStringSerializable {

        SIDE_NONE("none", false, false),
        SIDE_INPUT("input", true, false),
        SIDE_OUTPUT("output", false, true),
        SIDE_BOTH("both", true, true),
        SIDE_ACCESSIBLE("accessible", false, false);

        public static final SideConfig[] VALUES = values();

        private final String name;
        private final boolean input;
        private final boolean output;

        SideConfig(String name, boolean input, boolean output) {

            this.name = name;
            this.input = input;
            this.output = output;
        }

        public boolean isInput() {

            return input;
        }

        public boolean isOutput() {

            return output;
        }

        SideConfig prev() {

            switch (this) {
                case SIDE_INPUT:
                    return SIDE_NONE;
                case SIDE_OUTPUT:
                    return SIDE_INPUT;
                case SIDE_BOTH:
                    return SIDE_OUTPUT;
                case SIDE_ACCESSIBLE:
                    return SIDE_BOTH;
                default:
                    return SIDE_ACCESSIBLE;
            }
        }

        SideConfig next() {

            switch (this) {
                case SIDE_NONE:
                    return SIDE_INPUT;
                case SIDE_INPUT:
                    return SIDE_OUTPUT;
                case SIDE_OUTPUT:
                    return SIDE_BOTH;
                case SIDE_BOTH:
                    return SIDE_ACCESSIBLE;
                default:
                    return SIDE_NONE;
            }
        }

        @Override
        public String toString() {

            return this.getName();
        }

        @Override
        public String getName() {

            return this.name;
        }
    }
    // endregion
}
