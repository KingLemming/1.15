package cofh.thermal.innovation.item;

import cofh.core.item.FluidContainerItem;
import cofh.core.item.IAugmentableItem;
import cofh.core.item.IMultiModeItem;
import cofh.core.util.ChatHelper;
import cofh.core.util.ProxyUtils;
import cofh.core.util.Utils;
import cofh.core.util.helpers.AugmentDataHelper;
import cofh.core.util.helpers.FluidHelper;
import cofh.thermal.core.common.ThermalConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.core.key.CoreKeys.MULTIMODE_INCREMENT;
import static cofh.core.util.constants.Constants.MAX_POTION_AMPLIFIER;
import static cofh.core.util.constants.Constants.MAX_POTION_DURATION;
import static cofh.core.util.constants.NBTTags.*;
import static cofh.core.util.helpers.AugmentableHelper.*;
import static cofh.core.util.helpers.StringHelper.getTextComponent;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;

public class PotionInfuserItem extends FluidContainerItem implements IAugmentableItem, IMultiModeItem {

    protected static final int TIME_CONSTANT = 32;

    protected static final int MB_PER_CYCLE = 50;
    protected static final int MB_PER_USE = 250;

    protected IntSupplier numSlots = () -> ThermalConfig.toolAugments;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    public PotionInfuserItem(Properties builder, int fluidCapacity) {

        this(builder, fluidCapacity, FluidHelper::hasPotionTag);

        this.addPropertyOverride(new ResourceLocation("filled"), (stack, world, entity) -> getFluidAmount(stack) > 0 ? 1F : 0F);
        this.addPropertyOverride(new ResourceLocation("active"), (stack, world, entity) -> getFluidAmount(stack) > 0 && getMode(stack) > 0 ? 1F : 0F);

        ProxyUtils.registerColorable(this);
    }

    public PotionInfuserItem(Properties builder, int fluidCapacity, Predicate<FluidStack> validator) {

        super(builder, fluidCapacity, validator);
    }

    public PotionInfuserItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public PotionInfuserItem setAugValidator(Predicate<ItemStack> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {


        tooltip.add(getTextComponent("info.thermal.infuser.use").applyTextStyle(TextFormatting.GRAY));
        tooltip.add(getTextComponent("info.thermal.infuser.use.sneak").applyTextStyle(TextFormatting.DARK_GRAY));

        tooltip.add(getTextComponent("info.thermal.infuser.mode." + getMode(stack)).applyTextStyle(TextFormatting.ITALIC));
        tooltip.add(new TranslationTextComponent("info.cofh.mode_change", InputMappings.getKeynameFromKeycode(MULTIMODE_INCREMENT.getKey().getKeyCode())).applyTextStyle(TextFormatting.YELLOW));

        FluidStack fluid = getFluid(stack);
        List<EffectInstance> effects = new ArrayList<>();
        for (EffectInstance effect : PotionUtils.getEffectsFromTag(fluid.getTag())) {
            effects.add(new EffectInstance(effect.getPotion(), getEffectDuration(effect, stack), getEffectAmplifier(effect, stack), effect.isAmbient(), effect.doesShowParticles()));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn, effects);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (worldIn.getGameTime() % TIME_CONSTANT != 0) {
            return;
        }
        if (Utils.isClientWorld(worldIn)) {
            return;
        }
        if (!(entityIn instanceof LivingEntity) || Utils.isFakePlayer(entityIn) || getMode(stack) <= 0) {
            return;
        }
        LivingEntity living = (LivingEntity) entityIn;
        FluidStack fluid = getFluid(stack);
        if (fluid != null && fluid.getAmount() >= MB_PER_CYCLE) {
            boolean used = false;
            for (EffectInstance effect : PotionUtils.getEffectsFromTag(fluid.getTag())) {
                EffectInstance active = living.getActivePotionMap().get(effect.getPotion());

                if (active != null && active.getDuration() >= 40) {
                    continue;
                }
                if (effect.getPotion().isInstant()) {
                    effect.getPotion().affectEntity(null, null, (LivingEntity) entityIn, effect.getAmplifier(), 0.5D);
                } else {
                    EffectInstance potion = new EffectInstance(effect.getPotion(), getEffectDuration(effect, stack) / 4, getEffectAmplifier(effect, stack), effect.isAmbient(), false);
                    living.addPotionEffect(potion);
                }
                used = true;
            }
            if (entityIn instanceof PlayerEntity && ((PlayerEntity) entityIn).abilities.isCreativeMode) {
                return;
            }
            if (used) {
                drain(stack, MB_PER_CYCLE, EXECUTE);
            }
        }
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {

        FluidStack fluid = getFluid(stack);
        if (fluid != null && fluid.getAmount() >= MB_PER_USE) {
            if (Utils.isServerWorld(entity.world)) {
                for (EffectInstance effect : PotionUtils.getEffectsFromTag(fluid.getTag())) {
                    if (effect.getPotion().isInstant()) {
                        effect.getPotion().affectEntity(player, player, entity, effect.getAmplifier(), 0.5D);
                    } else {
                        EffectInstance potion = new EffectInstance(effect.getPotion(), getEffectDuration(effect, stack) / 2, getEffectAmplifier(effect, stack), effect.isAmbient(), true);
                        entity.addPotionEffect(potion);
                    }
                }
                if (!player.abilities.isCreativeMode) {
                    drain(stack, MB_PER_USE, EXECUTE);
                }
            }
            player.swingArm(hand);
            return true;
        }
        return false;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {

        if (getFluidAmount(stack) <= 0) {
            return super.getRGBDurabilityForDisplay(stack);
        }
        return getFluid(stack).getFluid().getAttributes().getColor(getFluid(stack));
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

    // region HELPERS
    protected void setAttributesFromAugment(ItemStack container, CompoundNBT augmentData) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        getAttributeFromAugmentAdd(subTag, augmentData, TAG_AUGMENT_POTION_AMPLIFIER);
        getAttributeFromAugmentAdd(subTag, augmentData, TAG_AUGMENT_POTION_DURATION);

        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_BASE_MOD);
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_FLUID_STORAGE);
    }

    protected boolean useDelegate(ItemStack stack, PlayerEntity player, Hand hand) {

        if (Utils.isFakePlayer(player) || !player.isSecondaryUseActive()) {
            return false;
        }
        if (Utils.isServerWorld(player.world)) {
            FluidStack fluid = getFluid(stack);
            if (fluid != null && (fluid.getAmount() >= MB_PER_USE || player.abilities.isCreativeMode)) {
                for (EffectInstance effect : PotionUtils.getEffectsFromTag(fluid.getTag())) {
                    if (effect.getPotion().isInstant()) {
                        effect.getPotion().affectEntity(null, null, player, getEffectAmplifier(effect, stack), 1.0D);
                    } else {
                        EffectInstance potion = new EffectInstance(effect.getPotion(), getEffectDuration(effect, stack), getEffectAmplifier(effect, stack), effect.isAmbient(), false);
                        player.addPotionEffect(potion);
                    }
                }
                if (!player.abilities.isCreativeMode) {
                    drain(stack, MB_PER_USE, EXECUTE);
                }
            }
        }
        player.swingArm(hand);
        stack.setAnimationsToGo(5);
        return true;
    }

    protected int getEffectAmplifier(EffectInstance effect, ItemStack stack) {

        return Math.min(MAX_POTION_AMPLIFIER, Math.round(effect.getAmplifier() + getAmplifierMod(stack)));
    }

    protected int getEffectDuration(EffectInstance effect, ItemStack stack) {

        return Math.min(MAX_POTION_DURATION, Math.round(effect.getDuration() * getDurationMod(stack)));
    }

    protected float getAmplifierMod(ItemStack stack) {

        return getPropertyWithDefault(stack, TAG_AUGMENT_POTION_AMPLIFIER, 0.0F);
    }

    protected float getDurationMod(ItemStack stack) {

        return 1.0F + getPropertyWithDefault(stack, TAG_AUGMENT_POTION_DURATION, 0.0F);
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
        int fluidExcess = getFluidAmount(container) - getCapacity(container);
        if (fluidExcess > 0) {
            drain(container, fluidExcess, EXECUTE);
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
