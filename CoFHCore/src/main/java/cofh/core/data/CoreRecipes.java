package cofh.core.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.core.CoFHCore.ITEMS;
import static cofh.lib.util.references.CoreReferences.ID_ECTOPLASM;

public class CoreRecipes extends RecipeProvider {

    public CoreRecipes(DataGenerator generatorIn) {

        super(generatorIn);
    }

    @Override
    public String getName() {

        return "CoFH Core: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        // @formatter:off
        ShapelessRecipeBuilder.shapelessRecipe(ITEMS.get(ID_ECTOPLASM))
                .addIngredient(Items.GHAST_TEAR)
                .addIngredient(Tags.Items.SLIMEBALLS)
                .addCriterion("has_ghast_tear", hasItem(Items.GHAST_TEAR))
                .build(consumer);
        // @formatter:on
    }

}
