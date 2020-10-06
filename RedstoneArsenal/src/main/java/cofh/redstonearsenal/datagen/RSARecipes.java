package cofh.redstonearsenal.datagen;

import cofh.core.datagen.RecipeProviderCoFH;
import cofh.core.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;

import java.util.function.Consumer;

import static cofh.core.util.constants.Constants.ID_REDSTONE_ARSENAL;
import static cofh.redstonearsenal.RedstoneArsenal.ITEMS;

public class RSARecipes extends RecipeProviderCoFH {

    public RSARecipes(DataGenerator generatorIn) {

        super(generatorIn, ID_REDSTONE_ARSENAL);
    }

    @Override
    public String getName() {

        return "Redstone Arsenal: Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Item> reg = ITEMS;

    }

}
