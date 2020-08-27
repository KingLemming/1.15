package cofh.thermal.core.init;

import cofh.lib.util.recipes.SerializableRecipeType;
import cofh.thermal.core.util.recipes.ThermalBoost;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TCoreRecipeTypes {

    private TCoreRecipeTypes() {

    }

    public static void register() {

        // TODO: Convert when a ForgeRegistry is added.
        // Recipes are self-registered as they do not currently have a proper Forge Registry.
        BOOST_TREE_EXTRACTOR.register();
    }

    // region RECIPES
    public static final ResourceLocation ID_BOOST_TREE_EXTRACTOR = new ResourceLocation(ID_THERMAL, "tree_extractor_boost");

    public static final SerializableRecipeType<ThermalBoost> BOOST_TREE_EXTRACTOR = new SerializableRecipeType<>(ID_BOOST_TREE_EXTRACTOR);
    // endregion
}
