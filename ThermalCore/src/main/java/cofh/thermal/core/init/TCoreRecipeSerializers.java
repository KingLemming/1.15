package cofh.thermal.core.init;

import cofh.thermal.core.util.managers.device.TreeExtractorManager;
import cofh.thermal.core.util.recipes.ThermalBoostSerializer;
import cofh.thermal.core.util.recipes.device.TreeExtractorBoost;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.core.init.TCoreRecipeTypes.ID_BOOST_TREE_EXTRACTOR;

public class TCoreRecipeSerializers {

    private TCoreRecipeSerializers() {

    }

    public static void register() {

        RECIPE_SERIALIZERS.register(ID_BOOST_TREE_EXTRACTOR, () -> new ThermalBoostSerializer<>(TreeExtractorBoost::new, TreeExtractorManager.instance().getDefaultCycles()));
    }

}
