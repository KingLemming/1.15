package cofh.thermal.core.util.recipes.machine;

import cofh.thermal.core.init.ThermalRecipeTypes;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.List;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.core.init.ThermalReferences.ID_RECIPE_INSOLATOR;

public class InsolatorRecipe extends ThermalRecipe {

    public InsolatorRecipe(ResourceLocation recipeId, int energy, float experience, List<Ingredient> inputItems, List<FluidStack> inputFluids, List<ItemStack> outputItems, List<Float> outputItemChances, List<FluidStack> outputFluids) {

        super(recipeId, energy, experience, inputItems, inputFluids, outputItems, outputItemChances, outputFluids);
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_RECIPE_INSOLATOR);
    }

    @Nonnull
    @Override
    public IRecipeType<?> getType() {

        return ThermalRecipeTypes.RECIPE_INSOLATOR;
    }

    //    @Nonnull
    //    @Override
    //    public String getGroup() {
    //
    //        return MACHINE_INSOLATOR_BLOCK.getTranslationKey();
    //    }
    //
    //    @Nonnull
    //    @Override
    //    public ItemStack getIcon() {
    //
    //        return new ItemStack(MACHINE_INSOLATOR_BLOCK);
    //    }

}
