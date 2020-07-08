package cofh.lib.event;

import cofh.lib.capability.templates.AreaEffectItemWrapper;
import cofh.lib.util.Utils;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.List;

import static cofh.lib.capability.CapabilityAreaEffect.AREA_EFFECT_ITEM_CAPABILITY;
import static cofh.lib.util.constants.Constants.ID_COFH_CORE;
import static cofh.lib.util.helpers.AreaEffectHelper.validAreaEffectMiningItem;
import static cofh.lib.util.references.EnsorcellationReferences.WEEDING;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.item.HoeItem.HOE_LOOKUP;

@Mod.EventBusSubscriber(modid = ID_COFH_CORE)
public class AreaEffectEvents {

    private static final HashSet<PlayerEntity> HARVESTING_PLAYERS = new HashSet<>();
    private static final HashSet<PlayerEntity> TILLING_PLAYERS = new HashSet<>();

    private AreaEffectEvents() {

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void handleBlockBreakEvent(BlockEvent.BreakEvent event) {

        PlayerEntity player = event.getPlayer();
        if (!(player instanceof ServerPlayerEntity) || Utils.isClientWorld(player.world)) {
            return;
        }
        if (HARVESTING_PLAYERS.contains(player)) {
            return;
        }
        HARVESTING_PLAYERS.add(player);
        ItemStack stack = player.getHeldItemMainhand();
        if (!validAreaEffectMiningItem(stack)) {
            return;
        }
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AREA_EFFECT_ITEM_CAPABILITY).orElse(new AreaEffectItemWrapper(stack)).getAreaEffectBlocks(event.getPos(), player);
        ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
        // TODO: Revisit if performance issues show. This is the most *proper* way to handle this, but is not particularly friendly.
        for (BlockPos pos : areaBlocks) {
            if (stack.isEmpty()) {
                break;
            }
            playerMP.interactionManager.tryHarvestBlock(pos);
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void handleBreakSpeedEvent(PlayerEvent.BreakSpeed event) {

        if (event.isCanceled()) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        if (!validAreaEffectMiningItem(stack)) {
            return;
        }
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AREA_EFFECT_ITEM_CAPABILITY).orElse(new AreaEffectItemWrapper(stack)).getAreaEffectBlocks(event.getPos(), player);

        float oldSpeed = event.getOriginalSpeed();
        float newSpeed = event.getNewSpeed();

        float curHardness = event.getState().getBlockHardness(player.world, event.getPos());
        if (curHardness <= 0) {
            return;
        }
        float maxHardness = getMaxHardness(player.world, areaBlocks, curHardness);

        if (maxHardness > curHardness) {
            event.setNewSpeed(event.getOriginalSpeed() * curHardness / maxHardness);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void handleUseHoeEvent(UseHoeEvent event) {

        if (event.isCanceled() || event.getResult() == Event.Result.ALLOW) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getContext().getItem();
        BlockPos target = event.getContext().getPos();
        World world = player.world;
        BlockState targetTilled = HOE_LOOKUP.get(world.getBlockState(target).getBlock());
        BlockPos up = target.up();
        boolean weeding = getEnchantmentLevel(WEEDING, stack) > 0;

        if (targetTilled == null || !world.isAirBlock(up) && !weeding) {
            return;
        }
        if (Utils.isClientWorld(world)) {
            player.swingArm(event.getContext().getHand());
            return;
        }
        if (TILLING_PLAYERS.contains(player)) {
            return;
        }
        TILLING_PLAYERS.add(player);
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AREA_EFFECT_ITEM_CAPABILITY).orElse(new AreaEffectItemWrapper(stack)).getAreaEffectBlocks(target, player);
        for (BlockPos pos : areaBlocks) {
            if (stack.isEmpty()) {
                break;
            }
            BlockState tilled = HOE_LOOKUP.get(world.getBlockState(pos).getBlock());
            if (tilled != null) {
                world.setBlockState(pos, tilled);
                if (weeding) {
                    up = pos.up();
                    if (!world.isAirBlock(up)) {
                        world.destroyBlock(up, !player.abilities.isCreativeMode);
                    }
                }
                stack.damageItem(1, player, (entity) -> {
                    entity.sendBreakAnimation(event.getContext().getHand());
                });
            }
        }
        world.setBlockState(target, targetTilled);
        if (weeding) {
            up = target.up();
            if (!world.isAirBlock(up)) {
                world.destroyBlock(up, !player.abilities.isCreativeMode);
            }
        }
        world.playSound(player, target, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        event.setResult(Event.Result.ALLOW);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleTickEndEvent(TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            HARVESTING_PLAYERS.clear();
            TILLING_PLAYERS.clear();
        }
    }

    // region HELPERS
    private static float getMaxHardness(IBlockReader world, List<BlockPos> areaBlocks, float curHardness) {

        float maxHardness = curHardness;
        float testHardness;

        for (BlockPos pos : areaBlocks) {
            testHardness = world.getBlockState(pos).getBlockHardness(world, pos);
            if (testHardness > maxHardness) {
                maxHardness = testHardness;
            }
        }
        return maxHardness;
    }
    // endregion
}
