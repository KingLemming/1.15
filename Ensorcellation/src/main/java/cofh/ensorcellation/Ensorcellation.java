package cofh.ensorcellation;

import cofh.core.init.CoreItems;
import cofh.ensorcellation.event.*;
import cofh.ensorcellation.init.ModConfig;
import cofh.ensorcellation.init.ModEnchantments;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_ENSORCELLATION;

@Mod(ID_ENSORCELLATION)
public class Ensorcellation {

    public static final Logger LOG = LogManager.getLogger(ID_ENSORCELLATION);

    public static final DeferredRegisterCoFH<Enchantment> ENCHANTMENTS = new DeferredRegisterCoFH<>(ForgeRegistries.ENCHANTMENTS, ID_ENSORCELLATION);

    public Ensorcellation() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        ENCHANTMENTS.register(modEventBus);


        ModConfig.register();

        ModEnchantments.register();

        CoreItems.registerHorseArmorOverrides();
        CoreItems.registerShieldOverride();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        CommonEvents.register();

        HorseEnchEvents.register();
        ShieldEnchEvents.register();
        PreservationEvents.register();
        SoulboundEvents.register();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ShieldEnchClientEvents.register();
    }
    // endregion
}
