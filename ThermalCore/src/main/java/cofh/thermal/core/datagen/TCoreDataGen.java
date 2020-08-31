package cofh.thermal.core.datagen;

import cofh.thermal.core.util.loot.TileNBTSync;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cofh.core.util.constants.Constants.ID_THERMAL;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_THERMAL)
public class TCoreDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {

        LootFunctionManager.registerFunction(new TileNBTSync.Serializer());

        if (event.includeServer()) {
            registerServerProviders(event.getGenerator());
        }
        if (event.includeClient()) {
            registerClientProviders(event.getGenerator(), event);
        }
    }

    private static void registerServerProviders(DataGenerator generator) {

        generator.addProvider(new TCoreTags.Block(generator));
        generator.addProvider(new TCoreTags.Item(generator));
        generator.addProvider(new TCoreTags.Fluid(generator));

        generator.addProvider(new TCoreLootTables(generator));
        generator.addProvider(new TCoreRecipes(generator));
    }

    private static void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new TCoreBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(new TCoreItemModels(generator, event.getExistingFileHelper()));
    }

}
