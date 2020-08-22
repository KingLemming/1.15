package cofh.thermal.cultivation.tileentity;

import cofh.lib.energy.EnergyStorageCoFH;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

import static cofh.thermal.core.common.ThermalConfig.deviceAugments;

public class DeviceSoilChargerTile extends ThermalTileBase implements ITickableTileEntity {

    public DeviceSoilChargerTile(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
        energyStorage = new EnergyStorageCoFH(getBaseEnergyStorage(), getBaseEnergyXfer());

        addAugmentSlots(deviceAugments);
        initHandlers();
    }

    @Override
    public void tick() {

    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return null;
    }

}
