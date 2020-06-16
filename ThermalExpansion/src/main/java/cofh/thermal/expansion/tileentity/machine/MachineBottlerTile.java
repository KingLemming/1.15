package cofh.thermal.expansion.tileentity.machine;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.tileentity.MachineTileReconfigurableProcess;
import cofh.thermal.expansion.inventory.container.machine.MachineBottlerContainer;
import cofh.thermal.expansion.util.managers.machine.BottlerRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.thermal.core.common.ThermalConfig.machineAugments;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_BOTTLER_TILE;

public class MachineBottlerTile extends MachineTileReconfigurableProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(BottlerRecipeManager.instance()::validItem);
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_MEDIUM, BottlerRecipeManager.instance()::validFluid);

    public MachineBottlerTile() {

        super(MACHINE_BOTTLER_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(inputTank, INPUT);

        addAugmentSlots(machineAugments);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = BottlerRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
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

        return new MachineBottlerContainer(i, world, pos, inventory, player);
    }

}
