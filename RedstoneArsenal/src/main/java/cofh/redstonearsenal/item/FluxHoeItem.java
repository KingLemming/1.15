package cofh.redstonearsenal.item;

import cofh.core.energy.EnergyContainerItemWrapper;
import cofh.core.util.Utils;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.core.util.helpers.StringHelper.*;
import static cofh.core.util.references.CoreReferences.HOLDING;

public class FluxHoeItem extends HoeItem implements IFluxItem {

    protected final float damage;
    protected final float damageCharged;
    protected final float attackSpeed;

    protected final int maxEnergy;
    protected final int extract;
    protected final int receive;

    public FluxHoeItem(IItemTier tier, float attackSpeedIn, Properties builder, int energy, int xfer) {

        super(tier, attackSpeedIn, builder.maxDamage(0).setNoRepair());

        this.damage = 0.0F;
        this.damageCharged = damage + 2.0F;
        this.attackSpeed = attackSpeedIn;

        this.maxEnergy = energy;
        this.extract = xfer;
        this.receive = xfer;

        addPropertyOverride(new ResourceLocation("charged"), (stack, world, entity) -> getEnergyStored(stack) > 0 ? 1F : 0F);
        addPropertyOverride(new ResourceLocation("active"), (stack, world, entity) -> getEnergyStored(stack) > 0 && getMode(stack) > 0 ? 1F : 0F);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(isCreative(stack)
                ? getTextComponent("info.cofh.infinite_source")
                : getTextComponent(localize("info.cofh.energy") + ": " + getScaledNumber(getEnergyStored(stack)) + " / " + getScaledNumber(getMaxEnergyStored(stack)) + " RF"));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        ItemStack stack = context.getItem();
        if (!hasEnergy(stack)) {
            return ActionResultType.PASS;
        }
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
        if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
        if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
            BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
            if (blockstate != null) {
                PlayerEntity player = context.getPlayer();
                world.playSound(player, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isRemote) {
                    world.setBlockState(blockpos, blockstate, 11);
                    if (player != null) {
                        if (player.abilities.isCreativeMode || hasEnergy(stack)) {
                            useEnergy(stack, player.abilities.isCreativeMode);
                        }
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        PlayerEntity player = (PlayerEntity) attacker;
        if (!player.abilities.isCreativeMode && hasEnergy(stack)) {
            useEnergy(stack, false);
        }
        return true;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {

        Multimap<String, AttributeModifier> multimap = HashMultimap.create();

        if (slot == EquipmentSlotType.MAINHAND) {
            if (hasEnergy(stack)) {
                double wDamage = getMode(stack) > 0 ? damageCharged : damage;
                double wSpeed = getMode(stack) > 0 ? attackSpeed + 0.2F : attackSpeed;

                multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", wDamage, AttributeModifier.Operation.ADDITION));
                multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", wSpeed, AttributeModifier.Operation.ADDITION));
            } else {
                multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0F, AttributeModifier.Operation.ADDITION));
                multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
            }
        }
        return multimap;
    }
    // endregion

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
    // endregion
}
