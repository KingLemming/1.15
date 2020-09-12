package cofh.thermal.core.util.recipes.device;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

import static cofh.core.util.RecipeJsonUtils.*;

public class TreeExtractorBoostSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<TreeExtractorBoost> {

    @Override
    public TreeExtractorBoost read(ResourceLocation recipeId, JsonObject json) {

        Ingredient ingredient;
        float boostMult = 1.0F;
        int boostCycles = 8;

        /* INPUT */
        ingredient = parseIngredient(json.get(INGREDIENT));

        if (json.has(BOOST_MOD)) {
            boostMult = json.get(BOOST_MOD).getAsFloat();
        }
        if (json.has(CYCLES)) {
            boostCycles = json.get(CYCLES).getAsInt();
        }
        return new TreeExtractorBoost(recipeId, ingredient, boostMult, boostCycles);
    }

    @Nullable
    @Override
    public TreeExtractorBoost read(ResourceLocation recipeId, PacketBuffer buffer) {

        Ingredient ingredient = Ingredient.read(buffer);

        float boostMult = buffer.readFloat();
        int boostCycles = buffer.readInt();

        return new TreeExtractorBoost(recipeId, ingredient, boostMult, boostCycles);
    }

    @Override
    public void write(PacketBuffer buffer, TreeExtractorBoost recipe) {

        recipe.ingredient.write(buffer);

        buffer.writeFloat(recipe.boostMult);
        buffer.writeInt(recipe.boostCycles);
    }

}
