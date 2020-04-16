package cofh.thermal.essentials.inventory.container;

import cofh.lib.inventory.InvWrapper;
import cofh.lib.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.thermal.core.tileentity.MachineTileBasic;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.essentials.init.TEssReferences.BASIC_SAWMILL_CONTAINER;

public class BasicSawmillContainer extends TileContainer {

    public final MachineTileBasic tile;

    public BasicSawmillContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(BASIC_SAWMILL_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (MachineTileBasic) world.getTileEntity(pos);
        IInventory tileInv = new InvWrapper(this.tile.getInventory());

        addSlot(new SlotCoFH(tileInv, 0, 44, 26));

        addSlot(new SlotRemoveOnly(tileInv, 1, 107, 26));
        addSlot(new SlotRemoveOnly(tileInv, 2, 125, 26));
        addSlot(new SlotRemoveOnly(tileInv, 3, 107, 44));
        addSlot(new SlotRemoveOnly(tileInv, 4, 125, 44));

        addSlot(new SlotCoFH(tileInv, 5, 8, 53));

        bindPlayerInventory(inventory);
    }

}
