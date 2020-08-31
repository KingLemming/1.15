package cofh.core.energy;

import cofh.core.item.IContainerItem;
import cofh.core.util.helpers.EnergyHelper;
import cofh.core.util.helpers.MathHelper;
import net.minecraft.item.ItemStack;

import static cofh.core.util.constants.NBTTags.TAG_ENERGY;

/**
 * Implement this interface on Item classes that support external manipulation of their internal energy storages.
 * <p>
 * NOTE: Use of NBT data on the containing ItemStack is encouraged.
 *
 * @author King Lemming
 */
public interface IEnergyContainerItem extends IContainerItem {

    default ItemStack setDefaultTag(ItemStack stack, int energy) {

        return EnergyHelper.setDefaultEnergyTag(stack, energy);
    }

    default int getSpace(ItemStack container) {

        return getMaxEnergyStored(container) - getEnergyStored(container);
    }

    default int getScaledEnergyStored(ItemStack container, int scale) {

        return MathHelper.round((double) getEnergyStored(container) * scale / getMaxEnergyStored(container));
    }

    /**
     * Get the amount of energy currently stored in the container item.
     */
    default int getEnergyStored(ItemStack container) {

        if (container.getTag() == null) {
            return 0;
        }
        return Math.min(container.getTag().getInt(TAG_ENERGY), getMaxEnergyStored(container));
    }

    int getExtract(ItemStack container);

    int getReceive(ItemStack container);

    /**
     * Get the max amount of energy that can be stored in the container item.
     */
    int getMaxEnergyStored(ItemStack container);

    /**
     * Adds energy to a container item. Returns the quantity of energy that was accepted. This should always return 0
     * if the item cannot be externally charged.
     *
     * @param container  ItemStack to be charged.
     * @param maxReceive Maximum amount of energy to be sent into the item.
     * @param simulate   If TRUE, the charge will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) received by the item.
     */
    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate);

    /**
     * Removes energy from a container item. Returns the quantity of energy that was removed. This should always
     * return 0 if the item cannot be externally discharged.
     *
     * @param container  ItemStack to be discharged.
     * @param maxExtract Maximum amount of energy to be extracted from the item.
     * @param simulate   If TRUE, the discharge will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) extracted from the item.
     */
    int extractEnergy(ItemStack container, int maxExtract, boolean simulate);

}
