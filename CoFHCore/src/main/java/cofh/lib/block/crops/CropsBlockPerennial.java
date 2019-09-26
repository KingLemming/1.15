package cofh.lib.block.crops;

import net.minecraft.state.IntegerProperty;
import net.minecraftforge.common.PlantType;

import static cofh.lib.util.constants.Constants.AGE_PERENNIAL;

public class CropsBlockPerennial extends CropsBlockCoFH {

    public CropsBlockPerennial(Properties properties) {

        super(properties);
    }

    public CropsBlockPerennial(Properties properties, PlantType type) {

        super(properties, type);
    }

    @Override
    protected IntegerProperty getAgeProperty() {

        return AGE_PERENNIAL;
    }

    @Override
    public int getMaximumAge() {

        return 10;
    }

    @Override
    public int getPostHarvestAge() {

        return 8;
    }

}
