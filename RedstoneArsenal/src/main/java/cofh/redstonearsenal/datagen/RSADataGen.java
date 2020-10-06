package cofh.redstonearsenal.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cofh.core.util.constants.Constants.ID_REDSTONE_ARSENAL;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_REDSTONE_ARSENAL)
public class RSADataGen {

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

        //        generator.addProvider(new RSATags.Block(generator));
        //        generator.addProvider(new RSATags.Item(generator));

        generator.addProvider(new RSALootTables(generator));
        generator.addProvider(new RSARecipes(generator));
    }

    private static void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new RSABlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(new RSAItemModels(generator, event.getExistingFileHelper()));
    }

}
