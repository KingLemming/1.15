package cofh.lib.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

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

        if (maxXP <= 0) {
            return 0;
        }
        Random rand = world instanceof World ? ((World) world).rand : RANDOM;
        return MathHelper.nextInt(rand, minXP, maxXP);
    }

}
