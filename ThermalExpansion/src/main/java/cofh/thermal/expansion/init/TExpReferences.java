package cofh.thermal.expansion.init;

import cofh.thermal.expansion.block.tutorial.*;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class TExpReferences {

    @ObjectHolder("thermal:firstblock")
    public static FirstBlock FIRSTBLOCK;

    @ObjectHolder("thermal:firstblock")
    public static TileEntityType<FirstBlockTile> FIRSTBLOCK_TILE;

    @ObjectHolder("thermal:firstblock")
    public static ContainerType<FirstBlockContainer> FIRSTBLOCK_CONTAINER;

    @ObjectHolder("thermal:fancyblock")
    public static FancyBlock FANCYBLOCK;

    @ObjectHolder("thermal:fancyblock")
    public static TileEntityType<FancyBlockTile> FANCYBLOCK_TILE;

}