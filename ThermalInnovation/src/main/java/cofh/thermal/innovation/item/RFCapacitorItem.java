package cofh.thermal.innovation.item;

import cofh.lib.item.EnergyContainerItem;
import cofh.lib.item.IAugmentableItem;
import net.minecraft.item.ItemStack;

import java.util.function.IntSupplier;
import java.util.function.Predicate;

public class RFCapacitorItem extends EnergyContainerItem implements IAugmentableItem {

    protected IntSupplier numSlots = () -> 1;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    public RFCapacitorItem(Properties builder, int maxEnergy, int maxTransfer) {

        super(builder, maxEnergy, maxTransfer);
    }

    public RFCapacitorItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public RFCapacitorItem setAugValidator(Predicate<ItemStack> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    // TODO: Finish w/ Augment Support

    // region IEnergyContainerItem
    @Override
    public int getExtract(ItemStack container) {

        return extract;
    }

    @Override
    public int getReceive(ItemStack container) {

        return receive;
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {

        return maxEnergy;
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
