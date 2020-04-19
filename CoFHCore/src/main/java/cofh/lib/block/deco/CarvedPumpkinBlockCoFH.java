package cofh.lib.block.deco;

import net.minecraft.block.Block;
import net.minecraft.block.CarvedPumpkinBlock;

import java.util.function.Supplier;

public class CarvedPumpkinBlockCoFH extends CarvedPumpkinBlock {

    protected Supplier<Block> carve;

    /**
     * This ensures that the predicate check isn't stupid. Can't do this for other hardcoded cases unfortunately.
     */
    public static void updateTest() {

        IS_PUMPKIN = (state) -> state != null && (state.getBlock() instanceof CarvedPumpkinBlockCoFH);
    }

    protected CarvedPumpkinBlockCoFH(Properties properties) {

        super(properties);
    }

    protected CarvedPumpkinBlockCoFH setCarve(Supplier<Block> carve) {

        this.carve = carve;
        return this;
    }

}
