package cofh.thermal.core.data;

import cofh.lib.data.RecipeProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalReferences.*;

public class TCoreRecipes extends RecipeProviderCoFH {

    public TCoreRecipes(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
    }

    @Override
    public String getName() {

        return "Thermal Core: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        ShapelessRecipeBuilder.shapelessRecipe(regItems.get("phytogro"), 8)
                .addIngredient(Tags.Items.SAND)
                .addIngredient(regItems.get("apatite"))
                .addIngredient(regItems.get("apatite"))
                .addIngredient(regItems.get("niter"))
                .addCriterion("has_apatite", hasItem(regItems.get("apatite")))
                .build(consumer, ID_THERMAL + ":phytogro_8");

        ShapelessRecipeBuilder.shapelessRecipe(regItems.get("phytogro"), 4)
                .addIngredient(Tags.Items.SAND)
                .addIngredient(Items.BONE_MEAL)
                .addIngredient(regItems.get("apatite"))
                .addIngredient(regItems.get("niter"))
                .addCriterion("has_apatite", hasItem(regItems.get("apatite")))
                .build(consumer, ID_THERMAL + ":phytogro_4");

        ShapelessRecipeBuilder.shapelessRecipe(Items.GUNPOWDER, 4)
                .addIngredient(Items.CHARCOAL)
                .addIngredient(regItems.get("niter"))
                .addIngredient(regItems.get("niter"))
                .addIngredient(regItems.get("sulfur"))
                .addCriterion("has_gunpowder", hasItem(Items.GUNPOWDER))
                .build(consumer, ID_THERMAL + ":gunpowder");

        ShapelessRecipeBuilder.shapelessRecipe(Items.PRISMARINE_SHARD, 4)
                .addIngredient(Items.PRISMARINE)
                .addIngredient(regItems.get(ID_WRENCH))
                .addCriterion("has_prismarine", hasItem(Items.PRISMARINE))
                .build(consumer, ID_THERMAL + ":split_prismarine");

        ShapelessRecipeBuilder.shapelessRecipe(Items.PRISMARINE_SHARD, 9)
                .addIngredient(Items.PRISMARINE_BRICKS)
                .addIngredient(regItems.get(ID_WRENCH))
                .addCriterion("has_prismarine_bricks", hasItem(Items.PRISMARINE_BRICKS))
                .build(consumer, ID_THERMAL + ":split_prismarine_bricks");

        ShapelessRecipeBuilder.shapelessRecipe(Items.QUARTZ, 4)
                .addIngredient(Items.QUARTZ_BLOCK)
                .addIngredient(regItems.get(ID_WRENCH))
                .addCriterion("has_quartz_block", hasItem(Items.QUARTZ_BLOCK))
                .build(consumer, ID_THERMAL + ":split_quartz_block");

        DeferredRegisterCoFH<Item> reg = ITEMS;

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_CHARCOAL_BLOCK))
                .key('#', Items.CHARCOAL)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_at_least_9_charcoal", hasItem(MinMaxBounds.IntBound.atLeast(9), Items.CHARCOAL))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_BAMBOO_BLOCK))
                .key('#', Items.BAMBOO)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_at_least_9_bamboo", hasItem(MinMaxBounds.IntBound.atLeast(9), Items.BAMBOO))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_SUGAR_CANE_BLOCK))
                .key('#', Items.SUGAR_CANE)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_at_least_9_sugar_cane", hasItem(MinMaxBounds.IntBound.atLeast(9), Items.SUGAR_CANE))
                .build(consumer);


        generateStorageRecipes(reg, consumer, "apatite");
        generateStorageRecipes(reg, consumer, "niter");
        generateStorageRecipes(reg, consumer, "sulfur");

        generateStorageRecipes(reg, consumer, "signalum");
        generateStorageRecipes(reg, consumer, "lumium");
        generateStorageRecipes(reg, consumer, "enderium");

        generateSmeltingAndBlastingRecipes(reg, consumer, "signalum", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "lumium", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "enderium", 0);
    }

}
