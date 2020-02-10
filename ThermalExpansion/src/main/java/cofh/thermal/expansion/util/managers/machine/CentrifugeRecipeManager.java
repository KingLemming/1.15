package cofh.thermal.expansion.util.managers.machine;

import cofh.thermal.core.util.managers.SimpleItemRecipeManager;

public class CentrifugeRecipeManager extends SimpleItemRecipeManager {

    private static final CentrifugeRecipeManager INSTANCE = new CentrifugeRecipeManager();
    protected static final int DEFAULT_ENERGY = 8000;

    public static CentrifugeRecipeManager instance() {

        return INSTANCE;
    }

    private CentrifugeRecipeManager() {

        super(DEFAULT_ENERGY, 4, 1);
    }

    @Override
    public void config() {

    }

}
