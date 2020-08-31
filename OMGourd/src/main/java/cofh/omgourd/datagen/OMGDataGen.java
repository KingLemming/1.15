package cofh.omgourd.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cofh.core.util.constants.Constants.ID_OMGOURD;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_OMGOURD)
public class OMGDataGen {

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

        generator.addProvider(new OMGLootTables(generator));
        generator.addProvider(new OMGRecipeProvider(generator));
    }

    private static void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new OMGItemModels(generator, event.getExistingFileHelper()));
    }

}
