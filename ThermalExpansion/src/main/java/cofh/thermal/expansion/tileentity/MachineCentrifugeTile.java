package cofh.thermal.expansion.tileentity;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.tileentity.MachineTileProcess;
import cofh.thermal.expansion.inventory.container.MachineCentrifugeContainer;
import cofh.thermal.expansion.util.managers.machine.CentrifugeRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.lib.util.StorageGroup.OUTPUT;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CENTRIFUGE_TILE;

public class MachineCentrifugeTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(CentrifugeRecipeManager.instance()::validRecipe);
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_SMALL);

    public MachineCentrifugeTile() {

        super(MACHINE_CENTRIFUGE_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlots(OUTPUT, 4);

        tankInv.addTank(outputTank, OUTPUT);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = CentrifugeRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineCentrifugeContainer(i, world, pos, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0);
    }
    // endregion
}
