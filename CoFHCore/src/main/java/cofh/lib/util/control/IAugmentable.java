package cofh.lib.util.control;

public interface IAugmentable {

    //    boolean installAugment(ItemStack augment);
    //
    //    CompoundNBT getAugmentProperties();

    /**
     * This returns whether or not augment functionality is enabled at all.
     */
    default boolean isAugmentable() {

        return true;
    }

}
