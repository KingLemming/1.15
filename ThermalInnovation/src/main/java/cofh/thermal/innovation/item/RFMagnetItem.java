package cofh.thermal.innovation.item;

import cofh.core.util.ChatHelper;
import cofh.lib.item.EnergyContainerItem;
import cofh.lib.item.IAugmentableItem;
import cofh.lib.item.IMultiModeItem;
import cofh.lib.util.RayTracer;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.lib.util.helpers.AugmentableHelper;
import cofh.thermal.core.common.ThermalConfig;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.core.init.TCoreReferences.SOUND_MAGNET;

public class RFMagnetItem extends EnergyContainerItem implements IAugmentableItem, IMultiModeItem {

    public static final int RADIUS = 4;
    public static final int REACH = 64;

    public static final int UPDATE_RATE = 8;
    public static final int GRACE_TICKS = 32;

    public static final int ENERGY_PER_ITEM = 25;
    public static final int ENERGY_PER_USE = 250;

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
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (worldIn.getGameTime() % UPDATE_RATE != 0) {
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
                if (item.getThrowerId() == null || !item.getThrowerId().equals(player.getUniqueID()) || item.getAge() >= GRACE_TICKS) {
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

    // region AUGMENTATION
    protected void updateAugmentState(ItemStack container, List<ItemStack> augments) {

        container.getOrCreateTag().put(TAG_PROPERTIES, new CompoundNBT());
        for (ItemStack augment : augments) {
            CompoundNBT augmentData = AugmentDataHelper.getAugmentData(augment);
            if (augmentData == null) {
                continue;
            }
            setAttributesFromAugment(container, augmentData);
        }
    }

    protected void setAttributesFromAugment(ItemStack container, CompoundNBT augmentData) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        float energyStorageMod = Math.max(getAttributeMod(augmentData, TAG_AUGMENT_ENERGY_STORAGE), getAttributeMod(subTag, TAG_AUGMENT_ENERGY_STORAGE));
        float energyXferMod = Math.max(getAttributeMod(augmentData, TAG_AUGMENT_ENERGY_XFER), getAttributeMod(subTag, TAG_AUGMENT_ENERGY_XFER));

        subTag.putFloat(TAG_AUGMENT_ENERGY_STORAGE, energyStorageMod);
        subTag.putFloat(TAG_AUGMENT_ENERGY_XFER, energyXferMod);
    }

    protected float getAttributeMod(CompoundNBT augmentData, String key) {

        return augmentData.getFloat(key);
    }

    protected float getAttributeModWithDefault(CompoundNBT augmentData, String key, float defaultValue) {

        return augmentData.contains(key) ? augmentData.getFloat(key) : defaultValue;
    }

    protected float getProperty(ItemStack container, String key) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        return subTag == null ? 1.0F : getAttributeMod(subTag, key);
    }

    protected float getPropertyWithDefault(ItemStack container, String key, float defaultValue) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        return subTag == null ? 1.0F : getAttributeModWithDefault(subTag, key, defaultValue);
    }
    // endregion

    // region IEnergyContainerItem
    @Override
    public int getExtract(ItemStack container) {

        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_XFER, 1.0F);
        return Math.round(extract * mod);
    }

    @Override
    public int getReceive(ItemStack container) {

        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_XFER, 1.0F);
        return Math.round(receive * mod);
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {

        float mod = getPropertyWithDefault(container, TAG_AUGMENT_ENERGY_STORAGE, 1.0F);
        return Math.round(super.getMaxEnergyStored(container) * mod);
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
    public void setAugments(ItemStack augmentable, List<ItemStack> augments) {

        AugmentableHelper.writeAugmentsToItem(augmentable, augments);
        updateAugmentState(augmentable, augments);
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
