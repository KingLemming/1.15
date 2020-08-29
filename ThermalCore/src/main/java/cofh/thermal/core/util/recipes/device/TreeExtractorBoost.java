package cofh.thermal.core.util.recipes.device;

import cofh.thermal.core.init.TCoreRecipeTypes;
import cofh.thermal.core.util.recipes.ThermalBoost;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.core.init.TCoreRecipeTypes.ID_BOOST_TREE_EXTRACTOR;

public class TreeExtractorBoost extends ThermalBoost {

    public TreeExtractorBoost(ResourceLocation recipeId, Ingredient inputItem, float boostMult, int boostCycles) {

        super(recipeId, inputItem, boostMult, boostCycles);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_BOOST_TREE_EXTRACTOR);
    }

    @Override
    public IRecipeType<?> getType() {

        return TCoreRecipeTypes.BOOST_TREE_EXTRACTOR;
    }

}
