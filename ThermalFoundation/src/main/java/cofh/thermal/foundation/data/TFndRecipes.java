package cofh.thermal.foundation.data;

import cofh.lib.data.RecipeProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;

public class TFndRecipes extends RecipeProviderCoFH {

    public TFndRecipes(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
    }

    @Override
    public String getName() {

        return "Thermal Foundation: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Item> regItems = ITEMS;

        generateStorageRecipes(regItems, consumer, "copper");
        generateStorageRecipes(regItems, consumer, "tin");
        generateStorageRecipes(regItems, consumer, "silver");
        generateStorageRecipes(regItems, consumer, "lead");
        generateStorageRecipes(regItems, consumer, "nickel");
        generateStorageRecipes(regItems, consumer, "platinum");

        generateStorageRecipes(regItems, consumer, "bronze");
        generateStorageRecipes(regItems, consumer, "electrum");
        generateStorageRecipes(regItems, consumer, "invar");
        generateStorageRecipes(regItems, consumer, "constantan");

        generateSmeltingAndBlastingRecipes(regItems, consumer, "copper", 0.6F);
        generateSmeltingAndBlastingRecipes(regItems, consumer, "tin", 0.6F);
        generateSmeltingAndBlastingRecipes(regItems, consumer, "silver", 0.8F);
        generateSmeltingAndBlastingRecipes(regItems, consumer, "lead", 0.8F);
        generateSmeltingAndBlastingRecipes(regItems, consumer, "nickel", 1.0F);
        generateSmeltingAndBlastingRecipes(regItems, consumer, "platinum", 1.0F);

        generateSmeltingAndBlastingRecipes(regItems, consumer, "bronze", 0);
        generateSmeltingAndBlastingRecipes(regItems, consumer, "electrum", 0);
        generateSmeltingAndBlastingRecipes(regItems, consumer, "invar", 0);
        generateSmeltingAndBlastingRecipes(regItems, consumer, "constantan", 0);
    }

}
