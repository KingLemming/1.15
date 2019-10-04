package cofh.core.init;

import cofh.core.block.CooledMagmaBlock;
import net.minecraft.block.Blocks;

import static cofh.core.CoFHCore.BLOCKS;
import static cofh.lib.util.modhelpers.CoreHelper.ID_COOLED_MAGMA;
import static net.minecraft.block.Block.Properties.from;

public class BlocksCore {

    private BlocksCore() {

    }

    public static void register() {

        BLOCKS.registerSpec(ID_COOLED_MAGMA, () -> new CooledMagmaBlock(from(Blocks.MAGMA_BLOCK).lightValue(6)));
    }

}
