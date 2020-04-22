package cofh.thermal.core.data;

import cofh.lib.data.RecipeProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalReferences.ID_WRENCH;

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

        ShapelessRecipeBuilder.shapelessRecipe(ITEMS.get("fertilizer"), 8)
                .addIngredient(Tags.Items.SAND)
                .addIngredient(ITEMS.get("apatite"))
                .addIngredient(ITEMS.get("apatite"))
                .addIngredient(ITEMS.get("niter"))
                .addCriterion("has_apatite", hasItem(ITEMS.get("apatite")))
                .build(consumer, ID_THERMAL + ":fertilizer_8");

        ShapelessRecipeBuilder.shapelessRecipe(ITEMS.get("fertilizer"), 4)
                .addIngredient(Tags.Items.SAND)
                .addIngredient(Items.BONE_MEAL)
                .addIngredient(ITEMS.get("apatite"))
                .addIngredient(ITEMS.get("niter"))
                .addCriterion("has_apatite", hasItem(ITEMS.get("apatite")))
                .build(consumer, ID_THERMAL + ":fertilizer_4");

        ShapelessRecipeBuilder.shapelessRecipe(Items.GUNPOWDER, 4)
                .addIngredient(Items.CHARCOAL)
                .addIngredient(ITEMS.get("niter"))
                .addIngredient(ITEMS.get("niter"))
                .addIngredient(ITEMS.get("sulfur"))
                .addCriterion("has_gunpowder", hasItem(Items.GUNPOWDER))
                .build(consumer, ID_THERMAL + ":gunpowder");

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

        ShapelessRecipeBuilder.shapelessRecipe(Items.QUARTZ, 4)
                .addIngredient(Items.QUARTZ_BLOCK)
                .addIngredient(ITEMS.get(ID_WRENCH))
                .addCriterion("has_quartz_block", hasItem(Items.QUARTZ_BLOCK))
                .build(consumer, ID_THERMAL + ":split_quartz_block");

        DeferredRegisterCoFH<Item> reg = ITEMS;

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
