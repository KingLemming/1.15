package cofh.lib.fluid;

import cofh.lib.util.IResourceStorage;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

/**
 * Implementation of a Fluid Storage object. Does NOT implement {@link IFluidTank}.
 *
 * @author King Lemming
 */
public class FluidStorageCoFH implements IFluidHandler, IFluidStackAccess, IResourceStorage {

    protected Predicate<FluidStack> validator;

    @Nonnull
    protected FluidStack fluid = FluidStack.EMPTY;
    protected int capacity = FluidAttributes.BUCKET_VOLUME;

    public FluidStorageCoFH() {

        this(e -> true);
    }

    public FluidStorageCoFH(int capacity) {

        this(capacity, e -> true);
    }

    public FluidStorageCoFH(Predicate<FluidStack> validator) {

        this.validator = validator;
    }

    public FluidStorageCoFH(int capacity, Predicate<FluidStack> validator) {

        this.capacity = capacity;
        this.validator = validator;
    }

    public FluidStorageCoFH setCapacity(int capacity) {

        this.capacity = capacity;
        return this;
    }

    public FluidStorageCoFH setValidator(Predicate<FluidStack> validator) {

        if (validator != null) {
            this.validator = validator;
        }
        return this;
    }

    public boolean isFluidValid(@Nonnull FluidStack stack) {

        return validator.test(stack);
    }

    public void setFluidStack(FluidStack stack) {

        this.fluid = stack;
    }

    // region NBT
    public FluidStorageCoFH readFromNBT(CompoundNBT nbt) {

        FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);
        setFluidStack(fluid);
        return this;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt) {

        fluid.writeToNBT(nbt);
        return nbt;
    }
    // endregion

    // region IFluidStackAccess
    @Override
    public FluidStack getFluidStack() {

        return fluid;
    }

    @Override
    public int getAmount() {

        return fluid.getAmount();
    }

    @Override
    public boolean isEmpty() {

        return fluid.isEmpty();
    }
    // endregion

    // region IFluidHandler
    @Override
    public int getTanks() {

        return 1;
    }

    @Nonnull
    @Override
    public FluidStack getFluidInTank(int tank) {

        return fluid;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {

        if (resource.isEmpty() || !isFluidValid(resource)) {
            return 0;
        }
        if (action.simulate()) {
            if (fluid.isEmpty()) {
                return Math.min(capacity, resource.getAmount());
            }
            if (!fluid.isFluidEqual(resource)) {
                return 0;
            }
            return Math.min(capacity - fluid.getAmount(), resource.getAmount());
        }
        if (fluid.isEmpty()) {
            fluid = new FluidStack(resource, Math.min(capacity, resource.getAmount()));
            return fluid.getAmount();
        }
        if (!fluid.isFluidEqual(resource)) {
            return 0;
        }
        int filled = capacity - fluid.getAmount();

        if (resource.getAmount() < filled) {
            fluid.grow(resource.getAmount());
            filled = resource.getAmount();
        } else {
            fluid.setAmount(capacity);
        }
        return filled;
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {

        if (resource.isEmpty() || !resource.isFluidEqual(fluid)) {
            return FluidStack.EMPTY;
        }
        return drain(resource.getAmount(), action);
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {

        if (maxDrain <= 0 || fluid.isEmpty()) {
            return FluidStack.EMPTY;
        }
        int drained = maxDrain;
        if (fluid.getAmount() < drained) {
            drained = fluid.getAmount();
        }
        FluidStack stack = new FluidStack(fluid, drained);
        if (action.execute()) {
            fluid.shrink(drained);
            if (fluid.isEmpty()) {
                setFluidStack(FluidStack.EMPTY);
            }
        }
        return stack;
    }

    @Override
    public int getTankCapacity(int tank) {

        return capacity;
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {

        return isFluidValid(stack);
    }
    // endregion

    // IResourceStorage
    @Override
    public void modify(int amount) {

        this.fluid.grow(amount);
        if (this.fluid.isEmpty()) {
            this.fluid = FluidStack.EMPTY;
        }
    }

    @Override
    public int getCapacity() {

        return getTankCapacity(0);
    }

    @Override
    public int getStored() {

        return fluid.getAmount();
    }

    @Override
    public String getUnit() {

        return "mB";
    }
    // endregion
}
