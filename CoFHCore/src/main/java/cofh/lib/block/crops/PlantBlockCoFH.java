package cofh.lib.block.crops;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

import static cofh.lib.util.constants.Constants.AGE;

public class PlantBlockCoFH extends BushBlock {

    protected final PlantType type;
    protected int reqLight = 9;

    protected PlantBlockCoFH(Properties properties) {

        this(properties, PlantType.Crop);
    }

    public PlantBlockCoFH(Properties properties, PlantType type) {

        super(properties);
        this.type = type;
    }

    public PlantBlockCoFH setRequiredLight(int reqLight) {

        this.reqLight = reqLight;
        return this;
    }

    protected IntegerProperty getAgeProperty() {

        return AGE;
    }

    protected int getAge(BlockState state) {

        return state.get(this.getAgeProperty());
    }

    protected int getHarvestAge() {

        return 7;
    }

    protected int getMaximumAge() {

        return 7;
    }

    protected int getPostHarvestAge() {

        return -1;
    }

    public BlockState withAge(int age) {

        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

}
