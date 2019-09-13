package cofh.lib.event;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static cofh.lib.capability.CapabilityArchery.BOW_ITEM_CAPABILITY;
import static cofh.lib.capability.CapabilityArchery.DEFAULT_BOW_CAPABILITY;
import static cofh.lib.capability.CapabilityMelee.SHIELD_ITEM_CAPABILITY;
import static cofh.lib.util.Utils.getHeldEnchantmentLevel;
import static cofh.lib.util.constants.Constants.DAMAGE_ARROW;
import static cofh.lib.util.helpers.ArcheryHelper.findAmmo;
import static cofh.lib.util.helpers.ArcheryHelper.validBow;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.QUICKDRAW;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.VOLLEY;
import static net.minecraft.enchantment.Enchantments.INFINITY;

public class ArcheryEvents {

    private static final ArcheryEvents INSTANCE = new ArcheryEvents();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        registered = true;
    }

    private ArcheryEvents() {

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void handleArrowLooseEvent(ArrowLooseEvent event) {

        ItemStack bow = event.getBow();

        if (!validBow(bow)) {
            return;
        }
        PlayerEntity shooter = event.getPlayer();
        event.setCanceled(bow.getCapability(BOW_ITEM_CAPABILITY).orElse(DEFAULT_BOW_CAPABILITY).fireArrow(bow, findAmmo(shooter), shooter, event.getCharge(), event.getWorld()));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void handleArrowNockEvent(ArrowNockEvent event) {

        if (!event.getBow().getCapability(BOW_ITEM_CAPABILITY).isPresent()) {
            return;
        }
        PlayerEntity shooter = event.getPlayer();
        ItemStack bow = event.getBow();
        ItemStack ammo = findAmmo(shooter);

        if (ammo.isEmpty() && EnchantmentHelper.getEnchantmentLevel(INFINITY, bow) > 0) {
            ammo = new ItemStack(Items.ARROW);
        }
        if (!ammo.isEmpty()) {
            shooter.setActiveHand(event.getHand());
            event.setAction(new ActionResult<>(ActionResultType.SUCCESS, bow));
        } else if (!shooter.abilities.isCreativeMode) {
            event.setAction(new ActionResult<>(ActionResultType.FAIL, bow));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void handleLivingAttackEvent(LivingAttackEvent event) {

        Entity entity = event.getEntity();

        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        DamageSource source = event.getSource();

        if (source instanceof IndirectEntityDamageSource || source.isUnblockable() || source.isProjectile()) {
            return;
        }
        if (source instanceof EntityDamageSource && ((EntityDamageSource) source).getIsThornsDamage()) {
            return;
        }
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        ItemStack shield = player.getActiveItemStack();
        shield.getCapability(SHIELD_ITEM_CAPABILITY).ifPresent(cap -> cap.onBlock(shield, player, source.getTrueSource()));
    }

    @SubscribeEvent
    public void handleItemUseTickEvent(LivingEntityUseItemEvent.Tick event) {

        int encQuickDraw = EnchantmentHelper.getEnchantmentLevel(QUICKDRAW, event.getItem());
        if (encQuickDraw > 0 && event.getDuration() > event.getItem().getUseDuration() - 20) {
            event.setDuration(event.getDuration() - 1);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void handleLivingHurtEvent(LivingHurtEvent event) {

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
