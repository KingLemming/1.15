package cofh.thermal.cultivation.event;

import net.minecraft.block.StemBlock;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.constants.Constants.ID_THERMAL_CULTIVATION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.cultivation.init.TCulReferences.ID_FROST_MELON_STEM;
import static cofh.thermal.cultivation.init.TCulReferences.ID_FROST_MELON_STEM_ATTACHED;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ID_THERMAL_CULTIVATION, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TCulClientSetupEvents {

    private TCulClientSetupEvents() {

    }

    @SubscribeEvent
    public static void colorSetupBlock(final ColorHandlerEvent.Block event) {

        BlockColors colors = event.getBlockColors();
        colors.register((blockState, lightReader, pos, d) -> 0x96DCF8, BLOCKS.get(ID_FROST_MELON_STEM_ATTACHED));
        colors.register((blockState, lightReader, pos, d) -> {
            int age = blockState.get(StemBlock.AGE);
            int r = 80 + age * 10;
            int g = 255 - age * 5;
            int b = 80 + age * 24;
            return r << 16 | g << 8 | b;
        }, BLOCKS.get(ID_FROST_MELON_STEM));
    }

}
