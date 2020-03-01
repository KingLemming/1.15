package cofh.core.init;

import cofh.core.block.EnderAirBlock;
import cofh.core.block.GlossedMagmaBlock;
import cofh.core.block.GlowAirBlock;
import cofh.core.block.SignalAirBlock;
import cofh.core.tileentity.EnderAirTile;
import cofh.core.tileentity.GlowAirTile;
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
        BLOCKS.register(ID_GLOW_AIR, () -> new GlowAirBlock(from(Blocks.AIR).lightValue(15)));
        BLOCKS.register(ID_ENDER_AIR, () -> new EnderAirBlock(from(Blocks.AIR).lightValue(3)));

        TILE_ENTITIES.register(ID_SIGNAL_AIR, () -> TileEntityType.Builder.create(SignalAirTile::new, SIGNAL_AIR).build(null));
        TILE_ENTITIES.register(ID_GLOW_AIR, () -> TileEntityType.Builder.create(GlowAirTile::new, GLOW_AIR).build(null));
        TILE_ENTITIES.register(ID_ENDER_AIR, () -> TileEntityType.Builder.create(EnderAirTile::new, ENDER_AIR).build(null));
    }

}
