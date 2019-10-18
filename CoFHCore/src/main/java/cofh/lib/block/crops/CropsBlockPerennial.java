package cofh.lib.block.crops;

import net.minecraft.state.IntegerProperty;
import net.minecraftforge.common.PlantType;

import static cofh.lib.util.constants.Constants.AGE_PERENNIAL;

public class CropsBlockPerennial extends CropsBlockCoFH {

    public CropsBlockPerennial(Properties properties, PlantType type, int growLight) {

        super(properties, type, growLight);
    }

    public CropsBlockPerennial(Properties properties) {

        super(properties);
    }

    @Override
    public IntegerProperty getAgeProperty() {

        return AGE_PERENNIAL;
    }

    @Override
    protected int getMaximumAge() {

        return 10;
    }

    @Override
    protected int getPostHarvestAge() {

        return 8;
    }

}
