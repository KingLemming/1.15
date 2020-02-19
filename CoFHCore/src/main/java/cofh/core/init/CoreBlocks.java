package cofh.core.init;

import cofh.core.block.GlossedMagmaBlock;
import cofh.core.block.SignalAirBlock;
import cofh.core.tileentity.SignalAirTile;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntityType;

import static cofh.core.CoFHCore.BLOCKS;
import static cofh.core.CoFHCore.TILE_ENTITIES;
import static cofh.lib.util.references.CoreReferences.*;
import static net.minecraft.block.Block.Properties.from;

public class CoreBlocks {

    private CoreBlocks() {

    }

    public static void register() {

        BLOCKS.register(ID_GLOSSED_MAGMA, () -> new GlossedMagmaBlock(from(Blocks.MAGMA_BLOCK).lightValue(6)));

        BLOCKS.register(ID_SIGNAL_AIR, () -> new SignalAirBlock(from(Blocks.AIR).lightValue(7)));

        TILE_ENTITIES.register(ID_SIGNAL_AIR, () -> TileEntityType.Builder.create(SignalAirTile::new, SIGNAL_AIR).build(null));
    }

}
