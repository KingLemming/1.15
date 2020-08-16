package cofh.thermal.innovation.datagen;

import cofh.lib.datagen.RecipeProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.lib.util.references.CoFHTags;
import cofh.lib.util.references.CoFHTags.Items;
import cofh.thermal.core.datagen.TCoreTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;

import java.util.function.Consumer;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraftforge.common.Tags;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;

public class TInoRecipes extends RecipeProviderCoFH {

    public TInoRecipes(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
    }

    @Override
    public String getName() {

        return "Thermal Innovation: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        ShapedRecipeBuilder.shapedRecipe(regItems.get("flux_capacitor"))
              .key('L', Items.INGOTS_LEAD)
              .key('C', Items.INGOTS_COPPER)
              .key('R', Tags.Items.DUSTS_REDSTONE)
              .key('S', regItems.get("sulfur"))
              .patternLine(" R ")
              .patternLine("LCL")
              .patternLine("RSR")
              .addCriterion("has_redstone", hasItem(Tags.Items.DUSTS_REDSTONE))
              .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regItems.get("flux_magnet"))
              .key('L', Items.INGOTS_LEAD)
              .key('I', Tags.Items.INGOTS_IRON)
              .key('R', Tags.Items.DUSTS_REDSTONE)
              .patternLine("IRI")
              .patternLine("LIL")
              .patternLine(" R ")
              .addCriterion("has_redstone", hasItem(Tags.Items.DUSTS_REDSTONE))
              .build(consumer);
    }

}
