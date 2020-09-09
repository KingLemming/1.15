package cofh.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class HardenedGlassBlock extends GlassBlock implements IDismantleable {

    public HardenedGlassBlock(Properties properties) {

        super(properties);
    }

    @Override
    public boolean canEntityDestroy(BlockState state, IBlockReader world, BlockPos pos, Entity entity) {

        return false;
    }

}
