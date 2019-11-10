package cofh.test.item;

import cofh.core.item.EnergyContainerItem;
import cofh.core.util.ChatHelper;
import cofh.lib.item.IMultiModeItem;
import cofh.lib.util.RayTracer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class MagnetItem extends EnergyContainerItem implements IMultiModeItem {

    protected static final int ENERGY_PER_ITEM = 25;
    protected static final int ENERGY_PER_USE = 250;
    protected static final int TIME_CONSTANT = 8;

    protected static boolean allowFakePlayers = false;

    protected int radius;

    public MagnetItem(Properties properties, int maxEnergy, int maxReceive, int radius) {

        super(properties, maxEnergy, 0, maxReceive);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        return ActionResultType.FAIL;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {


        BlockRayTraceResult traceResult = RayTracer.retrace(playerIn, RayTraceContext.FluidMode.NONE);
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (traceResult.getType() == RayTraceResult.Type.MISS) {
            return new ActionResult<>(ActionResultType.PASS, stack);
        }
        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }

    // region IMultiModeItem
    @Override
    public void onModeChange(PlayerEntity player, ItemStack stack) {

        player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.4F, 0.8F + 0.4F * getMode(stack));
        ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("info.thermal.rf_magnet.b." + getMode(stack)));
    }
    // endregion
}
