package cofh.thermal.expansion.init;

import cofh.thermal.core.util.recipes.ThermalCatalystSerializer;
import cofh.thermal.core.util.recipes.ThermalRecipeSerializer;
import cofh.thermal.core.util.recipes.ThermalRecipeType;
import cofh.thermal.expansion.util.managers.machine.*;
import cofh.thermal.expansion.util.recipes.*;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.expansion.init.TExpReferences.*;

public class TExpRecipes {

    private TExpRecipes() {

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

        CATALYST_PULVERIZER.register();
        CATALYST_INSOLATOR.register();

        RECIPE_SERIALIZERS.register(ID_RECIPE_FURNACE, () -> new ThermalRecipeSerializer<>(FurnaceRecipe::new, FurnaceRecipeManager.instance().getDefaultEnergy()));
        RECIPE_SERIALIZERS.register(ID_RECIPE_SAWMILL, () -> new ThermalRecipeSerializer<>(SawmillRecipe::new, SawmillRecipeManager.instance().getDefaultEnergy()));
        RECIPE_SERIALIZERS.register(ID_RECIPE_PULVERIZER, () -> new ThermalRecipeSerializer<>(PulverizerRecipe::new, PulverizerRecipeManager.instance().getDefaultEnergy()));
        RECIPE_SERIALIZERS.register(ID_RECIPE_INSOLATOR, () -> new InsolatorRecipeSerializer<>(InsolatorRecipe::new, InsolatorRecipeManager.instance().getDefaultEnergy(), InsolatorRecipeManager.instance().getDefaultWater()));
        RECIPE_SERIALIZERS.register(ID_RECIPE_CENTRIFUGE, () -> new ThermalRecipeSerializer<>(CentrifugeRecipe::new, CentrifugeRecipeManager.instance().getDefaultEnergy()));
        RECIPE_SERIALIZERS.register(ID_RECIPE_PRESS, () -> new ThermalRecipeSerializer<>(PressRecipe::new, PressRecipeManager.instance().getDefaultEnergy()));
        RECIPE_SERIALIZERS.register(ID_RECIPE_CRUCIBLE, () -> new ThermalRecipeSerializer<>(CrucibleRecipe::new, CrucibleRecipeManager.instance().getDefaultEnergy()));
        RECIPE_SERIALIZERS.register(ID_RECIPE_CHILLER, () -> new ThermalRecipeSerializer<>(ChillerRecipe::new, ChillerRecipeManager.instance().getDefaultEnergy()));
        RECIPE_SERIALIZERS.register(ID_RECIPE_REFINERY, () -> new ThermalRecipeSerializer<>(RefineryRecipe::new, RefineryRecipeManager.instance().getDefaultEnergy()));
        RECIPE_SERIALIZERS.register(ID_RECIPE_BREWER, () -> new ThermalRecipeSerializer<>(BrewerRecipe::new, BrewerRecipeManager.instance().getDefaultEnergy()));

        RECIPE_SERIALIZERS.register(ID_CATALYST_PULVERIZER, () -> new ThermalCatalystSerializer<>(PulverizerCatalyst::new));
        RECIPE_SERIALIZERS.register(ID_CATALYST_INSOLATOR, () -> new ThermalCatalystSerializer<>(InsolatorCatalyst::new));
    }

    public static final ThermalRecipeType<FurnaceRecipe> RECIPE_FURNACE = new ThermalRecipeType<>(ID_RECIPE_FURNACE);
    public static final ThermalRecipeType<SawmillRecipe> RECIPE_SAWMILL = new ThermalRecipeType<>(ID_RECIPE_SAWMILL);
    public static final ThermalRecipeType<PulverizerRecipe> RECIPE_PULVERIZER = new ThermalRecipeType<>(ID_RECIPE_PULVERIZER);
    public static final ThermalRecipeType<InsolatorRecipe> RECIPE_INSOLATOR = new ThermalRecipeType<>(ID_RECIPE_INSOLATOR);
    public static final ThermalRecipeType<CentrifugeRecipe> RECIPE_CENTRIFUGE = new ThermalRecipeType<>(ID_RECIPE_CENTRIFUGE);
    public static final ThermalRecipeType<CentrifugeRecipe> RECIPE_PRESS = new ThermalRecipeType<>(ID_RECIPE_PRESS);
    public static final ThermalRecipeType<CrucibleRecipe> RECIPE_CRUCIBLE = new ThermalRecipeType<>(ID_RECIPE_CRUCIBLE);
    public static final ThermalRecipeType<ChillerRecipe> RECIPE_CHILLER = new ThermalRecipeType<>(ID_RECIPE_CHILLER);
    public static final ThermalRecipeType<RefineryRecipe> RECIPE_REFINERY = new ThermalRecipeType<>(ID_RECIPE_REFINERY);
    public static final ThermalRecipeType<BrewerRecipe> RECIPE_BREWER = new ThermalRecipeType<>(ID_RECIPE_BREWER);

    public static final ThermalRecipeType<PulverizerCatalyst> CATALYST_PULVERIZER = new ThermalRecipeType<>(ID_CATALYST_PULVERIZER);
    public static final ThermalRecipeType<InsolatorCatalyst> CATALYST_INSOLATOR = new ThermalRecipeType<>(ID_CATALYST_INSOLATOR);

}
