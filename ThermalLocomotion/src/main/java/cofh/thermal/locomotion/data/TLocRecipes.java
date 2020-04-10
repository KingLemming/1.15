package cofh.thermal.locomotion.data;

import cofh.thermal.core.data.ThermalRecipeProvider;
import cofh.thermal.core.init.ThermalTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.TLocReferences.*;

public class TLocRecipes extends ThermalRecipeProvider {

    public TLocRecipes(DataGenerator generatorIn) {

        super(generatorIn);
    }

    @Override
    public String getName() {

        return "Thermal Locomotion: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        String rail = Items.RAIL.getRegistryName().getPath();
        String activatorRail = Items.ACTIVATOR_RAIL.getRegistryName().getPath();
        String detectorRail = Items.DETECTOR_RAIL.getRegistryName().getPath();
        String poweredRail = Items.POWERED_RAIL.getRegistryName().getPath();

        // @formatter:off
        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_CROSSOVER_RAIL))
                .key('I', Items.STICK)
                .key('X', Blocks.RAIL)
                .patternLine("XI")
                .patternLine("IX")
                .addCriterion("has_" + rail, hasItem(Items.RAIL))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_PRISMARINE_RAIL), 6)
                .key('C', Items.PRISMARINE_CRYSTALS)
                .key('S', Items.PRISMARINE_SHARD)
                .key('X', Blocks.RAIL)
                .patternLine("XSX")
                .patternLine("XCX")
                .patternLine("XSX")
                .addCriterion("has_" + rail, hasItem(Items.RAIL))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL))
                .key('I', Items.STICK)
                .key('X', BLOCKS.get(ID_PRISMARINE_RAIL))
                .patternLine("XI")
                .patternLine("IX")
                .addCriterion("has_" + BLOCKS.get(ID_PRISMARINE_RAIL).getRegistryName().getPath(), hasItem(ITEMS.get(ID_PRISMARINE_RAIL)))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL), 6)
                .key('C', Items.PRISMARINE_CRYSTALS)
                .key('S', Items.PRISMARINE_SHARD)
                .key('X', Blocks.ACTIVATOR_RAIL)
                .patternLine("XSX")
                .patternLine("XCX")
                .patternLine("XSX")
                .addCriterion("has_" + activatorRail, hasItem(Items.ACTIVATOR_RAIL))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL), 6)
                .key('C', Items.PRISMARINE_CRYSTALS)
                .key('S', Items.PRISMARINE_SHARD)
                .key('X', Blocks.DETECTOR_RAIL)
                .patternLine("XSX")
                .patternLine("XCX")
                .patternLine("XSX")
                .addCriterion("has_" + detectorRail, hasItem(Items.DETECTOR_RAIL))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_PRISMARINE_POWERED_RAIL), 6)
                .key('C', Items.PRISMARINE_CRYSTALS)
                .key('S', Items.PRISMARINE_SHARD)
                .key('X', Blocks.POWERED_RAIL)
                .patternLine("XSX")
                .patternLine("XCX")
                .patternLine("XSX")
                .addCriterion("has_" + poweredRail, hasItem(Items.POWERED_RAIL))
                .build(consumer);
        
        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_LUMIUM_RAIL), 6)
                .key('I', ThermalTags.Items.INGOTS_LUMIUM)
                .key('X', Blocks.RAIL)
                .patternLine("XIX")
                .patternLine("XIX")
                .patternLine("XIX")
                .addCriterion("has_" + rail, hasItem(Items.RAIL))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL))
                .key('I', Items.STICK)
                .key('X', BLOCKS.get(ID_LUMIUM_RAIL))
                .patternLine("XI")
                .patternLine("IX")
                .addCriterion("has_" + BLOCKS.get(ID_LUMIUM_RAIL).getRegistryName().getPath(), hasItem(ITEMS.get(ID_LUMIUM_RAIL)))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL), 6)
                .key('I', ThermalTags.Items.INGOTS_LUMIUM)
                .key('X', Blocks.ACTIVATOR_RAIL)
                .patternLine("XIX")
                .patternLine("XIX")
                .patternLine("XIX")
                .addCriterion("has_" + activatorRail, hasItem(Items.ACTIVATOR_RAIL))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL), 6)
                .key('I', ThermalTags.Items.INGOTS_LUMIUM)
                .key('X', Blocks.DETECTOR_RAIL)
                .patternLine("XIX")
                .patternLine("XIX")
                .patternLine("XIX")
                .addCriterion("has_" + detectorRail, hasItem(Items.DETECTOR_RAIL))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(BLOCKS.get(ID_LUMIUM_POWERED_RAIL), 6)
                .key('I', ThermalTags.Items.INGOTS_LUMIUM)
                .key('X', Blocks.POWERED_RAIL)
                .patternLine("XIX")
                .patternLine("XIX")
                .patternLine("XIX")
                .addCriterion("has_" + poweredRail, hasItem(Items.POWERED_RAIL))
                .build(consumer);
        // @formatter:on
    }

}
