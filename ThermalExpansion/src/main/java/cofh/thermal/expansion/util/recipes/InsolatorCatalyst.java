package cofh.thermal.expansion.util.recipes;

import cofh.thermal.core.util.recipes.ThermalCatalyst;
import cofh.thermal.expansion.init.TExpRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.expansion.init.TExpReferences.ID_CATALYST_INSOLATOR;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_INSOLATOR_BLOCK;

public class InsolatorCatalyst extends ThermalCatalyst {

    public InsolatorCatalyst(ResourceLocation recipeId, Ingredient ingredient, float primaryMod, float secondaryMod, float energyMod, float minChance, float useChance) {

        super(recipeId, ingredient, primaryMod, secondaryMod, energyMod, minChance, useChance);
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_CATALYST_INSOLATOR);
    }

    @Nonnull
    @Override
    public IRecipeType<?> getType() {

        return TExpRecipes.CATALYST_INSOLATOR;
    }

    @Nonnull
    @Override
    public String getGroup() {

        return MACHINE_INSOLATOR_BLOCK.getTranslationKey();
    }

    @Nonnull
    @Override
    public ItemStack getIcon() {

        return new ItemStack(MACHINE_INSOLATOR_BLOCK);
    }

}
