package cofh.lib.block;

import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import static cofh.lib.util.helpers.MathHelper.RANDOM;

public interface IExpDrop {

    int getMinXP();

    int getMaxXP();

    default int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {

        if (getMaxXP() <= 0 || silktouch > 0) {
            return 0;
        }
        return MathHelper.nextInt(RANDOM, getMinXP(), getMaxXP());
    }

}
