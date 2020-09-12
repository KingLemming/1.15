package cofh.thermal.core.init;

import cofh.core.util.recipes.SerializableRecipeType;
import cofh.thermal.core.util.recipes.device.TreeExtractorBoost;
import cofh.thermal.core.util.recipes.device.TreeExtractorMapping;
import net.minecraft.util.ResourceLocation;

import static cofh.core.util.constants.Constants.ID_THERMAL;

public class TCoreRecipeTypes {

    private TCoreRecipeTypes() {

    }

    public static void register() {

        // TODO: Convert when a ForgeRegistry is added.
        // Recipes are self-registered as they do not currently have a proper Forge Registry.
        BOOST_TREE_EXTRACTOR.register();
        MAPPING_TREE_EXTRACTOR.register();
    }

    // region RECIPES
    public static final ResourceLocation ID_BOOST_TREE_EXTRACTOR = new ResourceLocation(ID_THERMAL, "tree_extractor_boost");
    public static final ResourceLocation ID_MAPPING_TREE_EXTRACTOR = new ResourceLocation(ID_THERMAL, "tree_extractor_mapping");

    public static final SerializableRecipeType<TreeExtractorBoost> BOOST_TREE_EXTRACTOR = new SerializableRecipeType<>(ID_BOOST_TREE_EXTRACTOR);
    public static final SerializableRecipeType<TreeExtractorMapping> MAPPING_TREE_EXTRACTOR = new SerializableRecipeType<>(ID_MAPPING_TREE_EXTRACTOR);
    // endregion
}
