package cofh.thermal.core.util.recipes.internal;

import cofh.lib.util.helpers.MathHelper;
import cofh.thermal.core.util.IThermalInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CatalyzedMachineRecipe extends BaseMachineRecipe {

    protected int catalystSlot = 1;
    protected boolean catalyzable = true;

    public CatalyzedMachineRecipe(int energy, float experience, int catalystSlot) {

        super(energy, experience);
    }

    public CatalyzedMachineRecipe(int energy, float experience, @Nullable List<ItemStack> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> chance, @Nullable List<FluidStack> outputFluids) {

        super(energy, experience, inputItems, inputFluids, outputItems, chance, outputFluids);

        // If all of the output chances are locked, then the recipe is not catalyzable.
        for (float f : outputItemChances) {
            catalyzable &= f > 0.0F;
        }
    }

    public abstract IRecipeCatalyst getCatalyst(ItemStack input);

    /**
     * Okay so there's a bit of trickery happening here - internally "unmodifiable" chance is stored as a negative. Saves some memory and is kinda clever.
     * This shouldn't ever cause problems because you're relying on this method call and not hacking around in the recipe, right? ;)
     */
    @Override
    public List<Float> getOutputItemChances(IThermalInventory inventory) {

        ArrayList<Float> modifiedChances = new ArrayList<>(outputItemChances);

        // Catalyst Logic
        if (catalyzable && inventory.inputSlots().size() > catalystSlot) {
            ItemStack catalystStack = inventory.inputSlots().get(catalystSlot).getItemStack();
            IRecipeCatalyst catalyst = getCatalyst(catalystStack);
            if (catalyst != null) {
                if (modifiedChances.get(0) >= 0.0F) {
                    modifiedChances.set(0, Math.max(modifiedChances.get(0) * catalyst.getPrimaryMod(), catalyst.getMinChance()));
                }
                for (int i = 1; i < modifiedChances.size(); i++) {
                    if (modifiedChances.get(i) >= 0.0F) {
                        modifiedChances.set(i, Math.max(modifiedChances.get(1) * catalyst.getSecondaryMod(), catalyst.getMinChance()));
                    }
                }
            }
        }

        for (int i = 0; i < modifiedChances.size(); ++i) {
            if (modifiedChances.get(i) < 0.0F) {
                modifiedChances.set(i, Math.abs(modifiedChances.get(i)));
            }
        }
        return modifiedChances;
    }

    @Override
    public List<Integer> getInputItemCounts(IThermalInventory inventory) {

        if (inputItems.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Integer> ret = new ArrayList<>();
        for (ItemStack input : inputItems) {
            ret.add(input.getCount());
        }
        // Catalyst Logic
        if (catalyzable && inventory.inputSlots().size() > catalystSlot) {
            ItemStack catalystStack = inventory.inputSlots().get(catalystSlot).getItemStack();
            IRecipeCatalyst catalyst = getCatalyst(catalystStack);
            if (catalyst != null && MathHelper.RANDOM.nextFloat() < catalyst.getUseChance()) {
                ret.add(1);
            }
        }
        return ret;
    }

    @Override
    public int getEnergy(IThermalInventory inventory) {

        // Catalyst Logic
        if (catalyzable && inventory.inputSlots().size() > catalystSlot) {
            ItemStack catalystStack = inventory.inputSlots().get(catalystSlot).getItemStack();
            IRecipeCatalyst catalyst = getCatalyst(catalystStack);
            return catalyst == null ? energy : (int) (energy * catalyst.getEnergyMod());
        }

        return energy;
    }

    @Override
    public float getExperience(IThermalInventory inventory) {

        return experience;
    }
    // endregion
}
