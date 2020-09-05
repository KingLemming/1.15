package cofh.thermal.core.item;

import cofh.core.block.IDismantleable;
import cofh.core.block.IWrenchable;
import cofh.core.item.ItemCoFH;
import cofh.core.util.Utils;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class WrenchItem extends ItemCoFH {

    public WrenchItem(Properties builder) {

        super(builder);
    }

    protected boolean useDelegate(ItemStack stack, ItemUseContext context) {

        World world = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();

        if (player == null || world.isAirBlock(pos)) {
            return false;
        }
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (player.isSecondaryUseActive() && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(world, pos, state, player)) {
            if (Utils.isServerWorld(world)) {
                BlockRayTraceResult target = new BlockRayTraceResult(context.getHitVec(), context.getFace(), context.getPos(), context.isInside());
                ((IDismantleable) block).dismantleBlock(world, pos, state, target, player, false);
            }
            player.swingArm(context.getHand());
        } else if (!player.isSecondaryUseActive()) {

            if (block instanceof IWrenchable && ((IWrenchable) block).canWrench(world, pos, state, player)) {
                BlockRayTraceResult target = new BlockRayTraceResult(context.getHitVec(), context.getFace(), context.getPos(), context.isInside());
                ((IWrenchable) block).wrenchBlock(world, pos, state, target, player);
            } else if (state.isNormalCube(world, pos)) {
                BlockState rotState = block.rotate(state, world, pos, Rotation.CLOCKWISE_90);
                if (rotState != state) {
                    world.setBlockState(pos, rotState);
                }
            }
            return Utils.isServerWorld(world);
        }
        return true;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResultType.FAIL;
        }
        return player.canPlayerEdit(context.getPos(), context.getFace(), context.getItem()) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {

        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResultType.PASS;
        }
        return player.canPlayerEdit(context.getPos(), context.getFace(), stack) && useDelegate(stack, context) ? ActionResultType.SUCCESS : ActionResultType.PASS;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {

        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
        if (slot == EquipmentSlotType.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 0.0D, AttributeModifier.Operation.ADDITION));
            // multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double) this.attackSpeed, AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {

        return new ItemStack(this);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {

        return true;
    }

}
