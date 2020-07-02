package cofh.thermal.core.item;

import cofh.lib.item.IPlacementItem;
import cofh.lib.item.ItemCoFH;
import cofh.lib.util.IConveyableData;
import cofh.lib.util.Utils;
import cofh.lib.util.control.ISecurable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class RedprintItem extends ItemCoFH implements IPlacementItem {

    public RedprintItem(Properties builder) {

        super(builder);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (stack.getTag() != null) {
            tooltip.add(new StringTextComponent(stack.getTag().toString()));
        }
    }

    @Override
    public Rarity getRarity(ItemStack stack) {

        return stack.hasTag() ? Rarity.UNCOMMON : Rarity.COMMON;
    }

    protected boolean useDelegate(ItemStack stack, ItemUseContext context) {

        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();

        if (player == null || Utils.isClientWorld(world)) {
            return false;
        }
        if (player.isSecondaryUseActive() && context.getHand() == Hand.MAIN_HAND) {
            if (stack.getTag() != null) {
                player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.3F);
                stack.setTag(null);
            }
            return true;
        }
        BlockPos pos = context.getPos();
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof ISecurable && !((ISecurable) tile).canAccess(player)) {
            return false;
        }
        if (tile instanceof IConveyableData) {
            IConveyableData portable = (IConveyableData) tile;
            if (stack.getTag() == null && context.getHand() == Hand.MAIN_HAND) {
                portable.writeConveyableData(player, stack.getOrCreateTag());
                tile.markDirty();
                if (stack.getTag().isEmpty()) {
                    stack.setTag(null);
                    return false;
                } else {
                    player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 0.7F);
                }
            } else {
                portable.readConveyableData(player, stack.getTag());
                player.world.playSound(null, player.getPosition(), SoundEvents.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 0.5F, 0.8F);
            }
            return true;
        }
        return false;
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
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        ItemStack stack = player.getHeldItem(hand);
        if (player.isSecondaryUseActive()) {
            if (stack.getTag() != null) {
                player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 0.3F);
            }
            stack.setTag(null);
        }
        player.swingArm(hand);
        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }

    // region IPlacementItem
    @Override
    public boolean onBlockPlacement(ItemStack stack, ItemUseContext context) {

        return useDelegate(stack, context);
    }
    // endregion

}
