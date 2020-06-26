package cofh.thermal.core.tileentity.workbench;

import cofh.thermal.core.inventory.container.workbench.TinkerBenchContainer;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.thermal.core.init.TCoreReferences.TINKER_BENCH_TILE;

public class TinkerBenchTile extends ThermalTileBase {

    public TinkerBenchTile() {

        super(TINKER_BENCH_TILE);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new TinkerBenchContainer(i, world, pos, inventory, player);
    }

}
