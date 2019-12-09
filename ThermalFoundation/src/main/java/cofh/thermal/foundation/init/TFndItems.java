package cofh.thermal.foundation.init;

import static cofh.thermal.core.util.RegistrationHelper.registerGemSet;
import static cofh.thermal.core.util.RegistrationHelper.registerMetalSet;

public class TFndItems {

    private TFndItems() {

    }

    public static void register() {

        registerThermalMetals();
        registerThermalGems();
    }

    private static void registerThermalMetals() {

        registerMetalSet("copper");
        registerMetalSet("tin");
        registerMetalSet("silver");
        registerMetalSet("lead");
        registerMetalSet("nickel");
        registerMetalSet("platinum");

        registerMetalSet("bronze");
        registerMetalSet("electrum");
        registerMetalSet("invar");
        registerMetalSet("constantan");
    }

    private static void registerThermalGems() {

        registerGemSet("ruby");
        registerGemSet("sapphire");
    }

    private static void registerExtraMetals() {

        registerMetalSet("aluminum");
        registerMetalSet("zinc");
        registerMetalSet("titanium");
        registerMetalSet("osmium");
        registerMetalSet("iridium");

        registerMetalSet("steel");
    }

}
