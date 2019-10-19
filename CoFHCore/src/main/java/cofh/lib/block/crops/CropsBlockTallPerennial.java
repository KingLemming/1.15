package cofh.lib.block.crops;

import net.minecraft.state.IntegerProperty;
import net.minecraftforge.common.PlantType;

import static cofh.lib.util.constants.Constants.AGE_TALL_PERENNIAL;

public class CropsBlockTallPerennial extends CropsBlockTall {

    public CropsBlockTallPerennial(Properties properties, PlantType type, int growLight, float growMod) {

        super(properties, type, growLight, growMod);
    }

    public CropsBlockTallPerennial(Properties properties, int growLight, float growMod) {

        super(properties, growLight, growMod);
    }

    public CropsBlockTallPerennial(Properties properties) {

        super(properties);
    }

    @Override
    public IntegerProperty getAgeProperty() {

        return AGE_TALL_PERENNIAL;
    }

    @Override
    public int getMaximumAge() {

        return 9;
    }

    @Override
    public int getPostHarvestAge() {

        return 8;
    }

}
