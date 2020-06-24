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

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PRESS_CONTAINER;

public class MachinePressContainer extends TileContainer {

    public final MachineTileReconfigurable tile;

    public MachinePressContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_PRESS_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (MachineTileReconfigurable) world.getTileEntity(pos);
        IInventory tileInv = new InvWrapper(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 53, 17));
        addSlot(new SlotCoFH(tileInv, 1, 53, 53));

        addSlot(new SlotRemoveOnly(tileInv, 2, 116, 35));

        addSlot(new SlotCoFH(tileInv, 3, 8, 53));

        bindAugmentSlots(tileInv, 4, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
