package cofh.thermal.expansion.util.recipes.machine;

import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.expansion.util.recipes.TExpRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;
import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.expansion.util.recipes.TExpRecipeTypes.ID_RECIPE_FURNACE;

public class FurnaceRecipe extends ThermalRecipe {

    public FurnaceRecipe(ResourceLocation recipeId, int energy, float experience, int minTicks, @Nullable List<Ingredient> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> outputItemChances, @Nullable List<FluidStack> outputFluids) {

        super(recipeId, energy, experience, minTicks, inputItems, inputFluids, outputItems, outputItemChances, outputFluids);
    }

    public FurnaceRecipe(ResourceLocation recipeId, int energy, float experience, AbstractCookingRecipe recipe) {

        this(recipeId, energy, experience, -1, recipe.getIngredients(), Collections.emptyList(), Collections.singletonList(recipe.getRecipeOutput()), Collections.singletonList(BASE_CHANCE_LOCKED), Collections.emptyList());
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_RECIPE_FURNACE);
    }

    @Nonnull
    @Override
    public IRecipeType<?> getType() {

        return TExpRecipeTypes.RECIPE_FURNACE;
    }

}
