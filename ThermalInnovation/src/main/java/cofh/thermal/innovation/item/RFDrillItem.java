package cofh.thermal.innovation.item;

import cofh.core.util.ChatHelper;
import cofh.lib.capability.CapabilityAreaEffect;
import cofh.lib.capability.IAreaEffect;
import cofh.lib.energy.EnergyContainerItemWrapper;
import cofh.lib.energy.IEnergyContainerItem;
import cofh.lib.item.EnergyContainerItem;
import cofh.lib.item.IAugmentableItem;
import cofh.lib.item.IMultiModeItem;
import cofh.lib.util.Utils;
import cofh.lib.util.constants.ToolTypes;
import cofh.lib.util.helpers.AreaEffectHelper;
import cofh.lib.util.helpers.AugmentDataHelper;
import cofh.thermal.core.common.ThermalConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.AugmentableHelper.*;

public class RFDrillItem extends EnergyContainerItem implements IAugmentableItem, IMultiModeItem {

    protected static final Set<ToolType> TOOL_TYPES = new HashSet<>();
    protected static final Set<Material> MATERIALS = new HashSet<>();

    public static final int ENERGY_PER_USE = 200;

    static {
        TOOL_TYPES.add(ToolTypes.DRILL);
        TOOL_TYPES.add(ToolType.PICKAXE);
        TOOL_TYPES.add(ToolType.SHOVEL);

        MATERIALS.add(Material.ANVIL);
        MATERIALS.add(Material.IRON);
        MATERIALS.add(Material.ROCK);
    }

    protected IntSupplier numSlots = () -> ThermalConfig.toolAugments;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    public RFDrillItem(Properties builder, int maxEnergy, int maxTransfer) {

        super(builder, maxEnergy, maxTransfer);

        this.addPropertyOverride(new ResourceLocation("charged"), (stack, world, entity) -> getEnergyStored(stack) > 0 ? 1F : 0F);
        this.addPropertyOverride(new ResourceLocation("active"), (stack, world, entity) -> getEnergyStored(stack) > 0 && hasActiveTag(stack) ? 1F : 0F);
    }

    public RFDrillItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public RFDrillItem setAugValidator(Predicate<ItemStack> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {

        // Yes, Shovels can harvest Snow and Snow Blocks. That's an intentional omission here. :)
        return TOOL_TYPES.contains(state.getHarvestTool()) ? getHarvestLevel(stack) >= state.getHarvestLevel() : MATERIALS.contains(state.getMaterial());
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {

        return MATERIALS.contains(state.getMaterial()) || getToolTypes(stack).stream().anyMatch(state::isToolEffective) ? getEfficiency(stack) : super.getDestroySpeed(stack, state);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {

        return getToolTypes(stack).contains(tool) ? getHarvestLevel(stack) : -1;
    }

    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {

        return TOOL_TYPES;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {

        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
        if (slot == EquipmentSlotType.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", getAttackDamage(stack), AttributeModifier.Operation.ADDITION));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", getAttackSpeed(stack), AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (attacker instanceof PlayerEntity && !((PlayerEntity) attacker).abilities.isCreativeMode) {
            extractEnergy(stack, getEnergyPerUse(stack) * 2, false);
        }
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {

        if (Utils.isServerWorld(worldIn) && state.getBlockHardness(worldIn, pos) != 0.0F) {
            if (entityLiving instanceof PlayerEntity && !((PlayerEntity) entityLiving).abilities.isCreativeMode) {
                extractEnergy(stack, getEnergyPerUse(stack), false);
            }
        }
        return true;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {

        setActive(stack, entity);
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (!hasActiveTag(stack)) {
            return;
        }
        long activeTime = stack.getOrCreateTag().getLong(TAG_ACTIVE);

        if (entityIn.world.getGameTime() > activeTime) {
            stack.getOrCreateTag().remove(TAG_ACTIVE);
        }
    }

    // region HELPERS
    protected void setActive(ItemStack stack, LivingEntity entity) {

        stack.getOrCreateTag().putLong(TAG_ACTIVE, entity.world.getGameTime() + 20);
    }

    protected void setAttributesFromAugment(ItemStack container, CompoundNBT augmentData) {

        CompoundNBT subTag = container.getChildTag(TAG_PROPERTIES);
        if (subTag == null) {
            return;
        }
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_BASE_MOD);
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_ENERGY_STORAGE);
        getAttributeFromAugmentMax(subTag, augmentData, TAG_AUGMENT_ENERGY_XFER);

        getAttributeFromAugmentAdd(subTag, augmentData, TAG_AUGMENT_RADIUS);
    }

    protected boolean hasActiveTag(ItemStack stack) {

        return stack.getOrCreateTag().contains(TAG_ACTIVE);
    }

    protected float getAttackDamage(ItemStack stack) {

        return 2.0F + getBaseMod(stack);
    }

    protected float getAttackSpeed(ItemStack stack) {

        return -2.4F + getBaseMod(stack) / 10;
    }

    protected float getBaseMod(ItemStack stack) {

        return getPropertyWithDefault(stack, TAG_AUGMENT_BASE_MOD, 1.0F);
    }

    protected float getEfficiency(ItemStack stack) {

        return getEnergyStored(stack) < getEnergyPerUse(stack) ? 1.0F : 6.0F + getBaseMod(stack);
    }

    protected int getEnergyPerUse(ItemStack stack) {

        return ENERGY_PER_USE;
    }

    protected int getHarvestLevel(ItemStack stack) {

        return getEnergyStored(stack) < getEnergyPerUse(stack) ? -1 : 2;
    }

    protected int getRadius(ItemStack stack) {

        return (int) getPropertyWithDefault(stack, TAG_AUGMENT_RADIUS, 0.0F);
    }
    // endregion

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {

        return new RFDrillItemWrapper(stack, this);
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
        if (getMode(container) >= getNumModes(container)) {
            setMode(container, getNumModes(container) - 1);
        }
    }
    // endregion

    // region IMultiModeItem
    @Override
    public int getNumModes(ItemStack stack) {

        return 1 + getRadius(stack);
    }

    @Override
    public void onModeChange(PlayerEntity player, ItemStack stack) {

        player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 0.4F, 1.0F - 0.1F * getMode(stack));
        ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("info.thermal.drill.mode." + getMode(stack)));
    }
    // endregion

    // region CAPABILITY WRAPPER
    protected class RFDrillItemWrapper extends EnergyContainerItemWrapper implements IAreaEffect {

        private final LazyOptional<IAreaEffect> holder = LazyOptional.of(() -> this);

        public RFDrillItemWrapper(ItemStack containerIn, IEnergyContainerItem itemIn) {

            super(containerIn, itemIn);
        }

        @Override
        public ImmutableList<BlockPos> getAreaEffectBlocks(BlockPos pos, PlayerEntity player) {

            return AreaEffectHelper.getAreaEffectBlocksRadius(container, pos, player, getMode(container));
        }

        // region ICapabilityProvider
        @Override
        @Nonnull
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

            if (cap == CapabilityAreaEffect.AREA_EFFECT_ITEM_CAPABILITY) {
                return CapabilityAreaEffect.AREA_EFFECT_ITEM_CAPABILITY.orEmpty(cap, holder);
            }
            return super.getCapability(cap, side);
        }
        // endregion
    }
    // endregion
}
