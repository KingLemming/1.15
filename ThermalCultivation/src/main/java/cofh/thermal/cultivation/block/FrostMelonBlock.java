package cofh.thermal.cultivation.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static cofh.thermal.cultivation.init.TCulReferences.FROST_MELON_STEM;
import static cofh.thermal.cultivation.init.TCulReferences.FROST_MELON_STEM_ATTACHED;

public class FrostMelonBlock extends StemGrownBlock {

    public FrostMelonBlock(Block.Properties builder) {

        super(builder);
    }

    public StemBlock getStem() {

        return (StemBlock) FROST_MELON_STEM;
    }

    public AttachedStemBlock getAttachedStem() {

        return (AttachedStemBlock) FROST_MELON_STEM_ATTACHED;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {

        if (!worldIn.isAreaLoaded(pos, 1)) {
            return;
        }
        BlockPos above = pos.up();
        if (worldIn.isAirBlock(above)) {
            worldIn.setBlockState(above, Blocks.SNOW.getDefaultState(), 2);
        }
    }

}