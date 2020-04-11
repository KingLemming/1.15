package cofh.thermal.expansion.inventory.container.dynamo;

import cofh.lib.inventory.container.TileContainer;
import cofh.thermal.core.tileentity.DynamoTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_COMPRESSION_CONTAINER;

public class DynamoCompressionContainer extends TileContainer {

    public final DynamoTileBase tile;

    public DynamoCompressionContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(DYNAMO_COMPRESSION_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (DynamoTileBase) world.getTileEntity(pos);

        bindPlayerInventory(inventory);
    }

}
