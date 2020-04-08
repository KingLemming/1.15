package cofh.thermal.expansion.inventory.container;

import cofh.lib.inventory.InvWrapper;
import cofh.lib.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.thermal.core.tileentity.MachineTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_FURNACE_CONTAINER;

public class MachineFurnaceContainer extends TileContainer {

    public final MachineTileBase tile;

    public MachineFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_FURNACE_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (MachineTileBase) world.getTileEntity(pos);
        IInventory tileInv = new InvWrapper(this.tile.getInventory());

        addSlot(new SlotCoFH(tileInv, 0, 53, 26));
        addSlot(new FurnaceResultSlot(player, tileInv, 1, 116, 35));

        bindPlayerInventory(inventory);
    }

}
