package cofh.thermal.core.init;

import cofh.thermal.core.ThermalCore;
import cofh.thermal.core.util.managers.IManager;
import net.minecraft.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.List;

public class ThermalRecipeManager {

    private static final ThermalRecipeManager INSTANCE = new ThermalRecipeManager();

    private RecipeManager serverRecipeManager;
    private final List<IManager> managers = new ArrayList<>();

    public static ThermalRecipeManager instance() {

        return INSTANCE;
    }

    private ThermalRecipeManager() {

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
