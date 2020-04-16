package cofh.thermal.core.util.managers.dynamo;

import cofh.lib.inventory.FalseIInventory;
import cofh.thermal.core.init.ThermalRecipeTypes;
import cofh.thermal.core.util.managers.SingleItemFuelManager;
import cofh.thermal.core.util.recipes.ThermalFuel;
import cofh.thermal.core.util.recipes.internal.IDynamoFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;

import java.util.Map;

import static cofh.lib.util.constants.Constants.RF_PER_FURNACE_UNIT;

public class StirlingFuelManager extends SingleItemFuelManager {

    private static final StirlingFuelManager INSTANCE = new StirlingFuelManager();
    protected static int DEFAULT_ENERGY = 16000;

    public static StirlingFuelManager instance() {

        return INSTANCE;
    }

    private StirlingFuelManager() {

        super(DEFAULT_ENERGY);
    }

    @Override
    public boolean validFuel(ItemStack input) {

        return getEnergy(input) > 0;
    }

    public int getEnergy(ItemStack stack) {

        IDynamoFuel fuel = getFuel(stack);
        return fuel != null ? fuel.getEnergy() : getEnergyFurnaceFuel(stack);
    }

    public int getEnergyFurnaceFuel(ItemStack stack) {

        if (stack.isEmpty()) {
            return 0;
        }
        if (stack.getItem().hasContainerItem(stack)) {
            return 0;
        }
        int energy = ForgeHooks.getBurnTime(stack) * RF_PER_FURNACE_UNIT;
        return energy >= MIN_ENERGY ? energy : 0;
    }

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(ThermalRecipeTypes.FUEL_STIRLING);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addFuel((ThermalFuel) entry.getValue());
        }
    }
    // endregion
}
