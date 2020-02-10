package cofh.thermal.core.item;

import cofh.lib.block.IDismantleable;
import cofh.lib.item.ItemCoFH;
import cofh.lib.util.Utils;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class WrenchItem extends ItemCoFH {

    public WrenchItem(Properties builder) {

        super(builder);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

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

        if (player.isSneaking() && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(world, pos, state, player)) {
            if (Utils.isServerWorld(world)) {
                ((IDismantleable) block).dismantleBlock(world, pos, state, player, false);
            }
            player.swingArm(context.getHand());
        } else if (!player.isSneaking()) {
            BlockState rotState = block.rotate(state, world, pos, Rotation.CLOCKWISE_90);
            if (rotState != state) {
                world.setBlockState(pos, rotState);
            }
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
