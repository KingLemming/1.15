package cofh.core;

import cofh.core.event.CoreClientEvents;
import cofh.core.event.CoreCommonEvents;
import cofh.core.init.CoreBlocks;
import cofh.core.init.CoreConfig;
import cofh.core.init.CoreEffects;
import cofh.core.init.CoreItems;
import cofh.core.util.Proxy;
import cofh.core.util.ProxyClient;
import cofh.lib.capability.CapabilityAOE;
import cofh.lib.capability.CapabilityArchery;
import cofh.lib.capability.CapabilityEnchantable;
import cofh.lib.capability.CapabilityShield;
import cofh.lib.event.*;
import cofh.lib.network.PacketHandler;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
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
    public static PacketHandler packetHandler;
    public static Proxy proxy = DistExecutor.runForDist(() -> ProxyClient::new, () -> Proxy::new);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_COFH_CORE);
    public static final DeferredRegisterCoFH<Effect> EFFECTS = new DeferredRegisterCoFH<>(ForgeRegistries.POTIONS, ID_COFH_CORE);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_COFH_CORE);
    public static final DeferredRegisterCoFH<ParticleType<?>> PARTICLES = new DeferredRegisterCoFH<>(ForgeRegistries.PARTICLE_TYPES, ID_COFH_CORE);

    public CoFHCore() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        BLOCKS.register(modEventBus);
        EFFECTS.register(modEventBus);
        ITEMS.register(modEventBus);
        // PARTICLES.register(modEventBus);

        CoreConfig.register();

        CoreBlocks.register();
        CoreEffects.register();
        CoreItems.register();
        // CoreParticles.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        CapabilityAOE.register();
        CapabilityArchery.register();
        CapabilityEnchantable.register();
        CapabilityShield.register();

        CoreCommonEvents.register();

        ArcheryEvents.register();
        AOEEvents.register();
        ShieldEvents.register();
        EffectEvents.register();

        // packetHandler = new PacketHandler(new ResourceLocation(ID_COFH_CORE, "network"));
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        CoreClientEvents.register();

        AOEClientEvents.register();
    }
    // endregion

}
