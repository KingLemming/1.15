package cofh.thermal.innovation.item;

import cofh.core.util.ChatHelper;
import cofh.lib.item.FluidContainerItem;
import cofh.lib.item.IAugmentableItem;
import cofh.lib.item.IMultiModeItem;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.lib.util.helpers.FluidHelper;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.ArcheryHelper.isSimpleArrow;
import static cofh.lib.util.helpers.ItemHelper.areItemStacksEqualIgnoreTags;
import static cofh.lib.util.references.CoreReferences.HOLDING;

public class PotionQuiverItem extends FluidContainerItem implements IAugmentableItem, IMultiModeItem {

    protected static final int MB_PER_ARROW = 50;

    protected IntSupplier numSlots = () -> 1;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    protected int arrowCapacity;

    public PotionQuiverItem(Properties builder, int fluidCapacity) {

        this(builder, fluidCapacity, FluidHelper::hasPotionTag);
    }

    public PotionQuiverItem(Properties builder, int fluidCapacity, Predicate<FluidStack> validator) {

        super(builder, fluidCapacity, validator);
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
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {

        return !oldStack.equals(newStack) && (slotChanged || !areItemStacksEqualIgnoreTags(oldStack, newStack, TAG_ARROWS, TAG_FLUID));
    }

    // region HELPERS
    public int getNumArrows(ItemStack stack) {

        return isCreative(stack) ? getMaxArrows(stack) : stack.getOrCreateTag().getInt(TAG_ARROWS);
    }

    public int getMaxArrows(ItemStack stack) {

        float mod = getPropertyWithDefault(stack, TAG_AUGMENT_BASE_MOD, 1.0F);
        int holding = EnchantmentHelper.getEnchantmentLevel(HOLDING, stack);
        return Math.round(Utils.getEnchantedCapacity(arrowCapacity, holding) * mod);
    }

    public int getScaledArrowsStored(ItemStack stack, int scale) {

        return MathHelper.round((double) getNumArrows(stack) * scale / getMaxArrows(stack));
    }

    public int addArrows(ItemStack stack, int maxArrows, boolean simulate) {

        int stored = getNumArrows(stack);
        int toAdd = Math.min(maxArrows, getMaxArrows(stack) - stored);

        if (!simulate && !isCreative(stack)) {
            stored += toAdd;
            stack.getOrCreateTag().putInt(TAG_ARROWS, stored);
        }
        return toAdd;
    }

    public int removeArrows(ItemStack stack, int maxArrows, boolean simulate) {

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

    public static ItemStack findArrows(PlayerEntity player) {

        ItemStack offHand = player.getHeldItemOffhand();
        ItemStack mainHand = player.getHeldItemMainhand();

        if (isSimpleArrow(offHand)) {
            return offHand;
        } else if (isSimpleArrow(mainHand)) {
            return mainHand;
        }
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (isSimpleArrow(stack)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static boolean addToPlayerInventory(PlayerEntity player, ItemStack stack) {

        if (stack.isEmpty() || player == null) {
            return false;
        }
        PlayerInventory inv = player.inventory;
        for (int i = 0; i < inv.mainInventory.size(); i++) {
            if (inv.mainInventory.get(i).isEmpty()) {
                inv.mainInventory.set(i, stack.copy());
                return true;
            }
        }
        return false;
    }
    // endregion

    // region AUGMENTATION
    protected void setAttributesFromAugment(ItemStack container, CompoundNBT augmentData) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_BASE_MOD);
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_FLUID_STORAGE);
    }

    protected void getAttributeFromAugmentMax(CompoundNBT subTag, CompoundNBT augmentData, String attribute) {

        float mod = Math.max(getAttributeMod(augmentData, attribute), getAttributeMod(subTag, attribute));
        if (mod > 0.0F) {
            subTag.putFloat(attribute, mod);
        }
    }

    protected void getAttributeFromAugmentAdd(CompoundNBT subTag, CompoundNBT augmentData, String attribute) {

        float mod = getAttributeMod(augmentData, attribute) + getAttributeMod(subTag, attribute);
        if (mod > 0.0F) {
            subTag.putFloat(attribute, mod);
        }
    }

    protected float getAttributeMod(CompoundNBT augmentData, String key) {

        return augmentData.getFloat(key);
    }

    protected float getAttributeModWithDefault(CompoundNBT augmentData, String key, float defaultValue) {

        return augmentData.contains(key) ? augmentData.getFloat(key) : defaultValue;
    }

    protected float getPropertyWithDefault(ItemStack container, String key, float defaultValue) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        return subTag == null ? defaultValue : getAttributeModWithDefault(subTag, key, defaultValue);
    }
    // endregion

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
    }
    // endregion

    // region IMultiModeItem
    @Override
    public void onModeChange(PlayerEntity player, ItemStack stack) {

        player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.4F, 0.6F + 0.2F * getMode(stack));
        ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("info.thermal.infuser.mode." + getMode(stack)));
    }
    // endregion
}
