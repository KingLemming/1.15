package cofh.ensorcellment;

import cofh.ensorcellment.event.ClientEventsEnsorc;
import cofh.ensorcellment.event.CommonEventsEnsorc;
import cofh.ensorcellment.init.ConfigEnsorc;
import cofh.ensorcellment.init.EnchantmentsEnsorc;
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

import static cofh.lib.util.constants.Constants.ID_ENSORCELLMENT;

@Mod(ID_ENSORCELLMENT)
public class Ensorcellment {

    public static final String MOD_NAME = "Ensorcellment";
    public static final String VERSION = "1.0";
    public static final Logger LOG = LogManager.getLogger(ID_ENSORCELLMENT);

    public static final DeferredRegisterCoFH<Enchantment> ENCHANTMENTS = new DeferredRegisterCoFH<>(ForgeRegistries.ENCHANTMENTS, ID_ENSORCELLMENT);

    public Ensorcellment() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        ENCHANTMENTS.register(modEventBus);

        ConfigEnsorc.register();

        EnchantmentsEnsorc.register();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        CommonEventsEnsorc.register();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ClientEventsEnsorc.register();
    }
    // endregion
}
