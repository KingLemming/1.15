package cofh.thermal.expansion.util.recipes;

import cofh.thermal.core.util.recipes.ThermalCatalyst;
import cofh.thermal.expansion.init.TExpRecipes;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.core.util.recipes.RecipeJsonUtils.*;
import static cofh.thermal.expansion.init.TExpReferences.ID_CATALYST_PULVERIZER;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PULVERIZER_BLOCK;

public class PulverizerCatalyst extends ThermalCatalyst {

    protected PulverizerCatalyst(ResourceLocation id, Ingredient ingredient, float primaryMod, float secondaryMod, float energyMod, float minChance, float useChance) {

        super(id, ingredient, primaryMod, secondaryMod, energyMod, minChance, useChance);
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_CATALYST_PULVERIZER);
    }

    @Nonnull
    @Override
    public IRecipeType<?> getType() {

        return TExpRecipes.CATALYST_PULVERIZER;
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

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PulverizerCatalyst> {

        @Override
        public PulverizerCatalyst read(ResourceLocation recipeId, JsonObject json) {

            Ingredient ingredient;

            float primaryMod = 1.0F;
            float secondaryMod = 1.0F;
            float energyMod = 1.0F;
            float minChance = 0.0F;
            float useChance = 1.0F;

            /* INPUT */
            ingredient = parseIngredient(json);

            if (json.has(PRIMARY_MOD)) {
                primaryMod = json.get(PRIMARY_MOD).getAsFloat();
            }
            if (json.has(SECONDARY_MOD)) {
                secondaryMod = json.get(SECONDARY_MOD).getAsFloat();
            }
            if (json.has(ENERGY_MOD)) {
                energyMod = json.get(ENERGY_MOD).getAsFloat();
            }
            if (json.has(MIN_CHANCE)) {
                minChance = json.get(MIN_CHANCE).getAsFloat();
            }
            if (json.has(USE_CHANCE)) {
                useChance = json.get(USE_CHANCE).getAsFloat();
            }
            return new PulverizerCatalyst(recipeId, ingredient, primaryMod, secondaryMod, energyMod, minChance, useChance);
        }

        @Nullable
        @Override
        public PulverizerCatalyst read(ResourceLocation recipeId, PacketBuffer buffer) {

            Ingredient ingredient = Ingredient.read(buffer);

            float primaryMod = buffer.readFloat();
            float secondaryMod = buffer.readFloat();
            float energyMod = buffer.readFloat();
            float minChance = buffer.readFloat();
            float useChance = buffer.readFloat();

            return new PulverizerCatalyst(recipeId, ingredient, primaryMod, secondaryMod, energyMod, minChance, useChance);
        }

        @Override
        public void write(PacketBuffer buffer, PulverizerCatalyst recipe) {

            recipe.ingredient.write(buffer);

            buffer.writeFloat(recipe.primaryMod);
            buffer.writeFloat(recipe.secondaryMod);
            buffer.writeFloat(recipe.energyMod);
            buffer.writeFloat(recipe.minChance);
            buffer.writeFloat(recipe.useChance);
        }

    }

}
