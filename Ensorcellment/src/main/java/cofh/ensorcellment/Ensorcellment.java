package cofh.ensorcellment;

import cofh.ensorcellment.event.ClientEventsEnsorc;
import cofh.ensorcellment.event.CommonEventsEnsorc;
import cofh.ensorcellment.init.ConfigEnsorc;
import cofh.ensorcellment.init.EnchantmentsEnsorc;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Ensorcellment.MOD_ID)
public class Ensorcellment {

    public static final String MOD_ID = "ensorcellment";
    public static final String MOD_NAME = "Ensorcellment";
    public static final String VERSION = "1.0";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);

    public Ensorcellment() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        //        modEventBus.addListener(this::enqueueIMC);
        //        modEventBus.addListener(this::processIMC);

        modEventBus.addGenericListener(Enchantment.class, this::registerEnchantments);
        modEventBus.addGenericListener(Potion.class, this::registerPotions);

        EnchantmentsEnsorc.createEnchantments();

        ConfigEnsorc.register();
        ConfigEnsorc.genConfigs();
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        CommonEventsEnsorc.register();
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ClientEventsEnsorc.register();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }
    // endregion

    // region REGISTRATION
    private void registerEnchantments(final RegistryEvent.Register<Enchantment> event) {

        EnchantmentsEnsorc.registerEnchantments(event);
    }

    private void registerPotions(final RegistryEvent.Register<Potion> event) {

    }
    // endregion
}
