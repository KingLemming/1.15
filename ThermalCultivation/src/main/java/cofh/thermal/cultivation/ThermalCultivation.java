package cofh.thermal.cultivation;

import cofh.thermal.cultivation.data.TCulLootTables;
import cofh.thermal.cultivation.data.TCulRecipes;
import cofh.thermal.cultivation.data.TCulTags;
import cofh.thermal.cultivation.init.TCulBlocks;
import cofh.thermal.cultivation.init.TCulItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_CULTIVATION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.seeds;

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

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_BARLEY), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_ONION), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_RADISH), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_RICE), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_SADIROOT), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_SPINACH), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_BELL_PEPPER), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_EGGPLANT), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_GREEN_BEAN), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_PEANUT), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_STRAWBERRY), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_TOMATO), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_COFFEE), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_TEA), RenderType.cutout());
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
