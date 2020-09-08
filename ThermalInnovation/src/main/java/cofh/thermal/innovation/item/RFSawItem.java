package cofh.thermal.innovation.item;

import cofh.core.capability.CapabilityAreaEffect;
import cofh.core.capability.IAreaEffect;
import cofh.core.energy.EnergyContainerItemWrapper;
import cofh.core.energy.IEnergyContainerItem;
import cofh.core.item.EnergyContainerItem;
import cofh.core.item.IAugmentableItem;
import cofh.core.item.IMultiModeItem;
import cofh.core.util.ChatHelper;
import cofh.core.util.Utils;
import cofh.core.util.constants.ToolTypes;
import cofh.core.util.helpers.AreaEffectHelper;
import cofh.core.util.helpers.AugmentDataHelper;
import cofh.thermal.core.common.ThermalConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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

import static cofh.core.key.CoreKeys.MULTIMODE_INCREMENT;
import static cofh.core.util.constants.NBTTags.*;
import static cofh.core.util.helpers.AugmentableHelper.getAttributeFromAugmentMax;
import static cofh.core.util.helpers.AugmentableHelper.getPropertyWithDefault;

public class RFSawItem extends EnergyContainerItem implements IAugmentableItem, IMultiModeItem {

    protected static final Set<ToolType> TOOL_TYPES = new HashSet<>();
    protected static final Set<Material> MATERIALS = new HashSet<>();

    public static final int ENERGY_PER_USE = 200;

    static {
        TOOL_TYPES.add(ToolTypes.SAW);
        TOOL_TYPES.add(ToolType.AXE);

        MATERIALS.add(Material.WOOD);
        MATERIALS.add(Material.PLANTS);
        MATERIALS.add(Material.TALL_PLANTS);
        MATERIALS.add(Material.BAMBOO);
    }

    protected IntSupplier numSlots = () -> ThermalConfig.toolAugments;
    protected Predicate<ItemStack> augValidator = (e) -> true;

    public RFSawItem(Properties builder, int maxEnergy, int maxTransfer) {

        super(builder, maxEnergy, maxTransfer);

        this.addPropertyOverride(new ResourceLocation("charged"), (stack, world, entity) -> getEnergyStored(stack) > 0 ? 1F : 0F);
        this.addPropertyOverride(new ResourceLocation("active"), (stack, world, entity) -> getEnergyStored(stack) > 0 && hasActiveTag(stack) ? 1F : 0F);
    }

    public RFSawItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public RFSawItem setAugValidator(Predicate<ItemStack> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        int radius = getMode(stack) * 2 + 1;
        if (radius <= 1) {
            tooltip.add(new TranslationTextComponent("info.cofh.single_block").applyTextStyle(TextFormatting.ITALIC));
        } else {
            tooltip.add(new TranslationTextComponent("info.cofh.area").appendText(": " + radius + "x" + radius).applyTextStyle(TextFormatting.ITALIC));
        }
        if (getNumModes(stack) > 1) {
            tooltip.add(new TranslationTextComponent("info.cofh.mode_change", InputMappings.getKeynameFromKeycode(MULTIMODE_INCREMENT.getKey().getKeyCode())).applyTextStyle(TextFormatting.YELLOW));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {

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
    public ActionResultType onItemUse(ItemUseContext context) {

        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState blockstate = world.getBlockState(pos);
        Block block = AxeItem.BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
        if (block != null) {
            PlayerEntity player = context.getPlayer();
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (Utils.isServerWorld(world)) {
                world.setBlockState(pos, block.getDefaultState().with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)), 11);
                if (player != null && !player.abilities.isCreativeMode) {
                    extractEnergy(context.getItem(), getEnergyPerUse(context.getItem()), false);
                }
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
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
    }

    protected boolean hasActiveTag(ItemStack stack) {

        return stack.getOrCreateTag().contains(TAG_ACTIVE);
    }

    protected float getAttackDamage(ItemStack stack) {

        return 3.0F + getBaseMod(stack);
    }

    protected float getAttackSpeed(ItemStack stack) {

        return -2.1F + getBaseMod(stack) / 10;
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

        return getEnergyStored(stack) < getEnergyPerUse(stack) ? -1 : Math.max(2, (int) getBaseMod(stack));
    }

    protected int getRadius(ItemStack stack) {

        return (int) getPropertyWithDefault(stack, TAG_AUGMENT_AREA_RADIUS, 0.0F);
    }
    // endregion

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {

        return new RFSawItemWrapper(stack, this);
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

        if (getNumModes(stack) <= 1) {
            return;
        }
        player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 0.4F, 1.0F - 0.1F * getMode(stack));
        int radius = getMode(stack) * 2 + 1;
        if (radius <= 1) {
            ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("info.cofh.single_block"));
        } else {
            ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("info.cofh.area").appendText(": " + radius + "x" + radius));
        }
    }
    // endregion

    // region CAPABILITY WRAPPER
    protected class RFSawItemWrapper extends EnergyContainerItemWrapper implements IAreaEffect {

        private final LazyOptional<IAreaEffect> holder = LazyOptional.of(() -> this);

        public RFSawItemWrapper(ItemStack containerIn, IEnergyContainerItem itemIn) {

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
