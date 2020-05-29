package cofh.thermal.cultivation.item;

import cofh.core.item.FluidContainerItem;
import cofh.core.util.ChatHelper;
import cofh.lib.item.IMultiModeItem;
import cofh.lib.util.RayTracer;
import cofh.lib.util.Utils;
import net.minecraft.block.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.constants.Constants.RGB_DURABILITY_WATER;
import static cofh.lib.util.constants.NBTTags.TAG_ACTIVE;
import static cofh.lib.util.helpers.FluidHelper.IS_WATER;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;

public class WateringCanItem extends FluidContainerItem implements IMultiModeItem {

    protected static final int MB_PER_USE = 50;

    protected static boolean allowFakePlayers = false;
    protected static boolean removeSourceBlocks = true;

    protected int radius;
    protected int effectiveness;

    public WateringCanItem(Properties builder, int fluidCapacity, int radius, int effectiveness) {

        super(builder, fluidCapacity, IS_WATER);
        this.radius = radius;
        this.effectiveness = effectiveness;

        // TODO: Figure this out
        // this.addPropertyOverride(new ResourceLocation("water"), ((stack, world, entity) -> this.getFluidAmount(stack) > 0 ? isActive(stack) ? 1.0F : 0.5F : 0.0F));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (!isActive(stack)) {
            return;
        }
        long activeTime = stack.getTag().getLong(TAG_ACTIVE);
        if (entityIn.world.getGameTime() > activeTime) {
            clearActive(stack);
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {

        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) && (slotChanged || getFluidAmount(oldStack) > 0 != getFluidAmount(newStack) > 0 || getFluidAmount(newStack) > 0 && isActive(oldStack) != isActive(newStack));
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {

        return RGB_DURABILITY_WATER;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        World world = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();

        if (player == null || Utils.isFakePlayer(player) && !allowFakePlayers) {
            return ActionResultType.FAIL;
        }
        if (player.isSecondaryUseActive()) {
            BlockRayTraceResult traceResult = RayTracer.retrace(player, RayTraceContext.FluidMode.SOURCE_ONLY);
            if (traceResult.getType() != RayTraceResult.Type.MISS) {
                if (isWater(world.getBlockState(traceResult.getPos()))) {
                    return ActionResultType.PASS;
                }
            }
        }
        ItemStack stack = player.getHeldItem(context.getHand());
        BlockPos offsetPos = world.getBlockState(pos).isSolid() ? pos.offset(context.getFace()) : pos;

        if (getFluidAmount(stack) < MB_PER_USE) {
            return ActionResultType.FAIL;
        }
        setActive(stack, player);

        int radius = getRadius(stack);
        int x = offsetPos.getX();
        int y = offsetPos.getY();
        int z = offsetPos.getZ();

        for (int i = x - radius; i <= x + radius; ++i) {
            for (int k = z - radius; k <= z + radius; ++k) {
                Utils.spawnParticles(world, ParticleTypes.FALLING_WATER, i + world.rand.nextDouble(), y - 1 + world.rand.nextDouble(), k + world.rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
        Iterable<BlockPos> area = BlockPos.getAllInBoxMutable(offsetPos.add(-radius, -2, -radius), offsetPos.add(radius, 1, radius));
        for (BlockPos scan : area) {
            BlockState state = world.getBlockState(scan);
            if (state.getBlock() instanceof FarmlandBlock) {
                int moisture = state.get(FarmlandBlock.MOISTURE);
                if (moisture < 7) {
                    world.setBlockState(scan, state.with(FarmlandBlock.MOISTURE, 7));
                }
            }
        }
        if (Utils.isServerWorld(world)) {
            if (world.rand.nextInt(100) < Math.max(effectiveness - 5 * getMode(stack), 1)) {
                for (BlockPos scan : area) {
                    Block plant = world.getBlockState(scan).getBlock();
                    if (plant instanceof IGrowable || plant instanceof IPlantable || plant == Blocks.MYCELIUM || plant == Blocks.CHORUS_FLOWER) {
                        world.getPendingBlockTicks().scheduleTick(scan, plant, 0);
                    }
                }
            }
            if (!player.abilities.isCreativeMode) {
                drain(stack, MB_PER_USE * getRadius(stack) * 2, EXECUTE);
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        BlockRayTraceResult traceResult = RayTracer.retrace(playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (traceResult.getType() == RayTraceResult.Type.MISS) {
            return new ActionResult<>(ActionResultType.PASS, stack);
        }
        BlockPos tracePos = traceResult.getPos();

        if (!playerIn.isSecondaryUseActive() || !worldIn.isBlockModifiable(playerIn, tracePos) || Utils.isFakePlayer(playerIn) && !allowFakePlayers) {
            return new ActionResult<>(ActionResultType.FAIL, stack);
        }
        if (isWater(worldIn.getBlockState(tracePos)) && getSpace(stack) > 0) {
            if (removeSourceBlocks) {
                worldIn.setBlockState(tracePos, Blocks.AIR.getDefaultState(), 11);
            }
            fill(stack, new FluidStack(Fluids.WATER, FluidAttributes.BUCKET_VOLUME), EXECUTE);
            playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }
        return new ActionResult<>(ActionResultType.PASS, stack);
    }

    // region HELPERS
    protected boolean isWater(BlockState state) {

        return state.getBlock() == Blocks.WATER;
    }

    protected int getRadius(ItemStack stack) {

        return 1 + getMode(stack);
    }
    // endregion

    // region IMultiModeItem
    @Override
    public int getNumModes(ItemStack stack) {

        return radius;
    }

    @Override
    public void onModeChange(PlayerEntity player, ItemStack stack) {

        player.world.playSound(null, player.getPosition(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 0.6F, 1.0F - 0.1F * getMode(stack));
        int radius = getRadius(stack) * 2 + 1;
        ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("info.cofh.area").appendText(": " + radius + "x" + radius));
    }
    // endregion
}
