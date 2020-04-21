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

        DeferredRegisterCoFH<Item> reg = ITEMS;

        generateStorageRecipes(reg, consumer, "copper");
        generateStorageRecipes(reg, consumer, "tin");
        generateStorageRecipes(reg, consumer, "silver");
        generateStorageRecipes(reg, consumer, "lead");
        generateStorageRecipes(reg, consumer, "nickel");
        generateStorageRecipes(reg, consumer, "platinum");

        generateStorageRecipes(reg, consumer, "bronze");
        generateStorageRecipes(reg, consumer, "electrum");
        generateStorageRecipes(reg, consumer, "invar");
        generateStorageRecipes(reg, consumer, "constantan");

        generateSmeltingAndBlastingRecipes(reg, consumer, "copper", 0.6F);
        generateSmeltingAndBlastingRecipes(reg, consumer, "tin", 0.6F);
        generateSmeltingAndBlastingRecipes(reg, consumer, "silver", 0.8F);
        generateSmeltingAndBlastingRecipes(reg, consumer, "lead", 0.8F);
        generateSmeltingAndBlastingRecipes(reg, consumer, "nickel", 1.0F);
        generateSmeltingAndBlastingRecipes(reg, consumer, "platinum", 1.0F);

        generateSmeltingAndBlastingRecipes(reg, consumer, "bronze", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "electrum", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "invar", 0);
        generateSmeltingAndBlastingRecipes(reg, consumer, "constantan", 0);
    }

}
