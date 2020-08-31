package cofh.thermal.cultivation.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cofh.core.util.constants.Constants.ID_THERMAL_CULTIVATION;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_THERMAL_CULTIVATION)
public class TCulDataGen {

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

        generator.addProvider(new TCulTags.Block(generator));
        generator.addProvider(new TCulTags.Item(generator));
        generator.addProvider(new TCulTags.Fluid(generator));

        generator.addProvider(new TCulLootTables(generator));
        generator.addProvider(new TCulRecipes(generator));
    }

    private static void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new TCulBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(new TCulItemModels(generator, event.getExistingFileHelper()));
    }

}
