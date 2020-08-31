package cofh.core.fluid;

import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public class FluidStorageInfinite extends FluidStorageCoFH {

    public FluidStorageInfinite(int capacity) {

        super(capacity);
    }

    public FluidStorageInfinite(int capacity, Predicate<FluidStack> validator) {

        super(capacity, validator);
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
        int retAmount = Math.min(fluid.getAmount(), maxDrain);
        return new FluidStack(fluid, retAmount);
    }

}
