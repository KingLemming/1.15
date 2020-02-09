package cofh.thermal.locomotion.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;

import java.util.function.Consumer;

public class TLocGenRecipes extends RecipeProvider {

    public TLocGenRecipes(DataGenerator generatorIn) {

        super(generatorIn);
    }

    @Override
    public String getName() {

        return "Thermal Locomotion: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        // @formatter:off
//        ShapelessRecipeBuilder.shapelessRecipe(Items.PRISMARINE_SHARD, 4)
//                .addIngredient(Items.PRISMARINE)
//                .addIngredient(ITEMS.get(ID_WRENCH))
//                .build(consumer);
//
//        ShapelessRecipeBuilder.shapelessRecipe(Items.PRISMARINE_SHARD, 9)
//                .addIngredient(Items.PRISMARINE_BRICKS)
//                .addIngredient(ITEMS.get(ID_WRENCH))
//                .build(consumer);
        // @formatter:on
    }

}
