package cofh.thermal.core.util.recipes;

import net.minecraftforge.fluids.FluidStack;

public class SimpleFluidFuel extends AbstractFuel {

    public SimpleFluidFuel(FluidStack input, int energy) {

        super(energy);
        this.inputFluids.add(input);
    }

}
