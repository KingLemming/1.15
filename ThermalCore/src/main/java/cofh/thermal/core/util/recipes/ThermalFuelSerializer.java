package cofh.thermal.core.util.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static cofh.lib.util.RecipeJsonUtils.*;

public class ThermalFuelSerializer<T extends ThermalFuel> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    protected final int defaultEnergy;
    protected final ThermalFuelSerializer.IFactory<T> fuelFactory;

    public ThermalFuelSerializer(ThermalFuelSerializer.IFactory<T> fuelFactory, int defaultEnergy) {

        this.fuelFactory = fuelFactory;
        this.defaultEnergy = defaultEnergy;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {

        int energy = defaultEnergy;

        ArrayList<Ingredient> inputItems = new ArrayList<>();
        ArrayList<FluidStack> inputFluids = new ArrayList<>();

        /* INPUT */
        if (json.has(INGREDIENT)) {
            parseInputs(inputItems, inputFluids, json.get(INGREDIENT));
        } else if (json.has(INGREDIENTS)) {
            parseInputs(inputItems, inputFluids, json.get(INGREDIENTS));
        } else if (json.has(INPUT)) {
            parseInputs(inputItems, inputFluids, json.get(INPUT));
        } else if (json.has(INPUTS)) {
            parseInputs(inputItems, inputFluids, json.get(INPUTS));
        }

        /* ENERGY */
        if (json.has(ENERGY)) {
            energy = json.get(ENERGY).getAsInt();
        }
        if (json.has(ENERGY_MOD)) {
            energy *= json.get(ENERGY_MOD).getAsFloat();
        }
        return fuelFactory.create(recipeId, energy, inputItems, inputFluids);
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {

        int energy = buffer.readVarInt();

        int numInputItems = buffer.readVarInt();
        ArrayList<Ingredient> inputItems = new ArrayList<>(numInputItems);
        for (int i = 0; i < numInputItems; ++i) {
            inputItems.add(Ingredient.read(buffer));
        }

        int numInputFluids = buffer.readVarInt();
        ArrayList<FluidStack> inputFluids = new ArrayList<>(numInputFluids);
        for (int i = 0; i < numInputFluids; ++i) {
            inputFluids.add(buffer.readFluidStack());
        }
        return fuelFactory.create(recipeId, energy, inputItems, inputFluids);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {

        buffer.writeVarInt(recipe.energy);

        int numInputItems = recipe.inputItems.size();
        buffer.writeVarInt(numInputItems);
        for (int i = 0; i < numInputItems; ++i) {
            recipe.inputItems.get(i).write(buffer);
        }
        int numInputFluids = recipe.inputFluids.size();
        buffer.writeVarInt(numInputFluids);
        for (int i = 0; i < numInputFluids; ++i) {
            buffer.writeFluidStack(recipe.inputFluids.get(i));
        }
    }

    public interface IFactory<T extends ThermalFuel> {

        T create(ResourceLocation recipeId, int energy, List<Ingredient> inputItems, List<FluidStack> inputFluids);

    }

}
