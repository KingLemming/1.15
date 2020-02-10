package cofh.thermal.expansion.util.managers.machine;

import cofh.thermal.core.util.managers.SimpleItemRecipeManager;

public class PulverizerRecipeManager extends SimpleItemRecipeManager.Catalyzed {

    private static final PulverizerRecipeManager INSTANCE = new PulverizerRecipeManager();
    protected static final int DEFAULT_ENERGY = 4000;
    protected static float oreMultiplier = 2.0F;
    protected static boolean defaultOreRecipes = true;
    protected static boolean defaultIngotRecipes = true;

    public static PulverizerRecipeManager instance() {

        return INSTANCE;
    }

    private PulverizerRecipeManager() {

        super(DEFAULT_ENERGY, 4, 0);
    }

    @Override
    public void config() {

    }

}
