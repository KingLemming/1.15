package cofh.thermal.core.util.recipes.device;

import cofh.core.util.recipes.SerializableRecipe;
import cofh.thermal.core.init.TCoreRecipeTypes;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.core.init.TCoreRecipeTypes.ID_BOOST_TREE_EXTRACTOR;

public class TreeExtractorBoost extends SerializableRecipe {

    protected final Ingredient ingredient;

    protected float boostMult = 1.0F;
    protected int boostCycles = 8;

    protected TreeExtractorBoost(ResourceLocation recipeId, Ingredient inputItem, float boostMult, int boostCycles) {

        super(recipeId);

        this.ingredient = inputItem;

        this.boostMult = boostMult;
        this.boostCycles = boostCycles;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_BOOST_TREE_EXTRACTOR);
    }

    @Override
    public IRecipeType<?> getType() {

        return TCoreRecipeTypes.BOOST_TREE_EXTRACTOR;
    }

    // region GETTERS
    public Ingredient getIngredient() {

        return ingredient;
    }

    public float getBoostMult() {

        return boostMult;
    }

    public int getBoostCycles() {

        return boostCycles;
    }
    // endregion
}
