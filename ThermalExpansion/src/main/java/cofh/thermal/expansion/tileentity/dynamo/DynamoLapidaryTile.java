package cofh.thermal.expansion.tileentity.dynamo;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.tileentity.DynamoTileBase;
import cofh.thermal.core.util.managers.dynamo.LapidaryFuelManager;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoLapidaryContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_LAPIDARY_TILE;

public class DynamoLapidaryTile extends DynamoTileBase {

    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(LapidaryFuelManager.instance()::validFuel);

    public DynamoLapidaryTile() {

        super(DYNAMO_LAPIDARY_TILE);

        inventory.addSlot(fuelSlot, INPUT);
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return LapidaryFuelManager.instance().getEnergy(fuelSlot.getItemStack()) > 0;
    }

    @Override
    protected void processStart() {

        fuel += fuelMax = LapidaryFuelManager.instance().getEnergy(fuelSlot.getItemStack());
        fuelSlot.consume();
    }
    // endregion

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DynamoLapidaryContainer(i, world, pos, inventory, player);
    }

}
