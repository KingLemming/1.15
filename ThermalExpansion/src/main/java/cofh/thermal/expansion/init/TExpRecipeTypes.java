package cofh.thermal.expansion.init;

import cofh.thermal.core.util.recipes.ThermalRecipeType;
import cofh.thermal.expansion.util.recipes.dynamo.*;
import cofh.thermal.expansion.util.recipes.machine.*;

import static cofh.thermal.expansion.init.TExpReferences.*;

public class TExpRecipeTypes {

    private TExpRecipeTypes() {

    }

    public static void register() {

        // TODO: Convert when a ForgeRegistry is added.
        // Recipes are self-registered as they do not currently have a proper Forge Registry.
        RECIPE_FURNACE.register();
        RECIPE_SAWMILL.register();
        RECIPE_PULVERIZER.register();
        RECIPE_INSOLATOR.register();
        RECIPE_CENTRIFUGE.register();
        RECIPE_PRESS.register();
        RECIPE_CRUCIBLE.register();
        RECIPE_CHILLER.register();
        RECIPE_REFINERY.register();
        RECIPE_BREWER.register();
        RECIPE_BOTTLER.register();

        CATALYST_PULVERIZER.register();
        CATALYST_INSOLATOR.register();

        FUEL_STIRLING.register();
        FUEL_COMPRESSION.register();
        FUEL_MAGMATIC.register();
        FUEL_NUMISMATIC.register();
        FUEL_LAPIDARY.register();
    }

    public static final ThermalRecipeType<FurnaceRecipe> RECIPE_FURNACE = new ThermalRecipeType<>(ID_RECIPE_FURNACE);
    public static final ThermalRecipeType<SawmillRecipe> RECIPE_SAWMILL = new ThermalRecipeType<>(ID_RECIPE_SAWMILL);
    public static final ThermalRecipeType<PulverizerRecipe> RECIPE_PULVERIZER = new ThermalRecipeType<>(ID_RECIPE_PULVERIZER);
    public static final ThermalRecipeType<InsolatorRecipe> RECIPE_INSOLATOR = new ThermalRecipeType<>(ID_RECIPE_INSOLATOR);
    public static final ThermalRecipeType<CentrifugeRecipe> RECIPE_CENTRIFUGE = new ThermalRecipeType<>(ID_RECIPE_CENTRIFUGE);
    public static final ThermalRecipeType<PressRecipe> RECIPE_PRESS = new ThermalRecipeType<>(ID_RECIPE_PRESS);
    public static final ThermalRecipeType<CrucibleRecipe> RECIPE_CRUCIBLE = new ThermalRecipeType<>(ID_RECIPE_CRUCIBLE);
    public static final ThermalRecipeType<ChillerRecipe> RECIPE_CHILLER = new ThermalRecipeType<>(ID_RECIPE_CHILLER);
    public static final ThermalRecipeType<RefineryRecipe> RECIPE_REFINERY = new ThermalRecipeType<>(ID_RECIPE_REFINERY);
    public static final ThermalRecipeType<BrewerRecipe> RECIPE_BREWER = new ThermalRecipeType<>(ID_RECIPE_BREWER);
    public static final ThermalRecipeType<BottlerRecipe> RECIPE_BOTTLER = new ThermalRecipeType<>(ID_RECIPE_BOTTLER);

    public static final ThermalRecipeType<PulverizerCatalyst> CATALYST_PULVERIZER = new ThermalRecipeType<>(ID_CATALYST_PULVERIZER);
    public static final ThermalRecipeType<InsolatorCatalyst> CATALYST_INSOLATOR = new ThermalRecipeType<>(ID_CATALYST_INSOLATOR);

    public static final ThermalRecipeType<StirlingFuel> FUEL_STIRLING = new ThermalRecipeType<>(ID_FUEL_STIRLING);
    public static final ThermalRecipeType<CompressionFuel> FUEL_COMPRESSION = new ThermalRecipeType<>(ID_FUEL_COMPRESSION);
    public static final ThermalRecipeType<MagmaticFuel> FUEL_MAGMATIC = new ThermalRecipeType<>(ID_FUEL_MAGMATIC);
    public static final ThermalRecipeType<NumismaticFuel> FUEL_NUMISMATIC = new ThermalRecipeType<>(ID_FUEL_NUMISMATIC);
    public static final ThermalRecipeType<LapidaryFuel> FUEL_LAPIDARY = new ThermalRecipeType<>(ID_FUEL_LAPIDARY);

}
