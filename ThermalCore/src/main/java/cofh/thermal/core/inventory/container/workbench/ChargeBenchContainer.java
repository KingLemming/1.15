package cofh.thermal.core.inventory.container.workbench;

import cofh.core.inventory.InvWrapperCoFH;
import cofh.core.inventory.container.TileContainer;
import cofh.thermal.core.tileentity.workbench.ChargeBenchTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.core.init.TCoreReferences.CHARGE_BENCH_CONTAINER;

public class ChargeBenchContainer extends TileContainer {

    public final ChargeBenchTile tile;

    public ChargeBenchContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(CHARGE_BENCH_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (ChargeBenchTile) world.getTileEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        bindAugmentSlots(tileInv, 3, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
