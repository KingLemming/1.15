package cofh.thermal.core.util.recipes;

import cofh.core.util.recipes.SerializableRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public abstract class ThermalBoost extends SerializableRecipe {

    protected final Ingredient ingredient;

    protected float boostMult = 1.0F;
    protected int boostCycles = 8;

    protected ThermalBoost(ResourceLocation recipeId, Ingredient inputItem, float boostMult, int boostCycles) {

        super(recipeId);

        this.ingredient = inputItem;

        this.boostMult = boostMult;
        this.boostCycles = boostCycles;
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
