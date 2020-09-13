package cofh.thermal.expansion.util.recipes.machine;

import cofh.core.util.ComparableItemStack;
import cofh.thermal.core.util.IMachineInventory;
import cofh.thermal.core.util.recipes.internal.BaseMachineRecipe;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static cofh.core.util.constants.Constants.BASE_CHANCE_LOCKED;
import static cofh.core.util.helpers.ItemHelper.itemsEqual;
import static cofh.thermal.core.util.managers.AbstractManager.convert;

public class CrafterRecipe extends BaseMachineRecipe {

    protected final List<Ingredient> ingredients;
    protected final Set<ComparableItemStack> validItems = new ObjectOpenHashSet<>();

    public static final List<Float> CHANCE = Collections.singletonList(1.0F);

    public CrafterRecipe(int energy, IRecipe<?> recipe) {

        super(energy, 0, 0);

        ingredients = recipe.getIngredients();

        for (Ingredient ing : ingredients) {
            for (ItemStack stack : ing.getMatchingStacks()) {
                validItems.add(convert(stack));
            }
        }
        outputItems.add(recipe.getRecipeOutput());
        outputItemChances.add(BASE_CHANCE_LOCKED);
    }

    public boolean validItem(ItemStack item) {

        return validItems.contains(convert(item));
    }

    @Override
    public List<Float> getOutputItemChances(IMachineInventory inventory) {

        return CHANCE;
    }

    @Override
    public List<Integer> getInputItemCounts(IMachineInventory inventory) {

        if (ingredients.isEmpty()) {
            return Collections.emptyList();
        }
        int[] ret = new int[inventory.inputSlots().size()];
        int found = 0;

        for (Ingredient ing : ingredients) {
            for (ItemStack stack : ing.getMatchingStacks()) {
                for (int j = 0; j < ret.length; ++j) {
                    ItemStack inSlot = inventory.inputSlots().get(j).getItemStack();
                    if (inSlot.getCount() > ret[j] && itemsEqual(stack, inSlot)) {
                        ++ret[j];
                        ++found;
                        break;
                    }
                }
            }
        }
        return found < ingredients.size() ? Collections.emptyList() : IntStream.of(ret).boxed().collect(Collectors.toList());
    }

}
