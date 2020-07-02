package cofh.thermal.innovation.item;

import cofh.lib.item.FluidContainerItem;
import cofh.lib.item.IAugmentableItem;
import cofh.lib.util.helpers.FluidHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.IntSupplier;
import java.util.function.Predicate;

public class PotionQuiverItem extends FluidContainerItem implements IAugmentableItem {

    protected IntSupplier numSlots = () -> 1;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    public PotionQuiverItem(Properties builder, int fluidCapacity) {

        this(builder, fluidCapacity, FluidHelper::hasPotionTag);
    }

    public PotionQuiverItem(Properties builder, int fluidCapacity, Predicate<FluidStack> validator) {

        super(builder, fluidCapacity, validator);
    }

    public PotionQuiverItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public PotionQuiverItem setAugValidator(Predicate<ItemStack> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    // region IFluidContainerItem
    @Override
    public int getCapacity(ItemStack container) {

        return fluidCapacity;
    }
    // endregion

    // region IAugmentableItem
    @Override
    public int getAugmentSlots(ItemStack augmentable) {

        return numSlots.getAsInt();
    }

    @Override
    public boolean validAugment(ItemStack augmentable, ItemStack augment) {

        return augValidator.test(augment);
    }
    // endregion

}
