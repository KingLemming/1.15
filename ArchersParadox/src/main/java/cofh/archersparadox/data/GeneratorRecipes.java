package cofh.archersparadox.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.archersparadox.ArchersParadox.ITEMS;
import static cofh.archersparadox.init.ModReferences.ID_DIAMOND_ARROW;

public class GeneratorRecipes extends RecipeProvider {

    public GeneratorRecipes(DataGenerator generator) {

        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_DIAMOND_ARROW))
                .key('X', Tags.Items.GEMS_DIAMOND)
                .key('#', Items.STICK)
                .key('Y', Items.FEATHER)
                .patternLine("X")
                .patternLine("#")
                .patternLine("Y")
                .addCriterion("has_components", hasItem(Tags.Items.GEMS_DIAMOND))
                .build(consumer);
    }

}
