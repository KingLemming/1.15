package cofh.thermal.cultivation.data;

import cofh.lib.data.RecipeProviderCoFH;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.cultivation.init.TCulReferences.*;

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

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_PHYTOSOIL))
                .key('C', Items.CHARCOAL)
                .key('F', ITEMS.get("fertilizer"))
                .key('X', Blocks.DIRT)
                .patternLine("CFC")
                .patternLine("FXF")
                .patternLine("CFC")
                .addCriterion("has_fertilizer", hasItem(ITEMS.get("fertilizer")))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(ITEMS.get(seeds(ID_FROST_MELON)))
                .addIngredient(ITEMS.get(ID_FROST_MELON_SLICE))
                .addCriterion("has_frost_melon", hasItem(ITEMS.get(ID_FROST_MELON_SLICE)))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_FROST_MELON))
                .key('M', ITEMS.get(ID_FROST_MELON_SLICE))
                .patternLine("MMM")
                .patternLine("MMM")
                .patternLine("MMM")
                .addCriterion("has_frost_melon", hasItem(ITEMS.get(ID_FROST_MELON_SLICE)))
                .build(consumer);
    }

}
