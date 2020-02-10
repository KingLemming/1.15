package cofh.thermal.expansion.inventory.container;

import cofh.lib.inventory.InvWrapper;
import cofh.lib.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.thermal.core.tileentity.MachineTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_INSOLATOR_CONTAINER;

public class MachineInsolatorContainer extends TileContainer {

    public final MachineTileBase tile;

    public MachineInsolatorContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_INSOLATOR_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (MachineTileBase) world.getTileEntity(pos);
        IInventory tileInv = new InvWrapper(this.tile.getInventory());

        addSlot(new SlotCoFH(tileInv, 0, 62, 17));
        addSlot(new SlotCoFH(tileInv, 1, 62, 53));
        addSlot(new SlotRemoveOnly(tileInv, 2, 116, 17));
        addSlot(new SlotRemoveOnly(tileInv, 3, 134, 17));
        addSlot(new SlotRemoveOnly(tileInv, 4, 116, 35));
        addSlot(new SlotRemoveOnly(tileInv, 5, 134, 35));
        // addSlot(new SlotEnergy(myTile, myTile.getChargeSlot(), 8, 53));

        bindPlayerInventory(inventory);
    }

}
