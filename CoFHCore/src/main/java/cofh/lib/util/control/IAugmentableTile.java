package cofh.lib.util.control;

import cofh.lib.tileentity.ITileCallback;

public interface IAugmentableTile extends IAugmentable, ITileCallback {

    AugmentControlModule augmentControl();

    // region IAugmentControl

    @Override
    default boolean isAugmentable() {

        return augmentControl().isAugmentable();
    }
    // endregion
}
