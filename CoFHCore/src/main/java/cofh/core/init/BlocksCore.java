package cofh.core.init;

import cofh.core.block.GlossedMagmaBlock;
import net.minecraft.block.Blocks;

import static cofh.core.CoFHCore.BLOCKS;
import static cofh.lib.util.references.CoreReferences.ID_GLOSSED_MAGMA;
import static net.minecraft.block.Block.Properties.from;

public class BlocksCore {

    private BlocksCore() {

    }

    public static void register() {

        BLOCKS.registerSpec(ID_GLOSSED_MAGMA, () -> new GlossedMagmaBlock(from(Blocks.MAGMA_BLOCK).lightValue(6)));
    }

}
