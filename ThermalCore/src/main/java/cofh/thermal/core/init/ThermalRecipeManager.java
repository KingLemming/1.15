package cofh.thermal.core.init;

import cofh.thermal.core.util.managers.IManager;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ThermalRecipeManager implements IManager {

    public static final ThermalRecipeManager SERVER = new ThermalRecipeManager();
    public static final ThermalRecipeManager CLIENT = new ThermalRecipeManager();

    private final List<IManager> managers = new ArrayList<>();

    private RecipeManager recipeManager;

    public static ThermalRecipeManager instance(@Nullable World world) {

        return world != null && world.isRemote ? CLIENT : SERVER;
    }

    private ThermalRecipeManager() {

    }

    public void setRecipeManager(RecipeManager recipeManager) {

        this.recipeManager = recipeManager;
    }

    public static void register(IManager manager) {

        if (!SERVER.managers.contains(manager)) {
            SERVER.managers.add(manager);
        }
        if (!CLIENT.managers.contains(manager)) {
            CLIENT.managers.add(manager);
        }
    }

    public void refresh() {

        refresh(recipeManager);
    }

    // region IManager
    @Override
    public void config() {

        for (IManager sub : managers) {
            sub.config();
        }
    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        if (recipeManager == null) {
        }
        for (IManager sub : managers) {
            sub.refresh(recipeManager);
        }
    }
    // endregion
}
