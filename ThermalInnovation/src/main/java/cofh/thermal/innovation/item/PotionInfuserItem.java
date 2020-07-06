package cofh.thermal.innovation.item;

import cofh.core.util.ChatHelper;
import cofh.lib.item.FluidContainerItem;
import cofh.lib.item.IAugmentableItem;
import cofh.lib.item.IMultiModeItem;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.lib.util.helpers.FluidHelper;
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
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.core.key.CoreKeys.MULTIMODE_INCREMENT;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.AugmentableHelper.getAttributeFromAugmentMax;
import static cofh.lib.util.helpers.AugmentableHelper.getPropertyWithDefault;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;

public class PotionInfuserItem extends FluidContainerItem implements IAugmentableItem, IMultiModeItem {

    protected static final int TIME_CONSTANT = 32;

    protected static final int MB_PER_CYCLE = 50;
    protected static final int MB_PER_USE = 250;

    protected IntSupplier numSlots = () -> ThermalConfig.toolAugments;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    public PotionInfuserItem(Properties builder, int fluidCapacity) {

        this(builder, fluidCapacity, FluidHelper::hasPotionTag);
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

        tooltip.add(getTextComponent("info.thermal.infuser.use"));
        tooltip.add(getTextComponent("info.thermal.infuser.use.sneak"));

        tooltip.add(getTextComponent("info.thermal.infuser.mode." + getMode(stack)));
        tooltip.add(new TranslationTextComponent("info.cofh.mode_change", InputMappings.getKeynameFromKeycode(MULTIMODE_INCREMENT.getKey().getKeyCode())).applyTextStyle(TextFormatting.YELLOW));

        super.addInformation(stack, worldIn, tooltip, flagIn);
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
                    // TODO: Augment effects :)
                    EffectInstance potion = new EffectInstance(effect.getPotion(), effect.getDuration() / 4, effect.getAmplifier(), effect.isAmbient(), false);
                    living.addPotionEffect(potion);
                }
                used = true;
            }
            if (used) {
                drain(stack, MB_PER_CYCLE, IFluidHandler.FluidAction.EXECUTE);
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
                        // TODO: Augment effects :)
                        EffectInstance potion = new EffectInstance(effect.getPotion(), effect.getDuration() / 2, effect.getAmplifier(), effect.isAmbient(), true);
                        entity.addPotionEffect(potion);
                    }
                }
                drain(stack, MB_PER_USE, IFluidHandler.FluidAction.EXECUTE);
            }
            player.swingArm(hand);
            return true;
        }
        return false;
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

        if (Utils.isFakePlayer(player) || !player.isSecondaryUseActive()) {
            return false;
        }
        if (Utils.isServerWorld(player.world)) {
            FluidStack fluid = getFluid(stack);
            if (fluid != null && fluid.getAmount() >= MB_PER_USE) {
                for (EffectInstance effect : PotionUtils.getEffectsFromTag(fluid.getTag())) {
                    if (effect.getPotion().isInstant()) {
                        effect.getPotion().affectEntity(null, null, player, effect.getAmplifier(), 1.0D);
                    } else {
                        // TODO: Augment effects :)
                        EffectInstance potion = new EffectInstance(effect.getPotion(), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), false);
                        player.addPotionEffect(potion);
                    }
                }
                drain(stack, MB_PER_USE, IFluidHandler.FluidAction.EXECUTE);
            }
        }
        player.swingArm(hand);
        stack.setAnimationsToGo(5);
        return true;
    }

    protected void setAttributesFromAugment(ItemStack container, CompoundNBT augmentData) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_BASE_MOD);
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_FLUID_STORAGE);
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
