package cofh.thermal.cultivation;

import cofh.thermal.cultivation.init.TCulBlocks;
import cofh.thermal.cultivation.init.TCulItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.HoeItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_CULTIVATION;
import static cofh.lib.util.constants.Constants.TILLED;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalFeatures.*;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.cultivation.init.TCulReferences.*;

@Mod(ID_THERMAL_CULTIVATION)
public class ThermalCultivation {

    public ThermalCultivation() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        TCulBlocks.register();
        TCulItems.register();

        setFeature(FLAG_PHYTOGRO, true);

        setFeature(FLAG_RESOURCE_APATITE, true);
        setFeature(FLAG_RESOURCE_NITER, true);

        setFeature(FLAG_BASIC_COMPONENTS, true);

        setFeature(FLAG_AREA_AUGMENTS, true);
        setFeature(FLAG_STORAGE_AUGMENTS, true);
        setFeature(FLAG_UPGRADE_AUGMENTS, true);

        setFeature(FLAG_ARMOR_BEEKEEPER, true);
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
    // endregion
}
