package cofh.thermal.innovation.datagen;

import cofh.core.datagen.RecipeProviderCoFH;
import cofh.core.registries.DeferredRegisterCoFH;
import cofh.core.util.references.CoFHTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.core.util.constants.Constants.ID_THERMAL;
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

        DeferredRegisterCoFH<Item> reg = ITEMS;

        Item rfCoil = reg.get("rf_coil");

        ShapedRecipeBuilder.shapedRecipe(reg.get("flux_drill"))
                .key('C', CoFHTags.Items.GEARS_GOLD)
                .key('G', CoFHTags.Items.GEARS_TIN)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('P', rfCoil)
                .key('X', reg.get("drill_head"))
                .patternLine(" X ")
                .patternLine("ICI")
                .patternLine("GPG")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("flux_saw"))
                .key('C', CoFHTags.Items.GEARS_GOLD)
                .key('G', CoFHTags.Items.GEARS_TIN)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('P', rfCoil)
                .key('X', reg.get("saw_blade"))
                .patternLine(" X ")
                .patternLine("ICI")
                .patternLine("GPG")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("flux_capacitor"))
                .key('L', CoFHTags.Items.INGOTS_LEAD)
                .key('P', rfCoil)
                .key('R', Tags.Items.DUSTS_REDSTONE)
                .patternLine("RLR")
                .patternLine("LPL")
                .patternLine(" R ")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("flux_magnet"))
                .key('L', CoFHTags.Items.INGOTS_LEAD)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('P', rfCoil)
                .key('R', Tags.Items.DUSTS_REDSTONE)
                .patternLine("IRI")
                .patternLine("LIL")
                .patternLine(" P ")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("potion_infuser"))
                .key('B', Items.GLASS_BOTTLE)
                .key('C', CoFHTags.Items.INGOTS_COPPER)
                .key('G', CoFHTags.Items.GEARS_SILVER)
                .key('R', reg.get("cured_rubber"))
                .patternLine("RBR")
                .patternLine("CGC")
                .patternLine(" C ")
                .addCriterion("has_glass_bottle", hasItem(Items.GLASS_BOTTLE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("potion_quiver"))
                .key('B', Items.GLASS_BOTTLE)
                .key('C', CoFHTags.Items.INGOTS_COPPER)
                .key('G', CoFHTags.Items.GEARS_SILVER)
                .key('S', Tags.Items.STRING)
                .key('R', reg.get("cured_rubber"))
                .patternLine("C C")
                .patternLine("BGS")
                .patternLine("RCR")
                .addCriterion("has_glass_bottle", hasItem(Items.GLASS_BOTTLE))
                .build(consumer);
    }

}
