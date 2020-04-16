package cofh.thermal.essentials;

import cofh.thermal.essentials.init.TEssBlocks;
import cofh.thermal.essentials.init.TEssContainers;
import cofh.thermal.essentials.init.TEssTileEntities;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_ESSENTIALS;

@Mod(ID_THERMAL_ESSENTIALS)
public class ThermalEssentials {

    public ThermalEssentials() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);

        TEssBlocks.register();

        TEssTileEntities.register();
        TEssContainers.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

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
