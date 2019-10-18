package cofh.test;

import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.test.init.BlocksTest;
import cofh.test.init.ItemsTest;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
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

    public static final ItemGroup COFH_TEST_GROUP = new ItemGroup(-1, ID_COFH_TEST) {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {

            return new ItemStack(Items.DIAMOND);
        }
    };

    public CoFHTest() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        BlocksTest.register();
        ItemsTest.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }
    // endregion
}
