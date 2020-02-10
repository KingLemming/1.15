package cofh.thermal.expansion.util.managers.machine;

import cofh.thermal.core.util.managers.SimpleItemRecipeManager;

public class SawmillRecipeManager extends SimpleItemRecipeManager {

    private static final SawmillRecipeManager INSTANCE = new SawmillRecipeManager();
    protected static final int DEFAULT_ENERGY = 2000;
    protected static float logMultiplier = 1.5F;
    protected static boolean defaultLogRecipes = true;

    public static SawmillRecipeManager instance() {

        return INSTANCE;
    }

    private SawmillRecipeManager() {

        super(DEFAULT_ENERGY, 4, 0);
    }

    @Override
    public void config() {

    }

}
