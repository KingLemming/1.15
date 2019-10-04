package cofh.ensorcellment.event;

import cofh.ensorcellment.enchantment.PhalanxEnchantment;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static cofh.lib.util.modhelpers.EnsorcellmentHelper.PHALANX;

public class ClientEventsEnsorc {

    private static final ClientEventsEnsorc INSTANCE = new ClientEventsEnsorc();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        registered = true;
    }

    private ClientEventsEnsorc() {

    }

    private boolean hadPhalanx;
    private double modPhalanx;
    private long timePhalanx;

    @SubscribeEvent
    public void handleFOVUpdateEvent(FOVUpdateEvent event) {

        PlayerEntity entity = event.getEntity();
        ItemStack stack = event.getEntity().getActiveItemStack();

        if (stack.getItem().isShield(stack, entity)) {
            int encPhalanx = EnchantmentHelper.getEnchantmentLevel(PHALANX, stack);
            if (encPhalanx > 0) {
                modPhalanx = encPhalanx * PhalanxEnchantment.SPEED / 2D;
                hadPhalanx = true;
                timePhalanx = entity.world.getGameTime();
            }
            event.setNewfov((float) MathHelper.clamp(event.getFov() - modPhalanx, 1.0D, 2.5D));
        } else if (hadPhalanx) {
            if (entity.world.getGameTime() - 20 > timePhalanx) {
                hadPhalanx = false;
                modPhalanx = 0;
            }
            event.setNewfov((float) MathHelper.clamp(event.getFov() - modPhalanx, 1.0D, 2.5D));
        }
    }

}
