package cofh.thermal.expansion.util.recipes;

import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.expansion.init.TExpRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.expansion.init.TExpReferences.ID_RECIPE_CRUCIBLE;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CRUCIBLE_BLOCK;

public class CrucibleRecipe extends ThermalRecipe {

    public CrucibleRecipe(ResourceLocation recipeId, int energy, float experience, @Nullable List<Ingredient> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> outputItemChances, @Nullable List<FluidStack> outputFluids) {

        super(recipeId, energy, experience, inputItems, inputFluids, outputItems, outputItemChances, outputFluids);
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_RECIPE_CRUCIBLE);
    }

    @Nonnull
    @Override
    public IRecipeType<?> getType() {

        return TExpRecipes.RECIPE_CRUCIBLE;
    }

    @Nonnull
    @Override
    public String getGroup() {

        return MACHINE_CRUCIBLE_BLOCK.getTranslationKey();
    }

    @Nonnull
    @Override
    public ItemStack getIcon() {

        return new ItemStack(MACHINE_CRUCIBLE_BLOCK);
    }

}
