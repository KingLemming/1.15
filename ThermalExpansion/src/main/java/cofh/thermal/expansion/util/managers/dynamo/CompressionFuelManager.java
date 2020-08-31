package cofh.thermal.expansion.util.managers.dynamo;

import cofh.core.inventory.FalseIInventory;
import cofh.thermal.core.util.managers.SingleFluidFuelManager;
import cofh.thermal.core.util.recipes.ThermalFuel;
import cofh.thermal.core.util.recipes.internal.IDynamoFuel;
import cofh.thermal.expansion.init.TExpRecipeTypes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map;

public class CompressionFuelManager extends SingleFluidFuelManager {

    private static final CompressionFuelManager INSTANCE = new CompressionFuelManager();
    protected static int DEFAULT_ENERGY = 100000;

    public static CompressionFuelManager instance() {

        return INSTANCE;
    }

    private CompressionFuelManager() {

        super(DEFAULT_ENERGY);
    }

    public int getEnergy(FluidStack stack) {

        IDynamoFuel fuel = getFuel(stack);
        return fuel != null ? fuel.getEnergy() : 0;
    }

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipeTypes.FUEL_COMPRESSION);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addFuel((ThermalFuel) entry.getValue());
        }
    }
    // endregion
}
