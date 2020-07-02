package cofh.thermal.innovation.item;

import cofh.lib.item.EnergyContainerItem;
import cofh.lib.item.IAugmentableItem;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.lib.util.helpers.AugmentableHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.lib.util.constants.NBTTags.*;

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

    // region AUGMENTATION
    protected void updateAugmentState(ItemStack container) {

        updateAugmentState(container, AugmentableHelper.getAugments(container));
    }

    protected void updateAugmentState(ItemStack container, List<ItemStack> augments) {

        container.getOrCreateTag().put(TAG_PROPERTIES, new CompoundNBT());
        for (ItemStack augment : augments) {
            CompoundNBT augmentData = AugmentDataHelper.getAugmentData(augment);
            if (augmentData == null) {
                continue;
            }
            setAttributesFromAugment(container, augmentData);
        }
    }

    protected void setAttributesFromAugment(ItemStack container, CompoundNBT augmentData) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        float energyStorageMod = Math.max(getAttributeMod(augmentData, TAG_AUGMENT_ENERGY_STORAGE), getAttributeMod(subTag, TAG_AUGMENT_ENERGY_STORAGE));
        float energyXferMod = Math.max(getAttributeMod(augmentData, TAG_AUGMENT_ENERGY_XFER), getAttributeMod(subTag, TAG_AUGMENT_ENERGY_XFER));

        subTag.putFloat(TAG_AUGMENT_ENERGY_STORAGE, energyStorageMod);
        subTag.putFloat(TAG_AUGMENT_ENERGY_XFER, energyXferMod);
    }

    protected float getAttributeMod(CompoundNBT augmentData, String key) {

        return augmentData.getFloat(key);
    }

    protected float getAttributeModWithDefault(CompoundNBT augmentData, String key, float defaultValue) {

        return augmentData.contains(key) ? augmentData.getFloat(key) : defaultValue;
    }

    protected float getProperty(ItemStack container, String key) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        return subTag == null ? 1.0F : getAttributeMod(subTag, key);
    }

    protected float getPropertyWithDefault(ItemStack container, String key, float defaultValue) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        return subTag == null ? 1.0F : getAttributeModWithDefault(subTag, key, defaultValue);
    }
    // endregion

    // region IEnergyContainerItem
    @Override
    public int getExtract(ItemStack container) {

        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_XFER, 1.0F);
        return Math.round(extract * mod * mod);
    }

    @Override
    public int getReceive(ItemStack container) {

        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_XFER, 1.0F);
        return Math.round(receive * mod * mod);
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {

        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_STORAGE, 1.0F);
        return Math.round(maxEnergy * mod * mod);
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

    @Override
    public void setAugments(ItemStack augmentable, List<ItemStack> augments) {

        AugmentableHelper.writeAugmentsToItem(augmentable, augments);
        updateAugmentState(augmentable, augments);
    }
    // endregion

}
