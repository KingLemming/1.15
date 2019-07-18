package cofh.core;

import cofh.lib.capability.CapabilityArchery;
import cofh.lib.capability.CapabilityEnchantable;
import cofh.lib.capability.CapabilityMelee;
import cofh.lib.event.CommonEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CoFHCore.MOD_ID)
public class CoFHCore {

    public static final String MOD_ID = "cofh_core";
    public static final String MOD_NAME = "CoFH Core";
    public static final String VERSION = "1.0";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);

    public CoFHCore() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);

        FluidRegistry.enableUniversalBucket();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        CapabilityArchery.register();
        CapabilityEnchantable.register();
        CapabilityMelee.register();

        CommonEvents.register();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }
    // endregion
}
