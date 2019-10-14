package cofh.core;

import cofh.core.event.ClientEventsCore;
import cofh.core.event.CommonEventsCore;
import cofh.core.init.BlocksCore;
import cofh.core.init.ConfigCore;
import cofh.core.init.PotionsCore;
import cofh.lib.capability.CapabilityArchery;
import cofh.lib.capability.CapabilityEnchantable;
import cofh.lib.capability.CapabilityMelee;
import cofh.lib.event.*;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

@Mod(ID_COFH_CORE)
public class CoFHCore {

    public static final String MOD_NAME = "CoFH Core";
    public static final String VERSION = "1.0";
    public static final Logger LOG = LogManager.getLogger(ID_COFH_CORE);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_COFH_CORE);
    public static final DeferredRegisterCoFH<Effect> POTIONS = new DeferredRegisterCoFH<>(ForgeRegistries.POTIONS, ID_COFH_CORE);

    public CoFHCore() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        BLOCKS.register(modEventBus);
        POTIONS.register(modEventBus);

        ConfigCore.register();

        BlocksCore.register();
        PotionsCore.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        CapabilityArchery.register();
        CapabilityEnchantable.register();
        CapabilityMelee.register();

        ArcheryEvents.register();
        AreaEffectEvents.register();
        MeleeEvents.register();
        PotionEvents.register();

        CommonEventsCore.register();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        AreaEffectClientEvents.register();

        ClientEventsCore.register();
    }
    // endregion

}
