package cofh.thermal.core.init;

import cofh.thermal.core.util.managers.device.TreeExtractorManager;

import static cofh.thermal.core.common.ThermalRecipeManagers.registerManager;

public class TCoreRecipeManagers {

    private TCoreRecipeManagers() {

    }

    public static void register() {

        registerManager(TreeExtractorManager.instance());
    }

}
