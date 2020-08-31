package cofh.archersparadox;

import cofh.archersparadox.client.renderer.entity.*;
import cofh.archersparadox.init.APConfig;
import cofh.archersparadox.init.APEffects;
import cofh.archersparadox.init.APEntities;
import cofh.archersparadox.init.APItems;
import cofh.core.registries.DeferredRegisterCoFH;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.archersparadox.init.APIDs.ID_BLAZE_ARROW;
import static cofh.archersparadox.init.APReferences.*;
import static cofh.core.util.constants.Constants.ID_ARCHERS_PARADOX;

@Mod(ID_ARCHERS_PARADOX)
public class ArchersParadox {

    public static final Logger LOG = LogManager.getLogger(ID_ARCHERS_PARADOX);

    public static final DeferredRegisterCoFH<Effect> EFFECTS = new DeferredRegisterCoFH<>(ForgeRegistries.POTIONS, ID_ARCHERS_PARADOX);
    public static final DeferredRegisterCoFH<EntityType<?>> ENTITIES = new DeferredRegisterCoFH<>(ForgeRegistries.ENTITIES, ID_ARCHERS_PARADOX);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_ARCHERS_PARADOX);

    public static ItemGroup itemGroup;

    public ArchersParadox() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        EFFECTS.register(modEventBus);
        ENTITIES.register(modEventBus);
        ITEMS.register(modEventBus);

        APConfig.register();

        APEffects.register();
        APEntities.register();
        APItems.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(BLAZE_ARROW_ENTITY, BlazeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(CHALLENGE_ARROW_ENTITY, ChallengeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DIAMOND_ARROW_ENTITY, DiamondArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DISPLACEMENT_ARROW_ENTITY, DisplacementArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ENDER_ARROW_ENTITY, EnderArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EXPLOSIVE_ARROW_ENTITY, ExplosiveArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FROST_ARROW_ENTITY, FrostArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(GLOWSTONE_ARROW_ENTITY, GlowstoneArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(LIGHTNING_ARROW_ENTITY, LightningArrowRenderer::new);
        // RenderingRegistry.registerEntityRenderingHandler(MagmaArrowEntity.class, MagmaArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PHANTASMAL_ARROW_ENTITY, PhantasmalArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PRISMARINE_ARROW_ENTITY, PrismarineArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(QUARTZ_ARROW_ENTITY, QuartzArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(REDSTONE_ARROW_ENTITY, RedstoneArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SHULKER_ARROW_ENTITY, ShulkerArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SLIME_ARROW_ENTITY, SlimeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SPORE_ARROW_ENTITY, SporeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TRAINING_ARROW_ENTITY, TrainingArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(VERDANT_ARROW_ENTITY, VerdantArrowRenderer::new);

        if (APConfig.enableCreativeTab.get()) {
            itemGroup = new ItemGroup(-1, ID_ARCHERS_PARADOX) {

                @Override
                @OnlyIn(Dist.CLIENT)
                public ItemStack createIcon() {

                    return new ItemStack(ITEMS.get(ID_BLAZE_ARROW));
                }
            };
        } else {
            itemGroup = ItemGroup.COMBAT;
        }
    }
    // endregion
}
