package cofh.thermal.core.util.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;

public abstract class RecipeJsonUtils {

    private RecipeJsonUtils() {

    }

    public static boolean validityCheck(JsonObject json) {

        if (json.has(COMMENT)) {
            return false;
        }
        if (json.has(ENABLE) && !json.get(ENABLE).getAsBoolean()) {
            return false;
        }
        if (json.has(DEPENDENCY)) {
            return parseDependencies(json.get(DEPENDENCY));
        }
        return true;
    }

    // region HELPERS
    public static Ingredient parseIngredient(JsonObject json) {

        Ingredient ingredient;
        JsonElement element = JSONUtils.isJsonArray(json, INGREDIENT) ? JSONUtils.getJsonArray(json, INGREDIENT) : JSONUtils.getJsonObject(json, INGREDIENT);

        try {
            ingredient = Ingredient.deserialize(element);
        } catch (Throwable t) {
            ingredient = Ingredient.fromStacks(ItemStack.EMPTY);
        }
        return ingredient;
    }

    public static void parseInputs(List<ItemStack> items, List<FluidStack> fluids, JsonElement element) {

        if (element.isJsonArray()) {
            for (JsonElement arrayElement : element.getAsJsonArray()) {
                if (arrayElement.getAsJsonObject().has(FLUID)) {
                    fluids.add(parseFluidStack(arrayElement));
                } else {
                    items.add(parseItemStack(arrayElement));
                }
            }
        } else if (element.getAsJsonObject().has(FLUID)) {
            fluids.add(parseFluidStack(element));
        } else {
            items.add(parseItemStack(element));
        }
    }

    public static void parseOutputs(List<ItemStack> items, List<Float> chances, List<FluidStack> fluids, JsonElement element) {

        if (element.isJsonArray()) {
            for (JsonElement arrayElement : element.getAsJsonArray()) {
                if (arrayElement.getAsJsonObject().has(FLUID)) {
                    fluids.add(parseFluidStack(arrayElement));
                } else {
                    items.add(parseItemStack(arrayElement));
                    chances.add(parseItemChance(arrayElement));
                }
            }
        } else if (element.getAsJsonObject().has(FLUID)) {
            fluids.add(parseFluidStack(element));
        } else {
            items.add(parseItemStack(element));
            chances.add(parseItemChance(element));
        }
    }

    public static boolean parseDependencies(JsonElement element) {

        if (element.isJsonArray()) {
            boolean check = true;
            for (JsonElement arrayElement : element.getAsJsonArray()) {
                check &= parseDependency(arrayElement);
            }
            return check;
        } else {
            return parseDependency(element);
        }
    }

    public static ItemStack parseItemStack(JsonElement element) {

        if (element == null || element.isJsonNull()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack;
        Item item = null;
        int count = 1;

        if (element.isJsonPrimitive()) {
            item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(element.getAsString()));
            return item == null ? ItemStack.EMPTY : new ItemStack(item);
        } else {
            JsonObject itemObject = element.getAsJsonObject();

            /* COUNT */
            if (itemObject.has(COUNT)) {
                count = itemObject.get(COUNT).getAsInt();
            } else if (itemObject.has(AMOUNT)) {
                count = itemObject.get(AMOUNT).getAsInt();
            }

            /* ITEM */
            if (itemObject.has(ITEM)) {
                item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemObject.get(ITEM).getAsString()));
            }
            if (item == null) {
                return ItemStack.EMPTY;
            }
            stack = new ItemStack(item, count);

            /* NBT */
            if (itemObject.has(NBT)) {
                try {
                    stack.setTag(JsonToNBT.getTagFromJson(itemObject.get(NBT).getAsString()));
                } catch (Exception e) {
                    return ItemStack.EMPTY;
                }
            }
        }
        return stack;
    }

    public static void parseItemStacks(List<ItemStack> items, JsonElement element) {

        if (element.isJsonArray()) {
            for (JsonElement arrayElement : element.getAsJsonArray()) {
                items.add(parseItemStack(arrayElement));
            }
        } else {
            items.add(parseItemStack(element));
        }
    }

    public static void parseItemStacks(List<ItemStack> items, List<Float> chances, JsonElement element) {

        if (element.isJsonArray()) {
            for (JsonElement arrayElement : element.getAsJsonArray()) {
                items.add(parseItemStack(arrayElement));
                chances.add(parseItemChance(arrayElement));
            }
        } else {
            items.add(parseItemStack(element));
            chances.add(parseItemChance(element));
        }
    }

    public static FluidStack parseFluidStack(JsonElement element) {

        if (element == null || element.isJsonNull()) {
            return FluidStack.EMPTY;
        }
        FluidStack stack;
        Fluid fluid = null;
        int amount = FluidAttributes.BUCKET_VOLUME;

        if (element.isJsonPrimitive()) {
            fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(element.getAsString()));
            return fluid == null ? FluidStack.EMPTY : new FluidStack(fluid, amount);
        } else {
            JsonObject fluidObject = element.getAsJsonObject();

            /* AMOUNT */
            if (fluidObject.has(AMOUNT)) {
                amount = fluidObject.get(AMOUNT).getAsInt();
            } else if (fluidObject.has(COUNT)) {
                amount = fluidObject.get(COUNT).getAsInt();
            }

            /* FLUID */
            if (fluidObject.has(FLUID)) {
                fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidObject.get(FLUID).getAsString()));
            }
            if (fluid == null) {
                return FluidStack.EMPTY;
            }
            stack = new FluidStack(fluid, amount);

            /* NBT */
            if (fluidObject.has(NBT)) {
                try {
                    stack.setTag(JsonToNBT.getTagFromJson(fluidObject.get(NBT).getAsString()));
                } catch (Exception e) {
                    return FluidStack.EMPTY;
                }
            }
        }
        return stack;
    }

    public static void parseFluidStacks(List<FluidStack> fluids, JsonElement element) {

        if (element.isJsonArray()) {
            for (JsonElement arrayElement : element.getAsJsonArray()) {
                fluids.add(parseFluidStack(arrayElement));
            }
        } else {
            fluids.add(parseFluidStack(element));
        }
    }

    public static float parseItemChance(JsonElement element) {

        JsonObject json = element.getAsJsonObject();

        if (json.has(CHANCE)) {
            float chance = json.get(CHANCE).getAsFloat();
            if (chance > 0.0F && json.has(LOCKED) && json.get(LOCKED).getAsBoolean()) {
                chance *= BASE_CHANCE_LOCKED;
            }
            return chance;
        }
        return BASE_CHANCE_LOCKED;
    }

    public static boolean parseDependency(JsonElement element) {

        JsonObject json = element.getAsJsonObject();

        if (json.has(MOD)) {
            return ModList.get().isLoaded(json.get(MOD).getAsString());
        }
        return true;
    }
    // endregion

    // region STRING CONSTANTS
    public static final String AMOUNT = "amount";
    public static final String CHANCE = "chance";
    public static final String COMMENT = "//";
    public static final String CONSTANT = "constant";
    public static final String COUNT = "count";
    public static final String DEPENDENCY = "dependency";
    public static final String ENABLE = "enable";
    public static final String ENERGY = "energy";
    public static final String ENERGY_MOD = "energy_mod";
    public static final String EXPERIENCE = "experience";
    public static final String ENTRY = "entry";
    public static final String FLUID = "fluid";
    public static final String INGREDIENT = "ingredient";
    public static final String ITEM = "item";
    public static final String LOCKED = "locked";
    public static final String MIN_CHANCE = "min_chance";
    public static final String MOD = "mod";
    public static final String NBT = "nbt";
    public static final String ORE = "ore";
    public static final String PRIMARY_MOD = "primary_mod";
    public static final String REMOVE = "remove";
    public static final String RESULT = "result";
    public static final String SECONDARY_MOD = "secondary_mod";
    public static final String TYPE = "type";
    public static final String USE_CHANCE = "use_chance";
    public static final String WILDCARD = "wildcard";

    public static final String LAVA = "lava";
    public static final String WATER = "water";

    public static final String LAVA_MOD = "lava_mod";
    public static final String WATER_MOD = "water_mod";
    // endregion
}
