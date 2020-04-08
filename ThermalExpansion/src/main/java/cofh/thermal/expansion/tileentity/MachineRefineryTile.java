package cofh.thermal.expansion.tileentity;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.tileentity.MachineTileProcess;
import cofh.thermal.expansion.inventory.container.MachineRefineryContainer;
import cofh.thermal.expansion.util.managers.machine.RefineryRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.lib.util.StorageGroup.OUTPUT;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_REFINERY_TILE;

public class MachineRefineryTile extends MachineTileProcess {

    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_SMALL, RefineryRecipeManager.instance()::validRecipe);
    protected FluidStorageCoFH outputTankA = new FluidStorageCoFH(TANK_MEDIUM);
    protected FluidStorageCoFH outputTankB = new FluidStorageCoFH(TANK_MEDIUM);

    public MachineRefineryTile() {

        super(MACHINE_REFINERY_TILE);

        inventory.addSlot(outputSlot, OUTPUT);
        tankInv.addTank(inputTank, INPUT);
        tankInv.addTank(outputTankA, OUTPUT);
        tankInv.addTank(outputTankB, OUTPUT);

        renderFluid = new FluidStack(Fluids.WATER, FluidAttributes.BUCKET_VOLUME);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = RefineryRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            fluidInputCounts = curRecipe.getInputFluidCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected boolean cacheRenderFluid() {

        if (curRecipe == null) {
            return false;
        }
        if (inputTank.isEmpty()) {
            // This should definitely never happen, but who knows.
            return false;
        }
        FluidStack prevFluid = renderFluid;
        renderFluid = new FluidStack(inputTank.getFluidStack(), inputTank.isEmpty() ? 0 : FluidAttributes.BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineRefineryContainer(i, world, pos, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputTank.getAmount() >= fluidInputCounts.get(0);
    }
    // endregion
}
