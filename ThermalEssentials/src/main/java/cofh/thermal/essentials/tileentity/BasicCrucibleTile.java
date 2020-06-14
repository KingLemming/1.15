package cofh.thermal.essentials.tileentity;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.tileentity.MachineTileBasicProcess;
import cofh.thermal.expansion.util.managers.machine.CrucibleRecipeManager;
import cofh.thermal.essentials.inventory.container.BasicCrucibleContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.thermal.essentials.init.TEssReferences.BASIC_CRUCIBLE_TILE;

public class BasicCrucibleTile extends MachineTileBasicProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(CrucibleRecipeManager.instance()::validRecipe);
    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(EnergyHelper::validFurnaceFuel);
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_MEDIUM);

    public BasicCrucibleTile() {

        super(BASIC_CRUCIBLE_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(fuelSlot, CATALYST);

        tankInv.addTank(outputTank, OUTPUT);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = CrucibleRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected boolean cacheRenderFluid() {

        if (curRecipe == null) {
            return false;
        }
        FluidStack prevFluid = renderFluid;
        renderFluid = new FluidStack(curRecipe.getOutputFluids(this).get(0), FluidAttributes.BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new BasicCrucibleContainer(i, world, pos, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0);
    }

    @Override
    protected boolean validateOutputs() {

        if (outputTank.isEmpty()) {
            return true;
        }
        FluidStack output = outputTank.getFluidStack();
        FluidStack recipeOutput = curRecipe.getOutputFluids(this).get(0);
        if (outputTank.getSpace() < recipeOutput.getAmount()) {
            return false;
        }
        return FluidHelper.fluidsEqual(output, recipeOutput);
    }
    // endregion
}
