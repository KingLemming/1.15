package cofh.thermal.locomotion;

import cofh.thermal.locomotion.data.TLocLootTables;
import cofh.thermal.locomotion.data.TLocRecipes;
import cofh.thermal.locomotion.data.TLocTags;
import cofh.thermal.locomotion.init.TLocBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_LOCOMOTION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.init.ThermalReferences.*;

@Mod(ID_THERMAL_LOCOMOTION)
public class ThermalLocomotion {

    public ThermalLocomotion() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);

        TLocBlocks.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_CROSSOVER_RAIL), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_RAIL), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PRISMARINE_POWERED_RAIL), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_RAIL), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_POWERED_RAIL), RenderType.cutout());
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

        generator.addProvider(new TLocTags.Block(generator));
        generator.addProvider(new TLocTags.Item(generator));
        generator.addProvider(new TLocTags.Fluid(generator));

        generator.addProvider(new TLocLootTables(generator));
        generator.addProvider(new TLocRecipes(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

    }
    // endregion
}
