package cofh.thermal.core.util.recipes;

public class SimpleCatalyst implements IRecipeCatalyst {

    protected final float primaryMod;
    protected final float secondaryMod;
    protected final float energyMod;
    protected final float minChance;
    protected final float useChance;

    public SimpleCatalyst(float primaryMod, float secondaryMod, float energyMod, float minChance, float useChance) {

        this.primaryMod = primaryMod;
        this.secondaryMod = secondaryMod;
        this.energyMod = energyMod;
        this.minChance = minChance;
        this.useChance = useChance;
    }

    public float getPrimaryMod() {

        return primaryMod;
    }

    public float getSecondaryMod() {

        return secondaryMod;
    }

    public float getEnergyMod() {

        return energyMod;
    }

    public float getMinChance() {

        return minChance;
    }

    public float getUseChance() {

        return useChance;
    }

}
