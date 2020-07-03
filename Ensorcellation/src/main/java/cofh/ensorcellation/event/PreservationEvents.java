package cofh.ensorcellation.event;

import cofh.ensorcellation.enchantment.override.MendingEnchantmentAlt;
import cofh.ensorcellation.init.EnsorcConfig;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.constants.Constants.ID_ENSORCELLATION;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;

@Mod.EventBusSubscriber(modid = ID_ENSORCELLATION)
public class PreservationEvents {

    private PreservationEvents() {

    }

    @SubscribeEvent
    public static void handleAnvilRepairEvent(AnvilRepairEvent event) {

        if (event.isCanceled()) {
            return;
        }
        if (!EnsorcConfig.enableMendingOverride) {
            return;
        }
        ItemStack left = event.getItemInput();
        ItemStack output = event.getItemResult();

        if (getEnchantmentLevel(Enchantments.MENDING, left) <= 0) {
            return;
        }
        if (output.getDamage() < left.getDamage()) {
            event.setBreakChance(MendingEnchantmentAlt.anvilDamage);
        }
    }

    @SubscribeEvent
    public static void handleAnvilUpdateEvent(AnvilUpdateEvent event) {

        if (event.isCanceled()) {
            return;
        }
        if (!EnsorcConfig.enableMendingOverride) {
            return;
        }
        ItemStack left = event.getLeft();

        if (getEnchantmentLevel(Enchantments.MENDING, left) <= 0) {
            return;
        }
        ItemStack right = event.getRight();
        ItemStack output = left.copy();

        if (output.isDamageable() && output.getItem().getIsRepairable(left, right)) {
            int damageLeft = Math.min(output.getDamage(), output.getMaxDamage() / 4);
            if (damageLeft <= 0) {
                return;
            }
            int matCost;
            for (matCost = 0; damageLeft > 0 && matCost < right.getCount(); ++matCost) {
                int durability = output.getDamage() - damageLeft;
                output.setDamage(durability);
                damageLeft = Math.min(output.getDamage(), output.getMaxDamage() / 4);
            }
            event.setMaterialCost(matCost);
            // event.setCost(0);
            event.setOutput(output);
        } else if (output.isDamageable()) {
            int damageLeft = left.getMaxDamage() - left.getDamage();
            int damageRight = right.getMaxDamage() - right.getDamage();
            int damageRepair = damageLeft + damageRight + output.getMaxDamage() * 12 / 100;
            int damageOutput = output.getMaxDamage() - damageRepair;

            if (damageOutput < 0) {
                damageOutput = 0;
            }
            if (damageOutput < output.getDamage()) {
                output.setDamage(damageOutput);
            }
            // event.setCost(0);
            event.setOutput(output);
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void handlePickupXpEvent(PlayerXpEvent.PickupXp event) {

        if (event.isCanceled()) {
            return;
        }
        if (!EnsorcConfig.enableMendingOverride) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        ExperienceOrbEntity orb = event.getOrb();

        player.xpCooldown = 2;
        player.onItemPickup(orb, 1);

        if (orb.xpValue > 0) {
            player.giveExperiencePoints(orb.xpValue);
        }
        orb.remove();
        event.setCanceled(true);
    }

}