package cofh.test.item;

import cofh.core.item.FluidContainerItem;
import cofh.lib.capability.IAOEItem;
import cofh.lib.capability.IEnchantableItem;
import cofh.lib.fluid.FluidContainerItemWrapper;
import cofh.lib.fluid.IFluidContainerItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.capability.CapabilityAOE.AOE_ITEM_CAPABILITY;
import static cofh.lib.capability.CapabilityEnchantable.ENCHANTABLE_ITEM_CAPABILITY;
import static cofh.lib.util.constants.Constants.RGB_DURABILITY_WATER;
import static cofh.lib.util.constants.Tags.TAG_ACTIVE;
import static cofh.lib.util.helpers.FluidHelper.IS_WATER;
import static cofh.lib.util.references.CoreReferences.HOLDING;

public class WateringCanItem extends FluidContainerItem {

    public WateringCanItem(Properties properties, int fluidCapacity) {

        super(properties, fluidCapacity, IS_WATER);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (!isActive(stack)) {
            return;
        }
        long activeTime = stack.getTag().getLong(TAG_ACTIVE);
        if (entityIn.world.getGameTime() > activeTime) {
            clearActive(stack);
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {

        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) && (slotChanged || getFluidAmount(oldStack) > 0 != getFluidAmount(newStack) > 0 || getFluidAmount(newStack) > 0 && isActive(oldStack) != isActive(newStack));
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {

        return RGB_DURABILITY_WATER;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {

        return new WateringCanItemWrapper(stack, this);
    }

    // region CAPABILITIES
    private static class WateringCanItemWrapper extends FluidContainerItemWrapper implements IAOEItem, IEnchantableItem, ICapabilityProvider {

        private final LazyOptional<WateringCanItemWrapper> holder = LazyOptional.of(() -> this);

        public WateringCanItemWrapper(ItemStack containerIn, IFluidContainerItem itemIn) {

            super(containerIn, itemIn);
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {

            if (cap == AOE_ITEM_CAPABILITY) {
                return AOE_ITEM_CAPABILITY.orEmpty(cap, this.holder.cast());
            }
            if (cap == ENCHANTABLE_ITEM_CAPABILITY) {
                return ENCHANTABLE_ITEM_CAPABILITY.orEmpty(cap, this.holder.cast());
            }
            return super.getCapability(cap, side);
        }

        // region IAOEItem

        // endregion

        // region IEnchantableItem
        @Override
        public boolean supportsEnchantment(ItemStack stack, Enchantment ench) {

            return HOLDING != null && ench == HOLDING;
        }
        // endregion
    }
    // endregion

}
