package cofh.thermal.core;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("thermal_series")
public class ThermalSeries {

    public static final String MOD_ID = "thermal_series";
    public static final String MOD_NAME = "Thermal Series";
    public static final String VERSION = "1.0";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);

    public ThermalSeries() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }
    // endregion

    // region REGISTRATION
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public void registerBlocks(final RegistryEvent.Register<Block> event) {

        }

        @SubscribeEvent
        public void registerItems(final RegistryEvent.Register<Item> event) {

        }

        @SubscribeEvent
        public void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {

        }

        @SubscribeEvent
        public void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {

        }

    }
    // endregion
}
