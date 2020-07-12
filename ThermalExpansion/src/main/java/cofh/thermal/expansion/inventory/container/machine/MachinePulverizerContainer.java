package cofh.thermal.expansion.inventory.container.machine;

import cofh.lib.inventory.InvWrapperCoFH;
import cofh.lib.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.thermal.core.tileentity.ReconfigurableTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PULVERIZER_CONTAINER;

public class MachinePulverizerContainer extends TileContainer {

    public final ReconfigurableTileBase tile;

    public MachinePulverizerContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_PULVERIZER_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (ReconfigurableTileBase) world.getTileEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 44, 17));

        addSlot(new SlotCoFH(tileInv, 1, 44, 53));

        addSlot(new SlotRemoveOnly(tileInv, 2, 107, 26));
        addSlot(new SlotRemoveOnly(tileInv, 3, 125, 26));
        addSlot(new SlotRemoveOnly(tileInv, 4, 107, 44));
        addSlot(new SlotRemoveOnly(tileInv, 5, 125, 44));

        addSlot(new SlotCoFH(tileInv, 6, 8, 53));

        bindAugmentSlots(tileInv, 7, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
