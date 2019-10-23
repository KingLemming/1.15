package cofh.core.item;

import cofh.lib.energy.EnergyContainerItemWrapper;
import cofh.lib.energy.IEnergyContainerItem;
import cofh.lib.item.IColorableItem;
import cofh.lib.item.ItemCoFH;
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
import static cofh.lib.util.constants.Tags.TAG_ENERGY;
import static cofh.lib.util.helpers.StringHelper.*;
import static cofh.lib.util.references.CoreReferences.HOLDING;

public abstract class EnergyContainerItem extends ItemCoFH implements IEnergyContainerItem, IColorableItem {

    protected int maxEnergy;
    protected int maxExtract;
    protected int maxReceive;


    public EnergyContainerItem(Properties properties, int maxEnergy, int maxExtract, int maxReceive) {

        super(properties);
        this.maxEnergy = maxEnergy;
        this.maxExtract = maxExtract;
        this.maxReceive = maxReceive;
    }

    public EnergyContainerItem(Properties properties, int maxEnergy, int maxTransfer) {

        this(properties, maxEnergy, maxTransfer, maxTransfer);
    }

    public EnergyContainerItem setEnchantability(int enchantability) {

        this.enchantability = enchantability;
        return this;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (isCreative()) {
            tooltip.add(getTextComponent("info.cofh.infinite_energy"));
        } else {
            tooltip.add(getTextComponent(localize("info.cofh.charge") + ": " + getScaledNumber(getEnergyStored(stack)) + " / " + getScaledNumber(getMaxEnergyStored(stack)) + " RF"));
        }
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

        return !isCreative() && getEnergyStored(stack) > 0;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {

        return enchantability;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {

        return RGB_DURABILITY_FLUX;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {

        if (stack.getTag() == null) {
            setDefaultTag(stack, 0);
        }
        return MathHelper.clamp(1.0D - ((double) stack.getTag().getInt(TAG_ENERGY) / (double) getMaxEnergyStored(stack)), 0.0D, 1.0D);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {

        return new EnergyContainerItemWrapper(stack, this, maxExtract > 0, maxReceive > 0);
    }

    // region IEnergyContainerItem
    @Override
    public int getMaxEnergyStored(ItemStack container) {

        return Utils.getEnchantedCapacity(maxEnergy, EnchantmentHelper.getEnchantmentLevel(HOLDING, container));
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

        if (container.getTag() == null) {
            setDefaultTag(container, 0);
        }
        int stored = Math.min(container.getTag().getInt(TAG_ENERGY), getMaxEnergyStored(container));
        int receive = Math.min(this.maxReceive, Math.min(getMaxEnergyStored(container) - stored, maxReceive));

        if (!simulate && !isCreative()) {
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
        if (isCreative()) {
            return maxExtract;
        }
        int stored = Math.min(container.getTag().getInt(TAG_ENERGY), getMaxEnergyStored(container));
        int extract = Math.min(this.maxExtract, Math.min(maxExtract, stored));

        if (!simulate) {
            stored -= extract;
            container.getTag().putInt(TAG_ENERGY, stored);
        }
        return extract;
    }
    // endregion
}
