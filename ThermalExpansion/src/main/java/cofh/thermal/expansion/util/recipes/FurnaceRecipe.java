package cofh.thermal.expansion.util.recipes;

import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.expansion.init.TExpRecipes;
import cofh.thermal.expansion.util.managers.machine.FurnaceRecipeManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;
import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.core.util.recipes.RecipeJsonUtils.*;
import static cofh.thermal.expansion.init.TExpReferences.ID_RECIPE_FURNACE;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_FURNACE_BLOCK;

public class FurnaceRecipe extends ThermalRecipe {

    private FurnaceRecipe(ResourceLocation id, int energy, float experience, Ingredient input, ItemStack output) {

        super(id, energy, experience, input, output, BASE_CHANCE_LOCKED);
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_RECIPE_FURNACE);
    }

    @Nonnull
    @Override
    public IRecipeType<?> getType() {

        return TExpRecipes.RECIPE_FURNACE;
    }

    @Nonnull
    @Override
    public String getGroup() {

        return MACHINE_FURNACE_BLOCK.getTranslationKey();
    }

    @Nonnull
    @Override
    public ItemStack getIcon() {

        return new ItemStack(MACHINE_FURNACE_BLOCK);
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FurnaceRecipe> {

        @Override
        public FurnaceRecipe read(ResourceLocation recipeId, JsonObject json) {

            Ingredient input;
            ItemStack output;
            int energy = FurnaceRecipeManager.instance().getDefaultEnergy();
            float experience = 0.0F;

            /* INPUT */
            JsonElement jsonelement = JSONUtils.isJsonArray(json, INGREDIENT) ? JSONUtils.getJsonArray(json, INGREDIENT) : JSONUtils.getJsonObject(json, INGREDIENT);
            input = Ingredient.deserialize(jsonelement);

            /* OUTPUT */
            output = parseItemStack(json.get(RESULT));

            /* ENERGY */
            if (json.has(ENERGY)) {
                energy = json.get(ENERGY).getAsInt();
            }
            if (json.has(ENERGY_MOD)) {
                energy *= json.get(ENERGY_MOD).getAsFloat();
            }
            /* EXPERIENCE */
            if (json.has(EXPERIENCE)) {
                experience = json.get(EXPERIENCE).getAsFloat();
            }
            return new FurnaceRecipe(recipeId, energy, experience, input, output);
        }

        @Nullable
        @Override
        public FurnaceRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

            int energy = buffer.readVarInt();
            float experience = buffer.readFloat();
            Ingredient input = Ingredient.read(buffer);
            ItemStack output = buffer.readItemStack();

            return new FurnaceRecipe(recipeId, energy, experience, input, output);
        }

        @Override
        public void write(PacketBuffer buffer, FurnaceRecipe recipe) {

            buffer.writeVarInt(recipe.energy);
            buffer.writeFloat(recipe.experience);
            recipe.inputItems.get(0).write(buffer);
            buffer.writeItemStack(recipe.outputItems.get(0));
        }

    }

}
