package cofh.thermal.innovation.item;

import cofh.core.util.ChatHelper;
import cofh.lib.item.EnergyContainerItem;
import cofh.lib.item.IAugmentableItem;
import cofh.lib.item.IMultiModeItem;
import cofh.lib.util.RayTracer;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.thermal.core.common.ThermalConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.core.key.CoreKeys.MULTIMODE_INCREMENT;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.AugmentableHelper.*;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.init.TCoreReferences.SOUND_MAGNET;

public class RFMagnetItem extends EnergyContainerItem implements IAugmentableItem, IMultiModeItem {

    protected static final int RADIUS = 4;
    protected static final int REACH = 64;

    protected static final int TIME_CONSTANT = 8;
    protected static final int PICKUP_DELAY = 32;

    protected static final int ENERGY_PER_ITEM = 25;
    protected static final int ENERGY_PER_USE = 250;

    protected IntSupplier numSlots = () -> ThermalConfig.toolAugments;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    public RFMagnetItem(Properties builder, int maxEnergy, int maxTransfer) {

        super(builder, maxEnergy, maxTransfer);
    }

    public RFMagnetItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public RFMagnetItem setAugValidator(Predicate<ItemStack> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(getTextComponent("info.thermal.magnet.use"));
        tooltip.add(getTextComponent("info.thermal.magnet.use.sneak"));

        tooltip.add(getTextComponent("info.thermal.magnet.mode." + getMode(stack)));
        tooltip.add(new TranslationTextComponent("info.cofh.mode_change", InputMappings.getKeynameFromKeycode(MULTIMODE_INCREMENT.getKey().getKeyCode())).applyTextStyle(TextFormatting.YELLOW));

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (worldIn.getGameTime() % TIME_CONSTANT != 0) {
            return;
        }
        if (Utils.isClientWorld(worldIn) || Utils.isFakePlayer(entityIn) || getMode(stack) <= 0) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entityIn;

        if (getEnergyStored(stack) < ENERGY_PER_ITEM && !player.abilities.isCreativeMode) {
            return;
        }
        int radius = getRadius(stack);
        int radSq = radius * radius;

        // TODO: Filter
        AxisAlignedBB area = new AxisAlignedBB(player.getPosition().add(-radius, -radius, -radius), player.getPosition().add(1 + radius, 1 + radius, 1 + radius));
        List<ItemEntity> items = worldIn.getEntitiesWithinAABB(ItemEntity.class, area, EntityPredicates.IS_ALIVE);
        //ItemFilterWrapper wrapper = new ItemFilterWrapper(stack, getFilterSize(stack));

        if (Utils.isClientWorld(worldIn)) {
            for (ItemEntity item : items) {
                if (item.cannotPickup() || item.getPersistentData().getBoolean(TAG_CONVEYOR_COMPAT)) {
                    continue;
                }
                if (item.getPositionVector().squareDistanceTo(player.getPositionVector()) <= radSq) { // && wrapper.getFilter().matches(item.getItem())) {
                    worldIn.addParticle(RedstoneParticleData.REDSTONE_DUST, item.getPosX(), item.getPosY(), item.getPosZ(), 0, 0, 0);
                }
            }
        } else {
            int itemCount = 0;
            for (ItemEntity item : items) {
                if (item.cannotPickup() || item.getPersistentData().getBoolean(TAG_CONVEYOR_COMPAT)) {
                    continue;
                }
                if (item.getThrowerId() == null || !item.getThrowerId().equals(player.getUniqueID()) || item.getAge() >= PICKUP_DELAY) {
                    if (item.getPositionVector().squareDistanceTo(player.getPositionVector()) <= radSq) { // && wrapper.getFilter().matches(item.getItem())) {
                        item.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
                        item.setPickupDelay(0);
                        ++itemCount;
                    }
                }
            }
            if (!player.abilities.isCreativeMode) {
                extractEnergy(stack, ENERGY_PER_ITEM * itemCount, false);
            }
        }
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

    // TODO: Filter
    protected boolean useDelegate(ItemStack stack, PlayerEntity player, Hand hand) {

        if (Utils.isFakePlayer(player)) {
            return false;
        }
        if (player.isSecondaryUseActive()) {
            // TODO: Filter GUI
            //            player.openGui(ThermalInnovation.instance, GuiHandler.MAGNET_FILTER_ID, world, 0, 0, 0);
        } else if (getEnergyStored(stack) >= ENERGY_PER_USE || player.abilities.isCreativeMode) {
            RayTraceResult traceResult = RayTracer.retrace(player, REACH);
            if (traceResult.getType() != RayTraceResult.Type.BLOCK) {
                return false;
            }
            int radius = getRadius(stack);
            int radSq = radius * radius;

            World world = player.getEntityWorld();
            BlockPos pos = ((BlockRayTraceResult) traceResult).getPos();

            AxisAlignedBB area = new AxisAlignedBB(pos.add(-radius, -radius, -radius), pos.add(1 + radius, 1 + radius, 1 + radius));
            List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class, area, EntityPredicates.IS_ALIVE);
            // ItemFilterWrapper wrapper = new ItemFilterWrapper(stack, getFilterSize(stack));

            if (Utils.isClientWorld(world)) {
                for (ItemEntity item : items) {
                    if (item.getPositionVector().squareDistanceTo(traceResult.getHitVec()) <= radSq) {// && wrapper.getFilter().matches(item.getItem())) {
                        world.addParticle(ParticleTypes.PORTAL, item.getPosX(), item.getPosY(), item.getPosZ(), 0, 0, 0);
                    }
                }
            } else {
                int itemCount = 0;
                for (ItemEntity item : items) {
                    if (item.getPositionVector().squareDistanceTo(traceResult.getHitVec()) <= radSq) { // && wrapper.getFilter().matches(item.getItem())) {
                        item.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
                        item.setPickupDelay(0);
                        itemCount++;
                    }
                }
                if (!player.abilities.isCreativeMode) {
                    extractEnergy(stack, ENERGY_PER_USE + ENERGY_PER_ITEM * itemCount, false);
                }
            }
            player.swingArm(hand);
            stack.setAnimationsToGo(5);
            player.world.playSound(null, player.getPosition(), SOUND_MAGNET, SoundCategory.PLAYERS, 0.4F, 1.0F);
        }
        return true;
    }

    protected int getRadius(ItemStack stack) {

        float mod = getPropertyWithDefault(stack, TAG_AUGMENT_RADIUS, 1.0F);
        return Math.round(RADIUS + mod);
    }

    protected void setAttributesFromAugment(ItemStack container, CompoundNBT augmentData) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        getAttributeFromAugmentAdd(subTag, augmentData, TAG_AUGMENT_RADIUS);

        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_BASE_MOD);
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_ENERGY_STORAGE);
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_ENERGY_XFER);
    }

    // region IEnergyContainerItem
    @Override
    public int getExtract(ItemStack container) {

        float base = getPropertyWithDefault(container, TAG_AUGMENT_BASE_MOD, 1.0F);
        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_XFER, 1.0F);
        return Math.round(extract * mod * base);
    }

    @Override
    public int getReceive(ItemStack container) {

        float base = getPropertyWithDefault(container, TAG_AUGMENT_BASE_MOD, 1.0F);
        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_XFER, 1.0F);
        return Math.round(receive * mod * base);
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {

        float base = getPropertyWithDefault(container, TAG_AUGMENT_BASE_MOD, 1.0F);
        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_STORAGE, 1.0F);
        return Math.round(super.getMaxEnergyStored(container) * mod * base);
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
            extractEnergy(container, energyExcess, false);
        }
    }
    // endregion

    // region IMultiModeItem
    @Override
    public void onModeChange(PlayerEntity player, ItemStack stack) {

        player.world.playSound(null, player.getPosition(), SOUND_MAGNET, SoundCategory.PLAYERS, 0.4F, 0.8F + 0.4F * getMode(stack));
        ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("info.thermal.magnet.mode." + getMode(stack)));
    }
    // endregion
}
