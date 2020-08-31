package cofh.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cofh.core.util.constants.Constants.ID_COFH_CORE;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_COFH_CORE)
public class CoreDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {

        if (event.includeServer()) {
            registerServerProviders(event.getGenerator());
        }
        if (event.includeClient()) {
            registerClientProviders(event.getGenerator(), event);
        }
    }

    private static void registerServerProviders(DataGenerator generator) {

        generator.addProvider(new CoreLootTableProvider(generator));
        generator.addProvider(new CoreRecipeProvider(generator));
    }

    private static void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new CoreBlockStateProvider(generator, event.getExistingFileHelper()));
        generator.addProvider(new CoreItemModelProvider(generator, event.getExistingFileHelper()));
    }

}
