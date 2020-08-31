package cofh.thermal.expansion.util.managers.dynamo;

import cofh.core.inventory.FalseIInventory;
import cofh.thermal.core.util.managers.SingleItemFuelManager;
import cofh.thermal.core.util.recipes.ThermalFuel;
import cofh.thermal.core.util.recipes.internal.IDynamoFuel;
import cofh.thermal.expansion.init.TExpRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;

import java.util.Map;

import static cofh.core.util.constants.Constants.RF_PER_FURNACE_UNIT;

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
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipeTypes.FUEL_STIRLING);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addFuel((ThermalFuel) entry.getValue());
        }
    }
    // endregion

    //    // region CONVERSION
    //    protected List<StirlingFuel> convertedFuels = new ArrayList<>();
    //
    //    public List<StirlingFuel> getConvertedFuels() {
    //
    //        return convertedFuels;
    //    }
    //
    //    protected void createConvertedRecipes(RecipeManager recipeManager) {
    //
    //        for (IRecipe<IInventory> recipe : recipeManager.getRecipes(IRecipeType.SMELTING).values()) {
    //            createConvertedRecipe((AbstractCookingRecipe) recipe);
    //        }
    //    }
    //
    //    protected boolean createConvertedRecipe(AbstractCookingRecipe recipe) {
    //
    //        if (recipe.isDynamic() || recipe.getRecipeOutput().isEmpty()) {
    //            return false;
    //        }
    //        convertedRecipes.add(convert(recipe));
    //        return true;
    //    }
    //
    //    protected FurnaceRecipe convert(AbstractCookingRecipe recipe) {
    //
    //        ItemStack recipeOutput = recipe.getRecipeOutput();
    //        float experience = recipe.getExperience();
    //        int energy = defaultFoodRecipes && recipeOutput.getItem().isFood() ? defaultEnergy / 2 : defaultEnergy;
    //        return new FurnaceRecipe(new ResourceLocation(ID_THERMAL, "dyn_stirling_" + recipe.getIngredients().get(0).hashCode()), energy, experience, recipe);
    //    }
    //    // endregion
}
