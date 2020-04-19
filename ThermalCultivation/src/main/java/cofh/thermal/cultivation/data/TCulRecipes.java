package cofh.thermal.cultivation.data;

import cofh.lib.data.RecipeProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.cultivation.init.TCulReferences.ID_FROST_MELON;
import static cofh.thermal.cultivation.init.TCulReferences.ID_FROST_MELON_SLICE;

public class TCulRecipes extends RecipeProviderCoFH {

    public TCulRecipes(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
    }

    @Override
    public String getName() {

        return "Thermal Cultivation: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapelessRecipeBuilder.shapelessRecipe(ITEMS.get(seeds(ID_FROST_MELON)))
                .addIngredient(ITEMS.get(ID_FROST_MELON_SLICE))
                .addCriterion("has_frost_melon", hasItem(ITEMS.get(ID_FROST_MELON_SLICE)))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_FROST_MELON))
                .key('M', ITEMS.get(ID_FROST_MELON_SLICE))
                .patternLine("MMM")
                .patternLine("MMM")
                .patternLine("MMM")
                .addCriterion("has_frost_melon", hasItem(ITEMS.get(ID_FROST_MELON_SLICE)))
                .build(consumer);
    }

}
