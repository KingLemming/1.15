package cofh.archersparadox;

import cofh.archersparadox.data.ModRecipes;
import cofh.archersparadox.data.ModTags;
import cofh.archersparadox.entity.projectile.*;
import cofh.archersparadox.init.ModConfig;
import cofh.archersparadox.init.ModEffects;
import cofh.archersparadox.init.ModEntities;
import cofh.archersparadox.init.ModItems;
import cofh.archersparadox.renderer.entity.projectile.*;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_ARCHERS_PARADOX;

@Mod(ID_ARCHERS_PARADOX)
public class ArchersParadox {

    public static final Logger LOG = LogManager.getLogger(ID_ARCHERS_PARADOX);

    public static final DeferredRegisterCoFH<Effect> EFFECTS = new DeferredRegisterCoFH<>(ForgeRegistries.POTIONS, ID_ARCHERS_PARADOX);
    public static final DeferredRegisterCoFH<EntityType<?>> ENTITIES = new DeferredRegisterCoFH<>(ForgeRegistries.ENTITIES, ID_ARCHERS_PARADOX);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_ARCHERS_PARADOX);

    public static final ItemGroup ARCHERS_PARADOX_GROUP = new ItemGroup(-1, ID_ARCHERS_PARADOX) {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {

            return new ItemStack(Items.BOW);
        }
    };

    public ArchersParadox() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);

        EFFECTS.register(modEventBus);
        ENTITIES.register(modEventBus);
        ITEMS.register(modEventBus);

        ModConfig.register();

        ModEffects.register();
        ModEntities.register();
        ModItems.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(BlazeArrowEntity.class, BlazeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ChallengeArrowEntity.class, ChallengeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DiamondArrowEntity.class, DiamondArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EnderArrowEntity.class, EnderArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplosiveArrowEntity.class, ExplosiveArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FrostArrowEntity.class, FrostArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(LightningArrowEntity.class, LightningArrowRenderer::new);
        // RenderingRegistry.registerEntityRenderingHandler(MagmaArrowEntity.class, MagmaArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PhantasmalArrowEntity.class, PhantasmalArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PrismarineArrowEntity.class, PrismarineArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(QuartzArrowEntity.class, QuartzArrowRenderer::new);
        // RenderingRegistry.registerEntityRenderingHandler(RedstoneArrowEntity.class, RedstoneArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ShulkerArrowEntity.class, ShulkerArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SlimeArrowEntity.class, SlimeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SporeArrowEntity.class, SporeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TrainingArrowEntity.class, TrainingArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(VerdantArrowEntity.class, VerdantArrowRenderer::new);
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

        generator.addProvider(new ModTags.Item(generator));
        generator.addProvider(new ModRecipes(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

    }
    // endregion
}
