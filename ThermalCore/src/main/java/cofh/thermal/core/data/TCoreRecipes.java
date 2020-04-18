package cofh.thermal.core.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalReferences.ID_WRENCH;

public class TCoreRecipes extends ThermalRecipeProvider {

    public TCoreRecipes(DataGenerator generatorIn) {

        super(generatorIn);
    }

    @Override
    public String getName() {

        return "Thermal Core: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        // @formatter:off
        ShapelessRecipeBuilder.shapelessRecipe(Items.PRISMARINE_SHARD, 4)
                .addIngredient(Items.PRISMARINE)
                .addIngredient(ITEMS.get(ID_WRENCH))
                .addCriterion("has_prismarine", hasItem(Items.PRISMARINE))
                .build(consumer, ID_THERMAL + ":split_prismarine");

        ShapelessRecipeBuilder.shapelessRecipe(Items.PRISMARINE_SHARD, 9)
                .addIngredient(Items.PRISMARINE_BRICKS)
                .addIngredient(ITEMS.get(ID_WRENCH))
                .addCriterion("has_prismarine_bricks", hasItem(Items.PRISMARINE_BRICKS))
                .build(consumer, ID_THERMAL + ":split_prismarine_bricks");
        // @formatter:on

        generateStorageRecipes(consumer, "signalum");
        generateStorageRecipes(consumer, "lumium");
        generateStorageRecipes(consumer, "enderium");

        generateSmeltingAndBlastingRecipes(consumer, "signalum", 0);
        generateSmeltingAndBlastingRecipes(consumer, "lumium", 0);
        generateSmeltingAndBlastingRecipes(consumer, "enderium", 0);
    }

}
