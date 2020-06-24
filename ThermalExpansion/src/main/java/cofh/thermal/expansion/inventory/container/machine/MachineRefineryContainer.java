package cofh.thermal.expansion.inventory.container.machine;

import cofh.lib.inventory.InvWrapper;
import cofh.lib.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.thermal.core.tileentity.MachineTileReconfigurable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_REFINERY_CONTAINER;

public class MachineRefineryContainer extends TileContainer {

    public final MachineTileReconfigurable tile;

    public MachineRefineryContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_REFINERY_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (MachineTileReconfigurable) world.getTileEntity(pos);
        IInventory tileInv = new InvWrapper(this.tile.getItemInv());

        addSlot(new SlotRemoveOnly(tileInv, 0, 107, 35));

        addSlot(new SlotCoFH(tileInv, 1, 8, 53));

        bindAugmentSlots(tileInv, 2, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
