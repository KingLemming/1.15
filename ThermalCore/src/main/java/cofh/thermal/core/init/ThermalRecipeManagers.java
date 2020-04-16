package cofh.thermal.core.init;

import cofh.thermal.core.ThermalCore;
import cofh.thermal.core.util.managers.IManager;
import cofh.thermal.core.util.managers.dynamo.*;
import cofh.thermal.core.util.managers.machine.*;
import net.minecraft.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.List;

public class ThermalRecipeManagers {

    private static final ThermalRecipeManagers INSTANCE = new ThermalRecipeManagers();

    private RecipeManager serverRecipeManager;
    private final List<IManager> managers = new ArrayList<>();

    public static ThermalRecipeManagers instance() {

        return INSTANCE;
    }

    private ThermalRecipeManagers() {

    }

    public static void register() {

        register(FurnaceRecipeManager.instance());
        register(SawmillRecipeManager.instance());
        register(PulverizerRecipeManager.instance());
        register(InsolatorRecipeManager.instance());
        register(CentrifugeRecipeManager.instance());
        register(PressRecipeManager.instance());
        register(CrucibleRecipeManager.instance());
        register(ChillerRecipeManager.instance());
        register(RefineryRecipeManager.instance());
        register(BrewerRecipeManager.instance());
        register(BottlerRecipeManager.instance());

        register(StirlingFuelManager.instance());
        register(CompressionFuelManager.instance());
        register(MagmaticFuelManager.instance());
        register(NumismaticFuelManager.instance());
        register(LapidaryFuelManager.instance());
    }

    public void setServerRecipeManager(RecipeManager recipeManager) {

        this.serverRecipeManager = recipeManager;
    }

    public static void register(IManager manager) {

        if (!instance().managers.contains(manager)) {
            instance().managers.add(manager);
        }
    }

    public void config() {

        for (IManager sub : managers) {
            sub.config();
        }
    }

    public void refreshServer() {

        if (this.serverRecipeManager == null) {
            ThermalCore.LOG.error("The server's Recipe Manager is null! This is REALLY BAD and will prevent recipes from registering. Check your modpack and configs.");
            return;
        }
        for (IManager sub : managers) {
            sub.refresh(this.serverRecipeManager);
        }
    }

    public void refreshClient(RecipeManager recipeManager) {

        if (this.serverRecipeManager != null) {
            return;
        }
        if (recipeManager == null) {
            ThermalCore.LOG.error("The client's Recipe Manager is null! This is REALLY BAD and will prevent recipes from registering. Check your modpack and configs.");
            return;
        }
        for (IManager sub : managers) {
            sub.refresh(recipeManager);
        }
    }
    // endregion
}
