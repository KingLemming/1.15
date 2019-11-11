package cofh.core.item;

import cofh.lib.fluid.FluidEnchantableItemWrapper;
import cofh.lib.fluid.IFluidContainerItem;
import cofh.lib.item.IColorableItem;
import cofh.lib.item.ItemCoFH;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.FluidHelper;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static cofh.lib.util.constants.Tags.TAG_AMOUNT;
import static cofh.lib.util.constants.Tags.TAG_FLUID;
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

        if (fluid.isEmpty()) {
            tooltip.add(getTextComponent(localize("info.cofh.fluid") + ": " + localize("info.cofh.empty")));
        } else {
            //            String color = fluid.getFluid().getAttributes().getRarity(fluid).;
            //            tooltip.add(localize("info.cofh.fluid") + ": " + color + fluid.getFluid().getLocalizedName(fluid) + LIGHT_GRAY);
        }
        if (isCreative()) {
            tooltip.add(getTextComponent("info.cofh.infinite_source"));
        } else {
            tooltip.add(getTextComponent(localize("info.cofh.level") + ": " + format(fluid.getAmount()) + " / " + format(getCapacity(stack)) + " mB"));
        }
        if (FluidHelper.hasPotionTag(fluid)) {
            tooltip.add(getEmptyLine());
            tooltip.add(getTextComponent(localize("info.cofh.effects.") + ":"));
            addPotionTooltip(fluid, tooltip);
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {

        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) && (slotChanged || !areItemStacksEqualIgnoreTags(oldStack, newStack, TAG_FLUID));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {

        return !isCreative() && getFluidAmount(stack) > 0;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {

        return enchantability;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {

        return MathHelper.clamp(1.0D - ((double) getFluidAmount(stack) / (double) getCapacity(stack)), 0.0D, 1.0D);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {

        ArrayList<Enchantment> enchants = new ArrayList<>();
        if (HOLDING != null) {
            enchants.add(HOLDING);
        }
        return new FluidEnchantableItemWrapper(stack, this, enchants);
    }

    // region IFluidContainerItem
    @Override
    public boolean isFluidValid(ItemStack container, FluidStack resource) {

        return validator.test(resource);
    }

    @Override
    public int getCapacity(ItemStack container) {

        return Utils.getEnchantedCapacity(fluidCapacity, EnchantmentHelper.getEnchantmentLevel(HOLDING, container));
    }

    @Override
    public int fill(ItemStack container, FluidStack resource, IFluidHandler.FluidAction action) {

        if (container.getTag() == null) {
            container.setTag(new CompoundNBT());
        }
        if (resource.isEmpty() || !isFluidValid(container, resource)) {
            return 0;
        }
        int capacity = getCapacity(container);

        if (isCreative()) {
            if (action.execute()) {
                CompoundNBT fluidTag = resource.writeToNBT(new CompoundNBT());
                fluidTag.putInt(TAG_AMOUNT, capacity - FluidAttributes.BUCKET_VOLUME);
                container.getTag().put(TAG_FLUID, fluidTag);
            }
            return resource.getAmount();
        }
        if (action.simulate()) {
            if (!container.getTag().contains(TAG_FLUID)) {
                return Math.min(capacity, resource.getAmount());
            }
            FluidStack stack = FluidStack.loadFluidStackFromNBT(container.getTag().getCompound(TAG_FLUID));
            if (stack.isEmpty()) {
                return Math.min(capacity, resource.getAmount());
            }
            if (!stack.isFluidEqual(resource)) {
                return 0;
            }
            return Math.min(capacity - stack.getAmount(), resource.getAmount());
        }
        if (!container.getTag().contains(TAG_FLUID)) {
            CompoundNBT fluidTag = resource.writeToNBT(new CompoundNBT());
            if (capacity < resource.getAmount()) {
                fluidTag.putInt(TAG_AMOUNT, capacity);
                container.getTag().put(TAG_FLUID, fluidTag);
                return capacity;
            }
            fluidTag.putInt(TAG_AMOUNT, resource.getAmount());
            container.getTag().put(TAG_FLUID, fluidTag);
            return resource.getAmount();
        }
        CompoundNBT fluidTag = container.getTag().getCompound(TAG_FLUID);
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
        container.getTag().put(TAG_FLUID, stack.writeToNBT(fluidTag));
        return filled;
    }

    @Override
    public FluidStack drain(ItemStack container, int maxDrain, IFluidHandler.FluidAction action) {

        if (container.getTag() == null) {
            container.setTag(new CompoundNBT());
        }
        if (!container.getTag().contains(TAG_FLUID) || maxDrain == 0) {
            return FluidStack.EMPTY;
        }
        FluidStack stack = FluidStack.loadFluidStackFromNBT(container.getTag().getCompound(TAG_FLUID));
        if (stack.isEmpty()) {
            return FluidStack.EMPTY;
        }
        int drained = isCreative() ? maxDrain : Math.min(stack.getAmount(), maxDrain);
        if (action.execute() && !isCreative()) {
            if (maxDrain >= stack.getAmount()) {
                container.getTag().remove(TAG_FLUID);
                return stack;
            }
            CompoundNBT fluidTag = container.getTag().getCompound(TAG_FLUID);
            fluidTag.putInt(TAG_AMOUNT, fluidTag.getInt(TAG_AMOUNT) - drained);
            container.getTag().put(TAG_FLUID, fluidTag);
        }
        stack.setAmount(drained);
        return stack;
    }
    // endregion
}
