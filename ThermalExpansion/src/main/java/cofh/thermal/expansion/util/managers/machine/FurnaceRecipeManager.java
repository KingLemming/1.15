package cofh.thermal.expansion.util.managers.machine;

import cofh.thermal.core.util.managers.SimpleItemRecipeManager;

public class FurnaceRecipeManager extends SimpleItemRecipeManager {

    private static final FurnaceRecipeManager INSTANCE = new FurnaceRecipeManager();
    protected static final int DEFAULT_ENERGY = 2000;
    protected static boolean defaultFurnaceRecipes = true;
    protected static boolean defaultDustRecipes = true;
    protected static boolean defaultFoodRecipes = true;

    public static FurnaceRecipeManager instance() {

        return INSTANCE;
    }

    private FurnaceRecipeManager() {

        super(DEFAULT_ENERGY, 1, 0);
    }

    // region IManager
    @Override
    public void config() {

        //        String category = "Machines.Furnace";
        //        String comment;
        //
        //        comment = "Adjust this value to change the default energy value for this machine's recipes.";
        //        int defaultEnergy = config.getInt("Default Energy", category, DEFAULT_ENERGY, DEFAULT_ENERGY_MIN, DEFAULT_ENERGY_MAX, comment);
        //
        //        comment = "Adjust this value to change the relative energy cost of all of this machine's recipes. Scale is in percentage.";
        //        int scaleFactor = config.getInt("Energy Factor", category, DEFAULT_SCALE, DEFAULT_SCALE_MIN, DEFAULT_SCALE_MAX, comment);
        //
        //        comment = "If TRUE, default Minecraft Furnace recipes will be available for this machine.";
        //        defaultFurnaceRecipes = config.getBoolean("Default Furnace Recipes", category, defaultFurnaceRecipes, comment);
        //
        //        comment = "If TRUE, reduced cost Dust -> Ingot recipes will be created.";
        //        defaultDustRecipes = config.getBoolean("Reduced Dust -> Ingot Cost", category, defaultDustRecipes, comment);
        //
        //        comment = "If TRUE, reduced cost Food recipes will be created.";
        //        defaultFoodRecipes = config.getBoolean("Reduced Food Cost", category, defaultFoodRecipes, comment);
        //
        //        setDefaultEnergy(defaultEnergy);
        //        setScaleFactor(scaleFactor);
    }

    @Override
    public void initialize() {

        if (defaultFurnaceRecipes) {
            //            Map<ItemStack, ItemStack> smeltingList = FurnaceRecipes.instance().getSmeltingList();
            //            ItemStack output;
            //            int energy;
            //
            //            for (ItemStack key : smeltingList.keySet()) {
            //                if (key.isEmpty() || validRecipe(key)) {
            //                    continue;
            //                }
            //                output = smeltingList.get(key);
            //                if (hasPreferredOre(getOreName(output))) {
            //                    output = cloneStack(getPreferredOre(getOreName(output)), output.getCount());
            //                }
            //                energy = defaultEnergy;
            //
            //                if (defaultFoodRecipes && output.getItem() instanceof ItemFood) {
            //                    energy /= 2;
            //                }
            //                if (defaultDustRecipes && hasOrePrefix(key, PREFIX_DUST) && hasOrePrefix(output, PREFIX_INGOT)) {
            //                    addRecipe(energy * 3 / 4, key, output);
            //                } else {
            //                    if (getItemDamage(key) == OreDictionary.WILDCARD_VALUE) {
            //                        ItemStack testKey = cloneStack(key);
            //                        testKey.setItemDamage(0);
            //                        if (hasOreName(testKey) && defaultValidator.validate(getOreName(testKey))) {
            //                            addRecipe(energy, testKey, output);
            //                            continue;
            //                        }
            //                    }
            //                    addRecipe(energy, key, output);
            //                }
            //            }
        }
    }
    // endregion
}
