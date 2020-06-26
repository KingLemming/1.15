package cofh.thermal.core.tileentity.workbench;

import cofh.thermal.core.inventory.container.workbench.ChargeBenchContainer;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.thermal.core.init.TCoreReferences.CHARGE_BENCH_TILE;

public class ChargeBenchTile extends ThermalTileBase {

    public ChargeBenchTile() {

        super(CHARGE_BENCH_TILE);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new ChargeBenchContainer(i, world, pos, inventory, player);
    }

}
