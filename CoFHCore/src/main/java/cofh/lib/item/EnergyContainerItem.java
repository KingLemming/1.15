package cofh.lib.item;

import cofh.lib.energy.EnergyContainerItemWrapper;
import cofh.lib.energy.IEnergyContainerItem;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.constants.Constants.RGB_DURABILITY_FLUX;
import static cofh.lib.util.constants.NBTTags.TAG_ENERGY;
import static cofh.lib.util.helpers.StringHelper.*;
import static cofh.lib.util.references.CoreReferences.HOLDING;

public class EnergyContainerItem extends ItemCoFH implements IEnergyContainerItem {

    protected int maxEnergy;
    protected int extract;
    protected int receive;

    public EnergyContainerItem(Properties builder, int maxEnergy, int extract, int receive) {

        super(builder);
        this.maxEnergy = maxEnergy;
        this.extract = extract;
        this.receive = receive;
    }

    public EnergyContainerItem(Properties builder, int maxEnergy, int maxTransfer) {

        this(builder, maxEnergy, maxTransfer, maxTransfer);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(isCreative(stack)
                ? getTextComponent("info.cofh.infinite_energy")
                : getTextComponent(localize("info.cofh.energy") + ": " + getScaledNumber(getEnergyStored(stack)) + " / " + getScaledNumber(getMaxEnergyStored(stack)) + " RF"));
    }

    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {

        return !(newStack.getItem() == oldStack.getItem()) || (getEnergyStored(oldStack) > 0 != getEnergyStored(newStack) > 0);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {

        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) && (slotChanged || getEnergyStored(oldStack) > 0 != getEnergyStored(newStack) > 0);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {

        return !isCreative(stack) && getEnergyStored(stack) > 0;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {

        return RGB_DURABILITY_FLUX;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {

        if (stack.getTag() == null) {
            return 0;
        }
        return MathHelper.clamp(1.0D - getEnergyStored(stack) / (double) getMaxEnergyStored(stack), 0.0D, 1.0D);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {

        return new EnergyContainerItemWrapper(stack, this);
    }

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

        int holding = EnchantmentHelper.getEnchantmentLevel(HOLDING, container);
        return Utils.getEnchantedCapacity(maxEnergy, holding);
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

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
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

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
}
