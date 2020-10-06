package cofh.redstonearsenal.item;

import cofh.core.energy.IEnergyContainerItem;
import cofh.core.item.ICoFHItem;
import cofh.core.item.IMultiModeItem;
import cofh.core.util.helpers.MathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

import static cofh.core.util.constants.Constants.RGB_DURABILITY_FLUX;
import static cofh.core.util.constants.NBTTags.TAG_ENERGY;

public interface IFluxItem extends ICoFHItem, IEnergyContainerItem, IMultiModeItem {

    int ENERGY_PER_USE = 200;
    int ENERGY_PER_USE_CHARGED = 800;

    default int getEnergyPerUse(ItemStack stack) {

        return getMode(stack) > 0 ? ENERGY_PER_USE_CHARGED : ENERGY_PER_USE;
    }

    default boolean hasEnergy(ItemStack stack) {

        return getEnergyStored(stack) >= getEnergyPerUse(stack);
    }

    default int useEnergy(ItemStack stack, boolean simulate) {

        int unbreakingLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack), 0, 10);
        if (MathHelper.RANDOM.nextInt(2 + unbreakingLevel) >= 2) {
            return 0;
        }
        return extractEnergy(stack, getEnergyPerUse(stack), simulate);
    }

    @Override
    default boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {

        return !(newStack.getItem() == oldStack.getItem()) || (getEnergyStored(oldStack) > 0 != getEnergyStored(newStack) > 0);
    }

    @Override
    default boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {

        return !oldStack.equals(newStack) && (slotChanged || getEnergyStored(oldStack) > 0 != getEnergyStored(newStack) > 0);
    }

    @Override
    default boolean showDurabilityBar(ItemStack stack) {

        return !isCreative(stack) && getEnergyStored(stack) > 0;
    }

    @Override
    default int getRGBDurabilityForDisplay(ItemStack stack) {

        return RGB_DURABILITY_FLUX;
    }

    @Override
    default double getDurabilityForDisplay(ItemStack stack) {

        if (stack.getTag() == null) {
            return 0;
        }
        return MathHelper.clamp(1.0D - getEnergyStored(stack) / (double) getMaxEnergyStored(stack), 0.0D, 1.0D);
    }

    // region IEnergyContainerItem
    @Override
    default int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

        if (container.getTag() == null) {
            setDefaultTag(container, 0);
        }
        if (isCreative(container)) {
            return 0;
        }
        int stored = Math.min(container.getTag().getInt(TAG_ENERGY), getMaxEnergyStored(container));
        int receive = Math.min(Math.min(maxReceive, getReceive(container)), getSpace(container));

        if (!simulate) {
            stored += receive;
            container.getTag().putInt(TAG_ENERGY, stored);
        }
        return receive;
    }

    @Override
    default int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

        if (container.getTag() == null) {
            setDefaultTag(container, 0);
        }
        if (isCreative(container)) {
            return maxExtract;
        }
        int stored = Math.min(container.getTag().getInt(TAG_ENERGY), getMaxEnergyStored(container));
        int extract = Math.min(Math.min(maxExtract, getExtract(container)), stored);

        if (!simulate) {
            stored -= extract;
            container.getTag().putInt(TAG_ENERGY, stored);
        }
        return extract;
    }
    // endregion

    // region IMultiModeItem
    @Override
    default void onModeChange(PlayerEntity player, ItemStack stack) {

        if (getMode(stack) > 0) {
            player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.PLAYERS, 0.4F, 1.0F);
        } else {
            player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.2F, 0.6F);
        }
    }
    // endregion
}
