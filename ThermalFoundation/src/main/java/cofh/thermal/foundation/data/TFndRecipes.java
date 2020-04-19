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

        DeferredRegisterCoFH<Item> itemReg = ITEMS;

        generateStorageRecipes(itemReg, consumer, "copper");
        generateStorageRecipes(itemReg, consumer, "tin");
        generateStorageRecipes(itemReg, consumer, "silver");
        generateStorageRecipes(itemReg, consumer, "lead");
        generateStorageRecipes(itemReg, consumer, "nickel");
        generateStorageRecipes(itemReg, consumer, "platinum");

        generateStorageRecipes(itemReg, consumer, "bronze");
        generateStorageRecipes(itemReg, consumer, "electrum");
        generateStorageRecipes(itemReg, consumer, "invar");
        generateStorageRecipes(itemReg, consumer, "constantan");

        generateSmeltingAndBlastingRecipes(itemReg, consumer, "copper", 0.6F);
        generateSmeltingAndBlastingRecipes(itemReg, consumer, "tin", 0.6F);
        generateSmeltingAndBlastingRecipes(itemReg, consumer, "silver", 0.8F);
        generateSmeltingAndBlastingRecipes(itemReg, consumer, "lead", 0.8F);
        generateSmeltingAndBlastingRecipes(itemReg, consumer, "nickel", 1.0F);
        generateSmeltingAndBlastingRecipes(itemReg, consumer, "platinum", 1.0F);

        generateSmeltingAndBlastingRecipes(itemReg, consumer, "bronze", 0);
        generateSmeltingAndBlastingRecipes(itemReg, consumer, "electrum", 0);
        generateSmeltingAndBlastingRecipes(itemReg, consumer, "invar", 0);
        generateSmeltingAndBlastingRecipes(itemReg, consumer, "constantan", 0);
    }

}
