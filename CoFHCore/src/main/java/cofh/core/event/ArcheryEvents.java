package cofh.core.event;

import cofh.core.capability.templates.ArcheryBowItemWrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.core.capability.CapabilityArchery.BOW_ITEM_CAPABILITY;
import static cofh.core.util.Utils.getHeldEnchantmentLevel;
import static cofh.core.util.constants.Constants.DAMAGE_ARROW;
import static cofh.core.util.constants.Constants.ID_COFH_CORE;
import static cofh.core.util.helpers.ArcheryHelper.findAmmo;
import static cofh.core.util.helpers.ArcheryHelper.validBow;
import static cofh.core.util.references.EnsorcReferences.QUICK_DRAW;
import static cofh.core.util.references.EnsorcReferences.VOLLEY;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.INFINITY;

@Mod.EventBusSubscriber(modid = ID_COFH_CORE)
public class ArcheryEvents {

    private ArcheryEvents() {

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void handleArrowLooseEvent(ArrowLooseEvent event) {

        ItemStack bow = event.getBow();
        if (!validBow(bow)) {
            return;
        }
        PlayerEntity shooter = event.getPlayer();
        event.setCanceled(bow.getCapability(BOW_ITEM_CAPABILITY).orElse(new ArcheryBowItemWrapper(bow)).fireArrow(findAmmo(shooter), shooter, event.getCharge(), event.getWorld()));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void handleArrowNockEvent(ArrowNockEvent event) {

        ItemStack bow = event.getBow();
        if (!validBow(bow)) {
            return;
        }
        PlayerEntity shooter = event.getPlayer();
        ItemStack ammo = findAmmo(shooter);

        if (ammo.isEmpty() && getEnchantmentLevel(INFINITY, bow) > 0) {
            ammo = new ItemStack(Items.ARROW);
        }
        if (!ammo.isEmpty()) {
            shooter.setActiveHand(event.getHand());
            event.setAction(ActionResult.resultSuccess(bow));
        } else if (!shooter.abilities.isCreativeMode) {
            event.setAction(ActionResult.resultFail(bow));
        }
    }

    @SubscribeEvent
    public static void handleItemUseTickEvent(LivingEntityUseItemEvent.Tick event) {

        int encQuickDraw = getEnchantmentLevel(QUICK_DRAW, event.getItem());
        if (encQuickDraw > 0 && event.getDuration() > event.getItem().getUseDuration() - 20) {
            event.setDuration(event.getDuration() - encQuickDraw);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleLivingHurtEvent(LivingHurtEvent event) {

        if (event.isCanceled()) {
            return;
        }
        Entity entity = event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = event.getSource().getTrueSource();

        if (entity instanceof IProjectile) {
            return;
        }
        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        if (source.damageType.equals(DAMAGE_ARROW)) {
            int encVolley = getHeldEnchantmentLevel((LivingEntity) attacker, VOLLEY);
            if (encVolley > 0) {
                entity.hurtResistantTime = 0;
            }
        }
    }

}
