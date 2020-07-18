package cofh.archersparadox.datagen;

import cofh.core.CoFHCore;
import cofh.lib.data.RecipeProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.archersparadox.ArchersParadox.ITEMS;
import static cofh.archersparadox.init.APReferences.*;
import static cofh.lib.util.constants.Constants.ID_ARCHERS_PARADOX;
import static cofh.lib.util.references.CoreReferences.ID_ECTOPLASM;

public class APRecipeProvider extends RecipeProviderCoFH {

    public APRecipeProvider(DataGenerator generatorIn) {

        super(generatorIn, ID_ARCHERS_PARADOX);
    }

    @Override
    public String getName() {

        return "Archer's Paradox: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_BLAZE_ARROW), 4)
                .key('X', Items.BLAZE_POWDER)
                .key('#', Items.ARROW)
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(Items.BLAZE_POWDER))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_CHALLENGE_ARROW), 4)
                .key('X', Items.EXPERIENCE_BOTTLE)
                .key('Y', Tags.Items.GEMS_EMERALD)
                .key('Z', Tags.Items.GEMS_LAPIS)
                .key('#', ITEMS.get(ID_TRAINING_ARROW))
                .patternLine("Y#Z")
                .patternLine("#X#")
                .patternLine("Z#Y")
                .addCriterion("has_components", hasItem(Items.EXPERIENCE_BOTTLE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_DIAMOND_ARROW), 4)
                .key('X', Tags.Items.GEMS_DIAMOND)
                .key('#', Items.STICK)
                .key('Y', Items.FEATHER)
                .patternLine("X")
                .patternLine("#")
                .patternLine("Y")
                .addCriterion("has_components", hasItem(Tags.Items.GEMS_DIAMOND))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_DISPLACEMENT_ARROW), 4)
                .key('X', Items.GUNPOWDER)
                .key('#', ITEMS.get(ID_ENDER_ARROW))
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(ITEMS.get(ID_ENDER_ARROW)))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_ENDER_ARROW), 4)
                .key('X', Items.ENDER_PEARL)
                .key('#', Items.ARROW)
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(Items.ENDER_PEARL))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_EXPLOSIVE_ARROW), 4)
                .key('X', Items.TNT)
                .key('#', Items.ARROW)
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(Items.TNT))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_FROST_ARROW), 4)
                .key('X', Items.PACKED_ICE)
                .key('#', Items.ARROW)
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(Items.PACKED_ICE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_GLOWSTONE_ARROW), 4)
                .key('X', Items.GLOWSTONE_DUST)
                .key('#', Items.ARROW)
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(Items.GLOWSTONE_DUST))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_LIGHTNING_ARROW), 4)
                .key('X', Items.NETHER_STAR)
                .key('#', Items.ARROW)
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(Items.NETHER_STAR))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_PHANTASMAL_ARROW), 2)
                .key('X', Items.ARROW)
                .key('#', CoFHCore.ITEMS.get(ID_ECTOPLASM))
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(CoFHCore.ITEMS.get(ID_ECTOPLASM)))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_PRISMARINE_ARROW), 4)
                .key('X', Tags.Items.GEMS_PRISMARINE)
                .key('#', Items.STICK)
                .key('Y', Items.FEATHER)
                .patternLine("X")
                .patternLine("#")
                .patternLine("Y")
                .addCriterion("has_components", hasItem(Tags.Items.GEMS_PRISMARINE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_QUARTZ_ARROW), 4)
                .key('X', Tags.Items.GEMS_QUARTZ)
                .key('#', Items.STICK)
                .key('Y', Items.FEATHER)
                .patternLine("X")
                .patternLine("#")
                .patternLine("Y")
                .addCriterion("has_components", hasItem(Tags.Items.GEMS_QUARTZ))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_REDSTONE_ARROW), 4)
                .key('X', Items.REDSTONE)
                .key('#', Items.ARROW)
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .addCriterion("has_components", hasItem(Items.REDSTONE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_SHULKER_ARROW), 4)
                .key('X', Items.ENDER_EYE)
                .key('Y', Items.END_STONE)
                .key('Z', Items.POPPED_CHORUS_FRUIT)
                .key('#', Items.ARROW)
                .patternLine("Y#Z")
                .patternLine("#X#")
                .patternLine("Z#Y")
                .addCriterion("has_components", hasItem(Items.ENDER_EYE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_SLIME_ARROW), 4)
                .key('X', Tags.Items.SLIMEBALLS)
                .key('#', Items.STICK)
                .key('Y', Items.FEATHER)
                .patternLine("X")
                .patternLine("#")
                .patternLine("Y")
                .addCriterion("has_components", hasItem(Tags.Items.SLIMEBALLS))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_SPORE_ARROW), 4)
                .key('X', Items.MYCELIUM)
                .key('Y', Items.BONE_MEAL)
                .key('Z', Tags.Items.MUSHROOMS)
                .key('#', Items.ARROW)
                .patternLine("Y#Z")
                .patternLine("#X#")
                .patternLine("Z#Y")
                .addCriterion("has_components", hasItem(Items.MYCELIUM))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_TRAINING_ARROW), 4)
                .key('X', Items.RABBIT_HIDE)
                .key('#', Items.STICK)
                .key('Y', Items.FEATHER)
                .patternLine("X")
                .patternLine("#")
                .patternLine("Y")
                .addCriterion("has_components", hasItem(Items.RABBIT_HIDE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ITEMS.get(ID_VERDANT_ARROW), 4)
                .key('X', Items.GRASS_BLOCK)
                .key('Y', Items.BONE_MEAL)
                .key('Z', ItemTags.SMALL_FLOWERS)
                .key('#', Items.ARROW)
                .patternLine("Y#Z")
                .patternLine("#X#")
                .patternLine("Z#Y")
                .addCriterion("has_components", hasItem(Items.GRASS_BLOCK))
                .build(consumer);
    }

}
