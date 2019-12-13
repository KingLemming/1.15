package cofh.thermal.expansion.init;

import cofh.core.util.ProxyUtils;
import cofh.thermal.expansion.block.tutorial.FirstBlock;
import cofh.thermal.expansion.block.tutorial.FirstBlockContainer;
import cofh.thermal.expansion.block.tutorial.FirstBlockTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static cofh.thermal.expansion.init.TExpReferences.FIRSTBLOCK;

public class TExpBlocks {

    private TExpBlocks() {

    }

    public static void register() {

        registerBlock("firstblock", () -> new FirstBlock());
        // registerBlock("fancyblock", () -> new FancyBlock());

        TILE_ENTITIES.register("firstblock", () -> TileEntityType.Builder.create(FirstBlockTile::new, FIRSTBLOCK).build(null));
        // TILE_ENTITIES.register("fancyblock", () -> TileEntityType.Builder.create(FirstBlockTile::new, BLOCKS.get("fancyblock")).build(null));

        CONTAINERS.register("firstblock", () -> IForgeContainerType.create((windowId, inv, data) -> new FirstBlockContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

}
