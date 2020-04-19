package cofh.thermal.foundation;

import cofh.thermal.foundation.data.*;
import cofh.thermal.foundation.init.TFndBlocks;
import cofh.thermal.foundation.init.TFndItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_FOUNDATION;

@Mod(ID_THERMAL_FOUNDATION)
public class ThermalFoundation {

    public ThermalFoundation() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::gatherData);

        TFndBlocks.register();
        TFndItems.register();
    }

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

        generator.addProvider(new TFndTags.Block(generator));
        generator.addProvider(new TFndTags.Item(generator));
        generator.addProvider(new TFndTags.Fluid(generator));

        generator.addProvider(new TFndLootTables(generator));
        generator.addProvider(new TFndRecipes(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new TFndBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(new TFndItemModels(generator, event.getExistingFileHelper()));
    }
    // endregion
}
