package cofh.core.util;

public interface IInventoryCallback {

    default void onInventoryChange(int slot) {

    }

    default void onTankChange(int tank) {

    }

}
