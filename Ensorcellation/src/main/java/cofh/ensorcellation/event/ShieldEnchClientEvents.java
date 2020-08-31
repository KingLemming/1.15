package cofh.ensorcellation.event;

import cofh.core.util.helpers.MathHelper;
import cofh.ensorcellation.enchantment.PhalanxEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.core.util.constants.Constants.ID_ENSORCELLATION;
import static cofh.core.util.references.EnsorcReferences.PHALANX;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ID_ENSORCELLATION)
public class ShieldEnchClientEvents {

    private ShieldEnchClientEvents() {

    }

    private static boolean hadPhalanx;
    private static double modPhalanx;
    private static long timePhalanx;

    @SubscribeEvent
    public static void handleFOVUpdateEvent(FOVUpdateEvent event) {

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
