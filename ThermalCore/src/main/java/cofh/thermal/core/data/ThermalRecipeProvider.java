package cofh.thermal.core.data;

import net.minecraft.advancements.criterion.*;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;

public class ThermalRecipeProvider extends RecipeProvider {

    public ThermalRecipeProvider(DataGenerator generatorIn) {

        super(generatorIn);
    }

    // region HELPERS
    protected void generateStorageRecipes(Consumer<IFinishedRecipe> consumer, String type) {

        Item block = ITEMS.get(type + "_block");
        Item ingot = ITEMS.get(type + "_ingot");
        Item gem = ITEMS.get(type + "_gem");
        Item nugget = ITEMS.get(type + "_nugget");

        // @formatter:off
        if (block != null) {
            String blockName = block.getRegistryName().getPath();

            if (ingot != null) {
                String ingotName = ingot.getRegistryName().getPath();

                ShapedRecipeBuilder.shapedRecipe(block)
                        .key('#', ingot)
                        .patternLine("###")
                        .patternLine("###")
                        .patternLine("###")
                        .addCriterion("has_at_least_9_" + ingotName, hasItem(MinMaxBounds.IntBound.atLeast(9), ingot))
                        .build(consumer);

                ShapelessRecipeBuilder.shapelessRecipe(ingot, 9)
                        .addIngredient(block)
                        .addCriterion("has_at_least_9_" + ingotName, hasItem(MinMaxBounds.IntBound.atLeast(9), ingot))
                        .addCriterion("has_" + blockName, hasItem(block))
                        .build(consumer, ID_THERMAL + ":" + ingotName + "_from_block");

            } else if (gem != null) {
                String gemName = gem.getRegistryName().getPath();

                ShapedRecipeBuilder.shapedRecipe(block)
                        .key('#', gem)
                        .patternLine("###")
                        .patternLine("###")
                        .patternLine("###")
                        .addCriterion("has_at_least_9_" + gemName, hasItem(MinMaxBounds.IntBound.atLeast(9), gem))
                        .build(consumer);

                ShapelessRecipeBuilder.shapelessRecipe(gem, 9)
                        .addIngredient(block)
                        .addCriterion("has_at_least_9_" + gemName, hasItem(MinMaxBounds.IntBound.atLeast(9), gem))
                        .addCriterion("has_" + blockName, hasItem(block))
                        .build(consumer, ID_THERMAL + ":" + gemName + "_from_block");
            }
        }

        if (nugget != null) {
            String nuggetName = nugget.getRegistryName().getPath();

            if (ingot != null) {
                String ingotName = ingot.getRegistryName().getPath();

                ShapedRecipeBuilder.shapedRecipe(ingot)
                        .key('#', nugget)
                        .patternLine("###")
                        .patternLine("###")
                        .patternLine("###")
                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
                        .build(consumer, ID_THERMAL + ":" + ingotName + "_from_nuggets");

                ShapelessRecipeBuilder.shapelessRecipe(nugget, 9)
                        .addIngredient(ingot)
                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
                        .addCriterion("has_" + ingotName, hasItem(ingot))
                        .build(consumer, ID_THERMAL + ":" + nuggetName + "_from_ingot");
            }
//            else if (gem != null) {
//                String gemName = gem.getRegistryName().getPath();
//
//                ShapedRecipeBuilder.shapedRecipe(gem)
//                        .key('#', nugget)
//                        .patternLine("###")
//                        .patternLine("###")
//                        .patternLine("###")
//                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
//                        .build(consumer, ID_THERMAL + ":" + gemName + "_from_nuggets");
//
//                ShapelessRecipeBuilder.shapelessRecipe(nugget, 9)
//                        .addIngredient(gem)
//                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
//                        .addCriterion("has_" + gemName, hasItem(gem))
//                        .build(consumer, ID_THERMAL + ":" + nuggetName + "_from_gem");
//            }
        }
        // @formatter:on
    }

    protected void generateSmeltingAndBlastingRecipes(Consumer<IFinishedRecipe> consumer, String type, float xp) {

        Item ore = ITEMS.get(type + "_ore");
        Item ingot = ITEMS.get(type + "_ingot");
        Item gem = ITEMS.get(type + "_gem");
        Item nugget = ITEMS.get(type + "_nugget");
        Item dust = ITEMS.get(type + "_dust");

        // @formatter:off
        if (ingot != null) {
            String ingotName = ingot.getRegistryName().getPath();

            if (dust != null) {
                String dustName = dust.getRegistryName().getPath();

                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(dust), ingot, 0, 200)
                        .addCriterion("has_" + dustName, hasItem(dust))
                        .build(consumer, ID_THERMAL + ":" +ingotName + "_from_dust_smelting");

                CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(dust), ingot, 0, 100)
                        .addCriterion("has_" + dustName, hasItem(dust))
                        .build(consumer, ID_THERMAL + ":" +ingotName + "_from_dust_blasting");
            }

            if (ore != null) {
                String oreName = ore.getRegistryName().getPath();

                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ore), ingot, xp, 200)
                        .addCriterion("has_" + oreName, hasItem(ore))
                        .build(consumer, ID_THERMAL + ":" +ingotName + "_from_ore_smelting");

                CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ore), ingot, xp, 100)
                        .addCriterion("has_" + oreName, hasItem(ore))
                        .build(consumer, ID_THERMAL + ":" +ingotName + "_from_ore_blasting");
            }
        } else if (gem != null) {
            String gemName = gem.getRegistryName().getPath();

            if (ore != null) {
                String oreName = ore.getRegistryName().getPath();

                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ore), gem, xp, 200)
                        .addCriterion("has_" + oreName, hasItem(ore))
                        .build(consumer, gemName + "_from_ore_smelting");

                CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ore), gem, xp, 100)
                        .addCriterion("has_" + oreName, hasItem(ore))
                        .build(consumer, gemName + "_from_ore_blasting");
            }
        }
        // @formatter:on
    }

    // TODO: Change if Mojang implements some better defaults...
    protected InventoryChangeTrigger.Instance hasItem(MinMaxBounds.IntBound amount, IItemProvider itemIn) {

        return this.hasItem(new ItemPredicate(null, itemIn.asItem(), amount, MinMaxBounds.IntBound.UNBOUNDED, EnchantmentPredicate.field_226534_b_, EnchantmentPredicate.field_226534_b_, null, NBTPredicate.ANY)); // ItemPredicate.Builder.create().item(itemIn).count(amount).build());
    }
    // endregion
}
