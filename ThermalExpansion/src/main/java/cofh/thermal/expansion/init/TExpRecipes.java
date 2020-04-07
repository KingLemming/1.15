package cofh.thermal.expansion.init;

import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.core.util.recipes.ThermalRecipeType;
import cofh.thermal.expansion.util.recipes.*;

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

        CATALYST_PULVERIZER.register();
        CATALYST_INSOLATOR.register();

        RECIPE_SERIALIZERS.register(ID_RECIPE_FURNACE, FurnaceRecipe.Serializer::new);
        RECIPE_SERIALIZERS.register(ID_RECIPE_SAWMILL, SawmillRecipe.Serializer::new);
        RECIPE_SERIALIZERS.register(ID_RECIPE_PULVERIZER, PulverizerRecipe.Serializer::new);
        RECIPE_SERIALIZERS.register(ID_RECIPE_INSOLATOR, InsolatorRecipe.Serializer::new);

        RECIPE_SERIALIZERS.register(ID_CATALYST_PULVERIZER, PulverizerCatalyst.Serializer::new);
        RECIPE_SERIALIZERS.register(ID_CATALYST_INSOLATOR, InsolatorCatalyst.Serializer::new);
    }

    public static final ThermalRecipeType<FurnaceRecipe> RECIPE_FURNACE = new ThermalRecipeType<>(ID_RECIPE_FURNACE);
    public static final ThermalRecipeType<SawmillRecipe> RECIPE_SAWMILL = new ThermalRecipeType<>(ID_RECIPE_SAWMILL);
    public static final ThermalRecipeType<PulverizerRecipe> RECIPE_PULVERIZER = new ThermalRecipeType<>(ID_RECIPE_PULVERIZER);
    public static final ThermalRecipeType<InsolatorRecipe> RECIPE_INSOLATOR = new ThermalRecipeType<>(ID_RECIPE_INSOLATOR);
    public static final ThermalRecipeType<ThermalRecipe> RECIPE_CENTRIFUGE = new ThermalRecipeType<>(ID_RECIPE_CENTRIFUGE);

    public static final ThermalRecipeType<PulverizerCatalyst> CATALYST_PULVERIZER = new ThermalRecipeType<>(ID_CATALYST_PULVERIZER);
    public static final ThermalRecipeType<InsolatorCatalyst> CATALYST_INSOLATOR = new ThermalRecipeType<>(ID_CATALYST_INSOLATOR);

}
