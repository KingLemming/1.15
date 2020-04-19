package cofh.cofh_tools.data;

import cofh.lib.util.references.CoFHTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.Tag;

import java.util.function.Consumer;

import static cofh.cofh_tools.CoFHTools.ITEMS;

public class ModRecipes extends RecipeProvider {

    public ModRecipes(DataGenerator generatorIn) {

        super(generatorIn);
    }

    @Override
    public String getName() {

        return "CoFH Tools: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        createStandardToolRecipes("copper", CoFHTags.Items.INGOTS_COPPER, consumer);
        createStandardToolRecipes("tin", CoFHTags.Items.INGOTS_TIN, consumer);
        createStandardToolRecipes("silver", CoFHTags.Items.INGOTS_SILVER, consumer);
        createStandardToolRecipes("lead", CoFHTags.Items.INGOTS_LEAD, consumer);
        createStandardToolRecipes("nickel", CoFHTags.Items.INGOTS_NICKEL, consumer);
        createStandardToolRecipes("platinum", CoFHTags.Items.INGOTS_PLATINUM, consumer);

        createStandardToolRecipes("bronze", CoFHTags.Items.INGOTS_BRONZE, consumer);
        createStandardToolRecipes("electrum", CoFHTags.Items.INGOTS_ELECTRUM, consumer);
        createStandardToolRecipes("invar", CoFHTags.Items.INGOTS_INVAR, consumer);
        createStandardToolRecipes("constantan", CoFHTags.Items.INGOTS_CONSTANTAN, consumer);
    }

    protected void createStandardToolRecipes(String prefix, Tag<Item> tag, Consumer<IFinishedRecipe> consumer) {

        createShovelRecipe(ITEMS.get(prefix + "_shovel"), tag, consumer);
        createPickaxeRecipe(ITEMS.get(prefix + "_pickaxe"), tag, consumer);
        createAxeRecipe(ITEMS.get(prefix + "_axe"), tag, consumer);
        createHoeRecipe(ITEMS.get(prefix + "_hoe"), tag, consumer);
        createSwordRecipe(ITEMS.get(prefix + "_sword"), tag, consumer);
    }

    protected void createExtraToolRecipes(String prefix, Tag<Item> tag, Consumer<IFinishedRecipe> consumer) {

    }

    protected void createShovelRecipe(Item item, Tag<Item> tag, Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shapedRecipe(item)
                .key('#', Items.STICK)
                .key('X', tag)
                .patternLine(" X ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(tag))
                .build(consumer);
    }

    protected void createPickaxeRecipe(Item item, Tag<Item> tag, Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shapedRecipe(item)
                .key('#', Items.STICK)
                .key('X', tag)
                .patternLine("XXX")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(tag))
                .build(consumer);
    }

    protected void createAxeRecipe(Item item, Tag<Item> tag, Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shapedRecipe(item)
                .key('#', Items.STICK)
                .key('X', tag)
                .patternLine("XX ")
                .patternLine("X# ")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(tag))
                .build(consumer);
    }

    protected void createHoeRecipe(Item item, Tag<Item> tag, Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shapedRecipe(item)
                .key('#', Items.STICK)
                .key('X', tag)
                .patternLine("XX ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(tag))
                .build(consumer);
    }

    protected void createSwordRecipe(Item item, Tag<Item> tag, Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shapedRecipe(item)
                .key('#', Items.STICK)
                .key('X', tag)
                .patternLine(" X ")
                .patternLine(" X ")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(tag))
                .build(consumer);
    }

}
