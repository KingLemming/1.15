package cofh.cofh_archery;

import cofh.cofh_archery.entity.projectile.*;
import cofh.cofh_archery.item.projectile.*;
import cofh.cofh_archery.renderer.entity.projectile.*;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
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

    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_COFH_ARCHERY);
    public static final DeferredRegisterCoFH<EntityType<?>> ENTITIES = new DeferredRegisterCoFH<>(ForgeRegistries.ENTITIES, ID_COFH_ARCHERY);

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

        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
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
        RenderingRegistry.registerEntityRenderingHandler(MagmaArrowEntity.class, MagmaArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PhantasmalArrowEntity.class, PhantasmalArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PrismarineArrowEntity.class, PrismarineArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RedstoneArrowEntity.class, RedstoneArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ShulkerArrowEntity.class, ShulkerArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SlimeArrowEntity.class, SlimeArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TrainingArrowEntity.class, TrainingArrowRenderer::new);
    }
    // endregion

    // region REFERENCES
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> BLAZE_ARROW_ENTITY = ENTITIES.register("blaze_arrow", () -> EntityType.Builder.<BlazeArrowEntity>create(BlazeArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("blaze_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> DIAMOND_ARROW_ENTITY = ENTITIES.register("diamond_arrow", () -> EntityType.Builder.<DiamondArrowEntity>create(DiamondArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("diamond_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> ENDER_ARROW_ENTITY = ENTITIES.register("ender_arrow", () -> EntityType.Builder.<EnderArrowEntity>create(EnderArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("ender_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> EXPLOSIVE_ARROW_ENTITY = ENTITIES.register("explosive_arrow", () -> EntityType.Builder.<ExplosiveArrowEntity>create(ExplosiveArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("explosive_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> FROST_ARROW_ENTITY = ENTITIES.register("frost_arrow", () -> EntityType.Builder.<FrostArrowEntity>create(FrostArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("frost_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> LIGHTNING_ARROW_ENTITY = ENTITIES.register("lightning_arrow", () -> EntityType.Builder.<LightningArrowEntity>create(LightningArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("lightning_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> MAGMA_ARROW_ENTITY = ENTITIES.register("magma_arrow", () -> EntityType.Builder.<MagmaArrowEntity>create(MagmaArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("magma_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> PHANTASMAL_ARROW_ENTITY = ENTITIES.register("phantasmal_arrow", () -> EntityType.Builder.<PhantasmalArrowEntity>create(PhantasmalArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("phantasmal_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> PRISMARINE_ARROW_ENTITY = ENTITIES.register("prismarine_arrow", () -> EntityType.Builder.<PrismarineArrowEntity>create(PrismarineArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("prismarine_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> REDSTONE_ARROW_ENTITY = ENTITIES.register("redstone_arrow", () -> EntityType.Builder.<RedstoneArrowEntity>create(RedstoneArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("redstone_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> SHULKER_ARROW_ENTITY = ENTITIES.register("shulker_arrow", () -> EntityType.Builder.<ShulkerArrowEntity>create(ShulkerArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("shulker_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> SLIME_ARROW_ENTITY = ENTITIES.register("slime_arrow", () -> EntityType.Builder.<SlimeArrowEntity>create(SlimeArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("slime_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> TRAINING_ARROW_ENTITY = ENTITIES.register("training_arrow", () -> EntityType.Builder.<TrainingArrowEntity>create(TrainingArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("training_arrow"));

    public static final RegistryObject<Item> BLAZE_ARROW_ITEM = ITEMS.register("blaze_arrow", () -> new BlazeArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> DIAMOND_ARROW_ITEM = ITEMS.register("diamond_arrow", () -> new DiamondArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> ENDER_ARROW_ITEM = ITEMS.register("ender_arrow", () -> new EnderArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> EXPLOSIVE_ARROW_ITEM = ITEMS.register("explosive_arrow", () -> new ExplosiveArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> FROST_ARROW_ITEM = ITEMS.register("frost_arrow", () -> new FrostArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> LIGHTNING_ARROW_ITEM = ITEMS.register("lightning_arrow", () -> new LightningArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> MAGMA_ARROW_ITEM = ITEMS.register("magma_arrow", () -> new MagmaArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> PHANTASMAL_ARROW_ITEM = ITEMS.register("phantasmal_arrow", () -> new PhantasmalArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> PRISMARINE_ARROW_ITEM = ITEMS.register("prismarine_arrow", () -> new PrismarineArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> REDSTONE_ARROW_ITEM = ITEMS.register("redstone_arrow", () -> new RedstoneArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> SHULKER_ARROW_ITEM = ITEMS.register("shulker_arrow", () -> new ShulkerArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SLIME_ARROW_ITEM = ITEMS.register("slime_arrow", () -> new SlimeArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    public static final RegistryObject<Item> TRAINING_ARROW_ITEM = ITEMS.register("training_arrow", () -> new TrainingArrowItem(new Item.Properties().group(COFH_ARCHERY_GROUP)));
    // endregion

}
