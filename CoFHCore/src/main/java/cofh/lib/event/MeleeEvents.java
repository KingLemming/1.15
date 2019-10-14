package cofh.lib.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static cofh.lib.capability.CapabilityMelee.SHIELD_ITEM_CAPABILITY;

public class MeleeEvents {

    private static final MeleeEvents INSTANCE = new MeleeEvents();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        registered = true;
    }

    private MeleeEvents() {

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void handleLivingAttackEvent(LivingAttackEvent event) {

        if (event.isCanceled()) {
            return;
        }
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

}
