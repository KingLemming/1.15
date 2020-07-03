package cofh.ensorcellation.event;

import cofh.ensorcellation.enchantment.SoulboundEnchantment;
import cofh.lib.util.Utils;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;

import static cofh.lib.util.Utils.*;
import static cofh.lib.util.constants.Constants.ID_ENSORCELLATION;
import static cofh.lib.util.references.EnsorcellationReferences.SOULBOUND;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.world.GameRules.KEEP_INVENTORY;

@Mod.EventBusSubscriber(modid = ID_ENSORCELLATION)
public class SoulboundEvents {

    private SoulboundEvents() {

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handlePlayerDropsEvent(LivingDropsEvent event) {

        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            if (Utils.isFakePlayer(player) || player.world.getGameRules().getBoolean(KEEP_INVENTORY)) {
                return;
            }
            Iterator<ItemEntity> iter = event.getDrops().iterator();
            while (iter.hasNext()) {
                ItemStack stack = iter.next().getItem();
                if (getEnchantmentLevel(SOULBOUND, stack) > 0) {
                    if (addToPlayerInventory(player, stack)) {
                        iter.remove();
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handlePlayerCloneEvent(PlayerEvent.Clone event) {

        if (!event.isWasDeath()) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        PlayerEntity oldPlayer = event.getOriginal();
        if (Utils.isFakePlayer(player) || player.world.getGameRules().getBoolean(KEEP_INVENTORY)) {
            return;
        }
        for (int i = 0; i < oldPlayer.inventory.armorInventory.size(); ++i) {
            ItemStack stack = oldPlayer.inventory.armorInventory.get(i);
            int encSoulbound = getEnchantmentLevel(SOULBOUND, stack);
            if (encSoulbound > 0) {
                if (SoulboundEnchantment.permanent) {
                    if (encSoulbound > 1) {
                        removeEnchantment(stack, SOULBOUND);
                        addEnchantment(stack, SOULBOUND, 1);
                    }
                } else if (player.world.rand.nextInt(1 + encSoulbound) == 0) {
                    removeEnchantment(stack, SOULBOUND);
                    if (encSoulbound > 1) {
                        addEnchantment(stack, SOULBOUND, encSoulbound - 1);
                    }
                }
                if (addToPlayerInventory(player, stack)) {
                    oldPlayer.inventory.armorInventory.set(i, ItemStack.EMPTY);
                }
            }
        }
        for (int i = 0; i < oldPlayer.inventory.mainInventory.size(); ++i) {
            ItemStack stack = oldPlayer.inventory.mainInventory.get(i);
            int encSoulbound = getEnchantmentLevel(SOULBOUND, stack);
            if (encSoulbound > 0) {
                if (SoulboundEnchantment.permanent) {
                    if (encSoulbound > 1) {
                        removeEnchantment(stack, SOULBOUND);
                        addEnchantment(stack, SOULBOUND, 1);
                    }
                } else if (player.world.rand.nextInt(1 + encSoulbound) == 0) {
                    removeEnchantment(stack, SOULBOUND);
                    if (encSoulbound > 1) {
                        addEnchantment(stack, SOULBOUND, encSoulbound - 1);
                    }
                }
                if (addToPlayerInventory(player, stack)) {
                    oldPlayer.inventory.mainInventory.set(i, ItemStack.EMPTY);
                }
            }
        }
    }

}