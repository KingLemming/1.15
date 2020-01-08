package cofh.cofh_potions;

import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_COFH_POTIONS;

@Mod(ID_COFH_POTIONS)
public class CoFHPotions {

    public static final Logger LOG = LogManager.getLogger(ID_COFH_POTIONS);

    public static final DeferredRegisterCoFH<Potion> POTIONS = new DeferredRegisterCoFH<>(ForgeRegistries.POTION_TYPES, ID_COFH_POTIONS);

    public CoFHPotions() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        POTIONS.register(modEventBus);
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }
    // endregion

}
