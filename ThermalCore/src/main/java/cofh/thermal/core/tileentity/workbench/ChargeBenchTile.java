package cofh.thermal.core.tileentity.workbench;

import cofh.lib.energy.EnergyStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.thermal.core.inventory.container.workbench.ChargeBenchContainer;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.ITickableTileEntity;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INTERNAL;
import static cofh.thermal.core.init.TCoreReferences.CHARGE_BENCH_TILE;

public class ChargeBenchTile extends ThermalTileBase implements ITickableTileEntity {

    protected static final int BASE_ENERGY = 400000;

    protected ItemStorageCoFH[] benchSlots = new ItemStorageCoFH[4];
    protected ItemStorageCoFH chargeSlot = new ItemStorageCoFH(EnergyHelper::hasEnergyHandlerCap);

    public ChargeBenchTile() {

        super(CHARGE_BENCH_TILE);

        energyStorage = new EnergyStorageCoFH(BASE_ENERGY);

        for (int i = 0; i < benchSlots.length; ++i) {
            benchSlots[i] = new ItemStorageCoFH(EnergyHelper::hasEnergyHandlerCap);
            inventory.addSlot(benchSlots[i], INTERNAL);
        }
        inventory.addSlot(chargeSlot, INTERNAL);

        addAugmentSlots(4);
        initHandlers();
    }

    @Override
    public void tick() {

    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new ChargeBenchContainer(i, world, pos, inventory, player);
    }

}
