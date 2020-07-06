package cofh.thermal.innovation.item;

import cofh.core.util.ChatHelper;
import cofh.lib.capability.CapabilityArchery;
import cofh.lib.capability.IArcheryAmmoItem;
import cofh.lib.fluid.FluidContainerItemWrapper;
import cofh.lib.fluid.IFluidContainerItem;
import cofh.lib.item.FluidContainerItem;
import cofh.lib.item.IAugmentableItem;
import cofh.lib.item.IMultiModeItem;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.common.ThermalConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.core.key.CoreKeys.MULTIMODE_INCREMENT;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.ArcheryHelper.findArrows;
import static cofh.lib.util.helpers.AugmentableHelper.getAttributeFromAugmentMax;
import static cofh.lib.util.helpers.AugmentableHelper.getPropertyWithDefault;
import static cofh.lib.util.helpers.ItemHelper.areItemStacksEqualIgnoreTags;
import static cofh.lib.util.helpers.StringHelper.*;
import static cofh.lib.util.references.CoreReferences.HOLDING;

public class PotionQuiverItem extends FluidContainerItem implements IAugmentableItem, IMultiModeItem {

    protected static final int MB_PER_ARROW = 50;

    protected IntSupplier numSlots = () -> ThermalConfig.toolAugments;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    protected int arrowCapacity;

    public PotionQuiverItem(Properties builder, int fluidCapacity, int arrowCapacity) {

        this(builder, fluidCapacity, arrowCapacity, FluidHelper::hasPotionTag);
    }

    public PotionQuiverItem(Properties builder, int fluidCapacity, int arrowCapacity, Predicate<FluidStack> validator) {

        super(builder, fluidCapacity, validator);
        this.arrowCapacity = arrowCapacity;
    }

    public PotionQuiverItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public PotionQuiverItem setAugValidator(Predicate<ItemStack> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(getTextComponent("info.thermal.quiver.use"));
        tooltip.add(getTextComponent("info.thermal.quiver.use.sneak"));

        tooltip.add(getTextComponent("info.thermal.quiver.mode." + getMode(stack)));
        tooltip.add(new TranslationTextComponent("info.cofh.mode_change", InputMappings.getKeynameFromKeycode(MULTIMODE_INCREMENT.getKey().getKeyCode())).applyTextStyle(TextFormatting.YELLOW));

        tooltip.add(getTextComponent(localize("info.cofh.arrows") + ": " + (isCreative(stack)
                ? localize("info.cofh.infinite")
                : getStoredArrows(stack) + " / " + format(getMaxArrows(stack)))));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {

        return !oldStack.equals(newStack) && (slotChanged || !areItemStacksEqualIgnoreTags(oldStack, newStack, TAG_ARROWS, TAG_FLUID));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        ItemStack stack = playerIn.getHeldItem(handIn);
        return useDelegate(stack, playerIn, handIn) ? ActionResult.resultSuccess(stack) : ActionResult.resultPass(stack);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {

        return useDelegate(stack, context.getPlayer(), context.getHand()) ? ActionResultType.SUCCESS : ActionResultType.PASS;
    }

    protected boolean useDelegate(ItemStack stack, PlayerEntity player, Hand hand) {

        if (Utils.isFakePlayer(player)) {
            return false;
        }
        if (player.isSecondaryUseActive()) {
            ItemStack arrows = findArrows(player);
            if (!arrows.isEmpty() && arrows.getCount() < arrows.getMaxStackSize()) {
                arrows.grow(removeArrows(stack, arrows.getMaxStackSize() - arrows.getCount(), false));
            } else {
                arrows = new ItemStack(Items.ARROW, Math.min(getStoredArrows(stack), 64));
                if (Utils.addToPlayerInventory(player, arrows)) {
                    removeArrows(stack, arrows.getCount(), false);
                }
            }
        } else {
            ItemStack arrows = findArrows(player);
            arrows.shrink(addArrows(stack, arrows.getCount(), false));
        }
        stack.setAnimationsToGo(5);
        return true;
    }

    protected int getStoredArrows(ItemStack stack) {

        return isCreative(stack) ? getMaxArrows(stack) : stack.getOrCreateTag().getInt(TAG_ARROWS);
    }

    protected int getMaxArrows(ItemStack stack) {

        float base = getPropertyWithDefault(stack, TAG_AUGMENT_BASE_MOD, 1.0F);
        int holding = EnchantmentHelper.getEnchantmentLevel(HOLDING, stack);
        return Math.round(Utils.getEnchantedCapacity(arrowCapacity, holding) * base);
    }

    protected int addArrows(ItemStack stack, int maxArrows, boolean simulate) {

        int stored = getStoredArrows(stack);
        int toAdd = Math.min(maxArrows, getMaxArrows(stack) - stored);

        if (!simulate && !isCreative(stack)) {
            stored += toAdd;
            stack.getOrCreateTag().putInt(TAG_ARROWS, stored);
        }
        return toAdd;
    }

    protected int removeArrows(ItemStack stack, int maxArrows, boolean simulate) {

        if (isCreative(stack)) {
            return maxArrows;
        }
        int stored = Math.min(stack.getOrCreateTag().getInt(TAG_ARROWS), getMaxArrows(stack));
        int toRemove = Math.min(maxArrows, stored);
        if (!simulate) {
            stored -= toRemove;
            stack.getOrCreateTag().putInt(TAG_ARROWS, stored);
        }
        return toRemove;
    }

    protected void setAttributesFromAugment(ItemStack container, CompoundNBT augmentData) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_BASE_MOD);
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_FLUID_STORAGE);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {

        return new PotionQuiverItemWrapper(stack, this);
    }

    // region IFluidContainerItem
    @Override
    public int getCapacity(ItemStack container) {

        float base = getPropertyWithDefault(container, TAG_AUGMENT_BASE_MOD, 1.0F);
        float mod = getPropertyWithDefault(container, TAG_AUGMENT_FLUID_STORAGE, 1.0F);
        return Math.round(super.getCapacity(container) * mod * base);
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
    public void updateAugmentState(ItemStack container, List<ItemStack> augments) {

        container.getOrCreateTag().put(TAG_PROPERTIES, new CompoundNBT());
        for (ItemStack augment : augments) {
            CompoundNBT augmentData = AugmentDataHelper.getAugmentData(augment);
            if (augmentData == null) {
                continue;
            }
            setAttributesFromAugment(container, augmentData);
        }
        int fluidExcess = getFluidAmount(container) - getCapacity(container);
        if (fluidExcess > 0) {
            drain(container, fluidExcess, IFluidHandler.FluidAction.EXECUTE);
        }
        int arrowExcess = getStoredArrows(container) - getMaxArrows(container);
        if (arrowExcess > 0) {
            removeArrows(container, arrowExcess, false);
        }
    }
    // endregion

    // region IMultiModeItem
    @Override
    public void onModeChange(PlayerEntity player, ItemStack stack) {

        player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.4F, 0.6F + 0.2F * getMode(stack));
        ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("info.thermal.quiver.mode." + getMode(stack)));
    }
    // endregion

    // region CAPABILITY WRAPPER
    protected class PotionQuiverItemWrapper extends FluidContainerItemWrapper implements IArcheryAmmoItem {

        private final LazyOptional<IArcheryAmmoItem> holder = LazyOptional.of(() -> this);

        public PotionQuiverItemWrapper(ItemStack containerIn, IFluidContainerItem itemIn) {

            super(containerIn, itemIn);
        }

        @Override
        public void onArrowLoosed(PlayerEntity shooter) {

            if (shooter != null) {
                if (!shooter.abilities.isCreativeMode) {
                    removeArrows(container, 1, false);
                    drain(MB_PER_ARROW, getMode(container) == 1 ? FluidAction.EXECUTE : FluidAction.SIMULATE);
                }
            }
        }

        @Override
        public AbstractArrowEntity createArrowEntity(World world, PlayerEntity shooter) {

            FluidStack fluid = getFluid(container);
            ItemStack arrowStack;

            if (getMode(container) == 1 && fluid != null && fluid.getAmount() >= MB_PER_ARROW) {
                arrowStack = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionUtils.getPotionTypeFromNBT(fluid.getTag()));
                return ((TippedArrowItem) arrowStack.getItem()).createArrow(world, arrowStack, shooter);
            }
            arrowStack = new ItemStack(Items.ARROW);
            return ((ArrowItem) arrowStack.getItem()).createArrow(world, arrowStack, shooter);
        }

        @Override
        public boolean isEmpty(PlayerEntity shooter) {

            if (isCreative(container) || (shooter != null && shooter.abilities.isCreativeMode)) {
                return false;
            }
            return getStoredArrows(container) <= 0;
        }

        @Override
        public boolean isInfinite(ItemStack bow, PlayerEntity shooter) {

            return shooter != null && shooter.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bow) > 0;
        }

        // region ICapabilityProvider
        @Override
        @Nonnull
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

            if (cap == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) {
                return super.getCapability(cap, side);
            }
            return CapabilityArchery.AMMO_ITEM_CAPABILITY.orEmpty(cap, holder);
        }
        // endregion
    }
    // endregion
}
