package cofh.test.item;

import cofh.core.item.FluidContainerItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static cofh.lib.util.constants.Constants.RGB_DURABILITY_WATER;
import static cofh.lib.util.constants.Tags.TAG_ACTIVE;
import static cofh.lib.util.helpers.FluidHelper.IS_WATER;

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

}
