package cofh.lib.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class BlockCoFH extends Block {

    protected int minXP = 0;
    protected int maxXP = 0;

    public BlockCoFH(Properties properties) {

        super(properties);
    }

    public BlockCoFH setXPDrop(int minXP, int maxXP) {

        this.minXP = minXP;
        this.maxXP = maxXP;
        return this;
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {

        if (maxXP <= 0 || silktouch > 0) {
            return 0;
        }
        return MathHelper.nextInt(RANDOM, minXP, maxXP);
    }

}
