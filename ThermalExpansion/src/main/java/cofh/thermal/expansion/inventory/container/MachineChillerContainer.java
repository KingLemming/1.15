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

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CHILLER_CONTAINER;

public class MachineChillerContainer extends TileContainer {

    public final MachineTileBase tile;

    public MachineChillerContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_CHILLER_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (MachineTileBase) world.getTileEntity(pos);
        IInventory tileInv = new InvWrapper(this.tile.getInventory());

        addSlot(new SlotCoFH(tileInv, 0, 62, 26));
        addSlot(new SlotRemoveOnly(tileInv, 1, 125, 35));

        bindPlayerInventory(inventory);
    }

}
