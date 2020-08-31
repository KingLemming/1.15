package cofh.thermal.expansion.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cofh.core.util.constants.Constants.ID_THERMAL_EXPANSION;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_THERMAL_EXPANSION)
public class TExpDataGen {

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

        generator.addProvider(new TExpTags.Block(generator));
        generator.addProvider(new TExpTags.Item(generator));
        generator.addProvider(new TExpTags.Fluid(generator));

        generator.addProvider(new TExpLootTables(generator));
        generator.addProvider(new TExpRecipes(generator));
    }

    private static void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new TExpBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(new TExpItemModels(generator, event.getExistingFileHelper()));
    }

}
