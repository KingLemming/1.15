package cofh.thermal.cultivation;

import cofh.thermal.cultivation.client.gui.DeviceHiveExtractorScreen;
import cofh.thermal.cultivation.data.TCulItemModels;
import cofh.thermal.cultivation.data.TCulLootTables;
import cofh.thermal.cultivation.data.TCulRecipes;
import cofh.thermal.cultivation.data.TCulTags;
import cofh.thermal.cultivation.init.TCulBlocks;
import cofh.thermal.cultivation.init.TCulItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.HoeItem;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_CULTIVATION;
import static cofh.lib.util.constants.Constants.TILLED;
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
        modEventBus.addListener(this::gatherData);

        TCulBlocks.register();
        TCulItems.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        // CROPS
        {
            float chance = 0.65F;

            ComposterBlock.CHANCES.put(ITEMS.get(ID_BARLEY), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_ONION), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_RADISH), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_RICE), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_SADIROOT), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_SPINACH), chance);

            ComposterBlock.CHANCES.put(ITEMS.get(ID_BELL_PEPPER), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_EGGPLANT), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_GREEN_BEAN), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_PEANUT), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_STRAWBERRY), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_TOMATO), chance);

            ComposterBlock.CHANCES.put(ITEMS.get(ID_COFFEE), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(ID_TEA), chance);

            ComposterBlock.CHANCES.put(ITEMS.get(ID_FROST_MELON), chance);
        }
        {
            float chance = 0.5F;

            ComposterBlock.CHANCES.put(ITEMS.get(ID_FROST_MELON_SLICE), chance);
        }
        // SEEDS
        {
            float chance = 0.3F;

            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_BARLEY)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_ONION)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_RADISH)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_RICE)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_SADIROOT)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_SPINACH)), chance);

            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_BELL_PEPPER)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_EGGPLANT)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_GREEN_BEAN)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_PEANUT)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_STRAWBERRY)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_TOMATO)), chance);

            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_COFFEE)), chance);
            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_TEA)), chance);

            ComposterBlock.CHANCES.put(ITEMS.get(seeds(ID_FROST_MELON)), chance);
        }
        HoeItem.HOE_LOOKUP.put(BLOCKS.get(ID_PHYTOSOIL), BLOCKS.get(ID_PHYTOSOIL).getDefaultState().with(TILLED, true));
        HoeItem.HOE_LOOKUP.put(BLOCKS.get(ID_PHYTOSOIL_CHARGED), BLOCKS.get(ID_PHYTOSOIL_CHARGED).getDefaultState().with(TILLED, true));

        TCulBlocks.setup();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::colorSetup);

        ScreenManager.registerFactory(DEVICE_HIVE_EXTRACTOR_CONTAINER, DeviceHiveExtractorScreen::new);

        RenderType cutout = RenderType.getCutout();

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_BARLEY), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_ONION), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_RADISH), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_RICE), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_SADIROOT), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_SPINACH), cutout);

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_BELL_PEPPER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_EGGPLANT), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_GREEN_BEAN), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PEANUT), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_STRAWBERRY), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_TOMATO), cutout);

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_COFFEE), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_TEA), cutout);

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_FROST_MELON_STEM), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_FROST_MELON_STEM_ATTACHED), cutout);
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

        // generator.addProvider(new TCulBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(new TCulItemModels(generator, event.getExistingFileHelper()));
    }
    // endregion
}
