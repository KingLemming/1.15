package cofh.thermal.expansion.datagen;

import cofh.lib.datagen.RecipeProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.lib.util.references.CoFHTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.expansion.init.TExpIDs.*;

public class TExpRecipes extends RecipeProviderCoFH {

    public TExpRecipes(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
    }

    @Override
    public String getName() {

        return "Thermal Expansion: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        generateMachineRecipes(consumer);
        generateDynamoRecipes(consumer);
    }

    private void generateMachineRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        Block machineFrame = regBlocks.get("machine_frame");
        Item rfCoil = regItems.get("rf_coil");

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_FURNACE))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_COPPER)
                .key('P', rfCoil)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', Blocks.BRICKS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_SAWMILL))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_COPPER)
                .key('P', rfCoil)
                .key('X', regItems.get("saw_blade"))
                .key('Y', Tags.Items.STONE)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_PULVERIZER))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_COPPER)
                .key('P', rfCoil)
                .key('X', Blocks.PISTON)
                .key('Y', Items.FLINT)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_SMELTER))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_INVAR)
                .key('P', rfCoil)
                .key('X', Blocks.BLAST_FURNACE)
                .key('Y', Tags.Items.SAND)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_INSOLATOR))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_LUMIUM)
                .key('P', rfCoil)
                .key('X', Blocks.DIRT)
                .key('Y', Tags.Items.GLASS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_CENTRIFUGE))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_CONSTANTAN)
                .key('P', rfCoil)
                .key('X', Items.COMPASS)
                .key('Y', CoFHTags.Items.INGOTS_TIN)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_PRESS))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_CONSTANTAN)
                .key('P', rfCoil)
                .key('X', Tags.Items.STORAGE_BLOCKS_IRON)
                .key('Y', CoFHTags.Items.INGOTS_BRONZE)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_CRUCIBLE))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_INVAR)
                .key('P', rfCoil)
                .key('X', Tags.Items.GLASS)
                .key('Y', Blocks.NETHER_BRICKS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_CHILLER))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_INVAR)
                .key('P', rfCoil)
                .key('X', Tags.Items.GLASS)
                .key('Y', Blocks.PACKED_ICE)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_REFINERY))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_INVAR)
                .key('P', rfCoil)
                .key('X', Tags.Items.GLASS)
                .key('Y', CoFHTags.Items.INGOTS_COPPER)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_BREWER))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_CONSTANTAN)
                .key('P', rfCoil)
                .key('X', Blocks.BREWING_STAND)
                .key('Y', Tags.Items.GLASS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_MACHINE_BOTTLER))
                .key('C', machineFrame)
                .key('I', CoFHTags.Items.GEARS_COPPER)
                .key('P', rfCoil)
                .key('X', Items.BUCKET)
                .key('Y', Tags.Items.GLASS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);
    }

    private void generateDynamoRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        Item rfCoil = regItems.get("rf_coil");

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_DYNAMO_STIRLING))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', CoFHTags.Items.GEARS_IRON)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', Tags.Items.STONE)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_DYNAMO_COMPRESSION))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', CoFHTags.Items.GEARS_BRONZE)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', CoFHTags.Items.INGOTS_BRONZE)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_DYNAMO_MAGMATIC))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', CoFHTags.Items.GEARS_INVAR)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', CoFHTags.Items.INGOTS_INVAR)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_DYNAMO_NUMISMATIC))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', CoFHTags.Items.GEARS_TIN)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', CoFHTags.Items.INGOTS_CONSTANTAN)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(regBlocks.get(ID_DYNAMO_LAPIDARY))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', CoFHTags.Items.GEARS_GOLD)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', Tags.Items.GEMS_LAPIS)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);
    }

}
