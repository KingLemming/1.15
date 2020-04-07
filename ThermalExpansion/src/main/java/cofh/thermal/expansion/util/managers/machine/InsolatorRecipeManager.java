package cofh.thermal.expansion.util.managers.machine;

import cofh.lib.inventory.FalseIInventory;
import cofh.thermal.core.util.managers.SimpleItemRecipeManager;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.expansion.init.TExpRecipes;
import cofh.thermal.expansion.util.recipes.InsolatorRecipe;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map;

public class InsolatorRecipeManager extends SimpleItemRecipeManager.Catalyzed {

    private static final InsolatorRecipeManager INSTANCE = new InsolatorRecipeManager();
    protected static final int DEFAULT_ENERGY = 20000;
    protected static final FluidStack DEFAULT_FLUID = new FluidStack(Fluids.WATER, FluidAttributes.BUCKET_VOLUME / 2);

    protected static float plantMultiplier = 2.0F;
    protected static float tuberMultiplier = 2.5F;
    protected static boolean defaultPlantRecipes = true;

    protected int defaultWater = FluidAttributes.BUCKET_VOLUME / 2;

    public static InsolatorRecipeManager instance() {

        return INSTANCE;
    }

    private InsolatorRecipeManager() {

        super(DEFAULT_ENERGY, 4, 0);
    }

    public int getDefaultWater() {

        return defaultWater;
    }

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipes.RECIPE_INSOLATOR);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            convertRecipe((InsolatorRecipe) entry.getValue());
        }
    }
    // endregion
}
