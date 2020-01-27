package cofh.thermal.cultivation.init;

import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.registerAnnual;
import static cofh.thermal.core.util.RegistrationHelper.registerPerennial;

public class TCulBlocks {

    private TCulBlocks() {

    }

    public static void register() {

        registerPlants();
    }

    private static void registerPlants() {

        // ANNUAL
        registerAnnual(ID_BARLEY);
        registerAnnual(ID_ONION);
        registerAnnual(ID_RADISH);
        registerAnnual(ID_RICE);
        registerAnnual(ID_SADIROOT);
        registerAnnual(ID_SPINACH);

        // PERENNIAL
        registerPerennial(ID_BELL_PEPPER);
        registerPerennial(ID_GREEN_BEAN);
        registerPerennial(ID_PEANUT);
        registerPerennial(ID_STRAWBERRY);
        registerPerennial(ID_TOMATO);

        // BREWING
        registerPerennial(ID_COFFEE);
        registerPerennial(ID_TEA);
    }

}
