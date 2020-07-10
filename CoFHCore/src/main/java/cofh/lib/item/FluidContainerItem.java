package cofh.lib.item;

import cofh.lib.fluid.FluidContainerItemWrapper;
import cofh.lib.fluid.IFluidContainerItem;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.FluidHelper;
import cofh.lib.util.helpers.MathHelper;
import cofh.lib.util.helpers.StringHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

import static cofh.lib.util.constants.NBTTags.TAG_AMOUNT;
import static cofh.lib.util.constants.NBTTags.TAG_FLUID;
import static cofh.lib.util.helpers.FluidHelper.addPotionTooltip;
import static cofh.lib.util.helpers.ItemHelper.areItemStacksEqualIgnoreTags;
import static cofh.lib.util.helpers.StringHelper.*;
import static cofh.lib.util.references.CoreReferences.HOLDING;

public class FluidContainerItem extends ItemCoFH implements IFluidContainerItem, IColorableItem {

    protected Predicate<FluidStack> validator;
    protected int fluidCapacity;

    public FluidContainerItem(Properties builder, int fluidCapacity, Predicate<FluidStack> validator) {

        super(builder);
        this.fluidCapacity = fluidCapacity;
        this.validator = validator;
    }

    public FluidContainerItem(Properties builder, int fluidCapacity) {

        this(builder, fluidCapacity, e -> true);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        FluidStack fluid = getFluid(stack);

        if (!fluid.isEmpty()) {
            tooltip.add(StringHelper.getFluidName(fluid));
        }
        tooltip.add(isCreative(stack)
                ? getTextComponent("info.cofh.infinite_source")
                : getTextComponent(localize("info.cofh.amount") + ": " + format(fluid.getAmount()) + " / " + format(getCapacity(stack)) + " mB"));

        if (FluidHelper.hasPotionTag(fluid)) {
            tooltip.add(getEmptyLine());
            tooltip.add(getTextComponent(localize("info.cofh.effects") + ":"));
            addPotionTooltip(fluid, tooltip);
        }
    }

    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {

        return !(newStack.getItem() == oldStack.getItem()) || !areItemStacksEqualIgnoreTags(oldStack, newStack, TAG_FLUID);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {

        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) && (slotChanged || !areItemStacksEqualIgnoreTags(oldStack, newStack, TAG_FLUID));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {

        return !isCreative(stack) && getFluidAmount(stack) > 0;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {

        return MathHelper.clamp(1.0D - getFluidAmount(stack) / (double) getCapacity(stack), 0.0D, 1.0D);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {

        return new FluidContainerItemWrapper(stack, this);
    }

    // region IFluidContainerItem
    @Override
    public boolean isFluidValid(ItemStack container, FluidStack resource) {

        return validator.test(resource);
    }

    @Override
    public int getCapacity(ItemStack container) {

        int holding = EnchantmentHelper.getEnchantmentLevel(HOLDING, container);
        return Utils.getEnchantedCapacity(fluidCapacity, holding);
    }

    @Override
    public int fill(ItemStack container, FluidStack resource, IFluidHandler.FluidAction action) {

        CompoundNBT containerTag = container.getOrCreateTag();
        if (resource.isEmpty() || !isFluidValid(container, resource)) {
            return 0;
        }
        int capacity = getCapacity(container);

        if (isCreative(container)) {
            if (action.execute()) {
                CompoundNBT fluidTag = resource.writeToNBT(new CompoundNBT());
                fluidTag.putInt(TAG_AMOUNT, capacity - FluidAttributes.BUCKET_VOLUME);
                containerTag.put(TAG_FLUID, fluidTag);
            }
            return resource.getAmount();
        }
        if (action.simulate()) {
            if (!containerTag.contains(TAG_FLUID)) {
                return Math.min(capacity, resource.getAmount());
            }
            FluidStack stack = FluidStack.loadFluidStackFromNBT(containerTag.getCompound(TAG_FLUID));
            if (stack.isEmpty()) {
                return Math.min(capacity, resource.getAmount());
            }
            if (!stack.isFluidEqual(resource)) {
                return 0;
            }
            return Math.min(capacity - stack.getAmount(), resource.getAmount());
        }
        if (!containerTag.contains(TAG_FLUID)) {
            CompoundNBT fluidTag = resource.writeToNBT(new CompoundNBT());
            if (capacity < resource.getAmount()) {
                fluidTag.putInt(TAG_AMOUNT, capacity);
                containerTag.put(TAG_FLUID, fluidTag);
                return capacity;
            }
            fluidTag.putInt(TAG_AMOUNT, resource.getAmount());
            containerTag.put(TAG_FLUID, fluidTag);
            return resource.getAmount();
        }
        CompoundNBT fluidTag = containerTag.getCompound(TAG_FLUID);
        FluidStack stack = FluidStack.loadFluidStackFromNBT(fluidTag);
        if (stack.isEmpty() || !stack.isFluidEqual(resource)) {
            return 0;
        }
        int filled = capacity - stack.getAmount();
        if (resource.getAmount() < filled) {
            stack.grow(resource.getAmount());
            filled = resource.getAmount();
        } else {
            stack.setAmount(capacity);
        }
        containerTag.put(TAG_FLUID, stack.writeToNBT(fluidTag));
        return filled;
    }

    @Override
    public FluidStack drain(ItemStack container, int maxDrain, IFluidHandler.FluidAction action) {

        CompoundNBT containerTag = container.getOrCreateTag();
        if (!containerTag.contains(TAG_FLUID) || maxDrain == 0) {
            return FluidStack.EMPTY;
        }
        FluidStack stack = FluidStack.loadFluidStackFromNBT(containerTag.getCompound(TAG_FLUID));
        if (stack.isEmpty()) {
            return FluidStack.EMPTY;
        }
        int drained = isCreative(container) ? maxDrain : Math.min(stack.getAmount(), maxDrain);
        if (action.execute() && !isCreative(container)) {
            if (maxDrain >= stack.getAmount()) {
                containerTag.remove(TAG_FLUID);
                return stack;
            }
            CompoundNBT fluidTag = containerTag.getCompound(TAG_FLUID);
            fluidTag.putInt(TAG_AMOUNT, fluidTag.getInt(TAG_AMOUNT) - drained);
            containerTag.put(TAG_FLUID, fluidTag);
        }
        stack.setAmount(drained);
        return stack;
    }
    // endregion

    // region IColorableItem
    @Override
    public int getColor(ItemStack stack, int tintIndex) {

        if (tintIndex != 1) {
            return 0xFFFFFF;
        }
        return getFluidAmount(stack) > 0 ? getFluid(stack).getFluid().getAttributes().getColor(getFluid(stack)) : 0xFFFFFF;
    }
    // endregion
}
