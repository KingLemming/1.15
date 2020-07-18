package cofh.omgourd;

import cofh.core.init.CoreItems;
import cofh.lib.block.deco.CarvedPumpkinBlockCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.omgourd.init.OMGBlocks;
import cofh.omgourd.init.OMGConfig;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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

import static cofh.lib.util.constants.Constants.ID_OMGOURD;

@Mod(ID_OMGOURD)
public class OMGourd {

    public static final Logger LOG = LogManager.getLogger(ID_OMGOURD);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_OMGOURD);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_OMGOURD);

    public static ItemGroup itemGroup;

    public OMGourd() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        OMGConfig.register();

        OMGBlocks.register();

        CoreItems.registerShearsOverride();
        CarvedPumpkinBlockCoFH.updateTest();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        OMGBlocks.setup();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        if (OMGConfig.enableCreativeTab.get()) {
            itemGroup = new ItemGroup(-1, ID_OMGOURD) {

                @Override
                @OnlyIn(Dist.CLIENT)
                public ItemStack createIcon() {

                    return new ItemStack(ITEMS.get("carved_pumpkin_1"));
                }
            };
        } else {
            itemGroup = ItemGroup.BUILDING_BLOCKS;
        }
    }
    // endregion
}
