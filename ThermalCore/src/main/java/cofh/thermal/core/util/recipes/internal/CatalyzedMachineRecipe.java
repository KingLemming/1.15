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

    protected int catalystSlot;
    protected boolean catalyzable = true;

    protected CatalyzedMachineRecipe(int catalystSlot, int energy, float experience, @Nullable List<ItemStack> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> chance, @Nullable List<FluidStack> outputFluids) {

        super(energy, experience, inputItems, inputFluids, outputItems, chance, outputFluids);
        this.catalystSlot = catalystSlot;
        // If all of the output chances are locked, then the recipe is not catalyzable.
        for (float f : outputItemChances) {
            catalyzable &= f >= 0.0F;
        }
    }

    public CatalyzedMachineRecipe(int energy, float experience, @Nullable List<ItemStack> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> chance, @Nullable List<FluidStack> outputFluids) {

        this(1, energy, experience, inputItems, inputFluids, outputItems, chance, outputFluids);
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
            IRecipeCatalyst catalyst = getCatalyst(inventory.inputSlots().get(catalystSlot).getItemStack());
            if (catalyst == null) {
                return super.getOutputItemChances(inventory);
            }
            for (int i = 0; i < modifiedChances.size(); ++i) {
                if (modifiedChances.get(i) < 0.0F) {
                    modifiedChances.set(i, Math.abs(modifiedChances.get(i)));
                } else {
                    modifiedChances.set(i, Math.max(modifiedChances.get(i) * (i == 0 ? catalyst.getPrimaryMod() * inventory.getPrimaryMod() : catalyst.getSecondaryMod() * inventory.getSecondaryMod()), Math.max(catalyst.getMinOutputChance(), inventory.getMinOutputChance())));
                }
            }
            return modifiedChances;
        }
        return super.getOutputItemChances(inventory);
    }

    @Override
    public List<Integer> getInputItemCounts(IThermalInventory inventory) {

        if (inputItems.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<Integer> ret = new ArrayList<>(inputItems.size());
        for (ItemStack input : inputItems) {
            ret.add(input.getCount());
        }
        // Catalyst Logic
        if (catalyzable && inventory.inputSlots().size() > catalystSlot) {
            IRecipeCatalyst catalyst = getCatalyst(inventory.inputSlots().get(catalystSlot).getItemStack());
            if (catalyst != null && MathHelper.RANDOM.nextFloat() < catalyst.getUseChance() * inventory.getUseChance()) {
                ret.add(1);
            }
        }
        return ret;
    }

    @Override
    public int getEnergy(IThermalInventory inventory) {

        // Catalyst Logic
        if (catalyzable && inventory.inputSlots().size() > catalystSlot) {
            IRecipeCatalyst catalyst = getCatalyst(inventory.inputSlots().get(catalystSlot).getItemStack());
            return catalyst == null ? super.getEnergy(inventory) : Math.abs(Math.round(energy * catalyst.getEnergyMod() * inventory.getEnergyMod()));
        }
        return super.getEnergy(inventory);
    }

    @Override
    public float getExperience(IThermalInventory inventory) {

        if (catalyzable && inventory.inputSlots().size() > catalystSlot) {
            IRecipeCatalyst catalyst = getCatalyst(inventory.inputSlots().get(catalystSlot).getItemStack());
            return catalyst == null ? super.getExperience(inventory) : Math.round(energy * catalyst.getExperienceMod() * inventory.getExperienceMod());
        }
        return super.getExperience(inventory);
    }
    // endregion
}
