package cofh.thermal.expansion.util.recipes;

import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.expansion.init.TExpRecipes;
import cofh.thermal.expansion.util.managers.machine.PulverizerRecipeManager;
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
import java.util.ArrayList;
import java.util.List;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.core.util.recipes.RecipeJsonUtils.*;
import static cofh.thermal.expansion.init.TExpReferences.ID_RECIPE_PULVERIZER;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PULVERIZER_BLOCK;

public class PulverizerRecipe extends ThermalRecipe {

    private PulverizerRecipe(ResourceLocation id, int energy, float experience, Ingredient input, List<ItemStack> output, List<Float> chance) {

        super(id, energy, experience, input, output, chance);
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_RECIPE_PULVERIZER);
    }

    @Nonnull
    @Override
    public IRecipeType<?> getType() {

        return TExpRecipes.RECIPE_PULVERIZER;
    }

    @Nonnull
    @Override
    public String getGroup() {

        return MACHINE_PULVERIZER_BLOCK.getTranslationKey();
    }

    @Nonnull
    @Override
    public ItemStack getIcon() {

        return new ItemStack(MACHINE_PULVERIZER_BLOCK);
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PulverizerRecipe> {

        @Override
        public PulverizerRecipe read(ResourceLocation recipeId, JsonObject json) {

            Ingredient input;
            ArrayList<ItemStack> output = new ArrayList<>();
            ArrayList<Float> chance = new ArrayList<>();
            int energy = PulverizerRecipeManager.instance().getDefaultEnergy();
            float experience = 0.0F;

            /* INPUT */
            JsonElement jsonelement = JSONUtils.isJsonArray(json, INGREDIENT) ? JSONUtils.getJsonArray(json, INGREDIENT) : JSONUtils.getJsonObject(json, INGREDIENT);
            input = Ingredient.deserialize(jsonelement);

            /* OUTPUT */
            parseItemStacks(output, chance, json.get(RESULT));

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
            return new PulverizerRecipe(recipeId, energy, experience, input, output, chance);
        }

        @Nullable
        @Override
        public PulverizerRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

            int energy = buffer.readVarInt();
            float experience = buffer.readFloat();
            Ingredient input = Ingredient.read(buffer);

            int numOutputs = buffer.readVarInt();
            ArrayList<ItemStack> output = new ArrayList<>(numOutputs);
            ArrayList<Float> chance = new ArrayList<>(numOutputs);

            for (int i = 0; i < numOutputs; ++i) {
                output.add(buffer.readItemStack());
                chance.add(buffer.readFloat());
            }
            return new PulverizerRecipe(recipeId, energy, experience, input, output, chance);
        }

        @Override
        public void write(PacketBuffer buffer, PulverizerRecipe recipe) {

            buffer.writeVarInt(recipe.energy);
            buffer.writeFloat(recipe.experience);
            recipe.inputItems.get(0).write(buffer);

            int numOutputs = recipe.outputItems.size();
            buffer.writeVarInt(numOutputs);
            for (int i = 0; i < numOutputs; ++i) {
                buffer.writeItemStack(recipe.outputItems.get(i));
                buffer.writeFloat(recipe.outputItemChances.get(i));
            }
        }

    }

}
