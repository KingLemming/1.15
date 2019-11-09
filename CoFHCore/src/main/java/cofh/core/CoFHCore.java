package cofh.core;

import cofh.core.event.ClientEventsCore;
import cofh.core.event.CommonEventsCore;
import cofh.core.init.BlocksCore;
import cofh.core.init.ConfigCore;
import cofh.core.init.EffectsCore;
import cofh.lib.capability.CapabilityAOE;
import cofh.lib.capability.CapabilityArchery;
import cofh.lib.capability.CapabilityEnchantable;
import cofh.lib.capability.CapabilityShield;
import cofh.lib.event.*;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

@Mod(ID_COFH_CORE)
public class CoFHCore {

    public static final Logger LOG = LogManager.getLogger(ID_COFH_CORE);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_COFH_CORE);
    public static final DeferredRegisterCoFH<Effect> EFFECTS = new DeferredRegisterCoFH<>(ForgeRegistries.POTIONS, ID_COFH_CORE);

    public CoFHCore() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        BLOCKS.register(modEventBus);
        EFFECTS.register(modEventBus);

        ConfigCore.register();

        BlocksCore.register();
        EffectsCore.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        CapabilityAOE.register();
        CapabilityArchery.register();
        CapabilityEnchantable.register();
        CapabilityShield.register();

        CommonEventsCore.register();

        ArcheryEvents.register();
        AOEEvents.register();
        ShieldEvents.register();
        EffectEvents.register();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ClientEventsCore.register();

        AOEClientEvents.register();
    }
    // endregion

}
