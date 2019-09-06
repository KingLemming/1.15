package cofh.lib.fluid;

import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class FluidStackHolder implements IFluidStackAccess {

    protected final FluidStack stack;

    public FluidStackHolder(FluidStack stack) {

        this.stack = stack;
    }

    @Nonnull
    @Override
    public FluidStack getFluidStack() {

        return stack;
    }

    @Override
    public int getAmount() {

        // TODO: Fix
        // return stack.getAmount();
        if (isEmpty()) {
            return 0;
        }
        return stack.amount;
    }

    @Override
    public boolean isEmpty() {

        // TODO: Fix
        // return stack.isEmpty();
        return stack == null || stack.amount <= 0;
    }

}
