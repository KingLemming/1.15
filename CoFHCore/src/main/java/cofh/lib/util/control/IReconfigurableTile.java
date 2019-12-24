package cofh.lib.util.control;

import cofh.lib.tileentity.ITileCallback;
import net.minecraft.util.Direction;

public interface IReconfigurableTile extends IReconfigurable, ITileCallback {

    ReconfigControlModule reconfigControl();

    Direction getFacing();

    // region IReconfigurable
    @Override
    default SideConfig getSideConfig(Direction side) {

        return reconfigControl().getSideConfig(side);
    }

    @Override
    default boolean prevSideConfig(Direction side) {

        return reconfigControl().prevSideConfig(side);
    }

    @Override
    default boolean nextSideConfig(Direction side) {

        return reconfigControl().nextSideConfig(side);
    }

    @Override
    default boolean setSideConfig(Direction side, SideConfig config) {

        return reconfigControl().setSideConfig(side, config);
    }

    @Override
    default boolean clearAllSides() {

        return reconfigControl().clearAllSides();
    }

    @Override
    default boolean hasInputSide() {

        return reconfigControl().hasInputSide();
    }

    @Override
    default boolean hasOutputSide() {

        return reconfigControl().hasOutputSide();
    }
    // endregion
}
