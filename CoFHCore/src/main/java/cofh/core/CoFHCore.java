package cofh.core;

import cofh.core.command.CoFHCommand;
import cofh.core.data.CoreLootTables;
import cofh.core.data.CoreRecipes;
import cofh.core.event.CoreClientEvents;
import cofh.core.event.CoreCommonEvents;
import cofh.core.gui.TexturesCoFH;
import cofh.core.init.CoreBlocks;
import cofh.core.init.CoreConfig;
import cofh.core.init.CoreEffects;
import cofh.core.init.CoreItems;
import cofh.core.network.packet.client.IndexedChatPacket;
import cofh.core.network.packet.client.TileControlPacket;
import cofh.core.network.packet.client.TileGuiPacket;
import cofh.core.network.packet.client.TileStatePacket;
import cofh.core.network.packet.server.*;
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
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.*;

@Mod(ID_COFH_CORE)
public class CoFHCore {

    public static final Logger LOG = LogManager.getLogger(ID_COFH_CORE);
    public static final PacketHandler PACKET_HANDLER = new PacketHandler(new ResourceLocation(ID_COFH_CORE, "general"));
    public static Proxy proxy = DistExecutor.runForDist(() -> ProxyClient::new, () -> Proxy::new);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_COFH_CORE);
    public static final DeferredRegisterCoFH<Effect> EFFECTS = new DeferredRegisterCoFH<>(ForgeRegistries.POTIONS, ID_COFH_CORE);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_COFH_CORE);
    public static final DeferredRegisterCoFH<ParticleType<?>> PARTICLES = new DeferredRegisterCoFH<>(ForgeRegistries.PARTICLE_TYPES, ID_COFH_CORE);
    public static final DeferredRegisterCoFH<TileEntityType<?>> TILE_ENTITIES = new DeferredRegisterCoFH<>(ForgeRegistries.TILE_ENTITIES, ID_COFH_CORE);

    public CoFHCore() {

        registerPackets();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);

        MinecraftForge.EVENT_BUS.addListener(this::serverStarting);

        BLOCKS.register(modEventBus);
        EFFECTS.register(modEventBus);
        ITEMS.register(modEventBus);
        // PARTICLES.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);

        CoreConfig.register();

        CoreBlocks.register();
        CoreEffects.register();
        CoreItems.register();
        // CoreParticles.register();
    }

    private void registerPackets() {

        PACKET_HANDLER.registerPacket(PACKET_CONTROL, TileControlPacket::new);
        PACKET_HANDLER.registerPacket(PACKET_GUI, TileGuiPacket::new);
        PACKET_HANDLER.registerPacket(PACKET_STATE, TileStatePacket::new);
        PACKET_HANDLER.registerPacket(PACKET_CHAT, IndexedChatPacket::new);
        PACKET_HANDLER.registerPacket(PACKET_SECURITY, SecurityPacket::new);
        PACKET_HANDLER.registerPacket(PACKET_SECURITY_CONTROL, SecurityControlPacket::new);
        PACKET_HANDLER.registerPacket(PACKET_REDSTONE_CONTROL, RedstoneControlPacket::new);
        PACKET_HANDLER.registerPacket(PACKET_TRANSFER_CONTROL, TransferControlPacket::new);
        PACKET_HANDLER.registerPacket(PACKET_SIDE_CONFIG, SideConfigPacket::new);
        PACKET_HANDLER.registerPacket(PACKET_KEY_MULTIMODE, MultiModeItemPacket::new);
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
        EffectEvents.register();
        ShieldEvents.register();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(TexturesCoFH::preStitch);
        modEventBus.addListener(TexturesCoFH::postStitch);

        CoreClientEvents.register();

        AOEClientEvents.register();
    }

    private void serverStarting(final FMLServerStartingEvent event) {

        CoFHCommand.initialize(event.getCommandDispatcher());
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

        generator.addProvider(new CoreLootTables(generator));
        generator.addProvider(new CoreRecipes(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

    }
    // endregion
}
