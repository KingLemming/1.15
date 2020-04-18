package cofh.thermal.essentials.inventory.container;

import cofh.lib.inventory.InvWrapper;
import cofh.lib.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.thermal.core.tileentity.MachineTileBasic;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.essentials.init.TEssReferences.BASIC_CRUCIBLE_CONTAINER;

public class BasicCrucibleContainer extends TileContainer {

    public final MachineTileBasic tile;

    public BasicCrucibleContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(BASIC_CRUCIBLE_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (MachineTileBasic) world.getTileEntity(pos);
        IInventory tileInv = new InvWrapper(this.tile.getInventory());

        addSlot(new SlotCoFH(tileInv, 0, 53, 26));

        addSlot(new SlotCoFH(tileInv, 1, 8, 53));

        bindPlayerInventory(inventory);
    }

}
