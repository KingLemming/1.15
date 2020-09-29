package cofh.redstonearsenal.item;

import cofh.core.energy.EnergyContainerItemWrapper;
import cofh.core.energy.IEnergyContainerItem;
import cofh.core.item.ICoFHItem;
import cofh.core.util.Utils;
import cofh.core.util.helpers.MathHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

import java.util.List;

import static cofh.core.util.constants.Constants.RGB_DURABILITY_FLUX;
import static cofh.core.util.constants.NBTTags.TAG_ENERGY;
import static cofh.core.util.helpers.StringHelper.*;
import static cofh.core.util.helpers.StringHelper.getScaledNumber;
import static cofh.core.util.references.CoreReferences.HOLDING;

public class FluxSwordItem extends SwordItem implements ICoFHItem, IEnergyContainerItem {

    protected int maxEnergy = 320000;
    protected int extract = 4000;
    protected int receive = 4000;

    protected int energyPerUse = 200;
    protected int energyPerUseCharged = 800;

    protected int damage = 5;
    protected int damageCharged = 10;
    protected float attackSpeed = -2.4F;

    public FluxSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {

        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(isCreative(stack)
                ? getTextComponent("info.cofh.infinite_source")
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
