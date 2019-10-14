package cofh.core.event;

import cofh.core.init.ConfigCore;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.FEATHER_FALLING;

public class CommonEventsCore {

    private static final CommonEventsCore INSTANCE = new CommonEventsCore();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        registered = true;
    }

    private CommonEventsCore() {

    }

    @SubscribeEvent
    public void handleFarmlandTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {

        if (!ConfigCore.improvedFeatherFalling) {
            return;
        }
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            int encFeatherFalling = getMaxEnchantmentLevel(FEATHER_FALLING, (LivingEntity) entity);
            if (encFeatherFalling > 0) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void handleItemFishedEvent(ItemFishedEvent event) {

        if (!ConfigCore.enableFishingExhaustion) {
            return;
        }
        if (event.isCanceled()) {
            return;
        }
        PlayerEntity player = event.getHookEntity().angler;
        if (player == null || player instanceof FakePlayer) {
            return;
        }
        player.addExhaustion(ConfigCore.amountFishingExhaustion);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void handlePlayerPickupXpEvent(PlayerPickupXpEvent event) {

        if (event.isCanceled()) {
            return;
        }
        if (!ConfigCore.improvedMending) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        ExperienceOrbEntity orb = event.getOrb();

        player.xpCooldown = 2;
        player.onItemPickup(orb, 1);
        Map.Entry<EquipmentSlotType, ItemStack> entry = getMostDamagedItem(player);
        if (entry != null) {
            ItemStack itemstack = entry.getValue();
            if (!itemstack.isEmpty() && itemstack.isDamaged()) {
                int i = Math.min((int) (orb.xpValue * itemstack.getXpRepairRatio()), itemstack.getDamage());
                orb.xpValue -= durabilityToXp(i);
                itemstack.setDamage(itemstack.getDamage() - i);
            }
        }
        if (orb.xpValue > 0) {
            player.giveExperiencePoints(orb.xpValue);
        }
        orb.remove();
        event.setCanceled(true);
    }

    // region HELPERS
    private static Map.Entry<EquipmentSlotType, ItemStack> getMostDamagedItem(PlayerEntity player) {

        Map<EquipmentSlotType, ItemStack> map = Enchantments.MENDING.getEntityEquipment(player);
        Map.Entry<EquipmentSlotType, ItemStack> mostDamaged = null;
        if (map.isEmpty()) {
            return null;
        }
        double durability = 0.0D;

        for (Map.Entry<EquipmentSlotType, ItemStack> entry : map.entrySet()) {
            ItemStack stack = entry.getValue();
            if (calcDurabilityRatio(stack) > durability) {
                mostDamaged = entry;
                durability = calcDurabilityRatio(stack);
            }
        }
        return mostDamaged;
    }

    private static int durabilityToXp(int durability) {

        return durability / 2;
    }

    private static int xpToDurability(int xp) {

        return xp * 2;
    }

    private static double calcDurabilityRatio(ItemStack stack) {

        return (double) stack.getDamage() / stack.getMaxDamage();
    }
    // endregion
}
