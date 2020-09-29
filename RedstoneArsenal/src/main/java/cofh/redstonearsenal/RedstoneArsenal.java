package cofh.redstonearsenal;

import cofh.core.registries.DeferredRegisterCoFH;
import cofh.redstonearsenal.init.RSABlocks;
import cofh.redstonearsenal.init.RSAConfig;
import cofh.redstonearsenal.init.RSAItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.core.util.constants.Constants.ID_REDSTONE_ARSENAL;

@Mod(ID_REDSTONE_ARSENAL)
public class RedstoneArsenal {

    public static final Logger LOG = LogManager.getLogger(ID_REDSTONE_ARSENAL);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_REDSTONE_ARSENAL);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_REDSTONE_ARSENAL);

    public static ItemGroup itemGroup;

    public RedstoneArsenal() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        RSAConfig.register();

        RSABlocks.register();
        RSAItems.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        //        if (RSAConfig.enableCreativeTab.get()) {
        //            itemGroup = new ItemGroup(-1, ID_ARCHERS_PARADOX) {
        //
        //                @Override
        //                @OnlyIn(Dist.CLIENT)
        //                public ItemStack createIcon() {
        //
        //                    return new ItemStack(ITEMS.get(ID_BLAZE_ARROW));
        //                }
        //            };
        //        } else {
        //            itemGroup = ItemGroup.COMBAT;
        //        }
    }
    // endregion
}
