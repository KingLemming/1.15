package cofh.thermal.core.init;

import cofh.thermal.core.util.recipes.device.TreeExtractorBoostSerializer;
import cofh.thermal.core.util.recipes.device.TreeExtractorMappingSerializer;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.core.init.TCoreRecipeTypes.ID_BOOST_TREE_EXTRACTOR;
import static cofh.thermal.core.init.TCoreRecipeTypes.ID_MAPPING_TREE_EXTRACTOR;

public class TCoreRecipeSerializers {

    private TCoreRecipeSerializers() {

    }

    public static void register() {

        RECIPE_SERIALIZERS.register(ID_BOOST_TREE_EXTRACTOR, TreeExtractorBoostSerializer::new);
        RECIPE_SERIALIZERS.register(ID_MAPPING_TREE_EXTRACTOR, TreeExtractorMappingSerializer::new);
    }

}
