package cofh.thermal.innovation.item;

import cofh.core.item.EnergyContainerItem;
import cofh.core.item.IAugmentableItem;
import cofh.core.item.IMultiModeItem;
import cofh.core.util.ChatHelper;
import cofh.core.util.Utils;
import cofh.core.util.helpers.AugmentDataHelper;
import cofh.thermal.core.common.ThermalConfig;
import com.google.common.collect.Iterables;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.core.key.CoreKeys.MULTIMODE_INCREMENT;
import static cofh.core.util.constants.NBTTags.*;
import static cofh.core.util.helpers.AugmentableHelper.getAttributeFromAugmentMax;
import static cofh.core.util.helpers.AugmentableHelper.getPropertyWithDefault;
import static cofh.core.util.helpers.StringHelper.*;

public class RFCapacitorItem extends EnergyContainerItem implements IAugmentableItem, IMultiModeItem {

    protected static final int EQUIPMENT = 0;
    protected static final int INVENTORY = 1;

    protected IntSupplier numSlots = () -> ThermalConfig.storageAugments;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    public RFCapacitorItem(Properties builder, int maxEnergy, int maxTransfer) {

        super(builder, maxEnergy, maxTransfer);

        this.addPropertyOverride(new ResourceLocation("state"), (stack, world, entity) -> getMode(stack) / 6.0F + (isActive(stack) ? 0.5F : 0));
    }

    public RFCapacitorItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public RFCapacitorItem setAugValidator(Predicate<ItemStack> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(isActive(stack)
                ? new TranslationTextComponent("info.cofh_use_sneak_deactivate").applyTextStyle(TextFormatting.DARK_GRAY)
                : new TranslationTextComponent("info.cofh.use_sneak_activate").applyTextStyle(TextFormatting.DARK_GRAY));

        tooltip.add(getTextComponent("info.thermal.capacitor.mode." + getMode(stack)).applyTextStyle(TextFormatting.ITALIC));
        tooltip.add(new TranslationTextComponent("info.cofh.mode_change", InputMappings.getKeynameFromKeycode(MULTIMODE_INCREMENT.getKey().getKeyCode())).applyTextStyle(TextFormatting.YELLOW));

        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(getTextComponent(localize("info.cofh.transfer") + ": " + getScaledNumber(getExtract(stack)) + " RF/t"));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (Utils.isClientWorld(worldIn) || Utils.isFakePlayer(entityIn) || !isActive(stack)) {
            return;
        }
        Iterable<ItemStack> equipment;
        PlayerEntity player = (PlayerEntity) entityIn;

        switch (getMode(stack)) {
            case EQUIPMENT:
                equipment = player.getEquipmentAndArmor();
                break;
            case INVENTORY:
                equipment = player.inventory.mainInventory;
                break;
            default:
                equipment = Iterables.concat(Arrays.asList(player.inventory.mainInventory, player.inventory.armorInventory, player.inventory.offHandInventory));
        }
        for (ItemStack equip : equipment) {
            if (equip.equals(stack)) {
                continue;
            }
            int transfer = Math.min(this.getExtract(stack), this.getEnergyStored(stack));
            equip.getCapability(CapabilityEnergy.ENERGY, null)
                    .ifPresent(c -> this.extractEnergy(stack, c.receiveEnergy(transfer, false), false));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        ItemStack stack = playerIn.getHeldItem(handIn);
        return useDelegate(stack, playerIn) ? ActionResult.resultSuccess(stack) : ActionResult.resultFail(stack);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {

        return useDelegate(stack, context.getPlayer()) ? ActionResultType.SUCCESS : ActionResultType.PASS;
    }

    protected boolean useDelegate(ItemStack stack, PlayerEntity player) {

        if (Utils.isFakePlayer(player)) {
            return false;
        }
        if (player.isSecondaryUseActive()) {
            setActive(stack, !isActive(stack));
            player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.2F, isActive(stack) ? 0.8F : 0.5F);
        }
        return true;
    }

    protected boolean isActive(ItemStack stack) {

        return stack.getOrCreateTag().getBoolean(TAG_ACTIVE);
    }

    protected void setActive(ItemStack stack, boolean state) {

        stack.getOrCreateTag().putBoolean(TAG_ACTIVE, state);
    }

    protected void setAttributesFromAugment(ItemStack container, CompoundNBT augmentData) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_ENERGY_STORAGE);
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_ENERGY_XFER);
    }

    // region IEnergyContainerItem
    @Override
    public int getExtract(ItemStack container) {

        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_XFER, 1.0F);
        return Math.round(extract * mod * mod);
    }

    @Override
    public int getReceive(ItemStack container) {

        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_XFER, 1.0F);
        return Math.round(receive * mod * mod);
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {

        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_STORAGE, 1.0F);
        return Math.round(super.getMaxEnergyStored(container) * mod * mod);
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
        int energyExcess = getEnergyStored(container) - getMaxEnergyStored(container);
        if (energyExcess > 0) {
            setEnergyStored(container, getMaxEnergyStored(container));
        }
    }
    // endregion

    // region IMultiModeItem
    @Override
    public int getNumModes(ItemStack stack) {

        return 3;
    }

    @Override
    public void onModeChange(PlayerEntity player, ItemStack stack) {

        player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 0.4F, (isActive(stack) ? 0.7F : 0.5F) + 0.1F * getMode(stack));
        ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("info.thermal.capacitor.mode." + getMode(stack)));
    }
    // endregion
}
