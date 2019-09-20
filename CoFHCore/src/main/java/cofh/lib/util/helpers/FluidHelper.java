package cofh.lib.util.helpers;

import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;

public class FluidHelper {

    public static HashMap<Fluid, Integer> colorCache = new HashMap<>();

    public static int getFluidColor(FluidStack stack) {

        int color = -1;
        if (stack != null && stack.getFluid() != null) {
            color = colorCache.getOrDefault(stack.getFluid().getAttributes().getStill(stack), stack.getFluid().getAttributes().getColor(stack) != 0xffffffff ? stack.getFluid().getAttributes().getColor(stack) : ColorHelper.getColorFrom(stack.getFluid().getAttributes().getStill(stack)));
            if (!colorCache.containsKey(stack.getFluid())) colorCache.put(stack.getFluid(), color);
        }
        return color;
    }

    public static int getFluidColor(Fluid fluid) {

        int color = -1;
        if (fluid != null) {
            color = colorCache.getOrDefault(fluid, fluid.getAttributes().getColor() != 0xffffffff ? fluid.getAttributes().getColor() : ColorHelper.getColorFrom(fluid.getAttributes().getStillTexture()));
            if (!colorCache.containsKey(fluid)) colorCache.put(fluid, color);
        }
        return color;
    }

}