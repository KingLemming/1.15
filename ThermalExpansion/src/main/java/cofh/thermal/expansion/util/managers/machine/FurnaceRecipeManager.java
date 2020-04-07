package cofh.thermal.expansion.util.managers.machine;

import cofh.lib.inventory.FalseIInventory;
import cofh.thermal.core.ThermalCore;
import cofh.thermal.core.util.managers.SingleItemRecipeManager;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.expansion.init.TExpRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class FurnaceRecipeManager extends SingleItemRecipeManager {

    private static final FurnaceRecipeManager INSTANCE = new FurnaceRecipeManager();
    protected static final int DEFAULT_ENERGY = 2000;

    protected static boolean defaultFurnaceRecipes = true;
    protected static boolean defaultDustRecipes = true;
    protected static boolean defaultFoodRecipes = true;

    public static FurnaceRecipeManager instance() {

        return INSTANCE;
    }

    private FurnaceRecipeManager() {

        super(DEFAULT_ENERGY, 1, 0);
    }

    // region IManager
    @Override
    public void config() {

        //        String category = "Machines.Furnace";
        //        String comment;
        //
        //        comment = "Adjust this value to change the default energy value for this machine's recipes.";
        //        int defaultEnergy = config.getInt("Default Energy", category, DEFAULT_ENERGY, DEFAULT_ENERGY_MIN, DEFAULT_ENERGY_MAX, comment);
        //
        //        comment = "Adjust this value to change the relative energy cost of all of this machine's recipes. Scale is in percentage.";
        //        int scaleFactor = config.getInt("Energy Factor", category, DEFAULT_SCALE, DEFAULT_SCALE_MIN, DEFAULT_SCALE_MAX, comment);
        //
        //        comment = "If TRUE, default Minecraft Furnace recipes will be available for this machine.";
        //        defaultFurnaceRecipes = config.getBoolean("Default Furnace Recipes", category, defaultFurnaceRecipes, comment);
        //
        //        comment = "If TRUE, reduced cost Dust -> Ingot recipes will be created.";
        //        defaultDustRecipes = config.getBoolean("Reduced Dust -> Ingot Cost", category, defaultDustRecipes, comment);
        //
        //        comment = "If TRUE, reduced cost Food recipes will be created.";
        //        defaultFoodRecipes = config.getBoolean("Reduced Food Cost", category, defaultFoodRecipes, comment);
        //
        //        setDefaultEnergy(defaultEnergy);
        //        setScaleFactor(scaleFactor);
    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        if (defaultFurnaceRecipes) {
            Map<ResourceLocation, IRecipe<IInventory>> smeltingRecipes = recipeManager.getRecipes(IRecipeType.SMELTING);
            for (Map.Entry<ResourceLocation, IRecipe<IInventory>> entry : smeltingRecipes.entrySet()) {
                AbstractCookingRecipe smeltingRecipe = (AbstractCookingRecipe) entry.getValue();
                ItemStack recipeOutput = smeltingRecipe.getRecipeOutput();
                float experience = smeltingRecipe.getExperience();
                if (!smeltingRecipe.isDynamic() && !recipeOutput.isEmpty()) {
                    int energy = defaultFoodRecipes && recipeOutput.getItem().isFood() ? defaultEnergy / 2 : defaultEnergy;
                    NonNullList<Ingredient> ingredients = smeltingRecipe.getIngredients();
                    if (ingredients.isEmpty()) {
                        // TODO: Log an error.
                        continue;
                    }
                    for (ItemStack recipeInput : ingredients.get(0).getMatchingStacks()) {
                        addRecipe(energy, experience, recipeInput, recipeOutput);
                        ThermalCore.LOG.debug("Furnace - Added: " + recipeInput.toString() + " -> " + recipeOutput.toString() + " for " + energy + " RF");
                    }
                }
            }
        }
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipes.RECIPE_FURNACE);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addRecipe((ThermalRecipe) entry.getValue());
        }
    }
    // endregion
}
