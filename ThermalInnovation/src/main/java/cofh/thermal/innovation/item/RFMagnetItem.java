package cofh.thermal.innovation.item;

import cofh.lib.item.EnergyContainerItem;
import cofh.lib.item.IAugmentableItem;
import cofh.thermal.core.common.ThermalConfig;
import net.minecraft.item.ItemStack;

import java.util.function.IntSupplier;
import java.util.function.Predicate;

public class RFMagnetItem extends EnergyContainerItem implements IAugmentableItem {

    protected IntSupplier numSlots = () -> ThermalConfig.toolAugments;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    public RFMagnetItem(Properties builder, int maxEnergy, int maxTransfer) {

        super(builder, maxEnergy, maxTransfer);
    }

    public RFMagnetItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public RFMagnetItem setAugValidator(Predicate<ItemStack> augValidator) {

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
