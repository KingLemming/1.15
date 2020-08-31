package cofh.thermal.core.util.managers;

import cofh.core.util.ComparableItemStack;
import cofh.core.util.ComparableItemStackNBT;
import net.minecraft.item.ItemStack;

public abstract class AbstractManager implements IManager {

    public static int DEFAULT_ENERGY_MIN = 1000;
    public static int DEFAULT_ENERGY_MAX = 10000000;

    public static final int DEFAULT_SCALE = 100;
    public static final int DEFAULT_SCALE_MIN = 10;
    public static final int DEFAULT_SCALE_MAX = 10000;

    protected int defaultEnergy;
    protected int scaleFactor = 100;

    protected AbstractManager(int defaultEnergy) {

        this.defaultEnergy = defaultEnergy;
    }

    protected AbstractManager setDefaultEnergy(int defaultEnergy) {

        if (defaultEnergy > 0) {
            this.defaultEnergy = defaultEnergy;
        }
        return this;
    }

    protected AbstractManager setScaleFactor(int scaleFactor) {

        if (scaleFactor > 0) {
            this.scaleFactor = scaleFactor;
        }
        return this;
    }

    public static ComparableItemStack convert(ItemStack stack) {

        return new ComparableItemStack(stack);
    }

    public static ComparableItemStack convertNBT(ItemStack stack) {

        return new ComparableItemStackNBT(stack);
    }

    public int getDefaultEnergy() {

        return defaultEnergy;
    }

    public int getDefaultScale() {

        return scaleFactor;
    }

}
