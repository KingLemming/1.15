package cofh.thermal.expansion.util.managers.machine;

import cofh.thermal.core.util.managers.SimpleItemRecipeManager;
import net.minecraft.fluid.Fluids;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

public class InsolatorRecipeManager extends SimpleItemRecipeManager.Catalyzed {

    private static final InsolatorRecipeManager INSTANCE = new InsolatorRecipeManager();
    protected static final int DEFAULT_ENERGY = 20000;
    protected static final FluidStack DEFAULT_FLUID = new FluidStack(Fluids.WATER, FluidAttributes.BUCKET_VOLUME / 2);
    protected static float plantMultiplier = 2.0F;
    protected static float tuberMultiplier = 2.5F;
    protected static boolean defaultPlantRecipes = true;

    public static InsolatorRecipeManager instance() {

        return INSTANCE;
    }

    private InsolatorRecipeManager() {

        super(DEFAULT_ENERGY, 4, 0);
    }

    @Override
    public void config() {

    }

}
