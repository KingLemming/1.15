package cofh.thermal.cultivation.datagen;

import cofh.lib.datagen.RecipeProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.util.RegistrationHelper.block;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.cultivation.init.TCulIDs.*;

public class TCulRecipes extends RecipeProviderCoFH {

    public TCulRecipes(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
    }

    @Override
    public String getName() {

        return "Thermal Cultivation: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_PHYTOSOIL))
                .key('C', Items.CHARCOAL)
                .key('P', regItems.get("phytogro"))
                .key('X', Blocks.DIRT)
                .patternLine("CPC")
                .patternLine("PXP")
                .patternLine("CPC")
                .addCriterion("has_phytogro", hasItem(regItems.get("phytogro")))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(regItems.get(seeds(ID_FROST_MELON)))
                .addIngredient(regItems.get(ID_FROST_MELON_SLICE))
                .addCriterion("has_frost_melon", hasItem(regItems.get(ID_FROST_MELON_SLICE)))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_FROST_MELON))
                .key('M', regItems.get(ID_FROST_MELON_SLICE))
                .patternLine("MMM")
                .patternLine("MMM")
                .patternLine("MMM")
                .addCriterion("has_frost_melon", hasItem(regItems.get(ID_FROST_MELON_SLICE)))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_CHOCOLATE_CAKE))
                .key('A', Items.MILK_BUCKET)
                .key('B', Items.COCOA_BEANS)
                .key('C', Items.WHEAT)
                .key('D', regItems.get(ID_STRAWBERRY))
                .key('E', Items.EGG)
                .patternLine("ADA")
                .patternLine("BEB")
                .patternLine("CDC")
                .addCriterion("has_cocoa_beans", hasItem(Items.COCOA_BEANS))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_SPICE_CAKE))
                .key('A', Items.MILK_BUCKET)
                .key('B', Items.HONEY_BOTTLE)
                .key('C', Items.WHEAT)
                .key('D', regItems.get(ID_SADIROOT))
                .key('E', Items.EGG)
                .patternLine("ADA")
                .patternLine("BEB")
                .patternLine("CDC")
                .addCriterion("has_sadiroot", hasItem(regItems.get(ID_SADIROOT)))
                .build(consumer);

        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_BARLEY)), regItems.get(ID_BARLEY));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_BELL_PEPPER)), regItems.get(ID_BELL_PEPPER));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_COFFEE)), regItems.get(ID_COFFEE));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_CORN)), regItems.get(ID_CORN));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_EGGPLANT)), regItems.get(ID_EGGPLANT));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_GREEN_BEAN)), regItems.get(ID_GREEN_BEAN));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_HOPS)), regItems.get(ID_HOPS));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_ONION)), regItems.get(ID_ONION));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_PEANUT)), regItems.get(ID_PEANUT));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_RADISH)), regItems.get(ID_RADISH));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_RICE)), regItems.get(ID_RICE));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_SADIROOT)), regItems.get(ID_SADIROOT));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_SPINACH)), regItems.get(ID_SPINACH));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_STRAWBERRY)), regItems.get(ID_STRAWBERRY));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_TEA)), regItems.get(ID_TEA));
        generateStorageRecipes(regItems, consumer, regItems.get(block(ID_TOMATO)), regItems.get(ID_TOMATO));
    }

}
