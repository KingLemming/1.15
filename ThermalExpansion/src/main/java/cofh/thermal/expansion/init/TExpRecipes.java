package cofh.thermal.expansion.init;

import cofh.thermal.core.util.recipes.ThermalRecipeType;
import cofh.thermal.expansion.util.recipes.FurnaceRecipe;
import cofh.thermal.expansion.util.recipes.InsolatorRecipe;
import cofh.thermal.expansion.util.recipes.PulverizerRecipe;
import cofh.thermal.expansion.util.recipes.SawmillRecipe;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.expansion.init.TExpReferences.*;

public class TExpRecipes {

    private TExpRecipes() {

    }

    public static void register() {

        // Recipes are self-registered as they do not currently have a proper Forge Registry.
        // TODO: Convert when one is added.
        RECIPE_FURNACE.register();
        RECIPE_SAWMILL.register();
        RECIPE_PULVERIZER.register();
        RECIPE_INSOLATOR.register();
        //        RECIPE_CENTRIFUGE.register();

        RECIPE_SERIALIZERS.register(ID_RECIPE_FURNACE, FurnaceRecipe.Serializer::new);
        RECIPE_SERIALIZERS.register(ID_RECIPE_SAWMILL, SawmillRecipe.Serializer::new);
        RECIPE_SERIALIZERS.register(ID_RECIPE_PULVERIZER, PulverizerRecipe.Serializer::new);
        RECIPE_SERIALIZERS.register(ID_RECIPE_INSOLATOR, InsolatorRecipe.Serializer::new);
    }

    public static final ThermalRecipeType<FurnaceRecipe> RECIPE_FURNACE = new ThermalRecipeType<>(ID_RECIPE_FURNACE);
    public static final ThermalRecipeType<FurnaceRecipe> RECIPE_SAWMILL = new ThermalRecipeType<>(ID_RECIPE_SAWMILL);
    public static final ThermalRecipeType<FurnaceRecipe> RECIPE_PULVERIZER = new ThermalRecipeType<>(ID_RECIPE_PULVERIZER);
    public static final ThermalRecipeType<FurnaceRecipe> RECIPE_INSOLATOR = new ThermalRecipeType<>(ID_RECIPE_INSOLATOR);
    public static final ThermalRecipeType<FurnaceRecipe> RECIPE_CENTRIFUGE = new ThermalRecipeType<>(ID_RECIPE_CENTRIFUGE);

}
