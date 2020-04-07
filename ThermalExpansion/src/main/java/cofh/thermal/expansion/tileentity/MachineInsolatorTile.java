package cofh.thermal.expansion.tileentity;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.tileentity.MachineTileProcess;
import cofh.thermal.expansion.inventory.container.MachineInsolatorContainer;
import cofh.thermal.expansion.util.managers.machine.InsolatorRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_INSOLATOR_TILE;

public class MachineInsolatorTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(InsolatorRecipeManager.instance()::validRecipe);
    protected ItemStorageCoFH catalystSlot = new ItemStorageCoFH(InsolatorRecipeManager.instance()::validCatalyst);

    protected FluidStorageCoFH waterTank = new FluidStorageCoFH(TANK_SMALL, FluidHelper.IS_WATER);

    public MachineInsolatorTile() {

        super(MACHINE_INSOLATOR_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(catalystSlot, CATALYST);
        inventory.addSlots(OUTPUT, 4);

        tankInv.addTank(waterTank, INPUT);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = InsolatorRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
            fluidInputCounts = curRecipe.getInputFluidCounts(this);
        }
        return curRecipe != null;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineInsolatorContainer(i, world, pos, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0) && (fluidInputCounts.isEmpty() || waterTank.getAmount() >= fluidInputCounts.get(0));
    }
    // endregion
}
