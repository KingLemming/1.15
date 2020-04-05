package cofh.thermal.core.util.managers;

import cofh.lib.util.ComparableItemStack;
import cofh.lib.util.ComparableItemStackNBT;
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

    protected ComparableItemStack convert(ItemStack stack) {

        return new ComparableItemStack(stack);
    }

    protected ComparableItemStack convertNBT(ItemStack stack) {

        return new ComparableItemStackNBT(stack);
    }

    public int getDefaultEnergy() {

        return defaultEnergy;
    }

    public int getDefaultScale() {

        return scaleFactor;
    }

}
