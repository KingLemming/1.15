package cofh.omgourd.datagen;

import cofh.core.datagen.RecipeProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

import static cofh.core.util.constants.Constants.ID_OMGOURD;
import static cofh.omgourd.OMGourd.ITEMS;

public class OMGRecipeProvider extends RecipeProviderCoFH {

    public OMGRecipeProvider(DataGenerator generatorIn) {

        super(generatorIn, ID_OMGOURD);
    }

    @Override
    public String getName() {

        return "OMGourd: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        for (int i = 1; i <= 24; ++i) {
            ShapedRecipeBuilder.shapedRecipe(ITEMS.get("jack_o_lantern_" + i))
                    .key('A', ITEMS.get("carved_pumpkin_" + i))
                    .key('B', Items.TORCH)
                    .patternLine("A")
                    .patternLine("B")
                    .addCriterion("has_carved_pumpkin", hasItem(Items.CARVED_PUMPKIN))
                    .build(consumer);
        }
    }

}
