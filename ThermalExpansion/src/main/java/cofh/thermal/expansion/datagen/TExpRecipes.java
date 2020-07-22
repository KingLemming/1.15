package cofh.thermal.expansion.datagen;

import cofh.lib.data.RecipeProviderCoFH;
import cofh.thermal.core.datagen.ThermalFeatureRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TExpRecipes extends RecipeProviderCoFH {

    public TExpRecipes(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
    }

    @Override
    public String getName() {

        return "Thermal Expansion: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        ThermalFeatureRecipes.generateBasicPartsRecipes(this, consumer);
        ThermalFeatureRecipes.generateToolPartsRecipes(this, consumer);
    }

}