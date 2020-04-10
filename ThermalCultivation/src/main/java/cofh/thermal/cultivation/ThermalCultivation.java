package cofh.thermal.cultivation;

import cofh.thermal.cultivation.data.TCulLootTables;
import cofh.thermal.cultivation.data.TCulRecipes;
import cofh.thermal.cultivation.data.TCulTags;
import cofh.thermal.cultivation.gui.client.MachineHiveExtractorScreen;
import cofh.thermal.cultivation.init.TCulBlocks;
import cofh.thermal.cultivation.init.TCulContainers;
import cofh.thermal.cultivation.init.TCulItems;
import cofh.thermal.cultivation.init.TCulTileEntities;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_CULTIVATION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.cultivation.init.TCulReferences.*;

@Mod(ID_THERMAL_CULTIVATION)
public class ThermalCultivation {

    public ThermalCultivation() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::colorSetup);
        modEventBus.addListener(this::gatherData);

        TCulBlocks.register();
        TCulItems.register();

        TCulTileEntities.register();
        TCulContainers.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        // CROPS
        ComposterBlock.CHANCES.put(ITEMS.get(ID_BARLEY), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_ONION), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_RADISH), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_RICE), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_SADIROOT), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_SPINACH), 0.65F);

        ComposterBlock.CHANCES.put(ITEMS.get(ID_BELL_PEPPER), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_EGGPLANT), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_GREEN_BEAN), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_PEANUT), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_STRAWBERRY), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_TOMATO), 0.65F);

        ComposterBlock.CHANCES.put(ITEMS.get(ID_COFFEE), 0.65F);
        ComposterBlock.CHANCES.put(ITEMS.get(ID_TEA), 0.65F);

        // SEEDS
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_BARLEY)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_ONION)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_RADISH)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_RICE)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_SADIROOT)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_SPINACH)), 0.3F);

        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_BELL_PEPPER)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_EGGPLANT)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_GREEN_BEAN)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_PEANUT)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_STRAWBERRY)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_TOMATO)), 0.3F);

        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_COFFEE)), 0.3F);
        ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_TEA)), 0.3F);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ScreenManager.registerFactory(MACHINE_BEE_EXTRACTOR_CONTAINER, MachineHiveExtractorScreen::new);

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_BARLEY), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_ONION), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_RADISH), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_RICE), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_SADIROOT), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_SPINACH), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_BELL_PEPPER), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_EGGPLANT), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_GREEN_BEAN), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PEANUT), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_STRAWBERRY), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_TOMATO), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_COFFEE), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_TEA), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_FROST_MELON_STEM), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_FROST_MELON_STEM_ATTACHED), RenderType.getCutout());
    }

    private void colorSetup(final ColorHandlerEvent.Block event) {

        BlockColors colors = event.getBlockColors();
        colors.register((blockState, lightReader, pos, d) -> 0x96DCF8, BLOCKS.get(ID_FROST_MELON_STEM_ATTACHED));
        colors.register((blockState, lightReader, pos, d) -> {
            int age = blockState.get(StemBlock.AGE);
            int r = 80 + age * 10;
            int g = 255 - age * 5;
            int b = 80 + age * 24;
            return r << 16 | g << 8 | b;
        }, BLOCKS.get(ID_FROST_MELON_STEM));
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

        generator.addProvider(new TCulTags.Block(generator));
        generator.addProvider(new TCulTags.Item(generator));
        generator.addProvider(new TCulTags.Fluid(generator));

        generator.addProvider(new TCulLootTables(generator));
        generator.addProvider(new TCulRecipes(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

    }
    // endregion
}
