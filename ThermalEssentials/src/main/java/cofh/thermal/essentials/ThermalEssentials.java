package cofh.thermal.essentials;

import cofh.thermal.essentials.client.gui.BasicPulverizerScreen;
import cofh.thermal.essentials.client.gui.BasicSawmillScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cofh.lib.util.constants.Constants.ID_THERMAL_ESSENTIALS;
import static cofh.thermal.essentials.init.TEssReferences.BASIC_PULVERIZER_CONTAINER;
import static cofh.thermal.essentials.init.TEssReferences.BASIC_SAWMILL_CONTAINER;

@Mod(ID_THERMAL_ESSENTIALS)
public class ThermalEssentials {

    public ThermalEssentials() {

        // TODO: Restore
        //        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //
        //        modEventBus.addListener(this::commonSetup);
        //        modEventBus.addListener(this::clientSetup);
        //        modEventBus.addListener(this::gatherData);
        //
        //        TEssBlocks.register();
        //
        //        TEssTileEntities.register();
        //        TEssContainers.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ScreenManager.registerFactory(BASIC_SAWMILL_CONTAINER, BasicSawmillScreen::new);
        ScreenManager.registerFactory(BASIC_PULVERIZER_CONTAINER, BasicPulverizerScreen::new);
    }
    // endregion

    // region DATA
    private void gatherData(final GatherDataEvent event) {

        if (event.includeServer()) {
            registerServerProviders(event.getGenerator());
        }
        if (event.includeClient()) {
            registerClientProviders(event.getGenerator(), event);
        }
    }

    private void registerServerProviders(DataGenerator generator) {

        //        generator.addProvider(new TCulTags.Block(generator));
        //        generator.addProvider(new TCulTags.Item(generator));
        //        generator.addProvider(new TCulTags.Fluid(generator));
        //
        //        generator.addProvider(new TCulLootTables(generator));
        //        generator.addProvider(new TCulRecipes(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

    }
    // endregion
}
