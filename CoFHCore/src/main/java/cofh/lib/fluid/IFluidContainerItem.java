package cofh.lib.fluid;

import cofh.lib.item.IContainerItem;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

import static cofh.lib.util.constants.NBTTags.TAG_FLUID;

/**
 * Implement this interface on Item classes that support external manipulation of their internal
 * fluid storage.
 * <p>
 * NOTE: Use of NBT data on the containing ItemStack is encouraged.
 *
 * @author King Lemming
 */
public interface IFluidContainerItem extends IContainerItem {

    default int getSpace(ItemStack container) {

        return getCapacity(container) - getFluidAmount(container);
    }

    default int getScaledFluidStored(ItemStack container, int scale) {

        return MathHelper.round((double) getFluidAmount(container) * scale / getCapacity(container));
    }

    default int getFluidAmount(ItemStack container) {

        return getFluid(container).getAmount();
    }

    /**
     * @param container ItemStack which is the fluid container.
     * @return FluidStack representing the fluid in the container, EMPTY if the container is empty.
     */
    default FluidStack getFluid(ItemStack container) {

        CompoundNBT tag = container.getOrCreateTag();
        if (!tag.contains(TAG_FLUID)) {
            return FluidStack.EMPTY;
        }
        return FluidStack.loadFluidStackFromNBT(tag.getCompound(TAG_FLUID));
    }

    /**
     * @param container ItemStack which is the fluid container.
     * @param resource  FluidStack being queried.
     * @return TRUE if the fluid is valid in this particular container.
     */
    boolean isFluidValid(ItemStack container, FluidStack resource);

    /**
     * @param container ItemStack which is the fluid container.
     * @return Capacity of this fluid container.
     */
    int getCapacity(ItemStack container);

    /**
     * @param container ItemStack which is the fluid container.
     * @param resource  FluidStack attempting to fill the container.
     * @param action    If SIMULATE, the fill will only be simulated.
     * @return Amount of fluid that was (or would have been, if simulated) filled into the container.
     */
    int fill(ItemStack container, FluidStack resource, FluidAction action);

    /**
     * @param container ItemStack which is the fluid container.
     * @param maxDrain  Maximum amount of fluid to be removed from the container.
     * @param action    If SIMULATE, the drain will only be simulated.
     * @return Fluidstack holding the amount of fluid that was (or would have been, if simulated) drained from the
     * container.
     */
    FluidStack drain(ItemStack container, int maxDrain, FluidAction action);

}
