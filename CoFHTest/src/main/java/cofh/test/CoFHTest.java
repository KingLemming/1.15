package cofh.test;

import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.test.entity.projectile.ExplosiveArrowEntity;
import cofh.test.entity.projectile.PrismarineArrowEntity;
import cofh.test.entity.projectile.ShulkerArrowEntity;
import cofh.test.entity.projectile.SlimeArrowEntity;
import cofh.test.init.BlocksTest;
import cofh.test.init.ItemsTest;
import cofh.test.item.ExplosiveArrowItem;
import cofh.test.item.PrismarineArrowItem;
import cofh.test.item.ShulkerArrowItem;
import cofh.test.item.SlimeArrowItem;
import cofh.test.renderer.entity.ExplosiveArrowRenderer;
import cofh.test.renderer.entity.PrismarineArrowRenderer;
import cofh.test.renderer.entity.ShulkerArrowRenderer;
import cofh.test.renderer.entity.SlimeArrowRenderer;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

import static cofh.lib.util.constants.Constants.ID_COFH_TEST;

@Mod(ID_COFH_TEST)
public class CoFHTest {

    public static final Logger LOG = LogManager.getLogger(ID_COFH_TEST);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_COFH_TEST);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_COFH_TEST);
    public static final DeferredRegisterCoFH<EntityType<?>> ENTITIES = new DeferredRegisterCoFH<>(ForgeRegistries.ENTITIES, ID_COFH_TEST);

    public static final ItemGroup COFH_TEST_GROUP = new ItemGroup(-1, ID_COFH_TEST) {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {

            return new ItemStack(Items.DIAMOND);
        }
    };

    public static final RegistryObject<Item> EXPLOSIVE_ARROW_ITEM = ITEMS.register("explosive_arrow", () -> new ExplosiveArrowItem(new Item.Properties().group(COFH_TEST_GROUP)));
    public static final RegistryObject<Item> PRISMARINE_ARROW_ITEM = ITEMS.register("prismarine_arrow", () -> new PrismarineArrowItem(new Item.Properties().group(COFH_TEST_GROUP)));
    public static final RegistryObject<Item> SHULKER_ARROW_ITEM = ITEMS.register("shulker_arrow", () -> new ShulkerArrowItem(new Item.Properties().group(COFH_TEST_GROUP)));
    public static final RegistryObject<Item> SLIME_ARROW_ITEM = ITEMS.register("slime_arrow", () -> new SlimeArrowItem(new Item.Properties().group(COFH_TEST_GROUP)));

    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> EXPLOSIVE_ARROW_ENTITY = ENTITIES.register("explosive_arrow", () -> EntityType.Builder.<ExplosiveArrowEntity>create(ExplosiveArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("explosive_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> PRISMARINE_ARROW_ENTITY = ENTITIES.register("prismarine_arrow", () -> EntityType.Builder.<PrismarineArrowEntity>create(PrismarineArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("prismarine_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> SHULKER_ARROW_ENTITY = ENTITIES.register("shulker_arrow", () -> EntityType.Builder.<ShulkerArrowEntity>create(ShulkerArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("shulker_arrow"));
    public static final RegistryObject<EntityType<? extends AbstractArrowEntity>> SLIME_ARROW_ENTITY = ENTITIES.register("slime_arrow", () -> EntityType.Builder.<SlimeArrowEntity>create(SlimeArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build("slime_arrow"));

    public CoFHTest() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);

        BlocksTest.register();
        ItemsTest.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(ExplosiveArrowEntity.class, ExplosiveArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PrismarineArrowEntity.class, PrismarineArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ShulkerArrowEntity.class, ShulkerArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SlimeArrowEntity.class, SlimeArrowRenderer::new);
    }
    // endregion

}
