package cofh.thermal.core.util.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

import static cofh.lib.util.RecipeJsonUtils.*;

public class ThermalBoostSerializer<T extends ThermalBoost> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    protected final int defaultCycles;
    protected final ThermalBoostSerializer.IFactory<T> recipeFactory;

    public ThermalBoostSerializer(ThermalBoostSerializer.IFactory<T> recipeFactory, int defaultCycles) {

        this.recipeFactory = recipeFactory;
        this.defaultCycles = defaultCycles;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {

        Ingredient ingredient;

        float boostMult = 1.0F;
        int boostCycles = defaultCycles;

        /* INPUT */
        ingredient = parseIngredient(json.get(INGREDIENT));

        if (json.has(BOOST_MOD)) {
            boostMult = json.get(BOOST_MOD).getAsFloat();
        }
        if (json.has(CYCLES)) {
            boostCycles = json.get(CYCLES).getAsInt();
        }
        return recipeFactory.create(recipeId, ingredient, boostMult, boostCycles);
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {

        Ingredient ingredient = Ingredient.read(buffer);

        float boostMult = buffer.readFloat();
        int boostCycles = buffer.readInt();

        return recipeFactory.create(recipeId, ingredient, boostMult, boostCycles);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {

        recipe.ingredient.write(buffer);

        buffer.writeFloat(recipe.boostMult);
        buffer.writeInt(recipe.boostCycles);
    }

    public interface IFactory<T extends ThermalBoost> {

        T create(ResourceLocation recipeId, Ingredient inputItem, float boostMult, int boostCycles);

    }

}
