package cofh.thermal.core;

import cofh.thermal.core.init.BlocksTSeries;
import cofh.thermal.core.init.FluidsTSeries;
import cofh.thermal.core.init.ItemsTSeries;
import cofh.thermal.cultivation.init.BlocksTCultivation;
import cofh.thermal.cultivation.init.ItemsTCultivation;
import cofh.thermal.foundation.init.BlocksTFoundation;
import cofh.thermal.foundation.init.ItemsTFoundation;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_THERMAL_SERIES;

@Mod(ID_THERMAL_SERIES)
public class ThermalSeries {

    public static final String MOD_NAME = "Thermal Series";
    public static final String VERSION = "1.0";
    public static final Logger LOG = LogManager.getLogger(ID_THERMAL_SERIES);

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ID_THERMAL_SERIES);
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, ID_THERMAL_SERIES);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ID_THERMAL_SERIES);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, ID_THERMAL_SERIES);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, ID_THERMAL_SERIES);

    public ThermalSeries() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        FLUIDS.register(modEventBus);

        BlocksTSeries.register();
        FluidsTSeries.register();
        ItemsTSeries.register();

        BlocksTFoundation.register();
        ItemsTFoundation.register();

        BlocksTCultivation.register();
        ItemsTCultivation.register();
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
