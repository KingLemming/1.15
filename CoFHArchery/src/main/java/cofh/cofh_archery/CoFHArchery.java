package cofh.cofh_archery;

import cofh.cofh_archery.entity.projectile.*;
import cofh.cofh_archery.init.ModConfig;
import cofh.cofh_archery.init.ModEffects;
import cofh.cofh_archery.init.ModEntities;
import cofh.cofh_archery.init.ModItems;
import cofh.cofh_archery.renderer.entity.projectile.*;
import cofh.lib.registries.DeferredRegisterCoFH;
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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_COFH_ARCHERY;

@Mod(ID_COFH_ARCHERY)
public class CoFHArchery {

    public static final Logger LOG = LogManager.getLogger(ID_COFH_ARCHERY);

    public static final DeferredRegisterCoFH<Effect> EFFECTS = new DeferredRegisterCoFH<>(ForgeRegistries.POTIONS, ID_COFH_ARCHERY);
    public static final DeferredRegisterCoFH<EntityType<?>> ENTITIES = new DeferredRegisterCoFH<>(ForgeRegistries.ENTITIES, ID_COFH_ARCHERY);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_COFH_ARCHERY);

    public static final ItemGroup COFH_ARCHERY_GROUP = new ItemGroup(-1, ID_COFH_ARCHERY) {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {

            return new ItemStack(Items.DIAMOND);
        }
    };

    public CoFHArchery() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

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
        RenderingRegistry.registerEntityRenderingHandler(DiamondArrowEntity.class, DiamondArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EnderArrowEntity.class, EnderArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplosiveArrowEntity.class, ExplosiveArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FrostArrowEntity.class, FrostArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(LightningArrowEntity.class, LightningArrowRenderer::new);
        // RenderingRegistry.registerEntityRenderingHandler(MagmaArrowEntity.class, MagmaArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PhantasmalArrowEntity.class, PhantasmalArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PrismarineArrowEntity.class, PrismarineArrowRenderer::new);
        // RenderingRegistry.registerEntityRenderingHandler(RedstoneArrowEntity.class, RedstoneArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ShulkerArrowEntity.class, ShulkerArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SlimeArrowEntity.class, SlimeArrowRenderer::new);
        // RenderingRegistry.registerEntityRenderingHandler(SporeArrowEntity.class, SporeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TrainingArrowEntity.class, TrainingArrowRenderer::new);
    }
    // endregion
}
