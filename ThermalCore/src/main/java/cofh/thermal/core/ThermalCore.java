package cofh.thermal.core;

import cofh.core.CoFHCore;
import cofh.lib.client.renderer.model.entity.ArmorModelFullSuit;
import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.thermal.core.client.gui.ThermalTextures;
import cofh.thermal.core.client.gui.workbench.TinkerBenchScreen;
import cofh.thermal.core.client.renderer.model.MachineModelLoader;
import cofh.thermal.core.common.ThermalConfig;
import cofh.thermal.core.common.ThermalRecipeManagers;
import cofh.thermal.core.data.*;
import cofh.thermal.core.event.TCoreClientEvents;
import cofh.thermal.core.init.*;
import cofh.thermal.core.util.loot.TileNBTSync;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.resources.ReloadListener;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.init.TCoreReferences.*;

@Mod(ID_THERMAL)
public class ThermalCore {

    public static final Logger LOG = LogManager.getLogger(ID_THERMAL);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_THERMAL);
    public static final DeferredRegisterCoFH<Fluid> FLUIDS = new DeferredRegisterCoFH<>(ForgeRegistries.FLUIDS, ID_THERMAL);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_THERMAL);

    public static final DeferredRegisterCoFH<ContainerType<?>> CONTAINERS = new DeferredRegisterCoFH<>(ForgeRegistries.CONTAINERS, ID_THERMAL);
    public static final DeferredRegisterCoFH<EntityType<?>> ENTITIES = new DeferredRegisterCoFH<>(ForgeRegistries.ENTITIES, ID_THERMAL);
    public static final DeferredRegisterCoFH<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegisterCoFH<>(ForgeRegistries.RECIPE_SERIALIZERS, ID_THERMAL);
    public static final DeferredRegisterCoFH<SoundEvent> SOUND_EVENTS = new DeferredRegisterCoFH<>(ForgeRegistries.SOUND_EVENTS, ID_THERMAL);
    public static final DeferredRegisterCoFH<TileEntityType<?>> TILE_ENTITIES = new DeferredRegisterCoFH<>(ForgeRegistries.TILE_ENTITIES, ID_THERMAL);

    static {
        TCoreBlocks.register();
        TCoreFluids.register();
        TCoreItems.register();

        TCoreEntities.register();
        TCoreSounds.register();
    }

    public ThermalCore() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);
        modEventBus.addListener(this::gatherData);

        MinecraftForge.EVENT_BUS.addListener(this::serverAboutToStart);
        MinecraftForge.EVENT_BUS.addListener(this::serverStarted);
        MinecraftForge.EVENT_BUS.addListener(this::serverStopping);
        MinecraftForge.EVENT_BUS.addListener(this::serverStopped);
        MinecraftForge.EVENT_BUS.addListener(this::recipesUpdated);

        BLOCKS.register(modEventBus);
        CONTAINERS.register(modEventBus);
        ENTITIES.register(modEventBus);
        FLUIDS.register(modEventBus);
        ITEMS.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);

        ThermalConfig.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        TCoreBlocks.setup();

        LootFunctionManager.registerFunction(new TileNBTSync.Serializer());
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(ThermalTextures::preStitch);
        modEventBus.addListener(ThermalTextures::postStitch);

        TCoreClientEvents.register();

        ScreenManager.registerFactory(TINKER_BENCH_CONTAINER, TinkerBenchScreen::new);

        RenderType cutout = RenderType.getCutout();

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_SIGNALUM_GLASS), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_LUMIUM_GLASS), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_ENDERIUM_GLASS), cutout);

        ModelLoaderRegistry.registerLoader(new ResourceLocation(ID_THERMAL, "machine"), new MachineModelLoader());

        CoFHCore.PROXY.addModel(ITEMS.get(ID_BEEKEEPER_HELMET), ArmorModelFullSuit.LARGE);
        CoFHCore.PROXY.addModel(ITEMS.get(ID_BEEKEEPER_CHESTPLATE), ArmorModelFullSuit.DEFAULT);

        CoFHCore.PROXY.addModel(ITEMS.get(ID_HAZMAT_HELMET), ArmorModelFullSuit.LARGE);
        CoFHCore.PROXY.addModel(ITEMS.get(ID_HAZMAT_CHESTPLATE), ArmorModelFullSuit.DEFAULT);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {

        ThermalRecipeManagers.instance().setServerRecipeManager(event.getServer().getRecipeManager());

        event.getServer().getResourceManager().addReloadListener(
                new ReloadListener<Void>() {

                    @Override
                    protected Void prepare(IResourceManager resourceManagerIn, IProfiler profilerIn) {

                        return null;
                    }

                    @Override
                    protected void apply(Void nothing, IResourceManager resourceManagerIn, IProfiler profilerIn) {

                        ThermalRecipeManagers.instance().refreshServer();
                    }
                }
        );
    }

    private void serverStarted(FMLServerStartedEvent event) {

    }

    private void serverStopping(FMLServerStoppingEvent event) {

    }

    private void serverStopped(FMLServerStoppedEvent event) {

        ThermalRecipeManagers.instance().setServerRecipeManager(null);
    }

    private void recipesUpdated(RecipesUpdatedEvent event) {

        ThermalRecipeManagers.instance().refreshClient(event.getRecipeManager());
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

        generator.addProvider(new TCoreTags.Block(generator));
        generator.addProvider(new TCoreTags.Item(generator));
        generator.addProvider(new TCoreTags.Fluid(generator));

        generator.addProvider(new TCoreLootTables(generator));
        generator.addProvider(new TCoreRecipes(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new TCoreBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(new TCoreItemModels(generator, event.getExistingFileHelper()));
    }
    // endregion
}
