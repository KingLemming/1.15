package cofh.lib.util.control;

import net.minecraft.util.Direction;

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

    // region CONFIGS
    enum SideConfig {

        SIDE_NONE(false, false), SIDE_INPUT(true, false), SIDE_OUTPUT(false, true), SIDE_BOTH(true, true), SIDE_ACCESSIBLE(false, false);

        public static final SideConfig[] VALUES = values();

        boolean input;
        boolean output;

        SideConfig(boolean input, boolean output) {

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
    }
    // endregion
}
