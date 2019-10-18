package cofh.thermal.core;

import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.thermal.core.init.BlocksTSeries;
import cofh.thermal.core.init.FluidsTSeries;
import cofh.thermal.core.init.ItemsTSeries;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_THERMAL_SERIES;

@Mod(ID_THERMAL_SERIES)
public class ThermalSeries {

    public static final String MOD_NAME = "Thermal Series";
    public static final String VERSION = "1.0";
    public static final Logger LOG = LogManager.getLogger(ID_THERMAL_SERIES);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_THERMAL_SERIES);
    public static final DeferredRegisterCoFH<Fluid> FLUIDS = new DeferredRegisterCoFH<>(ForgeRegistries.FLUIDS, ID_THERMAL_SERIES);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_THERMAL_SERIES);
    public static final DeferredRegisterCoFH<TileEntityType<?>> TILE_ENTITIES = new DeferredRegisterCoFH<>(ForgeRegistries.TILE_ENTITIES, ID_THERMAL_SERIES);
    public static final DeferredRegisterCoFH<ContainerType<?>> CONTAINERS = new DeferredRegisterCoFH<>(ForgeRegistries.CONTAINERS, ID_THERMAL_SERIES);

    public ThermalSeries() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);

        BLOCKS.register(modEventBus);
        FLUIDS.register(modEventBus);
        ITEMS.register(modEventBus);

        BlocksTSeries.register();
        FluidsTSeries.register();
        ItemsTSeries.register();
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
}
