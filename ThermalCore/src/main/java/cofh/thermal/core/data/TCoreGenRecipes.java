package cofh.thermal.core.data;

import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.ThermalReferences.ID_WRENCH;

public class TCoreGenRecipes extends RecipeProvider {

    public TCoreGenRecipes(DataGenerator generatorIn) {

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

        generateMetalRecipes(consumer, "signalum");
        generateMetalRecipes(consumer, "lumium");
        generateMetalRecipes(consumer, "enderium");
        // @formatter:on
    }

    protected void generateMetalRecipes(Consumer<IFinishedRecipe> consumer, String type) {

        generateStorageAndSmeltingRecipes(consumer, type, false);
    }

    protected void generateGemRecipes(Consumer<IFinishedRecipe> consumer, String type) {

        generateStorageAndSmeltingRecipes(consumer, type, true);
    }

    protected void generateStorageAndSmeltingRecipes(Consumer<IFinishedRecipe> consumer, String type, boolean gem) {

        Item ore = ITEMS.get(type + "_ore");
        Item block = ITEMS.get(type + "_block");
        Item ingot = ITEMS.get(type + (gem ? "_gem" : "_ingot"));
        Item nugget = ITEMS.get(type + "_nugget");
        Item dust = ITEMS.get(type + "_dust");
        Item plate = ITEMS.get(type + "_plate");
        Item coin = ITEMS.get(type + "_coin");

        // @formatter:off
        if (block != null && ingot != null) {
            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', ingot)
                    .patternLine("###")
                    .patternLine("###")
                    .patternLine("###")
                    .addCriterion("has_components", hasItem(MinMaxBounds.IntBound.atLeast(9), ingot))
                    .build(consumer, block.getRegistryName() + (gem ? "_from_gems" : "_from_ingots"));
        }

        if (ingot != null && nugget != null) {
            ShapedRecipeBuilder.shapedRecipe(ingot)
                    .key('#', nugget)
                    .patternLine("###")
                    .patternLine("###")
                    .patternLine("###")
                    .addCriterion("has_components", hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
                    .build(consumer, ingot.getRegistryName() + "_from_nuggets");
        }

        if (!gem) {
            if (ingot != null && dust != null) {
                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(dust), ingot, 0, 200)
                        .addCriterion("has_dust", hasItem(dust))
                        .build(consumer, ingot.getRegistryName() + "_from_dust_smelting");

                CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(dust), ingot, 0, 100)
                        .addCriterion("has_dust", hasItem(dust))
                        .build(consumer, ingot.getRegistryName() + "_from_dust_blasting");
            }
        }
        // @formatter:on
    }

    // protected void generateSmeltingAndBlastingRecipes(Consumer<IFinishedRecipe> consumer, )

}
