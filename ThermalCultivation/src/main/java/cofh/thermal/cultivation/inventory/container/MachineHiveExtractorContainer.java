package cofh.thermal.cultivation.inventory.container;

import cofh.lib.inventory.InvWrapper;
import cofh.lib.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.cultivation.init.TCulReferences.MACHINE_BEE_EXTRACTOR_CONTAINER;

public class MachineHiveExtractorContainer extends TileContainer {

    public final ThermalTileBase tile;

    public MachineHiveExtractorContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_BEE_EXTRACTOR_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (ThermalTileBase) world.getTileEntity(pos);
        IInventory tileInv = new InvWrapper(this.tile.getInventory());

        addSlot(new SlotRemoveOnly(tileInv, 0, 44, 35));

        bindPlayerInventory(inventory);
    }

}
