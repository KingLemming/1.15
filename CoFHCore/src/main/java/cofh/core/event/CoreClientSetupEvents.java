package cofh.core.event;

import cofh.lib.item.SpawnEggItemCoFH;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ID_COFH_CORE, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CoreClientSetupEvents {

    private CoreClientSetupEvents() {

    }

    @SubscribeEvent
    public static void colorSetupItem(final ColorHandlerEvent.Item event) {

        ItemColors colors = event.getItemColors();
        for (SpawnEggItemCoFH eggItem : SpawnEggItemCoFH.getEggs()) {
            colors.register((stack, tintIndex) -> eggItem.getColor(tintIndex), eggItem);
        }
    }

}
