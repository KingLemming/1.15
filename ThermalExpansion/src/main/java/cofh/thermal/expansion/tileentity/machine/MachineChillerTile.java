package cofh.thermal.expansion.tileentity.machine;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.tileentity.MachineTileProcess;
import cofh.thermal.expansion.inventory.container.machine.MachineChillerContainer;
import cofh.thermal.expansion.util.managers.machine.ChillerRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.references.CoFHTags.Items.MACHINE_CASTS;
import static cofh.thermal.core.common.ThermalConfig.machineAugments;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CHILLER_TILE;

public class MachineChillerTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(ChillerRecipeManager.instance()::validItem);
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_MEDIUM, ChillerRecipeManager.instance()::validFluid);

    public MachineChillerTile() {

        super(MACHINE_CHILLER_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(inputTank, INPUT);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = ChillerRecipeManager.instance().getRecipe(this);
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
        FluidStack prevFluid = renderFluid;
        renderFluid = new FluidStack(inputTank.getFluidStack(), FluidAttributes.BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }

    @Override
    protected void resolveInputs() {

        // Input Items
        if (!itemInputCounts.isEmpty() && !inputSlot.getItemStack().getItem().isIn(MACHINE_CASTS)) {
            inputSlot.modify(-itemInputCounts.get(0));
        }
        // Input Fluids
        for (int i = 0; i < fluidInputCounts.size(); ++i) {
            inputTanks().get(i).modify(-fluidInputCounts.get(i));
        }
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineChillerContainer(i, world, pos, inventory, player);
    }

}
