package cofh.thermal.foundation.data;

import cofh.thermal.core.data.ThermalRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;

import java.util.function.Consumer;

public class TFndRecipes extends ThermalRecipeProvider {

    public TFndRecipes(DataGenerator generatorIn) {

        super(generatorIn);
    }

    @Override
    public String getName() {

        return "Thermal Foundation: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        generateStorageRecipes(consumer, "copper");
        generateStorageRecipes(consumer, "tin");
        generateStorageRecipes(consumer, "silver");
        generateStorageRecipes(consumer, "lead");
        generateStorageRecipes(consumer, "nickel");
        generateStorageRecipes(consumer, "platinum");

        generateStorageRecipes(consumer, "bronze");
        generateStorageRecipes(consumer, "electrum");
        generateStorageRecipes(consumer, "invar");
        generateStorageRecipes(consumer, "constantan");

        generateSmeltingAndBlastingRecipes(consumer, "copper", 0.6F);
        generateSmeltingAndBlastingRecipes(consumer, "tin", 0.6F);
        generateSmeltingAndBlastingRecipes(consumer, "silver", 0.8F);
        generateSmeltingAndBlastingRecipes(consumer, "lead", 0.8F);
        generateSmeltingAndBlastingRecipes(consumer, "nickel", 1.0F);
        generateSmeltingAndBlastingRecipes(consumer, "platinum", 1.0F);

        generateSmeltingAndBlastingRecipes(consumer, "bronze", 0);
        generateSmeltingAndBlastingRecipes(consumer, "electrum", 0);
        generateSmeltingAndBlastingRecipes(consumer, "invar", 0);
        generateSmeltingAndBlastingRecipes(consumer, "constantan", 0);
    }

}
